package com.example.tea.front.server.account.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tea.front.server.account.dao.persist.mapper.UserRoleMapper;
import com.example.tea.front.server.account.dao.persist.repository.IUserRoleRepository;
import com.example.tea.front.server.account.pojo.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/25 09:15
 */
@Slf4j
@Repository
public class UserRoleRepositoryImpl implements IUserRoleRepository {

    @Resource
    private UserRoleMapper userRoleMapper;

    public UserRoleRepositoryImpl() {
        log.debug("创建存储库对象: AdminRoleRepositoryImpl");
    }

    @Override
    public int insertBatch(List<UserRole> userRoleList) {
        log.debug("开始执行【批量插入用户与角色的关联数据】的数据访问，参数: {}", userRoleList);
        return userRoleMapper.insertBatch(userRoleList);
    }

    @Override
    public int deleteByAdminId(Long frontId) {
        log.debug("开始执行【根据用户ID删除用户与角色的关联数据】的数据访问，参数: {}", frontId);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", frontId);
        return userRoleMapper.delete(queryWrapper);
    }
}
