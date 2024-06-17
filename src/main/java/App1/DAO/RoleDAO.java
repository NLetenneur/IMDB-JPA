package App1.DAO;

import java.util.Iterator;
import java.util.List;

import App1.Entite.Acteur;
import App1.Entite.Film;
import App1.Entite.Role;
import App1.Exception.DataMissingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RoleDAO {

	public static void setRolesFromList(List<String> listeRoles, EntityManager em) {
		Iterator<String> iterator = listeRoles.iterator();
		while (iterator.hasNext()) {
			String ligneCourante = iterator.next();
			String[] tab = ligneCourante.split(";", -1);
			List<Role> roles = extractingRoles(em);
			if (!isRoleInDB(roles, tab)) {
				try {
					Film film = Film.getFilmByIMDB(tab[0], em);
					Acteur acteur = ActeurDAO.getActeurByIMDB(tab[1], em);
					Role role = new Role(film, acteur, tab[2]);
					em.persist(role);
				} catch (DataMissingException e) {

				}
			}
		}

	}

	private static boolean isRoleInDB(List<Role> roles, String[] tab) {
		for (Role item : roles) {
			if (item.getActeur().getIdImdb().equals(tab[1]) && (item.getFilm().getIdImdb().equals(tab[0]))
					&& (item.getPersonnage().equals(tab[2]))) {
				return true;
			}
		}
		return false;
	}

	private static List<Role> extractingRoles(EntityManager em) {
		TypedQuery<Role> query = em.createQuery("SELECT a From Role a", Role.class);
		List<Role> liste = query.getResultList();
		return liste;
	}

}
