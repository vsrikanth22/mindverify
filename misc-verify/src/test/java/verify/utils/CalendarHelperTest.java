package verify.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

import verify.utils.CalendarHelper;

public class CalendarHelperTest {

	@Test
	public void testSimpleFomart() throws Exception {

		Calendar data = CalendarHelper.date(2012, 7, 12, TimeZone.getDefault().getID());

		String stringData = CalendarHelper.format("yyyyMMdd", data);
		Assert.assertEquals("20120712", stringData);
	}

	@Test
	public void testBetween() throws Exception {
		Calendar srcIn = CalendarHelper.date(2012, 7, 12, TimeZone.getDefault().getID());
		Calendar srcNotIn = CalendarHelper.date(2012, 7, 15, TimeZone.getDefault().getID());
		Calendar low = CalendarHelper.date(2012, 7, 11, TimeZone.getDefault().getID());
		Calendar high = CalendarHelper.date(2012, 7, 13, TimeZone.getDefault().getID());

		Assert.assertTrue(CalendarHelper.between(srcIn, low, high));
		Assert.assertFalse(CalendarHelper.between(srcNotIn, low, high));

	}

	@Test
	public void testCompare() throws Exception {
		Calendar day1 = CalendarHelper.date(2011, 7, 12, TimeZone.getDefault().getID());
		Calendar day2 = CalendarHelper.date(2012, 8, 15, TimeZone.getDefault().getID());
		Calendar day3 = CalendarHelper.date(2014, 7, 11, TimeZone.getDefault().getID());
		Calendar day4 = CalendarHelper.date(2014, 7, 11, TimeZone.getDefault().getID());
		Calendar day5 = CalendarHelper.date(2011, 7, 12, TimeZone.getDefault().getID());

		Assert.assertTrue(CalendarHelper.le(day1, day2));
		Assert.assertTrue(CalendarHelper.eq(day3, day4));
		Assert.assertTrue(CalendarHelper.gt(day3, day2));
		Assert.assertTrue(CalendarHelper.lt(day1, day2));
		Assert.assertTrue(CalendarHelper.le(day1, day5));
		Assert.assertTrue(CalendarHelper.ge(day3, day2));
		Assert.assertTrue(CalendarHelper.ge(day3, day4));

		Assert.assertFalse(CalendarHelper.le(day2, day1));
		Assert.assertFalse(CalendarHelper.eq(day1, day4));
		Assert.assertFalse(CalendarHelper.gt(day2, day3));
		Assert.assertFalse(CalendarHelper.lt(day3, day2));
		Assert.assertFalse(CalendarHelper.ge(day1, day2));

	}

	public void testToCalendar() throws Exception {

	}

	@Test
	public void testDaysBetween() throws Exception {
		Calendar day1 = CalendarHelper.date(2010, 7, 12, TimeZone.getDefault().getID());
		Calendar day2 = CalendarHelper.date(2011, 7, 12, TimeZone.getDefault().getID());
		Calendar day3 = CalendarHelper.date(2011, 4, 1, TimeZone.getDefault().getID());
		Calendar day4 = CalendarHelper.date(2012, 5, 1, TimeZone.getDefault().getID());

		Calendar day5 = CalendarHelper.date(2010, 0, 1, TimeZone.getDefault().getID());
		Calendar day6 = CalendarHelper.date(2011, 0, 31, TimeZone.getDefault().getID());
		
		Calendar day7 = CalendarHelper.date(2012, 12, 16, TimeZone.getDefault().getID());
		Calendar day8 = CalendarHelper.date(2012, 12, 16, TimeZone.getDefault().getID());

		long result1 = CalendarHelper.daysBetween(day1.getTime(), day2.getTime());
		long result2 = CalendarHelper.daysBetween(day3, day4);
		long result3 = CalendarHelper.daysBetween(day5, day6);
		long result4 = CalendarHelper.daysBetween(day7, day8);
		Assert.assertEquals(365, result1);
		Assert.assertEquals(366 + 30, result2);
		Assert.assertEquals(365 + 30, result3);
		Assert.assertEquals(0, result4);

	}

	@Test
	public void testToday() throws Exception {
		Calendar today = CalendarHelper.today(TimeZone.getDefault().getID());
		Calendar expected = CalendarHelper.truncate(Calendar.getInstance(TimeZone.getDefault()), Calendar.DATE);
		Assert.assertTrue(CalendarHelper.eq(expected, today));
	}

	@Test
	public void testTodayInOtherTime() throws Exception {
		Calendar todayInOtherTime = CalendarHelper.todayInOtherTime(1110, TimeZone.getDefault().getID());
		Calendar expected = CalendarHelper.today(TimeZone.getDefault().getID());
		expected.set(Calendar.HOUR_OF_DAY, 11);
		expected.set(Calendar.MINUTE, 10);
		Assert.assertTrue(CalendarHelper.eq(expected, todayInOtherTime));

		Assert.assertNull(CalendarHelper.todayInOtherTime(null, TimeZone.getDefault().getID()));
		/*
		 * System.out.println(todayInOtherTime.getTime().toString()); System.out.println(expected.getTime().toString());
		 */
	}
	
	@Test
	public void testFormatDate() {
		System.out.println(String.format("%1$tY%<tm%<td", new Date()));
	}

}
