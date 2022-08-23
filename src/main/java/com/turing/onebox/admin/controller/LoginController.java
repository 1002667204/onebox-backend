package com.turing.onebox.admin.controller;
import com.turing.onebox.admin.service.InitService;
import com.turing.onebox.admin.service.UserService;
import com.turing.onebox.common.constant.OneboxConstant;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.model.dto.User;
import com.turing.onebox.common.utils.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 17:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private InitService initService;

    /**
     * 返回根目录文件列表
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public AjaxJson<?> doLogin(String username,String password, String isRemPwd, HttpSession session, HttpServletResponse response) {
        User user =userService.queryUserByUsernameAndPwd(username, password);

        if (user == null){
            return AjaxJson.getError("账号或密码错误，请重试");
        } else {
            //把user保存到session中
            session.setAttribute("sessionUser",user);

            //如果需要记住密码，则往外写cookie
           /* if("true".equals(isRemPwd)){
                Cookie c1=new Cookie("username",user.getUsername());
                c1.setMaxAge(10*24*60*60);
                response.addCookie(c1);
                Cookie c2=new Cookie("password",user.getPassword());
                c2.setMaxAge(10*24*60*60);
                response.addCookie(c2);
            }else{
                //把没有过期cookie删除
                Cookie c1=new Cookie("username","1");
                c1.setMaxAge(0);
                response.addCookie(c1);
                Cookie c2=new Cookie("password","1");
                c2.setMaxAge(0);
                response.addCookie(c2);
            }*/
            // 初始化根目录
            if (!initService.checkRootDir()){
                return AjaxJson.getError("初始化根目录失败");
            }

            return AjaxJson.getSuccessData(user);
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
