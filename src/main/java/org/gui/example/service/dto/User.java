package org.gui.example.service.dto;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.gui.example.util.RegexMatches;
import org.gui.example.util.Utils;
import org.springframework.format.annotation.DateTimeFormat;

@User.Check(message = "{valid.user.check.error}")
public class User {

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Constraint(validatedBy = Validator.class)
	@Documented
	public @interface Check {

		String message() default "";

		Class<?>[]groups() default {};

		Class<? extends Payload>[]payload() default {};
	}

	public static final class Validator implements ConstraintValidator<Check, User> {

		@Override
		public void initialize(Check ann) {

		}

		@Override
		public boolean isValid(User user, ConstraintValidatorContext context) {
			if(user==null){
				return true;
			}
			if( new Date().getTime() - user.birth.getTime() < 1000 * 3600*24*365 * 18) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate( "{valid.user.birth.too.young}"  )
					.addConstraintViolation();
				return false;
			}
			return true;
		}

	}

	@Pattern(regexp = RegexMatches.REGEX_USERNAME, message = "{valid.user.username.regex_err}")
	@NotNull(message = "{valid.user.username.empty}")
	private String username;

	@Pattern(regexp = RegexMatches.REGEX_EMAIL, message = "{valid.user.email.regex_err}")
	@NotNull(message = "{valid.user.email.empty}")
	private String email;

	@Size(max = 1)
	private String gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return Utils.toJSON(this);
	}
}
