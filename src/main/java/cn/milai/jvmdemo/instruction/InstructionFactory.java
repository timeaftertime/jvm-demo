package cn.milai.jvmdemo.instruction;

import cn.milai.jvmdemo.instruction.compare.CmpInstructions.DCMPG;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.DCMPL;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.FCMPG;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.FCMPL;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.LCMP;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPEQ;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPGE;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPGT;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPLE;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPLT;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPNE;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFEQ;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFGE;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFGT;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFLE;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFLT;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFNE;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.ACONST_NULL;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.DCONST_0;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.DCONST_1;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.FCONST_0;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.FCONST_1;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.FCONST_2;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.ICONST_0;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.ICONST_1;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.ICONST_2;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.ICONST_3;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.ICONST_4;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.ICONST_5;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.ICONST_M1;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.LCONST_0;
import cn.milai.jvmdemo.instruction.constant.ConstInstructions.LCONST_1;
import cn.milai.jvmdemo.instruction.constant.LdcInstructions.LDC;
import cn.milai.jvmdemo.instruction.constant.LdcInstructions.LDC2_W;
import cn.milai.jvmdemo.instruction.constant.LdcInstructions.LDC_W;
import cn.milai.jvmdemo.instruction.constant.NOP;
import cn.milai.jvmdemo.instruction.constant.PushInstructions.BIPUSH;
import cn.milai.jvmdemo.instruction.constant.PushInstructions.SIPUSH;
import cn.milai.jvmdemo.instruction.control.GOTO;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.DRETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.FRETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.IRETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.LRETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.RETURN;
import cn.milai.jvmdemo.instruction.control.SwitchInstructions.LOOKUPSWITCH;
import cn.milai.jvmdemo.instruction.control.SwitchInstructions.TABLESWITCH;
import cn.milai.jvmdemo.instruction.conversion.D2XInstructions.D2F;
import cn.milai.jvmdemo.instruction.conversion.D2XInstructions.D2I;
import cn.milai.jvmdemo.instruction.conversion.D2XInstructions.D2L;
import cn.milai.jvmdemo.instruction.conversion.F2XInstructions.F2D;
import cn.milai.jvmdemo.instruction.conversion.F2XInstructions.F2I;
import cn.milai.jvmdemo.instruction.conversion.F2XInstructions.F2L;
import cn.milai.jvmdemo.instruction.conversion.I2XInstructions.I2D;
import cn.milai.jvmdemo.instruction.conversion.I2XInstructions.I2F;
import cn.milai.jvmdemo.instruction.conversion.I2XInstructions.I2L;
import cn.milai.jvmdemo.instruction.conversion.L2XInstructions.L2D;
import cn.milai.jvmdemo.instruction.conversion.L2XInstructions.L2F;
import cn.milai.jvmdemo.instruction.conversion.L2XInstructions.L2I;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.AALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.BALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.CALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.DALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.FALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.IALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.LALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.SALOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD_3;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD_3;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD_3;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD_3;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD_3;
import cn.milai.jvmdemo.instruction.math.AddInstructions.DADD;
import cn.milai.jvmdemo.instruction.math.AddInstructions.FADD;
import cn.milai.jvmdemo.instruction.math.AddInstructions.IADD;
import cn.milai.jvmdemo.instruction.math.AddInstructions.LADD;
import cn.milai.jvmdemo.instruction.math.AndInstructions.IAND;
import cn.milai.jvmdemo.instruction.math.AndInstructions.LAND;
import cn.milai.jvmdemo.instruction.math.DivInstructions.DDIV;
import cn.milai.jvmdemo.instruction.math.DivInstructions.FDIV;
import cn.milai.jvmdemo.instruction.math.DivInstructions.IDIV;
import cn.milai.jvmdemo.instruction.math.DivInstructions.LDIV;
import cn.milai.jvmdemo.instruction.math.IINC;
import cn.milai.jvmdemo.instruction.math.MulInstructions.DMUL;
import cn.milai.jvmdemo.instruction.math.MulInstructions.FMUL;
import cn.milai.jvmdemo.instruction.math.MulInstructions.IMUL;
import cn.milai.jvmdemo.instruction.math.MulInstructions.LMUL;
import cn.milai.jvmdemo.instruction.math.OrInstructions.IOR;
import cn.milai.jvmdemo.instruction.math.OrInstructions.LOR;
import cn.milai.jvmdemo.instruction.math.RemInstructions.DREM;
import cn.milai.jvmdemo.instruction.math.RemInstructions.FREM;
import cn.milai.jvmdemo.instruction.math.RemInstructions.IREM;
import cn.milai.jvmdemo.instruction.math.RemInstructions.LREM;
import cn.milai.jvmdemo.instruction.math.ShInstructions.ISHL;
import cn.milai.jvmdemo.instruction.math.ShInstructions.ISHR;
import cn.milai.jvmdemo.instruction.math.ShInstructions.IUSHR;
import cn.milai.jvmdemo.instruction.math.ShInstructions.LSHL;
import cn.milai.jvmdemo.instruction.math.ShInstructions.LSHR;
import cn.milai.jvmdemo.instruction.math.ShInstructions.LUSHR;
import cn.milai.jvmdemo.instruction.math.SubInstructions.DSUB;
import cn.milai.jvmdemo.instruction.math.SubInstructions.FSUB;
import cn.milai.jvmdemo.instruction.math.SubInstructions.ISUB;
import cn.milai.jvmdemo.instruction.math.SubInstructions.LSUB;
import cn.milai.jvmdemo.instruction.math.XorInstructions.IXOR;
import cn.milai.jvmdemo.instruction.math.XorInstructions.LXOR;
import cn.milai.jvmdemo.instruction.reference.InvokeInstructions.INVOKEINTERFACE;
import cn.milai.jvmdemo.instruction.reference.InvokeInstructions.INVOKESPECIAL;
import cn.milai.jvmdemo.instruction.reference.InvokeInstructions.INVOKESTATIC;
import cn.milai.jvmdemo.instruction.reference.InvokeInstructions.INVOKEVIRTUAL;
import cn.milai.jvmdemo.instruction.reference.New;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP2;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP2_X1;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP2_X2;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP_X1;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP_X2;
import cn.milai.jvmdemo.instruction.stack.PopInstructions.POP;
import cn.milai.jvmdemo.instruction.stack.PopInstructions.POP2;
import cn.milai.jvmdemo.instruction.stack.Swap;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.AASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.BASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.CASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.DASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.FASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.IASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.LASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.SASTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE_3;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE_3;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE_3;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE_3;

