package com.example.service;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.BoolString;
import com.example.common.request.Req1001;
import com.example.common.response.Res1001;
import com.example.dao.PersonDao;
import com.example.mapper.PersonMapper;
import com.example.util.CertVerifyUtil;
import com.example.util.EncrypteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author created by zhangyong
 * @Time 2020/3/2 14:05
 */
@Slf4j
@Service
public class PersonDaoService {

    @Autowired
    private PersonDao personDao;

    /**
     * 校验请求参数
     * @param req1001
     * @return
     */
    public BoolString checkReq1001(Req1001 req1001) throws Exception{

        //身份证号是否符合规则
        if(!CertVerifyUtil.getInstance(req1001.getIdCard()).checkIDCardNum()){
            return BoolString.no("身份证号有误");
        }

        //身份证号是否已经存在
        if(personDao.hasIdCard(EncrypteUtil.encrypt(req1001.getIdCard()))>0){
            return BoolString.no("您已经成功领取，不能重复领取");
        }

        //银行卡号是否符合规则
        if(!req1001.getBankCard().substring(0,6).equals("622672")){
            return BoolString.no("请输入正确的光大银行卡号");
        }

        //当前获取赠险名额是否达到限制
        if(personDao.limitReach()>=6000){
            return BoolString.no("领取名额已满，活动已结束");
        }

        return BoolString.ok();
    }

    /**
     * 获取赠险
     * @param req1001
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void getService(Req1001 req1001) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        req1001.setTime(simpleDateFormat.format(new Date()));

        if(req1001.getQueOne().equals("y")){
            req1001.setQueOne("是");
        }else if(req1001.getQueOne().equals("n")){
            req1001.setQueOne("否");
        }
        if(req1001.getQueTwo().equals("y")){
            req1001.setQueTwo("是");
        }else if(req1001.getQueTwo().equals("n")){
            req1001.setQueTwo("否");
        }
        if(req1001.getQueThree().equals("y")){
            req1001.setQueThree("是");
        }else if(req1001.getQueThree().equals("n")){
            req1001.setQueThree("否");
        }
        if(req1001.getQueFour().equals("y")){
            req1001.setQueFour("是");
        }else if(req1001.getQueFour().equals("n")){
            req1001.setQueFour("否");
        }
        if(req1001.getQueFive().equals("y")){
            req1001.setQueFive("是");
        }else if(req1001.getQueFive().equals("n")){
            req1001.setQueFive("否");
        }

        req1001.setIdCard(EncrypteUtil.encrypt(req1001.getIdCard()));
        req1001.setPhone(EncrypteUtil.encrypt(req1001.getPhone()));
        req1001.setBankCard(EncrypteUtil.encrypt(req1001.getBankCard()));
        req1001.setEmail(EncrypteUtil.encrypt(req1001.getEmail()));

        //增加获取赠险的人员信息
        personDao.getDao(req1001);

    }

    /**
     * 查询
     * @return
     * @throws Exception
     */
    public List<Res1001> selectService() throws Exception {
        List<Res1001> list = personDao.selectDao();

        for (Res1001 res1001 : list) {
            res1001.setIdCard(EncrypteUtil.decrypte(res1001.getIdCard()));
            res1001.setPhone(EncrypteUtil.decrypte(res1001.getPhone()));
            res1001.setBankCard(EncrypteUtil.decrypte(res1001.getBankCard()));
            res1001.setEmail(EncrypteUtil.decrypte(res1001.getEmail()));
        }
        return list;
    }


    /**
     * 导出
     * @throws Exception
     */
    public void exportService() throws Exception {
        ExcelWriter writer = ExcelUtil.getWriter("F:/光大赠险名单.xlsx");

        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("idCard","身份证号");
        writer.addHeaderAlias("phone","手机号");
        writer.addHeaderAlias("bankCard","银行卡号");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("queOne","1、您是否愿意接受我行为您赠送团体人身保险，并同意按照保险公司要求提供个人姓名、身份证号？");
        writer.addHeaderAlias("queTwo","2、您是否是年龄在18周岁至65周岁之间身体健康能正常工作或正常生活的自然人？");
        writer.addHeaderAlias("queThree","3、您当前是否身体健康，未受到意外事故伤害和未出现感染新冠病毒感染症状？");
        writer.addHeaderAlias("queFour","4、您是否同意本保险受益人为法定？");
        writer.addHeaderAlias("queFive","5、您是否明确本保险的保险费由中国光大银行支付，保险责任由光大永明人寿保险有限公司湖北省分公司承担，您与保险公司的保险纠纷与我行无关。");
        writer.addHeaderAlias("time","领取日期");

        writer.merge(10,"光大赠险名单");
        writer.write(selectService(),true);
        writer.close();
    }

    /**
     * 已领取份数
     * @return
     */
    public int sumService() {

       return personDao.limitReach();

    }
}
