package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.config.redis.JedisService;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.Company;
import com.zfkj.demo.dao.entity.Organize;
import com.zfkj.demo.dao.mapper.CompanyMapper;
import com.zfkj.demo.dao.repository.CompanyRepository;
import com.zfkj.demo.dao.repository.OrganizationRepository;
import com.zfkj.demo.service.OrganizationService;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/26 16:59
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    SystemUserUtil userUtil;

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Boolean addOrSaveHeadOrganization(Organize organize) {
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
            /**
             * 父级的chil_id 字段为0
             */
            organize.setChildId(0);
            organize.setCompanyId(company.getId().intValue());
            organizationRepository.saveOrUpdate(organize);
            System.out.println("sucess");
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delOrganization(int id) {
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
            LambdaQueryWrapper<Organize> organizeLambdaQueryWrapper = new LambdaQueryWrapper<Organize>()
                    .eq(Organize::getId,id);
            Organize organize = organizationRepository.getOne(organizeLambdaQueryWrapper);
            /**
             * 子集删除
             */
            if (organize.getChildId()!=0){
                organizationRepository.removeById(id);
                System.out.println("sucess");
                return Boolean.TRUE;
            }else {
                /**
                 * 父级删除
                 */
                LambdaQueryWrapper<Organize> organizeLambdaQueryWrapper1 = new LambdaQueryWrapper<Organize>()
                        .eq(Organize::getChildId, id);
                List<Organize> organizes = organizationRepository.list(organizeLambdaQueryWrapper1);
                organizationRepository.removeById(id);
                for (Organize organize1 : organizes) {
                    organizationRepository.removeById(organize1);
                }
                return Boolean.TRUE;
            }


        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }
}
