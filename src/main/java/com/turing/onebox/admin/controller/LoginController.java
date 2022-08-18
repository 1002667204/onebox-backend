package com.turing.onebox.admin.controller;
import com.turing.onebox.admin.service.UserService;
import com.turing.onebox.common.utils.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserService userService;

    /**
     * 返回根目录文件列表
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public AjaxJson<?> doLogin(String username, String password,HttpSession session) {
        String str = "admin";
        String pwd = "12345";
//        if (str.equals(username) && pwd.equals(password)){
//            return AjaxJson.getSuccess();
//        }
//        return AjaxJson.getError("登录失败, 账号或密码错误");
        if (userService.queryUserByUsernameAndPwd(str, pwd)){
            return AjaxJson.getSuccess();
        } else {
            return AjaxJson.getError();
        }
    }


    /**
     * 退出登录
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/logout")
    public AjaxJson<?> doLogout(){
        return AjaxJson.getSuccess();
    }
}
