package model;





import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;



/**
 * Klasse Model enth�lt alle wichtigen Daten zum aktuellen Petrinetz.
 * Sie liefert alle Informationen zu den Stellen, Transitionen, Kanten,
 * Markierungen, der Gr��e der Elemente, der Gr��e der Zeichenfl�che.
 * Dar�berhinaus bietet sie Methoden zum Festlegen der Gr��e der Zeichenfl�che,
 * zum Anlegen von Markierungen, �ndern der Gr��e der Elemente und zur Berechnung
 * der Koordinaten der Kanten.
 * Au�erdem gibt sie Auskunft dar�ber, welche Funktion durch die jeweiligen Buttons
 * momentan aktiviert ist, wie z.B. das Erzeugen von Transitionen, Stellen, Kanten 
 * oder das Setzen von Marken etc. 
 * 
 * Diese Daten werden von der Oberfl�che, der Zeichenfl�che und beim Speichern
 * eines Netzes abgefragt.
 * 
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */

public class Model implements Serializable{	  	
	
	/** 
	 * Referenz zur Klasse Markierung
	 * */
	public Markierung mark;	
	
	/**
	 * Liste aller angelegten Markierungen zum aktuellen Petrinetz
	 * */
	public LinkedList<Markierung> Mark_Liste;	
	
	/** 
	 * beinhaltet alle Transitionen des aktuellen Netzes
	 */
	public LinkedList<Transition> Trans_Liste;
	
	/**
	 * beinhaltet alle Stellen des aktuellen Netzes
	 */
	public LinkedList<Stelle> Stellen_Liste;	
	
	/**
	 * beinhaltet alle Kanten des aktuellen Netzes
	 */
	public LinkedList<Kante> Kanten_Liste;	
		
	/**
	 * beinhaltet relevante Daten �ber Transitionen und Stellen welche parallel verschoben werden
	 */	
	public LinkedList<VerschiebenElement> VerschElem_Liste;
	
	/**
	 * Breite der Zeichenfl�che
	 */
	public int editorWidth = 600;
	
	/**
	 * H�he der Zeichenfl�che
	 */
	public int editorHeight = 600;
	
	/**
	 * Breite der Transitionen und Durchmesser Stellen
	 */	
	public int groesseWidth = 40;
	
	/**
	 * H�he der Transitionen und Durchmesser Stellen
	 */
	public int groesseHeight = 40;
	
	/**
	 * Breitendurchmesser der Marken, wenn nur ein dicker Punkt
	 */	
	public int groeMarkeWidth = 14;
	
	/**
	 * H�hendurchmesser der Marken, wenn nur ein dicker Punkt
	 */
	public int groeMarkeHeight = 14;
	
	/**
	 * Gr��e der Marken, wenn als Zahl
	 */	
	public int groeMarkeZahl = 20;	
	
	/**
	 * x-Koordinate des Kantenursprungs der Kante, welche gerade durch die Methode
	 * berechneKante berechnet wird
	 */
	public int xan;
	
	/**
	 * y-Koordinate des Kantenursprungs der Kante, welche gerade durch die Methode
	 * berechneKante berechnet wird
	 */
	public int yan;
	
	/**
	 * x-Koordinate des Kantenendes der Kante, welche gerade durch die Methode
	 * berechneKante berechnet wird
	 */
	public int xend;
	
	/**
	 * y-Koordinate des Kantenendes der Kante, welche gerade durch die Methode
	 * berechneKante berechnet wird
	 */
	public int yend;	
	
		
	/**
	 * x-Koordinate des 2. Punktes der Spitze der Kante, welche gerade durch die
	 * Methode berechneKante berechnet wird
	 */
	public int x2sp;
	
	/**
	 * y-Koordinate des 2. Punktes der Spitze der Kante, welche gerade durch die
	 * Methode berechneKante berechnet wird
	 */
	public int y2sp;	
	
	/**
	 * x-Koordinate des 3. Punktes der Spitze der Kante, welche gerade durch die
	 * Methode berechneKante berechnet wird
	 */
	public int x3sp;
	
