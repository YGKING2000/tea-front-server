package com.example.tea.front.server.account.controller;

import com.example.tea.front.server.account.pojo.param.UserAddNewParam;
import com.example.tea.front.server.account.pojo.param.UserLoginInfoParam;
import com.example.tea.front.server.account.pojo.vo.UserListItemVO;
import com.example.tea.front.server.account.pojo.vo.UserLoginResultVO;
import com.example.tea.front.server.account.pojo.vo.UserStandardVO;
import com.example.tea.front.server.account.service.IUserService;
import com.example.tea.front.server.common.consts.HttpConsts;
import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.common.security.CurrentPrincipal;
import com.example.tea.front.server.common.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/21 10:28
 */
@Slf4j
@RestController
@RequestMapping("/account/users")
@Validated
@Api(tags = "1.1. 用户管理模块")
public class UserController implements HttpConsts {

    @Resource
    private IUserService userService;

    public UserController() {
        log.info("创建控制器对象: UserController");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ApiOperationSupport(order = 10)
    public JsonResult login(
            @Validated UserLoginInfoParam userLoginInfoParam,
            @ApiIgnore HttpServletRequest request) {
        log.debug("开始处理【用户登录】的请求，参数: {}", userLoginInfoParam);
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader(HEADER_USER_AGENT);
        UserLoginResultVO userLoginResultVO = userService.login(userLoginInfoParam, ip, userAgent);
        return JsonResult.ok(userLoginResultVO);
    }

    @PostMapping("/logout")
    @ApiOperation("用户退出登录")
    @ApiOperationSupport(order = 11)
    public JsonResult logout() {
        log.debug("开始处理【用户退出登录】的请求，无参数");
        SecurityContextHolder.clearContext();
        return JsonResult.ok();
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/account/user/add-new')")
    @ApiOperation("添加用户")
    @ApiOperationSupport(order = 100)
    public JsonResult addNew(@Valid UserAddNewParam userAddNewParam) {
        log.debug("开始处理【添加用户】的请求，参数: {}", userAddNewParam);
        userService.addNew(userAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/account/user/delete')")
    @ApiOperation("根据ID删除用户")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable Long id) {
        log.debug("开始处理【根据ID删除用户】的请求，参数: {}", id);
        userService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/enable")
    @PreAuthorize("hasAuthority('/account/user/update')")
    @ApiOperation("启用用户")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    public JsonResult setEnable(@PathVariable Long id) {
        log.debug("开始处理【启用用户】的请求，参数: {}", id);
        userService.setEnable(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/disable")
    @PreAuthorize("hasAuthority('/account/user/update')")
    @ApiOperation("禁用用户")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    public JsonResult setDisable(@PathVariable Long id) {
        log.debug("开始处理【禁用用户】的请求，参数: {}", id);
        userService.setDisable(id);
        return JsonResult.ok();
    }

    @ApiOperation("根据ID查询用户")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/account/user/read')")
    public JsonResult getStandardById(@PathVariable @Range(min = 1, message = "获取用户详情失败，请提交合法的ID值!") Long id) {
        log.debug("开始处理【根据ID查询用户】的请求，参数: {}", id);
        UserStandardVO tag = userService.getStandardById(id);
        return JsonResult.ok(tag);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/account/user/read')")
    @ApiOperation("查询用户列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", dataType = "long")
    })
    public JsonResult list(Integer page, @AuthenticationPrincipal @ApiIgnore CurrentPrincipal principal) {
        log.debug("开始处理【查询用户列表】的请求，页码: {}", page);
        log.debug("当事人: {}", principal);
        Integer pageNum = page == null ? 1 : page;
        PageData<UserListItemVO> pageData = userService.list(pageNum);
        return JsonResult.ok(pageData);
    }

}
