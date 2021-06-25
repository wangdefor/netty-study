package com.wy.gobang.msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wangyong
 * @Classname GoBangModel
 * @Description 五子棋model
 * @Date 2021/5/28 15:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoBangModelMsg implements Serializable {

    /**
     * 用户
     */
    private User user;

    /**
     * 坐标x
     */
    private Integer x;

    /**
     * 坐标y
     */
    private Integer y;
}
