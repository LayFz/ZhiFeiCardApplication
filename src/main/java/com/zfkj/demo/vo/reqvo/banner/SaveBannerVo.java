package com.zfkj.demo.vo.reqvo.banner;

import com.zfkj.demo.common.enums.OpenCloseEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaveBannerVo {
    private Long id;
    private String imgUrl;
    private String name;
}
