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

@Entity
@Table(name = "PAYS")
public class Pays {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String nom;
	@OneToMany(mappedBy = "pays")
	private Set<Lieu> lieux = new HashSet<>();
	@OneToMany(mappedBy = "pays")
	private Set<Film> filmSet = new HashSet<>();

	public Pays() {

	}

	public Pays(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nom).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pays)) {
			return false;
		}
		Pays autre = (Pays) obj;
		return new EqualsBuilder().append(nom, autre.getNom()).isEquals();
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter pour lieux
	 * 
	 * @return lieux
	 */
	public Set<Lieu> getLieux() {
		return lieux;
	}

	/**
	 * Setter pour lieux
	 * 
	 * @param lieux lieux
	 */
	public void setLieux(Set<Lieu> lieux) {
		this.lieux = lieux;
	}

	/**
	 * Getter pour filmSet
	 * 
	 * @return filmSet
	 */
	public Set<Film> getFilmSet() {
		return filmSet;
	}

	/**
	 * Setter pour filmSet
	 * 
	 * @param filmSet filmSet
	 */
	public void setFilmSet(Set<Film> filmSet) {
		this.filmSet = filmSet;
	}

}