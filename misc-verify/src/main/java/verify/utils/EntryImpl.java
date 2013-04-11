package verify.utils;

import java.util.Map.Entry;


public final class EntryImpl<K, V> implements Entry<K, V> {

	private final K	key;
	private final V	value;

	public EntryImpl(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		throw new UnsupportedOperationException("the value is immutable.");
	}

	public String toString() {
		return key + "=" + value;
	}

}
