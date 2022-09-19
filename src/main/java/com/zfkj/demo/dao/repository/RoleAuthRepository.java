package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.RoleAuth;
import com.zfkj.demo.dao.mapper.RoleAuthMapper;
import org.springframework.stereotype.Repository;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-11-17 13:52
 **/
@Repository
public class RoleAuthRepository extends ServiceImpl<RoleAuthMapper, RoleAuth> {
}
