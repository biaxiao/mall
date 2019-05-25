package com.x.mall.config;

/**
 * 用于配置需要动态生成的mapper接口的路径
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类
 */
@Configuration
@MapperScan({"com.x.mall.mbg.mapper","com.x.mall.dao"})
public class MyBatisConfig {
    /*static {
        System.out.println("--ssssssssssss---");
    }*/
}