/**
 * {@link Instruction} 工厂类
 * @author milai
 * @date 2021.11.14
 */
public class InstructionFactory {

	private InstructionFactory() {
	}

	public static final Instruction I_NOP = new NOP();
	public static final Instruction I_ACONST_NULL = new ACONST_NULL();
	public static final Instruction I_ICONST_M1 = new ICONST_M1();
	public static final Instruction I_ICONST_0 = new ICONST_0();
	public static final Instruction I_ICONST_1 = new ICONST_1();
	public static final Instruction I_ICONST_2 = new ICONST_2();
	public static final Instruction I_ICONST_3 = new ICONST_3();
	public static final Instruction I_ICONST_4 = new ICONST_4();
	public static final Instruction I_ICONST_5 = new ICONST_5();
	public static final Instruction I_LCONST_0 = new LCONST_0();
	public static final Instruction I_LCONST_1 = new LCONST_1();
	public static final Instruction I_FCONST_0 = new FCONST_0();
	public static final Instruction I_FCONST_1 = new FCONST_1();
	public static final Instruction I_FCONST_2 = new FCONST_2();
	public static final Instruction I_DCONST_0 = new DCONST_0();
	public static final Instruction I_DCONST_1 = new DCONST_1();

	public static final Instruction I_ILOAD_0 = new ILOAD_0();
	public static final Instruction I_ILOAD_1 = new ILOAD_1();
	public static final Instruction I_ILOAD_2 = new ILOAD_2();
	public static final Instruction I_ILOAD_3 = new ILOAD_3();
	public static final Instruction I_LLOAD_0 = new LLOAD_0();
	public static final Instruction I_LLOAD_1 = new LLOAD_1();
	public static final Instruction I_LLOAD_2 = new LLOAD_2();
	public static final Instruction I_LLOAD_3 = new LLOAD_3();
	public static final Instruction I_FLOAD_0 = new FLOAD_0();
	public static final Instruction I_FLOAD_1 = new FLOAD_1();
	public static final Instruction I_FLOAD_2 = new FLOAD_2();
	public static final Instruction I_FLOAD_3 = new FLOAD_3();
	public static final Instruction I_DLOAD_0 = new DLOAD_0();
	public static final Instruction I_DLOAD_1 = new DLOAD_1();
	public static final Instruction I_DLOAD_2 = new DLOAD_2();
	public static final Instruction I_DLOAD_3 = new DLOAD_3();
	public static final Instruction I_ALOAD_0 = new ALOAD_0();
	public static final Instruction I_ALOAD_1 = new ALOAD_1();
	public static final Instruction I_ALOAD_2 = new ALOAD_2();
	public static final Instruction I_ALOAD_3 = new ALOAD_3();
	public static final Instruction I_IALOAD = new IALOAD();
	public static final Instruction I_LALOAD = new LALOAD();
	public static final Instruction I_FALOAD = new FALOAD();
	public static final Instruction I_DALOAD = new DALOAD();
	public static final Instruction I_AALOAD = new AALOAD();
	public static final Instruction I_BALOAD = new BALOAD();
	public static final Instruction I_CALOAD = new CALOAD();
	public static final Instruction I_SALOAD = new SALOAD();

