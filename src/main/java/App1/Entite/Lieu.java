package App1.Entite;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIEU")
public class Lieu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String ville;
	private String detailsSup;

	@ManyToOne
	@JoinColumn(name = "ID_PAYS")
	private Pays pays;
	
	@OneToMany(mappedBy = "lieuTournage")
	private Set<Film> films = new HashSet<>();
	
	@OneToMany(mappedBy = "lieuNaissance")
	private Set<Acteur> acteurs = new HashSet<>();

	@OneToMany(mappedBy = "lieuNaissance")
	private Set<Realisateur> realisateurs = new HashSet<>();
	/**
	 * Constructor
	 * 
	 * @param ville
	 * @param pays
	 */
	public Lieu(String ville, Pays pays) {
		this.ville = ville;
		this.pays = pays;
	}

	/**
	 * Constructor
	 * 
	 */
	public Lieu() {
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
	 * Getter pour ville
	 * 
	 * @return ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Setter pour ville
	 * 
	 * @param ville ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Getter pour detailsSup
	 * 
	 * @return detailsSup
	 */
	public String getDetailsSup() {
		return detailsSup;
	}

	/**
	 * Setter pour les détails supplémentaires de l'adresse
	 * 
	 * @param detailsSup detailsSup
	 */
	public void setDetailsSup(String detailsSup) {
		this.detailsSup = detailsSup;
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

}
