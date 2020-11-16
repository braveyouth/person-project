package com.example.mapper;

import com.example.common.request.Req1001;
import com.example.common.response.Res1001;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

/**
 * @author zhangy
 * @Time 2020/4/5 21:15
 */
@Repository
public interface PersonMapper {

    //身份证号是否已经存在
    int hasIdCard(String idCard);

    //当前获取赠险名额是否达到限制、已领取份数
    int limitReach();

    //增加获取赠险的人员信息
    void getDao(Req1001 req1001);

    //查询
    Page<Res1001> selectDao(RowBounds rowBounds);
}
