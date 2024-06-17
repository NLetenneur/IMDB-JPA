package App1.DAO;

import java.util.Iterator;
import java.util.List;

import App1.Entite.Pays;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PaysDAO {

	public static void setPaysFromList(List<String> listePays, EntityManager em) {
		Iterator<String> iterator = listePays.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			setPaysByName(tab[0].trim(), em);

		}

	}

	public static void setPaysByName(String nom, EntityManager em) {
		TypedQuery<Pays> query = em.createQuery("SELECT p From Pays p WHERE p.nom=:nom", Pays.class);
		query.setParameter("nom", nom);
		List<Pays> resultat = query.getResultList();
		// verifier que le pays d'existe pas encore dans la base
		if (resultat.size() == 0) {
			Pays pays = new Pays(nom.trim());
			em.persist(pays);
		}
	}

	public static Pays getPaysByName(String string, EntityManager em) {
		TypedQuery<Pays> query = em.createQuery("SELECT p From Pays p WHERE p.nom=:nom", Pays.class);
		query.setParameter("nom", string);
		List<Pays> resultat = query.getResultList();
		// verifier que le pays d'existe pas encore dans la base
		if (resultat.size() > 0) {
			Pays pays = resultat.get(0);
			return pays;
		}
		return null;
	}

}
