package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.Company;
import com.zfkj.demo.dao.mapper.CompanyMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepository extends ServiceImpl<CompanyMapper, Company> {
}
