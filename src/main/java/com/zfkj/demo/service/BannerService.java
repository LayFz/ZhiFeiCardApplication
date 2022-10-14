package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.Banner;
import com.zfkj.demo.vo.reqvo.banner.SaveBannerVo;

import java.util.List;

public interface BannerService {
    /**
     * 保存Banner信息
     */

    Boolean saveIntroductBanner(SaveBannerVo ReVo);

    Boolean saveContentBanner(SaveBannerVo ReVo);

    /**
     * 获取banner
     * @return
     */
    List<Banner> getBanners();

    Boolean ChangeIntroBanner();

    Boolean changeContentBanner();

    List<Banner> getBannersById(Integer id);
}
