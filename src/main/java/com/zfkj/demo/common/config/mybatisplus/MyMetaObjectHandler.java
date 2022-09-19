package com.zfkj.demo.common.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zfkj.demo.common.enums.YesNoEnum;
import com.zfkj.demo.common.utils.SystemUserUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lijunlin
 * @Description mybatis对象处理器
 * @Date 2022/6/24 13:42
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATEID = "createId";
    private static final String CREATETIME = "createTime";
    private static final String UPDATEID = "updateId";
    private static final String UPDATETIME = "updateTime";
    private static final String DELFLAG = "delFlag";

    @Autowired
    private SystemUserUtil userUtil;

    /**
     * 自动填充创建时间修改时间
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取所有的字段名称
        List<String> list = Arrays.stream(metaObject.getGetterNames()).collect(Collectors.toList());
        //有这个字段，才进行赋值
        if (list.contains(CREATEID)){
            this.setFieldValByName(CREATEID, userUtil.getLoginUser().getId(),metaObject);
        }
        if (list.contains(CREATETIME)){
            this.setFieldValByName(CREATETIME, LocalDateTime.now(),metaObject);
        }
        if (list.contains(UPDATEID)){
            this.setFieldValByName(UPDATEID, userUtil.getLoginUser().getId(),metaObject);
        }
        if (list.contains(UPDATETIME)){
            this.setFieldValByName(UPDATETIME, LocalDateTime.now(),metaObject);
        }
        if (list.contains(DELFLAG)){
            this.setFieldValByName(DELFLAG, YesNoEnum.NO,metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取所有的字段名称
        List<String> list = Arrays.stream(metaObject.getGetterNames()).collect(Collectors.toList());
        if (list.contains(UPDATEID)){
            this.setFieldValByName(UPDATEID, userUtil.getLoginUser().getId(),metaObject);
        }
        if (list.contains(UPDATETIME)){
            this.setFieldValByName(UPDATETIME, LocalDateTime.now(),metaObject);
        }
    }
}
