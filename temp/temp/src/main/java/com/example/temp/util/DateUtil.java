package com.example.temp.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 时间工具类
 *
 * @author monkey king
 * @date 2019-12-24 19:38:29
 */
@Slf4j
public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String MM月DD日 = "MM月dd日";
    public static final String YYYYMM = "yyyyMM";
    public static final String HH_MM = "HH:mm";
    public static final String MM_DD = "MM-dd";

    public static final String YYYY年MM月DD日HH_MM_SS = "yyyy年MM月dd日 HH:mm:ss";

    public static final String YYYY年MM月 = "yyyy年MM月";

    public static final String YYYYMMDDHH_MM_SS = "yyyy.MM.dd HH:mm:ss";
    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();


    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static Date localDateTimeToDate(LocalDateTime date) {
        ZonedDateTime zonedDateTime = date.atZone(ZoneId.systemDefault());
        Instant instant2 = zonedDateTime.toInstant();
        return Date.from(instant2);
    }

    public static Date subtractTime(Date date, int amount) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strTime = sdf.format(date.getTime() + amount);
            Date time = sdf.parse(strTime);
            return time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    System.out.println("put new sdf of pattern " + pattern + " to map");

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    /**
     * 使用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     * 如果新的线程中没有SimpleDateFormat，才会new一个
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    /**
     * 获取日期0点
     *
     * @param date
     * @return
     */
    public static String formatDaySt(Date date) {
        String dateNow = DateUtil.format(date, YYYY_MM_DD) + " 00:00:00";
        return dateNow;
    }

    /**
     * 获取日期23点59分59秒
     *
     * @param date
     * @return
     */
    public static String formatDayEnd(Date date) {
        String dateNow = DateUtil.format(date, YYYY_MM_DD) + " 23:59:59";
        return dateNow;
    }

    /**
     * 默认: yyyy-MM-dd HH:mm:ss 格式;
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return getSdf(YYYY_MM_DD_HH_MM_SS).format(date);
    }

    /**
     * 用于微信支付接口参数<br/>
     * YYYY-MM-DDTHH:mm:ss+TIMEZONE 格式;<br/>
     * 2021-03-21T22:03:34+08:00
     *
     * @param date
     * @return
     */
    public static String formatTimezone(Date date) {
        String createTime = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
        return createTime.replaceAll(" ", "T") + "+08:00";
    }

    /**
     * 默认: yyyy-MM-dd格式;
     *
     * @param date
     * @return
     */
    public static String formatShort(Date date) {
        return getSdf(YYYY_MM_DD).format(date);
    }

    /**
     * 默认: MM-dd格式;
     *
     * @param date
     * @return
     */
    public static String formatMonthShort(Date date) {
        return getSdf(MM月DD日).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }

    /**
     * 默认: yyyy-MM-dd HH:mm:ss 格式;
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr) throws ParseException {
        return getSdf(YYYY_MM_DD_HH_MM_SS).parse(dateStr);
    }

    /**
     * 默认: yyyy-MM-dd HH:mm:ss 格式;
     *
     * @param millis
     * @return
     */
    public static Date parse(long millis) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(millis);
        return instance.getTime();
    }

    /**
     * 默认: yyyy-MM-dd HH:mm 格式;
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseHHMM(String dateStr) throws ParseException {
        return getSdf(YYYY_MM_DD_HH_MM).parse(dateStr);
    }


    /**
     * 默认: yyyy-MM-dd 格式;
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseYYYMM(String dateStr) {
        Date parse = null;
        try {
            parse = getSdf(YYYY_MM_DD).parse(dateStr);
        } catch (Exception e) {
            log.info("需要格式化的日期:{}", dateStr);
            e.printStackTrace();
        }
        return parse;
    }


    /**
     * 格式化日期
     *
     * @param str
     * @return
     */
    public static Date formatYYYMM(String str) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 默认: yyyy-MM-dd HH 格式;
     *
     * @param dateStr
     * @return
     */
    public static Date parseHHMMDDHH(String dateStr) {
        Date parse = null;
        try {
            parse = getSdf(YYYY_MM_DD_HH).parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 用于微信支付接口参数<br/>
     * YYYY-MM-DDTHH:mm:ss+TIMEZONE 格式;<br/>
     * 2021-03-21T22:03:34+08:00
     *
     * @return
     */
    public static Date parseTimezone(String dateStr) throws ParseException {
        dateStr = dateStr.replaceAll("T", " ").replaceAll("\\+08:00", "");
        return parse(dateStr);
    }

    /**
     * 将年月日的短时间格式,默认加上时分秒; 00:00:00<br/>
     * 默认: yyyy-MM-dd HH:mm:ss 格式;
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseDefaultSt(String dateStr) throws ParseException {
        return getSdf(YYYY_MM_DD_HH_MM_SS).parse(dateStr + " 00:00:00");
    }

    /**
     * 将年月日的短时间格式,默认加上时分秒; 23:59:59<br/>
     * 默认: yyyy-MM-dd HH:mm:ss 格式;
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseDefaultEt(String dateStr) throws ParseException {
        return getSdf(YYYY_MM_DD_HH_MM_SS).parse(dateStr + " 23:59:59");
    }

    /**
     * 默认: yyyy-MM-dd格式;
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseShort(String dateStr) throws ParseException {
        return getSdf(YYYY_MM_DD).parse(dateStr);
    }


    /**
     * 返回当前日期的 格式为： format(yyyyMMdd)
     *
     * @return 日期格式
     */
    public static String getCurrentDate() {
        Date now = new Date();
        return getDateFormat(YYYY_MM_DD).format(now);
    }


    /**
     * 返回当前日期的 格式为： format(yyyyMMdd)
     *
     * @return 日期格式
     */
    public static Date getCurrentDates() throws ParseException {
        Date now = new Date();
        return parseShort(getDateFormat(YYYY_MM_DD).format(now));
    }

    /**
     * 根据相应的格式初始化日期格式对象
     *
     * @param pattern 格式
     * @return DateFormat
     */
    private static DateFormat getDateFormat(final String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 向一个老日期追加分钟,返回一个新日期;
     *
     * @param oldDate 被追加天数的日期;
     * @param minute  追加的分钟;
     * @return 返回一个日历对象, 追加天数后的日期;通过Calendar.getTime()可获得;
     */
    public static Calendar addMinuteFromOldDate(Date oldDate, int minute) {
        // 添加结束时间;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.MINUTE, minute);
        return calendar;
    }

    /**
     * 向一个老日期追加小时,返回一个新日期;
     *
     * @param oldDate 被追加天数的日期;
     * @param hour    追加的小时;
     * @return 返回一个日历对象, 追加天数后的日期;通过Calendar.getTime()可获得;
     */
    public static Calendar addHourFromOldDate(Date oldDate, int hour) {
        // 添加结束时间;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar;
    }

    /**
     * 向一个老日期追加天数,返回一个新日期;
     *
     * @param oldDate 被追加天数的日期;
     * @param days    追加的天数;
     * @return 返回一个日历对象, 追加天数后的日期;通过Calendar.getTime()可获得;
     */
    public static Calendar addDaysFromOldDate(Date oldDate, int days) {
        // 添加结束时间;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DATE, days);
        return calendar;
    }

    /**
     * 向一个老日期追加月份,返回一个新日期;
     *
     * @param oldDate 被追加月份的日期;
     * @param months  追加的月份;
     * @return 返回一个日历对象, 追加月份后的日期;通过Calendar.getTime()可获得;
     */
    public static Calendar addMonthsFromOldDate(Date oldDate, int months) {
        // 添加结束时间;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.MONTH, months);
        return calendar;
    }

    /**
     * 获取两个时间相差的天数,包含开始时间,包含结束时间;只取日期; <br>
     * <b>算法为: 天数 = (结算日-开始日)+1</b><br>
     * 如:2014-08-01 12:00:00 到 2014-08-01 12:00:01;也算一天;<br>
     * 2014-08-01 12:00:00 到 2014-08-02 23:59:59; 就算两天;<br>
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDifferDays(Date startTime, Date endTime) {
        int days = 0;
        try {
            SimpleDateFormat fDate = new SimpleDateFormat(YYYY_MM_DD);
            startTime = fDate.parse(fDate.format(startTime));
            endTime = fDate.parse(fDate.format(endTime));
            while (endTime.compareTo(startTime) >= 0) {
                Calendar ca = Calendar.getInstance();
                ca.setTime(startTime);
                ca.add(Calendar.DATE, 1);
                days++;
                startTime = fDate.parse(fDate.format(ca.getTime()));
            }
        } catch (ParseException e) {
            log.error("获取时间相差天数异常: ", e);
        }
        return days;
    }


    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date getFirstDayOfCurrentMonth() {
        // 获取当月第一天
        Date firstDay;
        // 获取前月的第一天
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        firstDay = cal.getTime();

        return firstDay;
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static Date getLastDayOfCurrentMonth() {
        // 获取当月最后一天
        Date lastDay;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        lastDay = cal.getTime();

        return lastDay;
    }


    public static Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * 计算两个时间之间间隔的天数
     *
     * @param timeEnd
     * @param timeStart
     * @return
     */
    public static int calIntervalDays(Date timeEnd, Date timeStart) {
        int intervalDays = (int) ((timeEnd.getTime() - timeStart.getTime()) / (1000 * 3600 * 24));
        return intervalDays;
    }

    /**
     * 获取指定时间所在天的第一秒，以"00:00:00"结尾
     *
     * @param date
     * @return
     */
    public static Date getStartTimeOfDay(Date date) throws ParseException {
        String dateStr = DateUtil.formatShort(date) + " 00:00:00";
        return DateUtil.parse(dateStr);
    }

    /**
     * 获取指定时间所在天的最后一秒，以"23:59:59"结尾
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfDay(Date date) throws ParseException {
        String dateStr = DateUtil.formatShort(date) + " 23:59:59";
        return DateUtil.parse(dateStr);
    }

    /**
     * 获取改时间的上个月的第一天和上个月的最后一天<br/>
     * 通过startDate和endDate这两个key来获取map的值
     *
     * @param date
     * @return
     */
    public static Map<String, Date> getLastMonthFirstDayAndEndDay(Date date) {

        Map<String, Date> map = new HashMap<>(16);

        if (null == date) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //上个月的1号
        map.put("startDate", cal.getTime());
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        //上个月的最后一天;
        map.put("endDate", cal.getTime());
        return map;
    }

    public static String getYyyyMmDd(String month) {
        Scanner input = new Scanner(System.in);
        int year = getYear(new Date());
        System.out.print("Enter month between 1 and 12: ");
        return year + "-" + month + "-" + "01";
    }

    /**
     * 获取当前时间的上个月的第一天和上个月的最后一天<br/>
     * default new Date();<br/>
     * 通过startDate和endDate这两个key来获取map的值
     *
     * @return
     */
    public static Map<String, Date> getLastMonthFirstDayAndEndDay() {
        return getLastMonthFirstDayAndEndDay(new Date());
    }

    /**
     * 获取今天0点
     *
     * @return
     */
    public static Date getTodayTime() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 比较两个时间范围是否在合理的月份之内;<br/>
     * 比如,两个时间相差是否超过3个月
     *
     * @param month
     * @param st
     * @param et
     * @return
     */
    public static boolean isBetweenDate(int month, Date st, Date et) {
        try {
            //把时间转化成统一格式;
            st = DateUtil.parse(DateUtil.format(st));
            et = DateUtil.parse(DateUtil.format(et));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(st);
            calendar.add(Calendar.MONTH, month);
            Date newDate = calendar.getTime();
            return newDate.after(et);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }

    public static Date parse(Date date) throws ParseException {
        String format = format(date, "yyyy-MM-dd HH:mm:ss");
        Date parse = parse(format);
        return parse;

    }

    /**
     * 获取每天零点时间
     *
     * @return
     */
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 获取两个日期之间天数
     *
     * @param begin_date
     * @param end_date
     * @return
     * @throws ParseException
     */
    public static int getInterval(Date begin_date, Date end_date) throws ParseException {
        Long day = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (begin_date != null) {
            String begin = sdf.format(begin_date);
            begin_date = sdf.parse(begin);
        } else {
            begin_date = sdf.parse(sdf.format(new Date()));
        }
        if (end_date != null) {
            String end = sdf.format(end_date);
            end_date = sdf.parse(end);
        } else {
            end_date = sdf.parse(sdf.format(new Date()));
        }
        day = (end_date.getTime() - begin_date.getTime()) / (24 * 60 * 60 * 1000);
        return day >= 0 ? day.intValue() : -day.intValue();
    }


    public static String parsTimeCn(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        String formatDate = "";
        try {
            Date d = sdf.parse(date);
            formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatDate;
    }

    /**
     * 日期格式不足10补0
     *
     * @param dateStr
     * @return
     */
    public static String fillDateWillZero(String dateStr) {
        int start = dateStr.indexOf("-");
        int end = dateStr.lastIndexOf("-");
        String month = dateStr.substring(start + 1, end);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = dateStr.substring(end + 1);
        if (day.length() == 1) {
            day = "0" + day;
        }
        return dateStr.substring(0, start + 1) + month + "-" + day;
    }


    /**
     * 比较两个时间的大小
     *
     * @param dateStr
     * @return
     */

    public static boolean compareTo(String dateStr) {
        try {
            Date date = new Date();
            return date.compareTo(DateUtil.parse(dateStr + ":00")) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取某天是几号
     *
     * @param day 几天前/后 今天之前为负数。今天之后为正数
     * @return
     */
    public static Integer getDay(Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据当前日期查询是星期几
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(date);
    }


    /**
     * 获取上个月最后一天的时间
     *
     * @return
     */
    public static String lastDayOfThisMonth() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        LocalDate lastDay = LocalDate.now().minusMonths(1L).with(TemporalAdjusters.lastDayOfMonth());
        return lastDay.format(df);
    }


    /**
     * 在一个指定日期上增加月份 并且获取月份的第一天日期
     *
     * @param oldDate 时间
     * @param months  几个月
     * @return
     */
    public static Date addMonthsFrom(Date oldDate, int months) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.setTime(oldDate);
        cal_1.add(Calendar.MONTH, months);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        System.out.println(firstDay);
        return cal_1.getTime();
    }

    public static void main(String[] args) {
        /*try {
            Date date = parse("2021-01-01", YYYY_MM_DD);
            Date date2 = parse("2021-12-31", YYYY_MM_DD);

            System.out.println("new1: " + getDifferDays(date, date2));
            System.out.println("new2: " + calIntervalDays(date2, date));
            Date da = getLastDayOfCurrentMonth();
            Boolean result = DateUtil.getTodayTime().equals(getTodayTime());
            System.out.println(result);
            //System.out.println(format(date, "yyyy-MM-dd HH:mm:ss"));
            System.out.println(parseTimezone("2021-03-21T22:03:34+08:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("dddd" + formatDayEnd(new Date()));*/

        //System.out.println(format(addMonthsFrom(new Date(),1)));
        //System.out.println(getWeek(new Date()));
        System.out.println(addMonthsFrom(new Date(), 1));
    }
}