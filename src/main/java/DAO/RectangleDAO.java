package DAO;
import Forme.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RectangleDAO extends DAO<Rectangle> {
	Connection conn = null;
	RectangleDAO(){
		conn = DataBase.connect();
	}
}
