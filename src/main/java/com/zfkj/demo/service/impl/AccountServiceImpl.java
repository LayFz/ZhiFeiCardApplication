package com.zfkj.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.config.redis.JedisService;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.enums.YesNoEnum;
import com.zfkj.demo.common.exception.Exceptions;
import com.zfkj.demo.common.utils.AesUtil;
import com.zfkj.demo.common.utils.AssertUtils;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.dao.repository.CompanyRepository;
import com.zfkj.demo.dao.repository.RoleRepository;
import com.zfkj.demo.dao.repository.UserRepository;
import com.zfkj.demo.dao.repository.UserRoleRepository;
import com.zfkj.demo.service.AccountManageService;
import com.zfkj.demo.service.IUserInfoService;
import com.zfkj.demo.vo.reqvo.account.*;
import com.zfkj.demo.vo.respvo.account.accountRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountManageService {

    @Autowired
    JedisService jedisService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SystemUserUtil userUtil;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    IUserInfoService iUserInfoService;

    @Autowired
    SystemUserUtil systemUserUtil;


    @Override
    public List<accountRespVo> getAccountList() {
        List<User> userList = userRepository.list();
        List<accountRespVo> resultList  = new ArrayList<>();
        for (int i=0;i<userList.size();i++){
            accountRespVo account = new accountRespVo();
            account.setId(userList.get(i).getId());
            account.setName(userList.get(i).getName());
            account.setPhone(userList.get(i).getPhone());
            LambdaQueryWrapper<UserRole> userRoleLambda = new LambdaQueryWrapper<UserRole>()
                    .eq(UserRole::getUserId,userList.get(i).getId());
            UserRole userRole = userRoleRepository.getOne(userRoleLambda);
            System.out.println("userRole:"+userRole);
            if (userRole!=null){
                int userRoleId = userRole.getRoleId();
                LambdaQueryWrapper<Role> roleLambda = new LambdaQueryWrapper<Role>()
                        .eq(Role::getId,userRoleId);
                String roleName = roleRepository.getOne(roleLambda).getRoleName();
                account.setRole(roleName);
            }else {
                account.setRole("/");
            }
            account.setCreatime(userList.get(i).getCreateTime());
            resultList.add(account);
        }
        return resultList;

    }

    @Override
    public List<accountRespVo> selectAccount(String reVo) {
            LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                    .eq(User::getPhone,reVo)
                    .or()
                    .eq(User::getName,reVo)
                    .or()
                    .eq(User::getAccount,reVo)
                    .or()
                    .eq(User::getRemark,reVo);
            List<User> userList = userRepository.list(userLambda);
            List<accountRespVo> resultList  = new ArrayList<>();
            for (int i=0;i<userList.size();i++){
                accountRespVo account = new accountRespVo();
                account.setId(userList.get(i).getId());
                account.setName(userList.get(i).getName());
                account.setPhone(userList.get(i).getPhone());
                LambdaQueryWrapper<UserRole> userRoleLambda = new LambdaQueryWrapper<UserRole>()
                        .eq(UserRole::getUserId,userList.get(i).getId());
                UserRole userRole = userRoleRepository.getOne(userRoleLambda);
                System.out.println("userRole:"+userRole);
                if (userRole!=null){
                    int userRoleId = userRole.getRoleId();
                    LambdaQueryWrapper<Role> roleLambda = new LambdaQueryWrapper<Role>()
                            .eq(Role::getId,userRoleId);
                    String roleName = roleRepository.getOne(roleLambda).getRoleName();
                    account.setRole(roleName);
                }else {
                    account.setRole("/");
                }
                account.setCreatime(userList.get(i).getCreateTime());
                resultList.add(account);
            }
            return resultList;
    }

    /**
     * 添加账号
     * @param reqVo
     * @return
     */
    @Override
    public Boolean addAccount(AddUpAccountVo reqVo){
        System.out.println(reqVo);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getAccount, reqVo.getPhone());
        if (userRepository.list(userLambdaQueryWrapper).size()!=0){
            System.out.println("账号已存在");
            return Boolean.FALSE;
        }else {
            User user = User.builder().build();
            user.setName(reqVo.getName());
            user.setPhone(reqVo.getPhone());
            user.setAccount(reqVo.getPhone());
            user.setStatus(OpenCloseEnum.OPEN);
            user.setDelFlag(YesNoEnum.NO);
            //构建user存储对象
            user.setPassword(AesUtil.encrypt(reqVo.getPhone().substring(reqVo.getPhone().length()-6)));
            userRepository.saveOrUpdate(user);
            UserRole userRole = new UserRole();
            LambdaQueryWrapper<User> userLambdaQueryWrapper1 =  new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, reqVo.getPhone());
            user = userRepository.getOne(userLambdaQueryWrapper1);
            userRole.setUserId(user.getId().intValue());
            userRole.setRoleId(reqVo.getRoleId());
            userRoleRepository.saveOrUpdate(userRole);
            return Boolean.TRUE;
        }

    }

    @Override
    public Boolean UpdateAccount(UpdateAccountVo reqVo) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper1 = new LambdaQueryWrapper<User>()
                .eq(User::getId, reqVo.getId());
        User user = userRepository.getOne(userLambdaQueryWrapper1);
        if (user.getPhone().equals(reqVo.getPhone())) {
            user.setName(reqVo.getName());
        } else {
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, reqVo.getPhone());
            if (userRepository.list(userLambdaQueryWrapper).size() != 0) {
                System.out.println("该账号已存在，请更换账号名称");
                return Boolean.FALSE;
            } else {
                user.setName(reqVo.getName());
                user.setPhone(reqVo.getPhone());
            }
        }
        userRepository.saveOrUpdate(user);
        /**
         * 查询并更新角色
         */
        LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, user.getId());
        if (userRoleRepository.list(userRoleLambdaQueryWrapper).size() != 0) {
            UserRole userRole = userRoleRepository.getOne(userRoleLambdaQueryWrapper);
            userRole.setRoleId(reqVo.getRoleId());
            userRoleRepository.saveOrUpdate(userRole);
            return Boolean.TRUE;
        } else {
            UserRole userRole = new UserRole();
            userRole.setRoleId(reqVo.getRoleId());
            userRole.setUserId(reqVo.getId());
            userRoleRepository.saveOrUpdate(userRole);
            return Boolean.TRUE;
        }
    }

    /**
     * 删除账号
     * @param reqvo
     * @return
     */
    @Override
    public Boolean delAccount(DelAccountVo reqvo) {
        LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                .eq(User::getId,reqvo.getId());
        User user= userRepository.getOne(userLambda);
        Long userid = user.getId();
        LambdaQueryWrapper<UserRole> userRoleLambda = new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId,reqvo.getId());
        UserRole userRole = userRoleRepository.getOne(userRoleLambda);
        if (userRole!=null){
            Long userRoleid = userRole.getId();
            userRepository.removeById(userid);
            userRoleRepository.removeById(userRoleid);
            return Boolean.TRUE;
        }else {
            userRepository.removeById(userid);
            return Boolean.TRUE;
        }

    }

    @Override
    public Boolean resetPassword(RePassAccountVo reqVo) {
        User user = User.builder().build();
        user.setId(reqVo.getId());
        String password = reqVo.getPhone();
        user.setPassword(AesUtil.encrypt(password.substring(password.length()-6)));
        userRepository.saveOrUpdate(user);
        return Boolean.TRUE;
    }


}
