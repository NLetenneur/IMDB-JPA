package App1.Entite;

import java.util.Arrays;
import java.util.List;

public class Menu {
	private int id;
	private String libelle;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param libelle
	 */
	public Menu(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public static List<Menu> createMenuIMDB() {
		List<Menu> menus = null;
		Menu menu1 = new Menu(1, " Afficher la filmographie d'un acteur");
		Menu menu2 = new Menu(2, " Afficher le casting principal d'un film");
		Menu menu3 = new Menu(3, " Afficher la liste des films sorti entre deux années");
		Menu menu4 = new Menu(4, " Afficher les films en commun à deux acteurs ou actrices");
		Menu menu5 = new Menu(5, " Afficher les acteurs ou actrices en commun pour deux films donnés");
		Menu menu6 = new Menu(6, " Afficher les films sorti entre deux années ayant un acteur ou une actrice au casting");
		Menu menu7 = new Menu(7, " Afficher le chemin le plus court entre deux acteurs ou actrices");
		Menu menu8 = new Menu(8, " Fin de l'application");
		menus=Arrays.asList(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8);
		return menus;
	}

	@Override
	public String toString() {
		return + id + "" + libelle;
	}
	
	
}
