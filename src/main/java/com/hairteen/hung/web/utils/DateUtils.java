package com.hairteen.hung.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    
    public static String getFirstDateOfMonth(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        Date firstDayOfMonth = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat(pattern); 
        String strDate = sdf.format(firstDayOfMonth);
        return strDate;
    }

    public static String getLastDateOfMonth(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date lastDayOfMonth = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat(pattern); 
        String strDate = sdf.format(lastDayOfMonth);
        return strDate;
    }
    
    public static Date convertStringToDate(String date, String pattern) {
        
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
