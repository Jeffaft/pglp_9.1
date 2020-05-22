import static org.junit.Assert.*;
import ExceptionPers.*;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.After;
import DAO.*;
import Forme.*;
import java.sql.SQLException;
public class TestDAO {
	@BeforeClass
	public static void initDb() {
		
		DataBase.create("DessinTest");
	}
	//TEST CARRE
	@Test
	public void CarreDAOCreateFindTest() {
		//création de l'objet à tester
		Point P = new Point(5,6);
		Carre c = new Carre ("CarreTest", P,20);
		//utilisation du DAO 
		DAO<Carre> carreDAO = FabriqueDAO.getCarreDAO("DessinTest");
		
		//test pour le ca
		try {
			carreDAO.create(c);
			carreDAO.create(c);
		} catch (ExistantException e) { };
		//récupération avec le DAO
		try {
			Carre c2 = carreDAO.find("CarreTest");
			//vérification
			System.out.println("c: "+c.toString());
			System.out.println("c2: "+c2.toString());
			assertEquals(c.toString(),c2.toString());
		} catch (InexistantException e) { };
		
		
		
	}
	@Test 
	public void CarreDeleteTest(){
		Carre Test = null;
		DAO<Carre> carreDAO = FabriqueDAO.getCarreDAO("DessinTest");
		//test delete inexistant
		try {
			carreDAO.delete("inexistant");
		} catch (InexistantException e) { };
		//test delete de l'objet
		try {
			carreDAO.delete("CarreTest");
		} catch (InexistantException e) { };
		//test find inexistant
		try {
			Test = carreDAO.find("CarreTest");
		} catch (InexistantException e) { };
		//vérification
		assertEquals(null,Test);
	}
	
	//TEST RECTANGLE
	@Test
	public void RectangleDAOCreateFindTest() {
		//création de l'objet à tester
		Point P = new Point(5,6);
		Rectangle r = new Rectangle ("RectangleTest", P,20,10);
		//utilisation du DAO 
		DAO<Rectangle> recDAO = FabriqueDAO.getRectangleDAO("DessinTest");
		
		//test pour le ca
		try {
			recDAO.create(r);
			recDAO.create(r);
		} catch (ExistantException e) { };
		//récupération avec le DAO
		try {
			Rectangle r2 = recDAO.find("RectangleTest");
			//vérification
			assertEquals(r.toString(),r2.toString());
		} catch (InexistantException e) { };
		
		
		
	}
	@Test 
	public void RectangleDeleteTest(){
		Rectangle Test = null;
		DAO<Rectangle> recDAO = FabriqueDAO.getRectangleDAO("DessinTest");
		//test delete inexistant
		try {
			recDAO.delete("inexistant");
		} catch (InexistantException e) { };
		//test delete de l'objet
		try {
			recDAO.delete("RectangleTest");
		} catch (InexistantException e) { };
		//test find inexistant
		try {
			Test = recDAO.find("RectangleTest");
		} catch (InexistantException e) { };
		//vérification
		assertEquals(null,Test);
	}
	
	//TEST CERCLE
	@Test
	public void CercleDAOCreateFindTest() {
		//création de l'objet à tester
		Point P = new Point(5,6);
		Cercle c = new Cercle ("CercleTest", P,2);
		//utilisation du DAO 
		DAO<Cercle> cerDAO = FabriqueDAO.getCercleDAO("DessinTest");
		
		//test pour le ca
		try {
			cerDAO.create(c);
			cerDAO.create(c);
		} catch (ExistantException e) { };
		//récupération avec le DAO
		try {
			Cercle c2 = cerDAO.find("CercleTest");
			//vérification
			System.out.println("c: "+c.toString());
			System.out.println("c2: "+c2.toString());
			assertEquals(c.toString(),c2.toString());
		} catch (InexistantException e) { };
		
		
		
	}
	@Test 
	public void CercleDeleteTest(){
		Cercle Test = null;
		DAO<Cercle> cerDAO = FabriqueDAO.getCercleDAO("DessinTest");
		//test delete inexistant
		try {
			cerDAO.delete("inexistant");
		} catch (InexistantException e) { };
		//test delete de l'objet
		try {
			cerDAO.delete("CercleTest");
		} catch (InexistantException e) { };
		//test find inexistant
		try {
			Test = cerDAO.find("CercleTest");
		} catch (InexistantException e) { };
		//vérification
		assertEquals(null,Test);
	}
	//TEST TRIANGLE
	@Test
	public void TriangleDAOCreateFindTest() {
		//création de l'objet à tester
		Point P = new Point(5,6);
		Point P2 = new Point(78,6);
		Point P3 = new Point(20,3);
		Triangle t = new Triangle ("TriangleTest", P,P2,P3);
		//utilisation du DAO 
		DAO<Triangle> triDAO = FabriqueDAO.getTriangleDAO("DessinTest");
		
		//test pour le ca
		try {
			triDAO.create(t);
			triDAO.create(t);
		} catch (ExistantException e) { };
		//récupération avec le DAO
		try {
			Triangle t2 = triDAO.find("TriangleTest");
			//vérification

			assertEquals(t.toString(),t2.toString());
		} catch (InexistantException e) { };
		
		
		
	}
	@Test 
	public void TriangleDeleteTest(){
		Triangle Test = null;
		DAO<Triangle> triDAO = FabriqueDAO.getTriangleDAO("DessinTest");
		//test delete inexistant
		try {
			triDAO.delete("inexistant");
		} catch (InexistantException e) { };
		//test delete de l'objet
		try {
			triDAO.delete("TriangleTest");
		} catch (InexistantException e) { };
		//test find inexistant
		try {
			Test = triDAO.find("TriangleTest");
		} catch (InexistantException e) { };
		//vérification
		assertEquals(null,Test);
	}
	
	
	@AfterClass
	public static void deleteDb() {
		DataBase.reset("DessinTest");
	}
}
