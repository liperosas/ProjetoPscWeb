package junit;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ElaboradorTeste.class, QuestaoTeste.class })
public class AllTests {

	
	
	public static Test allTests() {
		TestSuite suite = new TestSuite("Testes");
		suite.addTestSuite(QuestaoTeste.class);
		suite.addTestSuite(ElaboradorTeste.class);
		return suite;
	}

}
