package com.truing.onebox.admin.controller;
import com.truing.onebox.common.utils.AjaxJson;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    /**
     * 返回根目录文件列表
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public AjaxJson<?> doLogin(String username, String password,HttpSession session) {
        String str = "zhangsan";
        String pwd = "12345";
        if (str.equals(username) && pwd.equals(password)){
            return AjaxJson.getSuccess();
        }
        return AjaxJson.getError("登录失败, 账号或密码错误");
    }



    @PostMapping("/logout")
    public AjaxJson<?> doLogout(){
        return AjaxJson.getSuccess();
    }
}
