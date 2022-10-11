package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.StaffCustomer;
import com.zfkj.demo.dao.mapper.StaffCustomerMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StaffCustomerRepository extends ServiceImpl<StaffCustomerMapper, StaffCustomer> {
}
