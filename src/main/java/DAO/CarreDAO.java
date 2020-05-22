package DAO;
import Forme.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CarreDAO extends DAO<Carre> {
	Connection conn = null;
	
	CarreDAO(){
		conn = DataBase.connect();
	}
}
