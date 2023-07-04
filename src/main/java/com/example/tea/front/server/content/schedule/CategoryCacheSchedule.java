package com.example.tea.front.server.content.schedule;

import com.example.tea.front.server.content.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/01 15:21
 */
@Slf4j
@Component
public class CategoryCacheSchedule {
    @Resource
    private ICategoryService service;
    
    public CategoryCacheSchedule() {
        log.info("创建计划任务类对象: CategoryCacheSchedule");
    }

    // fixedRate: 执行频率，根据上一次执行的开始时间来计算下一次的执行时间，取值以毫秒为单位
    // fixedDelay: 执行间隔，根据上一次执行的结束时间来计算下一次的执行时间，取值以毫秒为单位
    // @Scheduled(fixedRate = 3 * 1000)
    // public void rebuildCacheList() {
    //     log.debug("开始执行【重建缓存中的分类列表】的计划任务");
    //     service.rebuildCacheList();
    // }
}
