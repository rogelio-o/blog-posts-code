package com.rogelioorts.com.blog.exaple.code.test.developer.one.hash.map;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MyHashMap<K,V> implements Map<K,V> {
	
	private static final int DEFAULT_THRESHOLD = 12;
	
	private MyEntry<K,V>[] table;
	
	@SuppressWarnings("unchecked")
	public MyHashMap(int threshold) {
		table = (MyEntry<K,V>[]) new MyEntry[threshold];
	}
	
	public MyHashMap() {
		this(DEFAULT_THRESHOLD);
	}

	@Override
	public int size() {
		int result = 0;
		
		for (int i = 0; i < table.length; ++i) {
            for (MyEntry<K,V> e = table[i]; e != null; e = e.next) {
                result++;
            }
        }
		
		return result;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		return findEntryInTableByKey(key) != null;
	}
	
	private MyEntry<K,V> findEntryInTableByKey(Object key) {
		return findEntryInTableByHash(hashOf(key), key);
	}
	
	private int hashOf(Object key) {
		return key == null ? 0 : key.hashCode();
	}
	
	private MyEntry<K,V> findEntryInTableByHash(int hash, Object key) {
		return findEntryInTableByIndex(indexOf(hash), key);
	}
	
	private int indexOf(int hash) {
		return hash % table.length;
	}
	
	private MyEntry<K,V> findEntryInTableByIndex(int index, Object key) {
		MyEntry<K,V> row = table[index];
		
		if(row == null) {
			return null;
		} else {
			return findEntryInTableRow(key, row);
		}
	}
	
	private MyEntry<K,V> findEntryInTableRow(Object key, MyEntry<K,V> row) {
		for (MyEntry<K,V> e = row; e != null; e = e.next) {
			if(isEntryForKey(e, key)) {
				return e;
			}
		}
		
		return null;
	}
	
	private boolean isEntryForKey(MyEntry<K,V> entry, Object key) {
		return Objects.equals(entry.getKey(), key);
	}

	@Override
	public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; ++i) {
            for (MyEntry<K,V> e = table[i]; e != null; e = e.next) {
            		V v = e.value;
                if (Objects.equals(v, value)) {
                    return true;
                }
            }
        }
        return false;
	}

	@Override
	public V get(Object key) {
		MyEntry<K,V> entry = findEntryInTableByKey(key);
		
		return entry == null ? null : entry.getValue();
	}

	@Override
	public V put(K key, V value) {
		MyEntry<K,V> oldEntry = findEntryInTableByKey(key);
		
		if(oldEntry == null) {
			addEntry(key, value);
			
			return null;
		} else {
			V oldValue = oldEntry.getValue();
			oldEntry.setValue(value);
			
			return oldValue;
		}
	}
	
	private void addEntry(K key, V value) {
		int hash = hashOf(key);
		int index = indexOf(hash);
		MyEntry<K,V> firstEntryOfRow = table[index];
		
		MyEntry<K,V> entry = new MyEntry<>(key, value, firstEntryOfRow);
		table[index] = entry;
	}

	@Override
	public V remove(Object key) {
		V oldValue = null;
		
		for (int i = 0; i < table.length; ++i) {
			MyEntry<K,V> previousEntry = null;
			for (MyEntry<K,V> e = table[i]; e != null; e = e.next) {
                if (isEntryForKey(e, key)) {
                		if(previousEntry != null) {
                			previousEntry.next = e.next;
                		} else {
                			table[i] = e.next;
                		}
                }
                
                previousEntry = e;
            }
		}

		return oldValue;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		m.entrySet().forEach(e -> put(e.getKey(), e.getValue()));
	}

	@Override
	public void clear() {
		for (int i = 0; i < table.length; ++i) {
			table[i] = null;
		}
	}

	@Override
	public Set<K> keySet() {
		return entrySet().stream().map(Entry::getKey).collect(Collectors.toSet());
	}

	@Override
	public Collection<V> values() {
		return entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return Arrays.asList(table).stream().flatMap(row -> getAllEntriesOfRow(row).stream()).collect(Collectors.toSet());
	}
	
	private Set<MyEntry<K,V>> getAllEntriesOfRow(MyEntry<K,V> row) {
		Set<MyEntry<K,V>> result = new HashSet<>();
		
		for (MyEntry<K,V> e = row; e != null; e = e.next) {
			result.add(e);
		}
		
		return result;
	}
	
	public static class MyEntry<K,V> implements Entry<K,V> {
		
		private final K key;
		
		private V value;
		
		private MyEntry<K,V> next;
		
		private MyEntry(K key, V value, MyEntry<K,V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
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
