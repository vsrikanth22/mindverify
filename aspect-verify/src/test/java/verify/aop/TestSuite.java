package verify.aop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import verify.aop.service.AccountServiceTest;

@RunWith(Suite.class)
@SuiteClasses(AccountServiceTest.class)
public class TestSuite {

}
