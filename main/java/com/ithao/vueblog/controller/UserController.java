package com.ithao.vueblog.controller;


import com.ithao.vueblog.common.Result;
import com.ithao.vueblog.entity.User;
import com.ithao.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ithao
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result findById(){
        Result result = new Result();
        User user = userService.getById(1L);
        return result.succ(user);
    }
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.succ(user);
    }


}
