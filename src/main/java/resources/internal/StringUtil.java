package resources.internal;

public class StringUtil {
	
	private StringUtil() {
		
	}
	
	public static boolean isNullOrEmpty(String string) {
		if (string == null || string.length() == 0) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isNotNullOrEmpty(String string) {
		return !isNullOrEmpty(string);
	}

}
