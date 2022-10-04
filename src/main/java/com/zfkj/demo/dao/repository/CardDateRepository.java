package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.CardDate;
import com.zfkj.demo.dao.mapper.CardDateMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CardDateRepository extends ServiceImpl<CardDateMapper, CardDate> {
}
