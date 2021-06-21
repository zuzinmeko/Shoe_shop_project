package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import static javax.persistence.FetchType.EAGER;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name = "brands")
@NamedQuery(name = "Brand.findAll",query="SELECT b FROM Brand b")
public class Brand implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = true)
	private String name;
	@CreationTimestamp
	private LocalDate created_at;
	@UpdateTimestamp
	private LocalDate updated_at;
	@ManyToOne
	@JoinColumn(name = "created_by")
	private Users created_by;
	@ManyToOne 
	@JoinColumn(name = "updated_by")
	private Users updated_by;
	
	public Brand() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}

	public LocalDate getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDate updated_at) {
		this.updated_at = updated_at;
	}

	public Users getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Users created_by) {
		this.created_by = created_by;
	}

	public Users getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Users updated_by) {
		this.updated_by = updated_by;
	}
   
}
