package App1.DAO;

import App1.Entite.Lieu;
import jakarta.persistence.EntityManager;

public class LieuDAO {
	public static Lieu ajouterLieuPersonnes(String string, EntityManager em) {
		String[] tab = string.split(",", -1);
		String ville = tab[0];
		String pays = tab[tab.length - 1];
		PaysDAO.setPaysByName(pays, em);
		Lieu lieu = new Lieu(ville, PaysDAO.getPaysByName(pays, em));
		if (tab.length > 2) {
			String detailsSup = "";
			for (int i = 1; i < tab.length - 1; i++) {
				detailsSup = detailsSup + tab[i];
			}
			lieu.setDetailsSup(detailsSup);
		}

		em.persist(lieu);
		return lieu;
	}

	public static Lieu ajouterLieuFilms(String string, EntityManager em) {
		String[] tab = string.split(",", -1);
		String ville = tab[tab.length - 1];
		String pays = tab[0];
		PaysDAO.setPaysByName(pays, em);
		Lieu lieu = new Lieu(ville, PaysDAO.getPaysByName(pays, em));
		if (tab.length > 2) {
			String detailsSup = "";
			for (int i = 1; i < tab.length - 1; i++) {
				detailsSup = detailsSup + tab[i];
			}
			lieu.setDetailsSup(detailsSup);
		}
		em.persist(lieu);
		return lieu;
	}
}
