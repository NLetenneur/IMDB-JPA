package App1.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import App1.Entite.Acteur;
import App1.Entite.Film;
import App1.Exception.DataMissingException;
import App1.Util.Convertion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ActeurDAO {

	public static void setActeursFromList(List<String> listeActeurs, EntityManager em) {
		Iterator<String> iterator = listeActeurs.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			List<Acteur> acteurs = extractingActeurs(em);
			// verifier que l'acteur d'existe pas encore dans la base
			if (!isActeurInDB(acteurs, tab[1])) {
				Acteur acteur = new Acteur(tab[1]);
				acteur.setIdImdb(tab[0]);
				if ((tab[2] != "") && (tab[2] != " ") && (tab[2].length() >= 11)) {
					acteur.setDateNaissance(Convertion.convertirStringEnDateUSA(tab[2]));
				}
				acteur.setLieuNaissance(LieuDAO.ajouterLieuPersonnes(tab[3], em));
				if (tab[4] != "" && tab[4] != null) {
					acteur.setTaille(Convertion.cleaningTaille(tab[4]));
				}
				acteur.setURL(tab[5]);
				em.persist(acteur);
			}
		}

	}

	private static boolean isActeurInDB(List<Acteur> acteurs, String string) {
		for (Acteur item : acteurs) {
			if (item.getIdentite().equals(string)) {
				return true;
			}
		}
		return false;
	}

	static List<Acteur> extractingActeurs(EntityManager em) {
		TypedQuery<Acteur> query = em.createQuery("SELECT a From Acteur a", Acteur.class);
		List<Acteur> liste = query.getResultList();
		return liste;

	}

	public static boolean isActeurInDBByImdbId(List<Acteur> acteurs, String string) {
		for (Acteur item : acteurs) {
			if (item.getIdImdb().equals(string)) {
				return true;
			}
		}
		return false;
	}

	public static Acteur getActeurByImdbId(List<Acteur> acteurs, String string) {
		Acteur acteur = new Acteur();
		for (Acteur item : acteurs) {
			if (item.getIdImdb().equals(string)) {
				acteur = item;
			}
		}
		return acteur;

	}

	public static Acteur getActeurByIMDB(String string, EntityManager em) throws DataMissingException {
		TypedQuery<Acteur> query = em.createQuery("SELECT a From Acteur a", Acteur.class);
		List<Acteur> liste = query.getResultList();
		Acteur acteur = new Acteur();
		for (Acteur item : liste) {
			if (item.getIdImdb().equals(string)) {
				acteur = item;
				em.persist(acteur);
			}
		}
		if (acteur.getId() == null) {
			throw new DataMissingException("L'acteur n'existe pas");
		}
		return acteur;
	}

	public static void getFilmsByIdentite(String choix, EntityManager em) throws DataMissingException {
		Acteur acteur = getActeurByName(choix, em);
		System.out.println(acteur.getFilms());

	}

	private static Acteur chooseAnActeurInList(EntityManager em, List<Acteur> liste) {
		System.out.println(
				"Plusieurs acteurs et/ou actrices correspondent à votre demande, saisissez l'ID de la personne en question");
		for (Acteur item : liste) {
			System.out.println(item.getId() + " - " + item.getIdentite());

		}
		Scanner scanner = new Scanner(System.in);
		long choix2 = scanner.nextLong();
		return getActeurById(choix2, em);
	}

	private static Acteur getActeurById(long iD, EntityManager em) {
		TypedQuery<Acteur> query = em.createQuery("SELECT a From Acteur a WHERE a.Id = :id", Acteur.class);
		query.setParameter("id", iD);
		Acteur acteur = query.getSingleResult();
		return acteur;
	}

	public static Acteur getActeurByName(String choix, EntityManager em) throws DataMissingException {
		TypedQuery<Acteur> query = em.createQuery("SELECT a From Acteur a WHERE a.identite LIKE :choix", Acteur.class);
		query.setParameter("choix", "%" + choix + "%");
		List<Acteur> liste = query.getResultList();
		Acteur acteur = null;
		if (liste.size() > 1) {
			acteur = chooseAnActeurInList(em, liste);
		} else if (liste.size() == 1) {
			acteur = liste.get(0);
		} else {
			throw new DataMissingException("L'acteur n'existe pas");
		}
		return acteur;
	}

	public static void getActeursBetween2Films(String choix1, String choix2, EntityManager em) throws DataMissingException {
		Film film1 = FilmDAO.getFilmsByNom(choix1, em);
		Film film2 = FilmDAO.getFilmsByNom(choix2, em);
		TypedQuery<Acteur> query = em.createQuery("SELECT a From Acteur a WHERE :film1 MEMBER OF a.films AND :film2 MEMBER OF a.film", Acteur.class);
		query.setParameter("film1", film1);
		query.setParameter("film2", film2);
		List<Acteur> liste = query.getResultList();
		for (Acteur item : liste) {
			System.out.println(item.getId() + " - " + item.getIdentite());

		}
		if(liste.size()==0) {
			throw new DataMissingException("Il n'y a pas d'acteurs en communs entre ces films"); 
		}
					
	}

}