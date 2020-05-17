package DAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public abstract class DataBase {
	
	public void create() {
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
            + "Ax int,"
            + "Ay int,"
            + "Bx int,"
            + "By int,"
            + "Cx int,"
            + "Cy int,"
            + "PRIMARY KEY(Nom)"
            + ")";
            String Groupe = "CREATE TABLE groupe("
                    + "Nomgroupe varchar(30),"
            		+ "NomForme varchar(30), "
                    + "PRIMARY KEY(NomGroupe,NomForme),"
                    + "FOREIGN KEY(NomForme) REFERENCES allForme(NomForme)"
                    + ")";
            String allForme = "CREATE TABLE allForme("
                    + "NomForme varchar(30),"
                    + "type varchar(30),"
                    + "PRIMARY KEY(NomGroupe)"

                    + ")";
            Statement stmt = conn.createStatement();

            try {
                stmt.execute(Carre);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Rectangle);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Cercle);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Triangle);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Groupe);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(allForme);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	}


