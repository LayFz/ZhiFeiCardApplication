package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.TextBoard;
import com.zfkj.demo.dao.mapper.TextBoardMapper;
import org.springframework.stereotype.Repository;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/19 12:26
 */
@Repository
public class TextBoardRepository extends ServiceImpl<TextBoardMapper, TextBoard> {
}
