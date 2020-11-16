package com.example.common;

import lombok.Getter;

/**
 * @author created by zhangyong
 * @Time 2020/3/2 15:22
 */
@Getter
public class BoolString {

    private final boolean ok;

    private final String errMsg;

    protected BoolString(boolean ok, String errMsg) {
        this.ok = ok;
        this.errMsg = errMsg;
    }

    public static BoolString no(String errMsg) {
        return new BoolString(false, errMsg);
    }

    public static BoolString ok() {
        return new BoolString(true, null);
    }
}
