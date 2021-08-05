package cn.milai.jvmdemo.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.attribute.Attribute;
import cn.milai.jvmdemo.classfile.attribute.Attributes;
import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * class 文件类成员
 * @author milai
 * @date 2021.08.02
 */
public class ClassMember {

	protected AccessMask accessMask;
	protected int nameIndex;
	protected int descriptorIndex;
	protected Attribute[] attributes;

	public ClassMember(DataInputStream data, ConstantPool constantPool) throws IOException {
		accessMask = new AccessMask(data.readUnsignedShort());
		nameIndex = data.readUnsignedShort();
		descriptorIndex = data.readUnsignedShort();
		attributes = Attributes.readAttributes(data, constantPool);
	}

	public AccessMask getAccessMask() { return accessMask; }

	public int getNameIndex() { return nameIndex; }

	public int getDescriptorIndex() { return descriptorIndex; }

	@Override
	public String toString() {
		return "ClassMember[nameIndex=" + nameIndex + ", descriptorIndex=" + descriptorIndex + "]";
	}

}
