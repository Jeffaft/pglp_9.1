package Command;

import DAO.DAO;
import DAO.DataBase;
import DAO.FabriqueDAO;
import Drawing.DrawingTUI;
import ExceptionPers.*;
import Forme.Carre;
import Forme.Cercle;
import Forme.Forme;
import Forme.GroupeForme;
import Forme.Rectangle;
import Forme.Triangle;

public class CommandSave implements Command{
	//save nom1 nom2 etc
	public void execute(String commande,GroupeForme session, String dbname) 
		throws ExceptionPers{
		String[] str = commande.split(" ");
		//si trop peu d'arguments
		if (str.length <2) {
			throw new CommandeIncorrecteException("il faut au moins une forme à sauvegarder.");
		}
		else {
			for (int i = 1; i<str.length; i++) {
				//vérif dessiné
				if (!session.contain(str[i])) {
				
					throw new ObjetInexistant(str[1]);
				}
				else {
					Forme f = null;
					f = session.getForme(str[i]);
					if (f instanceof Carre) {
						DAO<Carre> carrDAO = FabriqueDAO.getCarreDAO(dbname);
						carrDAO.update((Carre)f);
					}
					else if (f instanceof Rectangle) {
						DAO<Rectangle> dao = FabriqueDAO.getRectangleDAO(dbname);
						dao.update((Rectangle)f);
						
					}
					else if (f instanceof Cercle) {
						DAO<Cercle> dao = FabriqueDAO.getCercleDAO(dbname);
						dao.update((Cercle)f);
						
					}
					else if (f instanceof Triangle) {
						DAO<Triangle> dao = FabriqueDAO.getTriangleDAO(dbname);
						dao.update((Triangle)f);
						
					}
					else if (f instanceof GroupeForme) {
						DAO<GroupeForme> gpDAO = FabriqueDAO.getGroupeFormeDAO(dbname);
						gpDAO.update((GroupeForme)f);
					}
				}
			}
		}
		DrawingTUI.afficheResultat(DataBase.recapBDD(dbname) + session.toString() );	
	}

}
