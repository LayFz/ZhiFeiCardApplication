package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfkj.demo.dao.entity.Role;
import com.zfkj.demo.dao.entity.TextBoard;
import com.zfkj.demo.dao.repository.TextBoardRepository;
import com.zfkj.demo.service.TextBoardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/19 12:29
 */
@Service
public class TextBoardServiceImpl implements TextBoardService {
    @Autowired
    TextBoardRepository textBoardRepository;
    @Override
    public Boolean saveboard(TextBoard reqVo) {
        if (reqVo.getContent()!=null&&reqVo.getSex()!=null&&reqVo.getNickname()!=null&&reqVo.getIspublic()!=null){
            TextBoard textBoard = TextBoard.builder().build();
            BeanUtils.copyProperties(reqVo,textBoard);
            textBoardRepository.saveOrUpdate(textBoard);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }

    }

    @Override
    public List<TextBoard> gethomeBoard() {
        /**
         * 公开条件下的最新信息排序（前三条数据）
         */
        LambdaQueryWrapper<TextBoard> textBoardLambdaQueryWrapper = new LambdaQueryWrapper<TextBoard>()
                .eq(TextBoard::getIspublic,"YES")
                .orderBy(true,false,TextBoard::getCreateTime);
        List<TextBoard> boardList = textBoardRepository.list(textBoardLambdaQueryWrapper);
        List<TextBoard> resultList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            resultList.add(boardList.get(i));
        }
        return resultList;
    }
}
