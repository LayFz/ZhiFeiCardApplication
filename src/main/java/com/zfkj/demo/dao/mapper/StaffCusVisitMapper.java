package com.zfkj.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.demo.dao.entity.StaffCusVisit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Mapper
public interface StaffCusVisitMapper extends BaseMapper<StaffCusVisit> {
    long visitNumByMonth(@Param("staffids") String staffids, @Param("month") String Month);
    List<StaffCusVisit>  visitNum(@Param("staffids") String staffids, @Param("month") String Month);
}
