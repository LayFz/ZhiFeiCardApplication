package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.ArticleClassif;
import com.zfkj.demo.dao.entity.Banner;
import com.zfkj.demo.dao.entity.Company;
import com.zfkj.demo.dao.entity.CompanyIntro;
import com.zfkj.demo.dao.repository.BannerRepository;
import com.zfkj.demo.dao.repository.CompanyIntroRepository;
import com.zfkj.demo.dao.repository.CompanyRepository;
import com.zfkj.demo.service.CompanyIntroService;
import com.zfkj.demo.vo.reqvo.companyIntro.delIntroReVo;
import com.zfkj.demo.vo.reqvo.companyIntro.saveIntroReVo;
import com.zfkj.demo.vo.reqvo.companyIntro.upDownIntroReVo;
import com.zfkj.demo.vo.respvo.companyIntro.introRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyIntroServiceImpl implements CompanyIntroService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SystemUserUtil userUtil;

    @Autowired
    CompanyIntroRepository introRepository;

    @Autowired
    BannerRepository bannerRepository;

    @Override
    public List<introRespVo> getCompanyIntroList() {
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
            Long companyId = company.getId();
            //查询公司简介分类
            LambdaQueryWrapper<CompanyIntro> classifLambdaQuery = new LambdaQueryWrapper<CompanyIntro>()
                    .eq(CompanyIntro::getCompanyId,companyId)
                    .orderBy(true,true,CompanyIntro::getLevel);
            List<CompanyIntro> introList = introRepository.list(classifLambdaQuery);
            List<introRespVo> resultList = new ArrayList<>();
            System.out.println("Size:"+introList.size());
            for (CompanyIntro companyIntro : introList) {
                introRespVo respVo = introRespVo.builder().build();
                respVo.setName(companyIntro.getName());
                respVo.setAdminName(loginUser.getName());
                respVo.setCreatime(companyIntro.getCreateTime());
                resultList.add(respVo);
            }
            return resultList;

        }else {
            System.out.println("您已到期！");
            return null;

        }
    }


    @Override
    public Boolean addCompanyIntro(saveIntroReVo reVo) {
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
            LambdaQueryWrapper<CompanyIntro> classifLambda = new LambdaQueryWrapper<CompanyIntro>()
                    .eq(CompanyIntro::getCompanyId,company.getId())
                    .orderBy(true,false,CompanyIntro::getLevel);
            int level = introRepository.list(classifLambda).get(0).getLevel();

            LambdaQueryWrapper<Banner> bannerLambda = new LambdaQueryWrapper<Banner>()
                    .and(i->i.eq(Banner::getCompanyId,company.getId()).eq(Banner::getRemark,"个性化简介"));
            Banner banner = bannerRepository.getOne(bannerLambda);
            Long bannerId = banner.getId();

            CompanyIntro companyIntro = CompanyIntro.builder().build();
            companyIntro.setId(reVo.getId());
            companyIntro.setCompanyId(company.getId());
            companyIntro.setBelongBannerId(bannerId);
            companyIntro.setName(reVo.getName());
            companyIntro.setContent(reVo.getContent());
            companyIntro.setLevel(level+1);

            introRepository.save(companyIntro);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updataCompanyIntro(saveIntroReVo reVo) {
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
            LambdaQueryWrapper<CompanyIntro> classifLambda = new LambdaQueryWrapper<CompanyIntro>()
                    .eq(CompanyIntro::getCompanyId,company.getId())
                    .orderBy(true,false,CompanyIntro::getLevel);
            int level = introRepository.list(classifLambda).get(0).getLevel();

            LambdaQueryWrapper<Banner> bannerLambda = new LambdaQueryWrapper<Banner>()
                    .and(i->i.eq(Banner::getCompanyId,company.getId()).eq(Banner::getRemark,"个性化简介"));
            Banner banner = bannerRepository.getOne(bannerLambda);
            Long bannerId = banner.getId();

            CompanyIntro companyIntro = CompanyIntro.builder().build();
            companyIntro.setId(reVo.getId());
            companyIntro.setCompanyId(company.getId());
            companyIntro.setBelongBannerId(bannerId);
            companyIntro.setName(reVo.getName());
            companyIntro.setContent(reVo.getContent());
            companyIntro.setLevel(level);

            introRepository.saveOrUpdate(companyIntro);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delCompanyIntro(delIntroReVo reVo) {
        LambdaQueryWrapper<CompanyIntro> classifLambda = new LambdaQueryWrapper<CompanyIntro>()
                .eq(CompanyIntro::getId,reVo.getId());

        CompanyIntro companyIntro = introRepository.getOne(classifLambda);
        Long id = companyIntro.getId();
        System.out.println("Id:"+id);

        introRepository.removeById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean upIntroLevel(upDownIntroReVo reVo) {
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
            LambdaQueryWrapper<CompanyIntro> classifLambda = new LambdaQueryWrapper<CompanyIntro>()
                    .eq(CompanyIntro::getId,reVo.getId());
            int level = introRepository.getOne(classifLambda).getLevel();
            CompanyIntro companyIntro = CompanyIntro.builder().build();
            companyIntro.setId(reVo.getId());
            companyIntro.setCompanyId(company.getId());
            companyIntro.setLevel(level-1);

            introRepository.saveOrUpdate(companyIntro);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean downIntroLevel(upDownIntroReVo reVo) {
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
            LambdaQueryWrapper<CompanyIntro> classifLambda = new LambdaQueryWrapper<CompanyIntro>()
                    .eq(CompanyIntro::getId,reVo.getId());
            int level = introRepository.getOne(classifLambda).getLevel();
            CompanyIntro companyIntro = CompanyIntro.builder().build();
            companyIntro.setId(reVo.getId());
            companyIntro.setCompanyId(company.getId());
            companyIntro.setLevel(level+1);

            introRepository.saveOrUpdate(companyIntro);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }
}