	/**
	 * y-Koordinate des 3. Punktes der Spitze der Kante, welche gerade durch die
	 * Methode berechneKante berechnet wird
	 */
	public int y3sp;
	
	/**
	 * x-Koordinate des 1. Punktes der Spitze der Kante, welche gerade durch die
	 * Methode berechneKante berechnet wird
	 */
	public int x1sp;
	
	/**
	 * y-Koordinate des 1. Punktes der Spitze der Kante, welche gerade durch die
	 * Methode berechneKante berechnet wird
	 */
	public int y1sp;
	
	/**
	 * true, wenn einzelne Elemente verschoben werden k�nnen
	 */
    boolean verschieben = false;    
    
    /**
     * true, wenn mehrere Elemente zum parallen Verschieben gekennzeichnet werden k�nnen
     */
    boolean mehrVerschieben = false;
    
    /**
     * true, wenn mehrere zuvor gekennzeichnete Elemente verschoben werden k�nnen
     */
    boolean verschKennzeichnen = false;
    
    /**
     * true, wenn neue Positionen der verschobenen Elemente fixiert werden k�nnen
     */
    boolean fixiereNetz = false;
    
    /**
     * true, wenn zum L�schen gekennzeichnete Elemente gel�scht werden k�nnen
     */
    boolean loeschKennzeichnen = false;    
    
    /**
     * true, wenn Transitionen erzeugt werden k�nnen
     */
    boolean trans = false;
    
    /**
     * true, wenn Stellen erzeugt werden k�nnen
     */
    boolean stelle = false;
    
    /**
     * true, wenn Markenanzahl von Stellen um 1 erh�ht werden kann
     */
    boolean marke = false;
    
    /**
     * true, wenn Anzahl der Marken von Stellen ge�ndert werden kann
     */
    boolean anzahlMarken = false; 
    
    /**
     * true, wenn Kanten eingef�gt werden k�nnen
     */
    boolean kante = false;   
    
    /**
     * true, wenn Netzelemente einzeln gel�scht werden k�nnen
     */
    boolean loeschen = false;
    
    /**
     * true, wenn Netzelemente benannt werden k�nnen
     */
    boolean namen = false;    
    
    /**
     * true, wenn Netzelement aktuell vergr��ert dargestellt werden 
     */
    public boolean gross = false;
    
    /**
     * true, wenn Netzelemente aktuell in normaler Gr��e dargestellt werden
     */
    public boolean normal = true;
    
    /**
     * true, wenn Netzelemente aktuell verkleinert dargestellt werden
     */
    public boolean klein = false;
    
    
   	/**
   	 * true, wenn Markierung erfolgreich angelegt wurde. Wird von Methode
   	 * markAnlegen() verwendet.  
   	 */
    public boolean markAnlage = false;
    
    
    /**
     * true, wenn Anfangsmarkierung vorhanden. Wird von Methode anfMarkSetzen()
     * verwendet.
     */
    public boolean anfMarkVorh = false;
   
	
	/**
	 * Konstruktor der Klasse Model
	 */
	public Model() {
		Trans_Liste = new LinkedList();
		Stellen_Liste = new LinkedList();	    
	    Kanten_Liste = new LinkedList();	    
	    VerschElem_Liste = new LinkedList(); 
	    Mark_Liste = new LinkedList();
	    
	    //Anfangsmarkierung setzen
	    mark = new Markierung();
	    mark.name = "init Markierung";
	    Mark_Liste.addLast(mark);
	}
	
	

