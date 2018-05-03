package com.ibm.oms.service.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil {
    public static final String FORMAT_LONGDATETIME = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_NORMALDATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_SHORTDATETIME = "yyyy-MM-dd";
    public static final String FORMAT_LONGDATETIME_COMPACT = "yyyyMMddHHmmssSSS";
    public static final String FORMAT_SHORTDATETIME_COMPACT = "yyyyMMdd";

    private DateUtil() {
    }

    // 当天的凌晨时间 如：2009-08-08 00:00:00 000
    public static Timestamp getWeeHoursDateTime() {
        Calendar begin = Calendar.getInstance();
        begin.setTimeInMillis(System.currentTimeMillis());
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        Timestamp time = new Timestamp(begin.getTimeInMillis());
        return time;
    }

    public static java.sql.Timestamp getNowDateTime() {
        java.sql.Timestamp curDateTime = getMasterDateTime();
        return curDateTime;
    }

    public static java.util.Date getNowDate() {
        java.util.Date curDateTime = getMasterDateTime();
        return curDateTime;
    }

    public static java.sql.Timestamp nowDateTimeChange(int oFlag, int amount) {
        java.sql.Timestamp curDateTime = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getMasterDateTime());
            cal.add(oFlag, amount);
            curDateTime = new java.sql.Timestamp(cal.getTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;
    }

    public static java.sql.Timestamp nowDateTimeChange(java.sql.Timestamp nowDateTime, int oFlag, int amount) {
        java.sql.Timestamp curDateTime = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(nowDateTime);
            cal.add(oFlag, amount);
            curDateTime = new java.sql.Timestamp(cal.getTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;
    }

    public static java.util.Date nowDateChange(int oFlag, int amount) {
        java.util.Date curDateTime = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getMasterDateTime());
            cal.add(oFlag, amount);
            curDateTime = new java.util.Date(cal.getTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;
    }

    public static java.util.Date nowDateChange(java.util.Date nowDate, int oFlag, int amount) {
        java.util.Date curDateTime = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(nowDate);
            cal.add(oFlag, amount);
            curDateTime = new java.util.Date(cal.getTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;
    }

    /**
     * 取得上一个月的第一天
     * 
     * @return 上一个月的第一天
     */
    public static Timestamp lastMothFirstDateTime() {
        Timestamp lastMothFirstDateTime = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getMasterDateTime());
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
            StringBuilder dateBuilder = new StringBuilder();
            dateBuilder.append(cal.get(Calendar.YEAR)).append("-").append(cal.get(Calendar.MONTH) + 1)
                    .append("-01 00:00:00");
            lastMothFirstDateTime = DateUtil.getDateTimeFormatByString(dateBuilder.toString(),
                    DateUtil.FORMAT_LONGDATETIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastMothFirstDateTime;
    }

    public static int compareDateTime(java.util.Date firstDateTime, java.util.Date secondDateTime) {
        int rlt = 0;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(firstDateTime);
            long l1 = cal.getTimeInMillis();
            cal.setTime(secondDateTime);
            long l2 = cal.getTimeInMillis();
            long rr = l1 - l2;
            if (rr == 0) {
                return 0;
            }
            if (rr < 0) {
                return -1;
            }
            if (rr > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rlt;
    }

    private static java.sql.Timestamp formatDateTime(String argDate, String fFlag, Locale locale) {
        java.sql.Timestamp tt = null;
        try {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag, locale);
            java.util.Date date = new java.util.Date(argDate);
            String s = objSDF.format(date);
            int lng = s.trim().length();
            objSDF.applyPattern(fFlag.trim().substring(0, lng));
            tt = new java.sql.Timestamp(objSDF.parse(s).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tt;

    }

    public static java.sql.Timestamp getDateTimeFormatByString(String argDate, String fFlag) {
        java.sql.Timestamp tt = null;
        try {
            int lng = argDate.trim().length();
            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag);
            objSDF.applyPattern(fFlag.trim().substring(0, lng));
            tt = new java.sql.Timestamp(objSDF.parse(argDate).getTime());
        } catch (Exception e) {
            try {
                tt = formatDateTime(argDate, fFlag, null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return tt;
    }

    public static void main(String[] args) {
        System.out.println(getDateBefore(new Date(), 1));
        System.out.println("1/4/2013".indexOf("/"));
//        System.out.println(DateUtil.getDateFormatByString("2013-8-6", "31", "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(DateUtil.getDateFormatByString("04/07/2013", "3", "yyyy-MM-dd HH:mm:ss"));
    }

    public static java.sql.Timestamp getDateTimeFormatByString(String argDate, String fFlag, Locale locale) {
        java.sql.Timestamp tt = null;
        try {
            int lng = argDate.trim().length();
            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag, locale);
            objSDF.applyPattern(fFlag.trim().substring(0, lng));
            tt = new java.sql.Timestamp(objSDF.parse(argDate).getTime());
        } catch (Exception e) {
            try {
                tt = formatDateTime(argDate, fFlag, locale);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return tt;
    }

    public static java.util.Date getDateFormatByString(String argDate, String fFlag) {
        java.util.Date tt = null;
        try {
            int lng = argDate.trim().length();
            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag);
            objSDF.applyPattern(fFlag.trim().substring(0, lng));
            tt = new java.util.Date(objSDF.parse(argDate).getTime());
        } catch (Exception e) {
            try {
                tt = formatDateTime(argDate, fFlag, null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return tt;
    }

//    public static java.util.Date getDateFormatByString2(String argDate) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
//        try {
//            if (argDate != null && argDate.length() <= 10)
//                argDate += " 00:00:00";
//            date = sdf.parse(argDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }

//    public synchronized static java.util.Date getDateFormatByString(String argDate, String type, String fFlag) {
//        java.util.Date tt = null;
//        try {
//            if (StringUtils.isBlank(type)) {
//                type = "3";
//            }
//            if (StringUtils.isNotBlank(type) && argDate.indexOf("/") >= 0) {
//
//                if (type.indexOf("/") >= 0) {
//                    String[] ind = type.split("/");
//                    String[] datestr = argDate.split("/");
//                    String y = datestr[Integer.valueOf(ind[0])];
//                    String m = datestr[Integer.valueOf(ind[1])];
//                    String d = datestr[Integer.valueOf(ind[2])];
//                    if (m.length() == 1)
//                        m = "0" + m;
//                    if (d.length() == 1)
//                        d = "0" + d;
//                    argDate = y + "-" + m + "-" + d;
//                    return getDateFormatByString2(argDate);
//                } else if (type.equals("1")) {
//                    String[] datestr = argDate.split("/");
//                    argDate = datestr[1] + "/" + datestr[0] + "/" + datestr[2];
//                } else if (type.equals("2")) {
//                    String[] ind = "2/1/0".split("/");
//                    String[] datestr = argDate.split("/");
//                    String y = datestr[Integer.valueOf(ind[0])];
//                    String m = datestr[Integer.valueOf(ind[1])];
//                    String d = datestr[Integer.valueOf(ind[2])];
//                    if (m.length() == 1)
//                        m = "0" + m;
//                    if (d.length() == 1)
//                        d = "0" + d;
//                    argDate = y + "-" + m + "-" + d;
//                } else if (type.equals("3")) {
//                    return getDateFormatByString3(argDate);
//                } else if (type.equals("31")) {
//                    return getDateFormatByString31(argDate);
//                }
//            } else if (argDate.indexOf("-") >= 0) {
//                String[] datestr = argDate.split("-");
//                String y = datestr[0];
//                String m = datestr[1];
//                String d = datestr[2];
//                if (y.length() == 2 && d.length() <= 2)
//                    y = "20" + y;
//                if (m.length() == 1)
//                    m = "0" + m;
//                if (d.length() == 1)
//                    d = "0" + d;
//                argDate = y + "-" + m + "-" + d;
//            }
//
//            int lng = argDate.trim().length();
//            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag);
//            objSDF.applyPattern(fFlag.trim().substring(0, lng));
//            tt = new java.util.Date(objSDF.parse(argDate).getTime());
//        } catch (Exception e) {
//            try {
//                tt = formatDateTime(argDate, fFlag, null);
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//        return tt;
//    }

//    public static java.util.Date getDateFormatByString3(String argDate) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = null;
//        try {
//            if (argDate != null && argDate.indexOf("/") == 4) {
//                date = sdf2.parse(argDate);
//            } else {
//                date = sdf.parse(argDate);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }

//    public static java.util.Date getDateFormatByString31(String argDate) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
//        Date date = null;
//        try {
//            if (argDate != null && argDate.indexOf("/") == 4) {
//                date = sdf2.parse(argDate);
//            } else {
//                date = sdf.parse(argDate);
//            }
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return date;
//    }

//    public static java.util.Date getDateFormatByStringException(String argDate, String fFlag) throws Exception {
//        java.util.Date tt = null;
//        try {
//            int lng = argDate.trim().length();
//            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag);
//            objSDF.applyPattern(fFlag.trim().substring(0, lng));
//            tt = new java.util.Date(objSDF.parse(argDate).getTime());
//        } catch (Exception e) {
//            try {
//                tt = formatDateTime(argDate, fFlag, null);
//            } catch (Exception e1) {
//                throw e1;
//            }
//            throw e;
//        }
//        return tt;
//    }

    public static java.util.Date getDateFormatByString(String argDate, String fFlag, Locale locale) {
        java.util.Date tt = null;
        try {
            int lng = argDate.trim().length();
            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag, locale);
            objSDF.applyPattern(fFlag.trim().substring(0, lng));
            tt = new java.util.Date(objSDF.parse(argDate).getTime());
        } catch (Exception e) {
            try {
                tt = formatDateTime(argDate, fFlag, locale);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return tt;
    }

    public static String getStringFormatByTimestamp(java.sql.Timestamp argDate, String fFlag) {
        String strDateTime = "";
        try {
            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag.trim());
            strDateTime = objSDF.format(argDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDateTime;
    }

    public static String getStringFormatByDate(java.util.Date argDate, String fFlag) {
        String strDateTime = "";
        try {
            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag.trim());
            strDateTime = objSDF.format(argDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDateTime;
    }

    public static java.sql.Timestamp getMasterDateTime() {
        java.sql.Timestamp value = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
        return value;
    }

    public static java.sql.Date getMasterDateTime2() {
        java.sql.Date value = new java.sql.Date(getMasterDateTime().getTime());
        return value;
    }

//    public static boolean judgeDate(String argDate) {
//        boolean tag = false;
//        try {
//            java.sql.Timestamp tt = getDateTimeFormatByString(argDate, FORMAT_LONGDATETIME);
//            tag = true;
//        } catch (Exception e) {
//            tag = false;
//        }
//        return tag;
//    }

    public static String[] getStringArrayFormatByString(String argDate, String regex) {
        // 0:年,1:月,2:日
        String result[] = argDate.split(regex);
        // 去零操作,如出现:2007-06-02
        for (int i = 0; i < result.length; i++) {
            if (result[i].startsWith("0")) {
                int idx = result[i].indexOf("0");
                result[i] = result[i].substring(idx);
            }
        }
        return result;
    }

    public static int[] getIntArrayFormatByString(String argDate, String regex) {
        String strResult[] = getStringArrayFormatByString(argDate, regex);
        int intResult[] = new int[3];
        for (int i = 0; i < intResult.length; i++) {
            intResult[i] = Integer.parseInt(strResult[i]);
        }
        return intResult;
    }

    /**
     * 取得当前数据库的系统时间函数
     * 
     * @return 数据时间函数
     * @throws Exception
     */
    public static String getNowFunc() {
        String func = null;
        try {
            func = "sysdate";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return func;
    }

    /**
     * 把字符串转换成日期，如: oracle 写法：to_date('2008-06-07 17:02:23','yyyy-MM-DD HH24:MI:SS') mysql 写法： timestamp('2008-06-07
     * 17:02:23')
     * 
     * @param date 日期字符串
     * @param format 日期格式
     * @return
     */
    public static String getToDateString(String date, String format) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" to_date('");
        sqlBuffer.append(date);
        sqlBuffer.append("','");
        sqlBuffer.append(format);
        sqlBuffer.append("') ");
        return sqlBuffer.toString();
    }

    /**
     * 把字符串转换成日期，如: oracle 写法：to_date('2008-06-07 17:02:23','yyyy-MM-DD HH24:MI:SS') mysql 写法： timestamp('2008-06-07
     * 17:02:23')
     * 
     * @param date 日期字符串
     * @param format 日期格式
     * @return
     */
    public static String getToDateString(Timestamp date, String format) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" to_date('");
        sqlBuffer.append(DateUtil.getStringFormatByTimestamp(date, format));
        sqlBuffer.append("','");
        sqlBuffer.append(format);
        sqlBuffer.append("') ");
        return sqlBuffer.toString();
    }

    /**
     * 获取天或月或者周的时间
     * 
     * @param selType 通过类型(curDay,curMonth,curWeek)
     * @return
     */
    public static List<Timestamp> getDayOrMonthOrWeek(String selType) {
        List<Timestamp> timestampList = new ArrayList<Timestamp>();
        // 日历对象
        Calendar calendar = Calendar.getInstance();
        // 暂时月或日或周的存贮
        String dymdStr = "";
        // 返回天或月的开始及结束时间
        String dmwFirstAndEndStr[] = new String[2];
        // 天的开始时
        String dayBeginTime = " 00:00:00";
        // 天的结束时
        String dayEndTime = " 23:59:59";
        // 月的开始时
        String monthBeginTime = "-01 00:00:00";
        // 按日查
        if (selType != null) {
            if (selType.trim().equalsIgnoreCase("curDay")) {
                StringBuffer sb = new StringBuffer(8);
                sb.append(calendar.get(Calendar.YEAR));
                sb.append("-");
                sb.append(one2TwoDigit((calendar.get(Calendar.MONTH) + 1)));
                sb.append("-");
                sb.append(one2TwoDigit(calendar.get(Calendar.DAY_OF_MONTH)));
                dymdStr = sb.toString();

                dmwFirstAndEndStr[0] = dymdStr + dayBeginTime;
                dmwFirstAndEndStr[1] = dymdStr + dayEndTime;
            }
            // 按月查
            if (selType.trim().equalsIgnoreCase("curMonth")) {
                StringBuffer sb = new StringBuffer(10);
                sb.append(calendar.get(Calendar.YEAR));
                sb.append("-");
                sb.append(one2TwoDigit((calendar.get(Calendar.MONTH) + 1)));
                dymdStr = sb.toString();

                calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
                calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
                int MaxDate = calendar.get(Calendar.DATE);

                dmwFirstAndEndStr[0] = dymdStr + monthBeginTime;
                dmwFirstAndEndStr[1] = dymdStr + "-" + MaxDate + dayEndTime;
            }
            // 按周查
            if (selType.trim().equalsIgnoreCase("curWeek")) {
                Calendar startDate = (Calendar) calendar.clone();
                Calendar endDate = (Calendar) calendar.clone();
                int nowWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
                startDate.add(Calendar.DATE, 1 - nowWeekNum);
                endDate.add(Calendar.DATE, 7 - nowWeekNum);
                // 开始时间
                StringBuffer startSB = new StringBuffer(8);
                startSB.append(startDate.get(Calendar.YEAR));
                startSB.append("-");
                startSB.append(one2TwoDigit((startDate.get(Calendar.MONTH) + 1)));
                startSB.append("-");
                startSB.append(one2TwoDigit(startDate.get(Calendar.DAY_OF_MONTH)));
                startSB.append(dayBeginTime);

                // 结束时间
                StringBuffer endSB = new StringBuffer(8);
                endSB.append(endDate.get(Calendar.YEAR));
                endSB.append("-");
                endSB.append(one2TwoDigit((endDate.get(Calendar.MONTH) + 1)));
                endSB.append("-");
                endSB.append(one2TwoDigit(endDate.get(Calendar.DAY_OF_MONTH)));
                endSB.append(dayEndTime);

                dmwFirstAndEndStr[0] = startSB.toString();
                dmwFirstAndEndStr[1] = endSB.toString();
            }
        }
        timestampList.add(Timestamp.valueOf(dmwFirstAndEndStr[0]));
        timestampList.add(Timestamp.valueOf(dmwFirstAndEndStr[1]));

        return timestampList;
    }

    /**
     * 一位转两位，如 1 则转为 01 ,即在一位前补零
     * 
     * @param one
     * @return
     */
    private static String one2TwoDigit(int one) {
        if (one / 10 >= 1)
            return String.valueOf(one);
        return "0" + String.valueOf(one);
    }

    public static long getTimebyString(String effectedEnd) {
        long tt = 0;
        effectedEnd = effectedEnd.substring(0, 19);
        int lng = effectedEnd.trim().length();
        String fFlag = "yyyy-MM-dd HH:mm:ss";
        try {
            java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(fFlag);
            tt = objSDF.parse(effectedEnd).getTime();
        } catch (Exception e) {
        }
        return tt;
    }

    /**
     * add by xiaohl for 比较两个日期相差的天数
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int countDays(Timestamp beginDate, Timestamp endDate) {
        int days = 0;
        try {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String begin = getStringFormatByTimestamp(beginDate, FORMAT_SHORTDATETIME);
            String end = getStringFormatByTimestamp(endDate, FORMAT_SHORTDATETIME);
            Calendar c_b = Calendar.getInstance();
            Calendar c_e = Calendar.getInstance();
            try {
                c_b.setTime(df.parse(begin));
                c_e.setTime(df.parse(end));

                while (c_b.before(c_e)) {
                    days++;
                    c_b.add(Calendar.DAY_OF_YEAR, 1);
                }
                if (days == 0) {
                    while (c_e.before(c_b)) {
                        days--;
                        c_e.add(Calendar.DAY_OF_YEAR, 1);
                    }
                }
            } catch (Exception pe) {
                System.out.println("日期格式必须为：yyyy-MM-dd；如：2010-4-4.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * add by 2013-01-23 for
     * 
     * @param flag 1：前一个月；-1，后一个月
     * @return 获取前一个月or后一个月的日期
     * 
     */
    public static String getTodayBeforeMonth(int flag) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();// 得到当前系统时间
        long now = currentTime.getTime();// 返回自 1970 年 1 月 1 日 00:00:00 GMT
                                         // 以来此Date 对象表示毫秒数
        long dayOfMillisecond = 86400000;// 一天的毫秒数
        long monthOfDay = 30;
        if (flag == 1) {
            currentTime = new Date(now - dayOfMillisecond * monthOfDay);
        } else if (flag == -1) {
            currentTime = new Date(now + dayOfMillisecond * monthOfDay);
        }

        String current = formatter.format(currentTime);
        return current;
    }

    public static String plusDate(Date date, int days, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
        return df.format(calendar.getTime());
    }

    public static String getBeforeDate(Date date, int days, String dateFormat) {
        return plusDate(date, -days, dateFormat);
    }

    public static String getAfterDate(Date date, int days, String dateFormat) {
        return plusDate(date, days, dateFormat);
    }

    public static Date getAfterDate2(Date date, int days, String dateFormat) {
        java.util.Date tt = null;
        try {
            String dateStr = plusDate(date, days, dateFormat);
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
            tt = sdf.parse(dateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tt;
    }

    /**
     * 得到几天前的时间
     * 
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     * 
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * @author zhaozq 20131209 日期比较是否在开始和结束时间内
     * 
     */
    public static int compareNowToBeginAndEnd(Timestamp beginDate, Timestamp date, Timestamp endDate) throws Exception {
        try {
            if (beginDate != null && endDate != null) {
                int d1 = DateUtil.compareDateTime(date, beginDate);
                int d2 = DateUtil.compareDateTime(endDate, date);
                if (d1 == 1 && d2 == 1) {// 在开始和结束时间之间--有效期
                    return 1;
                } else if (d1 == -1) {// 比开始时间小--即将开始
                    return 2;
                } else if (d2 == -1) {// 比结束时间大--已经过期失效
                    return 3;
                }
            } else if (beginDate != null && endDate == null) {
                int d1 = DateUtil.compareDateTime(date, beginDate);
                if (d1 == 1) {// 有效期
                    return 1;
                } else {// 即将开始
                    return 2;
                }
            } else if (beginDate == null && endDate != null) {
                int d2 = DateUtil.compareDateTime(endDate, date);
                if (d2 == 1) {// 有效期
                    return 1;
                } else {// 已经过期
                    return 3;
                }
            } else if (beginDate == null && endDate == null) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 3;
    }

    public static long dateDiffMin(String startTime, String endTime, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数long diff;try {
        // 获得两个时间的毫秒时间差异
        long dateTime = 0;
        try {
            dateTime = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long day = dateTime / nd;// 计算差多少天
        long hour = dateTime % nd / nh;// 计算差多少小时
        long min = dateTime % nd % nh / nm;// 计算差多少分钟
        long sec = dateTime % nd % nh % nm / ns;// 计算差多少秒//输出结果
        // System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec
        // + "秒。");
        return day * 24 * 60 + hour * 60 + min;
    }
    
    
    /**
     * 得到前一个月的年月日时分秒
     * @author xiaonanxiang
     */
    public static String getLastMonthTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return year + "-" + (month < 10 ? "0" + month : month) + "-" + (date < 10 ? "0" + date : date) + " "
                + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
                + (second < 10 ? "0" + second : second);
    }
    
    /**
     * 得到前一个月第一天的年月日时分秒
     * @author xiaonanxiang
     */
    public static String getLastMonthFirstDayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = 1;
        int hour = 0;
        int minute = 0;
        int second = 0;
        return year + "-" + (month < 10 ? "0" + month : month) + "-" + (date < 10 ? "0" + date : date) + " "
                + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
                + (second < 10 ? "0" + second : second);
    }
}
