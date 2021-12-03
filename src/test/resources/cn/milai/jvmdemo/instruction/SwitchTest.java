package cn.milai.jvmdemo.instruction;

public class SwitchTest {
	
	public static int tableSwitch() {
		int a = 10;
		switch (a) {
			case 10:
				a = a + 1;
				break;
			case 11:
				a = a - 1;
				break;
			case 13:
				a = a * 2;
				break;
			default:
				break;
		}
		return a;
	}
	
	public static int lookupSwitch() {
		int a = 10;
		switch (a) {
			case 11:
				a = a + 1;
				break;
			case 100:
				a = a - 1;
				break;
			case 200:
				a = a * 2;
				break;
			default:
				a = a / 2;
				break;
		}
		return a;
	}
	
}