package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.Constants;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.repository.BaseAttachmentRepository;
import com.zfkj.demo.common.utils.FileTempPath;
import com.zfkj.demo.service.FileCommService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.respvo.file.FileUploadResVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * 文件接口公共接口
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-17
 */
@Api(tags = "后台管理系统-基础数据-文件上传")
@RestController
@RequestMapping("/api/manager/file")
public class FileCommController {

    @Autowired
    private FileCommService fileCommService;

    @PostMapping("/uploadImg")
    @ApiOperation(value = "单文件上传-图片", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    public Result<FileUploadResVO> uploadImg(MultipartFile file) {
        return Result.success(fileCommService.save(file, Constants.STR_UPLOAD_IMG));
    }

    @PostMapping("/uploadFile")
    @ApiOperation(value = "单文件上传-附件", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    public Result<FileUploadResVO> uploadFile(MultipartFile file) {
        return Result.success(fileCommService.save(file, Constants.STR_UPLOAD_FILE));
    }

}
