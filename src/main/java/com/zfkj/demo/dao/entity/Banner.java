package com.zfkj.demo.dao.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("banner")
public class Banner {
    private Long id;
    private String imgUrl;
    private Long companyId;
    private String name;
    private OpenCloseEnum isVaild;
    private String remark;


}
