package com.duoer.campus.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
@TableName("cat_tmp")
public class CatTemp extends Cat {
    private String username;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    @Version
    private Integer version;

    public CatTemp(Cat c, String username) {

        this.username = username;
    }
}
