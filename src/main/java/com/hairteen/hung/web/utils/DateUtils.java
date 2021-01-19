package com.hairteen.hung.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * Format date to HH:mm
     * 
     * @param date
     * @return
     */
    public static String formatDateToHM(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String strDate = formatter.format(date);
        return strDate;
    }
}
