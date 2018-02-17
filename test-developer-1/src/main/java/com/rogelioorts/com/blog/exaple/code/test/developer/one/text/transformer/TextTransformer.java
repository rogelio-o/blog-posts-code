package com.rogelioorts.com.blog.exaple.code.test.developer.one.text.transformer;

import java.util.Collections;
import java.util.stream.Collectors;

public class TextTransformer {
	
	public String decompress(String inText) {
		Text text = new Text(inText);
		return this.decompress(text, 1);
	}

	private String decompress(Text text, int times) {
		StringBuilder result = new StringBuilder("");

		while(text.hasNext() && !text.isCurrentEndPattern()) {
			if(text.isCurrentNumeric()) {
				int newTimes = getPatternCopyTimes(text);

				text.skipPatternStart();
				
				String subresult = decompress(text, newTimes);
				result.append(subresult);
			} else {
				result.append(text.getCurrent());
			}
			
			text.next();
		}
		
		return copyNTimes(result.toString(), times);
	}
	
	private int getPatternCopyTimes(Text text) {
		int result = 0;
		while(text.hasNext() && !text.isCurrentStartPattern()) {
			result = (result * 10) + text.getCurrentAsInteger();
			
			text.next();
		}
		
		return result;
	}
	
	private String copyNTimes(String part, int times) {
		return Collections.nCopies(times, part).stream()
				.collect(Collectors.joining());
	}
	
}

