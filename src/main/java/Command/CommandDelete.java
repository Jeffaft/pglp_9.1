package Command;

import DAO.DAO;
import DAO.DataBase;
import DAO.FabriqueDAO;
import ExceptionPers.CommandeIncorrecteException;
import ExceptionPers.ExceptionPers;
import ExceptionPers.InexistantException;
import ExceptionPers.ObjetInexistant;
import Forme.*;
import Forme.Carre;
import Forme.GroupeForme;

public class CommandDelete implements Command{
	public void execute(String commande,GroupeForme session,String dbname)
		throws ExceptionPers{
		String[] str = commande.split(" ");
		//verif nombre d'arguments
		if (str.length!=2) {
			throw new CommandeIncorrecteException("Vous ne pouvez supprimer qu'une figure à la fois.");
		}
		String type = DataBase.findType(str[1], dbname);
		if (type.equals("")) {
			throw new InexistantException(str[1]);
		}
		else if (type.equals("carre")) {
			DAO<Carre> dao = FabriqueDAO.getCarreDAO(dbname);
			dao.delete(str[1]);
		}
		else if (type.equals("rectangle")) {
			DAO<Rectangle> dao = FabriqueDAO.getRectangleDAO(dbname);
			dao.delete(str[1]);
		}
		else if (type.equals("cercle") ) {
			DAO<Cercle> dao = FabriqueDAO.getCercleDAO(dbname);
			dao.delete(str[1]);
		}
		else if (type.equals("triangle")) {
			DAO<Triangle> dao = FabriqueDAO.getTriangleDAO(dbname);
			dao.delete(str[1]);
		}
		else if (type.equals("groupe")) {
			DAO<GroupeForme> dao = FabriqueDAO.getGroupeFormeDAO(dbname);
			dao.delete(str[1]);
		}
	}
}
