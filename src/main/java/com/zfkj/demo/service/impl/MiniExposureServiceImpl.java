package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zfkj.demo.common.utils.MiniRoleUtils;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.common.utils.TimeUtil;
import com.zfkj.demo.dao.entity.StaffCusMail;
import com.zfkj.demo.dao.entity.StaffCusSave;
import com.zfkj.demo.dao.entity.StaffCusVisit;
import com.zfkj.demo.dao.entity.TextBoard;
import com.zfkj.demo.dao.repository.*;
import com.zfkj.demo.service.MiniExposureService;
import com.zfkj.demo.vo.respvo.miniexposure.MiniExResVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class MiniExposureServiceImpl implements MiniExposureService {
    @Autowired
    SystemUserUtil systemUserUtil;
    @Autowired
    TimeUtil timeUtil;

    @Autowired
    MiniRoleUtils miniRoleUtils;

    @Autowired
    CardDateRepository cardDateRepository;
    @Autowired
    StaffCustomerRepository staffCustomerRepository;
    @Autowired
    StaffCusVisitRepository staffCusVisitRepository;
    @Autowired
    StaffCusSaveRepository staffCusSaveRepository;
    @Autowired
    StaffCusMailRepository staffCusMailRepository;
    @Autowired
    TextBoardRepository textBoardRepository;
    @Override
    public HashMap<String, String> visitorProfile() {
        UserInfoVO user = systemUserUtil.getLoginUser();
        int userId = user.getId().intValue();
        Boolean isStaff = miniRoleUtils.isStaff();
        if (isStaff){
            //访客总量
            LambdaQueryWrapper<StaffCusVisit> totalLambda = new LambdaQueryWrapper<StaffCusVisit>()
                    .eq(StaffCusVisit::getStaffId,userId);
            //近七日访客总数
            LambdaQueryWrapper<StaffCusVisit> sevenLambda = new LambdaQueryWrapper<StaffCusVisit>()
                    .eq(StaffCusVisit::getStaffId,userId)
                    .apply("DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= date(visit_time)");
            //今日访客
            LambdaQueryWrapper<StaffCusVisit> todayLambda = new LambdaQueryWrapper<StaffCusVisit>()
                    .eq(StaffCusVisit::getStaffId,userId)
                    .apply("DATE_SUB(CURDATE(), INTERVAL 0 DAY) <= date(visit_time)");

            long totalVisitor = staffCusVisitRepository.count(totalLambda);
            long todayVistor = staffCusVisitRepository.count(todayLambda);
            long sevenVistor = staffCusVisitRepository.count(sevenLambda);

            //hashMap返回集
            HashMap<String, String> re = new HashMap<>();
            re.put("totalNum",String.valueOf(totalVisitor));
            re.put("todayNum",String.valueOf(todayVistor));
            re.put("sevenNum",String.valueOf(sevenVistor));

            return re;

        }
        return null;
    }

//    @Override
//    public HashMap<String, String> sevenTrend() {
//        UserInfoVO user = systemUserUtil.getLoginUser();
//        int userId = user.getId().intValue();
//        Boolean isStaff = miniRoleUtils.isStaff();
//        if (isStaff){
//            //今日访客数，近一天访客总数，近两天访客总数。。。
//            LambdaQueryWrapper<StaffCusVisit> todayLambda = new LambdaQueryWrapper<StaffCusVisit>()
//                    .eq(StaffCusVisit::getStaffId,userId)
//                    .apply("DATE_SUB(CURDATE(), INTERVAL 0 DAY) <= date(visit_time)");
//            LambdaQueryWrapper<StaffCusVisit> onedayLambda = new LambdaQueryWrapper<StaffCusVisit>()
//                    .eq(StaffCusVisit::getStaffId,userId)
//                    .apply("DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(visit_time)");
//            LambdaQueryWrapper<StaffCusVisit> twodayLambda = new LambdaQueryWrapper<StaffCusVisit>()
//                    .eq(StaffCusVisit::getStaffId,userId)
//                    .apply("DATE_SUB(CURDATE(), INTERVAL 2 DAY) <= date(visit_time)");
//            LambdaQueryWrapper<StaffCusVisit> threedayLambda = new LambdaQueryWrapper<StaffCusVisit>()
//                    .eq(StaffCusVisit::getStaffId,userId)
//                    .apply("DATE_SUB(CURDATE(), INTERVAL 3 DAY) <= date(visit_time)");
//            LambdaQueryWrapper<StaffCusVisit> fourdayLambda = new LambdaQueryWrapper<StaffCusVisit>()
//                    .eq(StaffCusVisit::getStaffId,userId)
//                    .apply("DATE_SUB(CURDATE(), INTERVAL 4 DAY) <= date(visit_time)");
//            LambdaQueryWrapper<StaffCusVisit> fivedayLambda = new LambdaQueryWrapper<StaffCusVisit>()
//                    .eq(StaffCusVisit::getStaffId,userId)
//                    .apply("DATE_SUB(CURDATE(), INTERVAL 5 DAY) <= date(visit_time)");
//            LambdaQueryWrapper<StaffCusVisit> sixdayLambda = new LambdaQueryWrapper<StaffCusVisit>()
//                    .eq(StaffCusVisit::getStaffId,userId)
//                    .apply("DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= date(visit_time)");
//
//            long todayNum = staffCusVisitRepository.count(todayLambda);
//            long onedayNum = staffCusVisitRepository.count(onedayLambda);
//            long twodayNum = staffCusVisitRepository.count(twodayLambda);
//            long threedayNum = staffCusVisitRepository.count(threedayLambda);
//            long fourdayNum = staffCusVisitRepository.count(fourdayLambda);
//            long fivedayNum = staffCusVisitRepository.count(fivedayLambda);
//            long sixdayNum = staffCusVisitRepository.count(sixdayLambda);
//
//            //计算前一天，前两天访客数，以此类推
//            long ago_one = onedayNum - todayNum;
//            long ago_two = twodayNum - onedayNum;
//            long ago_three = threedayNum - twodayNum;
//            long ago_four = fourdayNum - threedayNum;
//            long ago_five = fivedayNum - fourdayNum;
//            long ago_six = sixdayNum - fivedayNum;
//
//            HashMap<String,String> re = new HashMap<>();
//
//            re.put("today",String.valueOf(todayNum));
//            re.put("ago_oneday",String.valueOf(ago_one));
//            re.put("ago_twoday",String.valueOf(ago_two));
//            re.put("ago_threeday",String.valueOf(ago_three));
//            re.put("ago_fourday",String.valueOf(ago_four));
//            re.put("ago_fiveday",String.valueOf(ago_five));
//            re.put("ago_sixday",String.valueOf(ago_six));
//
//            return  re;
//        }
//        return null;
//    }

    @Override
    public HashMap<String, String> cumulativeDate(String startTime, String endTime) {
        UserInfoVO user = systemUserUtil.getLoginUser();
        int userId = user.getId().intValue();
        Boolean isStaff = miniRoleUtils.isStaff();
        if (isStaff){
            HashMap<String,String> re = new HashMap<>();
            if (startTime!=null&&endTime!=null){
                LambdaQueryWrapper<StaffCusVisit> staffCusVisitLambda = new LambdaQueryWrapper<StaffCusVisit>()
                        .eq(StaffCusVisit::getStaffId,userId)
                        .ge(StaffCusVisit::getVisitTime,startTime)
                        .le(StaffCusVisit::getVisitTime,endTime);

                LambdaQueryWrapper<StaffCusSave> staffCusSaveLambda = new LambdaQueryWrapper<StaffCusSave>()
                        .eq(StaffCusSave::getStaffId,userId)
                        .ge(StaffCusSave::getSaveTime,startTime)
                        .le(StaffCusSave::getSaveTime,endTime);
                LambdaQueryWrapper<StaffCusMail> staffCusMailLambda = new LambdaQueryWrapper<StaffCusMail>()
                        .eq(StaffCusMail::getStaffId,userId)
                        .ge(StaffCusMail::getMailTime,startTime)
                        .le(StaffCusMail::getMailTime,endTime);
                LambdaQueryWrapper<TextBoard> textBoardLambda = new LambdaQueryWrapper<TextBoard>()
                        .eq(TextBoard::getBelongId,userId)
                        .ge(TextBoard::getCreateTime,startTime)
                        .le(TextBoard::getCreateTime,endTime);
                //计算所有用户停留的总时长
                QueryWrapper<StaffCusVisit> stayTimeWrapper = new QueryWrapper<>();
                stayTimeWrapper.select(" IFNULL(SUM(stay_time),0) as total")
                        .eq("staff_id",userId)
                        .ge("visit_time",startTime)
                        .le("visit_time",endTime);

                Map<String,Object> map = staffCusVisitRepository.getMap(stayTimeWrapper);
                BigDecimal countStayTime = (BigDecimal) map.get("total");

                long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
                long saveNum = staffCusSaveRepository.count(staffCusSaveLambda);
                long mailNum = staffCusMailRepository.count(staffCusMailLambda);
                long textBoardNum = textBoardRepository.count(textBoardLambda);
                //计算次均停留
                String  total_ave_Staytime = ave(countStayTime.intValue(),Math.toIntExact(visitNum));

                re.put("visitNum",String.valueOf(visitNum));
                re.put("saveNum",String.valueOf(saveNum));
                re.put("mailNum",String.valueOf(mailNum));
                re.put("textBoardNum",String.valueOf(textBoardNum));
                re.put("stayTimeTotal",total_ave_Staytime);

            }else if (startTime!=null||endTime!=null){
                LambdaQueryWrapper<StaffCusVisit> staffCusVisitLambda = new LambdaQueryWrapper<StaffCusVisit>()
                        .eq(StaffCusVisit::getStaffId,userId)
                        .and(i->i.ge(StaffCusVisit::getVisitTime,startTime).or(z->z.le(StaffCusVisit::getVisitTime,endTime)));

                LambdaQueryWrapper<StaffCusSave> staffCusSaveLambda = new LambdaQueryWrapper<StaffCusSave>()
                        .eq(StaffCusSave::getStaffId,userId)
                        .and(i->i.ge(StaffCusSave::getSaveTime,startTime).or(z->z.le(StaffCusSave::getSaveTime,endTime)));
                LambdaQueryWrapper<StaffCusMail> staffCusMailLambda = new LambdaQueryWrapper<StaffCusMail>()
                        .eq(StaffCusMail::getStaffId,userId)
                        .and(i->i.ge(StaffCusMail::getMailTime,startTime).or(z->z.le(StaffCusMail::getMailTime,endTime)));
                LambdaQueryWrapper<TextBoard> textBoardLambda = new LambdaQueryWrapper<TextBoard>()
                        .eq(TextBoard::getBelongId,userId)
                        .and(i->i.ge(TextBoard::getCreateTime,startTime).or(z->z.le(TextBoard::getCreateTime,endTime)));

                QueryWrapper<StaffCusVisit> stayTimeWrapper = new QueryWrapper<>();
                stayTimeWrapper.select(" IFNULL(SUM(stay_time),0) as total")
                        .eq("staff_id",userId)
                        .and(i->i.ge("visit_time",startTime).or(z->z.le("visit_time",endTime)));
                Map<String,Object> map = staffCusVisitRepository.getMap(stayTimeWrapper);
                BigDecimal countStayTime = (BigDecimal) map.get("total");

                long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
                long saveNum = staffCusSaveRepository.count(staffCusSaveLambda);
                long mailNum = staffCusMailRepository.count(staffCusMailLambda);
                long textBoardNum = textBoardRepository.count(textBoardLambda);
                //计算次均停留
                String  total_ave_Staytime = ave(countStayTime.intValue(),Math.toIntExact(visitNum));

                re.put("visitNum",String.valueOf(visitNum));
                re.put("saveNum",String.valueOf(saveNum));
                re.put("mailNum",String.valueOf(mailNum));
                re.put("textBoardNum",String.valueOf(textBoardNum));
                re.put("stayTimeTotal",total_ave_Staytime);

            }else {
                LambdaQueryWrapper<StaffCusVisit> staffCusVisitLambda = new LambdaQueryWrapper<StaffCusVisit>()
                        .eq(StaffCusVisit::getStaffId,userId);
                LambdaQueryWrapper<StaffCusSave> staffCusSaveLambda = new LambdaQueryWrapper<StaffCusSave>()
                        .eq(StaffCusSave::getStaffId,userId);
                LambdaQueryWrapper<StaffCusMail> staffCusMailLambda = new LambdaQueryWrapper<StaffCusMail>()
                        .eq(StaffCusMail::getStaffId,userId);
                LambdaQueryWrapper<TextBoard> textBoardLambda = new LambdaQueryWrapper<TextBoard>()
                        .eq(TextBoard::getBelongId,userId);

                QueryWrapper<StaffCusVisit> stayTimeWrapper = new QueryWrapper<>();
                stayTimeWrapper.select(" IFNULL(SUM(stay_time),0) as total")
                        .eq("staff_id",userId);

                Map<String,Object> map = staffCusVisitRepository.getMap(stayTimeWrapper);
                BigDecimal countStayTime = (BigDecimal) map.get("total");

                long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
                long saveNum = staffCusSaveRepository.count(staffCusSaveLambda);
                long mailNum = staffCusMailRepository.count(staffCusMailLambda);
                long textBoardNum = textBoardRepository.count(textBoardLambda);
                //计算次均停留
                String  total_ave_Staytime = ave(countStayTime.intValue(),Math.toIntExact(visitNum));

                re.put("visitNum",String.valueOf(visitNum));
                re.put("saveNum",String.valueOf(saveNum));
                re.put("mailNum",String.valueOf(mailNum));
                re.put("textBoardNum",String.valueOf(textBoardNum));
                re.put("stayTimeTotal", total_ave_Staytime);
            }
            return re;

        }
        return null;
    }

    @Override
    public HashMap<String, String> averageDate(String startTime, String endTime) {
        UserInfoVO user = systemUserUtil.getLoginUser();
        int userId = user.getId().intValue();
        Boolean isStaff = miniRoleUtils.isStaff();
        if (isStaff){
            HashMap<String,String> re = new HashMap<>();
            if (startTime!=null&&endTime!=null){
                LambdaQueryWrapper<StaffCusVisit> staffCusVisitLambda = new LambdaQueryWrapper<StaffCusVisit>()
                        .eq(StaffCusVisit::getStaffId,userId)
                        .ge(StaffCusVisit::getVisitTime,startTime)
                        .le(StaffCusVisit::getVisitTime,endTime);

                LambdaQueryWrapper<StaffCusSave> staffCusSaveLambda = new LambdaQueryWrapper<StaffCusSave>()
                        .eq(StaffCusSave::getStaffId,userId)
                        .ge(StaffCusSave::getSaveTime,startTime)
                        .le(StaffCusSave::getSaveTime,endTime);
                LambdaQueryWrapper<StaffCusMail> staffCusMailLambda = new LambdaQueryWrapper<StaffCusMail>()
                        .eq(StaffCusMail::getStaffId,userId)
                        .ge(StaffCusMail::getMailTime,startTime)
                        .le(StaffCusMail::getMailTime,endTime);
                LambdaQueryWrapper<TextBoard> textBoardLambda = new LambdaQueryWrapper<TextBoard>()
                        .eq(TextBoard::getBelongId,userId)
                        .ge(TextBoard::getCreateTime,startTime)
                        .le(TextBoard::getCreateTime,endTime);
                //计算所有用户停留的总时长
                QueryWrapper<StaffCusVisit> stayTimeWrapper = new QueryWrapper<>();
                stayTimeWrapper.select(" IFNULL(SUM(stay_time),0) as total")
                        .eq("staff_id",userId)
                        .ge("visit_time",startTime)
                        .le("visit_time",endTime);

                Map<String,Object> map = staffCusVisitRepository.getMap(stayTimeWrapper);
                BigDecimal countStayTime = (BigDecimal) map.get("total");

                //计算日期间隙

                LocalDate start = LocalDate.parse(startTime,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate end = LocalDate.parse(endTime,DateTimeFormatter.ofPattern("yyyy-MM-dd"));

               Period period = Period.between(start,end);

                long day = period.getDays();


                long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
                long saveNum = staffCusSaveRepository.count(staffCusSaveLambda);
                long mailNum = staffCusMailRepository.count(staffCusMailLambda);
                long textBoardNum = textBoardRepository.count(textBoardLambda);

                String ave_visitNum = ave(Math.toIntExact(visitNum),Math.toIntExact(day));
                String ave_saveNum = ave(Math.toIntExact(saveNum),Math.toIntExact(day));
                String ave_mailNum = ave(Math.toIntExact(mailNum),Math.toIntExact(day));
                String ave_textNum = ave(Math.toIntExact(textBoardNum),Math.toIntExact(day));
                String  total_ave_Staytime = ave_stay(countStayTime.intValue(),Math.toIntExact(visitNum),Math.toIntExact(day));

                System.out.println("Num:"+ave_visitNum);


                re.put("visitNum",ave_visitNum);
                re.put("saveNum",ave_saveNum);
                re.put("mailNum",ave_mailNum);
                re.put("textBoardNum",ave_textNum);
                re.put("stayTimeTotal", total_ave_Staytime);

            }else {
                re.put("visitNum","0");
                re.put("saveNum","0");
                re.put("mailNum","0");
                re.put("textBoardNum","0");
                re.put("stayTimeTotal","0");
            }
            return re;

        }
        return null;
    }

    @Override
    public String ave(int a, int b) {
        if (a!=0&&b!=0){
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format((float)a/b);
        }
       return "0";
    }
    @Override
    public String ave_stay(int a,int b,int c){
        if (a!=0&&b!=0&&c!=0){
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format((float)a/b/c);
        }
       return "0" ;
    }

    @Override
    public List<MiniExResVo> miniExposureList(String startTime, String endTime) {
        UserInfoVO user = systemUserUtil.getLoginUser();
        int userId = user.getId().intValue();
        Boolean isStaff = miniRoleUtils.isStaff();
        if (isStaff){
            List<MiniExResVo> re = new ArrayList<>();
            if (startTime!=null&&endTime!=null){
                List<String> timeList = TimeUtil.getDate(startTime,endTime);
                System.out.println("TimeList:"+timeList);
                for (int i = 0;i<timeList.size();i++){
                    LambdaQueryWrapper<StaffCusVisit> staffCusVisitLambda = new LambdaQueryWrapper<StaffCusVisit>()
                            .eq(StaffCusVisit::getStaffId,userId)
                            .ge(StaffCusVisit::getVisitTime,startTime)
                            .le(StaffCusVisit::getVisitTime,endTime)
                            .likeRight(true,StaffCusVisit::getVisitTime,timeList.get(i));


                    LambdaQueryWrapper<StaffCusSave> staffCusSaveLambda = new LambdaQueryWrapper<StaffCusSave>()
                            .eq(StaffCusSave::getStaffId,userId)
                            .ge(StaffCusSave::getSaveTime,startTime)
                            .le(StaffCusSave::getSaveTime,endTime)
                            .likeRight(true,StaffCusSave::getSaveTime,timeList.get(i));
                    LambdaQueryWrapper<StaffCusMail> staffCusMailLambda = new LambdaQueryWrapper<StaffCusMail>()
                            .eq(StaffCusMail::getStaffId,userId)
                            .ge(StaffCusMail::getMailTime,startTime)
                            .le(StaffCusMail::getMailTime,endTime)
                            .likeRight(true,StaffCusMail::getMailTime,timeList.get(i));
                    LambdaQueryWrapper<TextBoard> textBoardLambda = new LambdaQueryWrapper<TextBoard>()
                            .eq(TextBoard::getBelongId,userId)
                            .ge(TextBoard::getCreateTime,startTime)
                            .le(TextBoard::getCreateTime,endTime)
                            .likeRight(true,TextBoard::getCreateTime,timeList.get(i));
                    //计算所有用户停留的总时长
                    QueryWrapper<StaffCusVisit> stayTimeWrapper = new QueryWrapper<>();
                    stayTimeWrapper.select(" IFNULL(SUM(stay_time),0) as total")
                            .eq("staff_id",userId)
                            .ge("visit_time",startTime)
                            .le("visit_time",endTime)
                            .likeRight(true,"visit_time",timeList.get(i));

                    Map<String,Object> map = staffCusVisitRepository.getMap(stayTimeWrapper);
                    BigDecimal countStayTime = (BigDecimal) map.get("total");

                    MiniExResVo miniExResVo = MiniExResVo.builder().build();

                    long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
                    long saveNum = staffCusSaveRepository.count(staffCusSaveLambda);
                    long mailNum = staffCusMailRepository.count(staffCusMailLambda);
                    long textBoardNum = textBoardRepository.count(textBoardLambda);
                    String total_ave_Staytime = ave(countStayTime.intValue(),Math.toIntExact(visitNum));

                    miniExResVo.setDayTime(timeList.get(i));
                    miniExResVo.setAveNum(total_ave_Staytime);
                    miniExResVo.setVisitNum(Math.toIntExact(visitNum));
                    miniExResVo.setSaveNum(Math.toIntExact(saveNum));
                    miniExResVo.setMailNum(Math.toIntExact(mailNum));
                    miniExResVo.setTextBoardNum(Math.toIntExact(textBoardNum));

                    re.add(miniExResVo);


                }
            }else {
                MiniExResVo miniExResVo = MiniExResVo.builder().build();

                miniExResVo.setDayTime("0");
                miniExResVo.setAveNum("0");
                miniExResVo.setVisitNum(0);
                miniExResVo.setSaveNum(0);
                miniExResVo.setMailNum(0);
                miniExResVo.setTextBoardNum(0);
                re.add(miniExResVo);
            }
            return re;
        }
        return null;
    }

    @Override
    public List<MiniExResVo> sevenTrend() {
        UserInfoVO user = systemUserUtil.getLoginUser();
        int userId = user.getId().intValue();
        Boolean isStaff = miniRoleUtils.isStaff();
        if (isStaff){
            //现在时间
            //获取当前日期时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endTime = sdf.format(System.currentTimeMillis());
            System.out.println("结束时间："+endTime);

            //获取七天前的日期
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 6);
            String startTime = sdf.format(calendar.getTime());
            System.out.println("结束时间："+startTime);

            List<String> timeList = TimeUtil.getDate(startTime,endTime);
            System.out.println("TimeList:"+timeList);
            List<MiniExResVo> re = new ArrayList<>();
            for(int i = 0;i< timeList.size();i++){
                LambdaQueryWrapper<StaffCusVisit> staffCusVisitLambda = new LambdaQueryWrapper<StaffCusVisit>()
                        .eq(StaffCusVisit::getStaffId,userId)
                        .likeRight(true,StaffCusVisit::getVisitTime,timeList.get(i));
                LambdaQueryWrapper<StaffCusSave> staffCusSaveLambda = new LambdaQueryWrapper<StaffCusSave>()
                        .eq(StaffCusSave::getStaffId,userId)
                        .likeRight(true,StaffCusSave::getSaveTime,timeList.get(i));
                LambdaQueryWrapper<StaffCusMail> staffCusMailLambda = new LambdaQueryWrapper<StaffCusMail>()
                        .eq(StaffCusMail::getStaffId,userId)
                        .likeRight(true,StaffCusMail::getMailTime,timeList.get(i));
                LambdaQueryWrapper<TextBoard> textBoardLambda = new LambdaQueryWrapper<TextBoard>()
                        .eq(TextBoard::getBelongId,userId)
                        .likeRight(true,TextBoard::getCreateTime,timeList.get(i));
                //计算所有用户停留的总时长
                QueryWrapper<StaffCusVisit> stayTimeWrapper = new QueryWrapper<>();
                stayTimeWrapper.select(" IFNULL(SUM(stay_time),0) as total")
                        .eq("staff_id",userId)
                        .likeRight(true,"visit_time",timeList.get(i));

                Map<String,Object> map = staffCusVisitRepository.getMap(stayTimeWrapper);
                BigDecimal countStayTime = (BigDecimal) map.get("total");


                long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
                long saveNum = staffCusSaveRepository.count(staffCusSaveLambda);
                long mailNum = staffCusMailRepository.count(staffCusMailLambda);
                long textBoardNum = textBoardRepository.count(textBoardLambda);
                String total_ave_Staytime = ave(countStayTime.intValue(),Math.toIntExact(visitNum));

                MiniExResVo miniExResVo = MiniExResVo.builder().build();
                miniExResVo.setDayTime(timeList.get(i));
                miniExResVo.setVisitNum(Math.toIntExact(visitNum));
                miniExResVo.setAveNum(total_ave_Staytime);
                miniExResVo.setSaveNum(Math.toIntExact(saveNum));
                miniExResVo.setMailNum(Math.toIntExact(mailNum));
                miniExResVo.setTextBoardNum(Math.toIntExact(textBoardNum));

                re.add(miniExResVo);

            }
            return re;
        }
        return null;
    }
}
