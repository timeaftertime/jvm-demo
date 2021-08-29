package cn.milai.jvmdemo;

/**
 * 环境变量
 * @author milai
 * @date 2021.08.27
 */
public class Env {

	public static final String JAVA_HOME = javaHome();

	private static String javaHome() {
		String home = System.getProperty("java.home").replace('\\', '/');
		if (!home.endsWith("/")) {
			home = home + "/";
		}
		return home;
	}

}
