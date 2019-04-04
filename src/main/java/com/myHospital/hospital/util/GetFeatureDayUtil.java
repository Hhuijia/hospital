package com.myHospital.hospital.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author QUEENEY
 * @date 2019/4/1
 */
public class GetFeatureDayUtil {

    public static String getFeatureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-EEE");
        return sdf.format(today);
    }
}
