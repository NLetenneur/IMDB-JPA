package App1.Entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "REALISATEUR")
public class Realisateur extends Personne {

	/**
	 * Constructor
	 * 
	 * @param identite
	 */
	public Realisateur(String identite) {
		super(identite);
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public Realisateur() {
	}

	
}