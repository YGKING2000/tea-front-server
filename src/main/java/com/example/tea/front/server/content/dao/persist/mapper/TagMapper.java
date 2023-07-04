package com.example.tea.front.server.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tea.front.server.content.pojo.entity.Tag;
import com.example.tea.front.server.content.pojo.vo.TagListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理标签数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 查询标签列表
     *
     * @return 标签列表
     */
    List<TagListItemVO> list();

}