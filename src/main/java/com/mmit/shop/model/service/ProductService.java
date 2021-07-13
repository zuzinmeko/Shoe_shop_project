package com.mmit.shop.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Brand;
import com.mmit.shop.model.entity.Category;
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
			product.setCreate_by(findById(product.getId()).getCreate_by());
			product.setUpdate_by(loginBean.getLoginUser());
			em.merge(product);		
			}
		
	}
	
	public Product findById(int id) {
		
		return em.find(Product.class, id);
	}

	public void remove(int id) {
		Product p=em.find(Product.class, id);
		em.remove(p);
		
	}

	public String findPhotoById(int id) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Product.findPhoto",String.class).
				setParameter("id", id).
				getSingleResult();
	}

	public List<Product> findByCategory(int category_id) {
		return null;
	}

	public List<Product> findByBrand(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	public void uploadData(Part fileUpload) {
		//create reader object
		try(BufferedReader br=new BufferedReader(new InputStreamReader(fileUpload.getInputStream()))) {
			String line=null;
			while((line=br.readLine()) != null) {
				createProduct(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void createProduct(String line) {
		//line= shoe-name category_name	brand_name	price	description
		String data[]=line.split("\t");
		if(data.length == 5) {
			Category c=getCategory(data[1]);
			Brand b=getBrand(data[2]);
			Product p =getProduct(c,b,data[0]);
			p.setPrice(Integer.parseInt(data[3]));
			p.setProductDetails(data[4]);
			
		}
	}

	private Product getProduct(Category c, Brand b, String pname) {
		Product p=new Product();
		TypedQuery<Product> query=em.createNamedQuery("Product.findByNameBrandCategory", Product.class);
		query.setParameter("bId", b.getId());
		query.setParameter("cId", c.getId());
		query.setParameter("pname", pname);
		try {
			p=query.getSingleResult();
		} catch (Exception e) {
			p=new Product ();
			p.setBrand(b);
			p.setCategory(c);
			p.setName(pname);
			em.persist(p);
		}
		return p;
	}

	private Brand getBrand(String bname) {
		TypedQuery<Brand> query=em.createNamedQuery("Brand.findByName", Brand.class);
		query.setParameter("name", bname);
		Brand brand=null;
		try {
			brand=query.getSingleResult();
		} catch (Exception e) {
			brand=new Brand();
			brand.setName(bname);
			em.persist(brand);

		}
		return brand;
	}

	private Category getCategory(String cname) {
		TypedQuery<Category> query=em.createNamedQuery("Category.findByName", Category.class);
		query.setParameter("name", cname);
		Category cat=null;
		try {
			cat=query.getSingleResult();
		} catch (Exception e) {
			cat=new Category();
			cat.setName(cname);
			em.persist(cat);

		}
		return cat;
	}

	

	
}
