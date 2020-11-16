package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * 课件
 * @author zb
 * @date 2019-03-31 16:55
 **/
@Document
public class Courseware extends DomainId {
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件URL
     */
    private String url;
    /**
     * 文件大小
     */
    private BigDecimal size;
    /**
     * 视频，持续时长
     */
    private String duration;
    /**
     * 课件顺序
     */
    private Integer sortNo;

    @Override
    public String toString() {
        return "Courseware{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", size=" + size +
                ", duration='" + duration + '\'' +
                ", sortNo=" + sortNo +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
