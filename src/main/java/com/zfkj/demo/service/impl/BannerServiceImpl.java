package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.utils.SystemUserUtil;

import com.zfkj.demo.dao.entity.Banner;
import com.zfkj.demo.dao.entity.Company;
import com.zfkj.demo.dao.repository.BannerRepository;
import com.zfkj.demo.dao.repository.CompanyRepository;
import com.zfkj.demo.service.BannerService;
import com.zfkj.demo.vo.reqvo.banner.SaveBannerVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SystemUserUtil userUtil;

    @Autowired
    BannerRepository bannerRepository;

    /**
     * 个性化简介配置
     * @param ReVo
     * @return
     */
    @Override
    public Boolean saveIntroductBanner(SaveBannerVo ReVo) {
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
            Long companyid = company.getId();
            LambdaQueryWrapper<Banner> bannerLambda = new LambdaQueryWrapper<Banner>()
                    .and(i->i.eq(Banner::getCompanyId,companyid).eq(Banner::getRemark,"个性化简介"));
            List<Banner> bannerList = bannerRepository.list(bannerLambda);
            Banner banner = Banner.builder().build();
            if(bannerList.size()<1){
                banner.setId(ReVo.getId());
                banner.setName(ReVo.getName());
                banner.setCompanyId(companyid);
                banner.setImgUrl(ReVo.getImgUrl());
                banner.setIsVaild(OpenCloseEnum.valueOf("CLOSE"));;
                banner.setRemark("个性化简介");
                bannerRepository.save(banner);
                return Boolean.TRUE;
            }else{
                banner.setId(bannerList.get(0).getId());
                banner.setName(ReVo.getName());
                banner.setCompanyId(companyid);
                banner.setImgUrl(ReVo.getImgUrl());
                banner.setRemark("个性化简介");
                bannerRepository.saveOrUpdate(banner);
                return Boolean.TRUE;
            }
        }else {
            System.out.println("您已到期！");
            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean saveContentBanner(SaveBannerVo ReVo) {
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
            Long companyid = company.getId();
            LambdaQueryWrapper<Banner> bannerLambda = new LambdaQueryWrapper<Banner>()
                    .and(i->i.eq(Banner::getCompanyId,companyid).eq(Banner::getRemark,"个性化内容"));
            List<Banner> bannerList = bannerRepository.list(bannerLambda);
            Banner banner = Banner.builder().build();
            if(bannerList.size()<1){
                banner.setId(ReVo.getId());
                banner.setName(ReVo.getName());
                banner.setCompanyId(companyid);
                banner.setImgUrl(ReVo.getImgUrl());
                banner.setIsVaild(OpenCloseEnum.valueOf("CLOSE"));;
                banner.setRemark("个性化内容");
                bannerRepository.save(banner);
                return Boolean.TRUE;
            }else{
                banner.setId(bannerList.get(0).getId());
                banner.setName(ReVo.getName());
                banner.setCompanyId(companyid);
                banner.setImgUrl(ReVo.getImgUrl());
                banner.setRemark("个性化内容");
                bannerRepository.saveOrUpdate(banner);
                return Boolean.TRUE;
            }
        }else {
            System.out.println("您已到期！");
            return Boolean.TRUE;
        }
    }

    @Override
    public List<Banner> getBanners() {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        //获取旗下公司
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //根据公司id获取banner
        LambdaQueryWrapper<Banner> bannerLambdaQueryWrapper = new LambdaQueryWrapper<Banner>()
                .eq(Banner::getCompanyId, company.getId());
        return bannerRepository.list(bannerLambdaQueryWrapper);
    }

    @Override
    public Boolean ChangeIntroBanner() {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        //获取旗下公司
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        LambdaQueryWrapper<Banner> bannerLambdaQueryWrapper = new LambdaQueryWrapper<Banner>()
                .eq(Banner::getCompanyId, company.getId())
                .eq(Banner::getRemark,"个性化简介");
        Banner banner = bannerRepository.getOne(bannerLambdaQueryWrapper);
        String temp = banner.getIsVaild().getCode();
        if (temp.equals("OPEN")){
            banner.setIsVaild(OpenCloseEnum.CLOSE);
        }else {
            banner.setIsVaild(OpenCloseEnum.OPEN);
        }
        bannerRepository.saveOrUpdate(banner);
        return Boolean.TRUE;
    }

    @Override
    public Boolean changeContentBanner() {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        //获取旗下公司
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        LambdaQueryWrapper<Banner> bannerLambdaQueryWrapper = new LambdaQueryWrapper<Banner>()
                .eq(Banner::getCompanyId, company.getId())
                .eq(Banner::getRemark,"个性化内容");
        Banner banner = bannerRepository.getOne(bannerLambdaQueryWrapper);
        String temp = banner.getIsVaild().getCode();
        if (temp.equals("OPEN")){
            banner.setIsVaild(OpenCloseEnum.CLOSE);
        }else {
            banner.setIsVaild(OpenCloseEnum.OPEN);
        }
        bannerRepository.saveOrUpdate(banner);
        return Boolean.TRUE;
    }

    @Override
    public List<Banner> getBannersById(int id) {
        LambdaQueryWrapper<Banner> bannerLambdaQueryWrapper = new LambdaQueryWrapper<Banner>()
                .eq(Banner::getCompanyId,id)
                .eq(Banner::getIsVaild, OpenCloseEnum.OPEN);
        return bannerRepository.list(bannerLambdaQueryWrapper);
    }
}
