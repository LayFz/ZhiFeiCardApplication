package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.TextBoard;

import java.util.HashMap;
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

    /**
     * 用户获取留言板内容
     * @return
     */
    List<TextBoard> gethomeBoard();

    /**
     * 获取留言板统计信息
     * @return
     */
    HashMap<String,String> getBoardDate();

    /**
     * 恢复留言板
     * @param reqVo
     * @return
     */
    Boolean responseBoard(TextBoard reqVo);

    /**
     * 留言板删除
     * @param reqVo
     * @return
     */
    Boolean delBoard(List<Integer> reqVo);


    /**
     * 查询各个留言板的内容
     * @return
     */
    List<TextBoard> selectNoresponse();

    List<TextBoard> selectResponse();

    List<TextBoard> slectPublic();

    List<TextBoard> selctNopublic();
}
