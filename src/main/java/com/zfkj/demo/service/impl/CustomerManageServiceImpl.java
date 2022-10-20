package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.dao.repository.*;
import com.zfkj.demo.service.CustomerManageService;
import com.zfkj.demo.vo.reqvo.customer.pcCusReVo;
import com.zfkj.demo.vo.respvo.customer.pcCusRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerManageServiceImpl implements CustomerManageService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SystemUserUtil userUtil;
    @Autowired
    CustomerDateRepository customerDateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public List<pcCusRespVo> customerList() {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getValidData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")){
            //获取该公司旗下的用户
            LambdaQueryWrapper<CustomerDate> customerDateLambda = new LambdaQueryWrapper<CustomerDate>()
                    .inSql(CustomerDate::getCusId,"SELECT cus_id FROM user_customer WHERE user_id IN(\n" +
                            "SELECT user_id FROM card_date WHERE child_id IN(\n" +
                            "SELECT id FROM organization WHERE company_id ="+company.getId()+"))");

            List<CustomerDate> customerDate = customerDateRepository.list(customerDateLambda);
//            //根据id去重
//            List<CustomerDate> customerDates = customerDate.stream()
//                    .collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>
//                            (Comparator.comparing(CustomerDate::getId))),ArrayList::new));

            //将cusid取出形成新的List
            List<Integer> cusIds = customerDate.stream().map(CustomerDate::getCusId).collect(Collectors.toList());
            //List转化字符串
            String ids = StringUtils.join(cusIds.toArray(),",");

            System.out.println("id:"+ids);
            LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                    .inSql(User::getId,"SELECT cus_id FROM cus_date WHERE cus_id IN ("+ids+")");
            List<User> user = userRepository.list(userLambda);
            List<pcCusRespVo> respVos = new ArrayList<>();
            for (int i=0;i<user.size();i++){
                pcCusRespVo respVo = pcCusRespVo.builder().build();
                respVo.setCustomerDate(customerDate.get(i));
                respVo.setUser(user.get(i));
                respVos.add(respVo);
             }
            return respVos;
        }else {
            System.out.println("您已到期！");
            return null;
        }
    }


    @Override
    public List<pcCusRespVo> seclectCustomer(String name) {
        //获取当前登录用户的角色集合
        UserInfoVO loginUser = userUtil.getLoginUser();
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getValidData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")){
            //获取该公司旗下的用户
            LambdaQueryWrapper<CustomerDate> customerDateLambda = new LambdaQueryWrapper<CustomerDate>()
                    .inSql(CustomerDate::getCusId,"SELECT cus_id FROM user_customer WHERE user_id IN(\n" +
                            "SELECT user_id FROM card_date WHERE child_id IN(\n" +
                            "SELECT id FROM organization WHERE company_id ="+company.getId()+"))");

            List<CustomerDate> customerDate = customerDateRepository.list(customerDateLambda);
//            //根据id去重
//            List<CustomerDate> customerDates = customerDate.stream()
//                    .collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>
//                            (Comparator.comparing(CustomerDate::getId))),ArrayList::new));

            //将cusid取出形成新的List
            List<Integer> cusIds = customerDate.stream().map(CustomerDate::getCusId).collect(Collectors.toList());
            //List转化字符串
            String ids = StringUtils.join(cusIds.toArray(),",");

            System.out.println("id:"+ids);
            LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                    .inSql(User::getId,"SELECT cus_id FROM cus_date WHERE cus_id IN ("+ids+")")
                    .like(User::getName,name);

            List<User> user = userRepository.list(userLambda);
            System.out.println("User:"+user);
            if (user.size()==0){
                return null;
            }
            List<pcCusRespVo> respVos = new ArrayList<>();
            for (int i=0;i<user.size();i++){
                pcCusRespVo respVo = pcCusRespVo.builder().build();
                respVo.setCustomerDate(customerDate.get(i));
                respVo.setUser(user.get(i));
                respVos.add(respVo);
            }
            return respVos;
        }else {
            System.out.println("您已到期！");
            return null;
        }
    }
}
