
import DAO.*;
public enum App {
		ENVIRONNEMENT;
		public void run(String[]args) {
		DataBase.create();
		DataBase.reset();
		}
		public static void main(String[]args) {
		ENVIRONNEMENT.run(args);
		}
}

