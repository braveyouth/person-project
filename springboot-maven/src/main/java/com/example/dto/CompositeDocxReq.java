package com.example.dto;

import java.util.List;
import java.util.Map;

/**
 * @author zhangy
 * @Time 2021-07-13 16:10
 * @Description: 下载docx请求参数
 */
public class CompositeDocxReq {
    //非循环列表的变量数据
    private Map<String, String> mappings;
    //多个循环列表
    private List<DataList> dataList;
    //图片数据
    private List<Map<String, Object>> picList;
    //导出文件名
    private String fileName;

    public static class DataList{
        //循环列表的变量数据
        private List<Map<String , Object>> dataInnerList;
        //处于docx中的表格序号，从0开始
        private Integer num;

        public List<Map<String, Object>> getDataInnerList() {
            return dataInnerList;
        }

        public void setDataInnerList(List<Map<String, Object>> dataInnerList) {
            this.dataInnerList = dataInnerList;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
    }

    public Map<String, String> getMappings() {
        return mappings;
    }

    public void setMappings(Map<String, String> mappings) {
        this.mappings = mappings;
    }

    public List<DataList> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataList> dataList) {
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
