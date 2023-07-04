package com.example.tea.front.server.account.dao.persist.mapper;

import com.example.tea.front.server.account.pojo.vo.RoleListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/25 09:10
 */
@Repository
public interface RoleMapper {

    /**
     * 查询角色列表
     *
     * @return 角色列表
     */
    List<RoleListItemVO> list();

}
