package com.example.tea.front.server.common.web;

import com.example.tea.front.server.common.exception.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @className JsonResult
 * @date 2023/06/15 10:59
 */
@Data
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult {
    /**
     * 响应的业务状态码值
     */
    @ApiModelProperty(value = "响应的业务状态码值")
    private Integer state;
    /**
     * 操作失败时的描述文本
     */
    @ApiModelProperty(value = "操作失败时的描述文本")
    private String message;
    /**
     * 操作成功时的响应数据
     */
    @ApiModelProperty(value = "操作成功时的响应数据")
    private Object data;

    public static JsonResult ok() {
        return ok(null);
    }

    public static JsonResult ok(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(ServiceCode.OK.getValue());
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult fail(ServiceCode serviceCode, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(serviceCode.getValue());
        jsonResult.setMessage(message);
        return jsonResult;
    }
}
