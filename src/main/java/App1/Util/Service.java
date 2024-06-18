package App1.Util;

import java.util.Scanner;

import App1.DAO.ActeurDAO;
import App1.DAO.FilmDAO;
import App1.Entite.Film;
import App1.Exception.DataMissingException;
import jakarta.persistence.EntityManager;

/**
 * Sert l'application de consultation
 */
public class Service {

	public static void goToSelection(int choix, EntityManager em) {
		switch (choix) {
		case 1:
			afficherFilmo(em);
			break;
		case 2:
			afficherCasting(em);
			break;
		case 3:
			afficherFilmsEntre2Dates(em);
			break;
		case 4:
			afficherFilmsEnCommunDeuxActeurs(em);
			break;
		case 5:
			afficherActeursEnCommunA2Films(em);
			break;
		case 6:
			afficherFilmsEntre2DatesAvec1Acteur(em);
			break;
		case 7:
			afficherCheminCourtEntre2Acteurs(em);
			break;

		}
	}

	private static void afficherCheminCourtEntre2Acteurs(EntityManager em) {
		System.out.println("A venir : trouver le chemin le plus court entre deux acteurs ou actrices en utilisant l'Algorithme A*.");
	}

	private static void afficherActeursEnCommunA2Films(EntityManager em) {
		String choix1 = saisieString("Saisissez le nom d'un film (ex : Le fantôme de Milburn)");
		String choix2 = saisieString("Saisissez le nom d'un film (ex : Ellis Island, les portes de l'espoir)");
		ActeurDAO.getActeursBetween2Films(choix1, choix2, em);

	}

	private static void afficherFilmsEntre2DatesAvec1Acteur(EntityManager em) {
		int choix1 = saisieInt("Saisissez une année");
		int choix2 = saisieInt("Saisissez une année postérieure à la première");
		String choix3 = saisieString("Saisissez le nom d'un acteur ou d'une actrice (ex : Craig Wasson)");

		try {
			FilmDAO.getFilmsBetween2YearsWithAnActeur(choix1, choix2, choix3, em);
		} catch (DataMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void afficherFilmsEnCommunDeuxActeurs(EntityManager em) {
		String choix1 = saisieString("Saisissez le nom d'un acteur ou d'une actrice (ex : Craig Wasson)");
		String choix2 = saisieString("Saisissez le nom d'un acteur ou d'une actrice (ex : Alice Krige)");
		try {
			FilmDAO.getFilmsBetween2Acteurs(choix1, choix2, em);
		} catch (DataMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void afficherFilmsEntre2Dates(EntityManager em) {
		int choix1 = saisieInt("Saisissez une année");
		int choix2 = saisieInt("Saisissez une année postérieure à la première");
		try {
			FilmDAO.getFilmsBetween2Years(choix1, choix2, em);
		} catch (DataMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void afficherCasting(EntityManager em) {
		String choix = saisieString("Saisissez le nom d'un film (ex : Le fantôme de Milburn)");
		try {
			Film film = FilmDAO.getFilmsByNom(choix, em);
			System.out.println(film.getCasting());
		} catch (DataMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void afficherFilmo(EntityManager em) {
		String choix = saisieString("Saisissez le nom d'un acteur ou d'une actrice (ex : Faye Dunaway)");
		try {
			ActeurDAO.getFilmsByIdentite(choix, em);
		} catch (DataMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int saisieInt(String string) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(string);
		int choix = scanner.nextInt();
		return choix;
	}

	public static String saisieString(String string) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(string);
		String choix = scanner.nextLine();
		return choix;
	}

}
