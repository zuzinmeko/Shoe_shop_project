package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.mmit.shop.model.entity.Users1;
@Stateless
public class UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Pbkdf2PasswordHash encoder;

	public List<Users1> findAll() {
		
		return em.createNamedQuery("Users.findAll",Users1.class).getResultList();
	}

	public void save(Users1 user) {
		
		user.setPassword(encoder.generate(user.getPassword().toCharArray()));
		em.persist(user);
	}

	public Users1 findByLogId(String LoginId) {
	
		TypedQuery<Users1> query=em.createNamedQuery("Users.findLoginId", Users1.class);
		query.setParameter("LoginId", LoginId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return null;
	}

	public long getCount() {
		TypedQuery<Long> query=em.createNamedQuery("User.getCount", Long.class);
		return query.getSingleResult();
	}
}
