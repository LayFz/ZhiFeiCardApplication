package com.zfkj.demo.vo.respvo.card;

import com.zfkj.demo.dao.entity.Card;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardRespVo {
    Card card;
    //电话
    private String phone;
    //公司
    private String company;
}
