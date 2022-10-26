package com.zfkj.demo.vo.respvo.pcexposure;

import com.zfkj.demo.dao.entity.Card;
import com.zfkj.demo.dao.entity.CardDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpersonResVo {
//    CardDate cardDate;
    Card card;
    private String companyName;
    private String organizaName;
    private Integer visitNum;
    private Integer customerNum;
    private Integer activeNum;
}
