package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId
    private Long id;
    private String username;
    @TableField(select = false)
    private String password;
    private Integer status;
    @TableField(exist = false)
    private Boolean isAdmin = false;
}
