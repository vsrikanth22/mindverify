package verify.validator;

import java.util.Currency;

public class CurrencyTest {
	
	public static void main(String[] args) {
		Currency currency = Currency.getInstance("hkd");
		System.out.println(currency.getCurrencyCode());
		System.out.println(currency.getSymbol());
		System.out.println(currency.getDefaultFractionDigits());
	}

}
