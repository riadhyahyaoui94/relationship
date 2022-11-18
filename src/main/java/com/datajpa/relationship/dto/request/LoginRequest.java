package com.datajpa.relationship.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;


}
