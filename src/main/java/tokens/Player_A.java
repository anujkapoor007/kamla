package tokens;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import constants.PossibleValues;
import domain.Token;
import util.Initializer;

public class Player_A extends Token {

	private String isLCC;

	private String fareSourceCode;
	
	public static List<Field> fields = new ArrayList<>();
	
	public static List<Method> getters = new ArrayList<>();
	
	public static List<Method> setters = new ArrayList<>();
	
	static {
		Initializer.staticInitializer(fields, getters, setters, Player_A.class);
		Player_A.createEncodingMap("isLCC", PossibleValues.POSSIBLE_YES_NO);
	}



	public String getIsLCC() {
		return isLCC;
	}

	public void setIsLCC(String isLCC) {
		this.isLCC = isLCC;
	}

	public String getFareSourceCode() {
		return fareSourceCode;
	}

	public void setFareSourceCode(String fareSourceCode) {
		this.fareSourceCode = fareSourceCode;
	}
	
	@Override
	public List<Method> fetchGetters() {
		return getters;
	}


	@Override
	public List<Method> fetchSetters() {
		return setters;
	}

	@Override
	public List<Field> fetchFields() {
		return fields;
	}

	@Override
	public String toString() {
		return "Layer 4 [isLCC=" + isLCC + ", fareSourceCode=" + fareSourceCode + "]";
	}

	
	
}
