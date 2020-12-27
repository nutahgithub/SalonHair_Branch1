package com.hairteen.hung.web.utils;

public class ConvertDataUtils {

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
