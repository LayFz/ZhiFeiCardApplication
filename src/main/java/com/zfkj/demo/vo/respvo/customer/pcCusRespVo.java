package com.zfkj.demo.vo.respvo.customer;

import com.zfkj.demo.dao.entity.CustomerDate;
import com.zfkj.demo.dao.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class pcCusRespVo {
    User user;
    CustomerDate customerDate;
}
