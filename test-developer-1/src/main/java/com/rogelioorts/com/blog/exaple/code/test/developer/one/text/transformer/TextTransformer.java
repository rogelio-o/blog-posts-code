package com.rogelioorts.com.blog.exaple.code.test.developer.one.text.transformer;


public class TextTransformer {
	
	public String decompress(String inText) {
		Text text = new Text(inText);
		return this.decompress(text, 1);
	}

	private String decompress(Text text, int times) {
		StringBuilder result = new StringBuilder("");

		while(text.hasNext() && !text.isCurrentEndPattern()) {
			if(text.isCurrentNumeric()) {
				int newTimes = 0;
				while(text.hasNext() && !text.isCurrentStartPattern()) {
					newTimes = (newTimes * 10) + text.getCurrentAsInteger();
					
					text.next();
				}
				text.next(); // skip start pattern
				
				String subresult = decompress(text, newTimes);
				result.append(subresult);
			} else {
				result.append(text.getCurrent());
			}
			
			text.next();
		}
		
		return copyNTimes(result.toString(), times);
	}
	
	private String copyNTimes(String part, int times) {
		String result = "";
		for(int t = 0; t < times; t++) {
			result += part;
		}
		
		return result;
	}
	
}

