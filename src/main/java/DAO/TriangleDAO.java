package DAO;
import Forme.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class TriangleDAO extends DAO<Triangle> {
	Connection conn = null;
	TriangleDAO(){
		conn = DataBase.connect();
	}
}
