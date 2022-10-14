package com.zfkj.demo.common.utils;


import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/10/10 13:47
 * 前端判断用户是否为客户和公司员工
 */
@Component
public class MiniRoleUtils {
    @Autowired
    SystemUserUtil systemUserUtil;

    /**
     * 判断请求用户是否为用户
     * @return Boolean
     */
    public Boolean isCustomer(){
        UserInfoVO userInfo = systemUserUtil.getLoginUser();
        List<RoleRespVo> roleRespVoList = userInfo.getRoles();
        if (roleRespVoList==null){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    /**
     * 判断请求用户是否为员工
     * @return Boolean
     */
    public Boolean isStaff(){
        UserInfoVO userInfo = systemUserUtil.getLoginUser();
        List<RoleRespVo> roleRespVoList = userInfo.getRoles();
        if (roleRespVoList == null){
            return Boolean.FALSE;
        }else {
            for (RoleRespVo roleRespVo : roleRespVoList) {
                String roleName = roleRespVo.getRoleName();
                if (roleName.equals("公司员工")) {
                    return Boolean.TRUE;
                }
            }
        }

        return Boolean.FALSE;
    }


}
