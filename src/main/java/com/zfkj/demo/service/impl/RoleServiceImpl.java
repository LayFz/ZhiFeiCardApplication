package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.zfkj.demo.dao.entity.Auth;
import com.zfkj.demo.dao.entity.Role;
import com.zfkj.demo.dao.entity.RoleAuth;
import com.zfkj.demo.dao.entity.UserRole;
import com.zfkj.demo.common.enums.ForSystemEnum;
import com.zfkj.demo.dao.repository.AuthRepository;
import com.zfkj.demo.dao.repository.RoleAuthRepository;
import com.zfkj.demo.dao.repository.RoleRepository;
import com.zfkj.demo.dao.repository.UserRoleRepository;
import com.zfkj.demo.service.IRoleService;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.reqvo.role.AddRoleAuthReqVo;
import com.zfkj.demo.vo.reqvo.role.AddUpdateRoleReqVo;
import com.zfkj.demo.vo.reqvo.role.QueryRoleReqVo;
import com.zfkj.demo.vo.respvo.role.RoleAuthListRespVo;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleAuthRepository roleAuthRepository;
    @Autowired
    private SystemUserUtil userUtil;
    @Autowired
    private AuthRepository authRepository;

    @Override
    public List<RoleRespVo> getRolesByUserId(Long userId) {
        List<RoleRespVo> roles = userRoleRepository.getBaseMapper().getRolesByUserId(userId);
        return roles.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Boolean addUpdRole(AddUpdateRoleReqVo reqVo) {
        Role role = Role.builder().build();
        BeanUtils.copyProperties(reqVo,role);
        roleRepository.saveOrUpdate(role);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Boolean delRole(List<Integer> ids) {
        //删除角色的同时要删除角色关联的权限资源，用户关联数据
        roleRepository.removeByIds(ids);
        userRoleRepository.remove(Wrappers.<UserRole>lambdaQuery().in(UserRole::getRoleId,ids));
        roleAuthRepository.remove(Wrappers.<RoleAuth>lambdaQuery().in(RoleAuth::getRoleId,ids));
        return Boolean.TRUE;
    }

    @Override
    public List<RoleAuthListRespVo> roleAuthList(List<Long> roleIds,ForSystemEnum forSystem) {
        //查询权限资源集合
        List<Auth> authList = authRepository.list(Wrappers.<Auth>lambdaQuery().eq(Objects.nonNull(forSystem),Auth::getForSystem,forSystem));

        /*****获取当前角色对应菜单**/
        List<RoleAuth> roleAuthList = roleAuthRepository.list(Wrappers.<RoleAuth>lambdaQuery().in(RoleAuth::getRoleId, roleIds));

        List<Long> authIdList = roleAuthList.stream().map(RoleAuth::getAuthId).distinct().collect(Collectors.toList());

        List<RoleAuthListRespVo> list = new ArrayList<>();
        authList.forEach(n -> {
            RoleAuthListRespVo authRespVo = RoleAuthListRespVo.builder().build();
            BeanUtils.copyProperties(n, authRespVo);
            list.add(authRespVo);
        });

        list.forEach(n -> handleSelect(n, authIdList));
        List<RoleAuthListRespVo> rootList = getRoot(list);
        rootList.forEach(n -> handleAuth(n, list));
        return rootList;
    }

    @Transactional
    @Override
    public Boolean addRoleAuth(AddRoleAuthReqVo reqVo) {
        //删除该角色之前分配的权限资源
        roleAuthRepository.remove(Wrappers.<RoleAuth>lambdaQuery().eq(RoleAuth::getRoleId,reqVo.getRoleId()));
        if (CollectionUtils.isEmpty(reqVo.getAuthIds())) {
            return Boolean.TRUE;
        }
        List<RoleAuth> list = Lists.newArrayList();
        reqVo.getAuthIds().forEach(authId-> list.add(RoleAuth.builder()
                .roleId(reqVo.getRoleId())
                .authId(authId)
                .build()));
        roleAuthRepository.saveBatch(list);
        return Boolean.TRUE;
    }

    @Override
    public PageResult<RoleRespVo> queryRole(@RequestBody QueryRoleReqVo reqVo) {
        List<RoleRespVo> list = Lists.newArrayList();
        IPage<Role> page = new Page<Role>(reqVo.getPageNum(), reqVo.getPageSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Objects.nonNull(reqVo.getRoleName()),"role_name",reqVo.getRoleName());
        queryWrapper.orderByDesc("id");
        roleRepository.getBaseMapper().selectPage(page, queryWrapper);
        page.getRecords().forEach(r->{
            RoleRespVo vo = RoleRespVo.builder().build();
            BeanUtils.copyProperties(r,vo);
            list.add(vo);
        });
        PageResult pageResult = new PageResult(reqVo.getPageNum(), reqVo.getPageSize(), page.getTotal(), list);
        return pageResult;
    }

    @Override
    public List<RoleRespVo> getRoles() {
        List<RoleRespVo> result = Lists.newArrayList();
        List<Role> list = roleRepository.list();
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach(r->{
                RoleRespVo vo = RoleRespVo.builder().build();
                BeanUtils.copyProperties(r,vo);
                result.add(vo);
            });
        }
        return result;
    }

    /**
     * 遍历角色权限id与权限id判断
     *
     * @param auth
     * @param authIdList
     */
    public void handleSelect(RoleAuthListRespVo auth, List<Long> authIdList) {
        authIdList.stream().forEach(n -> handleValidSelect(n, auth));
    }

    /**
     * 处理是否被选中及设置权限url
     *
     * @param id
     * @param auth
     */
    public void handleValidSelect(Long id, RoleAuthListRespVo auth) {
        if (auth.getIsSelect() != null && auth.getIsSelect()) {
            return;
        }

        if (id.equals(auth.getId())) {
            auth.setIsSelect(true);
        }

        if (!id.equals(auth.getId())) {
            auth.setIsSelect(false);
        }
    }

    public List<RoleAuthListRespVo> getRoot(List<RoleAuthListRespVo> authList) {
        Optional<RoleAuthListRespVo> minRoot = authList.stream().min(Comparator.comparing(RoleAuthListRespVo::getLevel));
        if (minRoot.isPresent()) {
            RoleAuthListRespVo root = minRoot.get();
            authList = authList.stream().filter(n -> n.getLevel().intValue() == root.getLevel().intValue()).collect(Collectors.toList());
            return authList;
        } else {
            return new ArrayList<>();
        }
    }

    public void handleAuth(RoleAuthListRespVo root, List<RoleAuthListRespVo> authList) {
        List<RoleAuthListRespVo> children = getChildren(root.getId(), authList);
        if (children == null || children.isEmpty()) {
            return;
        }
        if (CollectionUtils.isEmpty(root.getChilds())){
            root.setChilds(children);
        }else {
            root.getChilds().addAll(children);
        }
        children.forEach(n -> handleAuth(n, authList));
    }

    public List<RoleAuthListRespVo> getChildren(Long id, List<RoleAuthListRespVo> userAuth) {
        return userAuth.stream().filter(n -> n.getParentId().equals(id)).collect(Collectors.toList());
    }


}
