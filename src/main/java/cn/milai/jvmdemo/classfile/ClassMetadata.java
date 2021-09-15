package cn.milai.jvmdemo.classfile;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import cn.milai.jvmdemo.classfile.attribute.Attribute;
import cn.milai.jvmdemo.classfile.attribute.Attributes;
import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * class 文件元数据
 * @author milai
 * @date 2021.08.09
 */
public class ClassMetadata {

	private static final String MAGIC_NUMBER = "CAFEBABE";
	private int majorVersion;
	private int minorVersion;
	private ConstantPool constantPool;
	private AccessMask accessMask;
	private int classIndex;
	private int superClassIndex;
	private int[] interfacesIndex;
	private ClassMember fieldMembers[];
	private ClassMember methodMembers[];
	private Attribute[] attributes;

	public ClassMetadata(InputStream in) throws IOException {
		try (DataInputStream data = new DataInputStream(in)) {
			readAndCheckMagicNumber(data);
			readAndCheckClassVersion(data);
			readAndParseConstantPool(data);
			readAccessMask(data);
			readClassAndSuperClassIndex(data);
			readInterfacesIndex(data);
			readFieldMembers(data);
			readMethodMembers(data);
			readAttributes(data);
		}
	}

	private void readAndCheckMagicNumber(DataInputStream data) throws IOException {
		for (int i = 0; i < 4; i++) {
			if (data.readUnsignedByte() != Integer.valueOf(MAGIC_NUMBER.substring(2 * i, 2 * i + 2), 16))
				throw new ClassFormatError();
		}
	}

	private void readAndCheckClassVersion(DataInputStream data) throws IOException {
		int minorVersion = data.readUnsignedShort();
		int majorVersion = data.readUnsignedShort();
		if (majorVersion > 52 || minorVersion != 0) {
			throw new UnsupportedClassVersionError();
		}
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}

	private void readAccessMask(DataInputStream data) throws IOException {
		this.accessMask = new AccessMask(data.readUnsignedShort());
	}

	public void readAndParseConstantPool(DataInputStream data) throws IOException {
		constantPool = new ConstantPool(data);
	}

	private void readClassAndSuperClassIndex(DataInputStream data) throws IOException {
		classIndex = data.readUnsignedShort();
		superClassIndex = data.readUnsignedShort();
	}

	private void readInterfacesIndex(DataInputStream data) throws IOException {
		int interfaceCount = data.readUnsignedShort();
		interfacesIndex = new int[interfaceCount];
		for (int i = 0; i < interfaceCount; i++) {
			interfacesIndex[i] = data.readUnsignedShort();
		}
	}

	private void readFieldMembers(DataInputStream data) throws IOException {
		fieldMembers = readClassMembers(data);
	}

	private void readMethodMembers(DataInputStream data) throws IOException {
		methodMembers = readClassMembers(data);
	}

	private ClassMember[] readClassMembers(DataInputStream data) throws IOException {
		int memberCount = data.readUnsignedShort();
		ClassMember[] members = new ClassMember[memberCount];
		for (int i = 0; i < memberCount; i++) {
			members[i] = new ClassMember(data, constantPool);
		}
		return members;
	}

	private void readAttributes(DataInputStream data) throws IOException {
		attributes = Attributes.readAttributes(data, constantPool);
	}

	public String getMagicNumber() { return MAGIC_NUMBER; }

	public int getMajorVersion() { return majorVersion; }

	public int getMinorVersion() { return minorVersion; }

	public ConstantPool getConstantPool() { return constantPool; }

	public AccessMask getAccessMask() { return accessMask; }

	public int getClassIndex() { return classIndex; }

	public int getSuperClassIndex() { return superClassIndex; }

	public int[] getInterfacesIndex() { return interfacesIndex; }

	public ClassMember getFieldClassMember(int index) {
		return fieldMembers[index];
	}

	public ClassMember getMethodClassMember(int index) {
		return methodMembers[index];
	}

	public Attribute[] getAttributes() { return attributes; }

	public ClassMember[] getFields() { return Arrays.copyOf(fieldMembers, fieldMembers.length); }

	public ClassMember[] getMethods() { return Arrays.copyOf(methodMembers, methodMembers.length); }
}
