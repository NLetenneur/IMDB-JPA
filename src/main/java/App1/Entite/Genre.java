package App1.Entite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "GENRE")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String nom;

	public Genre() {

	}

	public Genre(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nom).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Genre)) {
			return false;
		}
		Genre autre = (Genre) obj;
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

	public static Genre getGenreByName(List<Genre> genres, String string) {
		Genre genre = new Genre();
		for (Genre item : genres) {
			if (item.getNom().equals(string)) {
				genre = item;
			}
		}

		return genre;
	}
}