	/**
	 * ver�ndert die Breite der Zeichenflaeche
	 * 
	 * @param width  Breite der Zeichenfl�che
	 */
	public void setEditorWidth(int width) {
		editorWidth = width;
	}
	
	
	/**
	 * ver�ndert die H�he der Zeichenflaeche
	 * 
	 * @param height   H�he der Zeichenfl�che
	 */
	public void setEditorHeight(int height) {
		editorHeight = height;
	}
	
	
	/**
	 * liefert die Breite der Zeichenflaeche
	 * 
	 * @return  Breite der Zeichenflaeche
	 */
	public int getEditorWidth() {
		return editorWidth;
	}
	
	
	/**
	 * liefert die Hoehe der Zeichenflaeche
	 * 
	 * @return  Hoehe der Zeichenflaeche
	 */
	public int getEditorHeight() {
		return editorHeight;
	}
	
	
	/**
	 * Legt Markierung vom aktuellen Netz an.
	 * Diese Markierung wird zusammen mit den anderen angelegten Markierungen
	 * zum aktuellen Netz in der Liste Mark_Liste gespeichert.
	 * Wird von der Oberfl�che beim Anlegen einer Markierung verwendet.
	 */
	public void markAnlegen() {		
		mark = new Markierung();
		mark.setName();
		if(mark.getName() != null) {
			for(int i=0; i < Stellen_Liste.size(); i++) {
				mark.setMarkierung(Stellen_Liste.get(i).anzahl_Marken);
			}
			Mark_Liste.addLast(mark);
			markAnlage = true;
		} 
		else {
			markAnlage = false;
		}
	}
	
