package com.duoer.campus.web;

import com.duoer.campus.entity.User;
import com.duoer.campus.service.UserService;
import com.duoer.campus.utils.JwtUtils;
import com.duoer.campus.web.format.ResponseCode;
import com.duoer.campus.web.format.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 用户登录检查
     *
     * @param u 前端传递用户对象
     * @return 状态
     */
    @RequestMapping("/login")
    public Result login(@RequestBody User u, HttpSession session, HttpServletResponse response) throws JsonProcessingException {
        // 响应代码值
        int code;
        // 仅用于登录时检查用户名和密码是否正确输入，作为响应内容时实际无用处
        int status = 0;
        // 响应消息
        String msg = "";

        // 获取当前会话中的用户名
        Object username;
        synchronized (UserController.class) {
            if ((username = session.getAttribute("username")) == null) { // 若为空，则允许登录
                User userSelected = userService.loginCheck(u);
                if (userSelected != null) {
                    String json = objectMapper.writeValueAsString(userSelected);
                    User userJSON = objectMapper.readValue(json, User.class);
                    System.out.println(userJSON);
                    String jwt = JwtUtils.createJWT(json);
                    System.out.println(jwt);

                    session.setAttribute("username", u.getUsername());
                    session.setAttribute("isAdmin", userSelected.getStatus() == 2 ? true : null);
                    code = ResponseCode.LOG_SUC.getCode();
                    if (u.getStatus() == -11) {
                        Cookie usernameCookie = new Cookie("username", u.getUsername());
                        Cookie passwordCookie = new Cookie("password", u.getPassword());
                        usernameCookie.setMaxAge(60 * 60 * 24 * 3);
                        passwordCookie.setMaxAge(60 * 60 * 24 * 3);
                        response.addCookie(usernameCookie);
                        response.addCookie(passwordCookie);
                    }
                } else {
                    code = ResponseCode.LOG_ERR.getCode();
                }
            } else { // 若非空，提示用户已登录其他账号
                code = ResponseCode.LOG_EXT.getCode();
                msg = "您当前已登录其他账号 " + username + "，请先注销该账号！";
            }
        }

        return new Result(code, status, msg);
    }

    @RequestMapping("/acc")
    public Result getAccount(@CookieValue(value = "username", required = false) String username,
                             @CookieValue(value = "password", required = false) String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        return new Result(ResponseCode.GET_SUC.getCode(), u);
    }

    /**
     * 注册新账号
     *
     * @param u 前端传递用户对象
     * @return 状态
     */
    @RequestMapping("/register")
    public Result register(@RequestBody User u) {
        int status = userService.registerCheck(u);
        int code = status == 1 ? ResponseCode.REG_SUC.getCode() : ResponseCode.REG_ERR.getCode();
        return new Result(code, status);
    }

    /**
     * 检查用户是否存在，注册时实时检测
     *
     * @param u 前端传递用户对象
     * @return 状态
     */
    @RequestMapping("/checkExist")
    public Result checkExist(@RequestBody User u) {
        int status = userService.existCheck(u);
        int code = status == 1 ? ResponseCode.REG_SUC.getCode() : ResponseCode.REG_ERR.getCode();
        return new Result(code, status);
    }

    /**
     * 返回用户名
     *
     * @return 用户名，默认为visitor（游客）
     */
    @GetMapping
    public Result usrMsg(HttpSession session) {
        String username = (String) session.getAttribute("username");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        isAdmin = isAdmin != null ? isAdmin : false;
        User u = new User();
        u.setUsername(username != null ? username : "visitor");
        u.setIsAdmin(isAdmin);
        return new Result(ResponseCode.GET_SUC.getCode(), u);
    }
}
