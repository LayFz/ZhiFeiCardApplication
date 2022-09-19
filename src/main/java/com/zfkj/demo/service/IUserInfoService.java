package com.zfkj.demo.service;

import com.zfkj.demo.common.enums.ForSystemEnum;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.vo.basevo.LoginUser;
import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.reqvo.user.AddUserRolesReqVo;
import com.zfkj.demo.vo.reqvo.user.LoginUserInfoVO;
import com.zfkj.demo.vo.reqvo.user.QueryUserReqVo;
import com.zfkj.demo.vo.reqvo.user.UserSaveUpdateReqVo;
import com.zfkj.demo.vo.respvo.role.RoleAuthListRespVo;
import com.zfkj.demo.vo.respvo.user.QueryUserRespVo;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
public interface IUserInfoService {

    /**
     * 保存/修改用户
     * @param reqVo
     * @return
     */
    QueryUserRespVo userSaveUpdate(UserSaveUpdateReqVo reqVo);

    /**
     * 分页条件查询用户信息
     * @param reqVo
     */
    PageResult<QueryUserRespVo> queryUser(QueryUserReqVo reqVo);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    LoginUser selectUserById(Integer id);

    /**
     * 启用/禁用用户
     * @param id
     * @param status
     */
    void openCloseUser(Integer id, OpenCloseEnum status);

    /**
     * 删除用户
     * @param ids
     */
    void delUsers(List<Integer> ids);

    /**
     * 用户分配角色
     * @param reqVo
     * @return
     */
    Boolean addUserRoles(AddUserRolesReqVo reqVo);

    /**
     * 用户登录
     * 根据账号 以及 手机号登录
     *
     * @param loginUserInfoVO
     */
    UserInfoVO loginByAccountOrPhone(LoginUserInfoVO loginUserInfoVO);

    /**
     * 获取用户菜单资源
     * @param forSystem
     * @return
     */
    List<RoleAuthListRespVo> getUserMenus(ForSystemEnum forSystem);

    /**
     * 导入用户
     * @param file
     */
    void importUser(MultipartFile file) throws IOException;

    /**
     * 导出模板
     */
    void exportTemplete(List<QueryUserRespVo> list,Class clas) throws IOException;

    /**
     * 导出用户
     * @param reqVo
     */
    void exportUser(QueryUserReqVo reqVo) throws IOException;
}
