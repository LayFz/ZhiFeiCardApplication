package com.zfkj.demo.service;


import com.zfkj.demo.vo.reqvo.card.SaveCardIntroReVo;
import com.zfkj.demo.vo.respvo.card.CardRespVo;


import java.util.List;

public interface CardService {

    //获取名片信息
    List<CardRespVo> getCard(int id);
    //获取名片员工userId
    int getstaffId(int id);

    //返回名片
    List<CardRespVo> returnCard(int id);

//    /**
//     * 保存名片
//     * @param reVo
//     * @return
//     */
//    Boolean addSaveNum(CardReVo reVo);
//
//    /**
//     * 增加转发名片数
//     * @param reVo
//     * @return
//     */
//    Boolean addActiveNum(CardReVo reVo);

    /**
     * 增加访问数
     * @param id
     * @return
     */
    Boolean addViewNum(int id);

    /**
     * 保存简介
     * @param reVo
     * @return
     */
    Boolean saveIntroduct(SaveCardIntroReVo reVo);


}
