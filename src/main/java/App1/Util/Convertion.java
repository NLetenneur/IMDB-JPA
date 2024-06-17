package App1.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Convertion {

	public static LocalDate convertirStringEnDateUSA(String string) {
		String dateATrimer = string.trim();
		LocalDate date = LocalDate.parse(dateATrimer, DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.US));
		return date;
	}

	public static double cleaningTaille(String string) {
		String[] tailleDecoupee = string.split(" ");
		double taille = Double.parseDouble(tailleDecoupee[0]);
		return taille;
	}

	public static Integer convertirAnnee(String string) {
		String[] tab = string.split("–");
		return Integer.parseInt(tab[0]);
	}

}