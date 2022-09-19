package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.Role;
import com.zfkj.demo.dao.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-11-17 13:51
 **/
@Repository
public class RoleRepository extends ServiceImpl<RoleMapper, Role> {
}
