package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Product;

@Stateless
public class ProductService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private LoginBean loginBean;
	public List<Product> findAll() {
	
		return em.createNamedQuery("product.findAll",Product.class).getResultList();
	}

	public void save(Product product) {
		if(product.getId()== 0)
		{	
			product.setCreate_by(loginBean.getLoginUser());
			em.persist(product);
		}else {
			product.setUpdate_by(loginBean.getLoginUser());
			em.merge(product);		
			}
		
	}
	
	public Product findById(int parseInt) {
		
		return null;
	}

	
}
