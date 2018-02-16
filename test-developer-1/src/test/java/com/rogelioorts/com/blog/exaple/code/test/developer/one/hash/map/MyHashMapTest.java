package com.rogelioorts.com.blog.exaple.code.test.developer.one.hash.map;

import java.util.Map;

import com.rogelioorts.com.blog.exaple.code.test.developer.one.map.MapTest;

public class MyHashMapTest extends MapTest {

	@Override
	protected Map<String, String> createMap() {
		return new MyHashMap<>();
	}

}
