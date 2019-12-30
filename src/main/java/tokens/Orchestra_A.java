package tokens;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import constants.PossibleValues;
import domain.Token;
import util.Initializer;

public class Orchestra_A extends Token {
	
	private String inventorySourceName;
	
	private String inventorySourcePseudoCity;
	
	private String inventorySourcePointOfSale;
	
	private List<String> inventorySourceAccountCodes;
	
	
	private String inventoryTargetName;
	
	private String inventoryTargetPseudoCity;
	
	private String inventoryTargetPointOfSale;
	
	private List<String> inventoryTargetAccountCodes;
	
	public static List<Field> fields = new ArrayList<>();
	
	public static List<Method> getters = new ArrayList<>();
	
	public static List<Method> setters = new ArrayList<>();
	
	static {
		Initializer.staticInitializer(fields, getters, setters, Orchestra_A.class);
		Orchestra_A.createEncodingMap("inventorySourceName", PossibleValues.POSSIBLE_INVENTORY_SOURCES);
		Orchestra_A.createEncodingMap("inventorySourcePseudoCity", PossibleValues.POSSIBLE_PSUEDO_CITIES);
		Orchestra_A.createEncodingMap("inventorySourcePointOfSale", PossibleValues.POSSIBLE_POINT_OF_SALES);
		Orchestra_A.createEncodingMap("inventorySourceAccountCodes", PossibleValues.POSSIBLE_ACCOUNT_CODES);
		
		Orchestra_A.createEncodingMap("inventoryTargetName", PossibleValues.POSSIBLE_INVENTORY_SOURCES);
		Orchestra_A.createEncodingMap("inventoryTargetPseudoCity", PossibleValues.POSSIBLE_PSUEDO_CITIES);
		Orchestra_A.createEncodingMap("inventoryTargetPointOfSale", PossibleValues.POSSIBLE_POINT_OF_SALES);
		Orchestra_A.createEncodingMap("inventoryTargetAccountCodes", PossibleValues.POSSIBLE_ACCOUNT_CODES);
	}
	

	public String getInventorySourceName() {
		return inventorySourceName;
	}

	public void setInventorySourceName(String inventorySourceName) {
		this.inventorySourceName = inventorySourceName;
	}

	public String getInventorySourcePseudoCity() {
		return inventorySourcePseudoCity;
	}

	public void setInventorySourcePseudoCity(String inventorySourcePseudoCity) {
		this.inventorySourcePseudoCity = inventorySourcePseudoCity;
	}

	public String getInventorySourcePointOfSale() {
		return inventorySourcePointOfSale;
	}

	public void setInventorySourcePointOfSale(String inventorySourcePointOfSale) {
		this.inventorySourcePointOfSale = inventorySourcePointOfSale;
	}

	public List<String> getInventorySourceAccountCodes() {
		return inventorySourceAccountCodes;
	}

	public void setInventorySourceAccountCodes(List<String> inventorySourceAccountCodes) {
		this.inventorySourceAccountCodes = inventorySourceAccountCodes;
	}

	public String getInventoryTargetName() {
		return inventoryTargetName;
	}

	public void setInventoryTargetName(String inventoryTargetName) {
		this.inventoryTargetName = inventoryTargetName;
	}

	public String getInventoryTargetPseudoCity() {
		return inventoryTargetPseudoCity;
	}

	public void setInventoryTargetPseudoCity(String inventoryTargetPseudoCity) {
		this.inventoryTargetPseudoCity = inventoryTargetPseudoCity;
	}

	public String getInventoryTargetPointOfSale() {
		return inventoryTargetPointOfSale;
	}

	public void setInventoryTargetPointOfSale(String inventoryTargetPointOfSale) {
		this.inventoryTargetPointOfSale = inventoryTargetPointOfSale;
	}

	public List<String> getInventoryTargetAccountCodes() {
		return inventoryTargetAccountCodes;
	}

	public void setInventoryTargetAccountCodes(List<String> inventoryTargetAccountCodes) {
		this.inventoryTargetAccountCodes = inventoryTargetAccountCodes;
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
		return "Layer 3 [inventorySourceName=" + inventorySourceName + ", inventorySourcePseudoCity="
				+ inventorySourcePseudoCity + ", inventorySourcePointOfSale=" + inventorySourcePointOfSale
				+ ", inventorySourceAccountCodes=" + inventorySourceAccountCodes + ", inventoryTargetName="
				+ inventoryTargetName + ", inventoryTargetPseudoCity=" + inventoryTargetPseudoCity
				+ ", inventoryTargetPointOfSale=" + inventoryTargetPointOfSale + ", inventoryTargetAccountCodes="
				+ inventoryTargetAccountCodes + "]";
	}
	
	
	

}
