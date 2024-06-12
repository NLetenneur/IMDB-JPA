package App1.Entite;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSONNE")
@MappedSuperclass
abstract class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String identite;
	private LocalDate dateNaissance;
	private String URL;

	/**
	 * Constructor
	 * 
	 * @param identite
	 */
	public Personne(String identite) {
		this.identite = identite;
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public Personne() {
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
	 * Getter pour dateNaissance
	 * 
	 * @return dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Setter pour dateNaissance
	 * 
	 * @param dateNaissance dateNaissance
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
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

}
