package cn.milai.jvmdemo.instruction;

public class CallMethods {

	public double call(int a, int b) {
		return sum(a, b) + sub(a, b) + mul(a, b) + div(a, b);
	}

	private int sum(int a, int b) {
		return a + b;
	}

	protected int sub(int a, int b) {
		return a - b;
	}

	long mul(long a, long b) {
		return a * b;
	}

	public static double div(double a, double b) {
		return a / b;
	}

}
