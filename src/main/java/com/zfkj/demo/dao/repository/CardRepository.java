package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.Card;
import com.zfkj.demo.dao.mapper.CardMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository extends ServiceImpl<CardMapper, Card> {
}
