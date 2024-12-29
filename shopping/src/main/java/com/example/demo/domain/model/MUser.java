package com.example.demo.domain.model;

import lombok.Data;

@Data
public class MUser {
	private String userId;
	private String mailAddress;
	private String password;
	private String name;
	//private Integer facultyId;
	//private String facultyName;
}
