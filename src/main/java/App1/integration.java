package App1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import App1.DAO.ActeurDAO;
import App1.DAO.FilmDAO;
import App1.DAO.PaysDAO;
import App1.DAO.RealisateurDAO;
import App1.DAO.RoleDAO;
import App1.Reader.Reader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class integration {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("IMDB");
		EntityManager em = emf.createEntityManager();
		Path home = Paths.get(
				"C:\\Users\\nlete\\Documents\\Diginamic\\24. Projet JPA\\Projet 4 - Internet Movie DataBase - Difficile 1");
		Path fichierPays = home.resolve("./pays.csv");
		boolean existsPays = Files.exists(fichierPays);
		Path fichierReal = home.resolve("./realisateursMini.csv");
		boolean existsReal = Files.exists(fichierReal);
		Path fichierActeurs = home.resolve("./acteursMini.csv");
		boolean existsActeurs = Files.exists(fichierActeurs);
		Path fichierFilms = home.resolve("./filmsMini.csv");
		boolean existsFilms = Files.exists(fichierFilms);
		Path fichierRoles = home.resolve("./rolesMini.csv");
		boolean existsRoles = Files.exists(fichierRoles);
		Path fichierFilmReal = home.resolve("./film_realisateurs.csv");
		boolean existsFilmReal = Files.exists(fichierFilmReal);
		Path fichierCasting = home.resolve("./castingPrincipal.csv");
		boolean existsCasting = Files.exists(fichierCasting);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		
		if (existsPays) {
			List <String> listePays = Reader.readFile(fichierPays, em);
			PaysDAO.setPaysFromList(listePays, em);
		}

		if (existsReal) {
			List <String> listeReal = Reader.readFile(fichierReal, em);
			RealisateurDAO.setRealisateursFromList(listeReal, em);
		}

		if (existsActeurs) {
			List <String> listeActeurs = Reader.readFile(fichierActeurs, em);
			ActeurDAO.setActeursFromList(listeActeurs, em);
		}

		if (existsFilms) {
			List <String> listeFilms = Reader.readFile(fichierFilms, em);
			FilmDAO.setFilmsFromList(listeFilms, em);
		}

		if (existsRoles) {
			List <String> listeRoles = Reader.readFile(fichierRoles, em);
			RoleDAO.setRolesFromList(listeRoles, em);
		}
		
		if(existsFilmReal) {
			List <String> listeFilmReal = Reader.readFile(fichierFilmReal, em);
			RealisateurDAO.linkFilmToRealFromList(listeFilmReal, em);
		}
		if(existsCasting) {
			List <String> listeCasting = Reader.readFile(fichierCasting, em);
			FilmDAO.linkFilmToActeurFromList(listeCasting, em);
		}
		transaction.commit();
		em.close();
	}

}
