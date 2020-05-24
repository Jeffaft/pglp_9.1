import static org.junit.Assert.*;
import org.junit.Test;

import Forme.Carre;
import Forme.Cercle;
import Forme.GroupeForme;
import Forme.Point;
import Forme.Rectangle;
import Forme.Triangle;
public class TestMoove {
	@Test
	public void carreMoove() {
		Point a = new Point(5,2);
		Carre c = new Carre("c1",a,2);
		c.moove(5, 8);
		assertTrue(c.getBas_gauche().getX() == 10);
		assertTrue(c.getBas_gauche().getY() == 10);
	}
	@Test
	public void cercleMoove() {
		Point a = new Point(5,2);
		Cercle c = new Cercle("c1",a,2);
		c.moove(5, 8);
		assertTrue(c.getCentre().getX() == 10);
		assertTrue(c.getCentre().getY() == 10);
	}
	@Test
	public void rectangleMoove() {
		Point a = new Point(5,2);
		Rectangle r = new Rectangle("r1",a,2,4);
		r.moove(5, 8);
		assertTrue(r.getBas_gauche().getX() == 10);
		assertTrue(r.getBas_gauche().getY() == 10);
	}
	@Test
	public void TriangleMoove() {
		Point a = new Point(5,2);
		Point b = new Point(10,12);
		Point c = new Point(20,2);
		Triangle t = new Triangle ("t",a,b,c);

		t.moove(5, 8);
		assertTrue(t.getA().getX() == 10);
		assertTrue(t.getA().getY() == 10);
		assertTrue(t.getB().getX() == 15);
		assertTrue(t.getB().getY() == 20);
		assertTrue(t.getC().getX() == 25);
		assertTrue(t.getC().getY() == 10);
	}
	@Test
	public void PointMoove() {
		Point a = new Point(5,2);
		a.moovePoint(5, 8);
		assertTrue(a.getX()== 10);
		assertTrue(a.getY() == 10);
	}
}	
