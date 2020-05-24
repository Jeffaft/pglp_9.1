package DAO;
import Forme.*;
public class FabriqueDAO {	

	public static DAO<Carre> getCarreDAO(String dbName){
		return new CarreDAO(dbName);
	}
	public static DAO<Rectangle> getRectangleDAO(String dbName){
		return new RectangleDAO(dbName);
	}
	public static DAO<Cercle> getCercleDAO(String dbName){
		return new CercleDAO(dbName);
	}
	public static DAO<Triangle> getTriangleDAO(String dbName){
		return new TriangleDAO(dbName);
	}
	public static DAO<GroupeForme> getGroupeFormeDAO(String dbName){
		return new GroupeFormeDAO(dbName);
	}
}
