package com.example.dao.impl;

import java.util.HashMap;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	public User getUserByName(String username) {
		HashMap<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("userName", username);
		CriteriaQuery<User> criteria = createEntityCriteria(propertyNameValues);
		criteria.distinct(true);
		Query query = getSession().createQuery(criteria);
		return (User) query.getSingleResult();
	}

}
