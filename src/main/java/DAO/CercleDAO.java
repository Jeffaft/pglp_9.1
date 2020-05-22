package DAO;
import Forme.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class CercleDAO extends DAO<Cercle>{
	Connection conn = null;
	CercleDAO(){
		conn = DataBase.connect();
	}
}
