package com.rogelioorts.com.blog.exaple.code.test.developer.one.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class MapTest {
	
	private Map<String, String> map;
	
	@Before
	public void initialize() {
		map = createMap();
	}
	
	protected abstract Map<String, String> createMap();
	
	@Test
	public void oneMapElementSizeIsOne() {
		map.put("a", "b");
		
		Assert.assertEquals(1, map.size());
	}
	
	@Test
	public void zeroMapElementsIsEmpty() {
		Assert.assertTrue(map.isEmpty());
	}
	
	@Test
	public void oneMapElementsIsNotEmpty() {
		map.put("a", "b");
		
		Assert.assertFalse(map.isEmpty());
	}
	
	@Test
	public void containsReturnsTrueIfTheMapHasTheKey() {
map.put("a", "b");
		
		Assert.assertTrue(map.containsKey("a"));
	}
	
	@Test
	public void containsReturnsFalseIfTheMapHasNotTheKey() {
		map.put("a", "b");
		
		Assert.assertFalse(map.containsKey("c"));
	}
	
	@Test
	public void containsReturnsTrueIfTheMapHasTheValue() {
		map.put("a", "b");
		
		Assert.assertTrue(map.containsValue("b"));
	}
	
	@Test
	public void containsReturnsFalseIfTheMapHasNotTheValue() {
		map.put("a", "b");
		
		Assert.assertFalse(map.containsValue("d"));
	}
	
	@Test
	public void getReturnsTheValueIfTheKeyExistsInTheMap() {
		map.put("a", "b");
		
		Assert.assertEquals("b", map.get("a"));
	}
	
	@Test
	public void getReturnsNullIfTheKeyNotExistInTheMap() {
		map.put("a", "b");
		
		Assert.assertNull(map.get("c"));
	}
	
	@Test
	public void putReturnsTheOldValueIfAPreviousValueExistsWithTheSameKey() {
		map.put("a", "b");
		
		Assert.assertEquals("b", map.put("a", "e"));
		Assert.assertEquals("e", map.get("a"));
	}
	
	@Test
	public void putSaveTheValueForTheKeyInTheMap() {
		map.put("a", "b");
		
		Assert.assertEquals("b", map.get("a"));
	}
	
	@Test
	public void putSaveTheValueForANullKeyInTheMap() {
		map.put(null, "b");
		
		Assert.assertEquals("b", map.get(null));
	}
	
	@Test
	public void removeDeletesTheEntryInTheMapByKey() {
		map.put("a", "b");
		map.remove("a");
		
		Assert.assertEquals(0, map.size());
		Assert.assertNull(map.get("a"));
	}
	
	@Test
	public void putAllPutsAllTheEntryOfAGivenMapInTheMap() {
		Map<String, String> otherMap = new HashMap<String, String>();
		otherMap.put("a", "b");
		otherMap.put("c", "d");
		
		map.putAll(otherMap);
		
		Assert.assertEquals("b", map.get("a"));
		Assert.assertEquals("d", map.get("c"));
	}
	
	@Test
	public void clearCleansTheMap() {
		map.put("a", "b");
		map.put("c", "d");
		
		map.clear();
		
		Assert.assertEquals(0, map.size());
		Assert.assertNull(map.get("a"));
		Assert.assertNull(map.get("b"));
	}
	
	@Test
	public void entrySetReturnsAllTheEntriesOfTheMap() {
		map.put("a", "b");
		map.put("c", "d");
		
		Set<Entry<String,String>> entries = map.entrySet();
		Assert.assertEquals(2, entries.size());
		Assert.assertTrue(entries.stream().anyMatch(e -> "a".equals(e.getKey()) && "b".equals(e.getValue())));
		Assert.assertTrue(entries.stream().anyMatch(e -> "c".equals(e.getKey()) && "d".equals(e.getValue())));
	}
	
	@Test
	public void keySetReturnsAllTheKeysOfTheMap() {
		map.put("a", "b");
		map.put("c", "d");
		
		Set<String> keys = map.keySet();
		Assert.assertEquals(2, keys.size());
		Assert.assertTrue(keys.contains("a"));
		Assert.assertTrue(keys.contains("c"));
	}
	
	@Test
	public void valuesReturnsAllTheValuesOfTheMap() {
		map.put("a", "b");
		map.put("c", "d");
		
		Collection<String> values = map.values();
		Assert.assertEquals(2, values.size());
		Assert.assertTrue(values.contains("b"));
		Assert.assertTrue(values.contains("d"));
	}
	
}
