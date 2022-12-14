package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.vo.reqvo.account.*;
import com.zfkj.demo.vo.respvo.account.accountRespVo;


import java.util.List;

public interface AccountManageService {




    /**
     * 获取账号列表
     */
    List<accountRespVo> getAccountList();

    /**
     * 查询账号
     * @param reVo
     * @return
     */
    List<accountRespVo> selectAccount(String reVo);
    /**
     * 添加账号
     */
    Boolean addAccount(AddUpAccountVo reqVo);


    /**
     * 编辑账号
     * @param reqVo
     * @return
     */
    Boolean UpdateAccount(UpdateAccountVo reqVo);

    /**
     * 删除账号
     */
    Boolean delAccount(DelAccountVo reqvo);

    /**
     * 重置密码
     */
    Boolean resetPassword(RePassAccountVo reqVo);
}
