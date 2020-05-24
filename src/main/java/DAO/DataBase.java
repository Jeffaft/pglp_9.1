package DAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public abstract class DataBase {
	
	public static Connection connect(String dbName) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:derby:"+dbName+";create=true");
		}  catch (Exception e) {
            e.printStackTrace();
        }
		
		return conn;
	}
	public static void  create(String dbName) {
		Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:derby:"+dbName+";create=true");
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
                    + "type varchar(30)"
                    + ")";
            String Groupe = "CREATE TABLE groupe("
                    + "NomGroupe varchar(30),"
            		+ "NomForme varchar(30), "
                    + "ordreF int,"
                    + "PRIMARY KEY(NomGroupe,NomForme)"
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
	
	public static void reset(String dbName) {
		Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:derby:"+dbName+";create=true");
            Statement stmt = conn.createStatement();
            try {
                
                stmt.execute("DROP TABLE Carre");
                System.out.println("table Carre deleted");                                                                                       
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
            	stmt.execute("DROP TABLE Rectangle");
                System.out.println("table Rectangle deleted");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
            	stmt.execute("DROP TABLE Cercle");
                System.out.println("table Cercle deleted");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
            	stmt.execute("DROP TABLE Triangle");
                System.out.println("table Triangle deleted");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
            	stmt.execute("DROP TABLE Groupe");
                System.out.println("table Groupe deleted");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
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
	
	public static String afficheTable(String tablename,String dbName) {
		Connection conn = DataBase.connect(dbName);
		String str = "";
		try {
			PreparedStatement sql = conn.prepareStatement("SELECT * FROM "+ tablename);
			sql.execute();
			
			ResultSet results = sql.getResultSet();
			str += "===============================\n";
			str+= "Table : "+ tablename +"\n";
			while(results.next()) {
				str += results.getObject(1) +" "+results.getObject(2) +"\n";
			}
			str += "===============================\n";
			conn.close();
		} catch (Exception e) 
		{ 
			e.printStackTrace();
		}
		return str;
	}
	
	public static String recapBDD(String dbName) {
		String str = "****RECAP DESSINS SAUVEGARDES****\n";
		str += afficheTable("allForme", dbName);
		return str;
	}
	
	public static String findType (String nom,String dbName) {
		String result = null;
		Connection conn = DataBase.connect(dbName);
		try {
			PreparedStatement sql = conn.prepareStatement("SELECT * FROM allForme WHERE NomForme = ? ");
			sql.setString(1, nom);
			sql.execute();	
			ResultSet results = sql.getResultSet();
			if(results.next()) {
				result = results.getString("type");
				conn.close();
				return  result;
			}
			conn.close();
		} catch (Exception e) 
		{ 
			e.printStackTrace();
		}
		
		return result;
	}
}


