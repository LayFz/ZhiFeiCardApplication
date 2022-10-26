package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.Organize;
import com.zfkj.demo.vo.reqvo.pcexposure.DePartReVo;
import com.zfkj.demo.vo.respvo.pcexposure.DepartResVo;
import com.zfkj.demo.vo.respvo.pcexposure.ExpersonResVo;
import com.zfkj.demo.vo.respvo.pcexposure.PcExResVo;

import java.util.HashMap;
import java.util.List;

public interface PcExposureService {
    /**
     * 名片统计
     * @param startMonth
     * @param endMonth
     * @return
     */
    List<PcExResVo> cardExposure(String startMonth, String endMonth);

    /**
     * 客户统计
     * @param startMonth
     * @param endMonth
     * @return
     */
    List<PcExResVo> customersNumber(String startMonth, String endMonth);

    /**
     * 优秀员工
     * @return
     */
    List<ExpersonResVo> personExcellent();
    List<DepartResVo> departmentStatistics(DePartReVo reVo);

    String ave(int a, int b);

    List<DepartResVo> getDePartList(List<Organize> organizeList,DePartReVo reVo);

}
