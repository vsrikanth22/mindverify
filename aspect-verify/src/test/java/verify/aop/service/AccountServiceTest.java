package verify.aop.service;

import org.junit.Test;

import verify.aop.service.AccountService;
import verify.aop.service.DefaultAccountService;

public class AccountServiceTest {

	private AccountService accountService = null;

	public AccountServiceTest() {
		this.accountService = new DefaultAccountService();
	}

	@Test
	public void testDebit() throws Exception {

		accountService.debit();

	}

}
