package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.dao.mapper.AccountManageMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountManageRepository extends ServiceImpl<AccountManageMapper, User> {
}
