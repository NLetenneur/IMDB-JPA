package App1.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import App1.Entite.Acteur;
import App1.Entite.Film;
import App1.Exception.DataMissingException;
import App1.Util.Convertion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class FilmDAO {

	public static void setFilmsFromList(List<String> listeFilms, EntityManager em) {
		Iterator<String> iterator = listeFilms.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			List<Film> films = extractingFilms(em);
			// verifier que le film d'existe pas encore dans la base
			if (!isFilmInDB(films, tab[1])) {
				Film film = new Film(tab[1]);
				film.setIdImdb(tab[0]);
				if ((tab[2] != "") && (tab[2] != " ")) {
					film.setAnnee(Convertion.convertirAnnee(tab[2]));
				}
				if ((tab[3] != "") && (tab[3] != " ") && (tab[3].length() >= 11)) {
					film.setRating(Double.parseDouble(tab[3]));
				}
				film.setURL(tab[4]);
				film.setLieuTournage(LieuDAO.ajouterLieuFilms(tab[5], em));
				film.setGenres(GenreDAO.ajouterGenres(tab[6], em));
				film.setLangue(LangueDAO.ajouterLangue(tab[7], em));
				film.setResume(tab[8]);
				PaysDAO.setPaysByName(tab[9].trim(), em);
				film.setPays(PaysDAO.getPaysByName(tab[9].trim(), em));
				em.persist(film);
			}
		}
	}

	public static boolean isFilmInDB(List<Film> films, String string) {
		for (Film item : films) {
			if (item.getNom().equals(string)) {
				return true;
			}
		}
		return false;
	}

	static List<Film> extractingFilms(EntityManager em) {
		TypedQuery<Film> query = em.createQuery("SELECT a From Film a", Film.class);
		List<Film> liste = query.getResultList();
		return liste;
	}

	public static boolean isFilmInDBByImdbId(List<Film> films, String string) {
		for (Film item : films) {
			if (item.getIdImdb().equals(string)) {
				return true;
			}
		}
		return false;
	}

	public static Film getFilmByImdbId(List<Film> films, String string) {
		Film film = new Film();
		for (Film item : films) {
			if (item.getIdImdb().equals(string)) {
				film = item;
			}
		}
		return film;
	}

	public static void linkFilmToActeurFromList(List<String> listeCasting, EntityManager em) {
		Iterator<String> iterator = listeCasting.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			List<Acteur> acteurs = ActeurDAO.extractingActeurs(em);
			List<Film> films = extractingFilms(em);
			if (isFilmInDBByImdbId(films, tab[0])) {
				Film film = getFilmByImdbId(films, tab[0]);
				if (ActeurDAO.isActeurInDBByImdbId(acteurs, tab[1])) {
					Acteur acteur = ActeurDAO.getActeurByImdbId(acteurs, tab[1]);
					if (!isFilmLinkedToActeur(acteur, film)) {
						Set<Acteur> casting = film.getCasting();
						casting.add(acteur);
						film.setCasting(casting);
					}
				}
				em.persist(film);

			}
		}
	}

	private static boolean isFilmLinkedToActeur(Acteur acteur, Film film) {
		Set<Film> films = acteur.getFilms();
		if (films.contains(film)) {
			return true;
		}

		return false;
	}

	public static Film getFilmsByNom(String choix, EntityManager em) throws DataMissingException {
		TypedQuery<Film> query = em.createQuery("SELECT f From Film f WHERE f.nom LIKE :choix", Film.class);
		query.setParameter("choix", "%" + choix + "%");
		List<Film> liste = query.getResultList();
		Film film = null;
		if (liste.size() > 1) {
			film = chooseAFilmInList(em, liste);
		} else if (liste.size() == 1) {
			film = liste.get(0);
		} else {
			throw new DataMissingException("Le film n'existe pas");
		}
		return film;
		
	}

	private static Film chooseAFilmInList(EntityManager em, List<Film> liste) {
		System.out.println(
				"Plusieurs film correspondent à votre demande, saisissez l'ID du film en question");
		for (Film item : liste) {
			System.out.println(item.getId() + " - " + item.getNom());

		}
		Scanner scanner = new Scanner(System.in);
		long choix2 = scanner.nextLong();
		Film film = getFilmById(choix2, em);
		return film;
	}

	private static Film getFilmById(long iD, EntityManager em) {
		TypedQuery<Film> query = em.createQuery("SELECT f From Film f WHERE f.Id = :id", Film.class);
		query.setParameter("id", iD);
		Film film = query.getSingleResult();
		return film;
	}

	public static void getFilmsBetween2Years(int choix1, int choix2, EntityManager em) throws DataMissingException {
		TypedQuery<Film> query = em.createQuery("SELECT f From Film f WHERE f.annee BETWEEN :choix1 AND :choix2", Film.class);
		query.setParameter("choix1", choix1);
		query.setParameter("choix2", choix2);
		List<Film> liste = query.getResultList();
		for (Film item : liste) {
			System.out.println(item.getId() + " - " + item.getNom());

		}
		if(liste.size()==0) {
			throw new DataMissingException("Il n'y a pas de film sortis sur cette période"); 
		}
		
	}

	public static void getFilmsBetween2Acteurs(String choix1, String choix2, EntityManager em) throws DataMissingException {
		Acteur acteur1 = ActeurDAO.getActeurByName(choix1, em);
		Acteur acteur2 = ActeurDAO.getActeurByName(choix2, em);
		TypedQuery<Film> query = em.createQuery("SELECT f From Film f WHERE :acteur1 MEMBER OF f.casting AND :acteur2 MEMBER OF f.casting", Film.class);
		query.setParameter("acteur1", acteur1);
		query.setParameter("acteur2", acteur2);
		List<Film> liste = query.getResultList();
		for (Film item : liste) {
			System.out.println(item.getId() + " - " + item.getNom());

		}
		if(liste.size()==0) {
			throw new DataMissingException("Il n'y a pas de film communs entre ces acteurs ou actrices"); 
		}
				
	}

	public static void getFilmsBetween2YearsWithAnActeur(int choix1, int choix2, String choix3, EntityManager em) throws DataMissingException {
		Acteur acteur = null;
		try {
			acteur = ActeurDAO.getActeurByName(choix3, em);
		} catch (DataMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}TypedQuery<Film> query = em.createQuery("SELECT f From Film f WHERE f.annee BETWEEN :choix1 AND :choix2 AND :acteur MEMBER OF f.casting", Film.class);
		query.setParameter("choix1", choix1);
		query.setParameter("choix2", choix2);
		query.setParameter("acteur", acteur);
		List<Film> liste = query.getResultList();
		for (Film item : liste) {
			System.out.println(item.getId() + " - " + item.getNom());

		}
		if(liste.size()==0) {
			throw new DataMissingException("Il n'y a pas de film sortis sur cette période"); 
		}
		
	}

}
