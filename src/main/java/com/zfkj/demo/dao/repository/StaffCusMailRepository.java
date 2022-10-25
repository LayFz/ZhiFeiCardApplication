package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.StaffCusMail;
import com.zfkj.demo.dao.mapper.StaffCusMailMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StaffCusMailRepository extends ServiceImpl<StaffCusMailMapper, StaffCusMail> {
}
