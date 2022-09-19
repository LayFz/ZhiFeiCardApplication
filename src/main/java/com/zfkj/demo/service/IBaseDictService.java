package com.zfkj.demo.service;

import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.reqvo.dict.BaseDictReqVO;
import com.zfkj.demo.vo.reqvo.dict.DictListReqVO;
import com.zfkj.demo.vo.respvo.dict.BaseDictRespVO;

import java.util.List;

/**
 * <p>
 * 基础字典信息表 服务类
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-07
 */
public interface IBaseDictService {
    /**
     * 新增字典
     * @author lijunlin
     * @param dictVO
     */
    Boolean addDict(BaseDictReqVO dictVO);

    /**
     * 修改字典
     * @author lijunlin
     * @param dictVO
     */
    Boolean editDict(BaseDictReqVO dictVO);
    /**
     * 分页获取字典数据
     * @author lijunlin
     * @param dictVO 查询信息
     * @return 字典信息
     */
    PageResult<BaseDictRespVO> getDictList(DictListReqVO dictVO);

    /**
     * 删除字典
     * @author lijunlin
     */
    Boolean deleteDict(List<Long> ids);

    /**
     * 根据类型获取字典
     * @return
     */
    List<BaseDictRespVO> getDictCode(String identifying);
}
