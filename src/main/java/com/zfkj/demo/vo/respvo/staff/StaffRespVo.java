package com.zfkj.demo.vo.respvo.staff;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StaffRespVo {
    private Integer id;
    private String name;
    //部门
    private String department;
    //岗位
    private String post;
    //访客数
    private int viewNumber;
    //客户
    private int cusNumber;
    //活跃数
    private int activeNumber;
    //留言数
    private int messageNumber;
    //名片被保存数
    private int saveNumber;
    //添加通讯录数
    private int MailNumber;
    private String createName;
    private LocalDateTime createTime;
}
