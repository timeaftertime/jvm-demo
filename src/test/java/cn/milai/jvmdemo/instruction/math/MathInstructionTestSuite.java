package cn.milai.jvmdemo.instruction.math;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{ AddInstructionsTest.class, AndInstructionsTest.class, DivInstructionsTest.class, IINCTest.class,
		MulInstructionsTest.class, OrInstructionsTest.class, RemInstructionsTest.class, ShInstructionsTest.class,
		SubInstructionsTest.class, XorInstructionsTest.class }
)
public class MathInstructionTestSuite {

}
