package com.example.tea.front.server.test;

import com.example.tea.front.server.common.security.CurrentPrincipal;
import com.example.tea.front.server.common.web.JsonResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 16:40
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public JsonResult test(@AuthenticationPrincipal CurrentPrincipal currentPrincipal) {
        return JsonResult.ok(currentPrincipal);
    }
}
