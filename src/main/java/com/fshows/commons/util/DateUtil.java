package com.fshows.commons.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类DateUtil.java的实现描述：TODO 类实现描述
 *
 * @author zhh 2015年4月21日 下午9:00:57
 */
public class DateUtil {

    private static final Log log = LogFactory.getLog(DateUtil.class);

    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 把字符串转换为日期
     *
     * @param dateStr
     * @return
     */
    public static Date toDate(String dateStr, String dateFormat) {
        String str = dateStr.trim();

        if (StringUtils.isEmpty(dateFormat)) {
            if (str.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
                dateFormat = "yyyy-MM-dd HH:mm:ss";
            } else if (str.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                dateFormat = "yyyy-MM-dd";
            }
        }

        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            log.error("format to date err:" + e.getMessage(), e);
        }

        return null;
    }

    public static Date toDate(String dateStr) {
        return toDate(dateStr, null);
    }

    public static Date toDate(long m) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(m);
        return c1.getTime();
    }

    /**
     * 清除时间
     *
     * @param date
     * @return
     */
    public static void clearTimes(Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 精确计算(忽略时间)两个日期相差的天数。 如: 2007-1-5 与 2007-1-6 返回1 2007-1-5 与 2007-1-4 返回-1 2007-1-5 与 2007-1-6 返回0
     *
     * @param startDay 开始日期
     * @param endDay   结束日期
     * @return 相差的天数
     */
    public static int getBetweenDays(Calendar startDay, Calendar endDay) {
        Calendar date1 = (Calendar) startDay.clone();
        Calendar date2 = (Calendar) endDay.clone();

        // 不比较时分秒信息
        clearTimes(date1);
        clearTimes(date2);

        if (date1.equals(date2))
            return 0;

        long m1 = date1.getTimeInMillis();
        long m2 = date2.getTimeInMillis();

        return (int) ((m2 - m1) / 24 / 60 / 60 / 1000);
    }

    /**
     * 精确计算(忽略时间)两个日期相差的天数。 如: 2007-1-5 与 2007-1-6 返回1 2007-1-5 与 2007-1-4 返回-1 2007-1-5 与 2007-1-5 返回0
     *
     * @param startDay 开始日期
     * @param endDay   结束日期
     * @return 相差的天数
     */
    public static int getBetweenDays(Date startDay, Date endDay) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDay);
        c2.setTime(endDay);
        return getBetweenDays(c1, c2);
    }

    /**
     * Format Date into which format you define
     *
     * @param date(java.util.Date)
     * @return String example formatDate(date, "MMMM dd, yyyy") = July 20, 2000
     */
    public static String formatDate(Date date, String newFormat) {
        if ((date == null) || (newFormat == null)) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(newFormat);

        return formatter.format(date);
    }

    /**
     * 取得剩余时间
     *
     * @param date
     * @return
     */
    public static String getRemainTime(Date date) {
        if (date == null)
            return "";

        StringBuilder sb = new StringBuilder();

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.setTime(date);
        long m1 = start.getTimeInMillis();
        long m2 = end.getTimeInMillis();
        long remain = m2 - m1;

        // 天
        long day = remain / 24 / 60 / 60 / 1000;
        if (day > 0)
            sb.append(day + "天");

        // 小时
        remain -= (day * 24 * 60 * 60 * 1000);
        long hour = remain / 60 / 60 / 1000;
        if (hour > 0 || sb.length() > 0)
            sb.append(hour + "时");

        // 分
        remain -= (hour * 60 * 60 * 1000);
        long minute = remain / 60 / 1000;
        if (minute > 0 || sb.length() > 0)
            sb.append(minute + "分");

        return sb.toString();
    }

    /**
     * 获取当前时间一小时后的时间
     */
    public static Date getOneHourAfterTime() {
        Calendar clender = Calendar.getInstance();
        clender.add(Calendar.HOUR_OF_DAY, 1);
        return clender.getTime();
    }

    /**
     * 获取当前日期，没有时分秒的
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar c = Calendar.getInstance();
        clearTimes(c);
        return c.getTime();
    }

    /**
     * 两个时间比较;d1>d2?true:false 出现异常就返回false
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean dateCompare(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        try {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(d1);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(d2);

            long p1 = c1.getTimeInMillis();
            long p2 = c2.getTimeInMillis();
            if (p1 > p2)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 获得当前时间的全字符串yyyyMMddhhmmss
     *
     * @return
     */
    public static String getNowDateTimeStr() {

        return DateTime.now().toString("yyyyMMddHHmmss");
    }

    /**
     * 获得当前时间的字符串yyyyMMdd
     *
     * @return
     */
    public static String getNowDateStr() {

        return DateTime.now().toString("yyyyMMdd");
    }

    /**
     * 获得当前时间的int类型yyyyMMdd
     *
     * @return
     */
    public static int getNowDate() {

        return Integer.valueOf(DateTime.now().toString("yyyyMMdd"));
    }

    /**
     * 获得当前时间的字符串yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowDateFormat() {

        return DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获得当前时间的字符串自定义转换类型
     *
     * @return
     */
    public static String getNowDateFormat(String format) {

        return DateTime.now().toString(format);
    }

    /**
     * 获得当前时间戳
     *
     * @return
     */
    public static long getNow() {

        return DateTime.now().getMillis();
    }

    /**
     * 获取当前时间附近小时的时间
     */
    public static Date getTimeByHours(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    /**
     * 传入的日期是否在3个月内不包括今天
     *
     * @param createDay
     * @return
     */
    public static boolean isScopeByThreeMonth(Integer createDay) {

        Integer today = Integer.valueOf(DateTime.now().toString("yyyyMMdd"));
        Integer threeMonth = Integer.valueOf(DateTime.now().plusMonths(-3).toString("yyyyMMdd"));

        return createDay >= threeMonth && createDay < today;
    }

    /**
     * 传入的日期是否在3个月内包括今天
     *
     * @param createDay
     * @return
     */
    public static boolean isScopeByThreeMonthAndToday(Integer createDay) {

        Integer today = Integer.valueOf(DateTime.now().toString("yyyyMMdd"));
        Integer threeMonth = Integer.valueOf(DateTime.now().plusMonths(-3).toString("yyyyMMdd"));

        return createDay >= threeMonth && createDay <= today;
    }

    public static long getWxPayTime(String timeEnd) {

        Long payTime = DateUtil.getNow();

        if (StringUtils.isNotBlank(timeEnd)) {
            Date date = DateUtil.toDate(timeEnd, "yyyyMMddHHmmss");
            payTime = date.getTime();
        }

        return payTime;
    }

    /**
     * 获得微信支付完成时间，用来防止日切
     *
     * @return
     */
    public static Date getWxPayAcrossDayTime() {

        DateTime now = DateTime.now();
        DateTime endTime =
            new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 54, 59);

        // 如果结束时间在当前时间后
        if (endTime.isBeforeNow()) {
            endTime = now.plusMinutes(5);
        }
        return endTime.toDate();
    }

    /**
     * 获得翼支付完成时间，用来防止日切
     *
     * @return
     */
    public static String getBestpayAcrossDayTime(long time) {

        DateTime now = new DateTime(time);
        DateTime endTime = new DateTime(
                now.getYear(),
                now.getMonthOfYear(),
                now.getDayOfMonth(),
                23,
                44,
                58
        );

        // 如果结束时间在当前时间后
        if (endTime.isAfter(now)) {
            endTime = now.plusMinutes(15);
        } else {
            endTime = endTime.plusMinutes(15);
        }
        return endTime.toString("yyyyMMddHHmmss");
    }

    /**
     * 把时间字符串转时间戳
     *
     * @param time
     * @return
     */
    public static long formatMillis(String time, String formatType) {
        DateFormat format = new SimpleDateFormat(formatType);
        Date date;
        try {
            date = format.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            return 0L;
        }
    }

    /**
     * 获得特定时间的时间戳
     *
     * @return
     */
    public static long getMillisByString(String time, String forMate) {
        return DateTimeFormat.forPattern(forMate).parseDateTime(time).getMillis();
    }

    /**
     * 获得特定时间戳的格式化字符串
     *
     * @return
     */
    public static String getStringByMillis(long millis, String formatType) {

        return new DateTime(millis).toString(formatType);
    }

    /**
     * Map: 开始时间 key = startTime  结束时间 key = endTime
     * 获得当天的开始与结束时间
     *
     * @return
     */
    public static Map<String, Long> getCurrentStartAndEnd() {

        return getStartAndEndByDay(DateTime.now().toString("yyyyMMdd"));
    }

    /**
     * Map: 开始时间 key = startTime  结束时间 key = endTime
     * @return
     */
    public static Map<String, Long> getYesterdayStartAndEnd(){

        return getStartAndEndByDay(DateTime.now().plusDays(-1).toString("yyyyMMdd"));
    }

    /**
     * Map: 开始时间 key = startTime  结束时间 key = endTime
     * 获得指定时间的开始与结束时间
     * @param day
     * @return
     */
    public static Map<String, Long> getStartAndEndByDay(String day) {

        DateTime dayTime = DateTime.parse(day, DateTimeFormat.forPattern("yyyyMMdd"));
        // 获得昨天开始与结束时间
        long startTime = dayTime.withTimeAtStartOfDay().getMillis();
        long endTime = dayTime.plusDays(1).withTimeAtStartOfDay().getMillis();

        Map<String, Long> params = Maps.newHashMap();
        params.put("startTime", startTime);
        params.put("endTime", endTime);

        return params;
    }

    /**
     * 根据订单号获得订单日日期
     * @param orderSn
     * @return
     */
    public static Date getDateByOrderSn(String orderSn){

        String date = orderSn.substring(0, 8);
        DateTime dateTime = DateTime.parse(date, DateTimeFormat.forPattern("yyyyMMdd"));
        return dateTime.toDate();
    }

    public static void main(String[] args) {
        System.out.println(getStartAndEndByDay("20161025"));
        System.out.println("---------");
        System.out.println(getCurrentStartAndEnd());
        System.err.println(getMillisByString("1970-01-01 08:00:00", "yyyy-MM-dd HH:mm:ss"));
        System.err.println(getBestpayAcrossDayTime(1484192267000L));

    }

    /**
     * 20170529   转为    1495987200
     * @param day
     * @return
     * @throws ParseException
     */
    public static Long getTimestampByday(Integer day) throws ParseException {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        Date date = s.parse(String.valueOf(day));
        long l = date.getTime() / 1000;
        return l;
    }


    public static List<String> getRangeDateStrList(Date startDate, Date endDate)
            throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        Long tmp = 24 * 60 * 60 * 1000L;
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / tmp; // 相隔天数

        List<String> targetList = new ArrayList<String>();
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(startDate);
        targetList.add(df.format(startDate));

        for (int i = 1; i <= step; i++) {
            java.util.Date nextDate = new Date(dateList.get(i - 1).getTime() + tmp);
            dateList.add(nextDate);
            targetList.add(df.format(nextDate));
        }
        return targetList;
    }
}
