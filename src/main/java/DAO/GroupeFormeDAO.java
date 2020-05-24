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
	DAO<Carre> carrDAO;
	DAO<Rectangle> recDAO;
	DAO<Cercle> cerDAO;
	DAO<Triangle> triDAO;
	GroupeFormeDAO(String dbName){
		this.dbName = dbName;
		carrDAO = FabriqueDAO.getCarreDAO(dbName);
		recDAO = FabriqueDAO.getRectangleDAO(dbName);
		cerDAO = FabriqueDAO.getCercleDAO(dbName);
		triDAO = FabriqueDAO.getTriangleDAO(dbName);
	}
	public void connect() {
		this.conn = DataBase.connect(dbName);
	}
	public void disconnect() {
		try {
			this.conn.close();
		} catch (SQLException e) 
		{ 
			
		}
	}
	public void create(GroupeForme gp) throws ExistantException{
		this.connect();	//connexion
		PreparedStatement sql = null;
		try {
			sql = this.conn.prepareStatement("SELECT * FROM Groupe WHERE nomGroupe = ?");
			sql.setString(1,gp.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				this.disconnect();
				throw new ExistantException(gp.getNom());
			}
			else {
				//insertion dans la table allForme
				sql = this.conn.prepareStatement("INSERT INTO allForme(NomForme,type)"
						+ " VALUES(?,'groupe')");
				sql.setString(1,gp.getNom());
				sql.executeUpdate();
				//parcours du groupe pour insérer tout les objet
				Iterator<Forme> itr = gp.getListIteraror();
				Forme f = null;
				int order = 0;
				while (itr.hasNext()) {
					f = itr.next();
					
					if (f instanceof Carre) {
						
						carrDAO.create((Carre)f);
					}
					else if (f instanceof Rectangle) {
						
						recDAO.create((Rectangle)f);
					}
					else if (f instanceof Cercle) {
						
						cerDAO.create((Cercle)f);
					}
					else if (f instanceof Triangle) {
						
						triDAO.create((Triangle)f);
					}
					else if (f instanceof GroupeForme) {
						DAO<GroupeForme> gpDAO = FabriqueDAO.getGroupeFormeDAO(dbName);
						gpDAO.create((GroupeForme)f);
					}
					
					sql = this.conn.prepareStatement("INSERT INTO Groupe(NomGroupe,NomForme,ordreF)"
							+ " VALUES (?,?,?)");
					sql.setString(1, gp.getNom());
					sql.setString(2, f.getNom());
					sql.setInt(3, order++);
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
			PreparedStatement sql2 = null;
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Groupe WHERE nomGroupe = ? ORDER by ordreF");
			sql.setString(1,nomGroupe);
			sql.execute();
				ResultSet results = sql.getResultSet();		
			if(results.next()) {
				result = new GroupeForme(nomGroupe);
				do {
				
					sql2 = this.conn.prepareStatement("SELECT * FROM allForme WHERE NomForme = ?");
					sql2.setString(1,results.getString("NomForme"));
					sql2.execute();
					ResultSet results2 = sql2.getResultSet();
					results2.next();
					if (results2.getString("type").equals("carre")) {
						result.add(carrDAO.find(results.getString("NomForme")));
					}
					else if (results2.getString("type").equals("rectangle")) {
						result.add(recDAO.find(results.getString("NomForme")));
					}
					else if (results2.getString("type").equals("cercle")) {
						result.add(cerDAO.find(results.getString("NomForme")));
					}
					else if (results2.getString("type").equals("triangle")) {
						result.add(triDAO.find(results.getString("NomForme")));
					}
					else if (results2.getString("type").equals("groupe")) {
						DAO<GroupeForme> gpDAO = FabriqueDAO.getGroupeFormeDAO(dbName);
						result.add(gpDAO.find(results.getString("NomForme")));
					}
				} while (results.next());
				
			}
			else {
				this.disconnect();
				throw new InexistantException(nomGroupe);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
		return result;
	}
	
	public void delete(String nomGroupe) throws InexistantException {
		this.connect();
		GroupeForme result = null;
		try {
			PreparedStatement sql2 = null;
			PreparedStatement sql =
			this.conn.prepareStatement("SELECT * FROM Groupe WHERE nomGroupe = ? ORDER by ordreF");
			sql.setString(1,nomGroupe);
			sql.execute();
				ResultSet results = sql.getResultSet();		
			if(results.next()) {

				do {			
					sql2 = this.conn.prepareStatement("SELECT * FROM allForme WHERE NomForme = ?");
					sql2.setString(1,results.getString("NomForme"));
					sql2.execute();
					ResultSet results2 = sql2.getResultSet();
					results2.next();					
					if (results2.getString("type").equals("carre")) {
						carrDAO.delete(results.getString("NomForme"));
					}
					else if (results2.getString("type").equals("rectangle")) {
						recDAO.delete(results.getString("NomForme"));
					}
					else if (results2.getString("type").equals("cercle")) {
						cerDAO.delete(results.getString("NomForme"));
					}
					else if (results2.getString("type").equals("triangle")) {
						triDAO.delete(results.getString("NomForme"));
					}
					else if (results2.getString("type").equals("groupe")) {
						DAO<GroupeForme> gpDAO = FabriqueDAO.getGroupeFormeDAO(dbName);
						gpDAO.delete(results.getString("NomForme"));
					}
				} while (results.next());
				//suppresion de toutes les références dans la table Groupe
				sql =this.conn.prepareStatement("DELETE FROM Groupe WHERE nomGroupe = ? ");
				sql.setString(1,nomGroupe);
				sql.execute();
				//suppresion dans la table allForme
				sql =this.conn.prepareStatement("DELETE FROM allForme WHERE NomForme = ? ");
				sql.setString(1,nomGroupe);
				sql.execute();
				
			}
			else {
				this.disconnect();
				throw new InexistantException(nomGroupe);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
		
	}
	
	public void update(GroupeForme gp) throws ExceptionPers {
		this.connect();
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Groupe WHERE nomGroupe = ? ORDER by ordreF");
			sql.setString(1,gp.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();		
			if(results.next()) {
				this.disconnect();
				this.delete(gp.getNom());
				this.create(gp);
			}
			else {
				this.disconnect();
				this.create(gp);;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
	}
	
	
}
