package com.example.tea.front.server.account.dao.persist.repository;

import com.example.tea.front.server.account.pojo.vo.RoleListItemVO;
import com.example.tea.front.server.common.pojo.vo.PageData;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/25 09:12
 */
public interface IRoleRepository {
    /**
     * 查询角色列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 角色列表
     */
    PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize);
}
