package util;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import constants.Delimiters;
import domain.Token;
import domain.URLSafeChar;
import exception.IllegalTokenFormatException;
import exception.UnsupportedVersionException;

public class Decode {

	public static Token[] decodeToken(String token) {

		Character versionInUse = token.charAt(0);

		Token[] tokens = null;

		// create instances of version classes
		tokens = VersionManager.getTokensForVersion(versionInUse);

		if (tokens == null) {
			throw new UnsupportedVersionException();
		}

		token = token.substring(1);

		String[] splittedToken = token.split(Character.toString(Delimiters.TOKEN_DELIMITER));

		for (int i = 0; i < splittedToken.length && i < tokens.length; i++) {

			if (splittedToken[i].length() == 0) {
				continue;
			}

			String[] layerToken = splittedToken[i].split(Character.toString(Delimiters.PAIR_DELIMITER));

			for (int j = 0; j < layerToken.length; j++) {

				if (layerToken[j] == null || layerToken[j].isEmpty()) {
					continue;
				}

				int fieldIndex = URLSafeChar.getIndex(layerToken[j].charAt(0));

				String value = layerToken[j].substring(1);

			// If the field is a list then delimited fields are processed separately.
				if (tokens[i].fetchFields().get(fieldIndex).getType() == List.class) {
					String[] listValues = value.split(Character.toString(Delimiters.LIST_DELIMITER));
					List<String> tokenValue = new LinkedList<String>();

					for (String listValue : listValues) {

						if (tokens[i].getPossibleValuesDecodingMap() != null
								&& tokens[i].getPossibleValuesDecodingMap()
										.get(tokens[i].fetchFields().get(fieldIndex).getName()) != null
								&& tokens[i].getPossibleValuesDecodingMap()
										.get(tokens[i].fetchFields().get(fieldIndex).getName())
										.get(listValue) != null) {

							tokenValue.add(tokens[i].getPossibleValuesDecodingMap()
									.get(tokens[i].fetchFields().get(fieldIndex).getName()).get(listValue));

						} else {
							tokenValue.add(listValue);
						}
					}

					try {
						tokens[i].fetchSetters().get(fieldIndex).invoke(tokens[i], tokenValue);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						throw new IllegalTokenFormatException();
					}

					// If the field is not a list.
				} else {

					if (tokens[i].getPossibleValuesDecodingMap() != null
							&& tokens[i].getPossibleValuesDecodingMap()
									.get(tokens[i].fetchFields().get(fieldIndex).getName()) != null
							&& tokens[i].getPossibleValuesDecodingMap()
									.get(tokens[i].fetchFields().get(fieldIndex).getName()).get(value) != null) {

						try {
							tokens[i].fetchSetters().get(fieldIndex).invoke(tokens[i],
									tokens[i].getPossibleValuesDecodingMap()
											.get(tokens[i].fetchFields().get(fieldIndex).getName()).get(value));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
							throw new IllegalTokenFormatException();
						}

					} else {
						try {
							tokens[i].fetchSetters().get(fieldIndex).invoke(tokens[i], value);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
							throw new IllegalTokenFormatException();
						}
					}
				}

			}

		}

		return tokens;

	}

}
