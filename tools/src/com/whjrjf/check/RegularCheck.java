package com.whjrjf.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangy
 */
public class RegularCheck {
    private static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
    private static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";
    private static final String MOBILE = "^(13[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$";
    private static final String INTEGER = "^-?(([1-9]\\d*$)|0)";
    private static final String INTEGER_POSITIVE = "^[1-9]\\d*|0$";
    private static final String INTEGER_NEGATIVE = "^-[1-9]\\d*|0$";
    private static final String DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";
    private static final String DOUBLE_NEGATIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";
    private static final String DOUBLE_POSITIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
    private static final String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    private static final String ZIP = "[0-9]\\d{5}(?!\\d)";
    private static final String STR_ENG_NUM_ = "^\\w+$";
    private static final String STR_ENG_NUM = "^[A-Za-z0-9]+";
    private static final String STR_ENG = "^[A-Za-z]+$";
    private static final String DATE_ALL = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";
    private static final String DATE_FORMAT1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    private static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
    private static final String JIGOU_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";
    private static final String STR_NUM = "^[0-9]+$";
    private static final String SAFE_STR = "[a-zA-Z0-9_@\\-\\.]{_min,_max}";

    public RegularCheck() {
    }

    public static boolean isEmail(String str) {
        return regular(str, "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+");
    }

    public static boolean isPhone(String str) {
        return regular(str, "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)");
    }

    public static boolean isMobile(String str) {
        return regular(str, "^(13[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$");
    }

    public static boolean isMobileLazy(String str) {
        return str.startsWith("1") && str.length() == 11 && isInteger(str);
    }

    public static boolean isUrl(String str) {
        return regular(str, "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$");
    }

    public static boolean isNumber(String str) {
        return regular(str, "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$") || regular(str, "^-?(([1-9]\\d*$)|0)");
    }

    public static boolean isInteger(String str) {
        return regular(str, "^-?(([1-9]\\d*$)|0)");
    }

    public static boolean isIntegerNegative(String str) {
        return regular(str, "^-[1-9]\\d*|0$");
    }

    public static boolean isIntegerPositive(String str) {
        return regular(str, "^[1-9]\\d*|0$");
    }

    public static boolean isDouble(String str) {
        return regular(str, "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");
    }

    public static boolean isDoubleNegative(String str) {
        return regular(str, "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$");
    }

    public static boolean isDoublePositive(String str) {
        return regular(str, "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$");
    }

    public static boolean isDate(String str) {
        return regular(str, "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))");
    }

    public static boolean isDateCHNFormat(String str) {
        return regular(str, "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)");
    }

    public static boolean isAge(String str) {
        return regular(str, "^(?:[1-9][0-9]?|1[01][0-9]|120)$");
    }

    public static boolean isLengOut(String str, int leng) {
        return null != str && str.length() <= 0 ? str.trim().length() > leng : false;
    }

    public static boolean isZIP(String str) {
        return regular(str, "[0-9]\\d{5}(?!\\d)");
    }

    public static boolean isLetter(String str) {
        return regular(str, "^[A-Za-z]+$");
    }

    public static boolean isLetterNumberStr(String str) {
        return regular(str, "^[A-Za-z0-9]+");
    }

    public static boolean isSafeStr_(String str) {
        return regular(str, "^\\w+$");
    }

    public static boolean isStrNumber(String str) {
        return regular(str, "^[0-9]+$");
    }

    public static boolean isSafeString(int min, int max, String str) {
        String reg = "[a-zA-Z0-9_@\\-\\.]{_min,_max}";
        reg = reg.replace("_min", min + "");
        reg = reg.replace("_max", max + "");
        return regular(str, reg);
    }

    private static boolean regular(String str, String pattern) {
        if (null != str && str.trim().length() > 0) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.matches();
        } else {
            return false;
        }
    }
}
