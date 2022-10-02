package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.Banner;
import com.zfkj.demo.dao.mapper.BannerMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BannerRepository extends ServiceImpl<BannerMapper, Banner> {
}
