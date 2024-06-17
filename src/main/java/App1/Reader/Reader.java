package App1.Reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import jakarta.persistence.EntityManager;

/**
 * Transforme un fichier .CSV en liste de String
 */
public class Reader {
	public static List<String> readFile(Path fichier, EntityManager em) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(fichier, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lines.remove(0);
		return lines;

	}

}
