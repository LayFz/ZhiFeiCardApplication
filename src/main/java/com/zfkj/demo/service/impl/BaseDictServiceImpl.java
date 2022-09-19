package com.zfkj.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zfkj.demo.dao.entity.BaseDict;
import com.zfkj.demo.common.enums.YesNoEnum;
import com.zfkj.demo.common.exception.BusinessRootRuntimeException;
import com.zfkj.demo.common.exception.Exceptions;
import com.zfkj.demo.dao.repository.BaseDictRepository;
import com.zfkj.demo.service.IBaseDictService;
import com.zfkj.demo.common.utils.AssertUtils;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.reqvo.dict.BaseDictReqVO;
import com.zfkj.demo.vo.reqvo.dict.DictListReqVO;
import com.zfkj.demo.vo.respvo.dict.BaseDictRespVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 基础字典信息表 服务实现类
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-07
 */
@Service
public class BaseDictServiceImpl implements IBaseDictService {

    @Autowired
    private BaseDictRepository dictRepository;

    @Autowired
    private SystemUserUtil userUtil;

    @Override
    public Boolean addDict(BaseDictReqVO dictVO) {
        BaseDict baseDict = BeanUtil.toBean(dictVO, BaseDict.class);
        if (Objects.isNull(dictVO.getParentId())){
            baseDict.setParentId(0L);
        }
        dictRepository.save(baseDict);
        return Boolean.TRUE;
    }

    @Override
    public Boolean editDict(BaseDictReqVO dictVO) {
        BaseDict baseDict = BeanUtil.toBean(dictVO, BaseDict.class);
        BaseDict entity = dictRepository.getById(baseDict.getId());
        // 判断是否允许编辑
        AssertUtils.isTrue(Objects.equals(YesNoEnum.YES,entity.getIsEdit()),Exceptions.DictEX.SEALED);
        dictRepository.updateById(baseDict);
        return Boolean.TRUE;
    }

    @Override
    public PageResult<BaseDictRespVO> getDictList(DictListReqVO reqVo) {
        // 组装分页对象
        IPage<BaseDictRespVO> dictPage = new Page<>(reqVo.getPageNum(), reqVo.getPageSize());
        // 分页对象传入mapper实现sql分页
        dictRepository.getBaseMapper().getDictList(dictPage,reqVo);
        // 组装查询结果
        PageResult<BaseDictRespVO> pageResult = new PageResult<>(reqVo.getPageNum(), reqVo.getPageSize(), dictPage.getTotal(), dictPage.getRecords());
        return pageResult;
    }

    @Transactional(rollbackFor = BusinessRootRuntimeException.class)
    @Override
    public Boolean deleteDict(List<Long> ids) {
        dictRepository.removeByIds(ids);
        return Boolean.TRUE;
    }

    @Override
    public List<BaseDictRespVO> getDictCode(String identifying) {
        List<BaseDictRespVO> list = Lists.newArrayList();
        List<BaseDict> dictList = dictRepository.list(Wrappers.<BaseDict>lambdaQuery().eq(BaseDict::getIdentifying, identifying).ne(BaseDict::getParentId, 0L));
        dictList.forEach(d->{
            BaseDictRespVO vo = BeanUtil.toBean(d, BaseDictRespVO.class);
            list.add(vo);
        });
        return list;
    }
}
