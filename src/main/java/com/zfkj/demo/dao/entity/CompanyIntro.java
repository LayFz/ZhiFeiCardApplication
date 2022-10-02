package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("company_intro")
public class CompanyIntro extends BaseEntity {
    private  String name;
    private  Long companyId;
    private  Long belongBannerId;
    private int level;
    private String content;

}
