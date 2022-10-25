package com.zfkj.demo.common.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component

public class TimeUtil {
        public static List<String> getDate(String startTime, String endTime){
            //定义时间格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<String> list = new ArrayList<>();
            try {
                // 转化成日期类型
                Date startDate = simpleDateFormat.parse(startTime);
                Date endDate = simpleDateFormat.parse(endTime);

                //用Calendar 进行日期比较判断
                Calendar calendar = Calendar.getInstance();
                while (startDate.getTime()<=endDate.getTime()){
                    // 把日期添加到集合
                    list.add(simpleDateFormat.format(startDate));
                    // 设置日期
                    calendar.setTime(startDate);
                    //把日期增加一分
                    calendar.add(Calendar.DATE, 1);
                    // 获取增加后的日期
                    startDate=calendar.getTime();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return list;
        }

        public static void main(String[] args) {
            List<String> betweenDate = getDate("2022-10-13", "2022-10-20");
            System.out.println(betweenDate);
        }
    }

