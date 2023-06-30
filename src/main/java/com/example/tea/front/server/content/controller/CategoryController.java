package com.example.tea.front.server.content.controller;

import com.example.tea.front.server.common.web.JsonResult;
import com.example.tea.front.server.content.pojo.vo.FirstCategoryListItemVO;
import com.example.tea.front.server.content.service.ICategoryService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 18:56
 */
@Slf4j
@Validated
@RestController
@Api(tags = "1.2 分类管理")
@RequestMapping("/content/categories")
public class CategoryController {
    @Resource
    private ICategoryService service;
    
    @ApiOperation("查询一级分类列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("/list/first")
    public JsonResult getFirstCategoryList() {
        log.debug("开始处理【查询一级分类列表】请求，无参数");
        List<FirstCategoryListItemVO> firstCategoryList = service.getFirstCategoryList();
        return JsonResult.ok(firstCategoryList);
    }
}
