package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.common.utils.MiniRoleUtils;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.dao.entity.CardDate;
import com.zfkj.demo.dao.entity.StaffCustomer;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.dao.repository.CardDateRepository;
import com.zfkj.demo.dao.repository.StaffCustomerRepository;
import com.zfkj.demo.dao.repository.UserRepository;
import com.zfkj.demo.service.MiniCusManageService;
import com.zfkj.demo.vo.respvo.customer.miniCusRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MiniCusManageServiceImpl implements MiniCusManageService {
    @Autowired
    CardDateRepository cardDateRepository;
    @Autowired
    StaffCustomerRepository staffCustomerRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    SystemUserUtil systemUserUtil;

    @Autowired
    MiniRoleUtils miniRoleUtils;

    @Override
    public int userId(int cardId) {

        LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                .eq(CardDate::getCardId,cardId);
        CardDate cardDate = cardDateRepository.getOne(cardDateLambda);

        return cardDate.getUserId();
    }

    @Override
    public Integer getAllCustomer(int cardId) {
        /*
         * 用户
         */
        //1.获取用户身份

        Boolean isStaff = miniRoleUtils.isStaff();
            if (!isStaff) {
                LambdaQueryWrapper<StaffCustomer> staffCustomerLambda = new
                        LambdaQueryWrapper<StaffCustomer>().eq(StaffCustomer::getUser_id, userId(cardId));
                List<StaffCustomer> staffCustomers = staffCustomerRepository.list(staffCustomerLambda);
                return staffCustomers.size();
            }
        return null;
    }

    @Override
    public Integer todayCustomer(int cardId) {

        Boolean isStaff = miniRoleUtils.isStaff();
            if (!isStaff) {
                LambdaQueryWrapper<StaffCustomer> staffCustomerLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 0 DAY) <= date(create_time)");
                List<StaffCustomer> staffCustomers = staffCustomerRepository.list(staffCustomerLambda);
                return staffCustomers.size();
            }
        return null;
    }

    @Override
    public Integer sevenDayCustomer(int cardId) {


        Boolean isStaff = miniRoleUtils.isStaff();

            if (!isStaff) {
                LambdaQueryWrapper<StaffCustomer> sevenLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time)");
                List<StaffCustomer> seven = staffCustomerRepository.list(sevenLambda);

                LambdaQueryWrapper<StaffCustomer> todayLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 0 DAY) <= date(create_time)");
                List<StaffCustomer> today = staffCustomerRepository.list(todayLambda);

                return seven.size()-today.size();
            }

        return null;

    }

    @Override
    public HashMap<String, Integer> getSevenTrend(int cardId) {


        Boolean isStaff = miniRoleUtils.isStaff();

            if (!isStaff) {
                /***
                 * 查询当天,前一天,前两天。。。创建的客户总数
                 */
                LambdaQueryWrapper<StaffCustomer> ago_todayLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 0 DAY) <= date(create_time)");
                LambdaQueryWrapper<StaffCustomer> ago_onedayLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(create_time)");
                LambdaQueryWrapper<StaffCustomer> ago_twodayLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 2 DAY) <= date(create_time)");
                LambdaQueryWrapper<StaffCustomer> ago_threedayLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 3 DAY) <= date(create_time)");
                LambdaQueryWrapper<StaffCustomer> ago_fourdayLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 4 DAY) <= date(create_time)");
                LambdaQueryWrapper<StaffCustomer> ago_fivedayLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 5 DAY) <= date(create_time)");
                LambdaQueryWrapper<StaffCustomer> ago_sixdayLambda = new LambdaQueryWrapper<StaffCustomer>()
                        .eq(StaffCustomer::getUser_id,userId(cardId))
                        .apply("DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= date(create_time)");
//        LambdaQueryWrapper<User> ago_sevendayLambda = new LambdaQueryWrapper<User>()
//                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time)");

                //hashMap返回集
                HashMap<String, Integer> re = new HashMap<>();

                /**
                 * 当天,前一天,前两天。。。创建的客户总数
                 */
                int  today = staffCustomerRepository.list(ago_todayLambda).size();
                int ago_one = staffCustomerRepository.list(ago_onedayLambda).size();
                int ago_two =staffCustomerRepository.list(ago_twodayLambda).size();
                int ago_three = staffCustomerRepository.list(ago_threedayLambda).size();
                int ago_four = staffCustomerRepository.list(ago_fourdayLambda).size();
                int ago_five = staffCustomerRepository.list(ago_fivedayLambda).size();
                int ago_six = staffCustomerRepository.list(ago_sixdayLambda).size();
//        int ago_seven = customerManageRepository.list(ago_sevendayLambda).size();

                /**
                 *计算前一天，前两天的用户数量，以此类推
                 */
                int ago_first = ago_one - today;
                int ago_second = ago_two - ago_one;
                int ago_thrid = ago_three - ago_two;
                int ago_fourth = ago_four - ago_three;
                int ago_fifth = ago_five - ago_four;
                int ago_sixth = ago_six - ago_five;
//        int ago_seventh = ago_seven - ago_six;

                re.put("today",today);
                re.put("ago_first",ago_first);
                re.put("ago_second",ago_second);
                re.put("ago_thrid",ago_thrid);
                re.put("ago_fourth",ago_fourth);
                re.put("ago_fifth",ago_fifth);
                re.put("ago_sixth",ago_sixth);

                return re;

        }
        return null;
    }

    @Override
    public List<miniCusRespVo> customerList(int cardId) {


        Boolean isStaff = miniRoleUtils.isStaff();

            if (!isStaff) {
                LambdaQueryWrapper<StaffCustomer> staffCustomerLambda = new
                        LambdaQueryWrapper<StaffCustomer>().eq(StaffCustomer::getUser_id,userId(cardId));
                List<StaffCustomer> staffCustomers = staffCustomerRepository.list(staffCustomerLambda);
                List<miniCusRespVo> resultList = new ArrayList<>();
                for (StaffCustomer staffCustomer : staffCustomers) {
                    miniCusRespVo mini = miniCusRespVo.builder().build();
                    LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                            .eq(User::getId, staffCustomer.getCus_id());
                    User user = userRepository.getOne(userLambda);

                    mini.setUser(user);
                    mini.setStaffCustomer(staffCustomer);

                    resultList.add(mini);
                }
                return resultList;
        }
        return null;


    }

    @Override
    public List<miniCusRespVo> selectCustomer(int cardId, String phone) {


        Boolean isStaff = miniRoleUtils.isStaff();

            if (!isStaff) {
                LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                        .eq(User::getPhone,phone);

                User user = userRepository.getOne(userLambda);
                if (user==null){
                    return customerList(cardId);
                }else {
                    LambdaQueryWrapper<StaffCustomer> staffCustomerLambda = new LambdaQueryWrapper<StaffCustomer>()
                            .eq(StaffCustomer::getUser_id,userId(cardId))
                            .eq(StaffCustomer::getCus_id,user.getId());

                    StaffCustomer staffCustomer = staffCustomerRepository.getOne(staffCustomerLambda);

                    List<miniCusRespVo> resultList = new ArrayList<>();

                    miniCusRespVo mini = miniCusRespVo.builder().build();

                    mini.setStaffCustomer(staffCustomer);
                    mini.setUser(user);
                    resultList.add(mini);

                    return resultList;
                }


        }
        return null;
    }
}
