package com.ithao.vueblog.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithao.vueblog.common.Result;

import com.ithao.vueblog.entity.Blog;
import com.ithao.vueblog.service.BlogService;
import com.ithao.vueblog.util.ShiroUtil;
import org.springframework.util.Assert;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ithao
 * @since 2020-06-09
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 5);
        IPage pageDate = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageDate);
    }
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name= "id") Long  id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已删除");

        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result list(@Validated @RequestBody Blog blog){
        Blog temp=null;
        if(blog.getId()!=null){
            temp=blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue()== ShiroUtil.getProfile().getId().longValue(),"没有权限编辑");
        }else{

            temp=new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtils.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }


}
