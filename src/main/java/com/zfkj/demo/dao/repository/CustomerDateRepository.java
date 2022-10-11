package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.CustomerDate;
import com.zfkj.demo.dao.mapper.CustomerDateMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDateRepository extends ServiceImpl<CustomerDateMapper, CustomerDate> {
}
