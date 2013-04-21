package verify.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class AssertHelper {

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isNotNull(Object object) {
		return object != null;
	}

	public static boolean isEmpty(String string) {
		return (isNull(string) || string.trim().length() == 0);
	}

	public static <E> boolean isNotEmpty(E... array) {
		return !isEmpty(array);
	}
	
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	public static <E> boolean isEmpty(Collection<E> list) {
		return (isNull(list) || list.size() == 0);
	}

	public static <E> boolean isNotEmpty(Collection<E> list) {
		return !isEmpty(list);
	}

	public static <E> boolean isEmpty(E... array) {
		return (isNull(array) || array.length == 0);
	}

	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return (isNull(map) || map.size() == 0);
	}

	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return !isEmpty(map);
	}

	public static <E> boolean isUniqueElement(List<E> list) {
		return (isNotNull(list) && list.size() == 1);
	}

	public static <E> boolean isUniqueElement(E... array) {
		return (isNotNull(array) && array.length == 1);
	}

	public static <E> E isNull(E object, E defaultValue) {
		return isNull(object) ? defaultValue : object;
	}

}
