package App1.Entite;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import App1.Exception.DataMissingException;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TypedQuery;

@Entity
@Table(name = "ACTEUR")

public class Acteur extends Personne {

	private double taille;

	@OneToMany(mappedBy = "acteur")
	private Set<Role> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "CASTING", joinColumns = { @JoinColumn(name = "id_acteur") }, inverseJoinColumns = {
			@JoinColumn(name = "id_film") })
	private Set<Film> films = new HashSet<>();

	/**
	 * Constructor
	 * 
	 * @param identite
	 */
	public Acteur(String identite) {
		super(identite);
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public Acteur() {
		super();
	}

	public void addRole(Role role) {
		roles.add(role);

	}

	/**
	 * Getter pour taille
	 * 
	 * @return taille
	 */
	public double getTaille() {
		return taille;
	}

	/**
	 * Setter pour taille
	 * 
	 * @param taille taille
	 */
	public void setTaille(double taille) {
		this.taille = taille;
	}

	/**
	 *Retourne un acteur à partir d'un ID IMDB
	 * @throws DataMissingException 
	 */
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