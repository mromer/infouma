package com.mromero.infoshool.bean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mromero.infoshool.model.School;

@Component
public class SchoolDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(School school) {
		entityManager.persist(school);
	}
	
	@Transactional
	public void delete(School school) {		
		entityManager.remove(school);
	}
	
	@Transactional
	public School getSchool(Long idSchool) {		
		School school = (School) entityManager.createQuery(
			    "from School where id = ?")
			    .setParameter(1, idSchool)
			    .getSingleResult();
		
		return school;
	}

	@SuppressWarnings("unchecked")
	public List<School> list() {
		return entityManager.createQuery("select t from School t")
				.getResultList();
	}

}
