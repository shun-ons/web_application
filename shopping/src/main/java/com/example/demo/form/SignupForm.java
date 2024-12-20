package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank(message = "入力必須です")
	@Email(message = "メールアドレス形式で入力してください")
	private String mailAddress;
	
	@NotBlank
	@Length(min = 4,max = 20)
	@Pattern(regexp ="^[a-zA-Z0-9]+$")
	private String password;
	
	@NotBlank
	private String name;
}
