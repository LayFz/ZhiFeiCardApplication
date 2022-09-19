package com.zfkj.demo.service;

import com.zfkj.demo.common.enums.ForSystemEnum;
import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.reqvo.role.AddRoleAuthReqVo;
import com.zfkj.demo.vo.reqvo.role.AddUpdateRoleReqVo;
import com.zfkj.demo.vo.reqvo.role.QueryRoleReqVo;
import com.zfkj.demo.vo.respvo.role.RoleAuthListRespVo;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
public interface IRoleService {

    /**
     * 分页查询角色列表
     * @return
     */
    PageResult<RoleRespVo> queryRole(QueryRoleReqVo reqVo);

    /**
     * 获取角色列表
     * @return
     */
    List<RoleRespVo> getRoles();

    /**
     * 查询用户角色列表
     * @param userId
     * @return
     */
    List<RoleRespVo> getRolesByUserId(Long userId);

    /**
     * 新增/编辑角色
     * @param reqVo
     * @return
     */
    Boolean addUpdRole(AddUpdateRoleReqVo reqVo);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    Boolean delRole(List<Integer> ids);

    /**
     * 获取角色所有权限资源树形集合
     * @param roleIds
     * @return
     */
    List<RoleAuthListRespVo> roleAuthList(List<Long> roleIds, ForSystemEnum forSystem);

    /**
     * 角色分配权限资源
     * @param reqVo
     * @return
     */
    Boolean addRoleAuth(AddRoleAuthReqVo reqVo);

}
