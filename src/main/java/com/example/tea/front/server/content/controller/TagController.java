package com.example.tea.front.server.content.controller;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.common.web.JsonResult;
import com.example.tea.front.server.content.pojo.vo.TagListItemVO;
import com.example.tea.front.server.content.service.ITagService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 处理标签相关请求的控制器类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/tags")
@Validated
@Api(tags = "1.2 标签模块")
public class TagController {

    @Resource
    private ITagService service;

    public TagController() {
        log.info("创建控制器对象: TagController");
    }

    @ApiOperation("查询标签列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查询类型，当需要查询全部数据时，此参数值应该是all")
    })
    @GetMapping("")
    public JsonResult list(Integer page, String queryType) {
        log.debug("开始处理【查询标签列表】请求，页码: {}", page);
        if (page == null) {
            page = 1;
        }
        Integer pageNum = page > 0 ? page : 1;
        PageData<TagListItemVO> pageData;
        if ("all".equals(queryType)) {
            pageData = service.list(1, Integer.MAX_VALUE);
        } else {
            pageData = service.list(pageNum);
        }
        return JsonResult.ok(pageData);
    }

}