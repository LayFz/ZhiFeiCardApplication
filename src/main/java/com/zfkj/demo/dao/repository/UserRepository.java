package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.dao.mapper.UserInfoMapper;
import com.zfkj.demo.vo.reqvo.user.UserSaveUpdateReqVo;
import org.springframework.stereotype.Repository;

/**
 * @program:
 * @description:
 * @author: lijunlin
 * @create: 2019/12/31 15:28
 **/
@Repository
public class UserRepository extends ServiceImpl<UserInfoMapper, User> {

    public User getUserByAccountPhone(UserSaveUpdateReqVo reqVo){
        User user = this.getOne(Wrappers.<User>lambdaQuery()
                        .and(wrapper -> wrapper
                                .eq(User::getAccount, reqVo.getAccount())
                                .or()
                                .eq(User::getPhone, reqVo.getPhone()))
                .last("limit 1")
        );
        return user;
    }


}
