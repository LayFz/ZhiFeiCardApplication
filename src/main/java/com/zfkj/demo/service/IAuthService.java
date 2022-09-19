package com.zfkj.demo.service;

import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.auth.AuthAddUpdateReqVo;
import com.zfkj.demo.vo.respvo.auth.*;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;

import java.util.List;

/**
 * <p>
 * 菜单资源表 服务类
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
public interface IAuthService {

    /**
     * 查询用户url资源权限
     * @param url
     * @return
     */
    String getAuthByUrl(String url);

    /**
     * 获取权限资源树
     * @param clientType
     * @return
     */
    AuthTreeTopRespVo getAuthTree(String clientType);

    /**
     * 添加/修改权限资源
     * @param reqVo
     * @return
     */
    Boolean addOrUpdateAuth(AuthAddUpdateReqVo reqVo);

    /**
     * 删除权限资源
     * @param ids
     * @return
     */
    Boolean delAuthByIds(List<Integer> ids);

}
