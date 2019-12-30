package util;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import domain.Token;
import exception.UnsupportedVersionException;
import tokens.Maestro_A;
import tokens.Orchestra_A;
import tokens.Player_A;
import tokens.Symphony_A;

public class DecodeTest {

	@Test(expected = UnsupportedVersionException.class)
	public void unsupportedVersion_Test()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Character latestVersion = VersionManager.getLatestVersion();

		Character nextVersion = (char) (latestVersion + 1);

		String encodedString = nextVersion.toString();

		Decode.decodeToken(encodedString);

	}

	@Test
	public void decode_ALL_VALUES_Test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Character versionInUse = 'A';

		Token[] tokens = VersionManager.getTokensForVersion(versionInUse);

		List<String> sourceAccountCodes = new LinkedList<String>();
		List<String> targetAccountCodes = new LinkedList<String>();

		sourceAccountCodes.add("ACC123");
		sourceAccountCodes.add("ACC456");

		targetAccountCodes.add("SALEABC");
		targetAccountCodes.add("SALEXYZ");

		Orchestra_A orchestra = (Orchestra_A) tokens[1];
		orchestra.setInventorySourceName("GOOGLE");
		orchestra.setInventorySourceAccountCodes(sourceAccountCodes);
		orchestra.setInventorySourcePointOfSale("US");
		orchestra.setInventorySourcePseudoCity("HP1");

		orchestra.setInventoryTargetName("YAHOO");
		orchestra.setInventoryTargetAccountCodes(targetAccountCodes);
		orchestra.setInventoryTargetPointOfSale("US");
		orchestra.setInventoryTargetPseudoCity("KJJ");

		Symphony_A symphony = (Symphony_A) tokens[2];

		symphony.setFareSourceType("GDS");
		symphony.setFareType("PUBLISHED");
		symphony.setMerchantOfRecord("ABC");
		symphony.setSupplierPaymentType("CREDIT_CARD");
		symphony.setReferral("YAHOO");

		Maestro_A maestro = (Maestro_A) tokens[3];
		maestro.setTripHash("12443");

		Player_A player = (Player_A) tokens[0];
		player.setIsLCC("Y");
		player.setFareSourceCode("UVZEeVhDZ2RvbEZlYlFaV2d5WWR");

		String encodedString = Encode.encodeToken(versionInUse, tokens);
		Token[] decodedTokens = Decode.decodeToken(encodedString);

		for (int i = 0; i < decodedTokens.length; i++) {
			for (int j = 0; j < decodedTokens[i].fetchGetters().size(); j++) {

				assertTrue(decodedTokens[i].fetchGetters().get(j).invoke(decodedTokens[i])
						.equals(tokens[i].fetchGetters().get(j).invoke(tokens[i])));
			}

		}

	}

}
