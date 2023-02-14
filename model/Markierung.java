package model;



import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.JOptionPane;

/**
 * Beinhaltet alle wichtigen Daten einer angelegten Markierung.
 * Sie beinhaltet deren Namen, sowie die jeweilige Anzahl der Marken  
 * pro Stelle.
 * 
 * Model generiert Objekte dieser Klasse und speichert diese in ihrer
 * Liste Mark_Liste.
 * 
 * @author Manuela Koller, Matrikelnummer q6900399 
 *
 */

public class Markierung implements Serializable{
	
	/**
	 * Liste in welche jeweils die Anzahl der Marken pro Stelle eingetragen wird
	 */
	public LinkedList<Integer> mark;	
	
	
	/**
	 * Name der Markierung
	 */
	public String name;
	
	
	/**
	 * Konstruktor der Klasse Markierung
	 */
	public Markierung() {
		mark = new LinkedList();
	}	
		
	
	/**
	 * Benennung der anzulegenden Markierung
	 */
	public void setName() {		
		name = "";
		String test = "";
		name = JOptionPane.showInputDialog("Bitte geben Sie der Markierung einen Namen"); 
		
		if(name != null) {
			if(name.length() >= 4) {
				for(int i=0; i < 4; i++) {
					test = test+name.charAt(i);					
				}				
				if(test.equals("init")) {					
					JOptionPane.showMessageDialog(null, "<html>Der Name init ist bereits für die<br>" +
							"Anfangsmarkierung reserviert.<br>" +
							"Bitte geben Sie einen anderen Namen ein.</html>");
					test = "";
					setName();				
				} 
			}
			else if(name.length() == 0) {
				JOptionPane.showMessageDialog(null, "<html>Es wurde kein Name für die Markierung eingegeben!<br>" +
						"Bitte geben Sie einen Namen ein.</html>");
				setName();				
			}
		}		
	}
	
	/**
	 * gibt den Namen der angelegten Markierung zurück
	 * @return Name der angelegten Markierung
	 */
	public String getName() {
		return name;
	}
	

	
	
	/**
	 * Anzahl der Marken einer Stelle wird am Listenende eingefügt. 
	 * 
	 * @param anzahl  Anzahl der Marken der Stelle
	 */
	public void setMarkierung(int anzahl) {
		mark.addLast(anzahl);
	}
	
}
