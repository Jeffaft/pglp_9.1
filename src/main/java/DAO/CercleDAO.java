package DAO;
import Forme.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import ExceptionPers.ExceptionPers;
import ExceptionPers.ExistantException;
import ExceptionPers.InexistantException;


public class CercleDAO extends DAO<Cercle>{
	Connection conn = null;
	String dbName = "";
	CercleDAO(String dbName){
		this.dbName = dbName;
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
	public void create(Cercle c) throws ExistantException {
		this.connect();	//connexion
		PreparedStatement sql = null;
		try {
			sql = this.conn.prepareStatement("SELECT * FROM Cercle WHERE nom = ?");
			sql.setString(1,c.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				this.disconnect();
				throw new ExistantException(c.getNom());
			}
			else {
				sql = this.conn.prepareStatement("INSERT INTO Cercle(nom,x ,y,rayon) VALUES(?,?,?,?)");
				sql.setString(1,c.getNom());
				sql.setInt(2,c.getCentre().getX());
				sql.setInt(3,c.getCentre().getY());
				sql.setInt(4,c.getRayon());
				sql.executeUpdate();
				sql = this.conn.prepareStatement("INSERT INTO allForme(NomForme,type)"
						+ " VALUES(?,'cercle')");
				sql.setString(1,c.getNom());
				sql.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
	}
	
	public void delete(String nom) throws InexistantException {
		this.connect();
		PreparedStatement sql = null;
		try {
			sql = this.conn.prepareStatement("SELECT * FROM Cercle WHERE nom = ?");
			sql.setString(1,nom);
			sql.execute();
			ResultSet results = sql.getResultSet();
			if(!results.next()) {
				this.disconnect();
				throw new InexistantException(nom);
			}
			else {
				sql = this.conn.prepareStatement("DELETE FROM Cercle WHERE nom = ?");
				sql.setString(1,nom);
				sql.executeUpdate();
				sql = this.conn.prepareStatement("DELETE FROM allForme WHERE nomForme = ?");
				sql.setString(1,nom);
				sql.executeUpdate();
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
	}
	
	public Cercle find(String nom) throws InexistantException {
		this.connect();
		Cercle result = null;
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Cercle WHERE nom = ?");
			sql.setString(1,nom);
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				Point p = new Point(results.getInt("x"),results.getInt("y"));
				result = new Cercle(results.getString("nom"),p,results.getInt("rayon"));
			}
			else {
				this.disconnect();
				throw new InexistantException(nom);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
			
	
		
		return result;
	}
	
	public void update(Cercle c) throws ExceptionPers {
		this.connect();
		Cercle result = null;
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Cercle WHERE nom = ?");
			sql.setString(1,c.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				sql = this.conn.prepareStatement("UPDATE Cercle set x = ?, y = ?, rayon = ? WHERE nom = ?");
				sql.setInt(1,c.getCentre().getX());
				sql.setInt(2,c.getCentre().getY());
				sql.setInt(3,c.getRayon());
				sql.setString(4,c.getNom());
				sql.executeUpdate();
			}
			else {
				this.disconnect();
				this.create(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
			
	}
	
	
}
