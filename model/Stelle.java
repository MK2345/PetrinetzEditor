package model;





import java.awt.Color;
import java.io.Serializable;


/**
 * Beinhaltet alle wichtigen Daten einer Stelle, wie deren Koordinaten, deren Größe,
 * deren Benennung und ihren entsprechenden Zustand(ob bereits als gelöscht,
 * ob als zu Verschieben oder zu löschen gekennzeichnet)
 * @version 30.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */
public class Stelle implements Serializable{
	
	/**
	 * x-Koordinate der Stelle
	 */
	public int stelle_x_kor;
	
	/**
	 * y-Koordinate der Stelle
	 */
	public int stelle_y_kor;
	
	/**
	 * Längendurchmesser der Stelle
	 */
	public int stelle_laenge;
	
	/**
	 * Breitendurchmesser der Stelle
	 */
	public int stelle_breite;
	
	/**
	 * Anzahl der Marken der Stelle
	 */
	public int anzahl_Marken;
	
	/**
	 * Zustand der Stelle, gelb wenn als zu Verschieben markiert,
	 * rot wenn als zu Löschen markiert, ansonsten grau
	 */
	public Color stelle_color;
	
	/**
	 * Benennung der Stelle
	 */
	public String tex;
	
	/**
	 * true, wenn als zu verschieben markiert
	 */
	public boolean versch_kenn;
	
	/**
	 * true, wenn als zu löschen markiert
	 */
	public boolean loesch_kenn;
	
	/**
	 * true, wenn Stelle als gelöscht gilt
	 */
	public boolean geloescht;
	
	
	/**
	 * Konstruktor
	 * 
	 * @param x_kor x-Koordinate der Stelle
	 * @param y_kor y-Koordinate der Stelle
	 * @param laenge Laengendurchmesser der Stelle
	 * @param breite Breitendurchmesser der Stelle
	 * @param anz_mark Anzahl der Marken der Stelle
	 * @param color  Zustand der Stelle, gelb wenn als zu verschieben markiert,
	 * 				  rot wenn als zu löschen markiert, ansonsten grau
	 * @param tex   Benennung der Stelle
	 * @param versch  boolean-Variable, true wenn als zu verschieben markiert
	 * @param loesch  boolean-Variable, true, wenn als zu löschen markiert
	 * @param geloescht  boolean-Variable, true, wenn Stelle als gelöscht gilt
	 */
	public Stelle(int x_kor, int y_kor, int laenge, int breite, int anz_mark, Color color, 
			String tex, boolean versch, boolean loesch,
			boolean geloescht) {
		stelle_x_kor = x_kor;
		stelle_y_kor = y_kor;
		stelle_laenge = laenge;
		stelle_breite = breite;
		anzahl_Marken = anz_mark;
		stelle_color = color;	
		this.tex = tex;
		versch_kenn = versch;
		loesch_kenn = loesch;
		this.geloescht = geloescht;
	}

}