	/**
	 * Macht die aktuelle Markierung im Netz zur Anfangsmarkierung.
	 * Wird von der Oberflaeche beim Anlegen der Anfangsmarkierung verwendet.
	 * 
	 */
	public void anfMarkSetzen() {			
		for(int i= 0; i < Mark_Liste.size(); i++) {
			String name1 = Mark_Liste.get(i).name;
			String name2 = "";		
			
			//momentane Anfangsmarkierung suchen
			if(name1.length() >=4) {
				for(int j = 0; j < 4; j++) {
					name2 = name2 + name1.charAt(j);					
				}				
				if(name2.equals("init")) {
					anfMarkVorh = true;
					Mark_Liste.get(i).mark.clear();
					for(int j=0; j < Stellen_Liste.size(); j++) {
						Mark_Liste.get(i).setMarkierung(Stellen_Liste.get(j).anzahl_Marken);						
					}
				} 
			}			
		}
		//wenn keine Anfangsmarkierung vorhanden, so wird diese erzeugt
		if(!anfMarkVorh) {			
			 mark = new Markierung();
			 mark.name = "init Markierung";
			 Mark_Liste.addFirst(mark);
			 for(int i=0; i < Stellen_Liste.size(); i++) {
				 Mark_Liste.get(0).setMarkierung(Stellen_Liste.get(i).anzahl_Marken);
			 }			 
		}		
	}	
	
	
	/**
	 * �ndert die Gr��e einer Transition.
	 * Wird von den Methoden groesseNormal(), groesseVerdoppeln() und
	 * groesseHalbieren() verwendet.
	 * @param i  Index der Transition im Trans_Array
	 * @param x  x-Koordinate der Transition
	 * @param y  y-Koordinate der Transition
	 */
	public void groesseTrans(int i, int x, int y) {
		Trans_Liste.set(i, new Transition(
				x, y, groesseHeight, groesseWidth,
				Trans_Liste.get(i).trans_color,
				Trans_Liste.get(i).text,
				Trans_Liste.get(i).trans_aktiv,
				Trans_Liste.get(i).versch_kenn,
				Trans_Liste.get(i).loesch_kenn,
				Trans_Liste.get(i).geloescht));	
	}
	
	
	/**
	 * �ndert die Gr��e einer Stelle.
	 * Wird von den Methoden groesseNormal(), groesseVerdoppeln() und
	 * groesseHalbieren() verwendet.
	 * @param i  Index der Stelle im Stellen_Array
	 * @param x  x-Koordinate der Stelle
	 * @param y  y-Koordinate der Stelle
	 */
	public void groesseStelle(int i, int x, int y){
		Stellen_Liste.set(i, new Stelle(
				x, y, groesseHeight, groesseWidth,
				Stellen_Liste.get(i).anzahl_Marken,
				Stellen_Liste.get(i).stelle_color,
				Stellen_Liste.get(i).tex,
				Stellen_Liste.get(i).versch_kenn,
				Stellen_Liste.get(i).loesch_kenn,
				Stellen_Liste.get(i).geloescht));
	}
	
	
	/**
	 * �ndert die Gr��en der Stellen, Transitionen, Kantenspitzen und Marken
	 * auf normal.
	 * Wird von der Oberfl�che verwendet.
	 */
	public void groesseNormal() {		
		groesseWidth = 40;
		groesseHeight = 40;
		groeMarkeWidth = 14;
		groeMarkeHeight = 14;		
		groeMarkeZahl = 20;			
		if(normal) {
			return;
		}		
		else if(gross) {
			for(int i=0; i < Trans_Liste.size(); i++) {				
				groesseTrans(i, Trans_Liste.get(i).trans_x_kor + (groesseWidth/2),
						Trans_Liste.get(i).trans_y_kor + (groesseHeight/2));
		
			}			
			for(int i=0; i< Stellen_Liste.size(); i++) {			
				groesseStelle(i, Stellen_Liste.get(i).stelle_x_kor + (groesseWidth/2),
						Stellen_Liste.get(i).stelle_y_kor + (groesseHeight/2));				
			}			
		}
		else if(klein) {
			for(int i=0; i < Trans_Liste.size(); i++) {
				groesseTrans(i, Trans_Liste.get(i).trans_x_kor - (groesseWidth/4),
						Trans_Liste.get(i).trans_y_kor - (groesseHeight/4));						
			}
			for(int i=0; i< Stellen_Liste.size(); i++) {				
				groesseStelle(i, Stellen_Liste.get(i).stelle_x_kor - (groesseWidth/4),
						Stellen_Liste.get(i).stelle_y_kor - (groesseHeight/4));				
			}
		}
		klein = false;
		normal = true;
		gross = false;		
	}
	
	
	/**
	 * Verdoppelt die normale Gr��e der Stellen, Transitionen, Kantenspitzen
	 * und Marken.
	 * Wird von der Oberfl�che verwendet.
	 */
	public void groesseVerdoppeln() {		
		groesseWidth = 80;
		groesseHeight = 80;
		groeMarkeWidth = 28;
		groeMarkeHeight = 28;		
		groeMarkeZahl = 40;	
		if(gross) {
			return;
		}
		else if(normal) {			
			for(int i=0; i < Trans_Liste.size(); i++) {			
				groesseTrans(i, Trans_Liste.get(i).trans_x_kor - (groesseWidth/4),
						Trans_Liste.get(i).trans_y_kor - (groesseHeight/4));						
			}
			
			for(int i=0; i< Stellen_Liste.size(); i++) {				
				groesseStelle(i, Stellen_Liste.get(i).stelle_x_kor - (groesseWidth/4),
						Stellen_Liste.get(i).stelle_y_kor - (groesseHeight/4));
			}			
		}
		else if(klein) {			
			for(int i=0; i < Trans_Liste.size(); i++) {			
				groesseTrans(i, Trans_Liste.get(i).trans_x_kor - 30,
						Trans_Liste.get(i).trans_y_kor - 30);			
			}
			
			for(int i=0; i< Stellen_Liste.size(); i++) {			
				groesseStelle(i, Stellen_Liste.get(i).stelle_x_kor - 30,
						Stellen_Liste.get(i).stelle_y_kor - 30);
			}			
		}		
		klein = false;
		normal = false;
		gross = true;
	}	
	
	
	/**
	 * Halbiert die normale Gr��e der Stellen, Transitionen, Kantenspitzen
	 * und Marken.
	 * Wird von der Oberfl�che verwendet.
	 */
	public void groesseHalbieren() {
		groesseWidth = 20;
		groesseHeight = 20;
		groeMarkeWidth = 7;
		groeMarkeHeight = 7;		
		groeMarkeZahl = 10;	
		
		if(klein) {
			return;
		}
		else if(normal) {
			for(int i=0; i < Trans_Liste.size(); i++) {		
				groesseTrans(i, Trans_Liste.get(i).trans_x_kor + 10,
						Trans_Liste.get(i).trans_y_kor + 10);			
			}
			for(int i=0; i< Stellen_Liste.size(); i++) {								
				groesseStelle(i, Stellen_Liste.get(i).stelle_x_kor + 10,
						Stellen_Liste.get(i).stelle_y_kor + 10);
			}
		}
		else if(gross) {
			for(int i=0; i < Trans_Liste.size(); i++) {			
				groesseTrans(i, Trans_Liste.get(i).trans_x_kor + 30,
						Trans_Liste.get(i).trans_y_kor + 30);		
			}
			for(int i=0; i< Stellen_Liste.size(); i++) {							
				groesseStelle(i, Stellen_Liste.get(i).stelle_x_kor + 30,
						Stellen_Liste.get(i).stelle_y_kor + 30);
			}
		}
		klein = true;
		normal = false;
		gross = false;		
	}
	
	
	
