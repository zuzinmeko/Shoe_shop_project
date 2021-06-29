package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@NamedQuery(name="product.findAll",query="SELECT p FROM Product p ORDER BY p.create_at DESC")
public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique = true)
	private String name;
	
	private int price;
	
	private String productDetails;
	private String photo;
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Brand brand;
	@ManyToOne
	@JoinColumn(name="create_by")
	private Users1 create_by;
	@ManyToOne
	@JoinColumn(name="update_by")
	private Users1 update_by;

	private Status status;
	private enum Status{
		Active,Inactive
	}
	private LocalDate create_at;
	private LocalDate update_at;
	
	public Product() {
		super();
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Users1 getCreate_by() {
		return create_by;
	}

	public void setCreate_by(Users1 create_by) {
		this.create_by = create_by;
	}

	public Users1 getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(Users1 update_by) {
		this.update_by = update_by;
	}

	public LocalDate getCreate_at() {
		return create_at;
	}

	public void setCreate_at(LocalDate create_at) {
		this.create_at = create_at;
	}

	public LocalDate getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDate update_at) {
		this.update_at = update_at;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
   
}
