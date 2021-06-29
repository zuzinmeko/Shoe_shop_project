package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import static javax.persistence.CascadeType.PERSIST;

/**
 * Entity implementation class for Entity: Orders
 *
 */
@Entity
@NamedQuery(name="orders.findAll",query="SELECT o FROM Orders o ORDER BY o.orderDate")
public class Orders implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Users1 customer;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	public enum Status {
		Pending,Delivered
	}
	@CreationTimestamp
	private LocalDate orderDate;
	@OneToMany(mappedBy = "order", cascade = PERSIST)
	
	private List<OrderDetail> details=new ArrayList<OrderDetail>();
	
	public void addOrderItem(OrderDetail detail) {
		detail.setOrder(this);
		details.add(detail);
	}
	
	public int getTotalQty() {
		return details.stream().mapToInt(d-> d.getSubQty()).sum();
	}
	public int getTotalAmount() {
		return details.stream().mapToInt(d->d.getProduct().getPrice()*d.getSubQty()).sum();
	}
	public Orders() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Users1 getCustomer() {
		return customer;
	}
	public void setCustomer(Users1 customer) {
		this.customer = customer;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public List<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
   
}
