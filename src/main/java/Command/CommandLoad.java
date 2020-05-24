package Command;

import DAO.DAO;
import DAO.DataBase;
import DAO.FabriqueDAO;
import Drawing.DrawingTUI;
import ExceptionPers.CommandeIncorrecteException;
import ExceptionPers.ExceptionPers;
import ExceptionPers.ObjetInexistant;
import Forme.Carre;
import Forme.Cercle;
import Forme.GroupeForme;
import Forme.Rectangle;
import Forme.Triangle;

public class CommandLoad implements Command {
	public void execute(String commande,GroupeForme session,String dbname) 
			throws ExceptionPers {
		String[] str = commande.split(" ");
		//si trop peu d'arguments
		if (str.length!=2) {
			throw new CommandeIncorrecteException("Veuillez entrer une forme à charger");
		}
		String type = DataBase.findType(str[1], dbname);
		if (type.equals(null)) {
			throw new ObjetInexistant(str[1]);
		}
		else if (type.equals("carre")) {
			DAO<Carre> dao = FabriqueDAO.getCarreDAO(dbname);
			session.add(dao.find(str[1]));
		}
		else if (type.equals("rectangle")) {
			DAO<Rectangle> dao = FabriqueDAO.getRectangleDAO(dbname);
			session.add(dao.find(str[1]));
		}
		else if (type.equals("cercle") ) {
			DAO<Cercle> dao = FabriqueDAO.getCercleDAO(dbname);
			session.add(dao.find(str[1]));
		}
		else if (type.equals("triangle")) {
			DAO<Triangle> dao = FabriqueDAO.getTriangleDAO(dbname);
			session.add(dao.find(str[1]));
		}
		else if (type.equals("groupe")) {
			DAO<GroupeForme> dao = FabriqueDAO.getGroupeFormeDAO(dbname);
			session.add(dao.find(str[1]));
		}
		DrawingTUI.afficheResultat(session.toString());
	}
	
}
