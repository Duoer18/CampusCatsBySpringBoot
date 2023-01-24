package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class User {
    private String username;
    @TableField(select = false)
    private String password;
    private Integer status;
    @TableField(exist = false)
    private Boolean isAdmin = false;
}
