package com.zfkj.demo.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.dao.entity.CustomerDate;
import com.zfkj.demo.dao.entity.StaffCustomer;

import com.zfkj.demo.dao.repository.CustomerDateRepository;
import com.zfkj.demo.dao.repository.StaffCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JudgeCusStaffUtil {

    @Autowired
    StaffCustomerRepository staffCustomerRepository;
    @Autowired
    CustomerDateRepository customerDateRepository;

    /**
     * 判断客户员工之间是否有联系
     * @param staffId
     * @param cusId
     * @return
     */
    public Boolean isRelevant(int staffId,int cusId){
        LambdaQueryWrapper<StaffCustomer> staffCustomerLambda = new
                LambdaQueryWrapper<StaffCustomer>()
                .eq(StaffCustomer::getUser_id,staffId)
                .eq(StaffCustomer::getCus_id,cusId);
        StaffCustomer staffCustomer = staffCustomerRepository.getOne(staffCustomerLambda);
        if (staffCustomer!=null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断该员工是否为该客户的专属员工
     * @param cusId
     * @return
     */
    public Boolean isExclusive(int cusId){
        LambdaQueryWrapper<CustomerDate> customerDateLambda = new LambdaQueryWrapper<CustomerDate>()
                .eq(CustomerDate::getCusId,cusId);
        CustomerDate customerDate = customerDateRepository.getOne(customerDateLambda);
//        System.out.println("customerDate:"+customerDate);
//            System.out.println("CardId:"+customerDate.getCard_id());
            if (customerDate.getCard_id()!=null){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;

    }
}
