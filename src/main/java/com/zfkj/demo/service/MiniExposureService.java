package com.zfkj.demo.service;

import com.zfkj.demo.vo.respvo.miniexposure.MiniExResVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface MiniExposureService {

    //相除
    String ave(int a,int b);
    //日均停留处理
    String ave_stay(int a,int b,int c);
    HashMap<String, String> visitorProfile();
    HashMap<String, String> sevenTrend();
    HashMap<String,String> cumulativeDate(String startTime,String endTime);
    HashMap<String,String>  averageDate(String startTime,String endTime);

    List<MiniExResVo> miniExposureList(String startTime,String endTime);


}
