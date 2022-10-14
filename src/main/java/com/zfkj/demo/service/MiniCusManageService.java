package com.zfkj.demo.service;

import com.zfkj.demo.vo.respvo.customer.miniCusRespVo;

import java.util.HashMap;
import java.util.List;

public interface MiniCusManageService {

    int userId(int cardId);
    /**
     * 获取用户总数
     */
    Integer getAllCustomer(int cardId);

    /**
     * 今日新增
     */
    Integer todayCustomer(int cardId);
    /**
     * 近七日新增数
     */
    Integer sevenDayCustomer(int cardId);

    /**
     * 近七日客户趋势
     */
    HashMap<String,Integer> getSevenTrend(int cardId);

    /**
     * 获取客户列表
     * @param cardId
     * @return
     */
    List<miniCusRespVo> customerList(int cardId);

    List<miniCusRespVo> selectCustomer(int cardId, String phone);
}
