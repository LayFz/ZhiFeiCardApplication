package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.dao.entity.Company;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.dao.repository.CompanyRepository;
import com.zfkj.demo.dao.repository.UserRepository;
import com.zfkj.demo.dao.repository.UserRoleRepository;
import com.zfkj.demo.service.CompanyService;
import com.zfkj.demo.service.IRoleService;
import com.zfkj.demo.service.IUserInfoService;

import com.zfkj.demo.vo.reqvo.user.AddUserRolesReqVo;
import com.zfkj.demo.vo.reqvo.user.UserSaveUpdateReqVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IUserInfoService iUserInfoService;



    @Override
    public List<Company> getCompanyList() {
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .orderBy(true,false,Company::getCreateId);
        System.out.println("查询结果："+companyLambdaQueryWrapper);
        return companyRepository.list(companyLambdaQueryWrapper);
    }

    @Override
    public Boolean addOrUpdateCompany(Company reqVo) {
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>().
                eq(Company::getNumber,reqVo.getNumber());
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getPhone,reqVo.getPhone());
        Company company = Company.builder().build();
        if (companyLambdaQueryWrapper==null){
            //判断管理员是否有账号
            if (userLambdaQueryWrapper==null){
                UserSaveUpdateReqVo user = new UserSaveUpdateReqVo();
                //默认手机号为管理员账号
                user.setPhone(reqVo.getPhone());
                //默认手机号后六位为管理员密码
                String password = reqVo.getPhone();
                user.setPassword(password.substring(password.length()-6));
                //创建企业管理员用户
                iUserInfoService.userSaveUpdate(user);
                //赋予企业管理员角色
                iUserInfoService.addUserRoles(null);

                //公司绑定管理员
                LambdaQueryWrapper<User> usercompany = new LambdaQueryWrapper<User>()
                        .eq(User::getPhone,reqVo.getPhone());
                User users  = (User) userRepository.list(usercompany);
                //构建存储对象
                company.setId(reqVo.getId());
                company.setName(reqVo.getName());
                company.setNickname(reqVo.getNickname());
                company.setLogoImg(reqVo.getLogoImg());
                company.setAdminName(reqVo.getAdminName());
                company.setPhone(reqVo.getPhone());
                company.setVaildData(reqVo.getVaildData());
                company.setUserId(users.getId().toString());
                companyRepository.saveOrUpdate(company);
                return Boolean.TRUE;
            }else{
                //公司绑定管理员
                LambdaQueryWrapper<User> usercompany = new LambdaQueryWrapper<User>()
                        .eq(User::getPhone,reqVo.getPhone());
                User users  = (User) userRepository.list(usercompany);
                //构建存储对象
                company.setId(reqVo.getId());
                company.setName(reqVo.getName());
                company.setNickname(reqVo.getNickname());
                company.setLogoImg(reqVo.getLogoImg());
                company.setAdminName(reqVo.getAdminName());
                company.setPhone(reqVo.getPhone());
                company.setVaildData(reqVo.getVaildData());
                company.setUserId(users.getId().toString());
                companyRepository.saveOrUpdate(company);
                return Boolean.TRUE;
            }
        }else{
            if (userLambdaQueryWrapper==null){
                UserSaveUpdateReqVo user = new UserSaveUpdateReqVo();
                //默认手机号为管理员账号
                user.setPhone(reqVo.getPhone());
                //默认手机号后六位为管理员密码
                String password = reqVo.getPhone();
                user.setPassword(password.substring(password.length()-6));
                iUserInfoService.userSaveUpdate(user);
                //赋予管理员权限
                iUserInfoService.addUserRoles(null);

                //公司绑定管理员
                LambdaQueryWrapper<User> usercompany = new LambdaQueryWrapper<User>()
                        .eq(User::getPhone,reqVo.getPhone());
                User users  = (User) userRepository.list(usercompany);
                //构建存储对象
                company.setName(reqVo.getName());
                company.setNickname(reqVo.getNickname());
                company.setLogoImg(reqVo.getLogoImg());
                company.setAdminName(reqVo.getAdminName());
                company.setPhone(reqVo.getPhone());
                company.setVaildData(reqVo.getVaildData());
                company.setUserId(users.getId().toString());
                companyRepository.saveOrUpdate(company);
                return Boolean.TRUE;
            }else{
                //公司绑定管理员
                LambdaQueryWrapper<User> usercompany = new LambdaQueryWrapper<User>()
                        .eq(User::getPhone,reqVo.getPhone());
                User users  = (User) userRepository.list(usercompany);
                //构建存储对象
                company.setName(reqVo.getName());
                company.setNickname(reqVo.getNickname());
                company.setLogoImg(reqVo.getLogoImg());
                company.setAdminName(reqVo.getAdminName());
                company.setPhone(reqVo.getPhone());
                company.setVaildData(reqVo.getVaildData());
                company.setUserId(users.getId().toString());
                companyRepository.saveOrUpdate(company);
                return Boolean.TRUE;
            }
        }
    }

    @Override
    public List<Company> selectCompanyBy(String number, String adminName, String phone,OpenCloseEnum status) {
        if (status.toString()!=null) {
            LambdaQueryWrapper<Company> selectCompanyBy = new LambdaQueryWrapper<Company>()
                    .eq(Company::getIsVaild,status)
                    .or().eq(Company::getNumber,number)
                    .or().eq(Company::getAdminName,adminName)
                    .or().eq(Company::getPhone,phone);
            return companyRepository.list(selectCompanyBy);

        }else{
            LambdaQueryWrapper<Company> selectCompanyBy = new LambdaQueryWrapper<Company>()
                    .or().eq(Company::getNumber,number)
                    .or().eq(Company::getAdminName,adminName)
                    .or().eq(Company::getPhone,phone);
            return companyRepository.list(selectCompanyBy);
        }
    }

    @Override
    public void openCloseCompany(Integer id, OpenCloseEnum status) {
        Company companyInfo = companyRepository.getById(Long.valueOf(id));
        if (Objects.nonNull(companyInfo)){
           companyInfo.setIsVaild(status);
            companyRepository.updateById(companyInfo);
        }
    }

    @Override
    public Boolean delCompany(List<Integer> ids) {
       companyRepository.removeByIds(ids);
       return Boolean.TRUE;
    }
}
