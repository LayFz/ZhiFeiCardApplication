package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.Organize;
import com.zfkj.demo.vo.respvo.organize.OrganizationVo;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/26 16:58
 */
public interface OrganizationService {
    /**
     * 添加修改组织
     * @param organize
     * @return
     */
    Boolean addOrSaveHeadOrganization(Organize organize);

    /**
     * 删除子或父节点组织
     * @param id
     * @return
     */
    Boolean delOrganization(int id);

    /**
     * 查询对应公司的组织结构信息
     * @return
     */
    List<OrganizationVo> getOrganization();
}
