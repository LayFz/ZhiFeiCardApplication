package com.zfkj.demo.dao.mapper;

import com.zfkj.demo.dao.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色关联 Mapper 接口
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<RoleRespVo> getRolesByUserId(@Param("userId") Long userId);

}
