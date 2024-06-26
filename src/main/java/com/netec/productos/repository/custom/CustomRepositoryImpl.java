package com.netec.productos.repository.custom;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CustomRepositoryImpl<T, K extends Serializable> implements CustomRepository<T, K> {

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	protected CustomRepositoryImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		this.entityManager.persist(entity);
	}

	@Override
	public T update(T entity) {
		return this.entityManager.merge(entity);
	}

	@Override
	public Optional<T> getById(K id) {
		return Optional.ofNullable(this.entityManager.find(this.persistentClass, id));
	}

	@Override
	public Long countAll() {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<T> root = query.from(this.persistentClass);
		query.select(criteriaBuilder.count(root));
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public void delete(T entity) {
		this.entityManager.remove(entity);
		
	}

}
