package com.ithao.vueblog.service.impl;

import com.ithao.vueblog.entity.User;
import com.ithao.vueblog.mapper.UserMapper;
import com.ithao.vueblog.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
