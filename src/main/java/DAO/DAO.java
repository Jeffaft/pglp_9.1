package DAO;

import ExceptionPers.ExistantException;
import ExceptionPers.InexistantException;

public abstract class DAO<T> {
	public abstract void create (T obj) throws ExistantException;
	public abstract T find (String nom)throws InexistantException;
	//public abstract T update (T obj);
	public abstract void delete (String nom)throws InexistantException;
	
}
