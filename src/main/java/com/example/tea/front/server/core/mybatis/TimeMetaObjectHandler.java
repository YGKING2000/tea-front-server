package com.example.tea.front.server.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @className TimeMetaObjectHandler
 * @date 2023/06/13 16:32
 */
@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {

    public static final String FIELD_CREATE_TIME = "gmtCreate";
    public static final String FIELD_UPDATE_TIME = "gmtModified";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_CREATE_TIME, now, metaObject);
        this.setFieldValByName(FIELD_UPDATE_TIME, now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_UPDATE_TIME, now, metaObject);
    }

}