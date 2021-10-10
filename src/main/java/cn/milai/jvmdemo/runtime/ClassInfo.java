package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
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

	private DefaultClassInfoLoader loader;
	private ClassInfo superClassInfo;
	private ClassInfo[] interfacesClassInfo;

	private String name;
	private String superName;
	private String[] interfacesName;
	private AccessMask access;
	private RTConstantPool pool;
	private String sourceFileName;

	private Field[] fields;
	private Method[] methods;

	public ClassInfo(ClassMetadata metadata, DefaultClassInfoLoader loader) {
		this.loader = loader;
		parseConstantPool(metadata);
		parseClassName(metadata);
		parseSuperClassName(metadata);
		parseInterfacesName(metadata);
		parseAccessMask(metadata);
		parseFields(metadata, pool);
		parseMethods(metadata, pool);
		parseSourceFileName(metadata, pool);
		resolveSuperAndInterface();
	}

	private void resolveSuperAndInterface() {
		if (this.superName != null) {
			this.superClassInfo = loader.load(superName);
		}
		this.interfacesClassInfo = new ClassInfo[this.interfacesName.length];
		for (int i = 0; i < interfacesName.length; i++) {
			this.interfacesClassInfo[i] = loader.load(interfacesName[i]);
		}
	}

	public ClassInfoLoader getClassInfoLoader() { return loader; }

	private void parseConstantPool(ClassMetadata metadata) {
		pool = new RTConstantPool(metadata.getConstantPool(), this);
	}

	private void parseClassName(ClassMetadata metadata) {
		name = getClassConstantInfoName(metadata.getClassIndex(), metadata);
	}

	private void parseSuperClassName(ClassMetadata metadata) {
		if (metadata.getSuperClassIndex() == 0) {
			return;
		}
		superName = getClassConstantInfoName(metadata.getSuperClassIndex(), metadata);
	}

	private void parseInterfacesName(ClassMetadata metadata) {
		int[] interfaces = metadata.getInterfacesIndex();
		interfacesName = new String[interfaces.length];
		for (int i = 0; i < interfaces.length; i++) {
			interfacesName[i] = getClassConstantInfoName(interfaces[i], metadata);
		}
	}

	private void parseAccessMask(ClassMetadata metadata) {
		access = metadata.getAccessMask();
	}

	private void parseFields(ClassMetadata metadata, RTConstantPool pool) {
		int len = metadata.getFields().length;
		fields = new Field[len];
		for (int i = 0; i < len; i++) {
			fields[i] = new Field(this, metadata.getFieldClassMember(i), pool);
		}
	}

	private void parseMethods(ClassMetadata metadata, RTConstantPool pool) {
		int len = metadata.getMethods().length;
		methods = new Method[len];
		for (int i = 0; i < len; i++) {
			methods[i] = new Method(this, metadata.getMethodClassMember(i), pool);
		}
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

	/**
	 * 当前类的引用是否可以赋上指定类型的对象
	 * @param target
	 * @return
	 */
	public boolean isAssignableFrom(ClassInfo target) {
		if (this == target) {
			return true;
		}
		return isInterface() ? target.isImplements(this) : target.isSubClassOf(this);
	}

	/**
	 * 当前类是否为指定类的子类
	 * @param target
	 * @return
	 */
	public boolean isSubClassOf(ClassInfo target) {
		for (ClassInfo now = superClassInfo; now != null; now = now.superClassInfo) {
			if (target.equals(now)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 当前类是否实现了指定接口
	 * @param target
	 * @return
	 */
	public boolean isImplements(ClassInfo target) {
		for (ClassInfo c : interfacesClassInfo) {
			if (c.equals(target) || c.isImplements(target)) {
				return true;
			}
		}
		for (ClassInfo now = superClassInfo; now != null; now = now.superClassInfo) {
			if (now.isImplements(target)) {
				return true;
			}
		}
		return false;
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

	public String getSourceFileName() { return sourceFileName; }

	public Field[] getFields() { return fields; }

	public Field getField(String name, String descriptor) {
		for (Field field : fields) {
			if (field.getName().equals(name) && field.getDescriptor().equals(descriptor)) {
				return field;
			}
		}
		return null;
	}

	public Method[] getMethods() { return methods; }

	public Method getMethod(String name, String descriptor) {
		for (Method method : methods) {
			if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
				return method;
			}
		}
		return null;
	}

	public ClassInfo getSuperClassInfo() { return superClassInfo; }

	public ClassInfo[] getInterfacesClassInfo() { return interfacesClassInfo; }

}
