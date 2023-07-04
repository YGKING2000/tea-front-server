package com.example.tea.front.server.common.util;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.github.pagehelper.PageInfo;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @className PageInfoToPageDataConverter
 * @date 2023/06/17 11:56
 */
public class PageInfoToPageDataConverter {
    public static <T> PageData<T> convert(PageInfo<T> pageInfo) {
        PageData<T> pageData = new PageData<>();
        pageData.setPageSize(pageInfo.getPageSize())
                .setTotal(pageInfo.getTotal())
                .setCurrentPage(pageInfo.getPageNum())
                .setMaxPage(pageInfo.getPages())
                .setList(pageInfo.getList());
        return pageData;
    }
}
