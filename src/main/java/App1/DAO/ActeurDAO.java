package App1.DAO;

import java.util.Iterator;
import java.util.List;

import App1.Entite.Acteur;
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
				acteur= item;
			}		
	}return acteur;

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
		if (acteur.getId()==null) {
			throw new DataMissingException("L'acteur n'existe pas");
		}
		return acteur;
	}	

}