package verify.validator;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ValidateObject {

	@NotBlank
	private String name;

	@NotNull
	private Double amount;

	@NotNull
	private Date today;

	@CurrencyCode
	private String currency;

	public ValidateObject(String name, Double amount, Date today) {
		this.name = name;
		this.amount = amount;
		this.today = today;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
