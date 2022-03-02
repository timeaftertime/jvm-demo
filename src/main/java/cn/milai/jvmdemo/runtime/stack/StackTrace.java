package cn.milai.jvmdemo.runtime.stack;

/**
 * 堆栈信息
 * @author milai
 * @date 2022.02.28
 */
public class StackTrace {

	private String fileName;
	private String className;
	private String method;
	private int line;

	public StackTrace(String fileName, String className, String method, int line) {
		this.fileName = fileName;
		this.className = className;
		this.method = method;
		this.line = line;
	}

	@Override
	public String toString() {
		return "at " + className + "." + method + " (" + fileName + ":" + line + ")";
	}

}
