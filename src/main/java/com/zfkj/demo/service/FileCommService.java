package com.zfkj.demo.service;

import com.zfkj.demo.vo.respvo.file.FileUploadResVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author lijunlin
 * @Description
 * @Date 2022/7/11 10:44
 **/
public interface FileCommService {

    /**
     * 保存文件信息到数据库
     * @param file
     * @param module
     * @return
     */
    FileUploadResVO save(MultipartFile file, String module);

}
