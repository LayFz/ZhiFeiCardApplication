package com.zfkj.demo.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.dao.entity.*;
import com.zfkj.demo.dao.repository.CardRepository;
import com.zfkj.demo.dao.repository.RoleRepository;
import com.zfkj.demo.dao.repository.UserRepository;
import com.zfkj.demo.dao.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component

public class JudgeRoleUtil {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    /**
     * 判断cardId是否为空
     * @param cardId
     * @return
     */
    public Boolean notNull(int cardId){
        LambdaQueryWrapper<Card> cardLambda = new LambdaQueryWrapper<Card>()
                .eq(Card::getId,cardId);
        Card card = cardRepository.getOne(cardLambda);
        if (card!=null){
           return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断角色
     * @param phone
     * @return
     */
    public String judgeRole(String phone){
        LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                .eq(User::getPhone,phone);

        if (userLambda!=null){
            User user= userRepository.getOne(userLambda);

            long userId = user.getId();
            LambdaQueryWrapper<UserRole> userRoleLambda = new LambdaQueryWrapper<UserRole>()
                    .eq(UserRole::getUserId,userId);

            UserRole userRole = userRoleRepository.getOne(userRoleLambda);
            if (userRole!=null){
                int roleId = userRole.getRoleId();
                LambdaQueryWrapper<Role> roleLambda  = new LambdaQueryWrapper<Role>()
                        .eq(Role::getId,roleId);
                System.out.println("角色："+roleRepository.getOne(roleLambda).getRoleName());
                return roleRepository.getOne(roleLambda).getRoleName();
            }
            return null;
        }
        return null;

    }

    /**
     * 判断用户是否为该员工本人
     * @param phone
     * @return
     */
    public Boolean staffHimself(String phone){
        LambdaQueryWrapper<User> userLambda = new LambdaQueryWrapper<User>()
                .eq(User::getPhone,phone);
        if (userLambda!=null){
            User user= userRepository.getOne(userLambda);

            long userId = user.getId();

            LambdaQueryWrapper<CardDate> cardDateLambda = new LambdaQueryWrapper<CardDate>()
                    .eq(CardDate::getUserId,userId);
            if (cardDateLambda!=null){
                return Boolean.TRUE;
            }
            }
        return Boolean.FALSE;
    }
}
