package com.rogelioorts.com.blog.exaple.code.test.developer.one.simple.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MySimpleMap<K, V> implements Map<K, V> {
	
	private List<SimpleEntry<K,V>> entries;
	
	public MySimpleMap() {
		entries = new ArrayList<>();
	}

	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return entries.stream()
				.map(SimpleEntry::getKey)
				.anyMatch(entryKey -> Objects.equals(entryKey, key));
	}

	@Override
	public boolean containsValue(Object value) {
		return entries.stream()
				.map(SimpleEntry::getValue)
				.anyMatch(entryValue -> Objects.equals(entryValue, value));
	}

	@Override
	public V get(Object key) {
		SimpleEntry<K,V> entry = findEntry(key);
		return entry == null ? null : entry.value;
	}
	
	private SimpleEntry<K,V> findEntry(Object key) {
		return entries.stream()
				.filter(e -> Objects.equals(e.getKey(), key))
				.findFirst().orElse(null);
	}

	@Override
	public V put(K key, V value) {
		SimpleEntry<K,V> oldEntry = findEntry(key);
		
		if(oldEntry == null) {
			entries.add(new SimpleEntry<>(key, value));
			
			return null;
		} else {
			V oldValue = oldEntry.value;
			oldEntry.value = value;
		
			return oldValue;
		}
	}

	@Override
	public V remove(Object key) {
		V result = null;
		Iterator<SimpleEntry<K,V>> it = entries.iterator();
		
		while (it.hasNext()) {
			SimpleEntry<K,V> entry = it.next();
			if(Objects.equals(entry.getKey(), key)) {
				it.remove();
				result = entry.getValue();
				break;
			}
		}
		
		return result;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		List<SimpleEntry<K,V>> simpleEntries = m.entrySet().stream()
				.map(SimpleEntry<K,V>::new).collect(Collectors.toList());
		entries.addAll(simpleEntries);
	}

	@Override
	public void clear() {
		entries.clear();
	}

	@Override
	public Set<K> keySet() {
		return entries.stream()
				.map(SimpleEntry::getKey).collect(Collectors.toSet());
	}

	@Override
	public Collection<V> values() {
		return entries.stream()
				.map(SimpleEntry::getValue).collect(Collectors.toSet());
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return entries.stream()
				.collect(Collectors.toSet());
	}
	
	public static class SimpleEntry<K,V> implements Entry<K,V> {
		
		private final K key;
		
		private V value;
		
		private SimpleEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		private SimpleEntry(Entry<? extends K, ? extends V> entry) {
			this(entry.getKey(), entry.getValue());
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
			V oldValue = this.value;
			this.value = value;
			
			return oldValue;
		}
		
	}

}
