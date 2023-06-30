package com.example.tea.front.server.content.controller;

import com.example.tea.front.server.common.vo.PageData;
import com.example.tea.front.server.common.web.JsonResult;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import com.example.tea.front.server.content.service.IArticleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 17:26
 */
@Slf4j
@Validated
@RestController
@Api(tags = "1.1 文章模块")
@RequestMapping("/content/articles")
public class ArticleController {
    @Resource
    private IArticleService service;

    public ArticleController() {
        log.debug("创建处理器对象: ArticleController");
    }

    @ApiOperationSupport(order = 400)
    @ApiOperation("根据类别ID查询文章列表")
    @GetMapping("/{categoryId:[0-9]+}/list")
    @PreAuthorize("hasAnyAuthority('/content/article/read')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "类别ID", required = true, defaultValue = "1", dataType = "Long"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "queryType", value = "查询类型，查询所有数据时值为all", defaultValue = "all")
    })
    public JsonResult listByCategoryId(
            @PathVariable Long categoryId,
            Integer pageNum, String queryType) {
        log.debug("开始处理【根据类别ID({})查询文章列表】请求，页码: {}", categoryId, pageNum);
        PageData<ArticleListItemVO> pageData;
        if ("all".equals(queryType)) {
            pageData = service.listByCategoryId(categoryId, 1, Integer.MAX_VALUE);
        } else {
            pageNum = pageNum == null || pageNum <= 0 ? 1 : pageNum;
            pageData = service.listByCategoryId(categoryId, pageNum);
        }
        return JsonResult.ok(pageData);
    }
}
