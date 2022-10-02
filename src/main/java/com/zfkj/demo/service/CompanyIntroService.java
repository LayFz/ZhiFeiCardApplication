package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.CompanyIntro;
import com.zfkj.demo.vo.reqvo.companyIntro.delIntroReVo;
import com.zfkj.demo.vo.reqvo.companyIntro.saveIntroReVo;
import com.zfkj.demo.vo.reqvo.companyIntro.upDownIntroReVo;
import com.zfkj.demo.vo.respvo.companyIntro.introRespVo;

import java.util.List;

public interface CompanyIntroService {
    List<introRespVo> getCompanyIntroList();

    Boolean addCompanyIntro(CompanyIntro reVo);

    Boolean updataCompanyIntro(saveIntroReVo reVo);

    Boolean delCompanyIntro(delIntroReVo reVo);

    Boolean upIntroLevel(upDownIntroReVo reVo);

    Boolean downIntroLevel(upDownIntroReVo reVo);
}
