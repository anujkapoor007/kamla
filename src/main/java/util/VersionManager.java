package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import domain.Token;
import exception.IllegalVersionClassException;
import tokens.Maestro_A;
import tokens.Orchestra_A;
import tokens.Player_A;
import tokens.Symphony_A;

public class VersionManager {

	private static TreeMap<Character, List<Class<? extends Token>>> versions = new TreeMap<>();

	static {

		List<Class<? extends Token>> versionATokens = new ArrayList<>();

		versionATokens.add(Player_A.class);
		versionATokens.add(Orchestra_A.class);
		versionATokens.add(Symphony_A.class);
		versionATokens.add(Maestro_A.class);

		versions.put('A', versionATokens);

	}

	public static Token[] getTokensForVersion(Character c) {

		Token[] tokens = null;

		if (versions.get(c) != null) {
			tokens = new Token[versions.get(c).size()];
			List<Class<? extends Token>> tokenList = versions.get(c);
			for (int i = 0; i < tokenList.size(); i++) {
				try {
					tokens[i] = tokenList.get(i).newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
					throw new IllegalVersionClassException();
				}
			}
		}
		
		return tokens;

	}
	
	public static Character getLatestVersion(){

		if (versions.size() == 0) {
			return null;
		}

		Entry<Character, List<Class<? extends Token>>> lastEntry = versions.lastEntry();
		
		return lastEntry.getKey();

	}

}
