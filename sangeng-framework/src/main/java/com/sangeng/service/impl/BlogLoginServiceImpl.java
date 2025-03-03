package com.sangeng.service.impl;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.LoginUser;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.vo.BlogUserLoginVo;
import com.sangeng.domain.vo.UserInfoVo;
import com.sangeng.service.BlogLoginService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.JwtUtil;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        //通过用户id生成jwt
             LoginUser loginUser= (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getUser().getId();
        //将jwt存入redis，并返回
        String jwt = JwtUtil.createJWT(id.toString());
        redisCache.setCacheObject("bloglogin:"+id,loginUser);

        //将token和userInfo返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt,userInfoVo);

        return ResponseResult.okResult(blogUserLoginVo);
    }
}
