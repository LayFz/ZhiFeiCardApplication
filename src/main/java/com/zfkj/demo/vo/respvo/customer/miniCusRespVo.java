package com.zfkj.demo.vo.respvo.customer;

import com.zfkj.demo.dao.entity.StaffCustomer;
import com.zfkj.demo.dao.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class miniCusRespVo {
    User user;
    StaffCustomer staffCustomer;
}
