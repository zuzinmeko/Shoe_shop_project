package com.mmit.shop.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.ProductService;

@Named
@RequestScoped
public class ProductBean {
	
	private List<Product> products;
	@Inject
	private ProductService service;
	
	private Product product;
	
	private Part imgPart;
	
	@PostConstruct
	private void init() {
		products=service.findAll();
		
		 String id=FacesContext.getCurrentInstance().getExternalContext().
		 getRequestParameterMap().get("pId"); if(id !=null && !id.equals(""))
		  product=service.findById(Integer.parseInt(id)); else product=new Product();
		 
		/* product=new Product(); */
	}
	
	public String save() {
		try {
			service.save(product);
			return "/admin/products.xhtml?faces-redirect=true";
			
		} catch (EJBException e) {
			FacesContext cxt=FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:name", new FacesMessage("Product name is already exist!"));
		}
		
		return null;
	}
	public List<Product> getProducts() {
		return products;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Part getImgPart() {
		return imgPart;
	}
	public void setImgPart(Part imgPart) {
		this.imgPart = imgPart;
	}
	
	
}
