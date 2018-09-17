package util;

public class JudgeEmpty {
	
	public static boolean isEmpty(Object obj) {
		if(obj==null) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object obj) {
		if(obj!=null) {
			return true;
		}
		return false;
	}
	
}
