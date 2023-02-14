package model;





import java.awt.Color;
import java.io.Serializable;

import javax.swing.JTextField;

/**
 * Beinhaltet alle wichtigen Daten einer Transition, wie seine Koordinaten, 
 * seine Gr��e, seine Benennung und seinen entsprechenden Zustand (ob als aktiviert, 
 * als gel�scht, als zum Verschieben oder zum L�schen gekennzeichnet).
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
	 * Zustand der Transition, gr�n wenn aktiviert, grau wenn nicht aktiviert,
	 * gelb wenn zum Verschieben gekennzeichnet, rot wenn zum L�schen gekennzeichnet
	 */
	public Color trans_color;
	
	/**
	 * Benennung der Transition
	 */
	public String text;
	
	/**
	 * true wenn Transition aktiviert ist, d.h. sie wird gr�n gezeichnet
	 */
	public boolean trans_aktiv;
	
	/**
	 * true, wenn Transition zum Verschieben gekennzeichnet ist, d.h. sie wird gelb gezeichnet
	 */
	public boolean versch_kenn;
	
	/**
	 * true, wenn Transition zum L�schen gekennzeichnet ist, d.h. sie wird rot gezeichnet
	 */
	public boolean loesch_kenn;
	
	/**
	 * true, wenn Transition als gel�scht gilt, d.h. sie wird nicht mehr gezeichnet
	 */
	public boolean geloescht;
	
	
	/**
	 * Konstruktor 
	 * 
	 * @param x_kor x-Koordinate der Transition
	 * @param y_kor y-Koordinate der Transition
	 * @param laenge Laenge der Transition
	 * @param breite Breite der Transition
	 * @param color  Farbe und somit Zustand der Transition, gr�n f�r aktiviert, grau f�r 
	 * 				nicht aktiviert, gelb f�r zum Verschieben markiert, rot f�r zum L�schen markiert
	 * @param text  Benennung der Transition
	 * @param aktiv boolean-Variable, true wenn Transition aktiviert ist
	 * @param versch boolean-Variable, true wenn Transition zum Verschieben gekennzeichnet
	 * @param loesch boolean-Variable, true wenn Transition zum L�schen gekennzeichnet
	 * @param geloescht boolean-Variable, true wenn Transition als gel�scht gilt
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
