

public class Report {

	/** Izpise opozorilo, a izvajanje se nadaljuje.
	 * 
	 * @param msg Opozorilo.
	 */
	public static void warning(String msg) {
		System.out.println(":-o " + msg);
	}
	
	/** Izpise opozorilo, ki je vezano na vrstico in stolpec izvorne kode.  Izvajanje se nadaljuje.
	 * 
	 * @param msg Opozorilo.
	 * @param line Vrstica izvorne kode, kjer je razlog za opozorilo.
	 * @param column Stoplec izvorne kode, kjer je razlog za opozorilo.
	 */
	public static void warning(String msg, int line, int column) {
		System.out.println(":-o [" + line + ":" + column + "] " + msg);
	}
	
	/** Izpise obvestilo o napaki.  Program se ustavi in konca s podano izhodno kodo.
	 * 
	 * @param msg Obvestilo o napaki.
	 * @param exitCode Izhodna koda programa.
	 */
	public static void error(String msg, int exitCode) {
		System.out.println(":-( " + msg);
		System.exit(exitCode);
	}

	/** Izpise obvestilo o napaki, ki je vezana na vrstico in stolpec izvorne kode.  Program se ustavi in konca s podano izhodno kodo.
	 *
	 * @param msg Obvestilo o napaki.
	 * @param line Vrstica izvorne kode, kjer je razlog za napako.
	 * @param column Stolpec izvorne kode, kjer je razlog za napako.
	 * @param exitCode Izhodna koda programa.
	 */
	public static void error(String msg, int line, int column, int exitCode) {
		System.out.println(":-( [" + line + ":" + column + "] " + msg);
		System.exit(exitCode);
	}

}
