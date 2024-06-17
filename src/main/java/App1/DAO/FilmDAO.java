package App1.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import App1.Entite.Acteur;
import App1.Entite.Film;
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
				if ((tab[2] != "") && (tab[2] != " ") && (tab[2].length() >= 11)) {
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

}
