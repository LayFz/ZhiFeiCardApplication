package com.zfkj.demo.service;

import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.dao.entity.Article;
import com.zfkj.demo.dao.entity.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 获取企业列表
     */
    List<Company> getCompanyList();

    /**
     * 根据企业编号，管理员名字，电话,状态进行查询
     */
    List<Company> selectCompanyBy(String reqVo, OpenCloseEnum openCloseEnum);

    /**
     * 添加,编辑企业
     */
    Boolean addOrUpdateCompany(Company reqVo);
    /**
     * 启用/停用企业
     * @param id
     * @param status
     */
    void openCloseCompany(Integer id, OpenCloseEnum status);
    /**
     * 删除企业
     */
    Boolean delCompany(List<Integer> ids);





}
