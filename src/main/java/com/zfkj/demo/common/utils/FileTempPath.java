package com.zfkj.demo.common.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.OsInfo;
import com.zfkj.demo.common.config.filehandel.FileUploadConfigPropertity;
import com.zfkj.demo.common.constant.Constants;
import com.zfkj.demo.common.exception.Exceptions;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

/**
 * 文件信息
 *
 * @author lijunlin
 * @date 2022年1月25日
 */
@Configuration
@AllArgsConstructor
public class FileTempPath {

    private final FileUploadConfigPropertity fileUploadConfigPropertity;

    /**
     * 获取module存储路径
     *
     * @param module 模块名称
     * @return 根路径/module/当天日期/
     */
    public String uploadPath(String module) {
        return path() + File.separator
                + module + File.separator
                + DateUtil.format(LocalDateTime.now(), DatePattern.PURE_DATE_PATTERN)
                + File.separator;
    }

    /**
     * 下载路径配置
     *
     * @param module 模块路径
     * @return 根路径/download/module
     */
    public String downloadPath(String module) {
        return path() + File.separator + Constants.STR_DOWNLOAD + File.separator + module + File.separator;
    }

    /**
     * 下载路径配置
     *
     * @return 根路径/download/
     */
    public String downloadPath() {
        return path() + File.separator + Constants.STR_DOWNLOAD + File.separator;
    }
    /**
     * 图片路径规则
     *
     * @return 根路径/upload/img/
     */
    public String imgSrcPath() {
        return path() + File.separator + Constants.STR_UPLOAD_IMG + File.separator;
    }

    /**
     * 视频路径规则
     *
     * @return 根路径/upload/video/
     */
    public String videoSrcPath() {
        return path() + File.separator + Constants.STR_UPLOAD_VIDEO + File.separator;
    }

    /**
     * 上传附件路径规则
     * @return 根路径/upload/file/
     */
    public String filePath() {
        return path() + File.separator + Constants.STR_UPLOAD_FILE + File.separator;
    }

    /**
     * 获取根路径
     *
     * @return 根路径
     */
    public String path() {
        OsInfo osInfo = new OsInfo();
        return osInfo.isWindows() ? fileUploadConfigPropertity.getWindows() : fileUploadConfigPropertity.getLinux();
    }

    /**
     * 文件上传 保存
     *
     * @param file
     * @param module
     * @param suff
     * @return
     */
    @SneakyThrows
    public File saveFile(MultipartFile file, String module, String[] suff) {
        if (file == null || StrUtil.isBlank(file.getOriginalFilename())) {
            return null;
        }
        String suffix = StrPool.DOT + FileUtil.getSuffix(file.getOriginalFilename());
        // 文件后缀校验
        AssertUtils.isTrue(!StrUtil.equalsAnyIgnoreCase(suffix, suff), Exceptions.FileUpload.UPLOAD_SUFFIX_ERROR);
        // 文件转存
        // 模块路径/雪花算法ID_上传文件名称
        String fileName = IdUtil.getSnowflakeNextIdStr() + "_" + file.getOriginalFilename();
        File files = FileUtil.file(uploadPath(module) + fileName);
        FileUtil.mkParentDirs(files);

        file.transferTo(files);
        return files;
    }


    /**
     * 文件上传 备查信息
     *
     * @param file http MultipartFile 文件流
     * @param suff 后缀 包含此类型的后缀不允许上传 如：.exe .sh
     * @return
     */
    @SneakyThrows
    public File saveInformationFile(MultipartFile file, String[] suff) {
        return saveFile(file, "information", suff);
    }

    @SneakyThrows
    public File saveCommFile(MultipartFile file, String module) {
        return saveFile(file, module, null);
    }

    /**
     * 下载路径处理 前提是放开uri
     * @param file
     * @return
     */
    public String downloadUrl(File file) {
        String filePath = file.getAbsolutePath();
        String downloadUrl = filePath
                .replace(path(), "")
                .replace(StrPool.BACKSLASH, StrPool.SLASH);
        return CharPool.SLASH + downloadUrl;
    }

    public String downloadUrl(String path) {
        return downloadUrl(FileUtil.file(path));
    }

}