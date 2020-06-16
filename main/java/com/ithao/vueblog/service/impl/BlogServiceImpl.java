package com.ithao.vueblog.service.impl;

import com.ithao.vueblog.entity.Blog;
import com.ithao.vueblog.mapper.BlogMapper;
import com.ithao.vueblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ithao
 * @since 2020-06-09
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
