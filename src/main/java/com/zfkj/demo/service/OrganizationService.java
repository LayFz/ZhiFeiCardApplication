package com.zfkj.demo.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zfkj.demo.dao.entity.Organize;

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
}
