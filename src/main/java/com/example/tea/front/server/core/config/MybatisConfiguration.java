package com.example.tea.front.server.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 19:10
 */
@Configuration
@MapperScan({"com.example.tea.front.server.content.dao.persist.mapper"})
public class MybatisConfiguration {
}
