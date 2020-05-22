package DAO;
import Forme.*;
public class FabriqueDAO {
	public static DAO<Carre> getCarreDAO(){
		return new CarreDAO();
	}
	public static DAO<Rectangle> getRectangleDAO(){
		return new RectangleDAO();
	}
	public static DAO<Cercle> getCercleDAO(){
		return new CercleDAO();
	}
	public static DAO<Triangle> getTriangleDAO(){
		return new TriangleDAO();
	}
}
