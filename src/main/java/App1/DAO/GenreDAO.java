package App1.DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import App1.Entite.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class GenreDAO {

	public static Set<Genre> ajouterGenres(String string, EntityManager em) {
		List<Genre> genres = extractingGenres(em);
		Set<Genre> genresSet = new HashSet<>();
		String[] genresAAjouter = string.split(",");
		Genre genre = new Genre();
		for (int i = 0; i < genresAAjouter.length - 1; i++) {
			if (!isGenreInDB(genres, genresAAjouter[i])) {

				genre = new Genre(genresAAjouter[i]);
				genresSet.add(genre);
			} else {
				genre = Genre.getGenreByName(genres, genresAAjouter[i]);
				genresSet.add(genre);
			}
			em.persist(genre);
		}		
		return genresSet;
	}

	private static boolean isGenreInDB(List<Genre> genres, String string) {
		for (Genre item : genres) {
			if (item.getNom().equals(string)) {
				return true;
			}
		}
		return false;
	}

	private static List<Genre> extractingGenres(EntityManager em) {
		TypedQuery<Genre> query = em.createQuery("SELECT a From Genre a", Genre.class);
		List<Genre> liste = query.getResultList();
		return liste;
	}

}
