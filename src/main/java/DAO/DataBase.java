package DAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public abstract class DataBase {
	
	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:derby:DessinBDD;create=true");
		}  catch (SQLException e) {
            e.printStackTrace();
        }
		
		return conn;
	}
	public static void  create() {
		Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:derby:DessinBDD;create=true");
            String Carre = "CREATE TABLE Carre("
            		+ "Nom varchar(30),"
                    + "x int,"
                    + "y int,"
                    + "longueur int,"             
                    + "PRIMARY KEY(Nom)"
                    + ")";
            String Rectangle = "CREATE TABLE Rectangle("
            		+ "Nom varchar(30),"
                    + "x int,"
                    + "y int,"
                    + "hauteur int,"
                    + "longueur int,"
                    + "PRIMARY KEY(Nom)"
                    + ")";
            String Cercle = "CREATE TABLE Cercle("
		            + "Nom varchar(30),"
		            + "x int,"
		            + "y int,"
		            + "rayon int,"
		            + "PRIMARY KEY(Nom)"
		            + ")";
            
            String Triangle = "CREATE TABLE Triangle("
		            + "Nom varchar(30),"
		            + "xA int,"
		            + "yA int,"
		            + "xB int,"
		            + "yB int,"
		            + "xC int,"
		            + "yC int,"
		            + "PRIMARY KEY(Nom)"
		            + ")";
            String allForme = "CREATE TABLE allForme("
                    + "NomForme varchar(30),"
                    + "type varchar(30),"
                    + "PRIMARY KEY(NomForme)"
                    + ")";
            String Groupe = "CREATE TABLE groupe("
                    + "NomGroupe varchar(30),"
            		+ "NomForme varchar(30), "
                    + "PRIMARY KEY(NomGroupe,NomForme),"
                    + "FOREIGN KEY(NomForme) REFERENCES allForme(NomForme)"
                    + ")";   
            Statement stmt = conn.createStatement();

            try {
                stmt.execute(Carre);
                System.out.println("table Carre created");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Rectangle);
                System.out.println("table Rectangle created");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Cercle);
                System.out.println("table Cercle created");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Triangle);
                System.out.println("table Triangle created");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(allForme);
                System.out.println("table allForme created");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Groupe);
                System.out.println("table Groupe created");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static void reset() {
		Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:derby:DessinBDD;create=true");
            try {
                Statement stmt = conn.createStatement();
                stmt.execute("DROP TABLE Carre");
                System.out.println("table Carre deleted");
                stmt.execute("DROP TABLE Rectangle");
                System.out.println("table Rectangle deleted");
                stmt.execute("DROP TABLE Cercle");
                System.out.println("table Cercle deleted");
                stmt.execute("DROP TABLE Triangle");
                System.out.println("table Triangle deleted");
                stmt.execute("DROP TABLE Groupe");
                System.out.println("table Groupe deleted");
                stmt.execute("DROP TABLE allForme");
                System.out.println("table allForme deleted");
                

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    System.out.println("close");
                    conn.close();
                } catch (SQLException e) { }
            }
        }
	}
}


