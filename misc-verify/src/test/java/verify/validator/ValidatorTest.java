package verify.validator;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class ValidatorTest {
	
	@Test
	public void testValidator() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		ValidateObject object = new ValidateObject("test", 1.0d, new Date());
		object.setCurrency("CNY");

		Set<ConstraintViolation<ValidateObject>> constraintViolations = validator.validate(object);
		assertEquals( 0, constraintViolations.size() );
	}

}
