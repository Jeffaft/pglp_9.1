package DAO;
import Forme.*;
import ExceptionPers.*;
//import ExceptionPers.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CarreDAO extends DAO<Carre> {
	Connection conn = null;
	String dbName = "";
	CarreDAO(String dbName){
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
	public void create(Carre c) throws ExistantException {
		this.connect();	//connexion
		PreparedStatement sql = null;
		try {
			sql = this.conn.prepareStatement("SELECT * FROM Carre WHERE nom = ?");
			sql.setString(1,c.getNom());
			sql.execute();
				ResultSet results = sql.getResultSet();
			if(results.next()) {
				this.disconnect();
				throw new ExistantException();
			}
			else {
				sql = this.conn.prepareStatement("INSERT INTO Carre(nom,x ,y, longueur) VALUES(?,?,?,?)");
				sql.setString(1,c.getNom());
				sql.setInt(2,c.getBas_gauche().getX());
				sql.setInt(3,c.getBas_gauche().getY());
				sql.setInt(4,c.getL());
				sql.executeUpdate();
				sql = this.conn.prepareStatement("INSERT INTO allForme(NomForme,type)"
						+ " VALUES(?,carre)");
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
			sql = this.conn.prepareStatement("SELECT * FROM Carre WHERE nom = ?");
			sql.setString(1,nom);
			sql.execute();
			ResultSet results = sql.getResultSet();
			if(!results.next()) {
				this.disconnect();
				throw new InexistantException();
			}
			else {
				sql = this.conn.prepareStatement("DELETE FROM Carre WHERE nom = ?");
				sql.setString(1,nom);
				sql.executeUpdate();
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.disconnect();
	}
	
	public Carre find(String nom) throws InexistantException {
		this.connect();
		Carre result = null;
		try {
			PreparedStatement sql =
					this.conn.prepareStatement("SELECT * FROM Carre WHERE nom = ?");
			sql.setString(1,nom);
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
			
	
		
		return result;
	}
	
}
