package App1.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import App1.Entite.Film;
import App1.Entite.Realisateur;
import App1.Util.Convertion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RealisateurDAO {
	public static void setRealisateursFromList(List<String> listeReal, EntityManager em) {
		Iterator<String> iterator = listeReal.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			List<Realisateur> realisateurs = extractingReal(em);
			// verifier que le réalisateur d'existe pas encore dans la base
			if (!isRealInDB(realisateurs, tab[1])) {
				Realisateur real = new Realisateur(tab[1]);
				real.setIdImdb(tab[0]);
				if ((tab[2].split(" ").length == 3)) {
					real.setDateNaissance(Convertion.convertirStringEnDateUSA(tab[2]));
				}
				real.setLieuNaissance(LieuDAO.ajouterLieuPersonnes(tab[3], em));
				real.setURL(tab[5]);
				em.persist(real);

			}

		}

	}

	public static void linkFilmToRealFromList(List<String> listeFilmReal, EntityManager em) {
		Iterator<String> iterator = listeFilmReal.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			List<Realisateur> realisateurs = extractingReal(em);
			List<Film> films = FilmDAO.extractingFilms(em);
			if (isRealInDBByImdbId(realisateurs, tab[1])) {
				Realisateur real = getRealByImdbId(realisateurs, tab[1]);

				if (FilmDAO.isFilmInDBByImdbId(films, tab[0])) {
					Film film = FilmDAO.getFilmByImdbId(films, tab[0]);
					if (!isFilmLinkedToReal(real, film)) {
						Set<Film> listeFilms = real.getFilms();
						listeFilms.add(film);
						real.setFilms(listeFilms);
					}
				}
				em.persist(real);

			}
		}
	}

	private static boolean isFilmLinkedToReal(Realisateur real, Film film) {
		Set<Film> films = real.getFilms();
		if (films.contains(film)) {
			return true;
		}

		return false;
	}

	static Realisateur getRealByImdbId(List<Realisateur> realisateurs, String string) {
		Realisateur real = new Realisateur();
		for (Realisateur item : realisateurs) {
			if (item.getIdImdb().equals(string)) {
				real = item;
			}
		}
		return real;
	}

	static boolean isRealInDBByImdbId(List<Realisateur> realisateurs, String string) {
		for (Realisateur item : realisateurs) {
			if (item.getIdImdb().equals(string)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isRealInDB(List<Realisateur> realisateurs, String string) {
		for (Realisateur item : realisateurs) {
			if (item.getIdentite().equals(string)) {
				return true;
			}
		}
		return false;
	}

	static List<Realisateur> extractingReal(EntityManager em) {
		TypedQuery<Realisateur> query = em.createQuery("SELECT r From Realisateur r", Realisateur.class);
		List<Realisateur> liste = query.getResultList();
		return liste;
	}

}
