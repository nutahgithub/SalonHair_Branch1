package com.hairteen.hung.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class ValidatorUtils {

    /**
     * Validate date.
     * 
     * @param strDate
     * @return
     */
    public static boolean validateJavaDate(String strDate)
    {
     /* Check if date is 'null' */
     if (strDate.trim().equals(""))
     {
         return true;
     }
     /* Date is not 'null' */
     else
     {
         /*
          * Set preferred date format,
          * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
         SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
         sdfrmt.setLenient(false);
         /* Create Date object
          * parse the string into date 
              */
         try
         {
             sdfrmt.parse(strDate); 
             System.out.println(strDate+" is valid date format");
         }
         /* Date format is invalid */
         catch (ParseException e)
         {
             System.out.println(strDate+" is Invalid Date format");
             return false;
         }
         /* Return true if date format is valid */
         return true;
     }
    }

    /**
     * Validate phone number.
     * 
     * @param strPhone
     * @return
     */
    public static boolean validatePhoneNumber(String strPhone) {
        Pattern pattern = Pattern.compile(ConstantDefine.PHONE_NUMBER_PATTERN);
        return pattern.matcher(strPhone).matches();
    }

    /**
     * Validate number.
     * 
     * @param strNumber
     * @return
     */
    public static boolean validateNumberPlus(String strNumber) {
        try {
            Float number = Float.parseFloat(strNumber);
            if (number < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
