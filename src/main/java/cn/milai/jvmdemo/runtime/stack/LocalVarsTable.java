package cn.milai.jvmdemo.runtime.stack;

import cn.milai.jvmdemo.runtime.slot.TableSlots;

/**
 * 局部变量表
 * @author milai
 * @date 2021.09.26
 */
public class LocalVarsTable extends TableSlots {

	public LocalVarsTable(int capacity) {
		super(capacity);
	}

}
