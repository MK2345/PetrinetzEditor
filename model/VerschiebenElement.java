package model;

import java.io.Serializable;







/**
 * Diese Klasse wird verwendet, wenn mehrere Netzelemente parallel verschoben werden,
 * sie stellt dabei ein Listenelement der LinkedList VerschElem_Liste vom Model dar.
 * Ein Objekt der Klasse VerschiebenElement enthält alle relevanten Daten eines 
 * zu verschiebenden Netzelementes.
 * Zu diesen Daten gehören u.a. deren Abstand zu dem Netzelement, von welchem der
 * Verschiebevorgang gestartet wird.
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */


public class VerschiebenElement implements Serializable{
	
	/**
	 * Position des Elements im Stellen- oder Trans_Array vom Model
	 */
	public int Pos;
	
	/**
	 * Sorte des Elements, 1 für Transition, 2 für Stelle
	 */
	public int Sorte;
	
	/**
	 * Abstand x-Koordinate zu x-Koordinate des Elements von welchem 
	 * die Verschiebung aus erfolgt. 
	 */
	public int AbstandX;
	
	/**
	 * Abstand y-Koordinate zu y-Koordinate des Elements von welchem 
	 * die Verschiebung aus erfolgt. 
	 */
	public int AbstandY;
	
	
	/**
	 * Konstruktor von VerschiebenElemente
	 * @param Position Position des Elements welches verschoben werden soll
	 * @param Art  Sorte des Elements, wenn Transition dann 1, wenn Stelle dann 2
	 * @param abX  Abstand zu x-Koordinate des Elements von welchem die Verschiebung aus erfolgt.
	 * @param abY  Abstand zu y-Koordinate des Elements von welchem die Verschiebung aus erfolgt.
	 */	
	public VerschiebenElement(int Position, int Art, int abX, int abY) {
		Pos = Position;
		Sorte = Art;
		AbstandX = abX;
		AbstandY = abY;
	}
}
