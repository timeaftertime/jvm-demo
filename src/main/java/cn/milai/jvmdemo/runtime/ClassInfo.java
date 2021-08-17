package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.AccessMask;
import cn.milai.jvmdemo.classfile.ClassMetadata;
import cn.milai.jvmdemo.classfile.attribute.Attribute;
import cn.milai.jvmdemo.classfile.attribute.SourceFileAttribute;
import cn.milai.jvmdemo.classfile.constant.ClassConstant;
import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.util.ClassNames;

/**
 * 类信息
 * @author milai
 * @date 2021.08.17
 */
public class ClassInfo {

	private String name;
	private String superName;
	private String[] interfacesName;
	private AccessMask access;
	private RTConstantPool pool;
	private String sourceFileName;

	public ClassInfo(ClassMetadata metadata) {
		parseConstantPool(metadata);
		parseClassName(metadata);
		parseSuperClassName(metadata);
		parseInterfacesName(metadata);
		parseAccessMask(metadata);
		parseFields(metadata, pool);
		parseMethods(metadata, pool);
		parseSourceFileName(metadata, pool);
	}

	private void parseConstantPool(ClassMetadata metadata) {
		pool = new RTConstantPool(metadata.getConstantPool(), this);
	}

	private void parseClassName(ClassMetadata metadata) {
		name = getClassConstantInfoName(metadata.getClassIndex(), metadata);
	}

	private void parseSuperClassName(ClassMetadata metadata) {
		if (metadata.getSuperClassIndex() == 0)
			return;
		superName = getClassConstantInfoName(metadata.getSuperClassIndex(), metadata);
	}

	private void parseInterfacesName(ClassMetadata metadata) {
		int[] interfaces = metadata.getInterfacesIndex();
		interfacesName = new String[interfaces.length];
		for (int i = 0; i < interfaces.length; i++)
			interfacesName[i] = getClassConstantInfoName(interfaces[i], metadata);
	}

	private void parseAccessMask(ClassMetadata metadata) {
		access = metadata.getAccessMask();
	}

	private void parseFields(ClassMetadata metadata, RTConstantPool pool) {
		// TODO
	}

	private void parseMethods(ClassMetadata metadata, RTConstantPool pool) {
		// TODO
	}

	private void parseSourceFileName(ClassMetadata metadata, RTConstantPool pool) {
		for (Attribute attr : metadata.getAttributes()) {
			if (attr instanceof SourceFileAttribute) {
				sourceFileName = pool.getString(((SourceFileAttribute) attr).getSourceFileIndex());
				return;
			}
		}
		sourceFileName = "Unkown";
	}

	private String getClassConstantInfoName(int index, ClassMetadata metadata) {
		ConstantPool constantPool = metadata.getConstantPool();
		ClassConstant classInfo = constantPool.getClass(index);
		return ClassNames.fromSlash(constantPool.getUTF8(classInfo.getIndex()).getValue());
	}

	public String getName() { return name; }

	public String getSuperName() { return superName; }

	public String[] getInterfacesName() { return interfacesName; }

	public boolean isPublic() { return access.isPublic(); }

	public boolean isFinal() { return access.isFinal(); }

	public boolean isSuper() { return access.isSuper(); }

	public boolean isInterface() { return access.isInterface(); }

	public boolean isAbstract() { return access.isAbstract(); }

	public boolean isAnnotation() { return access.isAnnotation(); }

	public boolean isEnum() { return access.isEnum(); }

	public RTConstantPool getConstantPool() { return pool; }

	public String getPackageName() { return getName().substring(0, getName().lastIndexOf('.')); }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClassInfo))
			return false;
		return ((ClassInfo) obj).name.equals(name);
	}

	public String getSourceFileName() { return sourceFileName; }

}
