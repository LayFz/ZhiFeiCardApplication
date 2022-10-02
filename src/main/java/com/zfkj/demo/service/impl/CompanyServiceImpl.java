package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.utils.AesUtil;
import com.zfkj.demo.dao.entity.Company;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.dao.entity.UserRole;
import com.zfkj.demo.dao.repository.*;
import com.zfkj.demo.service.CompanyService;
import com.zfkj.demo.service.IRoleService;
import com.zfkj.demo.service.IUserInfoService;

import com.zfkj.demo.vo.reqvo.user.AddUserRolesReqVo;
import com.zfkj.demo.vo.reqvo.user.UserSaveUpdateReqVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    UserRoleRepository userRoleRepository;



    @Override
    public List<Company> getCompanyList() {
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .orderBy(true,false,Company::getCreateId);
        return companyRepository.list(companyLambdaQueryWrapper);
    }

    @Override
    public Boolean addOrUpdateCompany(Company reqVo) {
        /**
         * 1.查询是否存在该公司（存在则失败）
         * 2.查询管理员账号（若账号已添加则不修改）
         * 3.完成添加并赋予角色
         */
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getName,reqVo.getName());
        /**
         * 公司不存在，添加模块
         */
        if (companyRepository.getOne(companyLambdaQueryWrapper)==null){
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                    .eq(User::getPhone,reqVo.getPhone());
            /**
             * 用户不存在
             */
            if (userRepository.getOne(userLambdaQueryWrapper)==null){
                User user = new User();
                user.setName(reqVo.getAdminName());
                user.setPassword(AesUtil.encrypt(reqVo.getPhone().substring(reqVo.getPhone().length()-6)));
                user.setAccount(reqVo.getPhone());
                user.setPhone(reqVo.getPhone());
                user.setRemark(reqVo.getNickname()+"旗下管理员");
                userRepository.save(user);
                /**
                 * 赋权
                 */
                LambdaQueryWrapper<User> reuserWrapper = new LambdaQueryWrapper<User>()
                        .eq(User::getPhone,user.getPhone());
                user = userRepository.getOne(reuserWrapper);
                UserRole userRole = new UserRole();
                userRole.setRoleId(2);
                userRole.setUserId(user.getId().intValue());
                userRoleRepository.save(userRole);
                reqVo.setUserId(user.getId().intValue());
                reqVo.setIsVaild(OpenCloseEnum.OPEN);
                companyRepository.saveOrUpdate(reqVo);
                return Boolean.TRUE;
            }else {
                /**
                 * 账号存在则只进行赋权操作
                 */
                User user = userRepository.getOne(userLambdaQueryWrapper);
                System.out.println(user);
                LambdaQueryWrapper<User> reuserWrapper = new LambdaQueryWrapper<User>()
                        .eq(User::getPhone,user.getPhone());
                user = userRepository.getOne(reuserWrapper);
                UserRole userRole = new UserRole();
                userRole.setRoleId(2);
                userRole.setUserId(user.getId().intValue());
                userRoleRepository.save(userRole);
                reqVo.setUserId(user.getId().intValue());
                reqVo.setIsVaild(OpenCloseEnum.OPEN);
                companyRepository.saveOrUpdate(reqVo);
                return Boolean.TRUE;

            }
        }else {
            /**
             * 修改
             */
            /**
             * 若电话没有修改
             */
            Company company = companyRepository.getOne(companyLambdaQueryWrapper);
            company.setPhone(reqVo.getPhone());
            company.setName(reqVo.getName());
            company.setNickname(reqVo.getNickname());
            company.setValidData(reqVo.getValidData());
            company.setLogoImg(reqVo.getLogoImg());
            company.setAdminName(reqVo.getAdminName());
            companyRepository.saveOrUpdate(company);
            System.out.println("已经存在该公司");
            return Boolean.TRUE;
        }

    }

    @Override
    public List<Company> selectCompanyBy(String reqVo, OpenCloseEnum openCloseEnum) {
        if (openCloseEnum != null){
            LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                    .like(Company::getNumber,reqVo)
                    .eq(Company::getIsVaild, openCloseEnum.getCode())
                    .or()
                    .like(Company::getPhone,reqVo)
                    .eq(Company::getIsVaild, openCloseEnum.getCode())
                    .or()
                    .like(Company::getName, reqVo)
                    .eq(Company::getIsVaild, openCloseEnum.getCode())
                    .or()
                    .like(Company::getNickname, reqVo)
                    .eq(Company::getIsVaild, openCloseEnum.getCode())
                    .or()
                    .like(Company::getAdminName, reqVo)
                    .eq(Company::getIsVaild, openCloseEnum.getCode());
            return companyRepository.list(companyLambdaQueryWrapper);
        }else {
            LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                    .like(Company::getNumber,reqVo)
                    .or()
                    .like(Company::getPhone,reqVo)
                    .or()
                    .like(Company::getName, reqVo)
                    .or()
                    .like(Company::getNickname, reqVo)
                    .or()
                    .like(Company::getAdminName, reqVo);
            return companyRepository.list(companyLambdaQueryWrapper);
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
        for (Integer id : ids) {
            LambdaQueryWrapper<Company> companyLambdaQueryWrapper =  new LambdaQueryWrapper<Company>()
                    .eq(Company::getId, id);
            Company company = companyRepository.getOne(companyLambdaQueryWrapper);
            LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<UserRole>()
                    .eq(UserRole::getUserId,company.getUserId());
            /**
             * 删除角色和公司
             */
            List<UserRole> userRoles = userRoleRepository.list(userRoleLambdaQueryWrapper);
            companyRepository.removeById(company);
            for (UserRole userRole : userRoles) {
                userRoleRepository.removeById(userRole);
            }
            return Boolean.TRUE;
        }
       return Boolean.TRUE;
    }
}
