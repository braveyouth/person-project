/**
 * Created by scorpion1750 on 2017/11/9.
 */
//var httpUrl = 'http://192.168.136.39:8080/PSBCO2O/pro/';
//var httpUrl = 'http://119.96.111.38:8858/yqx/'
//var httpUrl = 'http://192.168.136.51:8081/PSBCO2O/pro/';
//var httpUrl = 'http://58.53.209.97:8080/PSBCO2O/pro/';
//var httpUrl = 'http://rbsqg.bxt189.com:8080/PSBCO2O/pro/';
//var httpUrl = 'http://'+window.location.host +'/PSBCO2O/pro/';
//var httpUrl = '/PSBCO2O/pro/';
var httpUrl = '/yqx/'
//var httpUrl = '/'

function getData(url,data,callback){

    mui.ajax(httpUrl+url,{
        data:JSON.stringify(data),
        dataType:'json',//服务器返回json格式数据
        type:'post',//HTTP请求类型
        //timeout:10000,//超时时间设置为10秒；
        headers:{'Content-Type':'application/json'},
        success:function(rs){
            //服务器返回响应，根据响应结果，分析是否登录成功；
            if(rs.code == 0){
                callback(rs)
            }else{
                document.getElementById("mask").style.display = "none";//隐藏遮罩
                mui.alert(rs.msg);
            }
        },
        error:function(xhr,type,errorThrown){
            //异常处理；
            document.getElementById("mask").style.display = "none";//隐藏遮罩
            mui.alert('接口服务异常！');
            console.log(type);
        }
    });
}
function getDataAsy(url,data,callback){
    //debugger;
    mui.ajax(httpUrl+url,{
        data:JSON.stringify(data),
        dataType:'json',//服务器返回json格式数据
        type:'post',//HTTP请求类型
        async:false,
        headers:{'Content-Type':'application/json'},
        success:function(rs){
            //服务器返回响应，根据响应结果，分析是否登录成功；
            if(rs.code == 0){
                callback(rs)
            }else{
                mui.alert(rs.msg)
            }
        },
        error:function(xhr,type,errorThrown){
            //异常处理；
            mui.alert('接口服务异常！')
            console.log(type);
        }
    });
}
function getPro(dept,type,flag){
    if(type==0){
        var data = {
            deptLevel: '2',
            flag:flag,
            deptId:'',
            lantId:'',
            areaId:''
        };
    }else if(type==1){
        data = {
            deptLevel: '3',
            flag:flag,
            deptId:dept,
            lantId:'',
            areaId:''
        };
    }else if(type==2){
        data = {
            deptLevel: '',
            flag:flag,
            deptId:'',
            lantId:dept,
            areaId:''
        };
    }else if(type==3){
        data = {
            deptLevel: '',
            flag:flag,
            deptId:'',
            lantId:'',
            areaId:dept
        };
    }
    var arr = [];
    getDataAsy('PreOrder/1082',data,function(rs) {
        var data = rs.data;

        if(data.length>0){
            for(var i in data){
                var ob={};
                if(type==2){
                    ob.value = data[i].areaId;
                    ob.text = data[i].areaName;

                }else if(type==3){
                    ob.value = data[i].townId;
                    ob.text = data[i].townName;

                }else{
                    ob.value = data[i].deptId;
                    ob.text = data[i].deptName;
                }

                arr.push(ob);
            }
           // console.log(JSON.stringify(arr));

        }else{
            var ob1={};
            ob1.value = '000000';
            ob1.text = '全部';
            arr.push(ob1);
        }
    });
    return arr;
}
function postImg(url, id, callback) {
    $('#' + id).ajaxSubmit({
        success: function(rs) {
            callback(rs)
        },
        error: function(error) {
            $("#mask").hide();//关闭遮罩
            mui.alert('图片上传失败');
        },
        url: httpUrl+url,
        /*设置post提交到的页面*/
        type: 'post',
        /*设置表单以post方法提交*/
        dataType: 'json' /*设置返回值类型为文本*/
    })
}
function getRadio(id){
    var obj = document.getElementsByName(id);
    var type='';
    for(var i = 0; i < obj.length; i ++) {
        if (obj[i].checked) {
            type = obj[i].value;
        }
    }
    return type
}
function valid(){
    //var brea = document.getElementById("brea").getAttribute("attr");
    //var brea1 = document.getElementById("city1").getAttribute("attr");
    var comprador = document.getElementById("comprador").value;
    var idCard = document.getElementById("idCard").value;
    //var cardAddress = document.getElementById("cardAddress").value;
    //var address = document.getElementById("address").value;
    var contactNumber = document.getElementById("contactNumber").value;
    var contactNumber1 = document.getElementById("contactNumber1").value;

    //var remark = document.getElementById("remark").value;
    var referName = trim(document.getElementById("referName").value);
    //var referPhone = document.getElementById("referPhone").value;
    var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
        regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;

    var regEn1 = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
        regCn1 = /[·#￥‘|【】[\]]/im;

    var num = /^\d*$/; //全数字

    var strBin = "622672";

    var input1 = getRadio("input1"),
        input2 = getRadio("input2"),
        input3 = getRadio("input3"),
        input4 = getRadio("input4"),
        input5 = getRadio("input5"),
        input6 = getRadio("input6");

    //var szReg=/^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/
    var szReg=/^[a-zA-Z0-9]+([._\\-]*[a-zA-Z0-9])*@([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+\.){1,63}[a-zA-Z0-9]+$/

    //if(brea==''){
    //    mui.toast("请选择安装地址");
    //    return false;
    //}else
    /*if(comprador==''){
        mui.toast("请填写入网机主姓名");
        return false;
    }else if(comprador!=''&&regEn.test(comprador) || regCn.test(comprador)){
        mui.toast("入网机主姓名中包含特殊字符");
        return false;
    }else if(idCard==''){
        mui.toast("请填写身份证号码");
        return false;
    }else if(!IdCardValidate(idCard)){
        mui.toast("身份证信息有误，请重新输入");
        return false;
    }else if(cardAddress==''){
        mui.toast("请填写身份证地址");
        return false;
    }else if(cardAddress!=''&&cardAddress.length<7){
        mui.toast("身份证地址不能少于6个字符");
        return false;
    }else if(cardAddress!=''&&regEn.test(cardAddress) || regCn.test(cardAddress)){
        mui.toast("身份证地址中包含特殊字符");
        return false;
    }else if(contactNumber==''){
        mui.toast("请填写联系电话");
        return false;
    }else if(!contactNumber.match(/^((1[0-9]{1})+\d{9})$/)){
        mui.toast("联系电话格式不正确");
        return false;
    }else if(brea1==''||brea1=='undefined'){
        mui.toast("请选择收货地址");
        return false;
    }else if(address==''){
        mui.toast("请填写详细地址");
        return false;
    }else if(address!=''&&regEn.test(address) || regCn.test(address)){
        mui.toast("详细地址中包含特殊字符");
        return false;
    }else if(remark!=''&&regEn1.test(remark) || regCn1.test(remark)){
        mui.toast("备注中包含特殊字符");
        return false;
    }else if(referName!=''&&regEn.test(referName) || regCn.test(referName)){
        mui.toast("推荐人网点机构号码中包含特殊字符");
        return false;
    }else if(referName!=''&&referName.length!=8){
        mui.toast("网点机构号码为字母，数字组合的8位数");
        return false;
    }else if(referPhone!=''&&regEn.test(referPhone) || regCn.test(referPhone)){
        mui.toast("推荐人柜员号中包含特殊字符");
        return false;
    }*/
    if(comprador==''){
        mui.toast("请填写姓名");
        return false;
    }else if(comprador!=''&&regEn.test(comprador) || regCn.test(comprador)){
        mui.toast("姓名中包含特殊字符");
        return false;
    }else if(idCard==''){
        mui.toast("请填写身份证号码");
        return false;
    }else if(!IdCardValidate(idCard)){
        mui.toast("身份证信息有误，请重新输入");
        return false;
    }else if(contactNumber==''){
        mui.toast("请填写联系电话");
        return false;
    }else if(!contactNumber.match(/^((1[0-9]{1})+\d{9})$/)){
        mui.toast("联系电话格式不正确");
        return false;
    }else if(!szReg.test(contactNumber1)) {
        mui.toast("请填写正确邮箱");
        return false;
        //}else if(referName.length < 16 || referName.length > 19) {
    }else if(referName == "") {
        mui.toast("请填写银行卡号");
        return false;
    //}else if(referName.length < 16 || referName.length > 19) {
    }else if(referName.length < 16) {
        //mui.toast("银行卡号长度必须在16到19之间");
        mui.toast("银行卡号长度必须为16位");
        return false;
    }else if(!num.exec(referName)) {
        mui.toast("银行卡号必须全为数字");
        return false;
    }else if(strBin.indexOf(referName.substring(0, 6)) == -1) {
        mui.toast("仅支持光大银行电子账户Ⅱ类");
        return false;
    }else if(input1 == ""||input2 == ""||input3 == ""|| input4 == "" || input5 == ""){
        mui.toast("请完善问卷信息")
        return false
    }else if(input6 == "" || input6 == "n"){
        mui.toast("请确认同意投保")
        return false
    }
    /*else if(!$("#pickS").hasClass("agree-y")){
        mui.toast("请阅读赠险条款并勾选");
        return false;
    }*/


    return true;
}
//根据id获取名称
var proName = function(val){
    switch (val){
        case '11':return '北京';break;
        case '14':return '山西';break;
        case '23':return '黑龙江';break;
        case '33':return '浙江';break;
        case '42':return '湖北';break;
        case '43':return '湖南';break;
        case '44':return '广东';break;
        case '51':return '四川';break;
        case '53':return '云南';break;
        case '61':return '陕西';break;

        default:return "";break;
    }
};
//获取页面url后面参数的值
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
//去掉字符串头尾空格
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
//身份证验证
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X
function IdCardValidate(idCard) {
    idCard = trim(idCard.replace(/ /g, ""));
    if (idCard.length == 15) {
        return isValidityBrithBy15IdCard(idCard);
    } else if (idCard.length == 18) {
        var a_idCard = idCard.split("");// 得到身份证数组
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){
            return true;
        }else {
            return false;
        }
    } else {
        return false;
    }
}
/**
 * 判断身份证号码为18位时最后的验证位是否正确
 * @param a_idCard 身份证号码数组
 * @return
 */
function isTrueValidateCodeBy18IdCard(a_idCard) {
    var sum = 0; // 声明加权求和变量
    if (a_idCard[17].toLowerCase() == 'x') {
        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
    }
    for ( var i = 0; i < 17; i++) {
        sum += Wi[i] * a_idCard[i];// 加权求和
    }
    valCodePosition = sum % 11;// 得到验证码所位置
    if (a_idCard[17] == ValideCode[valCodePosition]) {
        return true;
    } else {
        return false;
    }
}
/**
 * 通过身份证判断是男是女
 * @param idCard 15/18位身份证号码
 * @return 'female'-女、'male'-男
 */
function maleOrFemalByIdCard(idCard){
    idCard = trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。
    if(idCard.length==15){
        if(idCard.substring(14,15)%2==0){
            return 'female';
        }else{
            return 'male';
        }
    }else if(idCard.length ==18){
        if(idCard.substring(14,17)%2==0){
            return 'female';
        }else{
            return 'male';
        }
    }else{
        return null;
    }
}
/**
 * 验证18位数身份证号码中的生日是否是有效生日
 * @param idCard 18位书身份证字符串
 * @return
 */
function isValidityBrithBy18IdCard(idCard18){
    var year =  idCard18.substring(6,10);
    var month = idCard18.substring(10,12);
    var day = idCard18.substring(12,14);
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
    // 这里用getFullYear()获取年份，避免千年虫问题
    if(temp_date.getFullYear()!=parseFloat(year)
        ||temp_date.getMonth()!=parseFloat(month)-1
        ||temp_date.getDate()!=parseFloat(day)){
        return false;
    }else{
        return true;
    }
}
/**
 * 验证15位数身份证号码中的生日是否是有效生日
 * @param idCard15 15位书身份证字符串
 * @return
 */
function isValidityBrithBy15IdCard(idCard15){
    var year =  idCard15.substring(6,8);
    var month = idCard15.substring(8,10);
    var day = idCard15.substring(10,12);
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
    // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
    if(temp_date.getYear()!=parseFloat(year)
        ||temp_date.getMonth()!=parseFloat(month)-1
        ||temp_date.getDate()!=parseFloat(day)){
        return false;
    }else{
        return true;
    }
}