	/**
	 * Berechnet Koordinaten einer Kante aus deren Anfangs- und Endpunkt.
	 * Wird von Methode paintComponent der Zeichenfl�che zum Zeichnen
	 * von Kanten verwendet.
	 *   
	 * @param xAnfang  x-Koordinate von welcher die Kante beginnt
	 * @param yAnfang  y-Koordinate von welcher die Kante beginnt
	 * @param xEnde  x-Koordinate an welcher die Kante endet
	 * @param yEnde  y-Koordinate an welcher die Kante endet
	 * @param kante  Kante deren Daten f�r die Berechnung abgefragt werden
	 */
	public void berechneKante(int xAnfang,int yAnfang, int xEnde, int yEnde, Kante kante) {
		//Anfangs- und Endpunkt
		xan = xAnfang;
		yan = yAnfang;		
		x1sp = xEnde;
		y1sp = yEnde;			
		
		//Berechnung der Steigung
		double steigungX = x1sp - xan;
		double steigungY = y1sp - yan;		
		//Berechnung der Ausrichtung
		double direktionX = steigungX / (Math.sqrt((steigungX*steigungX) + (steigungY*steigungY)));
		double direktionY = steigungY / (Math.sqrt((steigungX*steigungX) + (steigungY*steigungY)));
				
		//Koordinaten des Kantenursprungs
		xan = xan + (int)((groesseWidth/2)*direktionX);
		yan = yan + (int) ((groesseHeight/2)*direktionY);
		
		//Endpunkt der Kante mu� hinter dem 1. Punkt der Spitze liegen		
		xend = xEnde - (int)(((groesseWidth*9)/10)*direktionX);
		yend = yEnde - (int)(((groesseHeight*9)/10)*direktionY);	
		
		//Koordinaten des 1.Punktes der Kantenspitze
		x1sp = x1sp - (int)((groesseWidth/2)*direktionX);
		y1sp = y1sp - (int)((groesseHeight/2)*direktionY);			
		
		//verhindern, dass die Kantenspitze sich innerhalb einer Transition befindet
		if(kante.zu_Trans){			
			Rectangle rec = new Rectangle(Trans_Liste.get(kante.index_Trans).trans_x_kor,
					Trans_Liste.get(kante.index_Trans).trans_y_kor, 
					Trans_Liste.get(kante.index_Trans).trans_breite,
					Trans_Liste.get(kante.index_Trans).trans_laenge);
			
			if(rec.contains(x1sp, y1sp)) {				
				for(int i=0; i < 100; i++) {
					if(rec.contains(x1sp, y1sp)) {
						x1sp = x1sp - (int) ((2*direktionX));
						y1sp = y1sp - (int) ((2*direktionY));
					}					
				}			
			}			
		} 		
		
		//Steigungswinkel
    	double winkelA = Math.atan(steigungY / steigungX);
    	//Steigungswinkel in Grad
    	double winkelB = (winkelA/3.141592) * 180.0;

    	if(steigungX > 0.0 && steigungY > 0.0 || steigungX == 0.0 && steigungY == 0.0 ||
    			steigungX < 0.0 && steigungY <0.0) {
    		winkelB = winkelB + 180.0;
    	}    	
    	if((direktionY < 0.0) || ((direktionY == 0.0) && (direktionX > 0.0)) || 
    			((direktionX == 0.0) && (direktionY > 0.0))) {
    		winkelB = winkelB + 180.0;
    	}  
    	if(winkelB > 360.0) {
    		winkelB = winkelB % 360.0;
    	}
    	if(winkelB < 0.0) {
    		winkelB = winkelB + 360.0;
    	}  
    	
    	//Koordinaten des 2. Punktes der Kantenspitze
    	winkelA = (3.141592654*((winkelB+20)%360.0))/180.0;
    	x2sp = x1sp + (int)((groesseWidth/2)*Math.cos(winkelA));
    	y2sp = y1sp + (int)((groesseHeight/2)*Math.sin(winkelA));
    	
    	//Koordinaten des 3.Punktes der Kantenspitze
    	winkelA = (3.141592654*((winkelB-20)%360.0))/180.0;
    	x3sp = x1sp + (int)((groesseWidth/2)*Math.cos(winkelA));
    	y3sp = y1sp + (int)((groesseHeight/2)*Math.sin(winkelA));    	
	}
	

	
	
	
	
	
	/**
	 * Setzt alle Werte der booleschen Variablen auf false.
	 * Wird verwendet wenn z.B. ein Netz geladen wird oder ein neues Netz 
	 * gezeichnet werden soll. 
	 */
	public void setAnfang() {		
		verschieben = false;
		verschKennzeichnen = false;
		mehrVerschieben = false;
		fixiereNetz = false;
	    trans = false;
	    stelle = false;
	    marke = false;
	    anzahlMarken = false;	   
	    kante = false;   	
	    loeschen = false;
	    namen = false;
	    loeschKennzeichnen = false;
	   
	}	
	
	
	/**
	 * Variable verschieben wird auf true gesetzt. Wird verwendet von der
	 * Oberflaeche nach dem Dr�cken des Buttons "ein Element" verwendet, 
	 * wenn jeweils nur ein Element verschoben werden soll.
	 */
	 public void setVerschieben() {
		 setAnfang();	
	    verschieben = true;	    	    	    	
	 }
	 
	 
	 /**
	  * Variable verschKennzeichnen wird auf true gesetzt. Methode wird von der
	  * Oberflaeche nach dem Dr�cken des Buttons "kennzeichnen" verwendet, um  
	  * mehrere Elemente zum parallelen Verschieben gelb kennzeichnen zu k�nnen.
	  */
	 public void setVerschKennzei() {
		 setAnfang();	
		verschKennzeichnen = true;			
	}
	 
