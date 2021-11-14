package cn.milai.jvmdemo.instruction;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{ ByteCodeReaderTest.class, InstructionFactoryTest.class, ConstInstructionsTest.class, LdcInstructionsTest.class,
		LoadInstructionsTest.class, PushInstructionsTest.class, StoreInstructionsTest.class }
)
public class InstructionTestSuite {

}
