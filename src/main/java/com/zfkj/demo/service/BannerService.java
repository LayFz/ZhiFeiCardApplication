package com.zfkj.demo.service;

import com.zfkj.demo.vo.reqvo.banner.SaveBannerVo;

public interface BannerService {
    /**
     * 保存Banner信息
     */


    Boolean saveIntroductBanner(SaveBannerVo ReVo);

    Boolean saveContentBanner(SaveBannerVo ReVo);
}
