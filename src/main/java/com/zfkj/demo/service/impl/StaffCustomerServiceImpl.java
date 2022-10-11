package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.dao.entity.StaffCustomer;
import com.zfkj.demo.dao.repository.StaffCustomerRepository;
import com.zfkj.demo.service.StaffCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffCustomerServiceImpl implements StaffCustomerService {
    @Autowired
    StaffCustomerRepository staffCustomerRepository;

    @Override
    public Boolean addContact(int staffId, int cusId) {
        StaffCustomer staffCustomer = StaffCustomer.builder().build();
        staffCustomer.setUser_id(staffId);
        staffCustomer.setCus_id(cusId);

        staffCustomerRepository.saveOrUpdate(staffCustomer);
       return Boolean.TRUE;
    }

    @Override
    public Boolean addInteractionNum(int id) {
        LambdaQueryWrapper<StaffCustomer> staffCustomerLambda = new LambdaQueryWrapper<StaffCustomer>()
                .eq(StaffCustomer::getId,id);
        StaffCustomer staffCustomer = staffCustomerRepository.getOne(staffCustomerLambda);
        int num = staffCustomer.getInteraction_num()+1;
        staffCustomer.setInteraction_num(num);
        staffCustomerRepository.saveOrUpdate(staffCustomer);
        return Boolean.TRUE;
    }
}
