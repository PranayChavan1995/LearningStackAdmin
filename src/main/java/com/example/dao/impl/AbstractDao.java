package com.example.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.DaoInterface;

public abstract class AbstractDao<PK extends Serializable, T> implements DaoInterface<PK, T> {
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	protected CriteriaQuery<T> createEntityCriteria(HashMap<String, Object> property) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(persistentClass);
		Root<T> root = cr.from(persistentClass);
		List<Predicate> predicates = new ArrayList<Predicate>();

		for (String prop : property.keySet()) {
			predicates.add(cb.equal(root.get(prop), property.get(prop)));
		}
		cr.select(root).where(predicates.toArray(new Predicate[] {}));
		return cr;
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	@SuppressWarnings("unchecked")
	public List<T> findall() {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.addOrder(Order.desc("id"));
		return (List<T>) criteria.list();
	}

	public void persist(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void deleteByKey(PK key) {
		getSession().delete(getByKey(key));
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public List<T> findall(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * resultPerPage);
		criteria.setMaxResults(resultPerPage);
		return (List<T>) criteria.list();
	}

	public Long findallCount(HashMap<String, Object> constrains) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(constrains));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	public Long findSearchCount(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);
		criteria.add(Restrictions.allEq(propertyNameValues));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> search(HashMap entity, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);
		criteria.add(Restrictions.allEq(propertyNameValues));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<T>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);
		criteria.add(Restrictions.allEq(propertyNameValues));
		return (List<T>) criteria.list();
	}

	public T uniqueSearch(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(entity));
		return (T) criteria.uniqueResult();
	}

	public List<String> ajax(String name, String term) {
		Criteria criteria = createEntityCriteria();
		criteria.setProjection(Projections.property(name));
		criteria.add(Restrictions.ilike(name, term.toUpperCase(), MatchMode.START));
		return criteria.list();
	}
}