	 /**
	  * Variable mehrVerschieben wird auf true gesetzt. Methode wird von der
	  * Oberflaeche nach dem Dr�cken des Buttons "verschieben" verwendet, damit die 
	  * zuvor gekennzeichneten Elemente parallel verschoben werden k�nnen.
	  */
	 public void setMehrVerschieben() {
		 setAnfang();			 
		 mehrVerschieben = true;		 
	 }
	 
	 /**
	  * Variable fixierteNetz wird auf true gesetzt. Methode wird von der
	  * Oberflaeche nach dem Dr�cken des Buttons fixieren verwendet, wenn die 
	  * sich die gemeinsam verschobenen Elemente auf ihrer neuen Position befinden.
	  */
	 public void setFixNetz() {
			setAnfang();
			fixiereNetz = true;	   
	}	
	 
	 
	 /**
	  * Variable loeschKennzeichnen wird auf true gesetzt. Methode wird von der 
	  * Oberflaeche nach dem Dr�cken des Buttons kennzeichnen verwendet, um mehrere
	  * Elemente welche gleichzeitig gel�scht werden sollen, zuvor rot kennzeichnen 
	  * zu k�nnen.
	  */
	 public void setLoeschKennzei() {
		 setAnfang();
		 loeschKennzeichnen = true;
	 }	 	 
	 