	public static final Instruction I_ISTORE_0 = new ISTORE_0();
	public static final Instruction I_ISTORE_1 = new ISTORE_1();
	public static final Instruction I_ISTORE_2 = new ISTORE_2();
	public static final Instruction I_ISTORE_3 = new ISTORE_3();
	public static final Instruction I_LSTORE_0 = new LSTORE_0();
	public static final Instruction I_LSTORE_1 = new LSTORE_1();
	public static final Instruction I_LSTORE_2 = new LSTORE_2();
	public static final Instruction I_LSTORE_3 = new LSTORE_3();
	public static final Instruction I_FSTORE_0 = new FSTORE_0();
	public static final Instruction I_FSTORE_1 = new FSTORE_1();
	public static final Instruction I_FSTORE_2 = new FSTORE_2();
	public static final Instruction I_FSTORE_3 = new FSTORE_3();
	public static final Instruction I_DSTORE_0 = new DSTORE_0();
	public static final Instruction I_DSTORE_1 = new DSTORE_1();
	public static final Instruction I_DSTORE_2 = new DSTORE_2();
	public static final Instruction I_DSTORE_3 = new DSTORE_3();
	public static final Instruction I_IASTORE = new IASTORE();
	public static final Instruction I_LASTORE = new LASTORE();
	public static final Instruction I_FASTORE = new FASTORE();
	public static final Instruction I_DASTORE = new DASTORE();
	public static final Instruction I_AASTORE = new AASTORE();
	public static final Instruction I_BASTORE = new BASTORE();
	public static final Instruction I_CASTORE = new CASTORE();
	public static final Instruction I_SASTORE = new SASTORE();

	public static final Instruction I_POP = new POP();
	public static final Instruction I_POP2 = new POP2();
	public static final Instruction I_DUP = new DUP();
	public static final Instruction I_DUP_X1 = new DUP_X1();
	public static final Instruction I_DUP_X2 = new DUP_X2();
	public static final Instruction I_DUP2 = new DUP2();
	public static final Instruction I_DUP2_X1 = new DUP2_X1();
	public static final Instruction I_DUP2_X2 = new DUP2_X2();
	public static final Instruction I_SWAP = new Swap();

