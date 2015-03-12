package com.angel.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.angel.services.beans.ErrorEntityBean;
import com.angel.services.beans.SuccessServiceBean;

public class ServiceUtil {

    public static ErrorEntityBean getError(String code, String message) {
        ErrorEntityBean error = new ErrorEntityBean();
        error.setCode(code);
        error.setMessage(message);
        error.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        return error;
    }

    public static SuccessServiceBean getSuccess(String code, String message) {
        SuccessServiceBean success = new SuccessServiceBean();
        success.setCode(code);
        success.setMessage(message);
        return success;
    }

    public static String getSqlToStr(Timestamp dateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date date = null;
        // String str=String.valueOf(dateTime);
        // date = formatter.parse(str);
        SimpleDateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
        String file_date = FORMATTER.format(dateTime);
        return file_date;
    }

}
