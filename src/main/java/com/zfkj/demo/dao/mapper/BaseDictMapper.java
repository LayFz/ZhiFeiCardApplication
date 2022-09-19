package com.zfkj.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zfkj.demo.dao.entity.BaseDict;
import com.zfkj.demo.vo.reqvo.dict.DictListReqVO;
import com.zfkj.demo.vo.respvo.dict.BaseDictRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 基础字典信息表 Mapper 接口
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-07
 */
@Mapper
public interface BaseDictMapper extends BaseMapper<BaseDict> {

    IPage<BaseDictRespVO> getDictList(IPage dictPage, @Param("req") DictListReqVO req);

    IPage<BaseDictRespVO> getDictCode(IPage<BaseDictRespVO> dictPage,@Param("reqVo")  DictListReqVO reqVo);
}
