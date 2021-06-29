package com.mmit.shop.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users1;
import com.mmit.shop.model.service.UserService;

@Named
@RequestScoped
public class UserBean {
	private List<Users1> users1;
	
	
	@Inject
	private UserService service;
	
	@PostConstruct
	private void init() {
		users1=service.findAll();
	}
	public List<Users1> getUsers() {
		return users1;
	}
	
}
