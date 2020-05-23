import static org.junit.Assert.*;
import ExceptionPers.*;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
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
	public void CarreDAOCreateFindDeleteTest() {
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
			System.out.println(DataBase.afficheTable("allForme","DessinTest"));
			Carre c2 = carreDAO.find("CarreTest");
			//vérification
			System.out.println("c: "+c.toString());
			System.out.println("c2: "+c2.toString());
			assertEquals(c.toString(),c2.toString());
		} catch (InexistantException e) { };
		//
		Carre Test = null;
		//DAO<Carre> carreDAO = FabriqueDAO.getCarreDAO("DessinTest");
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
		//
		
		
	}
	@Test 
	public void CarreDeleteTest(){
		
	}
	
	//TEST RECTANGLE
	@Test
	public void RectangleDAOCreateFindDeleteTest() {
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
		System.out.println(DataBase.afficheTable("allForme","DessinTest"));
		try {
			
			Rectangle r2 = recDAO.find("RectangleTest");
			//vérification
			assertEquals(r.toString(),r2.toString());
		} catch (InexistantException e) { };
		
		//
		Rectangle Test = null;
		//DAO<Rectangle> recDAO = FabriqueDAO.getRectangleDAO("DessinTest");
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
		//
		
	}
	@Test 
	public void RectangleDeleteTest(){
		
	}
	
	//TEST CERCLE
	@Test
	public void CercleDAOCreateFindDeleteTest() {
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
		//
		Cercle Test = null;
		//DAO<Cercle> cerDAO = FabriqueDAO.getCercleDAO("DessinTest");
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
	@Test 
	public void CercleDeleteTest(){
		
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
		//
		Triangle Test = null;
		//DAO<Triangle> triDAO = FabriqueDAO.getTriangleDAO("DessinTest");
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
	@Test 
	public void TriangleDeleteTest(){
		
	}
	
	//TEST GROUPE
	@Test
	public void GroupeFormeDAOCreateFindSIMPLETest() {
		//création de l'objet à tester
		//un triangle
		Point P = new Point(5,6);
		Point P2 = new Point(78,6);
		Point P3 = new Point(20,3);
		Triangle t = new Triangle ("TriangleTestgp", P,P2,P3);
		//un carre
		Point a = new Point(5,6);
		Carre c = new Carre ("CarreTestgp", a,20);
		//un rectangle
		Point R1 = new Point(5,6);
		Rectangle r = new Rectangle ("RectangleTestgp", R1,20,10);
		//un cercle
		Point c1 = new Point(5,6);
		Cercle cer = new Cercle ("CercleTestgp", c1,2);
		//ajout dans un groupeforme
		GroupeForme gp = new GroupeForme("GroupeTestgp");
		gp.add(t);
		gp.add(c);
		gp.add(cer);
		gp.add(r);
		//utilisation du DAO 
		DAO<GroupeForme> gpDAO = FabriqueDAO.getGroupeFormeDAO("DessinTest");
		System.out.println("GP :"+gp.toString());
		System.out.println(DataBase.afficheTable("allForme","DessinTest"));
		System.out.println(DataBase.afficheTable("Carre","DessinTest"));
		System.out.println(DataBase.afficheTable("Triangle","DessinTest"));
		System.out.println(DataBase.afficheTable("Cercle","DessinTest"));
		System.out.println(DataBase.afficheTable("Rectangle","DessinTest"));
		System.out.println(DataBase.afficheTable("groupe","DessinTest"));
		//test pour le ca
		try {
			gpDAO.create(gp);
			gpDAO.create(gp);
		} catch (ExistantException e) { };
		//récupération avec le DAO
		try {
			GroupeForme gp2 = gpDAO.find("GroupeTestgp");
			//vérification
			System.out.println("GP2 :"+gp2.toString());
			assertEquals(gp.toString(),gp2.toString());
		} catch (InexistantException e) { };
		try {
			gpDAO.delete("GroupeTestgp");
		} catch (InexistantException e) { };
	}
	@Test
	public void GroupeFormeDAOCreateFindIMBRIQUETest() {
		//création de l'objet à tester
		//un triangle
		Point P = new Point(5,6);
		Point P2 = new Point(78,6);
		Point P3 = new Point(20,3);
		Triangle t = new Triangle ("TriangleTestgpIMBR", P,P2,P3);
		//un carre
		Point a = new Point(5,6);
		Carre c = new Carre ("CarreTestgpIMBR", a,20);
		//un rectangle
		Point R1 = new Point(5,6);
		Rectangle r = new Rectangle ("RectangleTestgpI", R1,20,10);
		//un cercle
		Point c1 = new Point(5,6);
		Cercle cer = new Cercle ("CercleTestgpI", c1,2);
		//ajout des groupes :
		GroupeForme gp1 = new GroupeForme("GroupeTriangleCarregp");
		gp1.add(t);
		gp1.add(c);
		GroupeForme gp = new GroupeForme("GroupeGlobal");
		gp.add(cer);
		gp.add(r);
		gp.add(gp1);
		//utilisation du DAO 
		DAO<GroupeForme> gpDAO = FabriqueDAO.getGroupeFormeDAO("DessinTest");
		//test pour le ca
		try {
			gpDAO.create(gp);
			gpDAO.create(gp);
		} catch (ExistantException e) { };
		//récupération avec le DAO
		try {
			GroupeForme gp2 = gpDAO.find("GroupeGlobal");
			//vérification
			System.out.println("GP global2 :"+gp2.toString());
			assertEquals(gp.toString(),gp2.toString());
		} catch (InexistantException e) { };
		
		try {
			gpDAO.delete("GroupeGlobal");
		} catch (InexistantException e) { };
	}
	@AfterClass
	public static void deleteDb() {
		System.out.println("TABLES APRES TEST");
		System.out.println(DataBase.afficheTable("allForme","DessinTest"));
		System.out.println(DataBase.afficheTable("Carre","DessinTest"));
		System.out.println(DataBase.afficheTable("Triangle","DessinTest"));
		System.out.println(DataBase.afficheTable("Cercle","DessinTest"));
		System.out.println(DataBase.afficheTable("Rectangle","DessinTest"));
		System.out.println(DataBase.afficheTable("groupe","DessinTest"));
		DataBase.reset("DessinTest");
	}
}
