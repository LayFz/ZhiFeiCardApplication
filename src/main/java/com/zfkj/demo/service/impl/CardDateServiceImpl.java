package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zfkj.demo.common.utils.MiniRoleUtils;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.Card;
import com.zfkj.demo.dao.entity.CardDate;
import com.zfkj.demo.dao.entity.RealationShip;
import com.zfkj.demo.dao.repository.CardDateRepository;
import com.zfkj.demo.dao.repository.RealationShipRepository;
import com.zfkj.demo.service.CardDateService;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/10/10 14:01
 */
@Service
public class CardDateServiceImpl implements CardDateService {
    @Autowired
    MiniRoleUtils miniRoleUtils;
    @Autowired
    CardDateRepository cardDateRepository;
    @Autowired
    RealationShipRepository realationShipRepository;
    @Autowired
    SystemUserUtil systemUserUtil;


    @Override
    public Boolean saveCard(int card_id) {
        UserInfoVO userInfoVO = systemUserUtil.getLoginUser();
        /*
         * 用户保存名片
         */
        //1.获取用户身份
        Boolean isCustermor = miniRoleUtils.isCustomer();
        Boolean isStaff = miniRoleUtils.isStaff();
        if (isCustermor){

//            // 客户调用逻辑
            LambdaQueryWrapper<CardDate> cardDateLambdaQueryWrapper = new LambdaQueryWrapper<CardDate>()
                    .eq(CardDate::getCardId, card_id);
            CardDate cardDate = cardDateRepository.getOne(cardDateLambdaQueryWrapper);
            cardDate.setSaveNumber(cardDate.getSaveNumber()+1);
            cardDateRepository.saveOrUpdate(cardDate);
            int staff_id = cardDate.getUserId();
            // 客户行为标记
            LambdaQueryWrapper<RealationShip> realationShipLambdaQueryWrapper = new LambdaQueryWrapper<RealationShip>()
                    .eq(RealationShip::getUser_id, staff_id)
                    .eq(RealationShip::getCus_id, userInfoVO.getId());
            RealationShip realationShip = RealationShip.builder().build();
            // 如果客户没有建立关系
            if (realationShipRepository.list(realationShipLambdaQueryWrapper).size()==0){
                realationShip.setUser_id(staff_id);
                realationShip.setCus_id(userInfoVO.getId().intValue());
                realationShip.setInteraction_num(1);
            }else {
                //如果已经建立关系
                realationShip = realationShipRepository.getOne(realationShipLambdaQueryWrapper);
                realationShip.setInteraction_num(realationShip.getInteraction_num()+1);
            }
            realationShipRepository.saveOrUpdate(realationShip);
            LambdaQueryWrapper<RealationShip> realationShipLambdaQueryWrapper1 = new LambdaQueryWrapper<RealationShip>()
                    .eq(RealationShip::getUser_id, staff_id);
            int cus_num = realationShipRepository.list(realationShipLambdaQueryWrapper1).size();
            cardDate.setCusNumber(cus_num);
            cardDateRepository.saveOrUpdate(cardDate);
            return Boolean.TRUE;
        }else {
            /*
             * 员工保存名片
             */
            if (isStaff){
                // 员工调用逻辑
                LambdaQueryWrapper<CardDate> cardDateLambdaQueryWrapper = new LambdaQueryWrapper<CardDate>()
                        .eq(CardDate::getCardId, card_id);
                CardDate cardDate = cardDateRepository.getOne(cardDateLambdaQueryWrapper);
                cardDate.setSaveNumber(cardDate.getSaveNumber()+1);
                cardDateRepository.saveOrUpdate(cardDate);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean addPhone(int card_id) {
        UserInfoVO userInfoVO = systemUserUtil.getLoginUser();
        /*
         * 用户
         */
        //1.获取用户身份
        Boolean isCustermor = miniRoleUtils.isCustomer();
        Boolean isStaff = miniRoleUtils.isStaff();
        if (isCustermor){

//            // 客户调用逻辑
            LambdaQueryWrapper<CardDate> cardDateLambdaQueryWrapper = new LambdaQueryWrapper<CardDate>()
                    .eq(CardDate::getCardId, card_id);
            CardDate cardDate = cardDateRepository.getOne(cardDateLambdaQueryWrapper);
            cardDate.setMailNumber(cardDate.getMailNumber()+1);
            cardDateRepository.saveOrUpdate(cardDate);
            int staff_id = cardDate.getUserId();
            // 客户行为标记
            LambdaQueryWrapper<RealationShip> realationShipLambdaQueryWrapper = new LambdaQueryWrapper<RealationShip>()
                    .eq(RealationShip::getUser_id, staff_id)
                    .eq(RealationShip::getCus_id, userInfoVO.getId());
            RealationShip realationShip = RealationShip.builder().build();
            // 如果客户没有建立关系
            if (realationShipRepository.list(realationShipLambdaQueryWrapper).size()==0){
                realationShip.setUser_id(staff_id);
                realationShip.setCus_id(userInfoVO.getId().intValue());
                realationShip.setInteraction_num(1);
            }else {
                //如果已经建立关系
                realationShip = realationShipRepository.getOne(realationShipLambdaQueryWrapper);
                realationShip.setInteraction_num(realationShip.getInteraction_num()+1);
            }
            realationShipRepository.saveOrUpdate(realationShip);
            LambdaQueryWrapper<RealationShip> realationShipLambdaQueryWrapper1 = new LambdaQueryWrapper<RealationShip>()
                    .eq(RealationShip::getUser_id, staff_id);
            int cus_num = realationShipRepository.list(realationShipLambdaQueryWrapper1).size();
            cardDate.setCusNumber(cus_num);
            cardDateRepository.saveOrUpdate(cardDate);
            return Boolean.TRUE;
        }else {
            /*
             * 员工
             */
            if (isStaff){
                // 员工调用
                LambdaQueryWrapper<CardDate> cardDateLambdaQueryWrapper = new LambdaQueryWrapper<CardDate>()
                        .eq(CardDate::getCardId, card_id);
                CardDate cardDate = cardDateRepository.getOne(cardDateLambdaQueryWrapper);
                cardDate.setMailNumber(cardDate.getMailNumber()+1);
                cardDateRepository.saveOrUpdate(cardDate);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }
}
