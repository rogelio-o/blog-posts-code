package com.rogelioorts.com.blog.exaple.code.test.developer.one.simple.map;

import java.util.Map;

import com.rogelioorts.com.blog.exaple.code.test.developer.one.map.MapTest;

public class MySimpleMapTest extends MapTest {

	@Override
	protected Map<String, String> createMap() {
		return new MySimpleMap<>();
	}

}
