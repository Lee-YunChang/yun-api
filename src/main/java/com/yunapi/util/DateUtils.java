package com.yunapi.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	public static SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat SDF_yyyyMMddHHmm = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	public static SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat SDF_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat SDF_yyMMdd = new SimpleDateFormat("yyMMdd");
	public static SimpleDateFormat SDF_yyyyMM = new SimpleDateFormat("yyyyMM");
	public static SimpleDateFormat SDF_yyyyMMddHHmm2 = new SimpleDateFormat("yyyyMMddHHmm");
	public static SimpleDateFormat SDF_yyyyMMddDay = new SimpleDateFormat("yyyy.MM.dd E");
	public static SimpleDateFormat SDF_hhmmss = new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat SDF_yyyyMMdd2 = new SimpleDateFormat("yyyy.MM.dd");

	private static final String S_FORMAT_yyMMdd = "%ty.%tm.%td";
	private static final String S_FORMAT_yyyyMM = "%tY%tm";
	private static final String S_FORMAT_yyyyMMdd = "%tY.%tm.%td";
	private static final String S_FORMAT_yyyyMMddHHmmss = "%tY.%tm.%td %tT";
	private static final String S_FORMAT_yyyyMMdd_day = "%tY.%tm.%td %ta";
	private static final String S_FORMAT_HHmm = "%tR";
	private static final String S_FORMAT_HHmmss = "%tT";

	public static Timestamp stringToTimestamp(String time) {
		try {
			time = time.replaceAll("[^0-9]", "");
			if(time.length() == 8) {
				time += "000000";
			} else if (time.length() == 12) {
				time += "00";
			}
			Date date = SDF_yyyyMMddHHmmss.parse(time);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date stringToDate(String time) {
		try {
			time = time.replaceAll("[^0-9]", "");
			if(time.length() == 8) {
				return SDF_yyyyMMdd.parse(time);
			} else if(time.length() == 10) {
				return SDF_yyyyMMddHHmm2.parse(time);
			}
			return SDF_yyyyMMddHHmmss.parse(time);
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getLastDay(String time) {
		try {
			String year = time.substring(0, 4);
			String month = time.substring(4, 6);
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(year),Integer.parseInt(month)-1,1); 
			String lastDay = year+month+Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			return lastDay;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//해당 일자가가 속한 주의 월요일날짜
	public static String getMondayOfDate(Date date){
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE,-1);
			c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return SDF_yyyyMMdd.format(c.getTime());
	}

	//해당 일자가 속한 주의 일요일날짜
	public static String getSundayOfDate(Date date){
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE,-1);
			c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
			c.add(Calendar.DATE,7);
		return SDF_yyyyMMdd.format(c.getTime());
	}

	public static int getWeekOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(c.get(Calendar.DAY_OF_WEEK)==1 && c.get(Calendar.WEEK_OF_MONTH)>1){
			c.add(Calendar.DATE,-1);
		}
		int weekOfMonth=c.get(Calendar.WEEK_OF_MONTH);
		c.set(Calendar.DATE,1);//해당 월 1일로 설정
		if(c.get(Calendar.DAY_OF_WEEK)>2)weekOfMonth-=1;//월요일 = 2 , 2보다 크면 화요일 이후.

		if(weekOfMonth==0){
			c.setTime(stringToDate(getMondayOfDate(c.getTime())));
			weekOfMonth = getWeekOfMonth(c.getTime());
		}
		return weekOfMonth;
	}
	
	public static String getTodayOfDate() {
		LocalDate now = LocalDate.now();
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static String stringFormat_yyMMdd(String time){
			if(StringUtils.isBlank(time)) return "";
			Date date = stringToDate(time);
		return String.format(S_FORMAT_yyMMdd,date,date,date);
	}

	public static String stringFormat_yyyyMMddHHmmss(String time){
			if(StringUtils.isBlank(time)) return "";
		Date date = stringToDate(time);
		return String.format(S_FORMAT_yyyyMMddHHmmss,date,date,date,date);
	}
	
	public static String stringFormat_yyyyMM(String time){
		if(StringUtils.isBlank(time)) return "";
		Date date = stringToDate(time);
	return String.format(S_FORMAT_yyyyMM,date,date);
	}
	
	public static String stringFormat_yyyyMMdd(String time){
		if(StringUtils.isBlank(time)) return "";
		Date date = stringToDate(time);
	return String.format(S_FORMAT_yyyyMMdd,date,date,date);
	}

	public static String stringFormat_yyyyMMdd_day(String time){
			if(StringUtils.isBlank(time)) return "";
		Date date = stringToDate(time);
		return String.format(S_FORMAT_yyyyMMdd_day,date,date,date,date);
	}

	public static String stringFormat_HHmm(String time){
		if(StringUtils.isBlank(time)) return "";
		Date date = stringToDate(time);
		return String.format(S_FORMAT_HHmm,date);
	}

	public static String stringFormat_HHmmss(String time){
		if(StringUtils.isBlank(time)) return "";
		Date date = stringToDate(time);
		return String.format(S_FORMAT_HHmmss,date);
	}
	
	public static Calendar getCalendarCopy(Calendar oriCalendar) {
		Calendar cal = Calendar.getInstance();				
		cal.set(Calendar.YEAR, oriCalendar.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, oriCalendar.get(Calendar.MONTH));
		cal.set(Calendar.DATE, oriCalendar.get(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, oriCalendar.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, oriCalendar.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, oriCalendar.get(Calendar.SECOND));
		return cal;
	}

}
