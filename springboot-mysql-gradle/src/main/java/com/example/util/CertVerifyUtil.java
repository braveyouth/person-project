package com.example.util;

import com.example.common.BoolInt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author created by zhangyong
 * @Time 2020/3/2 15:49
 */
@Slf4j
public class CertVerifyUtil {

    private String code;

    private CertVerifyUtil(String code) {
        this.code = code;
    }

    private final String[] provinces = {
            "11", "12", "13", "14", "15",
            "21", "22", "23",
            "31", "32", "33", "34", "35", "36", "37",
            "41", "42", "43", "44", "45", "46",
            "50", "51", "52", "53", "54",
            "61", "62", "63", "64", "65",
            "71", "81", "82"


    };

    private boolean inProvinces(String provinceCode) {
        for (String p : provinces) {
            if (p.equals(provinceCode)) {
                return true;
            }
        }
        return false;
    }

    interface CodeCheck {

        boolean check();
    }

    private class CheckDigit {

        private char[] codeCharacter;   // 代码集对照 ['A','B','C'] 即 0->'A' 1 ->'B'


        private char[] checkCodeCharacter;  // 校验码对照

        private int[] weights;

        private int mod;

        private CheckDigit(char[] codeCharacter, char[] checkCodeCharacter, int[] weights) {
            this.codeCharacter = codeCharacter;
            this.checkCodeCharacter = checkCodeCharacter;
            this.weights = weights;
            mod = checkCodeCharacter.length;
        }

        /**
         * 得到字符值
         */
        private BoolInt getCharValue(char s) {
            for (int i = 0; i < codeCharacter.length; i++) {
                if (s == codeCharacter[i]) {
                    return new BoolInt(true, i);
                }
            }
            return new BoolInt(false, 0);
        }

        /**
         * 根据校验值对应的字符 应 y=d- mod(x,d)  , 0=<mod(x,d)<=d-1 ,即 1=<y<=d 当 y=d 取checkCodeCharacter[0]
         */
        private char getCharByValue(int value) { // vale  在     1~ mod 之间
            if (value < checkCodeCharacter.length && value > 0) {
                return checkCodeCharacter[value];
            } else if (value == checkCodeCharacter.length) {
                return checkCodeCharacter[0];
            }
            throw new RuntimeException("value " + value + " is error");
        }

        private boolean checkLastNum(String unContactCheckCode, char actualLastNum) {
            int total = 0;
            for (int i = 0; i < unContactCheckCode.length(); i++) {
                final BoolInt charValue = getCharValue(unContactCheckCode.charAt(i));
                if (!charValue.isFlag()) {
                    log.info("找不到" + unContactCheckCode.charAt(i) + "对应的字符值");
                    return false;
                }
                total += charValue.getI() * weights[i];
            }
            return actualLastNum == getCharByValue(mod - (total % mod));
        }
    }

    public static CertVerifyUtil getInstance(String code) {
        return new CertVerifyUtil(code);
    }


    /**
     * 校验9位组织机构代码(XXXXXXXX-X)
     */
    public boolean checkOrganizationCode() {
        return new OrganizationCode().check();
    }

    public boolean checkUnifiedSocialCreditIdentifier() {
        return new UnifiedSocialCreditIdentifier().check();
    }

    /**
     * 校验18位身份证号
     */
    public boolean checkIDCardNum() {
        return new IDCardNum().check();
    }

    /**
     * 社会统一信用代码
     */
    private class UnifiedSocialCreditIdentifier implements CodeCheck {


        private String prefix = "(3[123459]|[15N][1239]|[67][129]|[248A][19]|9[123]|Y1)";


        private final int[] weights = {
                1, 3, 9, 27, 19, 26, 16, 17,
                20, 29, 25, 13, 8, 24, 10, 30,
                28};//  生成校验码时各个数字的的权值


        private final char[] Y = new char[]{
                '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'A', 'B',
                'C', 'D', 'E', 'F', 'G', 'H',
                'J', 'K', 'L', 'M', 'N', 'P',
                'Q', 'R', 'T', 'U', 'W', 'X',
                'Y'};

        public boolean check() {
            if (checkRegularly() && inProvinces(code.substring(2, 4))
                    && getInstance(code.substring(8, 16) + "-" + code.charAt(16)).checkOrganizationCode()
                    && new CheckDigit(Y, Y, weights).checkLastNum(code.substring(0, 17), code.charAt(17))) {
                return true;
            } else {
                return false;
            }
        }

        private boolean checkRegularly() {
            return !(StringUtils.isBlank(code) || code.length() != 18 ||
                    !code.matches(prefix + "[0-9]{6}[A-Z0-9]{8}[0-9X][0-9A-Y]"));
        }
    }


    /**
     * 组织机构代码校验类
     */
    private class OrganizationCode implements CodeCheck {

        private final int[] weights = {3, 7, 9, 10, 5, 8, 4, 2};

        private final char[] X = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


        private final char[] Y = new char[]{
                '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'X'};

        public boolean check() {
            if (checkRegularly() &&
                    new CheckDigit(X, Y, weights).checkLastNum(code.substring(0, 8), code.charAt(9))) {
                return true;
            } else {
                return false;
            }
        }

        private boolean checkRegularly() {
            if (StringUtils.isBlank(code) || code.length() != 10 || !code.matches("[A-Z0-9]{8}-[0-9X]")) {
                return false;
            }
            return true;
        }
    }


    /**
     * 身份证校验类
     */
    private class IDCardNum implements CodeCheck {

        private final int[] weights = {7, 9, 10, 5, 8, 4, 2, 1,
                6, 3, 7, 9, 10, 5, 8, 4, 2};// 18位身份证中，生成校验码时各个数字的的权值


        private final char[] Y = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

        private boolean checkRegularly() {
            if (StringUtils.isBlank(code) || code.length() != 18 || !code.matches("[0-9]{17}[0-9X]")) {
                return false;
            }
            return true;
        }

        public boolean checkDate() {
            String date = code.substring(6, 14);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            try {
                sdf.setLenient(false);
                sdf.parse(date);
            } catch (ParseException e) {

                return false;
            }
            return true;
        }

        private boolean checkLastNum() {
            int total = 0;
            for (int i = 0; i < code.length() - 1; i++) {
                total += (code.charAt(i) - '0') * weights[i];
            }
            char C18 = Y[total % 11];
            if (code.charAt(17) == C18) {
                return true;
            }
            return false;
        }

        public boolean check() {
            if (checkRegularly() && inProvinces(code.substring(0, 2)) && checkDate() && checkLastNum()) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static void main(String[] args) {

        final boolean b = getInstance("914201067844578688").checkUnifiedSocialCreditIdentifier();
        final boolean b1 = getInstance("914201020684427377").checkUnifiedSocialCreditIdentifier();
        final boolean b2 = getInstance("91420104MA4KPNYN6B").checkUnifiedSocialCreditIdentifier();
        final boolean b3 = getInstance("914201026823239222").checkUnifiedSocialCreditIdentifier();
        final boolean b4 = getInstance("91533124MA6NE3FK2N").checkUnifiedSocialCreditIdentifier();
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
    }

}
