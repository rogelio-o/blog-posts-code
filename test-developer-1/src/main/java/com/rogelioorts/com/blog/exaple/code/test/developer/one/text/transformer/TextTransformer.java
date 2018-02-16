package com.rogelioorts.com.blog.exaple.code.test.developer.one.text.transformer;


public class TextTransformer {
	
	private static final char PATTERN_START = '[';
	private static final char PATTERN_END = ']';
	
	public String decompress(String text) {
		return this.decompress(text, 0, 1).getText();
	}

	private Result decompress(String text, int index, int times) {
		StringBuilder result = new StringBuilder("");
		
		int i = index;
		for(int t = 0; t < times; t++) {
			i = index;
			char c;
			while(i < text.length() && (c = text.charAt(i)) != PATTERN_END) {
				if(isNumeric(c)) {
					int newTimes = 0;
					while(i < text.length() && (c = text.charAt(i)) != PATTERN_START) {
						newTimes = (newTimes * 10) + Integer.valueOf(String.valueOf(c));
						i++;
					}
					Result subresult = decompress(text, i + 1, newTimes);
					result.append(subresult.getText());
					i = subresult.getNewIndex();
				} else {
					result.append(c);
				}
				
				i++;
			}
		}
		
		return new Result(result.toString(), i);
	}
	
	private boolean isNumeric(char c) {  
	    return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';  
	}  
	
	public class Result {
		
		private String text;
		
		private int newIndex;
		
		public Result(String text, int newIndex) {
			this.text = text;
			this.newIndex = newIndex;
		}
		
		public String getText() {
			return text;
		}
		
		public int getNewIndex() {
			return newIndex;
		}
		
	}
	
}

