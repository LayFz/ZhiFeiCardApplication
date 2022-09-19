package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.UserRole;
import com.zfkj.demo.dao.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-11-17 13:53
 **/
@Repository
public class UserRoleRepository extends ServiceImpl<UserRoleMapper, UserRole> {

}
