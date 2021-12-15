package cn.milai.jvmdemo.instruction;

public class MethodInvoke {

	public static void main(String[] var0) {
		int var1 = (new MethodInvoke()).add(1, 2);
		int var2 = max(var1, 3);
		System.out.println(var2);
	}

	public int add(int var1, int var2) {
		return var1 + var2;
	}

	public static int max(int var0, int var1) {
		return var0 > var1 ? var0 : var1;
	}

	public static void call(Comparable<String> c) {
		c.compareTo(null);
	}
}