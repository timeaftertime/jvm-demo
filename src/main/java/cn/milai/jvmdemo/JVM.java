package cn.milai.jvmdemo;

/**
 * 启动类
 * @author milai
 * @date 2021.07.22
 */
public class JVM {

	public static final String VERSION = "JVM 1.0";
	public static final String UNKNOWN_OPTION = "Unknown option : ";
	public static final String HELP = "usage: jvm option";

	public static final String OPTION_VERSION = "-version";
	public static final String OPTION_HELP = "-?";

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println(HELP);
			return;
		}
		switch (args[0]) {
			case OPTION_VERSION :
				System.out.println(VERSION);
				return;
			case OPTION_HELP :
				System.out.println(HELP);
				return;
			default:
				System.out.println(UNKNOWN_OPTION + args[0]);
				return;
		}
	}
}
