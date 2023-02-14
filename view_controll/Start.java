package view_controll;

import model.Model;

/**
 * Die Klasse Start enthält die Main-Methode zum Starten des Petrinetz-Editors.
 * @author mk
 *
 */

public class Start {
	
	/**
	 * Durch die Main-Methode wird der Petrinetz-Editor gestartet.
	 * @param args
	 */
	public static void main(String[] args) {
		Model model = new Model();		
		NetzSpeichern dateiKlasse = new NetzSpeichern();
		Zeichenflaeche zeichenfl = new Zeichenflaeche(model);
		Oberflaeche oberflaeche = new Oberflaeche(zeichenfl, model, dateiKlasse);
		oberflaeche.setVisible(true);
	}
}
