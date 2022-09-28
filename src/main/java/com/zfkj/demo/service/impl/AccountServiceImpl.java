package com.zfkj.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.config.redis.JedisService;
import com.zfkj.demo.common.utils.AesUtil;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.dao.repository.CompanyRepository;
import com.zfkj.demo.dao.repository.RoleRepository;
import com.zfkj.demo.dao.repository.UserRepository;
import com.zfkj.demo.dao.repository.UserRoleRepository;
import com.zfkj.demo.service.AccountManageService;
import com.zfkj.demo.service.IUserInfoService;
import com.zfkj.demo.vo.reqvo.account.AddUpAccountVo;
import com.zfkj.demo.vo.reqvo.account.DelAccountVo;
import com.zfkj.demo.vo.reqvo.account.RePassAccountVo;
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
    SystemUserUtil userUtil;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    IUserInfoService iUserInfoService;

    @Autowired
    SystemUserUtil systemUserUtil;

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


    /**
     * 添加账号
     * @param reqVo
     * @return
     */
    @Override
    public Boolean addAccount(AddUpAccountVo reqVo){
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getVaildData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")){
//            //查重id
//            LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>().
//                    eq(User::getId,reqVo.getId());
//            Long id = userRepository.getOne(userLambda).getId();
//            System.out.println(""+id);
            //添加账号在sys_uer表
                User user = User.builder().build();
                user.setId(reqVo.getId());
                user.setName(reqVo.getName());
                user.setPhone(reqVo.getPhone());
                String password = reqVo.getPhone();
                user.setPassword(AesUtil.encrypt(password.substring(password.length()-6)));
                userRepository.save(user);
            //新增sys_user_role
                UserRole userRole = UserRole.builder().build();

                //根据管理员id获取该公司的角色列表
                LambdaQueryWrapper<Role> roleLambda = new LambdaQueryWrapper<Role>().
                        eq(Role::getCreateId,loginUser.getId())
                        .eq(Role::getRoleName,reqVo.getRole());

                Integer userid = reqVo.getId().intValue();
                Integer roleid = roleRepository.getOne(roleLambda).getId().intValue();
                userRole.setUserId(userid);
                userRole.setRoleId(roleid);
                userRoleRepository.save(userRole);
                return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean UpdateAccount(AddUpAccountVo reqVo) {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getVaildData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")){
//            //查重id
//            LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>().
//                    eq(User::getId,reqVo.getId());
//            Long id = userRepository.getOne(userLambda).getId();
//            System.out.println(""+id);
            //添加账号在sys_uer表
            User user = User.builder().build();
            user.setId(reqVo.getId());
            user.setName(reqVo.getName());
            user.setPhone(reqVo.getPhone());
            String password = reqVo.getPhone();
            user.setPassword(AesUtil.encrypt(password.substring(password.length()-6)));
            userRepository.saveOrUpdate(user);
            //新增sys_user_role
            UserRole userRole = UserRole.builder().build();
            //根据管理员id获取该公司的角色列表
            LambdaQueryWrapper<Role> roleLambda = new LambdaQueryWrapper<Role>().
                    eq(Role::getCreateId,loginUser.getId())
                    .eq(Role::getRoleName,reqVo.getRole());
            //根据账号id获取sys_user_role
            LambdaQueryWrapper<UserRole> userRoleLambda = new LambdaQueryWrapper<UserRole>()
                    .eq(UserRole::getUserId,reqVo.getId());
            Long id = userRoleRepository.getOne(userRoleLambda).getId();
            Integer userid = reqVo.getId().intValue();
            Integer roleid = roleRepository.getOne(roleLambda).getId().intValue();
            userRole.setId(id);
            userRole.setUserId(userid);
            userRole.setRoleId(roleid);
            userRoleRepository.saveOrUpdate(userRole);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
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
        Long userRoleid = userRole.getId();
        userRepository.removeById(userid);
        userRoleRepository.removeById(userRoleid);
        return Boolean.TRUE;
    }

    @Override
    public Boolean resetPassword(RePassAccountVo reqVo) {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getVaildData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")){
//
            User user = User.builder().build();
            user.setId(reqVo.getId());
            String password = reqVo.getPhone();
            user.setPassword(AesUtil.encrypt(password.substring(password.length()-6)));
            userRepository.saveOrUpdate(user);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.TRUE;
        }
    }
}
