package com.example.tea.front.server.content.controller;

import com.example.tea.front.server.common.security.CurrentPrincipal;
import com.example.tea.front.server.common.web.JsonResult;
import com.example.tea.front.server.content.pojo.param.CommentAddNewParam;
import com.example.tea.front.server.content.service.ICommentService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/02 16:45
 */
@Slf4j
@Validated
@RestController
@Api(tags = "1.4 评论模块")
@RequestMapping("/content/comments")
public class CommentController {
    @Resource
    private ICommentService service;
    
    public CommentController() {
        log.debug("创建控制器对象: CommentController");
    }
    
    @ApiOperation("发表评论")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(
            @ApiIgnore @AuthenticationPrincipal CurrentPrincipal principal,
            @ApiIgnore HttpServletRequest request,
            @Validated CommentAddNewParam commentAddNewParam) {
        log.debug("开始处理【发表评论】请求，参数为: {}", commentAddNewParam);
        String remoteAddr = request.getRemoteAddr();
        service.addNew(principal, remoteAddr, commentAddNewParam);
        return JsonResult.ok();
    }
    
    @ApiOperation("根据文章ID查询评论")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章ID", example = "7", dataType = "Long")
    })
    @GetMapping("/{articleId:[0-9]+}/list")
    public JsonResult list(@PathVariable @Range(min = 1, message = "请提交合法的文章ID值!") Long articleId) {
        log.debug("开始处理【根据文章ID查询评论】请求，参数为: {}", articleId);
        return JsonResult.ok(service.listByArticleId(articleId));
    }
}
