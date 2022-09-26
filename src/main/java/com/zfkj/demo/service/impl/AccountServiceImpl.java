package com.zfkj.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.config.redis.JedisService;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.dao.repository.RoleRepository;
import com.zfkj.demo.dao.repository.UserRepository;
import com.zfkj.demo.dao.repository.UserRoleRepository;
import com.zfkj.demo.service.AccountManageService;
import com.zfkj.demo.service.IUserInfoService;
import com.zfkj.demo.vo.reqvo.user.AddUserRolesReqVo;
import com.zfkj.demo.vo.reqvo.user.UserSaveUpdateReqVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountManageService {

    @Autowired
    JedisService jedisService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    IUserInfoService iUserInfoService;

    @Autowired
    SystemUserUtil systemUserUtil;


//    @Override
//    public UserInfoVO getToken(HttpServletRequest request) throws Exception{
//        // 用户请求认证校验
//        // 获取请求头 - token
//        String token = request.getHeader(Constants.AUTH_HEADER);
//        AssertUtils.notNull(token, Exceptions.LoginEX.NO_LOGIN);
//        // 检验用户token
//        String cacheObject = jedisService.getJson(Constants.LOGIN_CODE_KEY + token);
//        AssertUtils.notNull(cacheObject, Exceptions.LoginEX.NO_LOGIN);
//        System.out.println(cacheObject+"cacheObject");
//        // 返回API权限
//        return JSONUtil.toBean(cacheObject, UserInfoVO.class);
//
//    }


//    String user_name = String.valueOf(userInfoVO.getName());
//    @Override
//    public List<Account> getAllAccount() {
//        //获取该userid创建的用户
//            LambdaQueryWrapper<User> userList= new LambdaQueryWrapper<User>()
//                    .eq(User::getUpdateId,user_id);
//            Account account = Account.builder().build();
//
//            List<User> users = userRepository.list(userList);
//        for (User user : users) {
//            LambdaQueryWrapper<UserRole> userRole = new LambdaQueryWrapper<UserRole>().
//                    eq(UserRole::getUserId, user.getId());
//            List<UserRole> userRoles = userRoleRepository.list(userRole);
//
//            account.setName(user.getName());
//            account.setPhone(user.getPhone());
//            account.setAdminname(user_name);
//            account.setCreateTime(user.getCreateTime());
//            account.setRole();
//        }
//
//
//
//
//    }

    @Override
    public Boolean addOrUpdateAccount(Account reqVo){
        UserInfoVO userInfoVO = systemUserUtil.getLoginUser();
        String user_id = String.valueOf(userInfoVO.getId());
        AddUserRolesReqVo userRoles = AddUserRolesReqVo.builder().build();
        //查询公司管理员创建的角色id
        LambdaQueryWrapper<Role> roleListwrapper = new LambdaQueryWrapper<Role>()
                .eq(Role::getCreateId,user_id)
                .eq(Role::getRoleName,reqVo.getRole());
        List<Role> roleList = roleRepository.list(roleListwrapper);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getPhone,reqVo.getPhone());
        //查询账号是否新建
        if (userLambdaQueryWrapper != null){
            //构建存储对象
            UserSaveUpdateReqVo user = new UserSaveUpdateReqVo();
            user.setName(reqVo.getName());
            //默认手机号后六位为账号密码
            String password = reqVo.getPhone();
            user.setPassword(password.substring(password.length()-6));
            user.setPhone(reqVo.getPhone());
            //创建账号
            iUserInfoService.userSaveUpdate(user);
            //获取账号id
            LambdaQueryWrapper<User> userList = new LambdaQueryWrapper<User>()
                    .eq(User::getPhone,reqVo.getPhone());
            User users = (User) userRepository.list(userList);
            userRoles.setUserId(users.getId().intValue());
            //
            List<Integer> ids = new ArrayList<>();
            for (Role role : roleList) {
                ids.add(role.getId().intValue());
            }
            userRoles.setRoleIds(ids);
            //赋予角色
            iUserInfoService.addUserRoles(userRoles);

            return Boolean.TRUE;

        }else{
            UserSaveUpdateReqVo user = new UserSaveUpdateReqVo();
            user.setName(reqVo.getName());
            user.setPassword(reqVo.getPhone());
            user.setPhone(reqVo.getPhone());
            //更新账号信息
            iUserInfoService.userSaveUpdate(user);
            //获取账号id
            User users = (User) userRepository.list(userLambdaQueryWrapper);
            userRoles.setUserId(users.getId().intValue());
            //
            List<Integer> ids = new ArrayList<>();
            for (Role role : roleList) {
                ids.add(role.getId().intValue());
            }
            userRoles.setRoleIds(ids);
            //赋予角色
            iUserInfoService.addUserRoles(userRoles);
            return Boolean.TRUE;
        }
    }

    /**
     * 删除账号
     * @param ids
     * @return
     */
    @Override
    public Boolean delAccount(List<Integer> ids) {
       userRepository.removeByIds(ids);
       return Boolean.TRUE;
    }

    @Override
    public Boolean resetPassword(Account reqVo) {
        UserInfoVO userInfoVO = systemUserUtil.getLoginUser();
        String user_id = String.valueOf(userInfoVO.getId());

        AddUserRolesReqVo userRoles = AddUserRolesReqVo.builder().build();

        LambdaQueryWrapper<Role> roleListwrapper = new LambdaQueryWrapper<Role>()
                .eq(Role::getCreateId,user_id)
                .eq(Role::getRoleName,reqVo.getRole());
        List<Role> roleList = roleRepository.list(roleListwrapper);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getPhone,reqVo.getPhone());
        UserSaveUpdateReqVo user = new UserSaveUpdateReqVo();

        user.setName(reqVo.getName());
        String password = reqVo.getPhone();
        user.setPassword(password.substring(password.length()-6));
        user.setPhone(reqVo.getPhone());
        //更新账号信息
        iUserInfoService.userSaveUpdate(user);
        //获取账号id
        User users = (User) userRepository.list(userLambdaQueryWrapper);
        userRoles.setUserId(users.getId().intValue());
        //
        List<Integer> ids = new ArrayList<>();
        for (Role role : roleList) {
            ids.add(role.getId().intValue());
        }
        userRoles.setRoleIds(ids);
        //赋予角色
        iUserInfoService.addUserRoles(userRoles);
        return Boolean.TRUE;
    }
}
