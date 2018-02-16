package com.rogelioorts.com.blog.exaple.code.test.developer.one.text.transformer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextTransformerTest {
	
	private TextTransformer transformer;
	
	@Before
	public void initialize() {
		transformer = new TextTransformer();
	}

	@Test
	public void checkWithoutDecompress() {
		Assert.assertEquals("ab", transformer.decompress("ab"));
	}
	
	@Test
	public void checkSimpleDecompress() {
		Assert.assertEquals("abab", transformer.decompress("2[ab]"));
	}
	
	@Test
	public void checkSimpleDecompressWithTail() {
		Assert.assertEquals("ababcd", transformer.decompress("2[ab]cd"));
	}
	
	@Test
	public void checkNestedDecompress() {
		Assert.assertEquals("abccccabcccc", transformer.decompress("2[ab4[c]]"));
	}
	
}
