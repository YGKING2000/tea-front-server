package com.example.tea.front.server.account.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tea.front.server.account.dao.persist.mapper.UserMapper;
import com.example.tea.front.server.account.dao.persist.repository.IUserRepository;
import com.example.tea.front.server.account.pojo.entity.User;
import com.example.tea.front.server.account.pojo.vo.UserListItemVO;
import com.example.tea.front.server.account.pojo.vo.UserLoginInfoVO;
import com.example.tea.front.server.account.pojo.vo.UserStandardVO;
import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.common.util.PageInfoToPageDataConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/21 09:40
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Resource
    private UserMapper userMapper;

    public UserRepositoryImpl() {
        log.debug("创建存储库对象: UserRepositoryImpl");
    }

    @Override
    public int insert(User user) {
        log.debug("开始执行【插入用户】的数据访问，参数: {}", user);
        return userMapper.insert(user);
    }

    @Override
    public int insertBatch(List<User> userList) {
        log.debug("开始执行【批量插入用户】的数据访问，参数: {}", userList);
        return userMapper.insertBatch(userList);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("开始执行【根据ID删除用户】的数据访问，参数: {}", id);
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("开始执行【批量删除用户】的数据访问，参数: {}", idList);
        return userMapper.deleteBatchIds(idList);
    }

    @Override
    public int updateById(User user) {
        log.debug("开始执行【更新用户】的数据访问，参数: {}", user);
        return userMapper.updateById(user);
    }

    @Override
    public int count() {
        log.debug("开始执行【统计用户的数量】的数据访问，参数: 无");
        return userMapper.selectCount(null);
    }

    @Override
    public int countByUsername(String username) {
        log.debug("开始执行【根据用户名统计用户的数量】的数据访问，参数: {}", username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByPhone(String phone) {
        log.debug("开始执行【根据手机号码统计用户的数量】的数据访问，参数: {}", phone);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByEmail(String email) {
        log.debug("开始执行【根据电子邮箱统计用户的数量】的数据访问，参数: {}", email);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public UserStandardVO getStandardById(Long id) {
        log.debug("开始执行【根据ID查询用户详情】的数据访问，参数: {}", id);
        return userMapper.getStandardById(id);
    }

    @Override
    public UserLoginInfoVO getLoginInfoByUsername(String username) {
        log.debug("开始执行【根据用户名查询用户登录信息】的数据访问，参数: {}", username);
        return userMapper.getLoginInfoByUsername(username);
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询用户列表】的数据访问，页码: {}，每页记录数: {}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<UserListItemVO> list = userMapper.list();
        PageInfo<UserListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
