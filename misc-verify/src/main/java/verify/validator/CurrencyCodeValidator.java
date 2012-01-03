package verify.validator;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.collect.Sets;

public class CurrencyCodeValidator implements ConstraintValidator<CurrencyCode, String> {

	final private static Set<String> currencyCodesIniso4217 = Sets.newHashSet(new String[] { "JPY", "CNY", "SDG", "RON", "MKD", "MXN", "CAD",
			"ZAR", "AUD", "NOK", "ILS", "ISK", "SYP", "LYD", "UYU", "YER", "CSD", "EEK", "THB", "IDR", "LBP", "AED", "BOB", "QAR", "BHD", "HNL", "HRK", "COP", "ALL", "DKK", "MYR", "SEK", "RSD",
			"BGN", "DOP", "KRW", "LVL", "VEF", "CZK", "TND", "KWD", "VND", "JOD", "NZD", "PAB", "CLP", "PEN", "GBP", "DZD", "CHF", "RUB", "UAH", "ARS", "SAR", "EGP", "INR", "PYG", "TWD", "TRY",
			"BAM", "OMR", "SGD", "MAD", "BYR", "NIO", "HKD", "LTL", "SKK", "GTQ", "BRL", "EUR", "HUF", "IQD", "CRC", "PHP", "SVC", "PLN", "USD", "XBB", "XBC", "XBD", "UGX", "MOP", "SHP", "TTD",
			"UYI", "KGS", "DJF", "BTN", "XBA", "HTG", "BBD", "XAU", "FKP", "MWK", "PGK", "XCD", "COU", "RWF", "NGN", "BSD", "XTS", "TMT", "GEL", "VUV", "FJD", "MVR", "AZN", "MNT", "MGA", "WST",
			"KMF", "GNF", "SBD", "BDT", "MMK", "TJS", "CVE", "MDL", "KES", "SRD", "LRD", "MUR", "CDF", "BMD", "USN", "CUP", "USS", "GMD", "UZS", "CUC", "ZMK", "NPR", "NAD", "LAK", "SZL", "XDR",
			"BND", "TZS", "MXV", "LSL", "KYD", "LKR", "ANG", "PKR", "SLL", "SCR", "GHS", "ERN", "BOV", "GIP", "IRR", "XPT", "BWP", "XFU", "CLF", "ETB", "STD", "XXX", "XPD", "AMD", "XPF", "JMD",
			"MRO", "BIF", "CHW", "ZWL", "AWG", "MZN", "CHE", "XOF", "KZT", "BZD", "XAG", "KHR", "XAF", "GYD", "AFN", "SOS", "TOP", "AOA", "KPW" });

	@Override
	public void initialize(CurrencyCode constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return currencyCodesIniso4217.contains(value);
	}

}
