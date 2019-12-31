# kamla

## Introduction
Kamla is a token encoder and decoder, that specializes in generating minified tokens for your application. It works on the fundamental idea of sending indexes, instead of values, in order to generate a minified URL-safe token. Because it encodes key-value to an index of a table, the size of the encoded string does not depend on the size of values. 

For Example if we have a field key1 (indexed as B, explained later) with a list of possible values, represented in the table as follows:-

| Index | value |
| ------ | ------ |
| A | some value|
| B | another value|
| C | another bigger value|
| D | a rare value|


To represent "key1: another bigger value", we can just use 2 characters, "BC". Because we encode to indexes rather than value, the encoded string does not grow with size of the value. If "another bigger value" was a far bigger string, it would still be represented as "BC".

This article explains the underlying concept well.
https://www.linkedin.com/pulse/building-stateless-systems-minified-tokens-anuj-kapoor/

## How to use it?

We need to follow few rules :-

  - The token is made up of blocks of key-value pair, with each block having up to 65 fields
  - 'Value' in a key value pair is either a string or a List of string values.
  - We need to create sepearte class for each version of token, so that we can support multiple versions at a given time.
  

**Step 1: Create your classes for different token blocks**
Token blocks goes in token package, the classes Maestro_A, Orchestra_A, Player_A and Symphony_A have been provided as a template. Whent you create your own token block version class, make sure to extend Token class.


``` public class TokenBlock_version extends Token {```

Your token block must contain these static fields and a static initializer.

```	
public static List<Field> fields = new ArrayList<>();
public static List<Method> getters = new ArrayList<>();
public static List<Method> setters = new ArrayList<>();
	
static {
	Initializer.staticInitializer(fields, getters, setters, TokenBlock_version.class);
} 
  ```
**Step 2: Follow standard naming conventions for getters and setters for your fields**

**Step 3: Create String arrays for possible values of various fields, that you created in your token classes**

  Refer to PossibleValues.java in constants package

**Step 4: Create static encoding Maps**

Here is an example :-
```
Orchestra_A.createEncodingMap("inventorySourceName", PossibleValues.POSSIBLE_INVENTORY_SOURCES);
Orchestra_A.createEncodingMap("inventorySourcePseudoCity", PossibleValues.POSSIBLE_PSUEDO_CITIES);
Orchestra_A.createEncodingMap("inventorySourcePointOfSale", PossibleValues.POSSIBLE_POINT_OF_SALES);
Orchestra_A.createEncodingMap("inventorySourceAccountCodes", PossibleValues.POSSIBLE_ACCOUNT_CODES);
		
Orchestra_A.createEncodingMap("inventoryTargetName", PossibleValues.POSSIBLE_INVENTORY_SOURCES);
Orchestra_A.createEncodingMap("inventoryTargetPseudoCity", PossibleValues.POSSIBLE_PSUEDO_CITIES);
Orchestra_A.createEncodingMap("inventoryTargetPointOfSale", PossibleValues.POSSIBLE_POINT_OF_SALES);
Orchestra_A.createEncodingMap("inventoryTargetAccountCodes", PossibleValues.POSSIBLE_ACCOUNT_CODES);

```

**Step 5: Configure version manager**

In util.VersionManager, create a list of token blocks, that form your token and then assign a version to that token.

For Example:-

Here we create a token containing 4 blocks, Player_A, Orchestra_A, Symphony_A and Maestro_A. Then we assign a version 'A' in versions.put statement. You can use any chracter from UrlSafeChar.java, to get a url safe character for your version.

```
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
```

**Step 6: Encode**

use the function encodeToken to encode
```Encode.encodeToken(versionInUse, tokens)```

**Step 7: Decode**

use the function decodeToken to decode
```Decode.decodeToken(encodedString);```


License
----

MIT