	    /**
	     * Variable trans wird auf true gesetzt. Methode wird von der Oberflaeche
	     * nach dem Dr�cken des Buttons Transition verwendet, um das Erzeugen von
	     * Transitionen in der Zeichenflaeche zu erm�glichen.
	     */
	    public void setTransition() {
	    	setAnfang();	    	
	    	trans = true;  	    	
	    }
	    
	    /**
	     * Variable Stelle wird auf true gesetzt. Methode wird von der Oberflaeche
	     * nach dem Dr�cken des Buttons Stelle verwendet, um das Erzeugen von
	     * Stellen in der Zeichenflaeche zu erm�glichen.
	     */
	    public void setStelle(){
	    	setAnfang();	
	    	stelle = true;	    	  	
	    }
	    
	    /**
	     * Variable Marke wird auf true gesetzt. Methode wird von der Oberflaeche
	     * nach dem Dr�cken des Buttons Marke verwendet, um das Setzen von einzelnen
	     * Marken auf Stellen zu erm�glichen.
	     */
	    public void setMarke() {
	    	setAnfang();	
	    	marke = true;	    	
	    }
	    
	    /**
	     * Variable anzahlMarken wird auf true gesetzt. Methode wird von der Oberflaeche
	     * nach dem Dr�cken des Buttons Markenanzahl verwendet, um das Setzen von 
	     * jeweils einer bestimmten Anzahl von Marken auf Stellen zu erm�glichen.
	     */
	    public void setAnzahlMarken() {
	    	setAnfang();	
	    	anzahlMarken = true;	    	  	
	    }
	    
	    /**
	     * Variable Kante wird auf true gesetzt. Methode wird von der Oberflaeche
	     * nach dem Dr�cken des Buttons Kante verwendet, um das Ziehen von Kanten zu
	     * erm�glichen.
	     */
	    public void setKante() {
	    	setAnfang();	
	    	kante = true;	    	
	    }
	    
	    /**
	     * Variable loeschen wird auf true gesetzt. Methode wird von der Oberflaeche
	     * nach dem Dr�cken des Buttons ein Element unter L�schen verwendet, um das
	     * L�schen von einzelnen Elementen zu erm�glichen.
	     */
	    public void setLoeschen() {
	    	setAnfang();	
	    	loeschen = true;	    	
	    }
	    
	    /**
	     * Variable namen wird auf true gesetzt. Methode wird von der Oberflaeche nach 
	     * dem Dr�cken des Buttons Name verwendet, um das Benennen von Elementen zu
	     * erm�glichen.
	     */
	    public void setNamen() {
	    	setAnfang();		    	
	    	namen = true;	    	  	
	    }
	    
	    
	   
	    /**
	     * Gibt den Wert der Variablen verschieben zur�ck.
	     * true, wenn einzelne Elemente verschoben werden k�nnen.
	     * Methode wird von der Zeichenflaeche verwendet um festzustellen, ob ein
	     * Element mit der Maus verschoben werden kann.
	     * 
	     * @return Wert der Variable verschieben
	     */
	    public boolean getVerschieben() {	    	
	    	return verschieben;
	    }
	    
	    /**
	     * Gibt den Wert der Variablen verschKennzeichnen zur�ck.
	     * true, wenn Elemente zum anschlie�enden Verschieben gekennzeichnet werden k�nnen.
	     * Methode wird von der Zeichenflaeche verwendet um festzustellen, ob ein angeklicktes
	     * Netzelement gelb gekennzeichnet werden kann.
	     * 
	     * @return Wert der Variable verschKennzeichnen
	     */
	    public boolean getVerschKenn() {
	    	return verschKennzeichnen;
	    }
	    
	    /**
	     * Gibt den Wert der Variable mehrVerschieben zur�ck.
	     * true, wenn mehrere Elemente gleichzeitig verschoben werden k�nnen.
	     * Methode wird von der Zeichenflaeche verwendet um festzustellen, ob ein
	     * von mehreren zum Verschieben gekennzeichnetes Element zum Verschieben
	     * mit der Maus gezogen werden kann.
	     * 
	     * @return Wert der Variable mehrVerschieben
	     */
	    public boolean getMehrVerschieben() {
	    	return mehrVerschieben;
	    }
	    
