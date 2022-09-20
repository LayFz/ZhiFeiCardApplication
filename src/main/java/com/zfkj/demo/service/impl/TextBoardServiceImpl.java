package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mysql.cj.util.StringUtils;
import com.zfkj.demo.dao.entity.Role;
import com.zfkj.demo.dao.entity.TextBoard;
import com.zfkj.demo.dao.repository.TextBoardRepository;
import com.zfkj.demo.service.TextBoardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public HashMap<String, String> getBoardDate() {
        //公开
        LambdaQueryWrapper<TextBoard> openlambda = new LambdaQueryWrapper<TextBoard>()
                .eq(TextBoard::getIspublic,"YES");
        //非公开
        LambdaQueryWrapper<TextBoard> closelambda = new LambdaQueryWrapper<TextBoard>()
                .eq(TextBoard::getIspublic,"NO");
        //未回复
        LambdaQueryWrapper<TextBoard> responlambda = new LambdaQueryWrapper<TextBoard>()
                .isNull(TextBoard::getRescontent)
                .isNotNull(TextBoard::getIspublic);
        //hashMap返回集
        HashMap<String,String> re = new HashMap<>();
        /**
         * 以此下推  公开、非公开、回复、总计、未回复
         */
        long num_open = textBoardRepository.count(openlambda);
        long num_close = textBoardRepository.count(closelambda);
        long num_response = textBoardRepository.count(responlambda);
        long num_count = num_close+num_open;
        long num_readyresponse = num_count-num_response;
        re.put("close",String.valueOf(num_close));
        re.put("open",String.valueOf(num_open));
        re.put("count",String.valueOf(num_count));
        re.put("noresponse",String.valueOf(num_response));
        re.put("readyresponse",String.valueOf(num_readyresponse));
        return re;
    }

    @Override
    public Boolean responseBoard(TextBoard reqVo) {
        LambdaQueryWrapper<TextBoard> boardLambdaQueryWrapper = new LambdaQueryWrapper<TextBoard>()
                .eq(TextBoard::getId,reqVo.getId());
        TextBoard textBoard = textBoardRepository.getOne(boardLambdaQueryWrapper);
        if (textBoard!=null){
            textBoard.setRescontent(reqVo.getRescontent());
            textBoardRepository.saveOrUpdate(textBoard);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean delBoard(List<Integer> reqVo) {
        textBoardRepository.removeByIds(reqVo);
        return Boolean.TRUE;
    }

    @Override
    public List<TextBoard> selectNoresponse() {
        LambdaQueryWrapper<TextBoard> re = new LambdaQueryWrapper<TextBoard>()
                .isNull(TextBoard::getRescontent)
                .isNotNull(TextBoard::getIspublic);
        return textBoardRepository.list(re);
    }

    @Override
    public List<TextBoard> selectResponse() {
        LambdaQueryWrapper<TextBoard> re = new LambdaQueryWrapper<TextBoard>()
                .eq(TextBoard::getIspublic,"YES")
                .isNotNull(TextBoard::getRescontent)
                .or()
                .eq(TextBoard::getIspublic,"NO")
                .isNotNull(TextBoard::getRescontent);
        return textBoardRepository.list(re);
    }

    @Override
    public List<TextBoard> slectPublic() {
        LambdaQueryWrapper<TextBoard> re = new LambdaQueryWrapper<TextBoard>()
                .eq(TextBoard::getIspublic,"YES");
        return textBoardRepository.list(re);
    }

    @Override
    public List<TextBoard> selctNopublic() {
        LambdaQueryWrapper<TextBoard> re = new LambdaQueryWrapper<TextBoard>()
                .eq(TextBoard::getIspublic,"NO");
        return textBoardRepository.list(re);
    }
}
