package tokens;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import constants.PossibleValues;
import domain.Token;
import util.Initializer;

public class Symphony_A extends Token {
	
	private String merchantOfRecord;
	
	private String fareType;
	
	private String fareSourceType;
	
	private String supplierPaymentType;
	
	private String referral;
	
	public static List<Field> fields = new ArrayList<>();
	
	public static List<Method> getters = new ArrayList<>();
	
	public static List<Method> setters = new ArrayList<>();
	
	static {
		Initializer.staticInitializer(fields, getters, setters, Symphony_A.class);
		Symphony_A.createEncodingMap("merchantOfRecord", PossibleValues.POSSIBLE_MERCHANT_OF_RECORDS);
		Symphony_A.createEncodingMap("fareType", PossibleValues.POSSIBLE_FARE_TYPES);
		Symphony_A.createEncodingMap("fareSourceType", PossibleValues.POSSIBLE_FARE_SOURCE_TYPES);
		Symphony_A.createEncodingMap("supplierPaymentType", PossibleValues.POSSIBLE_SUPPLIER_PAYMENT_TYPES);
		Symphony_A.createEncodingMap("referral", PossibleValues.PSSIBLE_REFERRAL_TYPES);
	}

	public String getMerchantOfRecord() {
		return merchantOfRecord;
	}

	public void setMerchantOfRecord(String merchantOfRecord) {
		this.merchantOfRecord = merchantOfRecord;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getFareSourceType() {
		return fareSourceType;
	}

	public void setFareSourceType(String fareSourceType) {
		this.fareSourceType = fareSourceType;
	}

	public String getSupplierPaymentType() {
		return supplierPaymentType;
	}

	public void setSupplierPaymentType(String supplierPaymentType) {
		this.supplierPaymentType = supplierPaymentType;
	}

	public String getReferral() {
		return referral;
	}

	public void setReferral(String referral) {
		this.referral = referral;
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
		return "Layer 2 [merchantOfRecord=" + merchantOfRecord + ", fareType=" + fareType + ", fareSourceType="
				+ fareSourceType + ", supplierPaymentType=" + supplierPaymentType + ", referral=" + referral + "]";
	}
	
	

}
