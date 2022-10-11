package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.utils.JudgeCusStaffUtil;
import com.zfkj.demo.common.utils.JudgeRoleUtil;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.dao.repository.*;
import com.zfkj.demo.service.CardService;
import com.zfkj.demo.service.CustomerDateService;
import com.zfkj.demo.service.StaffCustomerService;
import com.zfkj.demo.vo.reqvo.card.SaveCardIntroReVo;
import com.zfkj.demo.vo.respvo.card.CardRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    JudgeRoleUtil judgeRoleUtil;
    @Autowired
    SystemUserUtil systemUserUtil;
    @Autowired
    JudgeCusStaffUtil judgeCusStaffUtil;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardDateRepository cardDateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    StaffCustomerRepository staffCustomerRepository;
    @Autowired
    CustomerDateRepository customerDateRepository;
    @Autowired
    CustomerDateService customerDateService;
    @Autowired
    StaffCustomerService staffCustomerService;

    @Override
    public List<CardRespVo> getCard(int id) {
        LambdaQueryWrapper<Card> cardLambda = new LambdaQueryWrapper<Card>()
                .eq(Card::getId,id);
        Card card = cardRepository.getOne(cardLambda);

        LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                .eq(CardDate::getCardId,card.getId());
        long userId =cardDateRepository.getOne(cardDateLambda).getUserId();
        int organizeId = cardDateRepository.getOne(cardDateLambda).getChildId();

        LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                .eq(User::getId,userId);
        String phone = userRepository.getOne(userLambda).getPhone();

        LambdaQueryWrapper<Organize> organizeLambda = new LambdaQueryWrapper<Organize>()
                .eq(Organize::getId,organizeId);
        int companyId = organizationRepository.getOne(organizeLambda).getCompanyId();

        LambdaQueryWrapper<Company> companyLambda = new LambdaQueryWrapper<Company>()
                .eq(Company::getId,companyId);
        String company  = companyRepository.getOne(companyLambda).getName();


        CardRespVo cardRespVo = CardRespVo.builder().build();

        cardRespVo.setCard(card);
        cardRespVo.setPhone(phone);
        cardRespVo.setCompany(company);

        List<CardRespVo> result = new ArrayList<>();
        result.add(cardRespVo);

        return result;

    }

    @Override
    public int getstaffId(int id) {
        LambdaQueryWrapper<Card> cardLambda = new LambdaQueryWrapper<Card>()
                .eq(Card::getId,id);
        Card card = cardRepository.getOne(cardLambda);

        LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                .eq(CardDate::getCardId,card.getId());
        return cardDateRepository.getOne(cardDateLambda).getUserId();
    }

    /**
     * 增加访问数
     * @param id
     * @return
     */
    @Override
    public Boolean addViewNum(int id) {
        LambdaQueryWrapper<Card> cardLambda = new LambdaQueryWrapper<Card>()
                .eq(Card::getId,id);
        Card card = cardRepository.getOne(cardLambda);

        LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                .eq(CardDate::getCardId,card.getId());
        CardDate cardDate = cardDateRepository.getOne(cardDateLambda);


        CardDate update = CardDate.builder().build();
        update.setId(cardDate.getId());
//        update.setCardId(card.getId().intValue());

        update.setViewNumber(cardDate.getViewNumber()+1);
        update.setCusNumber(cardDate.getCusNumber());
        update.setActiveNumber(cardDate.getActiveNumber());
        update.setMessageNumber(cardDate.getMessageNumber());
        update.setSaveNumber(cardDate.getSaveNumber());
        update.setMailNumber(cardDate.getMailNumber());
        update.setChildId(cardDate.getChildId());

        cardDateRepository.saveOrUpdate(update);
        return Boolean.TRUE;
    }


    /**
     * 返回名片数据
     * @param id
     * @return
     */
    @Override
    public List<CardRespVo> returnCard(int id) {
        String phone = systemUserUtil.getLoginUser().getPhone();
        int userId = systemUserUtil.getLoginUser().getId().intValue();

        int staffId = getstaffId(id);
        if(judgeRoleUtil.judgeRole(phone)==null){
            System.out.println("客户");
            if (judgeRoleUtil.notNull(id)){
                addViewNum(id);
                if (judgeCusStaffUtil.isRelevant(staffId,userId)){
                    System.out.println("员工用户有联系");
                    customerDateService.addVisitNum(userId);

                    LambdaQueryWrapper<StaffCustomer> staffCustomerLambda = new LambdaQueryWrapper<StaffCustomer>()
                            .eq(StaffCustomer::getUser_id,staffId)
                            .eq(StaffCustomer::getCus_id,userId);
                    int staffCusId = staffCustomerRepository.getOne(staffCustomerLambda).getId().intValue();

                    if (judgeCusStaffUtil.isExclusive(userId)){
                        System.out.println("非专属员工1");
                        staffCustomerService.addInteractionNum(staffCusId);
                    }else {
                        System.out.println("专属员工1");
                        LambdaQueryWrapper<CustomerDate> customerDateLambda = new LambdaQueryWrapper<CustomerDate>()
                                .eq(CustomerDate::getCusId,userId);
                        int cusId = customerDateRepository.getOne(customerDateLambda).getId().intValue();

                        customerDateService.addExclusive(cusId,id);

                        staffCustomerService.addInteractionNum(staffCusId);
                    }
                    return getCard(id);
                }else {
                    System.out.println("员工用户没有联系");
                    customerDateService.addVisitNum(userId);

                    staffCustomerService.addContact(staffId,userId);

                    LambdaQueryWrapper<StaffCustomer> staffCustomerLambda = new LambdaQueryWrapper<StaffCustomer>()
                            .eq(StaffCustomer::getUser_id,staffId)
                            .eq(StaffCustomer::getCus_id,userId);
                    int staffCusId = staffCustomerRepository.getOne(staffCustomerLambda).getId().intValue();

                    customerDateService.addVisitNum(userId);

                    if (judgeCusStaffUtil.isExclusive(userId)){
                        System.out.println("非专属员工2");
                        staffCustomerService.addInteractionNum(staffCusId);
                    }else {
                        System.out.println("专属员工2");
                        LambdaQueryWrapper<CustomerDate> customerDateLambda = new LambdaQueryWrapper<CustomerDate>()
                                .eq(CustomerDate::getCusId,userId);
                        int cusId = customerDateRepository.getOne(customerDateLambda).getId().intValue();
                        customerDateService.addExclusive(cusId,id);
                        staffCustomerService.addInteractionNum(staffCusId);
                    }
                    return getCard(id);
                }
            }
        }else if (judgeRoleUtil.judgeRole(phone).equals("员工")){
            if (judgeRoleUtil.notNull(id)){
                if (judgeRoleUtil.staffHimself(phone)){
                    System.out.println("员工本人");
                    addViewNum(id);
                }
                return getCard(id);
            }
        }else {
            System.out.println("管理员");
            return null;
        }
        return null;
    }

    @Override
    public Boolean saveIntroduct(SaveCardIntroReVo reVo) {
        LambdaQueryWrapper<Card> cardLambda = new LambdaQueryWrapper<Card>()
                .eq(Card::getId,reVo.getId());
        Card card = cardRepository.getOne(cardLambda);

        Card result = Card.builder().build();

        result.setId(card.getId());
        result.setName(card.getName());
        result.setJob(card.getJob());
        result.setEmail(card.getEmail());
        result.setWechatcardurl(card.getWechatcardurl());
        result.setWechatnumber(card.getWechatnumber());
        result.setPerFilePic(reVo.getImgUrl());
        result.setPerFileContent(reVo.getContent());

        cardRepository.saveOrUpdate(result);

        return Boolean.TRUE;
    }
}
