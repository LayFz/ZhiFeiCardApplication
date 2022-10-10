package com.zfkj.demo.service;


/**
 * @author liujie
 * @version 1.0
 * @date 2022/10/10 14:00
 */
public interface CardDateService {
    /**
     * 前端保存名片
     * @param card_id
     * @return
     */
    Boolean saveCard(int card_id);

    Boolean addPhone(int card_id);
}
