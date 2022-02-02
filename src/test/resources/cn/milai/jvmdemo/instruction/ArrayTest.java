package cn.milai.jvmdemo.instruction;

public class ArrayTest {

	Object[][][] array = new Object[3][4][5];

	public int test(int v) {
		if (this.array == null) {
			return v + 1;
		} else {
			if (this.array[0] == null) {
				return v + 2;
			} else if (this.array[0][0] == null) {
				return v + 1 + 2;
			} else {
				return v + 1 + 2 + 3;
			}
		}
	}
}