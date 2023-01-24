package com.duoer.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.duoer.campus.dao.UserMapper;
import com.duoer.campus.entity.User;
import com.duoer.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录校验
     *
     * @param u 用户对象
     * @return 状态码
     */
    @Override
    public int loginCheck(User u) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, u.getUsername())
                .eq(User::getPassword, u.getPassword());
        User userSelected = userMapper.selectOne(wrapper);
        if (userSelected == null) { // 账号或密码错误
            return -1;
        } else { // 用户状态正常
            return userSelected.getStatus();
        }
    }

    /**
     * 注册
     *
     * @param u 用户对象
     * @return 状态码
     */
    @Override
    public int registerCheck(User u) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, u.getUsername());
        User userExisted = userMapper.selectOne(wrapper);
        if (userExisted != null) { // 用户已存在
            return 0;
        } else { // 注册已成功
            u.setStatus(1);
            return userMapper.insert(u);
        }
    }

    /**
     * 查重
     *
     * @param u 用户对象
     * @return 状态码
     */
    @Override
    public int existCheck(User u) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, u.getUsername());
        User userExisted = userMapper.selectOne(wrapper);
        if (userExisted != null) { // 用户已存在
            return 0;
        } else {
            return 1;
        }
    }
}
