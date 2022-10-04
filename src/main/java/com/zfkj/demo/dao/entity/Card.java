package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("card")
public class Card extends BaseEntity {
    private String name;
    private String email;
    private String wechatnumber;
    private String wechatcardurl;
    private String job;
    private String perFilePic;
    private String perFileContent;



}
