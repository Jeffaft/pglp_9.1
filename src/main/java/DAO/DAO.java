package DAO;

import ExceptionPers.ExceptionPers;
import ExceptionPers.ExistantException;
import ExceptionPers.InexistantException;

public abstract class DAO<T> {
	public abstract void create (T obj) throws ExistantException;
	public abstract T find (String nom)throws InexistantException;
	//modifie l'objet en paramatre par l'objet en base, seul le nom ne change pas.
	public abstract void update (T obj) throws ExceptionPers;
	public abstract void delete (String nom)throws InexistantException;
	
}
