package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.dao.entity.CustomerDate;
import com.zfkj.demo.dao.repository.CustomerDateRepository;
import com.zfkj.demo.service.CustomerDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDateServiceImpl implements CustomerDateService {
    @Autowired
    CustomerDateRepository customerDateRepository;

    /**
     * 用户增加访问量
     * @param id
     * @return
     */
    @Override
    public Boolean addVisitNum(int id) {
        LambdaQueryWrapper<CustomerDate> customerDateLambda = new LambdaQueryWrapper<CustomerDate>()
                .eq(CustomerDate::getCusId,id);
//        CustomerDate customerDate = customerDateRepository.getOne(customerDateLambda);

        CustomerDate cus = CustomerDate.builder().build();
        cus = customerDateRepository.getOne(customerDateLambda);


        int visitNum = cus.getVisit_num()+1;
        cus.setVisit_num(visitNum);

        customerDateRepository.saveOrUpdate(cus);
        return Boolean.TRUE;
    }


    /**
     * 设置专属员工
     * @param id
     * @param cardId
     * @return
     */
    @Override
    public Boolean addExclusive(int id,int cardId) {
        LambdaQueryWrapper<CustomerDate> customerDateLambda = new LambdaQueryWrapper<CustomerDate>()
                .eq(CustomerDate::getId,id);
        CustomerDate customerDate = customerDateRepository.getOne(customerDateLambda);
        customerDate.setCard_id(cardId);
        customerDateRepository.saveOrUpdate(customerDate);
        return Boolean.TRUE;
    }
}
