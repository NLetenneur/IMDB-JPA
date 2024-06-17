package App1.Entite;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACTEUR")

public class Acteur extends Personne {

	private double taille;

	@OneToMany(mappedBy = "acteur")
	private Set<Role> roles = new HashSet<>();

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
	 * Retourne un acteur à partir d'un ID IMDB
	 * 
	 * @throws DataMissingException
	 */

	/**
	 * Getter pour films
	 * 
	 * @return films
	 */
	public Set<Film> getFilms() {
		return films;
	}

	/**
	 * Setter pour films
	 * 
	 * @param films films
	 */
	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	@ManyToMany
	@JoinTable(name = "CASTING", joinColumns = { @JoinColumn(name = "id_acteur") }, inverseJoinColumns = {
			@JoinColumn(name = "id_film") })
	private Set<Film> films = new HashSet<>();

}