	public static final Instruction I_IADD = new IADD();
	public static final Instruction I_LADD = new LADD();
	public static final Instruction I_FADD = new FADD();
	public static final Instruction I_DADD = new DADD();
	public static final Instruction I_ISUB = new ISUB();
	public static final Instruction I_LSUB = new LSUB();
	public static final Instruction I_FSUB = new FSUB();
	public static final Instruction I_DSUB = new DSUB();
	public static final Instruction I_IMUL = new IMUL();
	public static final Instruction I_LMUL = new LMUL();
	public static final Instruction I_FMUL = new FMUL();
	public static final Instruction I_DMUL = new DMUL();
	public static final Instruction I_IDIV = new IDIV();
	public static final Instruction I_LDIV = new LDIV();
	public static final Instruction I_FDIV = new FDIV();
	public static final Instruction I_DDIV = new DDIV();
	public static final Instruction I_IREM = new IREM();
	public static final Instruction I_LREM = new LREM();
	public static final Instruction I_FREM = new FREM();
	public static final Instruction I_DREM = new DREM();
	public static final Instruction I_ISHL = new ISHL();
	public static final Instruction I_LSHL = new LSHL();
	public static final Instruction I_ISHR = new ISHR();
	public static final Instruction I_LSHR = new LSHR();
	public static final Instruction I_IUSHR = new IUSHR();
	public static final Instruction I_LUSHR = new LUSHR();
	public static final Instruction I_IAND = new IAND();
	public static final Instruction I_LAND = new LAND();
	public static final Instruction I_IOR = new IOR();
	public static final Instruction I_LOR = new LOR();
	public static final Instruction I_IXOR = new IXOR();
	public static final Instruction I_LXOR = new LXOR();

	public static final Instruction I_I2L = new I2L();
	public static final Instruction I_I2F = new I2F();
	public static final Instruction I_I2D = new I2D();
	public static final Instruction I_L2I = new L2I();
	public static final Instruction I_L2F = new L2F();
	public static final Instruction I_L2D = new L2D();
	public static final Instruction I_F2I = new F2I();
	public static final Instruction I_F2L = new F2L();
	public static final Instruction I_F2D = new F2D();
	public static final Instruction I_D2I = new D2I();
	public static final Instruction I_D2L = new D2L();
	public static final Instruction I_D2F = new D2F();

	public static final Instruction I_RETURN = new RETURN();
	public static final Instruction I_IRETURN = new IRETURN();
	public static final Instruction I_LRETURN = new LRETURN();
	public static final Instruction I_FRETURN = new FRETURN();
	public static final Instruction I_DRETURN = new DRETURN();

	public static final Instruction I_LCMP = new LCMP();
	public static final Instruction I_FCMPL = new FCMPL();
	public static final Instruction I_FCMPG = new FCMPG();
	public static final Instruction I_DCMPL = new DCMPL();
	public static final Instruction I_DCMPG = new DCMPG();

