package cn.milai.jvmdemo.instruction;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.jvmdemo.instruction.constant.ConstantInstructionTestSuite;
import cn.milai.jvmdemo.instruction.control.ControlTestSuite;
import cn.milai.jvmdemo.instruction.conversion.ConversionTestSuite;
import cn.milai.jvmdemo.instruction.load.LoadInstructionTestSuite;
import cn.milai.jvmdemo.instruction.math.MathInstructionTestSuite;
import cn.milai.jvmdemo.instruction.stack.StackInstructionTestSuite;
import cn.milai.jvmdemo.instruction.store.StoreInstructionTestSuite;

@RunWith(Suite.class)
@SuiteClasses(
	{ ByteCodeReaderTest.class, InstructionFactoryTest.class, ConstantInstructionTestSuite.class,
		LoadInstructionTestSuite.class, StoreInstructionTestSuite.class, StackInstructionTestSuite.class,
		MathInstructionTestSuite.class, ConversionTestSuite.class, ControlTestSuite.class, InterpreterTest.class }
)
public class InstructionTestSuite {

}
