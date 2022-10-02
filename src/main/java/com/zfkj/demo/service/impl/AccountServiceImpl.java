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
import com.zfkj.demo.vo.reqvo.account.accountByReVo;
import com.zfkj.demo.vo.respvo.account.accountRespVo;
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


    @Override
    public List<accountRespVo> getAccountList() {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        System.out.println("LoginId:"+loginUser.getId());
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getValidData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")){
            LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                    .eq(User::getCreateId,loginUser.getId());
            List<User> userList = userRepository.list(userLambda);
            List<accountRespVo> resultList  = new ArrayList<>();
            for (int i=0;i<userList.size();i++){
                accountRespVo account = accountRespVo.builder().build();
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
                account.setAdminName(loginUser.getName());
                account.setCreatime(userList.get(i).getCreateTime());
                resultList.add(account);
            }
            return resultList;
        }else {
            System.out.println("您已到期！");
            return null;
        }

    }

    @Override
    public List<accountRespVo> selectAccount(accountByReVo reVo) {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        System.out.println("LoginId:"+loginUser.getId());
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getValidData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")){
            LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                    .eq(User::getId,reVo.getId())
                    .or().eq(User::getPhone,reVo.getPhone())
                    .or().eq(User::getName,reVo.getName());
            List<User> userList = userRepository.list(userLambda);
            List<accountRespVo> resultList  = new ArrayList<>();
            for (int i=0;i<userList.size();i++){
                accountRespVo account = accountRespVo.builder().build();
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
                account.setAdminName(loginUser.getName());
                account.setCreatime(userList.get(i).getCreateTime());
                resultList.add(account);
            }
            return resultList;
        }else {
            System.out.println("您已到期！");
            return null;
        }
    }

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
        long vaildTime = company.getValidData().getTime();
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
        long vaildTime = company.getValidData().getTime();
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
        long vaildTime = company.getValidData().getTime();
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
