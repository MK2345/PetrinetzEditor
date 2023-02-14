package model;






import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;



/**
 * Beinhaltet alle wichtigen Daten einer Kante, wie deren Position,
 * deren Zustand und die Indizes der Stelle und Transition in der jeweiligen
 * Liste der Klasse Model.
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */

public class Kante implements Serializable{		
	
	/**
	 * x-Koordinate des Startpunktes der Kante
	 */
	public int xAnfang;
	
	/**
	 * y-Koordinate des Startpunktes der Kante
	 */
	public int yAnfang;
	
	/**
	 * x-Koordinate des Endpunktes der Kante
	 */
	public int xEnde;
	
	/**
	 * y-Koordinate des Endpunktes der Kante
	 */
	public int yEnde;
	
	/**
	 * Farbe der Kante, rot wenn zum Löschen gekennzeichnet, ansonsten grau
	 */
	public Color color;
	
	/**
	 * true, wenn Kante von einer Transition aus geht
	 */
	public boolean von_Trans = false;
	
	/**
	 * true, wenn Kante von einer Stelle aus geht
	 */
	public boolean von_Stelle = false;
	
	/**
	 * true, wenn Kante in einer Transition endet
	 */
	public boolean zu_Trans = false;
	
	/**
	 * true, wenn Kante in einer Stelle endet
	 */
	public boolean zu_Stelle = false;
	
	/**
	 * Index der Transition im Trans_Array der Klasse Model
	 */
	public int index_Trans;
	
	/**
	 * Index der Stelle im Stellen_Array der Klasse Model
	 */
	public int index_Stelle;	
	
	/**
	 * true, wenn Kante zum Löschen markiert ist
	 */
	public boolean loesch_kenn;
	
	
	/**
	 * Konstruktor
	 * 
	 * @param x_Anf  x-Koordinate des Startpunktes der Kante
	 * @param y_Anf  y-Koordinate des Startpunktes der Kante
	 * @param x_End  x-Koordinate des Endpunktes der Kante
	 * @param y_End  y-Koordinate des Endpunktes der Kante
	 * @param color  Farbe der Kante, rot wenn sie zum Löschen gekennzeichnet wurde, ansonsten grau
	 * @param von_t  boolean-Variable, true wenn Kante von einer Transition aus beginnt
	 * @param von_s  boolean-Variable, true wenn Kante von einer Stelle aus beginnt
	 * @param zu_t  boolean-Variable, true wenn Kante in einer Transition endet
	 * @param zu_s  boolean-Variable, true wenn Kante in einer Stelle endet
	 * @param indexTrans  Index der Transition im Trans_Array der Klasse Model
	 * @param indexStelle  Index der Stelle im Stellen_Array der Klasse Model
	 * @param loesch_kenn  boolean-Variable, true wenn Kante zum Löschen gekennzeichnet wurde
	 */
	public Kante(int x_Anf, int y_Anf, int x_End, int y_End, Color color, 
			boolean von_t, boolean von_s, boolean zu_t, boolean zu_s, 
			int indexTrans, int indexStelle, boolean loesch_kenn) {		
	
		xAnfang = x_Anf;
		yAnfang = y_Anf;
		xEnde = x_End;
		yEnde = y_End;
		this.color = color;		
		von_Trans = von_t;
		von_Stelle = von_s;
		zu_Trans = zu_t;
		zu_Stelle = zu_s;		
		index_Trans = indexTrans;
		index_Stelle = indexStelle;
		this.loesch_kenn = loesch_kenn;
	}	
}
