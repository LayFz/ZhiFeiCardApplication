package com.zfkj.demo.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zfkj.demo.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.demo.vo.basevo.LoginUser;
import com.zfkj.demo.vo.reqvo.user.LoginUserInfoVO;
import com.zfkj.demo.vo.reqvo.user.QueryUserReqVo;
import com.zfkj.demo.vo.respvo.user.QueryUserRespVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<User> {

    IPage<QueryUserRespVo> queryUser(IPage page, @Param("reqVo") QueryUserReqVo reqVo);

    LoginUser selectByUserNameLimit1(@Param("username") String username);

    @Update("update sys_user set openid=#{openid} where account=#{username} or phone=#{username}")
    Integer bindUserOpenid(@Param("openid") String openid, @Param("username") String username);

    @Select("select * from sys_user where account = #{username}")
    LoginUser selectByUserName(@Param("username") String username);

    @Select("select * from sys_user where phone = #{phone}")
    LoginUser selectByPhone(@Param("phone") String phone);

    User loginByAccountOrPhone(LoginUserInfoVO loginUserInfoVO);
}
