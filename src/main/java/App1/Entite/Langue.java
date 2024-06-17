package App1.Entite;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LANGUE")
public class Langue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String nom;
	@OneToMany(mappedBy = "langue")
	private Set<Film> films = new HashSet<>();

	/**
	 * Constructor
	 * 
	 * @param nom
	 */
	public Langue(String nom) {
		this.nom = nom;
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public Langue() {
	}

	/**
	 * Getter pour id
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter pour id
	 * 
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter pour nom
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter pour nom
	 * 
	 * @param nom nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

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

	public static Langue getLangueByName(List<Langue> langues, String string) {
		Langue langue = new Langue();
		for (Langue item : langues) {
			if (item.getNom().equals(string)) {
				langue = item;
			}
		}
		return langue;
	}

}
