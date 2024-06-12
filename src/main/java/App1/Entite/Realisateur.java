package App1.Entite;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "REALISATEUR")
public class Realisateur extends Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_imdb")
	private String idImdb;
	private String identite;
	private String URL;
	private LocalDate anniversaire;

	/**
	 * Constructor
	 * 
	 * @param identite
	 */
	public Realisateur(String identite) {
		this.identite = identite;
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public Realisateur() {
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
	 * Getter pour idImdb
	 * 
	 * @return idImdb
	 */
	public String getIdImdb() {
		return idImdb;
	}

	/**
	 * Setter pour idImdb
	 * 
	 * @param idImdb idImdb
	 */
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	/**
	 * Getter pour identite
	 * 
	 * @return identite
	 */
	public String getIdentite() {
		return identite;
	}

	/**
	 * Setter pour identite
	 * 
	 * @param identite identite
	 */
	public void setIdentite(String identite) {
		this.identite = identite;
	}

	/**
	 * Getter pour uRL
	 * 
	 * @return uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * Setter pour uRL
	 * 
	 * @param uRL uRL
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}

	/**
	 * Getter pour anniversaire
	 * 
	 * @return anniversaire
	 */
	public LocalDate getAnniversaire() {
		return anniversaire;
	}

	/**
	 * Setter pour anniversaire
	 * 
	 * @param anniversaire anniversaire
	 */
	public void setAnniversaire(LocalDate anniversaire) {
		this.anniversaire = anniversaire;
	}

}