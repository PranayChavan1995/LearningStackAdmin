package com.example.dao;

import java.util.HashMap;
import java.util.List;

public interface DaoInterface<K, E> {
	public E getByKey(K key);

	public List<E> findall(HashMap<String, Object> constrains, int pageNo, int resultPerPage);

	public Long findallCount(HashMap<String, Object> constrains);

	public void persist(E entity);

	public void delete(E entity);

	public void deleteByKey(K key);

	public void update(E entity);

	public Long findSearchCount(HashMap entity);

	public List<E> search(HashMap map, int pageNo, int resultPerPage);

	public List<E> search(HashMap map);

	public E uniqueSearch(HashMap map);

	public List<String> ajax(String name, String term);
}
