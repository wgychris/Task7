package utils;

public class dataConversion {

	public static long convertFromStringToTwoDigitLong(String s) {
		if (s.indexOf('.') != -1) {
			String s1 = s.substring(0, s.indexOf('.'));
			String s2 = "";
			if (s.indexOf('.') + 3 <= s.length()) {
				s2 = s.substring(s.indexOf('.') + 1, s.indexOf('.') + 3);
			} else {
				s2 = s.substring(s.indexOf('.') + 1);
			}
			return Long.parseLong(s1 + s2);
		} else {
			StringBuffer sb = new StringBuffer(s);
			sb.append('0');
			sb.append('0');
			return Long.parseLong(sb.toString());
		}
	}

	public static long convertFromStringToThreeDigitLong(String s) {
		if (s.indexOf('.') != -1) {
			String s1 = s.substring(0, s.indexOf('.'));
			String s2 = "";
			if (s.indexOf('.') + 4 <= s.length()) {
				s2 = s.substring(s.indexOf('.') + 1, s.indexOf('.') + 4);
			} else {
				s2 = s.substring(s.indexOf('.') + 1);
			}
			return Long.parseLong(s1 + s2);
		} else {
			StringBuffer sb = new StringBuffer(s);
			sb.append('0');
			sb.append('0');
			sb.append('0');
			return Long.parseLong(sb.toString());
		}
	}

	public static boolean isDate(String s) {
		try {
			java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(
					"yyyy-mm-dd");
			sf.parse(s);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	// public static void main(String args[]){
	// System.out.println(convertFromStringToThreeDigitLong("321.9012"));
	// }
}
