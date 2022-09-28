package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.Account;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.vo.reqvo.account.AddUpAccountVo;
import com.zfkj.demo.vo.reqvo.account.DelAccountVo;
import com.zfkj.demo.vo.reqvo.account.RePassAccountVo;


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
     * 添加账号
     */
    Boolean addAccount(AddUpAccountVo reqVo);


    /**
     * 编辑账号
     * @param reqVo
     * @return
     */
    Boolean UpdateAccount(AddUpAccountVo reqVo);

    /**
     * 删除账号
     */
    Boolean delAccount(DelAccountVo reqvo);

    /**
     * 重置密码
     */
    Boolean resetPassword(RePassAccountVo reqVo);
}
