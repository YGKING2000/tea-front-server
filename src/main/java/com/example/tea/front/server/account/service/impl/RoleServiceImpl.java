package com.example.tea.front.server.account.service.impl;

import com.example.tea.front.server.account.dao.persist.repository.IRoleRepository;
import com.example.tea.front.server.account.pojo.vo.RoleListItemVO;
import com.example.tea.front.server.account.service.IRoleService;
import com.example.tea.front.server.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 处理角色数据的业务实现类
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @date 2023/06/13 17:52
 */
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;

    @Resource
    private IRoleRepository roleRepository;

    public RoleServiceImpl() {
        log.debug("创建业务类对象: RoleServiceImpl");
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum) {
        log.debug("开始处理【查询角色列表】的业务，页码: {}", pageNum);
        return roleRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【查询角色列表】的业务，页码: {}，每页记录数: {}", pageNum, pageSize);
        return roleRepository.list(pageNum, pageSize);
    }
}
