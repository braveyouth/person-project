package com.example.dao;

import com.example.common.request.Req1001;
import com.example.common.response.Res1001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author created by zhangyong
 * @Time 2020/3/2 14:06
 */
@Repository
public class PersonDao {

    @Autowired
    private JdbcTemplate template;

    /**
     * 身份证号是否已经存在
     * @param idCard
     * @return
     */
    public int hasIdCard(String idCard) {
        return template.queryForObject("select count(0) from person where id_card=?",
                Integer.class,
                idCard);
    }

    /**
     * 已领取份数
     * @return
     */
    public int limitReach() {
        return template.queryForObject("select count(0) from person",
                Integer.class);
    }

    /**
     * 增加获取赠险的人员信息
     * @param req1001
     * @return
     */
    public void getDao(final Req1001 req1001) {
        template.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO person(name,id_card,phone,bank_card,email,que_one,que_two,que_three,que_four,que_five,time)VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,req1001.getName());
            ps.setString(2,req1001.getIdCard());
            ps.setString(3,req1001.getPhone());
            ps.setString(4,req1001.getBankCard());
            ps.setString(5,req1001.getEmail());
            ps.setString(6,req1001.getQueOne());
            ps.setString(7,req1001.getQueTwo());
            ps.setString(8,req1001.getQueThree());
            ps.setString(9,req1001.getQueFour());
            ps.setString(10,req1001.getQueFive());
            ps.setString(11,req1001.getTime());
            return ps;
        });
    }

    /**
     * 查询
     * @return
     */
    public List<Res1001> selectDao() {
        try {
            return template.query("select * from person",
                    (rs,num) -> {
                        Res1001 res1001 = new Res1001();
                        res1001.setName(rs.getString("name"));
                        res1001.setIdCard(rs.getString("id_card"));
                        res1001.setPhone(rs.getString("phone"));
                        res1001.setBankCard(rs.getString("bank_card"));
                        res1001.setEmail(rs.getString("email"));
                        res1001.setQueOne(rs.getString("que_one"));
                        res1001.setQueTwo(rs.getString("que_two"));
                        res1001.setQueThree(rs.getString("que_three"));
                        res1001.setQueFour(rs.getString("que_four"));
                        res1001.setQueFive(rs.getString("que_five"));
                        res1001.setTime(rs.getString("time"));
                        return res1001;
                    });
        } catch (DataAccessException e) {
            return null;
        }
    }

//    //身份证号是否已经存在
//    int hasIdCard(String idCard);
//
//    //当前获取赠险名额是否达到限制
//    int limitReach();
//
//    //增加获取赠险的人员信息
//    int getDao(Req1001 req1001);


}
