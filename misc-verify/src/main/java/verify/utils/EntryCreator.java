package verify.utils;

import java.util.Map.Entry;

public interface EntryCreator<K, V, E> {

	public Entry<K, V> createEntry(E e);

}
