package cn.milai.jvmdemo.instruction;

public class NativeCaller {

	public static int newHashCode() {
		return new Object().hashCode();
	}
	
	public static int[] arrayCopy() {
		int[] from = new int[]{1, 2, 3};
		int[] to = new int[3];
		System.arraycopy(from, 0, to, 0, 3);
		return to;
	}
	
	public static double[] arrayCopyDouble() {
		double[] from = new double[] {1.1, 2.1, 3.1};
		double[] to = new double[4];
		System.arraycopy(from, 0, to, 1, from.length);
		return to;
	}
}
