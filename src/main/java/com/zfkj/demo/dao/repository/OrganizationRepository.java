package com.zfkj.demo.dao.repository;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.Organize;
import com.zfkj.demo.dao.mapper.OrganizationMapper;
import org.springframework.stereotype.Repository;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/26 18:30
 */
@Repository
public class OrganizationRepository extends ServiceImpl<OrganizationMapper, Organize> {
}
