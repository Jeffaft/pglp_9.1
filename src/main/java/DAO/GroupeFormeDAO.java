package DAO;
import Forme.*;
import ExceptionPers.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class GroupeFormeDAO extends DAO<GroupeForme>{
	Connection conn = null;
	String dbName = "";
	GroupeFormeDAO(String dbName){
		this.dbName = dbName;
	}
	public void connect() {
		this.conn = DataBase.connect(dbName);
	}
	public void disconnect() {
		try {
			System.out.println("disconnecting");
			this.conn.close();
		} catch (SQLException e) 
		{ 
			
		}
	}
	public void create(GroupeForme gp) throws ExistantException{
		this.connect();	//connexion
		PreparedStatement sql = null;
		try {
			sql = this.conn.prepareStatement("SELECT * FROM Groupe WHERE nom = ?");
			sql.setString(1,gp.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				this.disconnect();
				throw new ExistantException();
			}
			else {
				//insertion dans la table allForme
				sql = this.conn.prepareStatement("INSERT INTO allForme(NomForme,type)"
						+ " VALUES(?,groupe)");
				sql.setString(1,gp.getNom());
				sql.executeUpdate();
				Iterator<Forme> itr = gp.getListForme().iterator();
				Forme f;
				while (itr.hasNext()) {
					f = itr.next();
					
					if (f instanceof Carre) {
						DAO<Carre> carrDAO = FabriqueDAO.getCarreDAO(dbName);
						carrDAO.create((Carre)f);
					}
					else if (f instanceof Rectangle) {
						DAO<Rectangle> recDAO = FabriqueDAO.getRectangleDAO(dbName);
						recDAO.create((Rectangle)f);
					}
					else if (f instanceof Cercle) {
						DAO<Cercle> cerDAO = FabriqueDAO.getCercleDAO(dbName);
						cerDAO.create((Cercle)f);
					}
					else if (f instanceof Triangle) {
						DAO<Triangle> triDAO = FabriqueDAO.getTriangleDAO(dbName);
						triDAO.create((Triangle)f);
					}
					else if (f instanceof GroupeForme) {
						DAO<GroupeForme> gpDAO = FabriqueDAO.getGroupeFormeDAO(dbName);
						gpDAO.create((GroupeForme)f);
					}
					
					sql = this.conn.prepareStatement("INSERT INTO Groupe(NomGroupe,NomForme)"
							+ " VALUES (?,?)");
					sql.setString(1, gp.getNom());
					sql.setString(2, f.getNom());
					sql.executeUpdate();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
	}
	
	public GroupeForme find(String nomGroupe) throws InexistantException {
		this.connect();
		GroupeForme result = null;
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Groupe WHERE nom = ?");
			sql.setString(1,nomGroupe);
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				Point p = new Point(results.getInt("x"),results.getInt("y"));
				result = new Carre(results.getString("nom"),p,results.getInt("longueur"));
			}
			else {
				this.disconnect();
				throw new InexistantException();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
	}
	
}
