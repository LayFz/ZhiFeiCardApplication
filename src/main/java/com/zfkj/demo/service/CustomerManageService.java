package com.zfkj.demo.service;


import com.zfkj.demo.vo.reqvo.customer.pcCusReVo;
import com.zfkj.demo.vo.respvo.customer.pcCusRespVo;


import java.util.List;


public interface CustomerManageService {
    List<pcCusRespVo> customerList();

    List<pcCusRespVo> seclectCustomer(String name);
}
