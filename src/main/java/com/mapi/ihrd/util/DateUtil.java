package com.mapi.ihrd.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date now() {
        return Calendar.getInstance().getTime();
    }

}
