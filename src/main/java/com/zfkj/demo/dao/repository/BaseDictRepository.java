package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.BaseDict;
import com.zfkj.demo.dao.mapper.BaseDictMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lijunlin
 * @since 2022-01-07
 **/
@Repository
public class BaseDictRepository extends ServiceImpl<BaseDictMapper, BaseDict> {
}
