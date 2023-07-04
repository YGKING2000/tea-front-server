package com.example.tea.front.server.content.dao.persist.repository;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.pojo.vo.TagListItemVO;

/**
 * 处理标签数据的存储库接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface ITagRepository {

    /**
     * 查询标签列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 标签列表
     */
    PageData<TagListItemVO> list(Integer pageNum, Integer pageSize);

}
