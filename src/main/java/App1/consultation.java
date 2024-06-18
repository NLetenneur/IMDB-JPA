package App1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import App1.Entite.Menu;
import App1.Util.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *Permet de consulter la base de données à l'aide d'un menu 
 */
public class consultation {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("IMDB");
		EntityManager em = emf.createEntityManager();
		int menuSelection = 0;
		while (menuSelection != 8) {
			List<Menu> menus = Menu.createMenuIMDB();
			for (Menu menu : menus) {
				System.out.println(menu);
			}

			int choix = Service.saisieInt("Saisissez votre choix");
			List<Integer> choixPos = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
			if (choixPos.contains(choix)) {
				menuSelection = choix;
				if (menuSelection != 8) {
					Service.goToSelection(menuSelection, em);
				}
			}
		}
		em.close();
	}

}
