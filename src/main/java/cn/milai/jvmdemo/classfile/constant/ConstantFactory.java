package cn.milai.jvmdemo.classfile.constant;

/**
 * {@link Constant} 静态工厂
 * @author milai
 * @date 2021.07.29
 */
public class ConstantFactory {

	private ConstantFactory() {}

	/**
	 * 构造指定 tag 类型的 {@link Constant}
	 * @param tag
	 * @return
	 */
	public static Constant create(int tag) {
		switch (ConstantTag.of(tag)) {
			case UTF8 :
				return new UTF8Constant();
			case INTEGER :
				return new IntegerConstant();
			case FLOAT :
				return new FloatConstant();
			case LONG :
				return new LongConstant();
			case DOUBLE :
				return new DoubleConstant();
			case CLASS :
				return new ClassConstant();
			case STRING :
				return new StringConstant();
			case FIELDREF :
				return new FieldrefConstant();
			case METHODREF :
				return new MethodrefConstant();
			case INTERFACE_METHODREF :
				return new InterfaceMethodrefConstant();
			case NAME_AND_TYPE :
				return new NameAndTypeConstant();

			case METHOD_HANDLE :
				return new MethodHandleConstant();
			case METHOD_TYPE :
				return new MethodTypeConstant();
			case INVOKE_DYNAMIC :
				return new InvokeDynamicConstant();

			default:
				throw new IllegalArgumentException("不支持的常量类型:" + tag);
		}
	}

}
