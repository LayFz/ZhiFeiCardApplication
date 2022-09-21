package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.dao.repository.CustomerManageRepository;
import com.zfkj.demo.service.CustomerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CustomerManageServiceImpl implements CustomerManageService {

    @Autowired
    CustomerManageRepository customerManageRepository;

    @Override
    public List<User> getAllCustomerBytime(){
        LambdaQueryWrapper<User> customerLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .orderBy(true,true, User::getCreateTime);
        List<User> customerList = customerManageRepository.list(customerLambdaQueryWrapper);
//        List<User> resultList = new ArrayList<>();
//        for (int i= 0;i<customerList.size();i++){
//            resultList.add(customerList.get(i));
//        }
        return customerList;
    }

    @Override
    public Integer getAllCustomer() {
        LambdaQueryWrapper<User> customerLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .orderBy(true,true, User::getCreateTime);
        List<User> customerList = customerManageRepository.list(customerLambdaQueryWrapper);
        return customerList.size();
    }

    @Override
    public Integer sevenDayCustomer() {
        LambdaQueryWrapper<User> sevenLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time)");
        List<User> sevenList = customerManageRepository.list(sevenLambdaQueryWrapper);
        LambdaQueryWrapper<User> todayLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 0 DAY) <= date(create_time)");
        List<User> todayList = customerManageRepository.list(todayLambdaQueryWrapper);
        int result = todayList.size()-sevenList.size();
        if(result<=0){
            return 0;
        }
        return result;
    }

    @Override
    public Integer todayCustomer() {
        /***
         * 当天创建的客户总数
         */
        LambdaQueryWrapper<User> todayLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 0 DAY) <= date(create_time)");
        List<User> todayList = customerManageRepository.list(todayLambdaQueryWrapper);
        int result = todayList.size();
        return result;
    }


    @Override
    public User selectPhoneByUser(String phone) {
        LambdaQueryWrapper<User> PhoneLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getPhone,phone);
        return customerManageRepository.getOne(PhoneLambdaQueryWrapper);
    }

    /**
     * 获取七日趋势数据
     * @return
     */
    @Override
    public HashMap<String,Integer> getSevenTrend() {
        /***
         * 查询当天,前一天,前两天。。。创建的客户总数
         */
        LambdaQueryWrapper<User> ago_dayLambda = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 0 DAY) <= date(create_time)");
        LambdaQueryWrapper<User> ago_onedayLambda = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(create_time)");
        LambdaQueryWrapper<User> ago_twodayLambda = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 2 DAY) <= date(create_time)");
        LambdaQueryWrapper<User> ago_threedayLambda = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 3 DAY) <= date(create_time)");
        LambdaQueryWrapper<User> ago_fourdayLambda = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 4 DAY) <= date(create_time)");
        LambdaQueryWrapper<User> ago_fivedayLambda = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 5 DAY) <= date(create_time)");
        LambdaQueryWrapper<User> ago_sixdayLambda = new LambdaQueryWrapper<User>()
                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= date(create_time)");
//        LambdaQueryWrapper<User> ago_sevendayLambda = new LambdaQueryWrapper<User>()
//                .exists("select * from sys_user where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time)");

       //hashMap返回集
        HashMap<String, Integer> re = new HashMap<>();

        /**
         * 当天,前一天,前两天。。。创建的客户总数
         */
        int  today = customerManageRepository.list(ago_dayLambda).size();
        int ago_one = customerManageRepository.list(ago_onedayLambda).size();
        int ago_two = customerManageRepository.list(ago_twodayLambda).size();
        int ago_three = customerManageRepository.list(ago_threedayLambda).size();
        int ago_four = customerManageRepository.list(ago_fourdayLambda).size();
        int ago_five = customerManageRepository.list(ago_fivedayLambda).size();
        int ago_six = customerManageRepository.list(ago_sixdayLambda).size();
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
}
