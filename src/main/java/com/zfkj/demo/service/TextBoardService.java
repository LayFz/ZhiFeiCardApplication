package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.TextBoard;

import java.util.List;
/**
 * <前端类><留言板类/>
 */

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/19 12:29
 */
public interface TextBoardService {
    /**
     * 保存用户留言板
     * @param reqVo
     * @return
     */
    Boolean saveboard(TextBoard reqVo);

    List<TextBoard> gethomeBoard();
}
