package App1.Entite;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
abstract class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String idImdb;
	private String identite;
	private LocalDate dateNaissance;
	private String URL;
	
	@ManyToOne
	@JoinColumn(name = "ID_LieuNaiss")
	private Lieu lieuNaissance;

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
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Acteur)) {
			return false;
		}
		Acteur other = (Acteur) obj;
		return new EqualsBuilder().append(idImdb, other.getIdImdb()).isEquals();
	}

	@Override
	public String toString() {
		return "id=" + idImdb + ", identite=" + identite + ", anniversaire=" + dateNaissance;
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

	/** Getter pour idImdb
	 * @return idImdb
	 */
	public String getIdImdb() {
		return idImdb;
	}

	/**Setter pour idImdb
	 * @param idImdb idImdb 
	 */
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	/** Getter pour lieuNaissance
	 * @return lieuNaissance
	 */
	public Lieu getLieuNaissance() {
		return lieuNaissance;
	}

	/**Setter pour lieuNaissance
	 * @param lieuNaissance lieuNaissance 
	 */
	public void setLieuNaissance(Lieu lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	

}
