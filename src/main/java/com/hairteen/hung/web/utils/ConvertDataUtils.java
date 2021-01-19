package com.hairteen.hung.web.utils;

public class ConvertDataUtils {

    /**
     * Convert String to number
     * 
     * @param string
     * @return Integer
     */
    public static Integer convertStringToNumber(String string) {
        Integer number;
        try {
            number = Integer.parseInt(string);
        } catch(NumberFormatException e) {
            number = null;
        }
        return number;
    }
}
