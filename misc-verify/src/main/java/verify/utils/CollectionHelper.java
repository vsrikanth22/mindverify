package verify.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static verify.utils.AssertHelper.*;

public class CollectionHelper {

	public static <K, V, E> Map<K, V> populateMap(List<E> elements,
			EntryCreator<K, V, E> entryCreator) {
		Map<K, V> holder = new HashMap<K, V>();
		for (E element : elements) {
			Entry<K, V> entry = entryCreator.createEntry(element);
			holder.put(entry.getKey(), entry.getValue());
		}
		return holder;
	}

	public static <E> List<E> newListWithDefaultValue(DefaultValue<E> dv,
			int length) {
		List<E> results = new ArrayList<E>(length);
		Collections.fill(results, dv.defaultValue());
		return results;
	}

	public static <E> List<E> processNoFoundDataCallback(List<E> list,
			NoFoundDataCallback... noFoundDataCallbacks) {
		if (isEmpty(list) && isUniqueElement(noFoundDataCallbacks)) {
			NoFoundDataCallback cb = noFoundDataCallbacks[0];
			if (isNotNull(cb)) {
				cb.notifyTo();
			}
		}
		return list;
	}

	public static <K, V> boolean compare(Map<K, V> left, Map<K, V> right) {

		Set<K> leftKeys = left.keySet();
		Set<K> rightKeys = right.keySet();

		if (leftKeys.size() != rightKeys.size()) {
			return false;
		} else {
			for (K key : leftKeys) {
				if (!left.get(key).equals(right.get(key))) {
					return false;
				}
			}
		}
		return true;
	}

	public static <K, V> Map<K, V> combineTwoMap(Map<K, V> src, Map<K, V> target) {
		if (isNotEmpty(src) && isNotEmpty(target)) {
			src.putAll(target);
			return src;
		} else if (isNotEmpty(src) && isEmpty(target)) {
			return src;
		} else if (isNotEmpty(target) && isEmpty(src)) {
			return target;
		} else
			return null;
	}

	public static <E> Set<E> combineSets(Set<E>... sets) {
		Set<E> base = new HashSet<E>();
		for (Set<E> set : sets) {
			if (isNotEmpty(set))
				base.addAll(set);
		}
		return base;

	}

	public static <E> boolean checkAllElementsHasSameValueInOneField(
			List<E> elements, Comparator<E> comparator) {
		if (isNotEmpty(elements)) {
			if (elements.size() == 1)
				return true;
			E it = null;
			for (E element : elements) {
				if (it == null) {
					it = element;
				} else {
					int result = comparator.compare(it, element);
					if (result != 0)
						return false;
					else {
						it = element;
					}
				}
			}
			return true;
		}
		return false;
	}

	public static <E> E getFirstElement(List<E> list) {
		return list.get(0);
	}

	public static <T1, T2> List<T2> wrapOfList(List<T1> records,
			EntityAdapter<T1, T2> entityAdapter) {

		if (isNotEmpty(records)) {
			List<T2> results = new ArrayList<T2>(records.size());
			for (T1 record : records) {
				results.add(entityAdapter.transform(record));
			}
		}
		return new ArrayList<T2>();
	}

}
