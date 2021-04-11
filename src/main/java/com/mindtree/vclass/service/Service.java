package com.mindtree.vclass.service;

import java.util.List;
import com.mindtree.vclass.exception.ServiceException;


/**
 * This interface is used to perform service operation
 * 
 * @author D-HDKR
 * @version 1.0
 */
public interface Service <T> {

	public boolean isExists(String value) throws ServiceException;
	public T read(Object object) throws ServiceException;
	public List<T> read() throws ServiceException;
	public boolean create(T t) throws ServiceException;
	public boolean update(T t) throws ServiceException;
	public boolean delete(Object value) throws ServiceException;

}
