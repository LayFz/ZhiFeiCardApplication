package com.zfkj.demo.dao.entity;

import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account extends BaseEntity {
        //账号姓名
    private String name;
    //手机号
    private String phone;
    //角色
    private  String role;
    //创建人
    private  String adminname;


}
