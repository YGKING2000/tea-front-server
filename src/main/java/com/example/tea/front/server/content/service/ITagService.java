package com.example.tea.front.server.content.service;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.pojo.vo.TagListItemVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理标签数据的业务接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Transactional
public interface ITagService {

    /**
     * 查询标签列表，将使用默认的每页记录数
     *
     * @param pageNum 页码
     * @return 查询标签列表
     */
    PageData<TagListItemVO> list(Integer pageNum);

    /**
     * 查询标签列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 标签列表
     */
    PageData<TagListItemVO> list(Integer pageNum, Integer pageSize);
}
