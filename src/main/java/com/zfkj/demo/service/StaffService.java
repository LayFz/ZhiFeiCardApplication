package com.zfkj.demo.service;

import com.zfkj.demo.vo.reqvo.staff.AddUpStaffReVo;
import com.zfkj.demo.vo.reqvo.staff.DelStaffReVo;
import com.zfkj.demo.vo.respvo.staff.StaffRespVo;

import java.util.List;

public interface StaffService {

    List<StaffRespVo>  getStaffList();

    Boolean addStaff(AddUpStaffReVo reVo);

    Boolean updataStaff(AddUpStaffReVo reVo);

    Boolean delStaff(DelStaffReVo reVo);
}
