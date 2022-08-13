package com.truing.onebox.admin.controller;
import com.truing.onebox.common.utils.AjaxJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 17:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class LoginController {

    @GetMapping("/login")
    public AjaxJson<?> doLogin(String username, String password) {
        String str = "zhangsan";
        String pwd = "12345";
        if (str.equals(username) && pwd.equals(password)){
            return AjaxJson.getSuccess();
        }
        return AjaxJson.getError("登录失败, 账号或密码错误");
    }

}
