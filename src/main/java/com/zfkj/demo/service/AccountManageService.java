package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.Account;


import java.util.List;

public interface AccountManageService {


//    /**
//     * 获取Token
//     */
//    UserInfoVO getToken(HttpServletRequest request) throws Exception;

    /**
     * 获取账号列表
     */
//    List<Account> getAllAccount();
    /**
     * 添加,编辑账号
     */
    Boolean addOrUpdateAccount(Account reqVo);

    /**
     * 删除账号
     */
    Boolean delAccount(List<Integer> ids);

    /**
     * 重置密码
     */
    Boolean resetPassword(Account reqVo);
}
