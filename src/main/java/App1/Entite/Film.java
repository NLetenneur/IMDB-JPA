package App1.Entite;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "FILM")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_imdb")
	private String idImdb;

	@Column(name = "imdb_href")
	private String URL;
	private String nom;
	private Integer annee;
	private double rating;
	private String resume;

	@OneToMany(mappedBy = "film")
	private Set<Role> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "FILM_REAlISATEUR", joinColumns = { @JoinColumn(name = "id_film") }, inverseJoinColumns = {
			@JoinColumn(name = "id_realisateur") })
	private Set<Realisateur> realisateurs = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "FILM_GENRE", joinColumns = { @JoinColumn(name = "id_film") }, inverseJoinColumns = {
			@JoinColumn(name = "id_genre") })
	private Set<Genre> genres = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "CASTING", joinColumns = { @JoinColumn(name = "id_film") }, inverseJoinColumns = {
			@JoinColumn(name = "id_acteur") })
	private Set<Acteur> casting = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "ID_PAYS")
	private Pays pays;

	@ManyToOne
	@JoinColumn(name = "ID_LANGUE")
	private Langue langue;

	/**
	 * Constructor
	 * 
	 * @param nom
	 */
	public Film(String nom) {

		this.nom = nom;
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public Film() {
	}

	public boolean acteurExists(Acteur acteur) {
		return roles.stream().anyMatch(r -> r.getActeur().equals(acteur));
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(idImdb).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Film)) {
			return false;
		}
		Film other = (Film) obj;
		return new EqualsBuilder().append(idImdb, other.getIdImdb()).isEquals();
	}

	@Override
	public String toString() {
		return "id=" + idImdb + ", nom=" + nom + ", annee=" + annee;
	}

	public void addRole(Role role) {
		System.out.println("Ajout role : " + role + " pour le film : " + this);
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
	 * Getter pour annee
	 * 
	 * @return annee
	 */
	public Integer getAnnee() {
		return annee;
	}

	/**
	 * Setter pour annee
	 * 
	 * @param annee annee
	 */
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	/**
	 * Getter pour rating
	 * 
	 * @return rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Setter pour rating
	 * 
	 * @param rating rating
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * Getter pour resume
	 * 
	 * @return resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * Setter pour resume
	 * 
	 * @param resume resume
	 */
	public void setResume(String resume) {
		this.resume = resume;
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
	 * Getter pour realisateurs
	 * 
	 * @return realisateurs
	 */
	public Set<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	/**
	 * Setter pour realisateurs
	 * 
	 * @param realisateurs realisateurs
	 */
	public void setRealisateurs(Set<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	/**
	 * Getter pour genres
	 * 
	 * @return genres
	 */
	public Set<Genre> getGenres() {
		return genres;
	}

	/**
	 * Setter pour genres
	 * 
	 * @param genres genres
	 */
	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * Getter pour casting
	 * 
	 * @return casting
	 */
	public Set<Acteur> getCasting() {
		return casting;
	}

	/**
	 * Setter pour casting
	 * 
	 * @param casting casting
	 */
	public void setCasting(Set<Acteur> casting) {
		this.casting = casting;
	}

	/**
	 * Getter pour pays
	 * 
	 * @return pays
	 */
	public Pays getPays() {
		return pays;
	}

	/**
	 * Setter pour pays
	 * 
	 * @param pays pays
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	/**
	 * Getter pour langue
	 * 
	 * @return langue
	 */
	public Langue getLangue() {
		return langue;
	}

	/**
	 * Setter pour langue
	 * 
	 * @param langue langue
	 */
	public void setLangue(Langue langue) {
		this.langue = langue;
	}

}