package com.example.tea.front.server.account.dao.persist.repository.impl;

import com.example.tea.front.server.account.dao.persist.mapper.RoleMapper;
import com.example.tea.front.server.account.dao.persist.repository.IRoleRepository;
import com.example.tea.front.server.account.pojo.vo.RoleListItemVO;
import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.common.util.PageInfoToPageDataConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/25 09:17
 */
@Slf4j
@Repository
public class RoleRepositoryImpl implements IRoleRepository {

    @Resource
    private RoleMapper roleMapper;

    public RoleRepositoryImpl() {
        log.debug("创建存储库对象: RoleRepositoryImpl");
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询用户列表】的数据访问，页码: {}，每页记录数: {}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<RoleListItemVO> list = roleMapper.list();
        PageInfo<RoleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
