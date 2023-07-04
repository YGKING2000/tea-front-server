package com.example.tea.front.server.account.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 添加用户的参数类
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @date 2023/06/13 17:52
 */
@Data
public class UserAddNewParam implements Serializable {

    /**
     * 用户名
     */
    @NotEmpty(message = "添加用户失败，请填写用户名!")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{4,16}$", message = "添加用户失败，用户名必须是4~16个由字母、数字、下划线、减号组成的字符，且第1个字符必须是字母!")
    @ApiModelProperty(value = "用户名", required = true, example = "ikun")
    private String username;

    /**
     * 密码（原文）
     */
    @NotEmpty(message = "添加用户失败，请填写密码!")
    @Pattern(regexp = "^.{4,16}$", message = "添加用户失败，密码必须是4~16字符!")
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", example = "蔡徐坤")
    private String nickname;

    /**
     * 头像URL
     */
    @ApiModelProperty(value = "头像地址", dataType = "https://img2.baidu.com/it/u=4244269751,4000533845&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500")
    private String avatar;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号", example = "13266887799")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱", example = "1326688779@qq.com")
    private String email;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", example = "基尼太美")
    private String description;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @NotNull(message = "添加用户失败，请选择用户的启用状态!")
    @Min(value = 0, message = "添加用户失败，启用状态的值必须是0或1!")
    @Max(value = 1, message = "添加用户失败，启用状态的值必须是0或1!")
    @ApiModelProperty(value = "是否启用，1=启用，0=未启用", required = true, example = "1")
    private Integer enable;

    /**
     * 用户的角色ID的数组
     */
    @NotNull(message = "添加用户失败，请至少选择1种角色!")
    @Size(min = 1, message = "添加用户失败，请至少选择1种角色!")
    @ApiModelProperty(value = "用户的角色ID的数组", required = true)
    private Long[] roleIds;

}
