package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.common.utils.TimeUtil;
import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.dao.repository.*;
import com.zfkj.demo.service.PcExposureService;
import com.zfkj.demo.vo.reqvo.pcexposure.DePartReVo;
import com.zfkj.demo.vo.respvo.pcexposure.DepartResVo;
import com.zfkj.demo.vo.respvo.pcexposure.ExpersonResVo;
import com.zfkj.demo.vo.respvo.pcexposure.PcExResVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PcExposureServiceImpl implements PcExposureService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SystemUserUtil userUtil;
    @Autowired
    TimeUtil timeUtil;

    @Autowired
    CardDateRepository cardDateRepository;
    @Autowired
    StaffCusVisitRepository staffCusVisitRepository;
    @Autowired
    StaffCusSaveRepository staffCusSaveRepository;
    @Autowired
    StaffCusMailRepository staffCusMailRepository;
    @Autowired
    TextBoardRepository textBoardRepository;
    @Autowired
    StaffCusForwardRepository staffCusForwardRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public List<PcExResVo> cardExposure(String startMonth, String endMonth) {
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
            //获取该公司所有员工的userId
            LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                    .inSql(CardDate::getChildId,"SELECT id from organization WHERE company_id = "+company.getId()+"");

            List<CardDate> cardDate = cardDateRepository.list(cardDateLambda);

            List<Integer> staffIdList = cardDate.stream().map(CardDate::getUserId).collect(Collectors.toList());
            //List转化字符串
            String staffIds = StringUtils.join(staffIdList.toArray(),",");
            System.out.println("staffids:"+staffIds);

            if (startMonth!=null&&endMonth!=null){
                List<String> monthList = TimeUtil.getMonth(startMonth,endMonth);
                List<PcExResVo> re = new ArrayList<>();
                for (String s : monthList) {
                    QueryWrapper<StaffCusVisit> staffCusVisitLambda = new QueryWrapper<StaffCusVisit>()
                            .likeRight(true,"visit_time",s)
                            .inSql("staff_id",staffIds);

                    long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
//                long visitNum = staffCusVisitRepository.getBaseMapper().visitNumByMonth(staffIds, s);
//                List<StaffCusVisit> staffCusVisits = staffCusVisitRepository.getBaseMapper().visitNum(staffIds,s);
//                List<StaffCusVisit> staffCusVisits = staffCusVisitRepository.list(staffCusVisitLambda);
//                System.out.println("staffs:"+staffCusVisits);

                    PcExResVo pcExResVo = PcExResVo.builder().build();


                    pcExResVo.setMonthTime(s);
                    pcExResVo.setVisitNum(Math.toIntExact(visitNum));
                    System.out.println("pc:" + pcExResVo);
                    re.add(pcExResVo);
                }
                return re;
            }
            return null;
        }
        return null;
    }

    @Override
    public List<PcExResVo> customersNumber(String startMonth, String endMonth) {
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
            //获取该公司所有员工的userId
            LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                    .inSql(CardDate::getChildId,"SELECT id from organization WHERE company_id = "+company.getId()+"");

            List<CardDate> cardDate = cardDateRepository.list(cardDateLambda);

            List<Integer> staffIdList = cardDate.stream().map(CardDate::getUserId).collect(Collectors.toList());
            //List转化字符串
            String staffIds = StringUtils.join(staffIdList.toArray(),",");

            if (startMonth!=null&&endMonth!=null){
                List<String> monthList = TimeUtil.getMonth(startMonth,endMonth);
                List<PcExResVo> re = new ArrayList<>();
                for (int i = 0;i<monthList.size();i++){
                    LambdaQueryWrapper<StaffCusSave> staffCusSaveLambda = new LambdaQueryWrapper<StaffCusSave>()
                            .inSql(StaffCusSave::getStaffId,staffIds)
                            .likeRight(true,StaffCusSave::getSaveTime,monthList.get(i));

                    long saveNum = staffCusSaveRepository.count(staffCusSaveLambda);
                    PcExResVo pcExResVo = PcExResVo.builder().build();


                    pcExResVo.setMonthTime(monthList.get(i));
                    pcExResVo.setCustomerNum(Math.toIntExact(saveNum));
                    System.out.println("pc:"+pcExResVo);
                    re.add(pcExResVo);
                }
                return re;
            }
            return null;
        }
        return null;
    }

    @Override
    public List<ExpersonResVo> personExcellent() {
        UserInfoVO loginUser = userUtil.getLoginUser();
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getValidData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")) {
            //获取本月日期
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH)+1;
            int year = calendar.get(Calendar.YEAR);

            String thisMonth = ""+year+"-"+month;

            QueryWrapper<StaffCusVisit> staffCusVisitQuery = new QueryWrapper<>();
            staffCusVisitQuery.select("staff_id,count(*) as num")
                    .groupBy("staff_id")
                    .orderByDesc("num")
                    .likeRight("visit_time",thisMonth);
           List<StaffCusVisit> staffCusVisitList = staffCusVisitRepository.list(staffCusVisitQuery);
            System.out.println("staffCusVisitList:"+staffCusVisitList);

            List<ExpersonResVo> re = new ArrayList<>();
           for (int i = 0;i<staffCusVisitList.size();i++){
               LambdaQueryWrapper<CardDate> cardDateLambdaQuery  = new LambdaQueryWrapper<CardDate>()
                       .eq(CardDate::getUserId,staffCusVisitList.get(i).getStaffId());
               CardDate cardDate = cardDateRepository.getOne(cardDateLambdaQuery);

               LambdaQueryWrapper<StaffCusVisit> staffCusVisitLambda = new LambdaQueryWrapper<StaffCusVisit>()
                       .eq(StaffCusVisit::getStaffId,cardDate.getUserId());
               LambdaQueryWrapper<StaffCusSave> staffCusSaveLambda = new LambdaQueryWrapper<StaffCusSave>()
                       .eq(StaffCusSave::getStaffId,cardDate.getUserId());
               LambdaQueryWrapper<StaffCusMail> staffCusMailLambda = new LambdaQueryWrapper<StaffCusMail>()
                       .eq(StaffCusMail::getStaffId,cardDate.getUserId());
               LambdaQueryWrapper<TextBoard> textBoardLambda = new LambdaQueryWrapper<TextBoard>()
                       .eq(TextBoard::getBelongId,cardDate.getUserId());
               LambdaQueryWrapper<StaffCusForward> staffCusForwardLambda = new LambdaQueryWrapper<StaffCusForward>()
                       .eq(StaffCusForward::getStaffId,cardDate.getUserId());


               LambdaQueryWrapper<Card> cardLambdaQuery = new LambdaQueryWrapper<Card>()
                       .eq(Card::getId,cardDate.getCardId());
                Card card = cardRepository.getOne(cardLambdaQuery);
                LambdaQueryWrapper<Organize> organizeLambda = new LambdaQueryWrapper<Organize>()
                        .eq(Organize::getId,cardDate.getChildId());

                Organize organize = organizationRepository.getOne(organizeLambda);

               long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
               long saveNum = staffCusSaveRepository.count(staffCusSaveLambda);
               long mailNum = staffCusMailRepository.count(staffCusMailLambda);
               long textBoardNum = textBoardRepository.count(textBoardLambda);
               long forwardNum = staffCusForwardRepository.count(staffCusForwardLambda);

               long activeNum = visitNum+saveNum+mailNum+forwardNum+textBoardNum;

               ExpersonResVo expersonResVo = ExpersonResVo.builder().build();

               expersonResVo.setCard(card);
               expersonResVo.setCompanyName(company.getName());
               expersonResVo.setOrganizaName(organize.getNickName());
               expersonResVo.setVisitNum(Math.toIntExact(visitNum));
               expersonResVo.setCustomerNum(Math.toIntExact(saveNum));
               expersonResVo.setActiveNum(Math.toIntExact(activeNum));
               re.add(expersonResVo);
           }
        return re;
        }
        return null;
    }

    @Override
    public List<DepartResVo> departmentStatistics(DePartReVo reVo) {
        UserInfoVO loginUser = userUtil.getLoginUser();
        //查询公司是否可用并且是否到期
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<Company>()
                .eq(Company::getUserId, loginUser.getId());
        Company company = companyRepository.getOne(companyLambdaQueryWrapper);
        //到期时间
        long vaildTime = company.getValidData().getTime();
        //系统时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < vaildTime&&company.getIsVaild().getCode().equals("OPEN")) {
            List<DepartResVo> re;
            switch (reVo.getDepartLevel()){
                case 1:
                    //获取该公司的一级部门
                    LambdaQueryWrapper<Organize> organizeLambda = new LambdaQueryWrapper<Organize>()
                            .eq(Organize::getCompanyId,company.getId())
                            .eq(Organize::getChildId,0);
                    List<Organize> organizeList = organizationRepository.list(organizeLambda);
                    switch (reVo.getRank()){
                        case 1:
                            re = getDePartList(organizeList,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getVisitNum));
                            return re;
                        case 2:
                            re = getDePartList(organizeList,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getAve_visitNum));
                            return re;
                        case 3:
                            re = getDePartList(organizeList,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getCustomerNum));
                            return re;
                        case 4:
                            re = getDePartList(organizeList,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getAve_customerNum));
                            return re;
                        case 5:
                            re = getDePartList(organizeList,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getActiveNum));
                            return re;
                        case 6:
                            re = getDePartList(organizeList,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getAve_activeNum));
                            return re;
                        default:
                            return null;
                    }
                case 2:
                    //获取该公司的二级部门
                    LambdaQueryWrapper<Organize> organizeLambda_two = new LambdaQueryWrapper<Organize>()
                            .eq(Organize::getCompanyId,company.getId())
                            .ne(Organize::getChildId,0);
                    List<Organize> organizeList_two = organizationRepository.list(organizeLambda_two);
                    switch (reVo.getRank()){
                        case 1:
                            re = getDePartList(organizeList_two,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getVisitNum));
                            return re;
                        case 2:
                            re = getDePartList(organizeList_two,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getAve_visitNum));
                            return re;
                        case 3:
                            re = getDePartList(organizeList_two,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getCustomerNum));
                            return re;
                        case 4:
                            re = getDePartList(organizeList_two,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getAve_customerNum));
                            return re;
                        case 5:
                            re = getDePartList(organizeList_two,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getActiveNum));
                            return re;
                        case 6:
                            re = getDePartList(organizeList_two,reVo);
                            re.sort(Comparator.comparing(DepartResVo::getAve_activeNum));
                            return re;
                        default:
                            return null;
                    }
                default:
                    return null;
            }

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
    public List<DepartResVo> getDePartList(List<Organize> organizeList,DePartReVo reVo) {
        List<DepartResVo> re = new ArrayList<>();
        for (int i = 0;i<organizeList.size();i++){
            //获取该部门员工userId;
            LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                    .eq(CardDate::getChildId,organizeList.get(i).getId());
            List<CardDate> cardDateList = cardDateRepository.list(cardDateLambda);
            if (cardDateList.size()!=0){
                List<Integer> staffIdList = cardDateList.stream().map(CardDate::getUserId).collect(Collectors.toList());

                //List转化字符串
                String staffIds = StringUtils.join(staffIdList.toArray(),",");

                //获取访客量,人均访客量...
                LambdaQueryWrapper<StaffCusVisit> staffCusVisitLambda = new LambdaQueryWrapper<StaffCusVisit>()
                        .inSql(StaffCusVisit::getStaffId,staffIds)
                        .ge(StaffCusVisit::getVisitTime,reVo.getStartTime())
                        .le(StaffCusVisit::getVisitTime,reVo.getEndTime());
                LambdaQueryWrapper<StaffCusSave> staffCusSave = new LambdaQueryWrapper<StaffCusSave>()
                        .inSql(StaffCusSave::getStaffId,staffIds)
                        .ge(StaffCusSave::getSaveTime,reVo.getStartTime())
                        .le(StaffCusSave::getSaveTime,reVo.getEndTime());

                LambdaQueryWrapper<StaffCusMail> staffCusMailLambda = new LambdaQueryWrapper<StaffCusMail>()
                        .inSql(StaffCusMail::getStaffId,staffIds)
                        .ge(StaffCusMail::getMailTime,reVo.getStartTime())
                        .le(StaffCusMail::getMailTime,reVo.getEndTime());
                LambdaQueryWrapper<StaffCusForward> staffCusForwardLambda = new LambdaQueryWrapper<StaffCusForward>()
                        .inSql(StaffCusForward::getStaffId,staffIds)
                        .ge(StaffCusForward::getForwardTime,reVo.getStartTime())
                        .le(StaffCusForward::getForwardTime,reVo.getEndTime());
                LambdaQueryWrapper<TextBoard> textBoardLambda = new LambdaQueryWrapper<TextBoard>()
                        .inSql(TextBoard::getBelongId,staffIds)
                        .ge(TextBoard::getCreateTime,reVo.getStartTime())
                        .le(TextBoard::getCreateTime,reVo.getEndTime());

                long visitNum = staffCusVisitRepository.count(staffCusVisitLambda);
                long saveNum = staffCusSaveRepository.count(staffCusSave);
                long mailNum = staffCusMailRepository.count(staffCusMailLambda);
                long forward = staffCusForwardRepository.count(staffCusForwardLambda);
                long textboard = textBoardRepository.count(textBoardLambda);

                long activeNum = visitNum+saveNum+mailNum+forward+textboard;

                String ave_visitNum = ave(Math.toIntExact(visitNum),staffIdList.size());
                String ave_customer = ave(Math.toIntExact(saveNum),staffIdList.size());
                String ave_active = ave(Math.toIntExact(activeNum),staffIdList.size());

                DepartResVo departResVo = DepartResVo.builder().build();

                departResVo.setDepartName(organizeList.get(i).getNickName());
                departResVo.setDepartNum(staffIdList.size());
                departResVo.setVisitNum(Math.toIntExact(visitNum));
                departResVo.setAve_visitNum(ave_visitNum);
                departResVo.setCustomerNum(Math.toIntExact(saveNum));
                departResVo.setAve_customerNum(ave_customer);
                departResVo.setActiveNum(Math.toIntExact(activeNum));
                departResVo.setAve_activeNum(ave_active);

                re.add(departResVo);
            }
        }
        return re;
    }
}
