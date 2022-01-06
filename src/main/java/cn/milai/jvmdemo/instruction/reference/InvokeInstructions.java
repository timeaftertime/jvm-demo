package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.constants.MethodConst;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.AbstractMethodRef;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.Descriptor;
import cn.milai.jvmdemo.runtime.InterfaceMethodref;
import cn.milai.jvmdemo.runtime.Method;
import cn.milai.jvmdemo.runtime.MethodRef;
import cn.milai.jvmdemo.runtime.MethodResolver;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * invoke 相关指令
 * @author milai
 * @date 2021.12.15
 */
public class InvokeInstructions {

	private static abstract class InvokeInstruction implements Instruction {
		protected int operand;

		@Override
		public void readOperands(BytecodeReader reader) throws IOException {
			this.operand = reader.readUint16();
		}
	}

	private static void assertNotNull(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
	}

	private static void checkAccess(ClassInfo caller, Method m, AbstractMethodRef ref, ObjectRef invoker) {
		String signature = ref.targetClassName() + " " + ref.getName() + " " + ref.getDescriptor();
		if (m == null) {
			throw new NoSuchMethodError(signature);
		}
		if (m.isAbstract()) {
			throw new AbstractMethodError(signature);
		}
		ClassInfo targetClass = m.getClassInfo();
		if (m.isProtected() && caller.isSubClassOf(targetClass)
			&& !caller.getPackageName().equals(targetClass.getPackageName())) {
			throw new IllegalAccessError();
		}
		if (invoker != null && invoker.getClassInfo() != targetClass && !invoker.getClassInfo().isSubClassOf(
			targetClass
		)) {
			throw new IllegalAccessError();
		}
	}

	public static class INVOKESTATIC extends InvokeInstruction {

		@Override
		public void execute(Frame frame) {
			ClassInfo caller = frame.getMethod().getClassInfo();
			MethodRef ref = caller.getConstantPool().getMethodRef(operand);
			Method method = MethodResolver.resolveStatic(ref);
			checkAccess(caller, method, ref, null);
			frame.getThreadSpace().invoke(method);
		}

	}

	public static class INVOKESPECIAL extends InvokeInstruction {

		@Override
		public void execute(Frame frame) {
			ClassInfo caller = frame.getMethod().getClassInfo();
			MethodRef ref = caller.getConstantPool().getMethodRef(operand);
			ClassInfo called = ref.resolvedClass();
			// ACC_SUPER  https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokespecial
			ClassInfo searchFrom = null;
			if (!MethodConst.INIT.equals(ref.getName()) && caller.isSubClassOf(called) && caller.isSuper()) {
				searchFrom = caller.getSuperClassInfo();
			} else {
				searchFrom = ref.resolvedClass();
			}
			Method method = MethodResolver.resolve(searchFrom, ref);
			ObjectRef invoker = frame.getOperandStack().getRefFromTop(method.getArgsSlotCnt());
			assertNotNull(invoker);
			checkAccess(caller, method, ref, invoker);
			frame.getThreadSpace().invoke(method);

		}

	}

	public static class INVOKEVIRTUAL extends InvokeInstruction {

		@Override
		public void execute(Frame frame) {
			ClassInfo caller = frame.getMethod().getClassInfo();
			MethodRef ref = caller.getConstantPool().getMethodRef(operand);
			ObjectRef invoker = frame.getOperandStack().getRefFromTop(
				new Descriptor(ref.getDescriptor(), false).getArgsSlotCnt()
			);
			assertNotNull(invoker);
			Method method = MethodResolver.resolve(invoker.getClassInfo(), ref);
			checkAccess(caller, method, ref, invoker);
			frame.getThreadSpace().invoke(method);
		}

	}

	public static class INVOKEINTERFACE implements Instruction {

		private int operand;

		@Override
		public void readOperands(BytecodeReader reader) throws IOException {
			operand = reader.readUint16();
			reader.readUint8(); // 由于历史原因而存在，函数的参数个数，现在可以通过描述符来计算
			reader.readUint8(); // 用于 Oracle 某些 Java 虚拟机的实现，必须为 0
		}

		@Override
		public void execute(Frame frame) {
			ClassInfo caller = frame.getMethod().getClassInfo();
			InterfaceMethodref ref = caller.getConstantPool().getInterfaceMethodRef(operand);
			ObjectRef invoker = frame.getOperandStack().getRefFromTop(
				new Descriptor(ref.getDescriptor(), false).getArgsSlotCnt()
			);
			assertNotNull(invoker);
			Method method = MethodResolver.resolve(invoker.getClassInfo(), ref);
			checkAccess(caller, method, ref, invoker);
			if (MethodConst.isClinit(method) || MethodConst.isInit(method)) {
				throw new IncompatibleClassChangeError("不能调用初始化方法");
			}
			frame.getThreadSpace().invoke(method);
		}
	}

}
