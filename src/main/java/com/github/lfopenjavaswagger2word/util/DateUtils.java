package com.github.lfopenjavaswagger2word.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/18 10:39
 * @Description :
 */
public class DateUtils {

    /**
     * 获取当前时间
     * @param fmt 时间格式：例如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String now(String fmt) {
        SimpleDateFormat dateFormat = null;
        if (fmt == null || fmt.equals("")) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        Date date = new Date();
        String format = dateFormat.format(date);
        return format;
    }

}
