package com.example.coursedesign.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.coursedesign.mapper")
public class MybatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptoro() {
            PaginationInterceptor paginationInterceptor = new PaginationInterceptor ();
            return paginationInterceptor;
        }
}
