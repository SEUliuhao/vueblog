package com.ithao.vueblog.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.ithao.vueblog.mapper")
public class MybatisPlusConfig {
    @Bean
    //分页的插件
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
