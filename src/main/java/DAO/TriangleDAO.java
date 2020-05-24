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


public class TriangleDAO extends DAO<Triangle> {
	Connection conn = null;
	String dbName = "";
	TriangleDAO(String dbName){
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
	public void create(Triangle t) throws ExistantException {
		this.connect();	//connexion
		PreparedStatement sql = null;
		try {
			sql = this.conn.prepareStatement("SELECT * FROM Triangle WHERE nom = ?");
			sql.setString(1,t.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				this.disconnect();
				throw new ExistantException(t.getNom());
			}
			else {
				sql = this.conn.prepareStatement("INSERT INTO Triangle(nom,xA,yA,xB,yB,xC,yC) VALUES(?,?,?,?,?,?,?)");
				sql.setString(1,t.getNom());
				sql.setInt(2,t.getA().getX());
				sql.setInt(3,t.getA().getY());
				sql.setInt(4,t.getB().getX());
				sql.setInt(5,t.getB().getY());
				sql.setInt(6,t.getC().getX());
				sql.setInt(7,t.getC().getY());
				sql.executeUpdate();
				sql = this.conn.prepareStatement("INSERT INTO allForme(NomForme,type)"
						+ " VALUES(?,'triangle')");
				sql.setString(1,t.getNom());
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
			sql = this.conn.prepareStatement("SELECT * FROM Triangle WHERE nom = ?");
			sql.setString(1,nom);
			sql.execute();
			ResultSet results = sql.getResultSet();
			if(!results.next()) {
				this.disconnect();
				throw new InexistantException(nom);
			}
			else {
				sql = this.conn.prepareStatement("DELETE FROM Triangle WHERE nom = ?");
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
	
	public Triangle find(String nom) throws InexistantException {
		this.connect();
		Triangle result = null;
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Triangle WHERE nom = ?");
			sql.setString(1,nom);
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				Point p = new Point(results.getInt("xA"),results.getInt("yA"));
				Point p2 = new Point(results.getInt("xB"),results.getInt("yB"));
				Point p3 = new Point(results.getInt("xC"),results.getInt("yC"));
				result = new Triangle(results.getString("nom"),p,p2,p3);
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
	
	public void update(Triangle t) throws ExceptionPers {
		this.connect();
		Triangle result = null;
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Triangle WHERE nom = ?");
			sql.setString(1,t.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				sql = this.conn.prepareStatement("UPDATE Triangle"
						+ " set xA = ?, yA = ?,"
						+ " xB = ?, yB =?,"
						+ " xC = ?, yC = ?,"
						+ " WHERE nom = ?");
				sql.setInt(1,t.getA().getX());
				sql.setInt(2,t.getA().getY());
				sql.setInt(3,t.getB().getX());
				sql.setInt(4,t.getB().getY());
				sql.setInt(5,t.getC().getX());
				sql.setInt(5,t.getC().getY());
				sql.setString(5,t.getNom());
				sql.executeUpdate();
			}
			else {
				this.disconnect();
				this.create(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();

	}
}
