package com.example.common.response;

import com.example.common.ApiResponse;
import com.example.common.DataResponse;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author zhangy
 * @Time 2020/4/5 23:24
 */
@Data
public class Res1002{

    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;


    private List<Res1001> list;

}
