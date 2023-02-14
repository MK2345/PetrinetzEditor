package model;





import java.awt.Color;
import java.io.Serializable;

import javax.swing.JTextField;

/**
 * Beinhaltet alle wichtigen Daten einer Transition, wie seine Koordinaten, 
 * seine Größe, seine Benennung und seinen entsprechenden Zustand (ob als aktiviert, 
 * als gelöscht, als zum Verschieben oder zum Löschen gekennzeichnet).
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */

public class Transition implements Serializable{
	
	/**
	 * x-Koordinate der Transition
	 */
	public int trans_x_kor;
	
	/**
	 * y-Koordinate der Transition
	 */
	public int trans_y_kor;
	
	/**
	 * Laenge der Transition
	 */
	public int trans_laenge;
	
	/**
	 * Breite der Transition
	 */
	public int trans_breite;
	
	/**
	 * Zustand der Transition, grün wenn aktiviert, grau wenn nicht aktiviert,
	 * gelb wenn zum Verschieben gekennzeichnet, rot wenn zum Löschen gekennzeichnet
	 */
	public Color trans_color;
	
	/**
	 * Benennung der Transition
	 */
	public String text;
	
	/**
	 * true wenn Transition aktiviert ist, d.h. sie wird grün gezeichnet
	 */
	public boolean trans_aktiv;
	
	/**
	 * true, wenn Transition zum Verschieben gekennzeichnet ist, d.h. sie wird gelb gezeichnet
	 */
	public boolean versch_kenn;
	
	/**
	 * true, wenn Transition zum Löschen gekennzeichnet ist, d.h. sie wird rot gezeichnet
	 */
	public boolean loesch_kenn;
	
	/**
	 * true, wenn Transition als gelöscht gilt, d.h. sie wird nicht mehr gezeichnet
	 */
	public boolean geloescht;
	
	
	/**
	 * Konstruktor 
	 * 
	 * @param x_kor x-Koordinate der Transition
	 * @param y_kor y-Koordinate der Transition
	 * @param laenge Laenge der Transition
	 * @param breite Breite der Transition
	 * @param color  Farbe und somit Zustand der Transition, grün für aktiviert, grau für 
	 * 				nicht aktiviert, gelb für zum Verschieben markiert, rot für zum Löschen markiert
	 * @param text  Benennung der Transition
	 * @param aktiv boolean-Variable, true wenn Transition aktiviert ist
	 * @param versch boolean-Variable, true wenn Transition zum Verschieben gekennzeichnet
	 * @param loesch boolean-Variable, true wenn Transition zum Löschen gekennzeichnet
	 * @param geloescht boolean-Variable, true wenn Transition als gelöscht gilt
	 */
	public Transition(int x_kor, int y_kor, int laenge, int breite, Color color,
			String text,	boolean aktiv, boolean versch, boolean loesch,
			boolean geloescht) {
		trans_x_kor = x_kor;
		trans_y_kor = y_kor;
		trans_laenge = laenge;
		trans_breite = breite;
		trans_color = color;
		this.text = text;
		trans_aktiv = aktiv;
		versch_kenn = versch;
		loesch_kenn = loesch;
		this.geloescht = geloescht;
	}
}