	    /**
	     * Gibt den Wert der Variablen loeschKennzeichnen zur�ck.
	     * true, wenn Netzelemente zum L�schen gekennzeichnet werden k�nnen.
	     * Methode wird von der Zeichenflaeche verwendet um festzustellen, ob ein
	     * angeklicktes Netzelement rot gekennzeichnet werden kann.
	     * 
	     * @return Wert der Variable loeschKennzeichnen
	     */
	    public boolean getLoeschKenn() {
	    	return loeschKennzeichnen;
	    }  
    
	    
	    /**
	     * Gibt den Wert der Variablen trans zur�ck.
	     * true, wenn Transitionen ins Netz hinzugef�gt werden k�nnen.
	     * Methode wird von der Zeichenflaeche verwendet um festzustellen, ob an
	     * einer angeklickten Position innerhalb der Zeichenflaeche eine Transition
	     * erzeugt werden kann.
	     * 
	     * @return Wert der Variable trans
	     */
	    public boolean getTransition() {
	    	return trans;
	    }
	    
	    /**
	     * Gibt den Wert der Variablen stelle zur�ck.
	     * true, wenn Stellen ins Netz hinzugef�gt werden k�nnen.
	     * Methode wird von der Zeichenflaeche verwendet um festzustellen, ob an
	     * einer angeklicktes Position innerhalb der Zeichenflaeche eine Stelle
	     * erzeugt werden kann.
	     * 
	     * @return Wert der Variable stelle
	     */
	    public boolean getStelle() {
	    	return stelle;
	    }
	    
	    /**
	     * Gibt den Wert der Variablen marke zur�ck.
	     * true, wenn Anzahl der Marken von Stellen um 1 erh�ht werden k�nnen.
	     * Methode wird von der Zeichenflaeche verwendet um festzustellen, ob eine
	     * angeklickte Stelle eine zus�tzliche Marke erhalten kann.
	     * 
	     * @return Wert der Variable marke
	     */
	    public boolean getMarke() {
	    	return marke;
	    }
	    
	    /**
	     * Gibt den Wert der Variblen anzahlMarken zur�ck.
	     * true, wenn Anzahl der Marken von Stellen ge�ndert werden kann.
	     * Methode wird von der Zeichenflaeche verwendet um festzustellen, ob eine
	     * angeklickte Stelle eine bestimmte Anzahl von Marken erhalten kann.
	     * 
	     * @return Wert der Variable anzahlMarken
	     */
	    public boolean getAnzahlMarken() {
	    	return anzahlMarken;
	    }
	    
	    /**
	     * Gibt den Wert der Variablen kante zur�ck.
	     * true, wenn Kanten gezeichnet werden k�nnen. Methode
	     * wir von der Zeichenflaeche verwendet um festzustellen, ob eine
	     * Kante gezogen werden kann.
	     * 
	     * @return Wert der Variable kante
	     */
	    public boolean getKante() {
	    	return kante;
	    }
	    
	    /**
	     * Gibt den Wert der Variablen loeschen zur�ck.
	     * true, wenn Elemente einzeln gel�scht werden k�nnen. Methode
	     * wird von der Zeichenflaeche verwendet um festzustellen ob ein
	     * angeklicktes Netzelement gel�scht werden kann.
	     * 
	     * @return Wert der Variable loeschen
	     */
	    public boolean getLoeschen() {
	    	return loeschen;
	    }
	    
	    /**
	     * Gibt den Wert der Variablen namen zur�ck.
	     * true, wenn Elemente benannt werden k�nnen. Methode wird 
	     * von der Zeichenflaeche verwendet um festzustellen ob ein angeklicktes
	     * Netzelement (Transition oder Stelle) benannt werden kann.
	     * 
	     * @return Wert der Varible namen
	     */
	    public boolean getNamen() {
	    	return namen;
	    }
}
