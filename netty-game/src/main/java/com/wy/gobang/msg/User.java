package com.wy.gobang.msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;

/**
 * @author wangyong
 * @Classname User
 * @Description User
 * @Date 2021/6/25 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 颜色
     */
    private Color color;
}
