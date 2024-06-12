package App1.Entite;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** nom : String */
	private String personnage;

	@ManyToOne
	@JoinColumn(name = "ID_FILM")
	private Film film;

	@ManyToOne
	@JoinColumn(name = "ID_ACTEUR")
	private Acteur acteur;

	/**
	 * Constructor jpa
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param personnage
	 * @param film
	 * @param acteur
	 */
	public Role(Long id, String personnage, Film film, Acteur acteur) {
		super();
		this.id = id;
		this.personnage = personnage;
		this.film = film;
		this.acteur = acteur;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", personnage=" + personnage + ", film=" + film.getNom() + ", acteur="
				+ acteur.getIdentite() + "]";
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
	 * Getter pour personnage
	 * 
	 * @return personnage
	 */
	public String getPersonnage() {
		return personnage;
	}

	/**
	 * Setter pour personnage
	 * 
	 * @param personnage personnage
	 */
	public void setPersonnage(String personnage) {
		this.personnage = personnage;
	}

	/**
	 * Getter pour film
	 * 
	 * @return film
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * Setter pour film
	 * 
	 * @param film film
	 */
	public void setFilm(Film film) {
		this.film = film;
	}

	/**
	 * Getter pour acteur
	 * 
	 * @return acteur
	 */
	public Acteur getActeur() {
		return acteur;
	}

	/**
	 * Setter pour acteur
	 * 
	 * @param acteur acteur
	 */
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

}