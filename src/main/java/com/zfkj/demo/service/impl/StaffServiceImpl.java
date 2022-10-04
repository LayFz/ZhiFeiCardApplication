package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.utils.AesUtil;
import com.zfkj.demo.common.utils.SystemUserUtil;

import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.dao.repository.*;
import com.zfkj.demo.service.StaffService;
import com.zfkj.demo.vo.reqvo.staff.AddUpStaffReVo;
import com.zfkj.demo.vo.reqvo.staff.DelStaffReVo;
import com.zfkj.demo.vo.respvo.staff.StaffRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SystemUserUtil userUtil;

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CardDateRepository cardDateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserRoleRepository userRoleRepository;


    @Override
    public List<StaffRespVo> getStaffList() {
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
          //根据companyID获取该公司架构
            LambdaQueryWrapper<Organize> organizeLambda = new LambdaQueryWrapper<Organize>()
                    .eq(Organize::getCompanyId,company.getId());
            List<Organize> organizeList = organizationRepository.list(organizeLambda);
            List<StaffRespVo> result = new ArrayList<>();
            for (int i=0;i<organizeList.size();i++){
                //根据公司架构id在card_Date表查询user_id;card_id
                LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                        .eq(CardDate::getChildId,organizeList.get(i).getId());
                List<CardDate> cardDateList = cardDateRepository.list(cardDateLambda);
                for (int z = 0;z<cardDateList.size();z++){
                    LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                            .eq(User::getId,cardDateList.get(z).getUserId());
                    User user = userRepository.getOne(userLambda);

                    LambdaQueryWrapper<Card> cardLambda  =new LambdaQueryWrapper<Card>()
                            .eq(Card::getId,cardDateList.get(z).getCardId());
                    Card card = cardRepository.getOne(cardLambda);


                  StaffRespVo respVo = StaffRespVo.builder().build();

                  respVo.setId(user.getId().intValue());
                  respVo.setName(user.getName());
                  respVo.setDepartment(organizeList.get(i).getNickName());
                  respVo.setPost(card.getJob());
                  respVo.setViewNumber(cardDateList.get(z).getViewNumber());
                  respVo.setCusNumber(cardDateList.get(z).getCusNumber());
                  respVo.setActiveNumber(cardDateList.get(z).getActiveNumber());
                  respVo.setMessageNumber(cardDateList.get(z).getMessageNumber());
                  respVo.setSaveNumber(cardDateList.get(z).getSaveNumber());
                  respVo.setMailNumber(cardDateList.get(z).getMailNumber());
                  respVo.setCreateName(loginUser.getName());
                  respVo.setCreateTime(user.getCreateTime());

                  result.add(respVo);

                }
            }
            return result;
        }else {
            System.out.println("您已到期！");
            return null;

        }

    }

    @Override
    public Boolean addStaff(AddUpStaffReVo reVo) {
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
          LambdaQueryWrapper<User> userLambda  = new LambdaQueryWrapper<User>()
                  .eq(User::getPhone,reVo.getPhone());
          User userphone = userRepository.getOne(userLambda);
            System.out.println("userphone:"+userphone);
          //电话号码查重
          if (userphone==null){
              User user = User.builder().build();
              //创建账号
              user.setName(reVo.getName());
              user.setPassword(AesUtil.encrypt(reVo.getPhone().substring(reVo.getPhone().length()-6)));
              user.setPhone(reVo.getPhone());
              userRepository.saveOrUpdate(user);
              //赋予角色
              LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                      .eq(User::getPhone,reVo.getPhone());
              Long userId = userRepository.getOne(userLambdaQueryWrapper).getId();
              UserRole userRole = UserRole.builder().build();
              userRole.setUserId(userId.intValue());
              userRole.setRoleId(3);
              userRoleRepository.saveOrUpdate(userRole);
              //创建名片
              Card card = Card.builder().build();
              card.setName(reVo.getName());
              card.setEmail(reVo.getEmail());
              card.setJob(reVo.getPost());
              card.setWechatnumber(reVo.getWeChat());
              card.setWechatcardurl(reVo.getWeChatUrl());
              cardRepository.saveOrUpdate(card);
              //创建名片信息
              LambdaQueryWrapper<Card> cardLambda = new LambdaQueryWrapper<Card>()
                      .orderBy(true,false,Card::getId);
              Long cardId = cardRepository.list(cardLambda).get(0).getId();
              LambdaQueryWrapper<Organize> organizeLambda = new LambdaQueryWrapper<Organize>()
                      .and(i->i.eq(Organize::getCompanyId,company.getId()).eq(Organize::getNickName,reVo.getDepartment()));
              Long childId = organizationRepository.getOne(organizeLambda).getId();
              CardDate cardDate = CardDate.builder().build();
              cardDate.setCardId(cardId.intValue());

              cardDate.setUserId(userId.intValue());
              cardDate.setChildId(childId.intValue());
              cardDateRepository.saveOrUpdate(cardDate);
              return Boolean.TRUE;
          }else {
              System.out.println("该员工手机号已存在于其他企业");
              return Boolean.FALSE;
          }

        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updataStaff(AddUpStaffReVo reVo) {
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
            User user = User.builder().build();
            //修改账号
            user.setId(reVo.getId().longValue());
            user.setName(reVo.getName());
            user.setPassword(AesUtil.encrypt(reVo.getPhone().substring(reVo.getPhone().length()-6)));
            user.setPhone(reVo.getPhone());
            userRepository.saveOrUpdate(user);

            //修改名片信息
            LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                    .eq(CardDate::getUserId,reVo.getId());

            Integer cardId = cardDateRepository.getOne(cardDateLambda).getCardId();
            Long cardDateId = cardDateRepository.getOne(cardDateLambda).getId();
            CardDate cardDate = CardDate.builder().build();
            LambdaQueryWrapper<Organize> organizeLambda = new LambdaQueryWrapper<Organize>()
                    .and(i->i.eq(Organize::getCompanyId,company.getId()).eq(Organize::getNickName,reVo.getDepartment()));
            Long childId = organizationRepository.getOne(organizeLambda).getId();

            cardDate.setId(cardDateId);
            System.out.println("cardDateId"+cardDate.getId());
            cardDate.setCardId(cardId);
            cardDate.setUserId(reVo.getId());
            cardDate.setChildId(childId.intValue());

            cardDateRepository.saveOrUpdate(cardDate);

            //修改名片
            Card card = Card.builder().build();

            card.setId(cardId.longValue());
            card.setName(reVo.getName());
            card.setEmail(reVo.getEmail());
            card.setJob(reVo.getPost());
            card.setWechatnumber(reVo.getWeChat());
            card.setWechatcardurl(reVo.getWeChatUrl());

            cardRepository.saveOrUpdate(card);

            return Boolean.TRUE;

        }else {
            System.out.println("您已到期！");
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delStaff(DelStaffReVo reVo) {

        LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                .eq(User::getId,reVo.getId());
        Long userId = userRepository.getOne(userLambda).getId();
        LambdaQueryWrapper<CardDate>cardDateLambda = new LambdaQueryWrapper<CardDate>()
                .eq(CardDate::getUserId,userId);
        CardDate cardDate =cardDateRepository.getOne(cardDateLambda);
        Long cardDateId = cardDate.getId();
        Long card = cardDate.getCardId().longValue();
        LambdaQueryWrapper<Card> cardLambda = new LambdaQueryWrapper<Card>()
                .eq(Card::getId,card);
        Long cardId = cardRepository.getOne(cardLambda).getId();
        LambdaQueryWrapper<UserRole> userRoleLambda = new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId,userId);
        Long userRoleId = userRoleRepository.getOne(userRoleLambda).getId();

        cardRepository.removeById(cardId);
        cardDateRepository.removeById(cardDateId);
        userRoleRepository.removeById(userRoleId);
        userRepository.removeById(userId);

        return Boolean.TRUE;
    }
}
