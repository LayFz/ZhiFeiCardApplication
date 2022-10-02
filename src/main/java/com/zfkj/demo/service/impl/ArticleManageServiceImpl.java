package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.ArticleClassif;
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
                LambdaQueryWrapper<ArticleClassif> articleClassifLambda = new LambdaQueryWrapper<ArticleClassif>()
                        .eq(ArticleClassif::getId,articleList.get(i).getClassifyId());
                ArticleClassif articleClassif = articleClassifyRepository.getOne(articleClassifLambda);
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
    public List<ArticleReqVo> selectArticle(articleByVo ByVo) {
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
            //根据classify 查询classifyId;
            LambdaQueryWrapper<ArticleClassif> articleClassifLambdaQueryWrapper = new LambdaQueryWrapper<ArticleClassif>()
                    .eq(ArticleClassif::getName,ByVo.getClassif());
            Long classifId = articleClassifyRepository.getOne(articleClassifLambdaQueryWrapper).getId();

            //根据管理员id获取article表相关数据
            LambdaQueryWrapper<ArticleManage> articleLambda = new LambdaQueryWrapper<ArticleManage>()
                    .eq(ArticleManage::getId,ByVo.getId())
                    .or().eq(ArticleManage::getName,ByVo.getName())
                    .or().eq(ArticleManage::getClassifyId,classifId);
            List<ArticleManage> articleList = articleManageRepository.list(articleLambda);
            //根据
            List<ArticleReqVo> articleReqVoList = new ArrayList<>();
            ArticleReqVo reqVo = ArticleReqVo.builder().build();
            for (int i=0;i<articleList.size();i++){
                reqVo.setId(articleList.get(i).getId());
                reqVo.setName(articleList.get(i).getName());
                LambdaQueryWrapper<ArticleClassif> articleClassifLambda = new LambdaQueryWrapper<ArticleClassif>()
                        .eq(ArticleClassif::getId,articleList.get(i).getClassifyId());
                ArticleClassif articleClassif = articleClassifyRepository.getOne(articleClassifLambda);

                String classifName = articleClassif.getName();
                reqVo.setClassif(classifName);
                reqVo.setTrueNumber(articleList.get(i).getViewNumber());
                int number =articleList.get(i).getViewNumber()+ articleList.get(i).getFalseNumber();
                reqVo.setFalsenumber(number);
                reqVo.setCreateName(loginUser.getName());
                reqVo.setCreatetime(articleList.get(i).getCreateTime());

                articleReqVoList.add(reqVo);


            }

            return articleReqVoList;


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
            //根据classify 查询classifyId;
            LambdaQueryWrapper<ArticleClassif> articleClassifLambdaQueryWrapper = new LambdaQueryWrapper<ArticleClassif>()
                    .eq(ArticleClassif::getName,reVo.getClassIf());
            Long classifId = articleClassifyRepository.getOne(articleClassifLambdaQueryWrapper).getId();
            ArticleManage articleManage = ArticleManage.builder().build();
            articleManage.setId(reVo.getId());
            articleManage.setClassifyId(classifId);
            articleManage.setName(reVo.getName());
            articleManage.setHeadImg(reVo.getHeadImg());
            articleManage.setFalseNumber(reVo.getFalseNumber());
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
            //根据classify 查询classifyId;
            LambdaQueryWrapper<ArticleClassif> articleClassifLambdaQueryWrapper = new LambdaQueryWrapper<ArticleClassif>()
                    .eq(ArticleClassif::getName,reVo.getClassIf());
            Long classifId = articleClassifyRepository.getOne(articleClassifLambdaQueryWrapper).getId();
            ArticleManage articleManage = ArticleManage.builder().build();
            articleManage.setId(reVo.getId());
            articleManage.setClassifyId(classifId);
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
    public Boolean DelArticle(DelArticleVo reVo) {
        LambdaQueryWrapper<ArticleManage> articleManageLambda = new
                LambdaQueryWrapper<ArticleManage>().eq(ArticleManage::getId,reVo.getId());

        ArticleManage articleManage = articleManageRepository.getOne(articleManageLambda);
        Long id = articleManage.getId();
        articleManageRepository.removeById(id);
        return Boolean.TRUE;
    }
}
