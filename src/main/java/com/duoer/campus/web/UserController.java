package com.duoer.campus.web;

import com.alibaba.fastjson2.JSON;
import com.duoer.campus.entity.User;
import com.duoer.campus.service.UserService;
import com.duoer.campus.utils.JwtUtils;
import com.duoer.campus.response.ResponseCode;
import com.duoer.campus.response.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 用户登录检查
     *
     * @param u 前端传递用户对象
     * @return 状态
     */
    @RequestMapping("/login")
    public Result login(@RequestBody User u, HttpServletResponse response) {
        // 响应代码值
        int code;
        // 仅用于登录时检查用户名和密码是否正确输入，作为响应内容时实际无用处
        int status = 0;
        // 响应消息
        String msg = "";

        User userSelected = userService.loginCheck(u);
        if (userSelected != null) {
            userSelected.setIsAdmin(userSelected.getStatus() == 2);
            String json = JSON.toJSONString(userSelected);
            User userJSON = JSON.parseObject(json, User.class);
            System.out.println(userJSON);
            String jwt = JwtUtils.createJWT(json);
            System.out.println(jwt);
            redisTemplate.opsForValue().set("user_token_" + jwt, json, 60, TimeUnit.MINUTES);

//                    session.setAttribute("username", u.getUsername());
//                    session.setAttribute("isAdmin", userSelected.getStatus() == 2 ? true : null);

            code = ResponseCode.LOG_SUC.getCode();
            if (u.getStatus() == -11) {
                Cookie usernameCookie = new Cookie("username", u.getUsername());
                Cookie passwordCookie = new Cookie("password", u.getPassword());
                usernameCookie.setMaxAge(60 * 60 * 24 * 3);
                passwordCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }

            return new Result(code, jwt, msg);
        }

        code = ResponseCode.LOG_ERR.getCode();
        return new Result(code, status, msg);
        // 获取当前会话中的用户名
//        Object username;
//        synchronized (UserController.class) {
//            if ((username = session.getAttribute("username")) == null) { // 若为空，则允许登录
//                User userSelected = userService.loginCheck(u);
//                if (userSelected != null) {
//                    userSelected.setIsAdmin(userSelected.getStatus() == 2);
//                    String json = objectMapper.writeValueAsString(userSelected);
//                    User userJSON = objectMapper.readValue(json, User.class);
//                    System.out.println(userJSON);
//                    String jwt = JwtUtils.createJWT(json);
//                    System.out.println(jwt);
//
////                    session.setAttribute("username", u.getUsername());
////                    session.setAttribute("isAdmin", userSelected.getStatus() == 2 ? true : null);
//
//                    code = ResponseCode.LOG_SUC.getCode();
//                    if (u.getStatus() == -11) {
//                        Cookie usernameCookie = new Cookie("username", u.getUsername());
//                        Cookie passwordCookie = new Cookie("password", u.getPassword());
//                        usernameCookie.setMaxAge(60 * 60 * 24 * 3);
//                        passwordCookie.setMaxAge(60 * 60 * 24 * 3);
//                        response.addCookie(usernameCookie);
//                        response.addCookie(passwordCookie);
//                    }
//
//                    return new Result(code, jwt, msg);
//                } else {
//                    code = ResponseCode.LOG_ERR.getCode();
//                    return new Result(code, status, msg);
//                }
//            } else { // 若非空，提示用户已登录其他账号
//                code = ResponseCode.LOG_EXT.getCode();
//                msg = (String) username;
//                return new Result(code, status, msg);
//            }

    }

    @RequestMapping("/logout")
    public Result logout(@RequestHeader(name = "token", required = false, defaultValue = "") String token) {
        if (StringUtils.isNotEmpty(token)) {
            redisTemplate.delete("user_token_" + token);
        }
        return new Result(ResponseCode.LOG_SUC.getCode(), "logout");
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
    public Result usrMsg(HttpServletRequest request) {
//        String username = (String) session.getAttribute("username");
//        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
//        isAdmin = isAdmin != null ? isAdmin : false;
//        User u = new User();
//        u.setUsername(username != null ? username : "visitor");
//        u.setIsAdmin(isAdmin);
        String token = request.getHeader("token");
        System.out.println(token);
        String userJSON = JwtUtils.parseJWT(token);
        if (StringUtils.isEmpty(userJSON)) {
            return new Result(ResponseCode.GET_SUC.getCode(),
                    new User(null, "visitor", null, null, false));
        }

        User user = JSON.parseObject(userJSON, User.class);
        return new Result(ResponseCode.GET_SUC.getCode(), user);
    }
}
