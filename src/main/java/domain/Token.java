package domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Token {
	
	private static Map<String, Map<String, String>> possibleValuesEncodingMap;
	
	private static Map<String, Map<String, String>> possibleValuesDecodingMap;
	
	
	static {
		possibleValuesEncodingMap = new HashMap<>();
		possibleValuesDecodingMap = new HashMap<>();
	}
	
	
	public static void createEncodingMap(String fieldName, String[] possibleValues) {
		
		int charSetSize = URLSafeChar.charSet.length;
		
		Map<String, String> encodingMap = new HashMap<>();
		Map<String, String> decodingMap = new HashMap<>();
		
		short ones = 0;
		short tens = -1;
		short hundreds = -1;
		
		for (String possibleValue: possibleValues) {
			
			String encoding = Character.toString(URLSafeChar.charSet[ones]);
			
			if (tens > -1) {
				encoding =  Character.toString(URLSafeChar.charSet[tens]) + encoding;
			}
			
			if (hundreds > -1) {
				encoding =  Character.toString(URLSafeChar.charSet[hundreds]) + encoding;
			}
			
			encodingMap.put(possibleValue, encoding);
			decodingMap.put(encoding, possibleValue);
			
			ones++;
			
			if (ones == charSetSize) {
				ones = 0;
				tens++;
			}
			
			if (tens == charSetSize) {
				tens = 0;
				hundreds++;
			}
			
		}
		
		possibleValuesEncodingMap.put(fieldName, encodingMap);
		possibleValuesDecodingMap.put(fieldName, decodingMap);
		
	}


	public Map<String, Map<String, String>> getPossibleValuesEncodingMap() {
		return possibleValuesEncodingMap;
	}


	public Map<String, Map<String, String>> getPossibleValuesDecodingMap() {
		return possibleValuesDecodingMap;
	}
	
	public abstract List<Method> fetchGetters();
	
	public abstract List<Method> fetchSetters();
	
	public abstract List<Field> fetchFields();
	
	
}
