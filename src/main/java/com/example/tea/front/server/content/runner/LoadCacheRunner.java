package com.example.tea.front.server.content.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/01 14:50
 */
@Slf4j
@Component
public class LoadCacheRunner implements ApplicationRunner {
    public LoadCacheRunner() {
        log.debug("创建运行器对象: LoadCacheRunner");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("开始缓存预热【加载缓存数据】");
    }
}
