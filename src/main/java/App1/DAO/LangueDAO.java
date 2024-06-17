package App1.DAO;

import java.util.List;

import App1.Entite.Langue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class LangueDAO {

	public static Langue ajouterLangue(String string, EntityManager em) {
		List<Langue> langues = extractingLangues(em);
		Langue langue = new Langue();
		if (!isLangueInDB(langues, string)) {
			
			langue = new Langue(string);
		} else {
			langue = Langue.getLangueByName(langues, string);
		}
		em.persist(langue);
		return langue;
	}

	private static boolean isLangueInDB(List<Langue> langues, String string) {
		for (Langue item : langues) {
			if (item.getNom().equals(string)) {
				return true;
			}
		}
		return false;
	}

	private static List<Langue> extractingLangues(EntityManager em) {
		TypedQuery<Langue> query = em.createQuery("SELECT a From Langue a", Langue.class);
		List<Langue> liste = query.getResultList();
		return liste;
	}

}
