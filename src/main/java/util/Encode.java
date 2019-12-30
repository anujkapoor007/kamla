package util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import constants.Delimiters;
import domain.Token;
import domain.URLSafeChar;
import exception.IllegalTokenFormatException;


public class Encode {
	
	
	/*
	 * This function takes version, and an array of token objects as arguments,
	 * and returns an encoded token string
	 */
	
	public static String encodeToken(Character versionInUse, Token[] tokens) {

		StringBuilder encodedToken = new StringBuilder();
		
		encodedToken.append(versionInUse);

		for (int i = 0; i < tokens.length; i++) {

			Map<String, Map<String, String>> encodingMap = tokens[i].getPossibleValuesEncodingMap();

			for (int j = 0; j < tokens[i].fetchGetters().size(); j++) {

				if (j == URLSafeChar.charSet.length) {
					break;
				}

				Object value = null;
				try {
					value = tokens[i].fetchGetters().get(j).invoke(tokens[i]);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.out.println("There is a problem in declaration format of fields, getters or setters");
					e.printStackTrace();
					throw new IllegalTokenFormatException();
				}

				if (value != null) {
					//Layer's field converted to a character index
					encodedToken.append(URLSafeChar.charSet[j]);
					if (value instanceof List) {
						for(String listValue: (List<String>) value ) {
							appendValueToToken(tokens, encodedToken, i, encodingMap, j, listValue);
							encodedToken.append(Delimiters.LIST_DELIMITER);
						}
						encodedToken.deleteCharAt(encodedToken.length() - 1);
						
					} else if (!value.toString().isEmpty()) {
						
						appendValueToToken(tokens, encodedToken, i, encodingMap, j, value);
					}
					
					encodedToken.append(Delimiters.PAIR_DELIMITER);
				}
			}

			if (encodedToken.length() > 0
					&& encodedToken.charAt(encodedToken.length() - 1) == Delimiters.PAIR_DELIMITER) {
				encodedToken.deleteCharAt(encodedToken.length() - 1);
			}

			encodedToken.append(Delimiters.TOKEN_DELIMITER);

		}

		if (encodedToken.length() > 0 && encodedToken.charAt(encodedToken.length() - 1) == Delimiters.PAIR_DELIMITER) {
			encodedToken.deleteCharAt(encodedToken.length() - 1);
		}

		return encodedToken.toString();
	}

	/*
	 * This function takes an array of tokens, token string built so far, token index, encoding map, field index of token's layer
	 * object and fields value as argument. The function checks if an encoding map exists for a particular field, if it does,
	 * then it adds the encoded index to the token, else it simply adds the value as is to the tken string.
	 */
	
	private static void appendValueToToken(Token[] tokens, StringBuilder encodedToken, int i,
			Map<String, Map<String, String>> encodingMap, int j, Object value) {
		if (encodingMap != null && encodingMap.get(tokens[i].fetchFields().get(j).getName()) != null
				&& encodingMap.get(tokens[i].fetchFields().get(j).getName()).get(value) != null) {
			encodedToken.append(encodingMap.get(tokens[i].fetchFields().get(j).getName()).get(value));
		} else {
			encodedToken.append(value.toString());
		}
	}

}
