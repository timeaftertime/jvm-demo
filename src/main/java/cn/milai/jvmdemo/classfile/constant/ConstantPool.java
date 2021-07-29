package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Class 文件常量池
 * @author milai
 * @date 2021.07.29
 */
public class ConstantPool {

	private Constant[] infos;

	public ConstantPool(DataInputStream data) throws IOException {
		int size = data.readUnsignedShort();
		infos = new Constant[size];
		for (int i = 1; i < size; i++) {
			int tag = data.readUnsignedByte();
			infos[i] = ConstantFactory.create(tag);
			infos[i].init(data, this);
			if (needDoubleSlot(infos[i])) {
				i++;
			}
		}
	}

	private static boolean needDoubleSlot(Constant c) {
		return c.tag() == ConstantTag.LONG || c.tag() == ConstantTag.DOUBLE;
	}

	public Constant get(int index) {
		if (index <= 0 || index >= infos.length)
			throw new ArrayIndexOutOfBoundsException(index);
		Constant info = infos[index];
		if (info == null)
			throw new IllegalArgumentException("序号为 " + index + " 的常量不存在");
		return info;
	}

	/**
	 * 获取包含的常量个数
	 * @return
	 */
	public int size() { return infos.length; }

	public MethodrefConstant getMethodref(int index) { return (MethodrefConstant) get(index); }

	public FieldrefConstant getFieldref(int index) { return (FieldrefConstant) get(index); }

	public StringConstant getString(int index) { return (StringConstant) get(index); }

	public ClassConstant getClass(int index) { return (ClassConstant) get(index); }

	public FloatConstant getFloat(int index) { return (FloatConstant) get(index); }

	public IntegerConstant getInteger(int index) { return (IntegerConstant) get(index); }

	public LongConstant getLong(int index) { return (LongConstant) get(index); }

	public DoubleConstant getDouble(int index) { return (DoubleConstant) get(index); }

	public NameAndTypeConstant getNameAndType(int index) { return (NameAndTypeConstant) get(index); }

	public UTF8Constant getUTF8(int index) { return (UTF8Constant) get(index); }

}
