package com.bootdemo.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bootdemo.common.exception.BDException;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化日期 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }
    /**
     * 格式化日期 根据pattern
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        
        long l = 0;
        if(now.getTime()>date.getTime()) {
        	l=now.getTime() - date.getTime();
        }else {
        	l=date.getTime()-now.getTime();
        }
        
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        return r;
    }
    

	/**
	 * 毫秒数转换日期字符串 yyyy-MM-dd
	 * @param timestamp
	 * @return
	 */
	public static String formatDate(long timestamp) {
		return format(timestamp, DATE_PATTERN);
	}
	/**
	 * 毫秒数转换日期字符串 yyyy-MM-dd HH:mm:ss
	 * @param timestamp
	 * @return
	 */
	public static String formatDatetime(long timestamp) {
		return format(timestamp, DATE_TIME_PATTERN);
	}
	/**
	 * 字符串转换成日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String date, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			throw new BDException(e.getMessage());
		}
	}
	public static boolean ge(Date param0, Date param1) {
		Calendar c0 = Calendar.getInstance();
		c0.setTimeInMillis(param0.getTime());
		long l0 = c0.get(Calendar.YEAR) * 10000 + c0.get(Calendar.MONTH) * 100 + c0.get(Calendar.DATE);

		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(param1.getTime());
		long l1 = c1.get(Calendar.YEAR) * 10000 + c1.get(Calendar.MONTH) * 100 + c1.get(Calendar.DATE);

		return l0 >= l1;
	}

	/**
	 * 
	 * @param param0
	 * @param param1
	 * @return true param0 >= param1
	 */
	public static boolean gt(Date param0, Date param1) {
		Calendar c0 = Calendar.getInstance();
		c0.setTimeInMillis(param0.getTime());
		long l0 = c0.get(Calendar.YEAR) * 10000 + c0.get(Calendar.MONTH) * 100 + c0.get(Calendar.DATE);

		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(param1.getTime());
		long l1 = c1.get(Calendar.YEAR) * 10000 + c1.get(Calendar.MONTH) * 100 + c1.get(Calendar.DATE);

		return l0 > l1;
	}

	public static boolean le(Date param0, Date param1) {
		Calendar c0 = Calendar.getInstance();
		c0.setTimeInMillis(param0.getTime());
		long l0 = c0.get(Calendar.YEAR) * 10000 + c0.get(Calendar.MONTH) * 100 + c0.get(Calendar.DATE);

		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(param1.getTime());
		long l1 = c1.get(Calendar.YEAR) * 10000 + c1.get(Calendar.MONTH) * 100 + c1.get(Calendar.DATE);

		return l0 <= l1;
	}

	public static boolean lt(Date param0, Date param1) {
		Calendar c0 = Calendar.getInstance();
		c0.setTimeInMillis(param0.getTime());
		long l0 = c0.get(Calendar.YEAR) * 10000 + c0.get(Calendar.MONTH) * 100 + c0.get(Calendar.DATE);

		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(param1.getTime());
		long l1 = c1.get(Calendar.YEAR) * 10000 + c1.get(Calendar.MONTH) * 100 + c1.get(Calendar.DATE);

		return l0 < l1;
	}
	
	/**
	 * 比较两个日期相差的天数
	 * @param sdate
	 * @param edate
	 * @return
	 */
	public static int getDaysBetweenTwoDate(Date sdate, Date edate) {
		long days = (edate.getTime() - sdate.getTime()) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(days));
	}
	/**
	 * 获取指定日期的前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date lastDate(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DATE, -1);
		date = parseDate(format(calender.getTime(), DATE_PATTERN), DATE_PATTERN);

		return date;
	}

	/**
	 * 获取前一天日期
	 * 
	 * @param currDate
	 * @return StringDate
	 */
	public static String beforeDate(String currDate) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(parseDate(currDate, "yyyyMMdd"));
		calender.add(Calendar.DATE, -1);
		Date date = new Date(calender.getTime().getTime());
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	
	/**
	 * 获取后一天日期
	 * 
	 * @param currDate
	 * @return StringDate
	 */
	public static String afterDate(String currDate) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(parseDate(currDate, "yyyyMMdd"));
		calender.add(Calendar.DATE, 1);
		Date date = new Date(calender.getTime().getTime());
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	/**
	 * 天数增加
	 * 
	 * @param date
	 * @param count
	 * @return
	 */
	public static Date addDay(Date date, int count) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DATE, count);

		return calender.getTime();
	}


	/**
	 * 转换日期得到指定格式的日期字符串
	 * 
	 * @param formatString
	 *            需要把目标日期格式化什么样子的格式。例如,yyyy-MM-dd HH:mm:ss
	 * @param targetDate
	 *            目标日期
	 * @return
	 */
	public static String convertDate2String(String formatString, Date targetDate) {
		SimpleDateFormat format = null;
		String result = null;
		if (targetDate != null) {
			format = new SimpleDateFormat(formatString);
			result = format.format(targetDate);
		} else {
			return null;
		}
		return result;
	}

	/**
	 * 获取当前月的天数
	 * 
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calender = Calendar.getInstance(Locale.CHINA);
		calender.setTime(date);
		int days = calender.getActualMaximum(Calendar.DATE);
		return days;
	}

	/**
	 * 获取两个时间的时间间隔
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static int getDaysBetween(Date sdate, Date edate) {
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(sdate);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(edate);
		if (beginDate.after(endDate)) {
			Calendar swap = beginDate;
			beginDate = endDate;
			endDate = swap;
		}
		int days = endDate.get(Calendar.DAY_OF_YEAR) - beginDate.get(Calendar.DAY_OF_YEAR) + 1;
		int year = endDate.get(Calendar.YEAR);
		if (beginDate.get(Calendar.YEAR) != year) {
			beginDate = (Calendar) beginDate.clone();
			do {
				days += beginDate.getActualMaximum(Calendar.DAY_OF_YEAR);
				beginDate.add(Calendar.YEAR, 1);
			} while (beginDate.get(Calendar.YEAR) != year);
		}
		return days;
	}

	/**
	 * 获取当前日期 格式:XXXX-XX-XX
	 * 
	 * @param date
	 * @return
	 */
	public static Date getCurrDate() {
		String sdate = format(System.currentTimeMillis(), DATE_PATTERN);

		return parseDate(sdate, DATE_PATTERN);
	}
	
	/** 获取当前时间：字符串 */
	public static String getCurrStrDate() {
		
		return format(System.currentTimeMillis(), "yyyyMMdd");
	}

	/**
	 * 获取当前日期的当前日历
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayFromDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.DATE);
	}

	/**
	 * 获取当前日期的年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearFromDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取当前日期的月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthFromDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;

		return month;
	}

	/**
	 * 获取当前日期的月份-1
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);

		return month;
	}

	// 日期转换格式化
	public static long longToDate(long time) {

		try {
			SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN);
			String date = sf.format(new Date(time));
			return sf.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}

	public static int longToInt(String format, long time) {

		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			int date = Integer.valueOf(sf.format(new Date(time)));
			return date;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 获取当前年份
	 */
	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR));
	}

	/**
	 * 获取当前月份
	 */
	public static String getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(addZero(cal.get(Calendar.MONTH) + 1));
	}

	public static String addZero(int args) {
		if (args < 10) {
			return "0" + args;
		}
		return "" + args;
	}

	/**
	 * 获取当前月份中的第几天
	 */
	public static String getCurrentDay() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(addZero(cal.get(Calendar.DATE)));
	}

	public static String getCurrentDay(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return String.valueOf(addZero(cal.get(Calendar.DATE)));
	}

	public static String format(long timestamp, String pattern) {
		return format(new Date(timestamp), pattern);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) {
		return StringUtils.isBlank(strDate) ? null : parse(strDate,DATE_TIME_PATTERN);
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) {
		try {
			return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 获取指定年月的最后一天的日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		// cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
	}

	/**
	 * 获取指定"年, 月, 日"的Date实例
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String year, String month, String day) throws ParseException {
		String result = year + "-" + (month.length() == 1 ? ("0 " + month) : month) + "-" + (day.length() == 1 ? ("0 " + day) : day);
		return parse(result);
	}

	/**
	 * get different days between two dates
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long getDifferentDays(Date d1, Date d2) {
		long diff = d1.getTime() - d2.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		return days;
	}

	public static String getCurrentDate() {
		return format(new Date(), "yyyyMMdd");
	}

	public static String convert(String date, String pattern, String product) {
		DateFormat f = new SimpleDateFormat(pattern);
		DateFormat t = new SimpleDateFormat(product);
		try {
			String s = t.format(f.parse(date));
			return s;
		} catch (ParseException e) {
			throw new BDException(e.getMessage());
		}
	}

	/**
	 * 获取上一日时间
	 * 
	 * @return
	 */
	public static Date getBeforeDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		Date d = new Date(c.getTimeInMillis());
		return d;
	}

	public static Date getAfterDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		Date d = new Date(c.getTimeInMillis());
		return d;
	}

	/**
	 * 获取下一个工作日
	 * 
	 * @return
	 */
	public static String getNextWorkDay() {

		return format(getAfterDate(), "yyyyMMdd");
	}

	/**
	 * 获取下一个自然日
	 * 
	 * @return
	 */
	public static String getNextNaturalDay() {
		return format(getAfterDate(), "yyyyMMdd");
	}


	/**
	 * 格式化指定日期为yyyyMMdd格式
	 * 
	 * @param date
	 * @return
	 */
	public static String defaultFormat(Date date) {
		return format(date, "yyyyMMdd");
	}

	/**
	 * 格式化指定日期为yyyyMMddHH格式
	 * 
	 * @param date
	 * @return
	 */
	public static String defaultDateHourFormat() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String s = null;
		if (hour < 10) {
			s = "0" + hour;
		} else {
			s = String.valueOf(hour);
		}
		return format(cal.getTime(), "yyyyMMdd") + s;
	}

	public static int daysBetween(Date bdate) {
		try {
			return daysBetween(new Date(), bdate);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 计算两个日期之差
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));

			Calendar cal = Calendar.getInstance();

			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();

			long between_days = (time1 - time2) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}


	/** 获取某月1号0点时间 */
	public static String getFirstDayZeroTimeOfMonth(int year, int month, String patt) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, 1, 0, 0, 0);
		return format(c.getTime());
	}

	/** 获取某月最后一天23:59:59时间 */
	public static String getLastDayLastTimeOfMonth(int year, int month, String patt) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, 1, 23, 59, 59);
		c.add(Calendar.DATE, -1);
		return format(c.getTime());
	}

	/**
	 * 当前时间是否超过15:00
	 * @return
	 */
	public static boolean isOver15() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		if (hour <= 15) {
			return false;
		} else {
			return true;
		}
	}
	public static void main(String[] args) {
		Date date=DateUtils.parse("2018-06-21 13:13:13");
		System.out.println(getTimeBeforeAccurate(date));
	}
	
}
