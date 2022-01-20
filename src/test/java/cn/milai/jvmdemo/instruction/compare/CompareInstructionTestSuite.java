package cn.milai.jvmdemo.instruction.compare;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{ CmpInstructionsTest.class, IfAcmpInstructionsTest.class, IfIcmpInstructionsTest.class, IfInstructionsTest.class }
)
public class CompareInstructionTestSuite {

}
