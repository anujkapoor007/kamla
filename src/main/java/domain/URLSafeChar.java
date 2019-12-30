package domain;

public class URLSafeChar {
	
	public static final char[] charSet = {
			'A', //Capital Letters start 0
			'B',
			'C',
			'D',
			'E',
			'F',
			'G',
			'H',
			'I',
			'J',
			'K',
			'L',
			'M',
			'N',
			'O',
			'P',
			'Q',
			'R',
			'S',
			'T',
			'U',
			'V',
			'W',
			'X',
			'Y',
			'Z', //Capital Letters end 25
			'a', //Small Letters start 26
			'b',
			'c',
			'd',
			'e',
			'f',
			'g',
			'h',
			'i',
			'j',
			'k',
			'l',
			'm',
			'n',
			'o',
			'p',
			'q',
			'r',
			's',
			't',
			'u',
			'v',
			'w',
			'x',
			'y',
			'z', //Small Letters end 51
			'0', //digits start 52
			'1',
			'2',
			'3',
			'4',
			'5',
			'6',
			'7',
			'8',
			'9', //digits end 61
			'-', //62
			'_', //63
			'.'  //64
			
	};
	
	public static int getIndex(char c) {
		
		if ( c >='A' && c <='Z') {
			return  0 + c - 'A';
		} else if (c >='a' && c <='z') {
			return  26 + c - 'a';
		} else if (c >='0' && c <='9') {
			return  52 + c - '0';
		} else if (c== '-') {
			return 62;
		} else if (c== '_') {
			return 63;
		} else if (c== '.') {
			return 64;
		}
		return -1;
	}

}
