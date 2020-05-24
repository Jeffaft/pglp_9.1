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

public class RectangleDAO extends DAO<Rectangle> {
	Connection conn = null;
	String dbName = "";
	RectangleDAO(String dbName){
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
	public void create(Rectangle r) throws ExistantException {
		this.connect();	//connexion
		PreparedStatement sql = null;
		try {
			sql = this.conn.prepareStatement("SELECT * FROM Rectangle WHERE nom = ?");
			sql.setString(1,r.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				this.disconnect();
				throw new ExistantException(r.getNom());
			}
			else {
				sql = this.conn.prepareStatement("INSERT INTO Rectangle(nom,x ,y,hauteur, longueur) VALUES(?,?,?,?,?)");
				sql.setString(1,r.getNom());
				sql.setInt(2,r.getBas_gauche().getX());
				sql.setInt(3,r.getBas_gauche().getY());
				sql.setInt(4,r.getHauteur());
				sql.setInt(5,r.getLongueur());
				sql.executeUpdate();
				sql = this.conn.prepareStatement("INSERT INTO allForme(NomForme,type)"
						+ " VALUES(?,'rectangle')");
				sql.setString(1,r.getNom());
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
			sql = this.conn.prepareStatement("SELECT * FROM Rectangle WHERE nom = ?");
			sql.setString(1,nom);
			sql.execute();
			ResultSet results = sql.getResultSet();
			if(!results.next()) {
				this.disconnect();
				throw new InexistantException(nom);
			}
			else {
				sql = this.conn.prepareStatement("DELETE FROM Rectangle WHERE nom = ?");
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
	
	public Rectangle find(String nom) throws InexistantException {
		this.connect();
		Rectangle result = null;
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Rectangle WHERE nom = ?");
			sql.setString(1,nom);
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				Point p = new Point(results.getInt("x"),results.getInt("y"));
				result = new Rectangle(results.getString("nom"),p,results.getInt("hauteur"),results.getInt("longueur"));
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
	
	public void update(Rectangle r) throws ExceptionPers {
		this.connect();
		Rectangle result = null;
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Rectangle WHERE nom = ?");
			sql.setString(1,r.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				sql = this.conn.prepareStatement("UPDATE Rectangle set x = ?, y = ?, hauteur = ? longueur = ? WHERE nom = ?");
				sql.setInt(1,r.getBas_gauche().getX());
				sql.setInt(2,r.getBas_gauche().getX());
				sql.setInt(3,r.getHauteur());
				sql.setInt(4,r.getLongueur());
				sql.setString(5,r.getNom());
				sql.executeUpdate();
			}
			else {
				this.disconnect();
				this.create(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();

	}
	
}
