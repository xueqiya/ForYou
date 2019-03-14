package com.yiya.foryou.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/3/13	14:04
 * description:
 */
public class DateUtils {
    public static String nowData() {
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
