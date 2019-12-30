package util;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import domain.Token;
import tokens.Maestro_A;
import tokens.Orchestra_A;
import tokens.Player_A;
import tokens.Symphony_A;

public class EncodeTest {

	@Test
	public void encode_ALL_VALUES_Test() {

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

		assertEquals("AAUVZEeVhDZ2RvbEZlYlFaV2d5WWR~BA#AA,B~BB~CA~De~EC,D~FC~GA~Hg#AA~BA~CA~DB~EB#A12443#",
				Encode.encodeToken(versionInUse, tokens));

	}

}
