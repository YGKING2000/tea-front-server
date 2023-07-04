package com.example.tea.front.server.account.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tea.front.server.account.pojo.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/25 09:11
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 批量插入用户与角色的关联数据
     *
     * @param userRoleList 若干个用户与角色的关联数据的集合
     * @return 受影响的行数
     */
    int insertBatch(List<UserRole> userRoleList);


}
