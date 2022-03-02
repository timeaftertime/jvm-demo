package cn.milai.jvmdemo.instruction;

public class ExceptionHandle {

	public static void throwException() {
		throw new RuntimeException("oops");
	}

	public static int get() {
		int res = 0;
		try {
			throwException();
			res = 1;
		} catch (RuntimeException e) {
			res = 2;
		}
		return res;
	}
}