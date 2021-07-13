package com.example.dto;

import java.util.List;
import java.util.Map;

/**
 * @author zhangy
 * @Time 2021-07-13 16:10
 * @Description:
 */
public class CompositeDocxReq {
    //非循环列表的变量数据
    private Map<String, String> mappings;
    //循环列表的变量数据
    private List<Map<String , Object>> dataList;
    //图片数据
    private List<Map<String, Object>> picList;
    //导出文件名
    private String fileName;

    public Map<String, String> getMappings() {
        return mappings;
    }

    public void setMappings(Map<String, String> mappings) {
        this.mappings = mappings;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public List<Map<String, Object>> getPicList() {
        return picList;
    }

    public void setPicList(List<Map<String, Object>> picList) {
        this.picList = picList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
