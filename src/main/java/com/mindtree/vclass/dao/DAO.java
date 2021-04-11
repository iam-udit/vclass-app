package com.mindtree.vclass.dao;

import java.util.List;

import com.mindtree.vclass.exception.DAOException;

/**
 * This DAO interface is used to implement Model operations
 * 
 * @author D-HDKR
 * @version 1.0
 * @param <T>
 */
public interface DAO <T> {

	public T read(Object value) throws DAOException;
	public List<T> read() throws DAOException;
	public boolean create(T t) throws DAOException;
	public boolean update(T t) throws DAOException;
	public boolean delete(Object value) throws DAOException;
}
