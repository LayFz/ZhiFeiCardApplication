package com.zfkj.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zfkj.demo.dao.entity.Auth;
import com.zfkj.demo.dao.repository.AuthRepository;
import com.zfkj.demo.service.IAuthService;
import com.zfkj.demo.service.IRoleService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.auth.AuthAddUpdateReqVo;
import com.zfkj.demo.vo.respvo.auth.*;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 菜单资源表 服务实现类
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthRepository authRepository;


    @Override
    public String getAuthByUrl(String url) {
        String urlString = "";
        if (StringUtils.isEmpty(url)) {
            return urlString;
        }
        Auth auth = authRepository.getOne(Wrappers.<Auth>lambdaQuery().eq(Auth::getUrl, url).last("limit 1"));
        if (Objects.nonNull(auth)) {
            urlString = auth.getId() + "_" + auth.getUrl();
        }
        return urlString;
    }

    @Override
    public AuthTreeTopRespVo getAuthTree(String clientType) {
        //根据系统标识查询所有权限资源集合
        List<Auth> auths = authRepository.list(Wrappers.<Auth>lambdaQuery()
                .eq(Auth::getForSystem,clientType).orderByAsc(Auth::getSort)
        );
        //组装树形结构
        AuthTreeTopRespVo authTreeTopRespVo = new AuthTreeTopRespVo();
        auths.stream().forEach(n->{
            if(Constants.ZERO.equals(n.getLevel().toString())){
                AuthTreeRespVo authTreeRespVo = new AuthTreeRespVo();
                BeanUtils.copyProperties(n,authTreeRespVo);
                authTreeTopRespVo.getChild().add(authTreeRespVo);
                getChild(authTreeRespVo,auths);
            }
        });
        return authTreeTopRespVo;
    }

    @Transactional
    @Override
    public Boolean addOrUpdateAuth(AuthAddUpdateReqVo reqVo) {
        //有id就是修改，无id就是新增
        Auth auth = Auth.builder().build();
        BeanUtils.copyProperties(reqVo,auth);
        authRepository.saveOrUpdate(auth);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Boolean delAuthByIds(List<Integer> ids) {
        authRepository.removeByIds(ids);
        return Boolean.TRUE;
    }


    private void getChild(AuthTreeRespVo authTreeRespVo,List<Auth> auths){
        auths.stream().forEach(n->{
            if(authTreeRespVo.getId().equals(n.getParentId())){
                AuthTreeRespVo auth = new AuthTreeRespVo();
                BeanUtils.copyProperties(n,auth);
                authTreeRespVo.getChild().add(auth);
                getChild(auth,auths);
            }
        });
    }

}
