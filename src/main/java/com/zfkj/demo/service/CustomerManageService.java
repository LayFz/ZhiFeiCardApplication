package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.User;

import java.util.HashMap;
import java.util.List;


public interface CustomerManageService {

    /**
     * 获取用户列表按照时间升序排列
     */
    List<User> getAllCustomerBytime();

    /**
     * 获取用户总数
     */
    Integer getAllCustomer();

    /**
     * 近七日新增数
     */
    Integer sevenDayCustomer();

    /**
     * 今日新增
     */
    Integer todayCustomer();

    /**
     * 近七日客户趋势
     */

    HashMap<String,Integer> getSevenTrend();

    /**
     * 根据电话搜索用户
     */
    User selectPhoneByUser(String phone);
}
