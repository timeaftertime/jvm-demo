package cn.milai.jvmdemo.runtime;

import java.util.ArrayList;
import java.util.List;

import cn.milai.jvmdemo.classfile.TypeDesc;

/**
 * 方法描述符
 * @author milai
 * @date 2021.12.15
 */
public class Descriptor {

	private String returnType;
	private String[] argsType;
	private int argsSlotCnt;

	/**
	 * 以指定字符串构造 {@link Descriptor}
	 * @param descriptor
	 */
	public Descriptor(String descriptor) {
		List<String> types = new ArrayList<String>();
		String type = null;
		int nowIndex = 1;
		int argsSlotCnt = 0;
		while ((type = getNextType(nowIndex, descriptor)) != null) {
			types.add(type);
			nowIndex += type.length();
			argsSlotCnt += (TypeDesc.of(type).needDoubleSlot()) ? 2 : 1;
		}
		returnType = descriptor.substring(nowIndex + 1);
		argsType = types.toArray(new String[0]);
		this.argsSlotCnt = argsSlotCnt;
	}

	private String getNextType(int nowIndex, String descriptor) {
		char ch = descriptor.charAt(nowIndex);
		switch (ch) {
			case 'L' : {
				StringBuilder sb = new StringBuilder();
				for (int i = nowIndex;; i++) {
					sb.append(descriptor.charAt(i));
					if (descriptor.charAt(i) == ';')
						break;
				}
				return sb.toString();
			}
			case '[' : {
				return "[" + getNextType(nowIndex + 1, descriptor);
			}
			case ')' : {
				return null;
			}
			default: {
				return "" + ch;
			}
		}
	}

	public String getReturnType() { return returnType; }

	public String[] getArgsType() { return argsType; }

	public int getArgsSlotCnt() { return argsSlotCnt; }

}
