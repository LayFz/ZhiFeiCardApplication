package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.utils.MiniRoleUtils;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.*;

import com.zfkj.demo.dao.repository.ArticleClassifyRepository;
import com.zfkj.demo.dao.repository.CardDateRepository;
import com.zfkj.demo.dao.repository.CompanyRepository;
import com.zfkj.demo.dao.repository.OrganizationRepository;
import com.zfkj.demo.service.ArticleClassifyService;

import com.zfkj.demo.vo.reqvo.articleClassify.delArcClassifyId;
import com.zfkj.demo.vo.reqvo.articleClassify.saveArcClassifyReVo;
import com.zfkj.demo.vo.reqvo.articleClassify.upDownArcClassifyReVo;
import com.zfkj.demo.vo.respvo.articleClassify.articleClassifyRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleClassifyServiceImpl implements ArticleClassifyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SystemUserUtil userUtil;

    @Autowired
    ArticleClassifyRepository articleClassifyRepository;

    @Autowired
    MiniRoleUtils miniRoleUtils;

    @Autowired
    CardDateRepository cardDateRepository;

    @Autowired
    OrganizationRepository organizationRepository;






    @Override
    public List<ArticleClassify> getArticleClassify() {
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
            //查询公司文章分类
           LambdaQueryWrapper<ArticleClassify> classifLambdaQuery = new LambdaQueryWrapper<ArticleClassify>()
                   .eq(ArticleClassify::getCompanyId,companyId)
                   .orderBy(true,true, ArticleClassify::getLevel);
           List<ArticleClassify> classifList = articleClassifyRepository.list(classifLambdaQuery);
//           List<articleClassifyRespVo> resultList = new ArrayList<>();
//            System.out.println("Size:"+classifList.size());
//           for (int i=0;i<classifList.size();i++){
//               articleClassifyRespVo respvo = articleClassifyRespVo.builder().build();
//               respvo.setId(classifList.get(i).getId().intValue());
//               respvo.setName(classifList.get(i).getName());
//               respvo.setCreatime(classifList.get(i).getCreateTime());
//               respvo.setAdminName(loginUser.getName());
//
//               resultList.add(respvo);
//            }
            return classifList;

        }else {
            System.out.println("您已到期！");
            return null;

        }
    }


    @Override
    public Boolean addArticleClassify(ArticleClassify reVo) {
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
            LambdaQueryWrapper<ArticleClassify> classifLambda = new LambdaQueryWrapper<ArticleClassify>()
                    .eq(ArticleClassify::getCompanyId,company.getId())
                    .orderBy(true,false, ArticleClassify::getLevel);
            if (articleClassifyRepository.list(classifLambda).size()!=0){
                int level = articleClassifyRepository.list(classifLambda).get(0).getLevel();
                ArticleClassify articleClassif = ArticleClassify.builder().build();
                articleClassif.setCompanyId(company.getId());
                articleClassif.setName(reVo.getName());
                articleClassif.setLevel(level+1);
                articleClassifyRepository.save(articleClassif);
                return Boolean.TRUE;
            }else {
                ArticleClassify articleClassif = ArticleClassify.builder().build();
                articleClassif.setCompanyId(company.getId());
                articleClassif.setName(reVo.getName());
                articleClassif.setLevel(0);
                articleClassifyRepository.save(articleClassif);
                return Boolean.TRUE;
            }


        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updataArticleClassify(ArticleClassify reVo) {
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
            LambdaQueryWrapper<ArticleClassify> classifLambda = new LambdaQueryWrapper<ArticleClassify>()
                    .eq(ArticleClassify::getCompanyId,company.getId())
                    .orderBy(true,false, ArticleClassify::getLevel);
            int level = articleClassifyRepository.list(classifLambda).get(0).getLevel();

            ArticleClassify articleClassif = ArticleClassify.builder().build();
            articleClassif.setId(reVo.getId());
            articleClassif.setCompanyId(company.getId());
            articleClassif.setName(reVo.getName());
            articleClassif.setLevel(level);

            articleClassifyRepository.saveOrUpdate(articleClassif);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delArticleClassify(delArcClassifyId reVo) {
        LambdaQueryWrapper<ArticleClassify> classifLambda = new LambdaQueryWrapper<ArticleClassify>()
                .eq(ArticleClassify::getId,reVo.getId());

       ArticleClassify articleClassif = articleClassifyRepository.getOne(classifLambda);
       Long id = articleClassif.getId();
        System.out.println("Id:"+id);
        articleClassifyRepository.removeById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean upLevel(upDownArcClassifyReVo reVo) {
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
            LambdaQueryWrapper<ArticleClassify> classifLambda = new LambdaQueryWrapper<ArticleClassify>()
                    .eq(ArticleClassify::getId,reVo.getId());
            int level = articleClassifyRepository.getOne(classifLambda).getLevel();
            ArticleClassify articleClassif = ArticleClassify.builder().build();
            articleClassif.setId(reVo.getId());
            articleClassif.setLevel(level-1);
            articleClassifyRepository.saveOrUpdate(articleClassif);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean DownLevel(upDownArcClassifyReVo reVo) {
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
            LambdaQueryWrapper<ArticleClassify> classifLambda = new LambdaQueryWrapper<ArticleClassify>()
                    .eq(ArticleClassify::getId,reVo.getId());
            int level = articleClassifyRepository.getOne(classifLambda).getLevel();
            ArticleClassify articleClassif = ArticleClassify.builder().build();
            articleClassif.setId(reVo.getId());
            articleClassif.setLevel(level+1);
            articleClassifyRepository.saveOrUpdate(articleClassif);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public List<ArticleClassify> getContentTitle(Integer card_id) {
        UserInfoVO userInfoVO = userUtil.getLoginUser();
        /*
         * 用户
         */
        //1.获取用户身份
        Boolean isCustermor = miniRoleUtils.isCustomer();
        Boolean isStaff = miniRoleUtils.isStaff();
        if (isCustermor) {
            // 客户调用逻辑
            LambdaQueryWrapper<CardDate> cardDateLambdaQueryWrapper = new LambdaQueryWrapper<CardDate>()
                    .eq(CardDate::getCardId, card_id);
            CardDate cardDate = cardDateRepository.getOne(cardDateLambdaQueryWrapper);
            // 组织id
            int organize_id = cardDate.getChildId();
            LambdaQueryWrapper<Organize> organizeLambdaQueryWrapper = new LambdaQueryWrapper<Organize>()
                    .eq(Organize::getId, organize_id);
            Organize organize = organizationRepository.getOne(organizeLambdaQueryWrapper);
            // 公司id
            int company_id = organize.getCompanyId();
            LambdaQueryWrapper<ArticleClassify> articleClassifyLambdaQueryWrapper = new LambdaQueryWrapper<ArticleClassify>()
                    .eq(ArticleClassify::getCompanyId, company_id);
            return articleClassifyRepository.list(articleClassifyLambdaQueryWrapper);
        } else {
            /*
             * 员工
             */
            if (isStaff) {
                // 员工调用
                // 员工id
                int user_id = userInfoVO.getId().intValue();
                //
                LambdaQueryWrapper<CardDate> cardDateLambdaQueryWrapper = new LambdaQueryWrapper<CardDate>()
                        .eq(CardDate::getUserId, user_id);
                CardDate cardDate = cardDateRepository.getOne(cardDateLambdaQueryWrapper);
                int organize_id = cardDate.getChildId();
                // 组织id
                LambdaQueryWrapper<Organize> organizeLambdaQueryWrapper = new LambdaQueryWrapper<Organize>()
                        .eq(Organize::getId, organize_id);
                Organize organize = organizationRepository.getOne(organizeLambdaQueryWrapper);
                // 公司id
                int company_id = organize.getCompanyId();
                LambdaQueryWrapper<ArticleClassify> articleClassifyLambdaQueryWrapper = new LambdaQueryWrapper<ArticleClassify>()
                        .eq(ArticleClassify::getCompanyId, company_id);
                return articleClassifyRepository.list(articleClassifyLambdaQueryWrapper);
            }
        }
        System.out.println("身份校验失败");
        return null;
    }
}
