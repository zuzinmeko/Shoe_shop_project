package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity implementation class for Entity: Users
 *
 */
@Entity

public class Users implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String userName;
		private String LoginId;
		private String password;
		private String phone;
		private String address;
		@CreationTimestamp
		private LocalDate created_at;
		@UpdateTimestamp
		private LocalDate updated_at;
		
		
	public Users() {
		super();
	}
   
}
