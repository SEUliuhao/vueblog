package com.ithao.vueblog.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ithao.vueblog.common.Result;
import com.ithao.vueblog.common.dto.LoginDto;
import com.ithao.vueblog.entity.User;
import com.ithao.vueblog.service.UserService;
import com.ithao.vueblog.util.JwtUtils;
import org.springframework.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户名不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){//
            log.info(user.getPassword());
            log.info(SecureUtil.md5(loginDto.getPassword()));
            return Result.fail("密码不正确！");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return Result.succ(MapUtil.builder()
                            .put("id",user.getId())
                            .put("username",user.getUsername())
                            .put("avatar",user.getAvatar())
                            .put("email",user.getEmail())
                            .map());
    }
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
