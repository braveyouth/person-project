package com.example.common;

import lombok.Getter;

/**
 * @author created by zhangyong
 * @Time 2020/3/2 15:50
 */
@Getter
public class BoolInt {

    private final boolean flag;

    private final int i;

    public BoolInt(boolean flag, int i) {
        this.flag = flag;
        this.i = i;
    }
}
