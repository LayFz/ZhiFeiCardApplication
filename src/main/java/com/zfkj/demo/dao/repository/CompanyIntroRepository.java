package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.CompanyIntro;
import com.zfkj.demo.dao.mapper.CompanyIntroMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyIntroRepository extends ServiceImpl<CompanyIntroMapper, CompanyIntro> {
}
