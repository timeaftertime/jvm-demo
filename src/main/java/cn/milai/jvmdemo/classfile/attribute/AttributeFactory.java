package cn.milai.jvmdemo.classfile.attribute;

/**
 * {@link Attribute} 工厂类
 * @author milai
 * @date 2021.08.05
 */
public class AttributeFactory {

	private AttributeFactory() {}

	public static Attribute createAttribute(int attrNameIndex, String attrName, int attrLen) {
		AttributeName name = AttributeName.find(attrName);
		if (name != null) {
			switch (name) {
				case CODE :
					return new CodeAttribute(attrNameIndex, attrLen);
				case CONSTANT_VALUE :
					return new ConstantValueAttribute(attrNameIndex, attrLen);
				case DEPRECATED :
					return new DeprecatedAttribute(attrNameIndex, attrLen);
				case EXCEPTIONS :
					return new ExceptionsAttribute(attrNameIndex, attrLen);
				case LINE_NUMBER_TABLE :
					return new LineNumberTableAttribute(attrNameIndex, attrLen);
				case LOCAL_VARIABLE_TABLE :
					return new LocalVariableTableAttribute(attrNameIndex, attrLen);
				case SOURCE_FILE :
					return new SourceFileAttribute(attrNameIndex, attrLen);
				case SYNTHETIC :
					return new SyntheticAttribute(attrNameIndex, attrLen);
			}
		}
		return new UnknownAttribute(attrNameIndex, attrLen);
	}

}
