package com.example.tea.front.server.account.dao.persist.repository;

import com.example.tea.front.server.account.pojo.entity.UserRole;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/25 09:14
 */
public interface IUserRoleRepository {

    /**
     * 批量插入用户与角色的关联数据
     *
     * @param userRoleList 若干个用户与角色的关联数据的集合
     * @return 受影响的行数
     */
    int insertBatch(List<UserRole> userRoleList);

    /**
     * 根据用户id删除用户与角色的关联数据
     *
     * @param adminId 用户id
     * @return 受影响的行数
     */
    int deleteByAdminId(Long adminId);
}
