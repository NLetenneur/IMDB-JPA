package App1.DAO;

import java.util.Iterator;
import java.util.List;

import App1.Entite.Film;
import App1.Util.Convertion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class FilmDAO {

	public static void setFilmsFromList(List<String> listeFilms, EntityManager em) {
		Iterator<String> iterator = listeFilms.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			EntityTransaction transaction = em.getTransaction();
			List<Film> films = extractingFilms(em);
			// verifier que le film d'existe pas encore dans la base
			if (!isFilmInDB(films, tab[1])) {
				transaction.begin();
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
				transaction.commit();
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

	private static List<Film> extractingFilms(EntityManager em) {
		TypedQuery<Film> query = em.createQuery("SELECT a From Film a", Film.class);
		List<Film> liste = query.getResultList();
		return liste;
	}

}
