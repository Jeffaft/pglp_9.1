package DAO;

public abstract class DAO<T> {
	public abstract void create (T obj);
	public abstract T find (String nom);
	public abstract T update (T obj);
	public abstract void delete (T obj);
	
}
