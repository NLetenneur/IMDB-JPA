package App1.Entite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="PAYS")
public class Pays {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	private String nom;
	@OneToMany(mappedBy = "pays")
	private Set<Lieu> lieux = new HashSet<>();
	@OneToMany(mappedBy = "pays")
	private Set<Film> films = new HashSet<>();
	
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
		Pays autre = (Pays)obj;
		return new EqualsBuilder().append(nom, autre.getNom()).isEquals();
	}
	
	/** Getter
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/** Setter
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	

}