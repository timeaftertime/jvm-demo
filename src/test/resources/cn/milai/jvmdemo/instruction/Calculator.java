package cn.milai.jvmdemo.instruction;

public class Calculator {

	private int value;

	public Calculator(int initialValue) {
		this.value = initialValue;
	}

	public long[] accumulate(int[][] questions) {
		long[] ans = new long[questions.length];
		for (int j = 0; j < questions.length; j++) {
			if (questions[j].length < 2) {
				return null;
			}
			int from = questions[j][0];
			int to = questions[j][1];
			if (from > to) {
				int tmp = from;
				from = to;
				to = tmp;
			}
			ans[j] = value;
			for (int i = from; i <= to; i++) {
				ans[j] = sum(ans[j], i);
			}
		}
		return ans;
	}

	public static long sum(long a, long b) {
		return a + b;
	}

	public static long[] testEntry() {
		Calculator c = new Calculator(10);
		return c.accumulate(new int[][] { { 1, 100 }, { 100, 1 } });
	}

}