	/**
	 * 创建指定 {@code opcode} 的 {@link Instruction}
	 * @param opcode
	 * @return
	 */
	public static Instruction create(int opcode) {
		Opcode op = Opcode.find(opcode);
		if (op == null) {
			throw new IllegalArgumentException("未知操作码: " + opcode);
		}
		switch (op) {
			case NOP :
				return I_NOP;
			case ACOST_NULL :
				return I_ACONST_NULL;
			case ICONST_M1 :
				return I_ICONST_M1;
			case ICONST_0 :
				return I_ICONST_0;
			case ICONST_1 :
				return I_ICONST_1;
			case ICONST_2 :
				return I_ICONST_2;
			case ICONST_3 :
				return I_ICONST_3;
			case ICONST_4 :
				return I_ICONST_4;
			case ICONST_5 :
				return I_ICONST_5;
			case LCONST_0 :
				return I_LCONST_0;
			case LCONST_1 :
				return I_LCONST_1;
			case FCONST_0 :
				return I_FCONST_0;
			case FCONST_1 :
				return I_FCONST_1;
			case FCONST_2 :
				return I_FCONST_2;
			case DCONST_0 :
				return I_DCONST_0;
			case DCONST_1 :
				return I_DCONST_1;
			case BIPUSH :
				return new BIPUSH();
			case SIPUSH :
				return new SIPUSH();
			case LDC :
				return new LDC();
			case LDC_W :
				return new LDC_W();
			case LDC2_W :
				return new LDC2_W();

			case ILOAD :
				return new ILOAD();
			case LLOAD :
				return new LLOAD();
			case FLOAD :
				return new FLOAD();
			case DLOAD :
				return new DLOAD();
			case ALOAD :
				return new ALOAD();
			case ALOAD_0 :
				return I_ALOAD_0;
			case ALOAD_1 :
				return I_ALOAD_1;
			case ALOAD_2 :
				return I_ALOAD_2;
			case ALOAD_3 :
				return I_ALOAD_3;
			case ILOAD_0 :
				return I_ILOAD_0;
			case ILOAD_1 :
				return I_ILOAD_1;
			case ILOAD_2 :
				return I_ILOAD_2;
			case ILOAD_3 :
				return I_ILOAD_3;
			case LLOAD_0 :
				return I_LLOAD_0;
			case LLOAD_1 :
				return I_LLOAD_1;
			case LLOAD_2 :
				return I_LLOAD_2;
			case LLOAD_3 :
				return I_LLOAD_3;
			case FLOAD_0 :
				return I_FLOAD_0;
			case FLOAD_1 :
				return I_FLOAD_1;
			case FLOAD_2 :
				return I_FLOAD_2;
			case FLOAD_3 :
				return I_FLOAD_3;
			case DLOAD_0 :
				return I_DLOAD_0;
			case DLOAD_1 :
				return I_DLOAD_1;
			case DLOAD_2 :
				return I_DLOAD_2;
			case DLOAD_3 :
				return I_DLOAD_3;
			case IALOAD :
				return I_IALOAD;
			case LALOAD :
				return I_LALOAD;
			case FALOAD :
				return I_FALOAD;
			case DALOAD :
				return I_DALOAD;
			case AALOAD :
				return I_AALOAD;
			case BALOAD :
				return I_BALOAD;
			case CALOAD :
				return I_CALOAD;
			case SALOAD :
				return I_SALOAD;

			case ISTORE :
				return new ISTORE();
			case LSTORE :
				return new LSTORE();
			case FSTORE :
				return new FSTORE();
			case DSTORE :
				return new DSTORE();
			case ISTORE_0 :
				return I_ISTORE_0;
			case ISTORE_1 :
				return I_ISTORE_1;
			case ISTORE_2 :
				return I_ISTORE_2;
			case ISTORE_3 :
				return I_ISTORE_3;
			case LSTORE_0 :
				return I_LSTORE_0;
			case LSTORE_1 :
				return I_LSTORE_1;
			case LSTORE_2 :
				return I_LSTORE_2;
			case LSTORE_3 :
				return I_LSTORE_3;
			case FSTORE_0 :
				return I_FSTORE_0;
			case FSTORE_1 :
				return I_FSTORE_1;
			case FSTORE_2 :
				return I_FSTORE_2;
			case FSTORE_3 :
				return I_FSTORE_3;
			case DSTORE_0 :
				return I_DSTORE_0;
			case DSTORE_1 :
				return I_DSTORE_1;
			case DSTORE_2 :
				return I_DSTORE_2;
			case DSTORE_3 :
				return I_DSTORE_3;
			case IASTORE :
				return I_IASTORE;
			case LASTORE :
				return I_LASTORE;
			case FASTORE :
				return I_FASTORE;
			case DASTORE :
				return I_DASTORE;
			case AASTORE :
				return I_AASTORE;
			case BASTORE :
				return I_BASTORE;
			case CASTORE :
				return I_CASTORE;
			case SASTORE :
				return I_SASTORE;

			case POP :
				return I_POP;
			case POP2 :
				return I_POP2;
			case DUP :
				return I_DUP;
			case DUP_X1 :
				return I_DUP_X1;
			case DUP_X2 :
				return I_DUP_X2;
			case DUP2 :
				return I_DUP2;
			case SWAP :
				return I_SWAP;

			case IADD :
				return I_IADD;
			case LADD :
				return I_LADD;
			case FADD :
				return I_FADD;
			case DADD :
				return I_DADD;
			case ISUB :
				return I_ISUB;
			case LSUB :
				return I_LSUB;
			case FSUB :
				return I_FSUB;
			case DSUB :
				return I_DSUB;
			case IMUL :
				return I_IMUL;
			case LMUL :
				return I_LMUL;
			case FMUL :
				return I_FMUL;
			case DMUL :
				return I_DMUL;
			case IDIV :
				return I_IDIV;
			case LDIV :
				return I_LDIV;
			case FDIV :
				return I_FDIV;
			case DDIV :
				return I_DDIV;
			case IREM :
				return I_IREM;
			case LREM :
				return I_LREM;
			case FREM :
				return I_FREM;
			case DREM :
				return I_DREM;
			case ISHL :
				return I_ISHL;
			case LSHL :
				return I_LSHL;
			case ISHR :
				return I_ISHR;
			case LSHR :
				return I_LSHR;
			case IUSHR :
				return I_IUSHR;
			case LUSHR :
				return I_LUSHR;
			case IAND :
				return I_IAND;
			case LAND :
				return I_LAND;
			case IOR :
				return I_IOR;
			case LOR :
				return I_LOR;
			case IXOR :
				return I_IXOR;
			case LXOR :
				return I_LXOR;
			case IINC :
				return new IINC();

			case I2L :
				return I_I2L;
			case I2F :
				return I_I2F;
			case I2D :
				return I_I2D;
			case L2I :
				return I_L2I;
			case L2F :
				return I_L2F;
			case L2D :
				return I_L2D;
			case F2I :
				return I_F2I;
			case F2L :
				return I_F2L;
			case F2D :
				return I_F2D;
			case D2I :
				return I_D2I;
			case D2L :
				return I_D2L;
			case D2F :
				return I_D2F;

			case LCMP :
				return I_LCMP;
			case FCMPL :
				return I_FCMPL;
			case FCMPG :
				return I_FCMPG;
			case DCMPL :
				return I_DCMPL;
			case DCMPG :
				return I_DCMPG;
			case IFEQ :
				return new IFEQ();
			case IFNE :
				return new IFNE();
			case IFLT :
				return new IFLT();
			case IFGE :
				return new IFGE();
			case IFGT :
				return new IFGT();
			case IFLE :
				return new IFLE();
			case IF_ICMPEQ :
				return new IF_ICMPEQ();
			case IF_ICMPNE :
				return new IF_ICMPNE();
			case IF_ICMPLT :
				return new IF_ICMPLT();
			case IF_ICMPGE :
				return new IF_ICMPGE();
			case IF_ICMPGT :
				return new IF_ICMPGT();
			case IF_ICMPLE :
				return new IF_ICMPLE();

			case GOTO :
				return new GOTO();
			case TABLESWITCH :
				return new TABLESWITCH();
			case LOOKUPSWITCH :
				return new LOOKUPSWITCH();
			case IRETURN :
				return I_IRETURN;
			case LRETURN :
				return I_LRETURN;
			case FRETURN :
				return I_FRETURN;
			case DRETURN :
				return I_DRETURN;
			case RETURN :
				return I_RETURN;

			case INVOKEVIRTUAL :
				return new INVOKEVIRTUAL();
			case INVOKESPECIAL :
				return new INVOKESPECIAL();
			case INVOKESTATIC :
				return new INVOKESTATIC();
			case INVOKEINTERFACE :
				return new INVOKEINTERFACE();
			case NEW :
				return new New();

			default:
				throw new IllegalArgumentException("未实现指令类型: " + op);
		}
	}

}
