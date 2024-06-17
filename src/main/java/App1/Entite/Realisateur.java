package App1.Entite;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "REALISATEUR")
public class Realisateur extends Personne {
	@ManyToMany
	@JoinTable(name = "FILM_REAlISATEUR", joinColumns = { @JoinColumn(name = "id_realisateur") }, inverseJoinColumns = {
			@JoinColumn(name = "id_film") })
	private Set<Film> films = new HashSet<>();

	/** Getter pour films
	 * @return films
	 */
	public Set<Film> getFilms() {
		return films;
	}

	/**Setter pour films
	 * @param films films 
	 */
	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	/**
	 * Constructor
	 * 
	 * @param identite
	 */
	public Realisateur(String identite) {
		super(identite);
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public Realisateur() {
	}


	
}