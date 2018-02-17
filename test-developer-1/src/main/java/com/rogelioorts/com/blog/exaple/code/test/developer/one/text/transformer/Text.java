package com.rogelioorts.com.blog.exaple.code.test.developer.one.text.transformer;

public class Text {
	
	private static final char PATTERN_START = '(';
	private static final char PATTERN_END = ')';
	
	private String text;
	
	private int index;
	
	public Text(String text) {
		this.text = text;
		this.index = 0;
	}
	
	public String getText() {
		return text;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void next() {
		index++;
	}
	
	public boolean hasNext() {
		return index < text.length();
	}
	
	public char getCurrent() {
		return text.charAt(index);
	}
	
	public int getCurrentAsInteger() {
		return Integer.valueOf(String.valueOf(getCurrent()));
	}
	
	public boolean isCurrentEndPattern() {
		return PATTERN_END == getCurrent();
	}
	
	public boolean isCurrentStartPattern() {
		return PATTERN_START == getCurrent();
	}
	
	public boolean isCurrentNumeric() {
		char c = getCurrent();
		return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' 
				|| c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
	}

}
