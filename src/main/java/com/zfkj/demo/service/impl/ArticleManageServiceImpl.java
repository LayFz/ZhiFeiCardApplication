package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.Article;
import com.zfkj.demo.dao.entity.ArticleClassify;
import com.zfkj.demo.dao.entity.ArticleManage;
import com.zfkj.demo.dao.entity.Company;
import com.zfkj.demo.dao.repository.ArticleClassifyRepository;
import com.zfkj.demo.dao.repository.ArticleManageRepository;
import com.zfkj.demo.dao.repository.CompanyRepository;
import com.zfkj.demo.service.ArticleManageService;
import com.zfkj.demo.vo.reqvo.article.AddUpArticleVo;
import com.zfkj.demo.vo.reqvo.article.DelArticleVo;
import com.zfkj.demo.vo.reqvo.article.ArticleReqVo;
import com.zfkj.demo.vo.respvo.article.articleByVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleManageServiceImpl implements ArticleManageService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SystemUserUtil userUtil;

    @Autowired
    ArticleManageRepository articleManageRepository;

    @Autowired
    ArticleClassifyRepository articleClassifyRepository;
    /**
     * 个性化内容--文章管理
     */
    @Override
    public List<ArticleReqVo> getArticleData() {
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
            //根据管理员id获取article表相关数据
            LambdaQueryWrapper<ArticleManage> articleLambda = new LambdaQueryWrapper<ArticleManage>()
                    .eq(ArticleManage::getCreateId,loginUser.getId());
            List<ArticleManage> articleList = articleManageRepository.list(articleLambda);
            //根据

            List<ArticleReqVo> articleReqVoList = new ArrayList<>();
            System.out.println("Size"+articleList);
            for (int i=0;i<articleList.size();i++){
                ArticleReqVo reqVo = ArticleReqVo.builder().build();
                reqVo.setId(articleList.get(i).getId());
                reqVo.setName(articleList.get(i).getName());
                LambdaQueryWrapper<ArticleClassify> articleClassifLambda = new LambdaQueryWrapper<ArticleClassify>()
                        .eq(ArticleClassify::getId,articleList.get(i).getClassifyId());
                ArticleClassify articleClassif = articleClassifyRepository.getOne(articleClassifLambda);
                String classifName = articleClassif.getName();
                reqVo.setClassif(classifName);
                reqVo.setTrueNumber(articleList.get(i).getViewNumber());
                int number =articleList.get(i).getViewNumber()+ articleList.get(i).getFalseNumber();
                reqVo.setFalsenumber(number);
                reqVo.setCreateName(loginUser.getName());
                reqVo.setCreatetime(articleList.get(i).getCreateTime());
                System.out.println("reqvo"+reqVo);
                articleReqVoList.add(reqVo);
                System.out.println("reqvoList:"+articleReqVoList);
            }
            return articleReqVoList;

        }else {
            System.out.println("您已到期！");
            return null;

        }
    }

    /**
     * 查询
     */
    @Override
    public List<ArticleManage> selectArticle(String search) {
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
            LambdaQueryWrapper<ArticleManage> articleManageLambdaQueryWrapper = new LambdaQueryWrapper<ArticleManage>()
                    .eq(ArticleManage::getCreateId, loginUser.getId())
                    .like(ArticleManage::getName, search);
            return articleManageRepository.list(articleManageLambdaQueryWrapper);
        }else {
            System.out.println("您已到期！");
            return null;
        }

    }

    @Override
    public Boolean addArticle(AddUpArticleVo reVo) {
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
            LambdaQueryWrapper<ArticleClassify> articleClassifLambdaQueryWrapper = new LambdaQueryWrapper<ArticleClassify>()
                    .eq(ArticleClassify::getId,reVo.getClass_id());
            long classifId = articleClassifyRepository.getOne(articleClassifLambdaQueryWrapper).getId();
            ArticleManage articleManage = ArticleManage.builder().build();
            articleManage.setClassifyId(classifId);
            articleManage.setName(reVo.getName());
            articleManage.setHeadImg(reVo.getHeadImg());
            articleManage.setFalseNumber(reVo.getFalseNumber());
            articleManage.setViewNumber(reVo.getFalseNumber()+1);
            articleManage.setContent(reVo.getContent());
            articleManageRepository.save(articleManage);
           return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean updataArticle(AddUpArticleVo reVo) {
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
            LambdaQueryWrapper<ArticleManage> articleManageLambdaQueryWrapper = new LambdaQueryWrapper<ArticleManage>()
                    .eq(ArticleManage::getId, reVo.getId());
            ArticleManage articleManage = articleManageRepository.getOne(articleManageLambdaQueryWrapper);
            articleManage.setClassifyId(reVo.getClass_id());
            articleManage.setName(reVo.getName());
            articleManage.setHeadImg(reVo.getHeadImg());
            articleManage.setFalseNumber(reVo.getFalseNumber());
            articleManage.setContent(reVo.getContent());
            articleManageRepository.saveOrUpdate(articleManage);
            return Boolean.TRUE;
        }else {
            System.out.println("您已到期！");
            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean DelArticle(AddUpArticleVo reVo) {
        articleManageRepository.removeById(reVo.getId());
        return Boolean.TRUE;
    }

    @Override
    public ArticleManage getArticleById(long id) {
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
            LambdaQueryWrapper<ArticleManage> articleManageLambdaQueryWrapper = new LambdaQueryWrapper<ArticleManage>()
                    .eq(ArticleManage::getId, id)
                    .eq(ArticleManage::getCreateId,loginUser.getId());
            return articleManageRepository.getOne(articleManageLambdaQueryWrapper);
        }else {
            System.out.println("您已到期！");
            return null;
        }
    }
}
