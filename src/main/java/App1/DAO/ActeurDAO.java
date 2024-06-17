package App1.DAO;

import java.util.Iterator;
import java.util.List;

import App1.Entite.Acteur;
import App1.Util.Convertion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ActeurDAO {

	public static void setActeursFromList(List<String> listeActeurs, EntityManager em) {
		Iterator<String> iterator = listeActeurs.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			EntityTransaction transaction = em.getTransaction();
			List<Acteur> acteurs = extractingActeurs(em);
			// verifier que l'acteur d'existe pas encore dans la base
			if (!isActeurInDB(acteurs, tab[1])) {
				transaction.begin();
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
				transaction.commit();

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

	private static List<Acteur> extractingActeurs(EntityManager em) {
		TypedQuery<Acteur> query = em.createQuery("SELECT a From Acteur a", Acteur.class);
		List<Acteur> liste = query.getResultList();
		return liste;

	}

}
