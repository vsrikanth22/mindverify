package verify.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static verify.utils.AssertHelper.*;

/**
 * Calendar Helper
 * 
 * @author e467885
 * 
 */
public abstract class CalendarHelper {

	public static String format(String format, Calendar calendar) {
		return new SimpleDateFormat(format).format(calendar.getTime());
	}

	public static Calendar parse(String format, String datetime,
			TimeZone timeZone) {
		try {
			Calendar result = Calendar.getInstance();
			result.setTime(new SimpleDateFormat(format).parse(datetime));

			Calendar calendar = today(timeZone);
			calendar.set(Calendar.YEAR, result.get(Calendar.YEAR));
			calendar.set(Calendar.MONTH, result.get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_MONTH,
					result.get(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR_OF_DAY, result.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, result.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, result.get(Calendar.SECOND));

			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException("can not parse " + datetime
					+ "with the format " + format);
		}
	}

	public static Calendar todayInOtherTime(Integer time, String timeZone) {

		if (time == null)
			return null;

		Calendar calendar = today(timeZone);
		calendar.set(Calendar.HOUR_OF_DAY, time / 100);
		calendar.set(Calendar.MINUTE, time % 100);
		return calendar;
	}

	/**
	 * get date object of today.
	 * 
	 * @param timeZone
	 * @return a date object without time
	 */
	public static Calendar today(String timeZone) {
		return truncate(now(timeZone), Calendar.DATE);
	}

	public static Calendar today(TimeZone timeZone) {
		return truncate(now(timeZone), Calendar.DATE);
	}

	public static Calendar todayWithOffset(TimeZone timeZone, int type,
			int offset) {
		Calendar today = today(timeZone);
		today.add(type, offset);
		return today;
	}

	/**
	 * create a date object.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param timeZone
	 * @return
	 */
	public static Calendar date(int year, int month, int day, String timeZone) {
		Calendar date = today(timeZone);
		date.set(year, month - 1, day);
		return date;
	}

	/**
	 * create a date time object.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param timeZone
	 * @return
	 */
	public static Calendar datetime(int year, int month, int day, int hour,
			int minute, int second, String timeZone) {
		Calendar date = today(timeZone);
		date.set(year, month - 1, day, hour, minute, second);
		return date;
	}

	/**
	 * get datetime object of today.
	 * 
	 * @param timeZone
	 * @return a date object
	 */
	public static Calendar now(String timeZone) {
		return Calendar.getInstance(getTimeZone(timeZone));
	}

	public static Calendar now(TimeZone timeZone) {
		return Calendar.getInstance(timeZone);
	}

	public static Calendar now() {
		return Calendar.getInstance();
	}

	public static Calendar nowWithOffset(int type, int offset) {
		Calendar now = now();
		now.add(type, offset);
		return now;
	}

	/**
	 * parse time zone from a string value.
	 * 
	 * @param timeZone
	 * @return
	 */
	public static TimeZone getTimeZone(String timeZone) {
		if (isEmpty(timeZone)) {
			throw new IllegalArgumentException(
					"the timezone is empty, please check.");
		}
		return TimeZone.getTimeZone(timeZone);
	}

	/**
	 * convert a date object to calendar object
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static Calendar toCalendarDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return truncate(c);
	}

	/**
	 * [low, high)
	 * 
	 * @param now
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static boolean between(Calendar now, Calendar low, Calendar high) {
		return (now.compareTo(low) >= 0 && now.compareTo(high) < 0);
	}

	/**
	 * equals
	 * 
	 * @param one
	 * @param another
	 * @return
	 */
	public static boolean eq(Calendar one, Calendar another) {
		return (one.compareTo(another) == 0);
	}

	/**
	 * less than
	 * 
	 * @param one
	 * @param another
	 * @return
	 */
	public static boolean lt(Calendar one, Calendar another) {
		return (one.compareTo(another) < 0);
	}

	/**
	 * great than
	 * 
	 * @param one
	 * @param another
	 * @return
	 */
	public static boolean gt(Calendar one, Calendar another) {
		return (one.compareTo(another) > 0);
	}

	/**
	 * less and equals
	 * 
	 * @param one
	 * @param another
	 * @return
	 */
	public static boolean le(Calendar one, Calendar another) {
		return (one.compareTo(another) <= 0);
	}

	public static boolean ge(Calendar one, Calendar another) {
		return (one.compareTo(another) >= 0);
	}

	public static Calendar truncate(Calendar calendar) {
		return truncate(calendar, Calendar.DATE);
	}

	public static Calendar truncate(Date date) {
		return truncate(toCalendar(date), Calendar.DATE);
	}

	public static int compareDate(Date src, Date target) {
		return truncate(src).compareTo(truncate(target));
	}

	public static long daysBetween(Date startDate, Date endDate) {

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		return daysBetween(start, end);
	}

	public static long daysBetween(Calendar startDate, Calendar endDate) {

		startDate = (Calendar) startDate.clone();
		long daysBetween = 0;

		while (startDate.get(Calendar.YEAR) < endDate.get(Calendar.YEAR)) {
			if (startDate.get(Calendar.DAY_OF_YEAR) != 1) {
				int diff = startDate.getMaximum(Calendar.DAY_OF_YEAR)
						- startDate.get(Calendar.DAY_OF_YEAR);
				diff++;
				startDate.add(Calendar.DAY_OF_YEAR, diff);
				daysBetween += diff;
			} else {
				daysBetween += startDate.getMaximum(Calendar.DAY_OF_YEAR);
				startDate.add(Calendar.YEAR, 1);
			}
		}
		daysBetween += endDate.get(Calendar.DAY_OF_YEAR)
				- startDate.get(Calendar.DAY_OF_YEAR);
		return daysBetween;
	}

	public static Calendar convertToTz(long millis, String tzId) {
		return convertToTz(millis, TimeZone.getTimeZone(tzId));
	}

	public static Calendar convertToTz(long millis, TimeZone tz) {
		Calendar calendar = Calendar.getInstance(tz);
		calendar.setTimeInMillis(millis);
		return calendar;
	}

	public static int getNumberOfSecond(int hourAndMin) {
		return hourAndMin / 100 * 3600 + hourAndMin % 100 * 60;
	}

	public static Calendar truncate(Calendar calendar, int field) {
		Calendar val = (Calendar) calendar.clone();

		CalendarFieldType _field = CalendarFieldType.get(field);

		if (_field == null)
			throw new IllegalArgumentException("the field value is not valid.");

		for (CalendarFieldType type : CalendarFieldType.values()) {
			if (type.ordinal() < _field.ordinal()) {
				val.set(type.getCode(), val.getMinimum(type.getCode()));
			}
		}

		return val;

	}

}
