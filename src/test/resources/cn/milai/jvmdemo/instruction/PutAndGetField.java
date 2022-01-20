package cn.milai.jvmdemo.instruction;

public class PutAndGetField {

	private int a;
	private static int b;

	public void incA(int a) {
		this.a += a;
	}

	public static void incB(int b) {
		PutAndGetField.b += b;
	}
}
