package com.zfkj.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.demo.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerManageMapper extends BaseMapper<User> {
}
