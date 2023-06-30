package com.example.tea.front.server.common.web;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/14 17:46
 */
public enum ServiceCode {

    /**
     * 操作成功
     */
    OK(20000),
    /**
     * 请求参数错误
     */
    ERROR_BAD_REQUEST(40000),
    /**
     * 错误: 未授权
     */
    ERROR_UNAUTHORIZED(40100),
    /**
     * 错误: 未授权，因为被禁用
     */
    ERROR_UNAUTHORIZED_DISABLED(40101),
    /**
     * 错误: 禁止访问，用于权限
     */
    ERROR_FORBIDDEN(40300),
    /**
     * 错误: 数据不存在
     */
    ERROR_NOT_FOUND(40400),
    /**
     * 错误: 数据冲突
     */
    ERROR_CONFLICT(40900),
    /**
     * 错误: 未知的插入数据失败
     */
    ERROR_INSERT(50000),
    /**
     * 错误: 未知的删除数据失败
     */
    ERROR_DELETE(50100),
    /**
     * 错误: 未知的修改数据失败
     */
    ERROR_UPDATE(50200),
    /**
     * JWT已过期
     */
    ERROR_JWT_EXPIRED(60000),
    /**
     * JWT验证签名失败，可能是伪造了签名
     */
    ERROR_JWT_SIGNATURE(60100),
    /**
     * JWT格式错误，可能是伪造了签名
     */
    ERROR_JWT_MALFORMED(60200),
    /**
     * 错误: 上传的文件为空（没有选择有效的文件）
     */
    ERROR_UPLOAD_EMPTY(90000),
    /**
     * 错误: 上传的文件类型有误
     */
    ERROR_UPLOAD_INVALID_TYPE(90100),
    /**
     * 错误: 上传的文件超出限制
     */
    ERROR_UPLOAD_EXCEED_MAX_SIZE(90200),
    /**
     * 未知错误
     */
    ERROR_UNKNOWN(99999);

    private final Integer value;

    ServiceCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
