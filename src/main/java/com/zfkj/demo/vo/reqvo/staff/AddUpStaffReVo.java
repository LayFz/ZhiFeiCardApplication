package com.zfkj.demo.vo.reqvo.staff;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUpStaffReVo {
    private Integer id;
    private String name;
    //部门
    private String department;
    //岗位
    private String post;
    private String phone;
    private String email;
    private String weChat;
    private String weChatUrl;

}
