package com.zfkj.demo.service;

import com.zfkj.demo.vo.reqvo.articleClassify.delArcClassifyId;
import com.zfkj.demo.vo.reqvo.articleClassify.saveArcClassifyReVo;
import com.zfkj.demo.vo.reqvo.articleClassify.upDownArcClassifyReVo;
import com.zfkj.demo.vo.respvo.articleClassify.articleClassifyRespVo;

import java.util.List;

public interface ArticleClassifyService {

    List<articleClassifyRespVo> getArticleClassify();

    Boolean addArticleClassify(saveArcClassifyReVo reVo);

    Boolean updataArticleClassify(saveArcClassifyReVo reVo);

    Boolean delArticleClassify(delArcClassifyId reVo);

    Boolean upLevel(upDownArcClassifyReVo reVo);

    Boolean DownLevel(upDownArcClassifyReVo reVo);
}
