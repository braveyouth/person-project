package com.whjrjf.check;

/**
 * @author zhangy
 */
public class IdCardCheck {

    private static int[] weight = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static char[] validate = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    private static String idcardRegex = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    public IdCardCheck() {
    }

    public static boolean check(String idCard) {
        if (null != idCard && idCard.length() != 0) {
            if (!idCard.matches(idcardRegex)) {
                return false;
            } else {
                String idCardLength = idCard.substring(0, idCard.length() - 1);
                char v = idCard.charAt(idCard.length() - 1);
                int sum = 0;
                int mode = 0;

                for(int i = 0; i < idCardLength.length(); ++i) {
                    sum += Integer.parseInt(String.valueOf(idCardLength.charAt(i))) * weight[i];
                }

                mode = sum % 11;
                return v == validate[mode];
            }
        } else {
            return false;
        }
    }

}
