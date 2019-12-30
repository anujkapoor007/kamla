package tokens;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import domain.Token;
import util.Initializer;

public class Maestro_A extends Token {

	private String tripHash;
	
	public static List<Field> fields = new ArrayList<>();
	
	public static List<Method> getters = new ArrayList<>();
	
	public static List<Method> setters = new ArrayList<>();
	
	static {
		Initializer.staticInitializer(fields, getters, setters, Maestro_A.class);
	}


	public String getTripHash() {
		return tripHash;
	}

	public void setTripHash(String tripHash) {
		this.tripHash = tripHash;
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
		return "Layer 1 [tripHash=" + tripHash + "]";
	}
	
	

}
