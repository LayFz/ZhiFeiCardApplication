package com.zfkj.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.zfkj.demo.common.enums.ForSystemEnum;
import com.zfkj.demo.common.enums.SexEnum;
import com.zfkj.demo.common.excel.SelectSheetWriteHandler;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.common.utils.WebUtil;
import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.enums.YesNoEnum;
import com.zfkj.demo.common.exception.BusinessRootRuntimeException;
import com.zfkj.demo.common.exception.Exceptions;
import com.zfkj.demo.dao.repository.*;
import com.zfkj.demo.service.IRoleService;
import com.zfkj.demo.service.IUserInfoService;
import com.zfkj.demo.common.utils.AesUtil;
import com.zfkj.demo.common.utils.AssertUtils;
import com.zfkj.demo.vo.basevo.LoginUser;
import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.reqvo.user.*;
import com.zfkj.demo.vo.respvo.auth.AuthVO;
import com.zfkj.demo.vo.respvo.role.RoleAuthListRespVo;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import com.zfkj.demo.vo.respvo.user.ExportUserRespVo;
import com.zfkj.demo.vo.respvo.user.QueryUserRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAuthRepository roleAuthRepository;

    @Autowired
    private SystemUserUtil userUtil;

    @Autowired
    private AuthRepository authRepository;

    @Transactional
    @Override
    public QueryUserRespVo userSaveUpdate(UserSaveUpdateReqVo reqVo) {
        User user = User.builder().build();
        BeanUtils.copyProperties(reqVo,user);
        if (Objects.nonNull(reqVo.getId())){
            //?????????????????????????????????????????????????????????
            user.setPassword(null);
            user.setAccount(null);
        } else {
            //??????????????????
            //???????????????????????????
            User userByAccountPhone = userRepository.getUserByAccountPhone(reqVo);
            AssertUtils.isTrue(Objects.isNull(userByAccountPhone), Exceptions.UserEX.USER_HAVE);
            //??????user????????????
            user.setPassword(AesUtil.encrypt(user.getPassword()));
        }
//        user.setLastLogin(new Date());
        System.out.println(user+"ipipiip");
        System.out.println(user.getId()+"ipipiip");
        userRepository.saveOrUpdate(user);
        //??????????????????
        QueryUserRespVo respVo = QueryUserRespVo.builder().build();
        BeanUtils.copyProperties(user,respVo);
        return respVo;
    }

    @Override
    public PageResult<QueryUserRespVo> queryUser(QueryUserReqVo reqVo) {
        //??????????????????QueryWrapper??????????????????,??????????????????
        //????????????????????????
//        IPage<User> userPage = new Page<User>(reqVo.getPage(), reqVo.getSize());
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(Objects.nonNull(reqVo.getName()),"name",reqVo.getName());
//        queryWrapper.like(Objects.nonNull(reqVo.getPhone()),"phone",reqVo.getPhone());
//        queryWrapper.like(Objects.nonNull(reqVo.getAccount()),"account",reqVo.getAccount());
//        queryWrapper.like(Objects.nonNull(reqVo.getAddress()),"address",reqVo.getAddress());
//        queryWrapper.like(Objects.nonNull(reqVo.getRemark()),"remark",reqVo.getRemark());
//        queryWrapper.orderByDesc("id");
//        userRepository.getBaseMapper().selectPage(userPage, queryWrapper);

        //??????????????????mapper.xml?????????sql???????????????????????????
        IPage<QueryUserRespVo> userPage = new Page<QueryUserRespVo>(reqVo.getPageNum(), reqVo.getPageSize());
        userRepository.getBaseMapper().queryUser(userPage,reqVo);
        userPage.getRecords().forEach(u->{
            //??????????????????
            List<String> roleList = Lists.newArrayList();
            List<UserRole> userRoles = userRoleRepository.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, u.getId()));
            if (CollectionUtils.isNotEmpty(userRoles)){
                //??????????????????
                userRoles.forEach(ur->{
                    Role role = roleRepository.getById(ur.getRoleId());
                    if (Objects.nonNull(role)){
                        roleList.add(role.getRoleName());
                    }
                });
            }
            u.setRoleName(roleList.stream().map(String::valueOf).collect(Collectors.joining(",")));
        });

        PageResult pageResult = new PageResult(reqVo.getPageNum(), reqVo.getPageSize(), userPage.getTotal(), userPage.getRecords());
        return pageResult;
    }

    @Override
    public LoginUser selectUserById(Integer id) {
        LoginUser loginUser = LoginUser.builder().build();
        User userInfo = userRepository.getById(id);
        AssertUtils.isTrue(Objects.nonNull(userInfo),Exceptions.UserEX.ACCOUNT_NOT_FIND);
        BeanUtils.copyProperties(userInfo,loginUser);
        if (Objects.nonNull(loginUser)){
            //????????????????????????
            List<RoleRespVo> roles = roleService.getRolesByUserId(loginUser.getId());
            if (CollectionUtils.isNotEmpty(roles)){
                loginUser.setRoleList(roles);
            }
        }
        return loginUser;
    }

    @Override
    public void openCloseUser(Integer id, OpenCloseEnum status) {
        User userInfo = userRepository.getById(Long.valueOf(id));
        if (Objects.nonNull(userInfo)){
            userInfo.setStatus(status);
            userRepository.updateById(userInfo);
        }
    }

    @Override
    public void delUsers(List<Integer> ids) {
        //?????????????????????????????????YES
        userRepository.update(Wrappers.<User>lambdaUpdate()
                .set(User::getDelFlag, YesNoEnum.YES)
                .in(User::getId,ids));
    }

    @Override
    @Transactional(rollbackFor = BusinessRootRuntimeException.class)
    public Boolean addUserRoles(AddUserRolesReqVo reqVo) {
        //??????????????????????????????????????????
        userRoleRepository.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId,reqVo.getUserId()));
        if (CollectionUtils.isEmpty(reqVo.getRoleIds())) {
            return Boolean.TRUE;
        }
        List<UserRole> list = Lists.newArrayList();
        reqVo.getRoleIds().forEach(roleId -> list.add(UserRole.builder()
                .userId(reqVo.getUserId())
                .roleId(roleId)
                .build()));
        userRoleRepository.saveBatch(list);
        return Boolean.TRUE;
    }

    @Override
    public UserInfoVO loginByAccountOrPhone(LoginUserInfoVO loginUserInfoVO) {
        // ????????????
        String decrypt = AesUtil.encrypt(loginUserInfoVO.getPassword());
        loginUserInfoVO.setPassword(decrypt);

        // ??????????????????????????????
        User userInfo = userRepository.getBaseMapper().loginByAccountOrPhone(loginUserInfoVO);
        // ??????????????????
        AssertUtils.notNull(userInfo,Exceptions.LoginEX.USER_ERROR);
        // ??????????????????????????????
        AssertUtils.isTrue(StrUtil.equalsIgnoreCase(userInfo.getStatus().getCode(), OpenCloseEnum.OPEN.getCode()),Exceptions.LoginEX.USER_STATUS_ERROR);
        UserInfoVO userInfoVO = BeanUtil.copyProperties(userInfo, UserInfoVO.class);

        // ??????????????????
//        userInfo.setLastLogin(new Date());
        System.out.println(userInfo.getId()+"ididididid");
        System.out.println(userInfo+"ididididid");
//        System.out.println(userInfo);
//        System.out.println("12315613516531315");
//        userRepository.saveOrUpdate(userInfo);
        // ??????????????????
        List<RoleRespVo> rolesByUserId = roleService.getRolesByUserId(userInfo.getId());
        // ?????????????????????
        List<RoleRespVo> roles = rolesByUserId.stream().filter(role -> StrUtil.equals(role.getIsValid().getCode(), YesNoEnum.YES.getCode())).collect(Collectors.toList());


        // ???????????????
        if(roles.isEmpty()){
            return userInfoVO;
        }
        userInfoVO.setRoles(roles);
        System.out.println(roles+"roles");
        // ??????API ????????????
        List<Long> roleIds = new ArrayList<>();
        roles.forEach(role -> {
            if(Objects.nonNull(role)){
                roleIds.add(role.getId());
            }
        });


        /**
         * ????????????
         */
        List<Auth> authList = new ArrayList<>();
        for (int i = 0; i < roleIds.size(); i++) {
            LambdaQueryWrapper<RoleAuth> authLambdaQueryWrapper = new LambdaQueryWrapper<RoleAuth>()
                    .eq(RoleAuth::getRoleId, roleIds.get(i));
            List<RoleAuth> roleAuths = roleAuthRepository.list(authLambdaQueryWrapper);
            for (RoleAuth roleAuth : roleAuths) {
                LambdaQueryWrapper<Auth> authLambdaQueryWrapper1 = new LambdaQueryWrapper<Auth>()
                        .eq(Auth::getId, roleAuth.getAuthId());
                Auth auth = authRepository.getOne(authLambdaQueryWrapper1);
                authList.add(auth);
            }

        }
        List<AuthVO> authVOS = new ArrayList<>();
        for (Auth auth : authList) {
            AuthVO authVO = new AuthVO();
            authVO.setApi(auth.getUrl());
            authVO.setIcon(auth.getIcon());
            authVO.setCreateId(auth.getCreateId());
            authVO.setForSystem(auth.getForSystem().getName());
            authVO.setName(auth.getName());
            authVO.setLevel(auth.getLevel());
            authVO.setUpdateId(auth.getUpdateId());
            authVOS.add(authVO);
        }
        /**
         * ?????????????????????userInfo
         */
        userInfoVO.setApiAuth(authVOS);
        return userInfoVO;
    }

    @Override
    public List<RoleAuthListRespVo> getUserMenus(ForSystemEnum forSystem) {
        //???????????????????????????????????????
        UserInfoVO loginUser = userUtil.getLoginUser();
        List<Long> roleIds = loginUser.getRoles().stream().map(RoleRespVo::getId).collect(Collectors.toList());
        return roleService.roleAuthList(roleIds,forSystem);
    }

    @Override
    public void importUser(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), ImportUserReqVo.class, new PageReadListener<ImportUserReqVo>(dataList -> {
            //??????excel????????????????????????
            dataList.forEach(u->{
                UserSaveUpdateReqVo vo = new UserSaveUpdateReqVo();
                BeanUtils.copyProperties(u,vo);
                //???????????????????????????
                userSaveUpdate(vo);
            });
        })).sheet().doRead();
    }

    @Override
    public void exportTemplete(List<QueryUserRespVo> list,Class clas) throws IOException {
        //?????????????????????
        HttpServletResponse response = WebUtil.getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "????????????";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20") + ".xlsx");

        // ??????????????????????????????????????????????????????????????????
        Map<Integer, String[]> selectMap = new HashMap<>();

        Field[] fields = clas.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if (Objects.equals("sex",name)){
                //??????
                String[] sexArr = {SexEnum.MAN.getName(),SexEnum.WOMAN.getName()};
                selectMap.put(i, sexArr);
            }else if (Objects.equals("status",name)){
                //??????
                String[] statusArr = {OpenCloseEnum.OPEN.getName(),OpenCloseEnum.CLOSE.getName()};
                selectMap.put(i, statusArr);
            }
        }

        //??????excel
        EasyExcel.write(response.getOutputStream(), clas)
                .registerWriteHandler(new SelectSheetWriteHandler(selectMap))
                .sheet(fileName)
                .doWrite(list);
    }

    @Override
    public void exportUser(QueryUserReqVo reqVo) throws IOException {
        //???????????????????????????????????????
        reqVo.setPageSize(-1);
        PageResult<QueryUserRespVo> page = queryUser(reqVo);
        List<QueryUserRespVo> list = page.getList();
        exportTemplete(list, ExportUserRespVo.class);
    }

}
