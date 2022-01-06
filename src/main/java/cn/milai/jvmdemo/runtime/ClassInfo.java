package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.classfile.AccessMask;
import cn.milai.jvmdemo.classfile.ClassMetadata;
import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.classfile.attribute.Attribute;
import cn.milai.jvmdemo.classfile.attribute.SourceFileAttribute;
import cn.milai.jvmdemo.classfile.constant.ClassConstant;
import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.constants.MethodConst;
import cn.milai.jvmdemo.util.ClassNames;

/**
 * 类信息
 * @author milai
 * @date 2021.08.17
 */
public class ClassInfo {

	private ClassInfoLoader loader;
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

	private MemberSlots staticSlots;

	private int instanceSlotSize;
	private int staticSlotSize;

	private boolean initialized;

	private ClassInfo(ClassInfoLoader loader) {
		this.loader = loader;
	}

	public ClassInfo(ClassMetadata metadata, ClassInfoLoader loader) {
		this(loader);
		parseConstantPool(metadata);
		parseClassName(metadata);
		parseSuperClassName(metadata);
		parseInterfacesName(metadata);
		parseAccessMask(metadata);
		parseFields(metadata, pool);
		parseMethods(metadata, pool);
		parseSourceFileName(metadata, pool);
		resolveSuperAndInterface();
		allocateSlots();
	}

	public static ClassInfo array(String arrayClass, ClassInfoLoader loader) {
		ClassInfo c = new ClassInfo(loader);
		if (!ClassConst.isArray(arrayClass)) {
			throw new IllegalArgumentException("该类不是数组类型:" + arrayClass);
		}
		c.loader = loader;
		c.access = new AccessMask(AccessMask.ACC_PUBLIC);
		c.name = arrayClass;
		c.superName = ClassConst.OBJECT;
		c.interfacesName = new String[] { ClassConst.SERIALIZABLE, ClassConst.CLONEABLE };
		c.resolveSuperAndInterface();
		return c;
	}

	public static ClassInfo primitive(String primitiveClass, ClassInfoLoader loader) {
		ClassInfo c = new ClassInfo(loader);
		if (!ClassConst.isPrimitive(primitiveClass)) {
			throw new IllegalArgumentException("该类不是原始类型:" + primitiveClass);
		}
		c.access = new AccessMask(AccessMask.ACC_PUBLIC);
		c.name = primitiveClass;
		return c;
	}

	private void allocateSlots() {
		calculateSlotIds();
		initStaticSlots();
	}

	private void initStaticSlots() {
		staticSlots = new MemberSlots(staticSlotSize);
		for (Field field : fields) {
			if (field.isStatic() && field.isFinal()) {
				getConstantPool().get(field.getConstantValueIndex());
				switch (TypeDesc.of(field.getDescriptor())) {
					case BOOLEAN :
					case BYTE :
					case CHAR :
					case SHORT :
					case INT :
						staticSlots.setInt(field.getSlotId(), pool.getInt(field.getConstantValueIndex()));
						break;
					case FLOAT :
						staticSlots.setFloat(field.getSlotId(), pool.getFloat(field.getConstantValueIndex()));
						break;
					case LONG :
						staticSlots.setLong(field.getSlotId(), pool.getLong(field.getConstantValueIndex()));
						break;
					case DOUBLE :
						staticSlots.setDouble(field.getSlotId(), pool.getDouble(field.getConstantValueIndex()));
						break;
					default:
						// TODO 引用/数组类型
						break;
				}
			}
		}
	}

	private void calculateSlotIds() {
		int iSlotId = superClassInfo == null ? 0 : superClassInfo.instanceSlotSize;
		int sSlotId = 0;
		for (Field field : fields) {
			int size = TypeDesc.of(field.getDescriptor()).needDoubleSlot() ? 2 : 1;
			if (field.isStatic()) {
				field.setSlotId(sSlotId);
				sSlotId += size;
			} else {
				field.setSlotId(iSlotId);
				iSlotId += size;
			}
		}
		staticSlotSize = sSlotId;
		instanceSlotSize = iSlotId;
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

	public boolean isInitialized() { return initialized; }

	/**
	 * 在指定 {@link ThreadSpace} 中执行类的初始化。
	 * 若已经初始化过，将忽略	
	 * @param thread
	 */
	public synchronized void init(ThreadSpace thread) {
		if (initialized) {
			return;
		}
		initialized = true;
		Method clinit = getMethod(MethodConst.CLINIT, MethodConst.RETURN_VOID, true);
		if (clinit != null) {
			thread.invoke(clinit);
		}
		if (superClassInfo != null) {
			superClassInfo.init(thread);
		}
	}

	/**
	 * 当前类是否为数组类
	 * @return
	 */
	public boolean isArray() { return ClassConst.isArray(name); }

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

	public Field getField(String name, String descriptor, boolean isStatic) {
		for (Field field : fields) {
			if (field.getName().equals(name) && field.getDescriptor().equals(descriptor) && isStatic == field
				.isStatic()) {
				return field;
			}
		}
		return null;
	}

	public Method[] getMethods() { return methods; }

	public Method getMethod(String name, String descriptor, boolean isStatic) {
		for (Method method : methods) {
			if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
				return method;
			}
		}
		return null;
	}

	public ClassInfo getSuperClassInfo() { return superClassInfo; }

	public ClassInfo[] getInterfacesClassInfo() { return interfacesClassInfo; }

	public int getInstanceSlotCnt() { return instanceSlotSize; }

	public void setInstanceSlotCnt(int instanceSlotCnt) { this.instanceSlotSize = instanceSlotCnt; }

	public int getStaticSlotCnt() { return staticSlotSize; }

	public void setStaticSlotCnt(int staticSlotCnt) { this.staticSlotSize = staticSlotCnt; }

	public MemberSlots getStaticSlots() { return staticSlots; }

	@Override
	public String toString() {
		return String.format("ClassInfo(%s)", name);
	}

}
