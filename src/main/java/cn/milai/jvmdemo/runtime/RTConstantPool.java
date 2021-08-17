package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.constant.ClassConstant;
import cn.milai.jvmdemo.classfile.constant.Constant;
import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.classfile.constant.ConstantTag;
import cn.milai.jvmdemo.classfile.constant.FieldrefConstant;
import cn.milai.jvmdemo.classfile.constant.InterfaceMethodrefConstant;
import cn.milai.jvmdemo.classfile.constant.MethodrefConstant;
import cn.milai.jvmdemo.classfile.constant.StringConstant;
import cn.milai.jvmdemo.classfile.constant.ValueConstant;

/**
 * 运行时常量池
 * @author milai
 * @date 2021.08.16
 */
public class RTConstantPool {

	private Object[] constants;
	private ClassInfo owner;

	public RTConstantPool(ConstantPool pool, ClassInfo owner) {
		this.owner = owner;
		constants = new Object[pool.size()];
		for (int i = 1; i < pool.size(); i++) {
			Constant constant = pool.get(i);
			switch (constant.tag()) {
				case INTEGER :
				case FLOAT :
				case LONG :
				case DOUBLE :
				case UTF8 :
					ValueConstant<?> valueConstant = (ValueConstant<?>) constant;
					constants[i] = valueConstant.getValue();
					break;
				case STRING :
					constants[i] = pool.getUTF8(((StringConstant) constant).getIndex()).getValue();
					break;
				case CLASS :
					constants[i] = new ClassRef(pool, this, (ClassConstant) constant);
					break;
				case FIELDREF :
					constants[i] = new FieldRef(pool, this, (FieldrefConstant) constant);
					break;
				case METHODREF :
					constants[i] = new MethodRef(pool, this, (MethodrefConstant) constant);
					break;
				case INTERFACE_METHODREF :
					constants[i] = new InterfaceMethodref(pool, this, (InterfaceMethodrefConstant) constant);
					break;

				default:
					break;
			}
			if (ConstantTag.needDoubleSlot(constant.tag()))
				i++;
		}
	}

	public ClassInfo getOwner() { return owner; }

	public Object get(int index) {
		return constants[index];
	}

	public int getInt(int index) {
		return (int) (constants[index] == null ? 0 : constants[index]);
	}

	public long getLong(int index) {
		return (long) (constants[index] == null ? 0 : constants[index]);
	}

	public float getFloat(int index) {
		return (float) (constants[index] == null ? 0 : constants[index]);
	}

	public double getDouble(int index) {
		return (double) (constants[index] == null ? 0 : constants[index]);
	}

	public String getString(int index) {
		return (String) constants[index];
	}

	public ClassRef getClassRef(int index) {
		return (ClassRef) constants[index];
	}

	public FieldRef getFieldRef(int index) {
		return (FieldRef) constants[index];
	}

	public MethodRef getMethodRef(int index) {
		return (MethodRef) constants[index];
	}

	public InterfaceMethodref getInterfaceMethodRef(int index) {
		return (InterfaceMethodref) constants[index];
	}

	public int size() {
		return constants.length;
	}

}
