package com.cun.plus.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.cun.plus.mapper")
public class MybatisPlusConfig {

}
