package verify.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { CurrencyCodeValidator.class })
@Documented
public @interface CurrencyCode {

	String message() default "{com.statestr.gcth.extension.constraints.currency}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
