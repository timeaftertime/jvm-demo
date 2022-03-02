package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.attribute.LineNumberTable;

/**
 * 字节码 PC 与源代码行数的对应关系
 * @author milai
 * @date 2022.02.28
 */
public class LineNumberResolver {

	private LineNumberTable[] tables;

	public LineNumberResolver(LineNumberTable[] lineNumberTables) {
		tables = lineNumberTables;
	}

	/**
	 * 将指定 pc 转换为源代码行数，若转换失败，返回 -1
	 * @param pc
	 * @return
	 */
	public int resolve(int pc) {
		if (tables != null) {
			for (int i = tables.length - 1; i >= 0; i--) {
				if (pc >= tables[i].getStartPC()) {
					return tables[i].getLineNumber();
				}
			}
		}
		return -1;
	}

}
