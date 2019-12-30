package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import domain.Token;

public class Initializer {

	/*

	This method takes the Token class Name, extract all the fields, getter and setters, 
	sort them in alphabetic order and assign it to the respective reference passed in the argument
	*/
	public static void staticInitializer(List<Field> fields, List<Method> getters, List<Method> setters,
			Class<? extends Token> className) {
		
		for (Field field: className.getDeclaredFields()) {
			if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
				fields.add(field);
			}
		}
		
		Method[] methods = className.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				getters.add(method);
			} else if (method.getName().startsWith("set")) {
				setters.add(method);
			}
		}

		Collections.sort(fields, (a, b) -> a.getName().compareTo(b.getName()));
		Collections.sort(getters, (a, b) -> a.getName().compareTo(b.getName()));
		Collections.sort(setters, (a, b) -> a.getName().compareTo(b.getName()));
	}

}
