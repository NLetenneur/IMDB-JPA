package App1.Entite;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACTEUR")

public class Acteur extends Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_imdb")
	private String idImdb;
	private String identite;
	private String URL;
	private LocalDate dateNaissance;

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
		this.identite = identite;
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public Acteur() {
		super();
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

	public void addRole(Role role) {
		roles.add(role);

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
	 * Getter pour roles
	 * 
	 * @return roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * Setter pour roles
	 * 
	 * @param roles roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

}