package com.redbank.dtos.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequestDTO implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String email;
	private String password;

}