package verify.utils;

import java.util.HashMap;
import java.util.Map;

public enum CalendarFieldType {

	MILLISECOND(14, 1), SECOND(13, 1000), MINUTE(12, 60000), HOUR(11, 3600000), DATE(
			5, 1), MONTH(2, 1), YEAR(1, 1);

	private int code;
	private int multiple;

	private CalendarFieldType(int code, int multiple) {
		this.code = code;
		this.multiple = multiple;
	}

	public int getCode() {
		return code;
	}

	public int getMultiple() {
		return multiple;
	}

	private static final Map<Integer, CalendarFieldType> lookup = new HashMap<Integer, CalendarFieldType>();

	static {
		for (CalendarFieldType m : CalendarFieldType.values())
			lookup.put(m.getCode(), m);
	}

	public static CalendarFieldType get(int code) {
		return lookup.get(code);
	}

}
