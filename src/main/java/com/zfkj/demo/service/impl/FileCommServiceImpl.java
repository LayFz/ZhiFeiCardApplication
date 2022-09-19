package com.zfkj.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrPool;
import com.zfkj.demo.common.config.filehandel.FileUploadConfigPropertity;
import com.zfkj.demo.common.utils.FileTempPath;
import com.zfkj.demo.dao.entity.BaseAttachment;
import com.zfkj.demo.dao.repository.BaseAttachmentRepository;
import com.zfkj.demo.service.FileCommService;
import com.zfkj.demo.vo.respvo.file.FileUploadResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author lijunlin
 * @Description
 * @Date 2022/7/11 10:45
 **/
@Service
public class FileCommServiceImpl implements FileCommService {

    @Autowired
    private FileTempPath fileTempPath;

    @Autowired
    private BaseAttachmentRepository attachmentRepository;

    @Autowired
    private FileUploadConfigPropertity fileUploadConfigPropertity;

    @Override
    public FileUploadResVO save(MultipartFile file, String module) {
        long size = file.getSize();
        String originalFilename = file.getOriginalFilename();
        File save = fileTempPath.saveCommFile(file, module);
        String filePath = save.getAbsolutePath();
        String downloadUrl = fileUploadConfigPropertity.getUploadHead() + StrPool.SLASH + filePath
                .replace(fileTempPath.path(), "")
                .replace(StrPool.BACKSLASH, StrPool.SLASH);

        BaseAttachment baseAttachment = new BaseAttachment();
        baseAttachment.setName(save.getName());
        baseAttachment.setRealName(originalFilename);
        baseAttachment.setPath(save.getAbsolutePath());
        baseAttachment.setUrl(downloadUrl);
        baseAttachment.setSize(size);
        baseAttachment.setSuff(FileUtil.getSuffix(save));
        attachmentRepository.save(baseAttachment);
        FileUploadResVO fileUploadResVO = BeanUtil.toBean(baseAttachment, FileUploadResVO.class);
        return fileUploadResVO;
    }
}
