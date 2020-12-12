package com.hairteen.hung.web.utils;

/**
 * String common Project.
 * 
 * @author NguyenThanh
 *
 */
public class StringUtil {

    /**
     * Check null or empty.
     * 
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }
}
