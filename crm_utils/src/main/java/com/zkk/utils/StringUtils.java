package com.zkk.utils;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/16 14:53
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
