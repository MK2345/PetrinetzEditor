package view_controll;





import java.awt.Graphics;
import java.lang.Math;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
 
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Kante;
import model.Model;
import model.Stelle;
import model.Transition;
import model.VerschiebenElement;

import java.lang.Math;

/**
 * Zeichenfläche in welcher das Petrinetz graphisch dargestellt wird.
 * Nimmt Mausklicks und Mauszüge auf der Zeichenfläche entgegen und verarbeitet diese
 * je nach voreingestellter Funktion.
 * 
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */

public class Zeichenflaeche extends JPanel implements MouseListener, MouseMotionListener {
	
	/**
	 * Model von welchem die benötigten Daten abgerufen werden
	 */
	Model model; 
	
	/**Klasse Einstellungen in welchem die Editorgröße festgelegt wird*/
	Einstellungen einstell;	

		
	/**
	 * Hilfsvariable zum Löschen und Verschieben von Elementen
	 */
	int merke = -1;
	
	/**
	 * x-Koordinate der Position auf welcher ein Mausereignis stattgefunden hat
	 */
	int x;
	
	/**
	 * y-Koordinate der Position auf welcher ein Mausereignis stattgefunden hat
	 */
	int y;
	
	
	/**
	 * Abstand x-Koordinate zum verschobenen Element
	 */
	int xOffset;
	
	/**
	 * Abstand x-Koordinate zum verschobenen Element
	 */
	int yOffset;	
	
	/**
	 * true, wenn momentan eine Transition verschoben wird
	 */
	boolean schiebtTransition = false;
	
	/**
	 * true, wenn momentan eine Stelle verschoben wird
	 */
	boolean schiebtStelle = false;
	
	
	/**
	 * Hilfsvariablen wenn Kanten gezogen werden
	 * true wenn Kante von einer Transition aus gezogen wird
	 */
	boolean von_Tr = false;
	
	/**
	 * true wenn Kante von einer Stelle aus gezogen wird
	 */
	boolean von_St = false;
	
	/**
	 * true wenn Kante an einer Transition endet
	 */
	boolean zu_Tr = false;
	
	/**
	 * true wenn Kante an einer Stelle endet
	 */
	boolean zu_Stelle = false;
	
	/**
	 * Index der Transition der gezogenen Kante
	 */
	int index_Tr = -1;
	
	/**
	 * Index der Stelle der gezogenen Kante
	 */
	int index_St = -1;	
	
	
	/**
	 * true wenn gerade eine Kante gezogen wird
	 */
	boolean zugkante = false;
	
	
	
	/**
	 * x-Koordinate des Anfangspunktes der Zugkante
	 */
	int zug_xStart;
	
	/**
	 * y-Koordinate des Anfangspunktes der Zugkante
	 */
	int zug_yStart;
	
	/**
	 * x-Koordinate des Endpunktes der Zugkante
	 */
	int zug_xEnde;
	
	/**
	 * y-Koordinate des Endpunktes der Zugkante
	 */
	int zug_yEnde;
	
	
	/**
	 * x-Koordinate des 1. Punktes der Kantenspitze der Zugkante
	 */
	int zug_xEnde1;
	
	
	/**
	 * y-Koordinate des 1. Punktes der Kantenspitze der Zugkante
	 */
	int zug_yEnde1;
	
	/**
	 * x-Koordinate des 2. Punktes der Kantenspitze der Zugkante
	 */
	int zug_xEnde2;
	
	/**
	 * y-Koordinate des 2. Punktes der Kantenspitze der Zugkante
	 */
	int zug_yEnde2;
	
	/**
	 * x-Koordinate des 3. Punktes der Kantenspitze der Zugkante
	 */
	int zug_xEnde3;
	
	/**
	 * y-Koordinate des 3. Punktes der Kantenspitze der Zugkante
	 */
	int zug_yEnde3;	
	
	
	/**
	 * Hilfsliste wenn Transition geschaltet wurde
	 */
	LinkedList<Integer> index_von_Stelle;
	
	/**
	 * Hilfsliste wenn Transition geschaltet wurde
	 */
	LinkedList<Integer> index_zu_Stelle;
	
	/**
	 * Hilfsliste wenn Transition geschaltet wurde
	 */
	LinkedList<Integer> index_von_Stelle_Leer;
	
	/**
	 * Breite der Zeichenflaeche
	 */
	int panelWidth;
	
	/**
	 * Höhe der Zeichenflaeche
	 */
	int panelHeight;
    
	
	
	
	/**
	 * Konstruktor der Klasse Zeichenflaeche
	 * @param model  Model, von welchem die benoetigten Daten abgerufen werden
	 */
    public Zeichenflaeche(Model model) {
    	setBackground(Color.white);
    
    	setPreferredSize(new Dimension(panelWidth, panelHeight));
    	addMouseListener(this);
    	addMouseMotionListener(this);
    	this.model = model;
    	index_von_Stelle = new LinkedList();
    	index_zu_Stelle = new LinkedList();
    	index_von_Stelle_Leer = new LinkedList();	
    	panelWidth = model.getEditorWidth();
    	panelHeight = model.getEditorHeight();
    }  
    
    /**
     * Stellt die Breite der Zeichenflaeche ein. Methode wird durch Einstellungen aufgerufen,
     * wenn Größe der Zeichenflaeche unter Menüoption Einstellungen verändert wurde.
     * @param zahl1  Breite des Boards
     */
    public void setPanelWidth(int zahl1) {
    	panelWidth = zahl1;
    }
    
    /**
     * Stellt die Höhe der Zeichenflaeche ein. Methode wird durch Einstellungen aufgerufen,
     * wenn Größe der Zeichenflaeche unter Menüoption Einstellungen verändert wurde.
     * @param zahl2  Höhe des Boards
     */
    public void setPanelHeight(int zahl2) {
    	panelHeight = zahl2;
    }
    

    /** 
     * Zeichnet das Petrinetz in seinem aktuellen Zustand mit allen Transitionen, Stellen, 
     * Kanten und Marken.    
     */
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        setPreferredSize(new Dimension(panelWidth, panelHeight));        
        
        //Linienstärke und Unschärfe einstellen
        Stroke s = new BasicStroke((3*model.groesseWidth)/40);
        g2.setStroke(s);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);        
      
        //Zugkante zeichnen wenn momentan eine Kante gezogen wird.
        if(zugkante) {
        	int[] xZiehPoints = {zug_xEnde1, zug_xEnde2, zug_xEnde3};
        	int[] yZiehPoints = {zug_yEnde1, zug_yEnde2, zug_yEnde3};
        	g2.drawLine(zug_xStart, zug_yStart, zug_xEnde, zug_yEnde);
        	g2.fillPolygon(xZiehPoints, yZiehPoints, 3);
        }      
        
        //alle fixierten Kanten zeichnen
        for(int i=0; i < model.Kanten_Liste.size(); i++) {        	
        	//x- und y-Koordinaten von Kantenanfang, Kantenende und Spitze bestimmen       	
        	model.berechneKante(model.Kanten_Liste.get(i).xAnfang, 
        			model.Kanten_Liste.get(i).yAnfang,
        			model.Kanten_Liste.get(i).xEnde,
        			model.Kanten_Liste.get(i).yEnde,
        			model.Kanten_Liste.get(i));
        	
        	//Koordinanten für Kantenfang und Kantenende
        	int z1 = model.xan;           	
        	int z2 = model.yan;   
        	int z3 = model.xend;   
        	int z4 = model.yend;   
        	
        	//Koordinaten für Kantenspitze   
        	int z13 = model.x1sp;
        	int z14 = model.y1sp;
        	int z9 = model.x2sp;
        	int z10 = model.y2sp;
        	int z11 = model.x3sp;
        	int z12 = model.y3sp;             	
        	
        	int[] xPoints = {z13, z9, z11};
        	int[] yPoints = {z14, z10, z12};        	
        	
        	//Kante mit Spitze schwarz zeichnen, welche nicht als zu löschen gekennzeichnet wurde
        	if(!model.Kanten_Liste.get(i).loesch_kenn) {
        		g2.drawLine(z1, z2, z3, z4);
        		g2.fillPolygon(xPoints, yPoints, 3); 
        		
            //Kante mit Spitze rot zeichnen, welche als zu löschen gekennzeichnet wurde	
        	} else if(model.Kanten_Liste.get(i).loesch_kenn){
        		g2.drawLine(z1, z2, z3, z4);
        		g2.setColor(Color.red);        		
        		g2.fillPolygon(xPoints, yPoints, 3); 
        		 g2.setColor(Color.black);
        	}        	       	
        }
        
       
        //alle Transitionen zeichnen
        for(int i=0; i < model.Trans_Liste.size(); i++) {  
        
        	//aktivierte Transitionen werden grün gezeichnet
        	if(model.Trans_Liste.get(i).trans_aktiv && 
        			!model.Trans_Liste.get(i).geloescht &&
        			!model.Trans_Liste.get(i).versch_kenn &&
        			!model.Trans_Liste.get(i).loesch_kenn) {
        		
        		g2.setColor(Color.green);
        		 g2.fillRect(model.Trans_Liste.get(i).trans_x_kor, 
        				 model.Trans_Liste.get(i).trans_y_kor, 
        				 model.groesseWidth, model.groesseHeight);  

        	//nicht aktivierte Transitionen werden grau gezeichnet
        	} else if (!model.Trans_Liste.get(i).geloescht &&
        			!model.Trans_Liste.get(i).versch_kenn &&
        			!model.Trans_Liste.get(i).loesch_kenn &&
        			!model.Trans_Liste.get(i).trans_aktiv){
        		g2.setColor(Color.lightGray);
        		 g2.fillRect(model.Trans_Liste.get(i).trans_x_kor, 
        				 model.Trans_Liste.get(i).trans_y_kor, 
        				 model.groesseWidth, model.groesseHeight); 
        	
        	//zum Verschieben gekennzeichnete Transitionen werden gelb gezeichnet
        	} else if(!model.Trans_Liste.get(i).geloescht &&
        			model.Trans_Liste.get(i).versch_kenn &&
        			!model.Trans_Liste.get(i).loesch_kenn) {        		
        		g2.setColor(Color.yellow);
        		g2.fillRect(model.Trans_Liste.get(i).trans_x_kor,
        				model.Trans_Liste.get(i).trans_y_kor,
        				model.groesseWidth, model.groesseHeight);

        	//zum Löschen gekennzeichnete Transitionen werden rot gezeichnet	
        	} else if(model.Trans_Liste.get(i).loesch_kenn) {
        		g2.setColor(Color.red);
        		g2.fillRect(model.Trans_Liste.get(i).trans_x_kor,
        				model.Trans_Liste.get(i).trans_y_kor,
        				model.groesseWidth, model.groesseHeight);
        	}
       	 //falls die Transition einen Namen hat, wird dieser rechts neben die Transition geschrieben
        	if(!model.Trans_Liste.get(i).geloescht) { 
        		
        			 g2.setColor(Color.black);  
               		 g2.drawString(model.Trans_Liste.get(i).text, 
               				 model.Trans_Liste.get(i).trans_x_kor, 
               				 model.Trans_Liste.get(i).trans_y_kor - 5);        		
       		
        	}
        }        

        
        //alle Stellen zeichnen
        for(int i=0; i < model.Stellen_Liste.size(); i++) {        	
        	
        	//zum Löschen gekennzeichnete Stellen werden rot gezeichnet
        	if(model.Stellen_Liste.get(i).loesch_kenn &&
        			!model.Stellen_Liste.get(i).geloescht) {
        		g2.setColor(Color.red);
        		g2.fillOval(model.Stellen_Liste.get(i).stelle_x_kor, 
        				model.Stellen_Liste.get(i).stelle_y_kor, 
        				model.groesseWidth, model.groesseHeight);        		
        		g2.setColor(Color.black);
        	}
        	
        	//nicht zum Löschen oder Verschieben gekennzeichnete Stellen werden grau gezeichnet       	
        	if(!model.Stellen_Liste.get(i).geloescht && 
        			!model.Stellen_Liste.get(i).loesch_kenn &&
        			!model.Stellen_Liste.get(i).versch_kenn 
        			) {
        		g2.setColor(Color.lightGray);
        		g2.fillOval(model.Stellen_Liste.get(i).stelle_x_kor, 
        				model.Stellen_Liste.get(i).stelle_y_kor, 
        				model.groesseWidth, model.groesseHeight);                 	
            	
            //zum Verschieben gekennzeichnete Stellen werden gelb gezeichnet
        	} else if(!model.Stellen_Liste.get(i).geloescht && 
        			!model.Stellen_Liste.get(i).loesch_kenn &&
        			model.Stellen_Liste.get(i).versch_kenn) {
        		g2.setColor(Color.yellow);
        		g2.fillOval(model.Stellen_Liste.get(i).stelle_x_kor, 
        				model.Stellen_Liste.get(i).stelle_y_kor, 
        				model.groesseWidth, model.groesseHeight);
        	}
        	
        	g2.setColor(Color.black);
        	//wenn Stelle genau eine Marke enthält, wird diese als großer Punkt in die Mitte gezeichnet
        	if(model.Stellen_Liste.get(i).anzahl_Marken == 1 && 
        			!model.Stellen_Liste.get(i).geloescht ) {  	


        		g2.fillOval(model.Stellen_Liste.get(i).stelle_x_kor+((model.groesseWidth)/3), 
        				model.Stellen_Liste.get(i).stelle_y_kor+((model.groesseHeight)/3), 
        				model.groeMarkeWidth, model.groeMarkeHeight);
        	
        	//wenn Stelle mehr als eine Marke enthält, werden diese als Zahl eingetragen
        	} else if(model.Stellen_Liste.get(i).anzahl_Marken > 1 && 
        			!model.Stellen_Liste.get(i).geloescht) {
        		
        		int anz = model.Stellen_Liste.get(i).anzahl_Marken;            		
        		String str = Integer.toString(anz);   
        		Font anzahlMarken = new Font(str, Font.PLAIN, model.groeMarkeZahl);
        		g2.setFont(anzahlMarken);   		
        		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        				RenderingHints.VALUE_ANTIALIAS_ON); 

        		//Position der Zahl innerhalb der Stelle bestimmen
        		if(anz < 10) {
            		g2.drawString(str, 
    				model.Stellen_Liste.get(i).stelle_x_kor + ((model.groesseWidth*2/5)), 
    				model.Stellen_Liste.get(i).stelle_y_kor +((model.groesseHeight*2)/3));
        		} else if ((anz > 9) && (anz < 100) ) {
        			g2.drawString(str, 
            				model.Stellen_Liste.get(i).stelle_x_kor + ((model.groesseWidth*1)/5), 
            				model.Stellen_Liste.get(i).stelle_y_kor +((model.groesseHeight*2)/3));
        		} else if (anz > 99) {
        			g2.drawString(str, 
            				model.Stellen_Liste.get(i).stelle_x_kor + (model.groesseWidth/15), 
            				model.Stellen_Liste.get(i).stelle_y_kor +((model.groesseHeight*2)/3));
        		}        		
        	} 
        	//Namen der Stelle zeichnen, falls vorhanden
        	if(!model.Stellen_Liste.get(i).geloescht) {        		
        		g2.setColor(Color.black); 
        		String text = model.Stellen_Liste.get(i).tex;
        		//Schriftgröße festlegen
        		Font textGr = new Font(text, Font.PLAIN, 12);
        		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        				RenderingHints.VALUE_ANTIALIAS_ON); 
        		g2.setFont(textGr);
        		g2.drawString(text, 
        				model.Stellen_Liste.get(i).stelle_x_kor, 
        				model.Stellen_Liste.get(i).stelle_y_kor - 5);

        	}        	
        }        
    }  
    
    /**
     * Prüft ob auf eine bestimmte Transition geklickt wurde
     * @param x  x-Koordinate des Mausklicks
     * @param y  y-Koordinate des Mausklicks
     * @param trans  Transition in welche evtl. geklickt wurde
     * @return  true oder false je nachdem ob auf diese Transition geklickt wurde
     */
    public boolean transitionPressed(int x, int y, Transition trans) {
    	boolean pressed = false;
    	if(x >= trans.trans_x_kor && 
				 x <= trans.trans_x_kor+model.groesseWidth &&
				 y >= trans.trans_y_kor &&
				 y <= trans.trans_y_kor+model.groesseHeight 
				 ) {
    		pressed = true;    		
    	}
    	return pressed;
    }
    
    /**
     * Prüft ob auf eine bestimmte Stelle geklickt wurde
     * @param x  x-Koordinate des Mausklicks
     * @param y  y-Koordinate des Mausklicks
     * @param stelle  Stelle in welche evtl. geklickt wurde
     * @return  true oder false je nachdem ob auf diese Stelle geklickt wurde
     */
    public boolean stellePressed(int x, int y, Stelle stelle) {
    	boolean pressed = false;
    	if(x >= stelle.stelle_x_kor && 
				 x <= stelle.stelle_x_kor+model.groesseWidth &&
				 y >= stelle.stelle_y_kor && 
				 y <= stelle.stelle_y_kor+model.groesseHeight 
				 ) {
    		pressed = true;
    	}
    	return pressed;
    }
    
    /**
     * Prüft ob auf eine bestimmte Kante geklickt wurde
     * @param x  x-Koordinate des Mausklicks
     * @param y  y-Koordinate des Mausklicks
     * @param kante  Kante in welche evtl. geklickt wurde
     * @return  true oder false je nachdem ob auf diese Kante gekickt wurde
     */
    public boolean kantePressed(int x, int y, Kante kante) {
    	boolean pressed = false;     	
    	int rechtsx, linksx;
    	int obeny, unteny;  
    	double abstand = Double.MAX_VALUE;
    	double min_abstand;
    	double laenge_norm_richt;
    	double norm_richtx, norm_richty;
    	double zw_vektorx, zw_vektory;
    	double abst_vektor;   
    	
    		if(kante.xAnfang <= kante.xEnde) {
        		linksx = kante.xAnfang;
        		rechtsx = kante.xEnde;
        	} else {
        		linksx = kante.xEnde;
        		rechtsx = kante.xAnfang;
        	}
        	if(kante.yAnfang <= kante.yEnde) {
        		obeny = kante.yAnfang;
        		unteny = kante.yEnde;
        	} else {
        		obeny = kante.yEnde;
        		unteny = kante.yAnfang;
        	}
        	
        	if(((x >= linksx-10) && (x <= rechtsx+10)) &&
        			((y >= obeny-10) && (y <= unteny+10))) {
        		
        		laenge_norm_richt = Math.sqrt(((kante.xAnfang - 
        				kante.xEnde) * (kante.xAnfang - kante.xEnde)) +
        				((kante.yAnfang - kante.yEnde) * 
        						(kante.yAnfang - kante.yEnde)));
        		
        		norm_richtx = (kante.xEnde - kante.xAnfang) / laenge_norm_richt;
        		norm_richty = (kante.yEnde - kante.yAnfang) / laenge_norm_richt;

        		zw_vektorx = (x - kante.xAnfang);
        		zw_vektory = (y - kante.yAnfang);
        		
        		abst_vektor = (zw_vektorx * norm_richty) - (zw_vektory * norm_richtx);
        		min_abstand = Math.sqrt(abst_vektor * abst_vektor);

        		if((min_abstand < abstand) && (min_abstand < 12)) {
        			pressed = true;
        			abstand = min_abstand;
        		}       		
        	} 	
    	return pressed;
    }
    
    
    /**
     * Speichert alle Ein- und Ausgangsstellen einer geschalteten Transition
     * @param tr  ist die geschaltete Transition deren Ein- und Ausgangsstellen gespeichert werden
     */
    public void speichereEinAusStellen(int tr) {
    	 for(int j=0; j < model.Kanten_Liste.size(); j++) {        					
				if(model.Kanten_Liste.get(j).index_Trans == tr && 
						model.Kanten_Liste.get(j).von_Stelle &&
						model.Kanten_Liste.get(j).zu_Trans) {
					    index_von_Stelle.addLast(model.Kanten_Liste.get(j).index_Stelle);             				
				} else if(model.Kanten_Liste.get(j).index_Trans == tr &&
						model.Kanten_Liste.get(j).von_Trans &&
						model.Kanten_Liste.get(j).zu_Stelle){
					index_zu_Stelle.addLast(model.Kanten_Liste.get(j).index_Stelle);	
				}        				       		
			}
    }

    
    
    /**
     * Speichert alle Eingangsstellen einer Transition. Dient als Hilfmethode wenn
     * Transition geschaltet wurde.
     * @param tr ist die Transition deren Eingangsstellen gespeichert werden
     */
    public void speichereEinStellen(int tr) {
    	for(int j=0; j < model.Kanten_Liste.size(); j++) {
    		if(model.Kanten_Liste.get(j).index_Trans == tr &&
    				model.Kanten_Liste.get(j).von_Stelle &&
    				model.Kanten_Liste.get(j).zu_Trans) {
    			index_von_Stelle.addLast(model.Kanten_Liste.get(j).index_Stelle);
    		}
    	}
    }

    
    /**
     * Speichert die Stellen aus der Liste index_von_Stelle, welche keine Marken besitzen,
     * in die Liste index_von_Stelle_Leer. Dient als Hilfsmethode wenn Transition 
     * geschaltet wurde.
     */
     public void speiEinStellenOhneMarken() {
     	for(int j=0; j < index_von_Stelle.size(); j++) {
     		if(model.Stellen_Liste.get(index_von_Stelle.get(j)).anzahl_Marken == 0) {
     			index_von_Stelle_Leer.add(index_von_Stelle.get(j));
     		}
     	}
     }
    
    
     
     public void mousePressed(MouseEvent e) {     	
    	 x = e.getX();
         y = e.getY();         
         
         //wenn aktivierte Transition geschaltet wurde
         if(e.isMetaDown() && !model.getLoeschKenn() && !model.getVerschKenn() &&
        		 !model.getMehrVerschieben()) {           	 
        	 
        	 //prüfen ob mit rechter Maustaste auf eine Transition geklickt wurde
        	 for(int i=0; i < model.Trans_Liste.size(); i++) { 
        		 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x, y, model.Trans_Liste.get(i))) {
        			 
        			 //prüfen ob Transition auch aktiviert ist
        			 if(model.Trans_Liste.get(i).trans_aktiv) {         				 
        				 
        				 //alle Ein- und Ausgangsstellen der Transition zwischenspeichern
        				 speichereEinAusStellen(i);  	
             			
             			//Anzahl der Marken der Eingangsstellen werden jeweils um 1 reduziert
             			for(int l=0; l < index_von_Stelle.size(); l++) {        				        				
             				 int index1 = index_von_Stelle.get(l);
             				 model.Stellen_Liste.get(index1).anzahl_Marken = 
             						 model.Stellen_Liste.get(index1).anzahl_Marken - 1;
             			}
             			
             			//Anzahl der Marken der Ausgangsstellen werden jeweils um 1 erhöht
             			for(int m=0; m < index_zu_Stelle.size(); m++) {        				        				
             				 int index2 = index_zu_Stelle.get(m);
             				 model.Stellen_Liste.get(index2).anzahl_Marken =
             						 model.Stellen_Liste.get(index2).anzahl_Marken + 1;
             			}
             			
             			//prüfen ob noch alle Eingangsstellen Marken besitzen, wenn nicht wird die 
             			//Transition deaktiviert             			
             			for(int p=0; p < index_von_Stelle.size(); p++) {
             				if(model.Stellen_Liste.get(index_von_Stelle.get(p)).anzahl_Marken == 0) {
             					model.Trans_Liste.get(i).trans_aktiv = false;
             				}
             			}            			

             			
             			
             			//prüfen ob eine der Eingangsstellen nun keine Marken mehr enthält, wenn ja dann
             			//Kanten_Array überprüfen ob diese Stelle auch Eingangsstelle einer anderen 
             			//Transition ist, wenn eine dieser Transitionen noch aktiviert ist, wird diese
             			//deaktiviert
             			
             			speiEinStellenOhneMarken();

             			//in Kanten_Array nach Transitionen mit den leergewordenen Eingangsstellen suchen und diese
             			//ggf. deaktivieren
             			for(int p=0; p < model.Kanten_Liste.size(); p++) {             				
             				int indStelle = model.Kanten_Liste.get(p).index_Stelle;
             				int indTrans = model.Kanten_Liste.get(p).index_Trans;
             				for(int q=0; q < index_von_Stelle_Leer.size(); q++) {
             					if((indStelle == index_von_Stelle_Leer.get(q)) &&
             							model.Kanten_Liste.get(p).von_Stelle) {	
             						model.Trans_Liste.get(model.Kanten_Liste.get(p).index_Trans).trans_aktiv = false;             						
             					}
             				}
             			}             			
             			index_von_Stelle_Leer.clear();             			
             			
             			
             			
             			//prüfen ob durch Ausgangsstellen neue Transitionen aktiviert werden können
             			 LinkedList<Integer> hilfeTrAktiv = new LinkedList();
        				 LinkedList<Integer> hilfeStAktiv = new LinkedList();
        				 //zuerst alle Transitionen speichern zu welchen diese Stellen die Eingangsstellen sind
             			for(int p=0; p < index_zu_Stelle.size(); p++) {
             				for(int q=0; q < model.Kanten_Liste.size(); q++) {
             					if(model.Kanten_Liste.get(q).von_Stelle &&
             							model.Kanten_Liste.get(q).zu_Trans &&
             							model.Kanten_Liste.get(q).index_Stelle == index_zu_Stelle.get(p)) {
             						hilfeTrAktiv.add(model.Kanten_Liste.get(q).index_Trans);
             						
             					}
             				}             				
        			    }
             			 index_von_Stelle.clear(); 
                		 index_zu_Stelle.clear();  
             			
        				//die gespeicherten Transitionen überprüfen ob alle ihre Eingangsstellen Marken besitzen             			
             			for(int p=0; p < hilfeTrAktiv.size(); p++) {
             				//Eingangsstellen der Transition zwischenspeichern
             				speichereEinStellen(hilfeTrAktiv.get(p)); 
             				boolean vorb_marken = true;
             				for(int q=0; q < index_von_Stelle.size(); q++) {
             					if(model.Stellen_Liste.get(index_von_Stelle.get(q)).anzahl_Marken == 0){
             						vorb_marken = false;
             					}
             				}
             				if(vorb_marken) {
             					model.Trans_Liste.get(hilfeTrAktiv.get(p)).trans_aktiv = true;
             				}
             				vorb_marken = true;
             			}
             			hilfeTrAktiv.clear();
             			index_von_Stelle.clear();
             			index_zu_Stelle.clear();
             			
             			//wenn Transition nicht aktiviert ist, Meldung zeigen
        			} else if(!model.Trans_Liste.get(i).trans_aktiv) {
        				JOptionPane.showMessageDialog(null, "<html>Transition ist nicht aktiviert.<br>" +
        						"Besitzen alle Eingangsstellen Marken?</html>");
        			}
        			repaint();

        		 }
        	 }
         } else if(e.isMetaDown() && model.getLoeschKenn()){
        	 JOptionPane.showMessageDialog(null, "<html>Bitte beenden Sie zuerst Ihren Löschvorgang!</html>");

         }else if(e.isMetaDown() && model.getVerschKenn()) {
        	 JOptionPane.showMessageDialog(null, "<html>Bitte beenden Sie zuerst den Vorgang des<br>" +
 					"Verschiebens mehrerer Elemente!</html>");
         }else if(e.isMetaDown() && model.getMehrVerschieben()) {
        	 JOptionPane.showMessageDialog(null, "<html>Bitte beenden Sie zuerst den Vorgang des<br>" +
 					"Verschiebens mehrerer Elemente!</html>");
         }         
        	 
                

         
         ///////////////////////////////Verschieben eines Elements////////////////////////////////
           
         //wenn nur ein Element verschoben wird
    	 if(model.getVerschieben() && !e.isMetaDown()) {     
    		 schiebtTransition = false;//damit bei Mausklick auf leere Fläche nichts verschoben wird
			 schiebtStelle = false;//damit bei Mausklick auf leere Fläche nichts verschoben wird
			 
    		 for(int i=0; i < model.Trans_Liste.size(); i++) {    			
    			 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x, y, model.Trans_Liste.get(i))) {     				
    				
    				 xOffset = x - model.Trans_Liste.get(i).trans_x_kor;    				
    				 yOffset = y - model.Trans_Liste.get(i).trans_y_kor; 				
    				
     				 merke = i;
     				 schiebtTransition = true;
     				 schiebtStelle = false;
    			 }    			
    		 }
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i))) {
 			 
    				 xOffset = x - model.Stellen_Liste.get(i).stelle_x_kor;
    				 yOffset = y - model.Stellen_Liste.get(i).stelle_y_kor;
    				 merke = i;
    				 schiebtTransition = false;
     				 schiebtStelle = true;
    			 }
    		 }  
    		 //repaint in MouseDragged
    	 }
    	 
    	 /////////////////////////////////Verschieben mehrerer Elemente/////////////////////
    	 
    	 //wenn mehrere Elemente verschoben werden sollen, werden diese gelb gekennzeichnet
    	 if(model.getVerschKenn()&& !e.isMetaDown()) {    		
    		 for(int i=0; i < model.Trans_Liste.size(); i++) {
    			 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x, y, model.Trans_Liste.get(i))) { 
    				model.Trans_Liste.get(i).versch_kenn = true;     				
    			 }   
    		 }
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i))) {
    				 model.Stellen_Liste.get(i).versch_kenn = true;    				
    			 }
    		 } repaint();
    	 }
    	
    	 
    	 
    	 
    	 //mehrere Elemente, welche zuvor gelb gekennzeichnet wurden, werden gleichzeitig verschoben
    	 if(model.getMehrVerschieben()&& !e.isMetaDown()) {    		 
    		 model.VerschElem_Liste.clear();     	
    		 
    		 for(int i=0; i < model.Trans_Liste.size(); i++) {
    			 //suche ob Verschiebung von bestimmter Transition gestartet wird
    			 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x, y, model.Trans_Liste.get(i)) &&
    					 model.Trans_Liste.get(i).versch_kenn) {     				
    				
    				 xOffset = x - model.Trans_Liste.get(i).trans_x_kor;    				
    				 yOffset = y - model.Trans_Liste.get(i).trans_y_kor; 		
    				
     				 merke = i;
     				 schiebtTransition = true;
     				 schiebtStelle = false;     				
     				 model.VerschElem_Liste.add(new VerschiebenElement(merke, 1, 0, 0)); 
     				
     				 //restliche zu verschiebenden Transitionen zur Liste hinzufügen
     				 for(int q=0; q < model.Trans_Liste.size(); q++) {
    					 if(q == i) {
    						 //do nothing
    					 } else if(model.Trans_Liste.get(q).versch_kenn) {
    						 int zahl1 = model.Trans_Liste.get(merke).trans_x_kor -
    								 model.Trans_Liste.get(q).trans_x_kor;
    						 int zahl2 = model.Trans_Liste.get(merke).trans_y_kor -
    								 model.Trans_Liste.get(q).trans_y_kor;
    						 
    						 model.VerschElem_Liste.add(new VerschiebenElement(q, 1, zahl1, zahl2));   						
    					 }
    				 }
     				 //restliche zu verschiebenden Stellen zur Liste hinzufügen
     				 for(int q=0; q < model.Stellen_Liste.size(); q++) {
    					if(model.Stellen_Liste.get(q).versch_kenn) {
    						 int zahl1 = model.Trans_Liste.get(merke).trans_x_kor -
    								 model.Stellen_Liste.get(q).stelle_x_kor;
    						 int zahl2 = model.Trans_Liste.get(merke).trans_y_kor -
    								 model.Stellen_Liste.get(q).stelle_y_kor;
    						 model.VerschElem_Liste.add(new VerschiebenElement(q, 2, zahl1, zahl2));
    					 }
    				 }
    			 }  
    		 }
    		 
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {
    			 //suche ob Verschiebung von bestimmter Stelle gestartet wird
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i))&&
    					 model.Stellen_Liste.get(i).versch_kenn) {
    				
    				 xOffset =  x - model.Stellen_Liste.get(i).stelle_x_kor;
    				 yOffset =  y - model.Stellen_Liste.get(i).stelle_y_kor;
    				
    				 merke = i;
    				 schiebtTransition = false;
     				 schiebtStelle = true;
     				 model.VerschElem_Liste.add(new VerschiebenElement(merke, 2, 0, 0));
     				 
     				 //restliche zu verschiebenden Stelle zur Liste hinzufügen
     				 for(int q=0; q < model.Stellen_Liste.size(); q++) {
     					 if(q == i) {
     						 //do nothing
     					 } else if(model.Stellen_Liste.get(q).versch_kenn) {
     						 int zahl1 = model.Stellen_Liste.get(merke).stelle_x_kor -
     								 model.Stellen_Liste.get(q).stelle_x_kor;
     						 int zahl2 = model.Stellen_Liste.get(merke).stelle_y_kor -
     								 model.Stellen_Liste.get(q).stelle_y_kor;
     						 model.VerschElem_Liste.add(new VerschiebenElement(q, 2, zahl1, zahl2));
     					 }
     				 }
     				 //restliche zu verschiebenden Transitionen zur Liste hinzufügen
     				for(int q=0; q < model.Trans_Liste.size(); q++) {
   					 if(model.Trans_Liste.get(q).versch_kenn) {
   						 int zahl1 = model.Stellen_Liste.get(merke).stelle_x_kor -
   								 model.Trans_Liste.get(q).trans_x_kor;
   						 int zahl2 = model.Stellen_Liste.get(merke).stelle_y_kor -
   								 model.Trans_Liste.get(q).trans_y_kor;   						 
   						 model.VerschElem_Liste.add(new VerschiebenElement(q, 1, zahl1, zahl2));   						
   					 }
   				 }
    			 }
    		 }  
    		 //repaint in MouseDragged
    	 }   
    	 
    	 
    	 
    	 
    	 ////////////////////////////Transition erzeugen///////////////////////////////
    	 
    	 //eine Transition wird erzeugt
    	 if(model.getTransition()&& !e.isMetaDown()) { 
    		 //Transition erzeugen und aktivieren, da noch keine Eingangsstellen vorhanden    		 
    		 model.Trans_Liste.addLast(new Transition(x-(model.groesseWidth/2), 
    				 y-(model.groesseHeight/2), model.groesseWidth, 
    				 model.groesseHeight, Color.black, "", true, false, false, false));   
    		   		
    		 repaint();
    	 } 
    	 
    	 
    	 //eine Stelle wird erzeugt
    	 if(model.getStelle()&& !e.isMetaDown()) {  
    		 model.Stellen_Liste.addLast(new Stelle(x-(model.groesseWidth/2), 
    				 y-(model.groesseHeight/2), model.groesseWidth, 
    				 model.groesseHeight, 
    				 0, Color.black, "", false, false, false));
    		 
    		 repaint();
    	 }
    	 
    	 
    	 ///////////////////////////////////////Markenanzahl erhöhen////////////////////////////////
    	 
    	 //Markenanzahl einer Stelle wird erhöht
    	 if(model.getMarke()&& !e.isMetaDown()) {
    		 LinkedList <Integer> hilfeTrans = new LinkedList();
    		 LinkedList <Integer> hilfeStellen = new LinkedList();
    		 boolean alleMarkiert = true;
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {
    			 //wenn auf Stelle gedrückt, wird Anzahl der Marken erhöht
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i))) {
    				model.Stellen_Liste.get(i).anzahl_Marken = 
    						model.Stellen_Liste.get(i).anzahl_Marken + 1;   
    				
    				//prüfen ob durch setzen der Marke eine Transition aktiviert werden kann
    				
    				//zuerst alle Transitionen, von welchen die Stelle Eingangsstelle
    				//darstellt zwischenspeichern
    				for(int j=0; j < model.Kanten_Liste.size(); j++) {
    					if(model.Kanten_Liste.get(j).von_Stelle &&
    							model.Kanten_Liste.get(j).index_Stelle == i &&
    							model.Kanten_Liste.get(j).zu_Trans) {
    						hilfeTrans.add(model.Kanten_Liste.get(j).index_Trans);
    					}
    				}
    				//alle gespeicherten Transition prüfen, ob alle ihre Eingangsstellen
    				//mind. eine Marke enthalten, wenn ja werden diese Transitionen aktiviert
    				for(int j=0; j < hilfeTrans.size(); j++) {
    					//alle Eingangsstellen der jeweiligen Transition zwischenspeichern
    					for(int k=0; k < model.Kanten_Liste.size(); k++) {
    						if(model.Kanten_Liste.get(k).von_Stelle &&
    								model.Kanten_Liste.get(k).zu_Trans &&
    								model.Kanten_Liste.get(k).index_Trans == hilfeTrans.get(j)) {
    							hilfeStellen.add(model.Kanten_Liste.get(k).index_Stelle);
    						}
    					}
    					//prüfen ob alle Eingangsstellen mind. eine Marke enthalten
    					for(int l=0; l < hilfeStellen.size(); l++) {
    						if(model.Stellen_Liste.get(hilfeStellen.get(l)).anzahl_Marken == 0) {
    							alleMarkiert = false;
    						}
    					}
    					hilfeStellen.clear();
    					
    					//wenn alle Eingangsstellen Marken besitzen, wird die Transition aktiviert
    					if(alleMarkiert) {
    						model.Trans_Liste.get(hilfeTrans.get(j)).trans_aktiv = true;
    					}
    					alleMarkiert = true;    					
    				}    				
    				hilfeTrans.clear();    				
    				repaint();
    			 }
    		 }
    	 }
    	 
    	 
    	 //gewünschte Anzahl der Marken bei gewünschter Stelle eintragen
    	 if(model.getAnzahlMarken()&& !e.isMetaDown()) {
    		 LinkedList <Integer> hilfeTrans = new LinkedList();
    		 LinkedList <Integer> hilfeStellen = new LinkedList();
    		 boolean alleMarkiert = true;
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {
    			 //wenn auf Stelle gedrückt, wird Anzahl der Marken erhöht
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i))) {
    				 
    				String anzahl = JOptionPane.showInputDialog("Bitte gewünschte Anzahl der Marken eingeben"); 
    				
    				if(anzahl != null) {
    					
    					int zahl = 0;
    					try {
    						zahl = Integer.parseInt(anzahl);
    					} catch(NumberFormatException ffe) {
    						JOptionPane.showMessageDialog(this, "Bitte eine ganze Zahl größer gleich 0 eingeben!");    						
    						break;
    					}
    					
    					zahl = new Integer(anzahl).intValue();
        				model.Stellen_Liste.get(i).anzahl_Marken = zahl;   
        				
        				//prüfen Marken gelöscht wurden (also auf 0 gesetzt), wenn ja dann
        				//prüfen ob evtl. Transition deaktiviert werden muß
        				if(zahl == 0) {
        					LinkedList <Integer> index_Trans = new LinkedList();
        					for(int n=0; n < model.Kanten_Liste.size(); n++) {
        						if(model.Kanten_Liste.get(n).von_Stelle &&
        								(model.Kanten_Liste.get(n).index_Stelle == i) &&
        								model.Trans_Liste.get(model.Kanten_Liste.get(n).index_Trans).trans_aktiv) {
        							model.Trans_Liste.get(model.Kanten_Liste.get(n).index_Trans).trans_aktiv = false;
        						}
        					}
        				}    				
        				
        				//prüfen ob durch setzen der Marke eine Transition aktiviert werden kann    				
        				//zuerst alle Transitionen, von welchen die Stelle Eingangsstelle
        				//darstellt zwischenspeichern
        				for(int j=0; j < model.Kanten_Liste.size(); j++) {
        					if(model.Kanten_Liste.get(j).von_Stelle &&
        							model.Kanten_Liste.get(j).index_Stelle == i &&
        							model.Kanten_Liste.get(j).zu_Trans) {
        						hilfeTrans.add(model.Kanten_Liste.get(j).index_Trans);
        					}
        				}
        				//alle gespeicherten Transition prüfen, ob alle ihre Eingangsstellen
        				//mind. eine Marke enthalten, wenn ja werden diese Transitionen aktiviert
        				for(int j=0; j < hilfeTrans.size(); j++) {
        					//alle Eingangsstellen der jeweiligen Transition zwischenspeichern
        					for(int k=0; k < model.Kanten_Liste.size(); k++) {
        						if(model.Kanten_Liste.get(k).von_Stelle &&
        								model.Kanten_Liste.get(k).zu_Trans &&
        								model.Kanten_Liste.get(k).index_Trans == hilfeTrans.get(j)) {
        							hilfeStellen.add(model.Kanten_Liste.get(k).index_Stelle);
        						}
        					}
        					//prüfen ob alle Eingangsstellen mind. eine Marke enthalten
        					for(int l=0; l < hilfeStellen.size(); l++) {
        						if(model.Stellen_Liste.get(hilfeStellen.get(l)).anzahl_Marken == 0) {
        							alleMarkiert = false;
        						}
        					}
        					hilfeStellen.clear();
        					
        					//wenn alle Eingangsstellen Marken besitzen, wird die Transition aktiviert
        					if(alleMarkiert) {
        						model.Trans_Liste.get(hilfeTrans.get(j)).trans_aktiv = true;
        					}
        					alleMarkiert = true;    					
        				}    				
        				hilfeTrans.clear();    				
        				repaint();
    				}    				
    			 }
    		 }
    	 }
    	 
    	     	 
    	 /////////////////////Kante zeichnen//////////////////////////////////
    	 
    	 if(model.getKante()&& !e.isMetaDown()) {    		
    		 for(int i=0; i < model.Trans_Liste.size(); i++) {      			
    			 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x, y, model.Trans_Liste.get(i))) {
    				von_Tr = true;
    				 von_St = false;
    				 index_Tr = i;    							 
    			 }    			
    		 }
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i))) {
    				 von_St = true;
    				 von_Tr = false;
    				 index_St = i;    				  				
    			 }
    		 } 
    		 //repaint wird in mouseReleased aufgerufen, da dort der Endpunkt der Kante bestimmt wird
    	 }
    	 
    	 
    	 
    	 
    
    	 //////////////////////Löschen eines Elements//////////////////////////////////
    	 
    	 
    	 /**
    	  * Löschen einer Transition, Stelle oder Kante. Wenn Transition oder Stelle gelöscht werden soll,
    	  * wird lediglich deren boolean Wert *gelöscht* auf true gesetzt
    	  */
    	 if(model.getLoeschen() && !e.isMetaDown()) {    	
    		 
    		 ///////////////////////löschen einer Transition////////////////////////////////
    		 //wenn eine Transition gelöscht werden soll, dann wird diese und ihre Kanten hier gelöscht
    		 for(int i=0; i < model.Trans_Liste.size(); i++) {    			
    			 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x, y, model.Trans_Liste.get(i))) {
    				 model.Trans_Liste.get(i).geloescht = true; //löschen der Transition	
    				 //löschen der Kanten
    				 for(int j=0; j < model.Kanten_Liste.size(); j++) {    					
    					 if(model.Kanten_Liste.get(j).index_Trans == i) {    						
    						 model.Kanten_Liste.remove(j);
    						 j = j - 1;
    					 }
    				 }   				 
    				 repaint();
    			 }
    		 } 
    		 
    		 //////////////////////löschen einer Stelle//////////////////////////////////////////////
    		//wenn eine Stelle gelöscht werden soll, dann wird diese und ihre Kanten hier gelöscht   		
    		 LinkedList<Integer> trans_akt = new LinkedList();  //für Transitionen welche mit Stelle verbunden sind	    		 
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {    			
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i))) {
    				 model.Stellen_Liste.get(i).geloescht = true;    //löschen der Stelle	
    				 //löschen der Kanten
    				 for(int j=0; j < model.Kanten_Liste.size(); j++) {
    					 if(model.Kanten_Liste.get(j).index_Stelle == i) {
    						 if(model.Kanten_Liste.get(j).von_Stelle) {
    							 //Index der Transition zwischenspeichern wenn sie Eingangsstelle war
        						 trans_akt.add(model.Kanten_Liste.get(j).index_Trans);
    						 }	    						 
    						 model.Kanten_Liste.remove(j);
    						 j = j - 1;
    					 }
    				 }
    				 repaint();
    			 }
    		 } 
    		 
    		 //prüfen ob zwischengespeicherte Transitionen noch weitere Eingangsstellen haben, wenn nein, werden diese
    		 //aktiviert
    		 for(int i=0; i < trans_akt.size(); i++) {
    			boolean aktiv = true; //wenn Wert auf true bleibt, wird Transition aktiviert
    			 for(int j=0; j < model.Kanten_Liste.size(); j++) {
    				 if(model.Kanten_Liste.get(j).von_Stelle &&
    						 model.Kanten_Liste.get(j).index_Trans == trans_akt.get(i)){
    					 aktiv = model.Trans_Liste.get(trans_akt.get(i)).trans_aktiv;
    				 } 
    			 }
    			 //wenn true, dann wird Transition aktiviert, da keine Eingangsstellen mehr vorhanden
    			 if(aktiv){
    				 model.Trans_Liste.get(trans_akt.get(i)).trans_aktiv = true;
    			 }
    		 } 
    		 
    		 LinkedList <Integer> stellen_akt = new LinkedList(); //für Eingangsstellen der gespeicherten Transitionen
    		 for(int i=0; i < trans_akt.size(); i++) {
    			 //zwischenspeichern der Eingangsstellen zur Transition
    			 for(int j=0; j < model.Kanten_Liste.size(); j++) {
    				 if(model.Kanten_Liste.get(j).von_Stelle && 
    						 model.Kanten_Liste.get(j).index_Trans == trans_akt.get(i)) {
    					 stellen_akt.addLast(model.Kanten_Liste.get(j).index_Stelle);
    				 }
    			 }
    			 //prüfen ob eine der Eingangsstellen keine Marken enthält, wenn nein wird Transition aktiviert
    			 boolean aktiv = true; //wenn Wert auf true bleibt, wird Transition aktiviert
    			 for(int k=0; k < stellen_akt.size(); k++) {
    				 if(model.Stellen_Liste.get(stellen_akt.get(k)).anzahl_Marken == 0) {
    					 aktiv = false;
    				 }
    			 }
    			 if(aktiv) {
    				 model.Trans_Liste.get(trans_akt.get(i)).trans_aktiv = true;
    			 }    			 
    		 }
    		trans_akt.clear();
    		
    		
    		////////////////////////löschen einer Kante//////////////////////////////
    		
    		//wenn eine Kante gelöscht werden soll, dann wird diese nachfolgend gelöscht           	
        	LinkedList <Integer> stellenindex = new LinkedList(); //Stellen welche zur Transition führen
        	int indexTrans = -1; //Index der Transition welche zur Kante gehört
        	int indexStelle = -1; //Index der Stelle welche zur Kante gehört
        	boolean doppelkante = false;
        	
        	int indexKante1 = -1; //Index der Kante, wenn Kante der Eingangsstelle
        	int indexKante2 = -1;  //Index der Kante, wenn Kante der Ausgangsstelle
        	
        	//zu löschende Kante suchen
        	for(int i=0; i < model.Kanten_Liste.size(); i++) {
        		if(kantePressed(x, y, model.Kanten_Liste.get(i))) {        			
        				indexTrans = model.Kanten_Liste.get(i).index_Trans;
        				indexStelle = model.Kanten_Liste.get(i).index_Stelle;
        				
        				//Index der Kante festhalten, ja nachdem ob Kante der Eingangs- oder Ausgangsstelle
        				if(model.Kanten_Liste.get(i).von_Stelle) {
        					indexKante1 = i;

        				} else if(model.Kanten_Liste.get(i).zu_Stelle) {
        					indexKante2 = i;
        				}
        				
        				//prüfen ob zweite Kante zwischen dieser Transition und Stelle existiert
        				for(int j=0; j < model.Kanten_Liste.size(); j++) {        					
        					if((model.Kanten_Liste.get(j).index_Trans == indexTrans) &&
        							(model.Kanten_Liste.get(j).index_Stelle == indexStelle) &&
        							(i != j)) {
        						doppelkante = true;
        						if((indexKante1 != -1) && (indexKante2 == -1)) {
        							indexKante2 = j; //2. Kante ist Kante der Ausgangsstelle

        						} else if((indexKante1 == -1) && (indexKante2 != -1)) {
        							indexKante1 = j; //2. Kante ist Kante der Eingangsstelle
        						}
        					}
        				}
        				
        				//wenn Doppelkante, dann prüfen welche Kante gelöscht werden soll
        				if(doppelkante && (indexKante1 != -1) && (indexKante2 != -1)) {
        					
        					//Koordinaten der Kantenspitze der Kante der Eingangsstelle ermitteln
        					model.berechneKante(model.Kanten_Liste.get(indexKante1).xAnfang,
        							model.Kanten_Liste.get(indexKante1).yAnfang,
        							model.Kanten_Liste.get(indexKante1).xEnde,
        							model.Kanten_Liste.get(indexKante1).yEnde,
        							model.Kanten_Liste.get(indexKante1));
        					int xind1 = model.xend; //x-Koordinate von Ende der Kante der Eingangsstelle
        					int yind1 = model.yend; //y-koordinate von Ende der Kante der Eingangsstelle
        					
        					//Koordinaten der Kantenspitze der Kante der Ausgangsstelle ermitteln
        					model.berechneKante(model.Kanten_Liste.get(indexKante2).xAnfang,
        							model.Kanten_Liste.get(indexKante2).yAnfang,
        							model.Kanten_Liste.get(indexKante2).xEnde,
        							model.Kanten_Liste.get(indexKante2).yEnde,
        							model.Kanten_Liste.get(indexKante2));
        					int xind2 = model.xend; //x-Koordinate von Ende der Kante der Ausgangsstelle
        					int yind2 = model.yend; //y-Koordinate von Ende der Kante der Ausgangsstelle
        					
        					//Abstände jeweils zwischen den Kantenspitzen von Kante der Eingangs- und Ausgangsstelle
        					//und den Koordinaten des MouseEvents
        					int distxKante1 = Math.abs(x - xind1);
            				int distyKante1 = Math.abs(y - yind1);
            				int distxKante2 = Math.abs(x - xind2);
            				int distyKante2 = Math.abs(y - yind2);			
            				
            				//die Kantenspitze welche geringere Entfernung zum MouseEvent hat, wird später gelöscht            				
            				if((distxKante1 < distxKante2) && (distyKante1 < distyKante2)) {
            					indexKante2 = -1; 
            					doppelkante = false;
            					
            				}            				
            				else if((distxKante1 > distxKante2) && (distyKante1 > distyKante2)) {
            					indexKante1 = -1;
            					doppelkante = false;            					
            				}
        				}
        				
        				//prüfen ob Kante der Ausgangsstelle gelöscht werden soll 
        				if((!doppelkante) && (indexKante1 == -1) && (indexKante2 != -1)) {        					
        					model.Kanten_Liste.remove(indexKante2); 
        					repaint();	
        				}
        				
        				//prüfen ob Kante der Eingangsstelle gelöscht werden soll
        				else if((!doppelkante) && (indexKante1 != -1) && (indexKante2 == -1)) {
        					
        					//weitere Eingangsstellen der Transition zwischenspeichern
        					for(int k=0; k < model.Kanten_Liste.size(); k++) {
        						if((model.Kanten_Liste.get(k).index_Trans == indexTrans) &&
        								model.Kanten_Liste.get(k).von_Stelle &&
        								(indexKante1 != k)){
        							stellenindex.addLast(model.Kanten_Liste.get(k).index_Stelle);
        						}        						
        					}
        					//prüfen ob alle restlichen Eingangsstellen Marken enthalten
        					boolean aktiv = true;
        					for(int k=0; k < stellenindex.size(); k++) {
        						if(model.Stellen_Liste.get(stellenindex.get(k)).anzahl_Marken == 0) {
        							aktiv = false;
        						}
        					}
        					//wenn alle restlichen Eingangsstellen Marken enthalten, wird die Transition aktiviert
        					if(aktiv) {
        						model.Trans_Liste.get(model.Kanten_Liste.get(indexKante1).index_Trans).trans_aktiv = true;
        					}
        					//wenn mind. eine Eingangsstelle keine Marken enthält, wird die Transition deaktiviert
        					else {
        						model.Trans_Liste.get(model.Kanten_Liste.get(indexKante1).index_Trans).trans_aktiv = false;
        					}
        					model.Kanten_Liste.remove(indexKante1);  //Kante wird gelöscht
        					repaint();	
        				}                   		
            	}            		
        	}           		
        } 	    		     

    	 
    	
    	 ////////////////////mehrere Elemente werden zum Löschen gekennzeichnet////////////////////////
    	 
    	
    	 if(model.getLoeschKenn()&& !e.isMetaDown()) {
    		 //zu löschende Transitionen kennzeichnen
    		 for(int i=0; i < model.Trans_Liste.size(); i++) {
    			 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x, y, model.Trans_Liste.get(i)) ) { 
    				model.Trans_Liste.get(i).loesch_kenn = true;  
    				//alle Kanten dieser Transition auch kennzeichnen
    				for(int j=0; j < model.Kanten_Liste.size(); j++) {
    					if(model.Kanten_Liste.get(j).index_Trans == i) {
    						model.Kanten_Liste.get(j).loesch_kenn = true;
    					}
    				}
    			 }   
    		 }
    		 //zu löschende Stellen kennzeichnen
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i)) ) {
    				 model.Stellen_Liste.get(i).loesch_kenn = true;    
    				 //alle Kanten dieser Stelle auch kennzeichnen
    				 for(int j=0; j < model.Kanten_Liste.size(); j++) {
      					if(model.Kanten_Liste.get(j).index_Stelle == i) {
      						model.Kanten_Liste.get(j).loesch_kenn = true;
      					}
      				}
    			 }
    		 } 
    		 
    		 //zu löschende Kanten kennzeichnen   
    		 int indTr = -1;
    		 int indSt = -1;
    		 boolean doppelk = false;
    		 int indKante1 = -1;
    		 int indKante2 = -1;
    		 
    		 for(int i=0; i < model.Kanten_Liste.size(); i++) {
    			 if(kantePressed(x, y, model.Kanten_Liste.get(i))) {
    				 indTr = model.Kanten_Liste.get(i).index_Trans;
    				 indSt = model.Kanten_Liste.get(i).index_Stelle;
    				 
    				 //Index festhalten je nachdem ob Kante der Eingangsstelle oder Ausgangsstelle
    				 if(model.Kanten_Liste.get(i).von_Stelle) {
    					 indKante1 = i;    					 
    				 } else if(model.Kanten_Liste.get(i).zu_Stelle) {
    					 indKante2 = i;    					 
    				 }
    				 
    				 //prüfen ob zweite Kante zwischen dieser Transition und Stelle existiert
    				 for(int j=0; j < model.Kanten_Liste.size(); j++) {
    					 //wenn ja, zweiten Index festhalten, je nachdem ob Kante der Eingangs- oder Ausgangsstelle
    					 if((model.Kanten_Liste.get(j).index_Trans == indTr) &&
    							 (model.Kanten_Liste.get(j).index_Stelle == indSt) &&
    							 (i != j)) {
    						 doppelk = true;
    						 if((indKante1 != -1) && (indKante2 == -1)) {    							 
    							 indKante2 = j;    							
    						 } else if((indKante1 == -1) && (indKante2 != -1)) {
    							 indKante1 = j;    							
    						 }
    					 }
    				 }
    				 //prüfen wenn Doppelkante, welche Kante gelöscht werden soll
    				 if(doppelk && (indKante1 != -1) && (indKante2 != -1)) {
    					 //Koordinaten der Kantenspitze der Kante der Eingangsstelle ermitteln
    					 model.berechneKante(model.Kanten_Liste.get(indKante1).xAnfang,
     							model.Kanten_Liste.get(indKante1).yAnfang,
     							model.Kanten_Liste.get(indKante1).xEnde,
     							model.Kanten_Liste.get(indKante1).yEnde,
     							model.Kanten_Liste.get(indKante1));
    					 int xind1 = model.xend;
    					 int yind1 = model.yend;
    					 
    					 //Koordinaten der Kantenspitze von Kante der Ausgangsstelle ermitteln
    					 model.berechneKante(model.Kanten_Liste.get(indKante2).xAnfang,
     							model.Kanten_Liste.get(indKante2).yAnfang,
     							model.Kanten_Liste.get(indKante2).xEnde,
     							model.Kanten_Liste.get(indKante2).yEnde,
     							model.Kanten_Liste.get(indKante2));
     					int xind2 = model.xend;
     					int yind2 = model.yend;
     					
     					//Abstände jeweils zwischen Kantenspitzen von Kante der Eingangs- und 
     					//Ausgangsstelle und des MouseEvents ermitteln
    					int distxKante1 = Math.abs(x - xind1);
        				int distyKante1 = Math.abs(y - yind1);
        				int distxKante2 = Math.abs(x - xind2);
        				int distyKante2 = Math.abs(y - yind2);
        				
        				//prüfen ob Eingangsstelle gelöscht werden soll
        				if((distxKante1 < distxKante2) && (distyKante1 < distyKante2)) {
        					model.Kanten_Liste.get(indKante1).loesch_kenn = true;
        					System.out.println("löschenkenn "+indKante1);
        					indKante2 = -1;
        					doppelk = false;
        					
        				}
        				//prüfen ob Ausgangsstelle gelöscht werden soll
        				else if((distxKante1 > distxKante2) && (distyKante1 > distyKante2)) {
        					model.Kanten_Liste.get(indKante2).loesch_kenn = true;
        					System.out.println("löschenkenn "+indKante2);
        					indKante1 = -1;
        					doppelk = false;        					
        				}
    				 } else {
    					 model.Kanten_Liste.get(i).loesch_kenn = true;
    				 }   				 
    			 }
    		 }     		 
    		 repaint();
    	 } 	 
    	 ///gelöscht wird in Klasse Fenster///////////

    	 
    	 
    	 
    	 
    	 //////////////////////Namen////////////////////////////////
    	    	
    	 //Namen zu ausgewählter Transition oder Stelle setzen
    	 if(model.getNamen()&& !e.isMetaDown()) {
    		//testen ob Name einer Transition gesetzt werden soll
    		 for(int i=0; i < model.Trans_Liste.size(); i++) {    			
    			 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x, y, model.Trans_Liste.get(i))) {    			
    				String t = JOptionPane.showInputDialog("Bitte Namen eingeben");
    				if(t != null) {
    					model.Trans_Liste.get(i).text = t;   			
       				 repaint();
    				}     				
    			 }
    		 } 
    		 //testen ob Name einer Stelle gesetzt werden soll
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {    			
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x, y, model.Stellen_Liste.get(i))) {    				 
    				 String tt = JOptionPane.showInputDialog("Bitte Namen eingeben");
    				 if(tt != null) {
    					 model.Stellen_Liste.get(i).tex = tt;      				
    	    			 repaint();
    				 }     				
    			 }
    		 }      
    	 }
     }
     
     /**
      * prüft ob Zeichenfläche vergrößert werden muß, wenn gerade 1 Element
      * verschoben wird
      * @param x  x-Position des Elements welches gerade verschoben wird
      * @param y  y-Position des Elements welches gerade verschoben wird
      */
     public void zeichenfGroesse1(int x, int y) {
    	//prüfen ob Zeichenfeld verbreitert werden muß
		  if(x - xOffset > panelWidth) {  
			  model.setEditorWidth(x - xOffset + model.getEditorWidth());  
			  einstell = new Einstellungen(this, model, false);
			  einstell.grOK.doClick();
			  einstell.dispose();
			  this.resize(model.getEditorWidth(), model.getEditorHeight());
		  }
		  //prüfen ob Zeichenfeld verlängert werden muß
		  if(y - yOffset > panelHeight) {
			  model.setEditorHeight(y - yOffset + model.getEditorHeight());
			  einstell = new Einstellungen(this, model, false);
			  einstell.grOK.doClick();
			  einstell.dispose();
			  this.resize(model.getEditorWidth(), model.getEditorHeight());
		  }
     }
     
     /**
      * prüft ob Zeichenfläche vergrößert werden muß, wenn gerade mehrere Elemente
      * gleichzeitig verschoben werden
      * @param x  x-Position des Elements welches gerade verschoben wird
      * @param y  y-Position des Elements welches gerade verschoben wird
      * @param abX  x-Abstand zum Startelement der Verschiebung
      * @param abY  y-Abstand zum Startelement der Verschiebung
      */
     public void zeichenfGroesse2(int x, int y, int abX, int abY) {
			  //prüfen ob Zeichenfläche verbreitert werden muß
			  if(x - abX - xOffset > panelWidth) {
				model.setEditorWidth(x - xOffset + model.getEditorWidth());    					 
			    einstell = new Einstellungen(this, model, false);
				einstell.grOK.doClick();
				einstell.dispose();
				this.resize(model.getEditorWidth(), model.getEditorHeight());
			  }
			  
			  //prüfen ob Zeichenfläche verlängert werden muß
			  if(y - abY - yOffset > panelHeight) {
				model.setEditorHeight(y - yOffset + model.getEditorHeight());
			    einstell = new Einstellungen(this, model, false);
				einstell.grOK.doClick();
				einstell.dispose();
				this.resize(model.getEditorWidth(), model.getEditorHeight());
			  }	 	
     }
    
     /**
      * Wenn momentan eine Kante gezogen wird, dann werden hier deren aktuelle Koordinaten
      * berechnet und durch repaint-Aufruf im Petrinetz aktualisiert.
      * Wenn momentan Netzelemente verschoben werden, dann werden hier deren aktuelle
      * Koordinaten bestimmt und durch repaint-Aufruf im Netz aktualisiert.      
      */
      public void mouseDragged(MouseEvent e) {
    	  
    	  //wenn gerade eine Kante gezogen wird
    	  if(model.getKante()) {
    		  int xZieh = e.getX();
    		  int yZieh = e.getY();
    		  berechneZugKante(x, y, xZieh, yZieh);  	    		  
    		  repaint();
    	  }
    	  
    	  
    	  
    	  //wenn gerade ein Element verschoben wird
    	  if(model.getVerschieben() && !e.isMetaDown()) {   
    		  LinkedList<Integer> z_stelle = new LinkedList(); //Kanten-Index wenn Kante zur Stelle läuft
    		  LinkedList<Integer> v_stelle = new LinkedList(); //Kanten-Index wenn Kante von Stelle wegläuft
    		  LinkedList<Integer> z_trans = new LinkedList(); //Kanten-Index wenn Kante zur Transition läuft
    		  LinkedList<Integer> v_trans = new LinkedList(); //Kanten-Index wenn Kante von Transition wegläuft
    		  
    		  	  //wenn gerade eine Transition verschoben wird
    			  if(merke != -1 && schiebtTransition) { 
    				  
    				  zeichenfGroesse1(e.getX(), e.getY());	//bei Bedarf wird Zeichenfläche vergrößert
    				  
    				  //neue Position der Transition festlegen, wobei Position x und y mind. 0 haben muß    				  
    				  if((e.getX() - xOffset >= 0 && e.getY() - yOffset >= 0) ||
    						  e.getX() - xOffset < 0 || e.getY() - yOffset < 0) {
    					      					  
    					  if(e.getX() - xOffset >= 0 && e.getY() - yOffset >=0) {    						  
    						  model.Trans_Liste.set(merke,
     	    	    				 (new Transition( e.getX() - xOffset, e.getY() - yOffset, 
     	    	    					 model.Trans_Liste.get(merke).trans_laenge, 
     	    	    					 model.Trans_Liste.get(merke).trans_breite, 
     	    	    					 Color.black, 
     	    	    					 model.Trans_Liste.get(merke).text,
     	    	    					 model.Trans_Liste.get(merke).trans_aktiv,
     	    	    					 model.Trans_Liste.get(merke).versch_kenn,
     	    	    					 model.Trans_Liste.get(merke).loesch_kenn,
     	    	    					 model.Trans_Liste.get(merke).geloescht)));  						  
    					  
    					  }
    					  //xKoordinate wird 0 damit Transition nicht über die Zeichenfläche nach links hinausgeschoben wird	 
    					  else if(e.getX() - xOffset < 0 && e.getY() - yOffset >= 0) {    						  
    						  model.Trans_Liste.set(merke,
     	    	    				 (new Transition( 0, e.getY() - yOffset, 
     	    	    					 model.Trans_Liste.get(merke).trans_laenge, 
     	    	    					 model.Trans_Liste.get(merke).trans_breite, 
     	    	    					 Color.black, 
     	    	    					 model.Trans_Liste.get(merke).text,
     	    	    					 model.Trans_Liste.get(merke).trans_aktiv,
     	    	    					 model.Trans_Liste.get(merke).versch_kenn,
     	    	    					 model.Trans_Liste.get(merke).loesch_kenn,
     	    	    					 model.Trans_Liste.get(merke).geloescht)));
    						  
    					  }
    					  //yKoordinate wird 0 damit Transition nicht über die Zeichenfläche nach unten hinausgeschoben wird
    					  else if(e.getX() - xOffset >= 0 && e.getY() - yOffset < 0) {
    						  model.Trans_Liste.set(merke,
     	    	    				 (new Transition( e.getX() - xOffset, 0, 
     	    	    					 model.Trans_Liste.get(merke).trans_laenge, 
     	    	    					 model.Trans_Liste.get(merke).trans_breite, 
     	    	    					 Color.black, 
     	    	    					 model.Trans_Liste.get(merke).text,
     	    	    					 model.Trans_Liste.get(merke).trans_aktiv,
     	    	    					 model.Trans_Liste.get(merke).versch_kenn,
     	    	    					 model.Trans_Liste.get(merke).loesch_kenn,
     	    	    					 model.Trans_Liste.get(merke).geloescht)));
    					  }      					  
    	    					  
    					  //Kanten speichern welche zur Transition hin- oder wegführen
    					  for(int i=0; i < model.Kanten_Liste.size(); i++) {        					 
    	        		  	  if(model.Kanten_Liste.get(i).von_Trans &&
    	        					model.Kanten_Liste.get(i).zu_Stelle &&
    	        					model.Kanten_Liste.get(i).index_Trans == merke) {
    	        				  v_trans.add(i);    
    	        			  } else if(model.Kanten_Liste.get(i).zu_Trans &&
    	        					model.Kanten_Liste.get(i).von_Stelle &&
    	        					model.Kanten_Liste.get(i).index_Trans == merke) {
    	        				  z_trans.add(i);      
    	        			  }
    	        		   }
    	    					  
    					  //Kanten welche von der Transition wegführen werden verlängert
    					  for(int j=0; j < v_trans.size(); j++) {
    	    	    		 int in = v_trans.get(j);
    	    	    		 model.Kanten_Liste.set(in, new Kante(
    	    	    				 model.Trans_Liste.get(merke).trans_x_kor+(model.groesseWidth/2),
    	    	    				 model.Trans_Liste.get(merke).trans_y_kor+(model.groesseHeight/2),
    	    	    				 model.Kanten_Liste.get(in).xEnde,
    	    	    				 model.Kanten_Liste.get(in).yEnde,
    	    	    				 model.Kanten_Liste.get(in).color,
    	    	    				 model.Kanten_Liste.get(in).von_Trans,
    	    	    				 model.Kanten_Liste.get(in).von_Stelle,
    	    	    				 model.Kanten_Liste.get(in).zu_Trans,
    	    	    				 model.Kanten_Liste.get(in).zu_Stelle,
    	    	    				 model.Kanten_Liste.get(in).index_Trans,
    	    	    				 model.Kanten_Liste.get(in).index_Stelle,
    	    	    				 model.Kanten_Liste.get(in).loesch_kenn));
    					  }
    	    	    				 
    	    	    	  //Kanten welche zur Transition hinführen werden verlängert
    	    	    		for(int k=0; k < z_trans.size(); k++) {
    	    	    		 int ind = z_trans.get(k);    	    					
    	    	    		 model.Kanten_Liste.set(ind, new Kante(
    	    	    				 model.Kanten_Liste.get(ind).xAnfang,
    	    	    				 model.Kanten_Liste.get(ind).yAnfang,
    	    	    				 model.Trans_Liste.get(merke).trans_x_kor+(model.groesseWidth/2),
    	    	    				 model.Trans_Liste.get(merke).trans_y_kor+(model.groesseHeight/2),
    	    	    				 model.Kanten_Liste.get(ind).color,
    	    	    				 model.Kanten_Liste.get(ind).von_Trans,
    	    	    				 model.Kanten_Liste.get(ind).von_Stelle,
    	    	    				 model.Kanten_Liste.get(ind).zu_Trans,
    	    	    				 model.Kanten_Liste.get(ind).zu_Stelle,
    	    	    				 model.Kanten_Liste.get(ind).index_Trans,
    	    	    				 model.Kanten_Liste.get(ind).index_Stelle,
    	    	    				 model.Kanten_Liste.get(ind).loesch_kenn));    					 
    	    	    	 }       				
    	    	       	 repaint();	  
    				  }    				   
    			  } 
    			  
    			  //wenn gerade Stelle verschoben wird
    			  if(merke != -1 && schiebtStelle) {
    				  
    				  zeichenfGroesse1(e.getX(), e.getY()); //bei Bedarf wird Zeichenfläche vergrößert   				  

    				  //neue Position der Stelle festlegen, wobei Position x und y mind. 0 haben muß
    				  if((e.getX() - xOffset >= 0 && e.getY() - yOffset >= 0) ||
    						  e.getX() - xOffset < 0 || e.getY() - yOffset < 0) {
    					  
    					  if(e.getX() - xOffset >= 0 && e.getY() - yOffset >= 0) {
    						  model.Stellen_Liste.set(merke, new Stelle(e.getX() - xOffset,
        							  e.getY() - yOffset,
        							  model.Stellen_Liste.get(merke).stelle_laenge,
        							  model.Stellen_Liste.get(merke).stelle_breite,
        							  model.Stellen_Liste.get(merke).anzahl_Marken,
        							  model.Stellen_Liste.get(merke).stelle_color,
        							  model.Stellen_Liste.get(merke).tex,
        							  model.Stellen_Liste.get(merke).versch_kenn,
        							  model.Stellen_Liste.get(merke).loesch_kenn,
        							  model.Stellen_Liste.get(merke).geloescht)); 
    					  }
    					  //xKoordinate wird 0, damit Stelle nicht über Zeichenfläche nach links hinausgeschoben wird
    					  else if (e.getX() - xOffset < 0 && e.getY() - yOffset >= 0) {
    						  model.Stellen_Liste.set(merke, new Stelle(0,
        							  e.getY() - yOffset,
        							  model.Stellen_Liste.get(merke).stelle_laenge,
        							  model.Stellen_Liste.get(merke).stelle_breite,
        							  model.Stellen_Liste.get(merke).anzahl_Marken,
        							  model.Stellen_Liste.get(merke).stelle_color,
        							  model.Stellen_Liste.get(merke).tex,
        							  model.Stellen_Liste.get(merke).versch_kenn,
        							  model.Stellen_Liste.get(merke).loesch_kenn,
        							  model.Stellen_Liste.get(merke).geloescht));
    					  }
    					  //yKoordinate wird 0, damit Stelle nicht über Zeichenfläche nach unten hinausgeschoben wird
    					  else if (e.getX() - xOffset >= 0 && e.getY() - yOffset < 0){
    						  model.Stellen_Liste.set(merke, new Stelle(e.getX() - xOffset,
        							  0,
        							  model.Stellen_Liste.get(merke).stelle_laenge,
        							  model.Stellen_Liste.get(merke).stelle_breite,
        							  model.Stellen_Liste.get(merke).anzahl_Marken,
        							  model.Stellen_Liste.get(merke).stelle_color,
        							  model.Stellen_Liste.get(merke).tex,
        							  model.Stellen_Liste.get(merke).versch_kenn,
        							  model.Stellen_Liste.get(merke).loesch_kenn,
        							  model.Stellen_Liste.get(merke).geloescht));
    					  }
    					  
    					  
    					//Kanten speichern, welche zur Stelle hin- oder wegführen
    					  for(int i=0; i < model.Kanten_Liste.size(); i++) {        					 
        					  if(model.Kanten_Liste.get(i).von_Stelle &&
        							model.Kanten_Liste.get(i).zu_Trans &&
        							model.Kanten_Liste.get(i).index_Stelle == merke) {
        						  v_stelle.add(i);    
        					  } else if(model.Kanten_Liste.get(i).zu_Stelle &&
        							model.Kanten_Liste.get(i).von_Trans &&
        							model.Kanten_Liste.get(i).index_Stelle == merke) {
        						  z_stelle.add(i);      
        					  }
        				  }	   					 
    	     				 
    	     			  //Kanten welche von der Stelle wegführen werden verlängert
    					  for(int j=0; j < v_stelle.size(); j++) {
    	    					 int in = v_stelle.get(j);
    	    					 model.Kanten_Liste.set(in, new Kante(
    	    							 model.Stellen_Liste.get(merke).stelle_x_kor+(model.groesseWidth/2),
    	    							 model.Stellen_Liste.get(merke).stelle_y_kor+(model.groesseHeight/2),
    	    							 model.Kanten_Liste.get(in).xEnde,
    	    							 model.Kanten_Liste.get(in).yEnde,
    	    							 model.Kanten_Liste.get(in).color,
    	    							 model.Kanten_Liste.get(in).von_Trans,
    	    							 model.Kanten_Liste.get(in).von_Stelle,
    	    							 model.Kanten_Liste.get(in).zu_Trans,
    	    							 model.Kanten_Liste.get(in).zu_Stelle,
    	    							 model.Kanten_Liste.get(in).index_Trans,
    	    							 model.Kanten_Liste.get(in).index_Stelle,
    	    							 model.Kanten_Liste.get(in).loesch_kenn));
    	    			 }
    					  
    	    			 //Kanten welche zur Stelle hinführen werden verlängert
    	    			 for(int k=0; k < z_stelle.size(); k++) {
    	    					 int ind = z_stelle.get(k);    	    					
    	    					 model.Kanten_Liste.set(ind, new Kante(
    	    							 model.Kanten_Liste.get(ind).xAnfang,
    	    							 model.Kanten_Liste.get(ind).yAnfang,
    	    							 model.Stellen_Liste.get(merke).stelle_x_kor+(model.groesseWidth/2),
    	    							 model.Stellen_Liste.get(merke).stelle_y_kor+(model.groesseHeight/2),
    	    							 model.Kanten_Liste.get(ind).color,
    	    							 model.Kanten_Liste.get(ind).von_Trans,
    	    							 model.Kanten_Liste.get(ind).von_Stelle,
    	    							 model.Kanten_Liste.get(ind).zu_Trans,
    	    							 model.Kanten_Liste.get(ind).zu_Stelle,
    	    							 model.Kanten_Liste.get(ind).index_Trans,
    	    							 model.Kanten_Liste.get(ind).index_Stelle,
    	    							 model.Kanten_Liste.get(ind).loesch_kenn));    					 
    	    			  } 	     				 
    	        		  repaint(); 
    				  }	 
    				     				 
     			  }    	  
    	  } 
    	  //wenn mehrere Elemente parallel verschoben werden
    	  else if (model.getMehrVerschieben()) {
    		  
    		  LinkedList<Integer> z_stelle = new LinkedList(); //Kanten-Index wenn Kante zur Stelle läuft
    		  LinkedList<Integer> v_stelle = new LinkedList(); //Kanten-Index wenn Kante von Stelle wegläuft
    		  LinkedList<Integer> z_trans = new LinkedList(); //Kanten-Index wenn Kante zur Transition läuft
    		  LinkedList<Integer> v_trans = new LinkedList(); //Kanten-Index wenn Kante von Transition wegläuft
    		  
    		  for(int p=0; p < model.VerschElem_Liste.size(); p++) {
    			  
    			  //testen ob Transition mitverschoben wird	
    			  if(model.VerschElem_Liste.get(p).Sorte == 1) {
    				  int abstandX = model.VerschElem_Liste.get(p).AbstandX;
  	 				  int abstandY = model.VerschElem_Liste.get(p).AbstandY;  	 				
  	 				  int indT = model.VerschElem_Liste.get(p).Pos;
  	 				  
  	 				zeichenfGroesse2(e.getX(), e.getY(), abstandX, abstandY);  	//bei Bedarf wird Zeichenfläche vergrößert 				  
  	 				  
  	 				  if(((e.getX() - abstandX - xOffset) >= 0 && (e.getY() - abstandY - yOffset) >= 0)||
  	 						  e.getX() - abstandX - xOffset < 0 || e.getY() - abstandY - yOffset < 0) {
  	 					  
  	 					  if((e.getX() - abstandX - xOffset) >= 0 && (e.getY() - abstandY - yOffset >= 0)) {
  	 						model.Trans_Liste.set(model.VerschElem_Liste.get(p).Pos,
  	  	  							 (new Transition(e.getX()- abstandX - xOffset, 
  	  	  							e.getY()  - abstandY - yOffset, 
  	  	  							 model.Trans_Liste.get(indT).trans_laenge, 
  	  	  							 model.Trans_Liste.get(indT).trans_breite, 
  	  	  							 model.Trans_Liste.get(indT).trans_color, 
  	  	  							 model.Trans_Liste.get(indT).text,
  	  	  							 model.Trans_Liste.get(indT).trans_aktiv,
  	  	  							 true,
  	  	  							 model.Trans_Liste.get(indT).loesch_kenn,
  	  	  							model.Trans_Liste.get(indT).geloescht)));  
  	 					  }
  	 					  //xKoordinate wird 0, damit Elemente nicht über Zeichenfläche nach links hinausgeschoben werden
  	 					  else if(e.getX() - abstandX - xOffset < 0 && e.getY() - abstandY - yOffset >= 0){
  	 						model.Trans_Liste.set(model.VerschElem_Liste.get(p).Pos,
 	  	  							 (new Transition(0, 
 	  	  							e.getY()  - abstandY - yOffset, 
 	  	  							 model.Trans_Liste.get(indT).trans_laenge, 
 	  	  							 model.Trans_Liste.get(indT).trans_breite, 
 	  	  							 model.Trans_Liste.get(indT).trans_color, 
 	  	  							 model.Trans_Liste.get(indT).text,
 	  	  							 model.Trans_Liste.get(indT).trans_aktiv,
 	  	  							 true,
 	  	  							 model.Trans_Liste.get(indT).loesch_kenn,
 	  	  							model.Trans_Liste.get(indT).geloescht)));  
  	 					  }
  	 					 //yKoordinate wird 0, damit Elemente nicht über Zeichenfläche nach unten hinausgeschoben werden
  	 					  else if (e.getX() - abstandX - xOffset >= 0 && e.getY() - abstandY - yOffset < 0) {
  	 						model.Trans_Liste.set(model.VerschElem_Liste.get(p).Pos,
 	  	  							 (new Transition(e.getX()- abstandX - xOffset, 
 	  	  							 0, 
 	  	  							 model.Trans_Liste.get(indT).trans_laenge, 
 	  	  							 model.Trans_Liste.get(indT).trans_breite, 
 	  	  							 model.Trans_Liste.get(indT).trans_color, 
 	  	  							 model.Trans_Liste.get(indT).text,
 	  	  							 model.Trans_Liste.get(indT).trans_aktiv,
 	  	  							 true,
 	  	  							 model.Trans_Liste.get(indT).loesch_kenn,
 	  	  							model.Trans_Liste.get(indT).geloescht)));  
  	 					  }
  	 					   	 				  
  	 					//Indices der Kanten zwischenspeichern  
  	  	 				for(int r=0; r < model.Kanten_Liste.size(); r++) {
  	  	 					//wenn Kante an Transition beginnt
  	  	 					if(model.Kanten_Liste.get(r).von_Trans && 
  	  	 							model.Kanten_Liste.get(r).zu_Stelle &&
  	  	 							model.Kanten_Liste.get(r).index_Trans == 
  	  	 							model.VerschElem_Liste.get(p).Pos ) {  	 						
  	  	 						v_trans.add(r);  						
  	  	 					} 
  	  	 					//wenn Kante an Transition endet
  	  	 					else if(model.Kanten_Liste.get(r).zu_Trans &&
  	  	 							model.Kanten_Liste.get(r).von_Stelle &&
  	  	 							model.Kanten_Liste.get(r).index_Trans == 
  	  	 							model.VerschElem_Liste.get(p).Pos 
  	  	 							) {  	 						
  	  	 						z_trans.add(r);  	 						
  	  	 					}
  	  	 				}
  	  	 				//Kanten welche von Transition wegführen werden verlängert
  	  	 				if(v_trans.size() > 0) {
  	  	 					for(int s=0; s < v_trans.size(); s++) {
  	  	 						int in = v_trans.get(s);
  	  	 						int indexT = model.VerschElem_Liste.get(p).Pos;
  	  	 						model.Kanten_Liste.set(in, new Kante(
  	  	 								model.Trans_Liste.get(indexT).trans_x_kor+(model.groesseWidth/2),
  	  	 								model.Trans_Liste.get(indexT).trans_y_kor+(model.groesseHeight/2),
  	  	 								model.Kanten_Liste.get(in).xEnde,
  	  	 								model.Kanten_Liste.get(in).yEnde,
  	  	 								model.Kanten_Liste.get(in).color,
  	  	 								model.Kanten_Liste.get(in).von_Trans,
  	  	 								model.Kanten_Liste.get(in).von_Stelle,
  	  	 								model.Kanten_Liste.get(in).zu_Trans,
  	  	 								model.Kanten_Liste.get(in).zu_Stelle,
  	  	 								model.Kanten_Liste.get(in).index_Trans,
  	  	 								model.Kanten_Liste.get(in).index_Stelle,
  	  	 								model.Kanten_Liste.get(in).loesch_kenn));
  	  	 					}
  	  	 					v_trans.clear();
  	  	 				}
  	  	 				//Kanten welche zur Transition hinführen werden verlängert
  	  	 				if(z_trans.size() > 0) {
  	  	 					for(int s=0; s < z_trans.size(); s++) {
  	  	 						int in = z_trans.get(s);
  	  	 						int indexTT = model.VerschElem_Liste.get(p).Pos;
  	  	 						model.Kanten_Liste.set(in, new Kante(
  	  	 								model.Kanten_Liste.get(in).xAnfang,
  	  	 								model.Kanten_Liste.get(in).yAnfang,
  	  	 								model.Trans_Liste.get(indexTT).trans_x_kor+(model.groesseWidth/2),
  	  	 								model.Trans_Liste.get(indexTT).trans_y_kor+(model.groesseHeight/2),
  	  	 								model.Kanten_Liste.get(in).color,
  	  	 								model.Kanten_Liste.get(in).von_Trans,
  	  	 								model.Kanten_Liste.get(in).von_Stelle,
  	  	 								model.Kanten_Liste.get(in).zu_Trans,
  	  	 								model.Kanten_Liste.get(in).zu_Stelle,
  	  	 								model.Kanten_Liste.get(in).index_Trans,
  	  	 								model.Kanten_Liste.get(in).index_Stelle,
  	  	 								model.Kanten_Liste.get(in).loesch_kenn));  	 					
  	  	 					}
  	  	 					z_trans.clear();
  	  	 				}  	 					
  	 				}  	 			  
    			  }	
    			  //testen ob Stelle mitverschoben wird
    			  else if(model.VerschElem_Liste.get(p).Sorte == 2) {
    				  	int abstandX = model.VerschElem_Liste.get(p).AbstandX;
    	 				int abstandY = model.VerschElem_Liste.get(p).AbstandY;
    	 				int indS = model.VerschElem_Liste.get(p).Pos;
    	 				
    	 				zeichenfGroesse2(e.getX(), e.getY(), abstandX, abstandY);    //bei Bedarf wird Zeichenfläche vergrößert	 	
    	 				  
    	 				if((e.getX() - abstandX - xOffset) >= 0 && (e.getY() - abstandY - yOffset) >= 0 ||
    	 						e.getX() - abstandX - xOffset <0 || e.getY() - abstandY - yOffset < 0) {
    	 					if((e.getX() - abstandX - xOffset >=0) && (e.getY() - abstandY - yOffset >= 0)) {
    	 						model.Stellen_Liste.set(model.VerschElem_Liste.get(p).Pos,
            	 						new Stelle(e.getX() - abstandX - xOffset,
            	 								e.getY() - abstandY - yOffset,
            	 								model.Stellen_Liste.get(indS).stelle_laenge,
            	 								model.Stellen_Liste.get(indS).stelle_breite,
            	 								model.Stellen_Liste.get(indS).anzahl_Marken,
            	 								model.Stellen_Liste.get(indS).stelle_color,
            	 								model.Stellen_Liste.get(indS).tex,
            	 								true,
            	 								model.Stellen_Liste.get(indS).loesch_kenn,
            	 								model.Stellen_Liste.get(indS).geloescht));
    	 					}
    	 					 //xKoordinate wird 0, damit Elemente nicht über Zeichenfläche nach links hinausgeschoben werden
    	 					else if((e.getX() - abstandX - xOffset) < 0 && (e.getY() - abstandY - yOffset >= 0)) {
    	 						model.Stellen_Liste.set(model.VerschElem_Liste.get(p).Pos,
            	 						new Stelle(0,
            	 								e.getY() - abstandY - yOffset,
            	 								model.Stellen_Liste.get(indS).stelle_laenge,
            	 								model.Stellen_Liste.get(indS).stelle_breite,
            	 								model.Stellen_Liste.get(indS).anzahl_Marken,
            	 								model.Stellen_Liste.get(indS).stelle_color,
            	 								model.Stellen_Liste.get(indS).tex,
            	 								true,
            	 								model.Stellen_Liste.get(indS).loesch_kenn,
            	 								model.Stellen_Liste.get(indS).geloescht));
    	 					}
    	 					 //yKoordinate wird 0, damit Elemente nicht über Zeichenfläche nach unten hinausgeschoben werden
    	 					else if((e.getX() - abstandX - xOffset) >= 0 && (e.getY() - abstandY - yOffset) < 0) {
    	 						model.Stellen_Liste.set(model.VerschElem_Liste.get(p).Pos,
            	 						new Stelle(e.getX() - abstandX - xOffset,
            	 								0,
            	 								model.Stellen_Liste.get(indS).stelle_laenge,
            	 								model.Stellen_Liste.get(indS).stelle_breite,
            	 								model.Stellen_Liste.get(indS).anzahl_Marken,
            	 								model.Stellen_Liste.get(indS).stelle_color,
            	 								model.Stellen_Liste.get(indS).tex,
            	 								true,
            	 								model.Stellen_Liste.get(indS).loesch_kenn,
            	 								model.Stellen_Liste.get(indS).geloescht));
    	 					}        	 				

    	 					//Indices der Kanten zwischenspeichern  
        	 				for(int r=0; r < model.Kanten_Liste.size();r++) {
        	 					//wenn Kante an Stelle beginnt
        	 					if(model.Kanten_Liste.get(r).von_Stelle && 
          	 							model.Kanten_Liste.get(r).zu_Trans &&
          	 							model.Kanten_Liste.get(r).index_Stelle == 
          	 							model.VerschElem_Liste.get(p).Pos) {
          	 						v_stelle.add(r);      	 						
          	 					} 
        	 					//wenn Kante an Stelle endet
        	 					else if(model.Kanten_Liste.get(r).zu_Stelle &&
          	 							model.Kanten_Liste.get(r).von_Trans &&
          	 							model.Kanten_Liste.get(r).index_Stelle == 
          	 							model.VerschElem_Liste.get(p).Pos) {
          	 						z_stelle.add(r);      	 						
          	 					}
        	 				}
        	 				//Kanten welche von Stelle wegführen werden verlängert
        	 				if(v_stelle.size() > 0) {
          	 					for(int s=0; s < v_stelle.size(); s++) {
          	 						int in = v_stelle.get(s);
          	 						int indexS = model.VerschElem_Liste.get(p).Pos;
          	 						model.Kanten_Liste.set(in, new Kante(
          	 								model.Stellen_Liste.get(indexS).stelle_x_kor+(model.groesseWidth/2),
          	 								model.Stellen_Liste.get(indexS).stelle_y_kor+(model.groesseHeight/2),
          	 								model.Kanten_Liste.get(in).xEnde,
          	 								model.Kanten_Liste.get(in).yEnde,
          	 								model.Kanten_Liste.get(in).color,
          	 								model.Kanten_Liste.get(in).von_Trans,
          	 								model.Kanten_Liste.get(in).von_Stelle,
          	 								model.Kanten_Liste.get(in).zu_Trans,
          	 								model.Kanten_Liste.get(in).zu_Stelle,
          	 								model.Kanten_Liste.get(in).index_Trans,
          	 								model.Kanten_Liste.get(in).index_Stelle,
          	 								model.Kanten_Liste.get(in).loesch_kenn));
          	 					}
          	 					v_stelle.clear();
          	 				}
        	 				//Kanten welche zur Stelle hinführen werden verlängert
        	 				if(z_stelle.size() > 0) {
          	 					for(int s=0; s < z_stelle.size(); s++) {
          	 						int in = z_stelle.get(s);
          	 						int indexSS = model.VerschElem_Liste.get(p).Pos;
          	 						model.Kanten_Liste.set(in, new Kante(
          	 								model.Kanten_Liste.get(in).xAnfang,
          	 								model.Kanten_Liste.get(in).yAnfang,
          	 								model.Stellen_Liste.get(indexSS).stelle_x_kor+(model.groesseWidth/2),
          	 								model.Stellen_Liste.get(indexSS).stelle_y_kor+(model.groesseHeight/2),
          	 								model.Kanten_Liste.get(in).color,
          	 								model.Kanten_Liste.get(in).von_Trans,
          	 								model.Kanten_Liste.get(in).von_Stelle,
          	 								model.Kanten_Liste.get(in).zu_Trans,
          	 								model.Kanten_Liste.get(in).zu_Stelle,
          	 								model.Kanten_Liste.get(in).index_Trans,
          	 								model.Kanten_Liste.get(in).index_Stelle,
          	 								model.Kanten_Liste.get(in).loesch_kenn));      	 					
          	 					}
          	 					z_stelle.clear();
          	 				}
    	 				}    	 				
    			  }
	 		  }    		  
    	  }
    	  repaint();
      }     

	@Override
	public void mouseClicked(MouseEvent e) {	
		//do nothing
	}
	
	
	/**
	 * Hier werden die endgültigen Koordinaten der soeben gezogenen Kante bestimmt.
	 */
	public void mouseReleased(MouseEvent e) {
		 int x1 = e.getX();
         int y1 = e.getY();         
         
         if(model.getKante()) {
        	 zugkante = false;
        	 //testen ob Kante in einer Transition endet
    		 for(int i=0; i < model.Trans_Liste.size(); i++) {    			
    			 if(!model.Trans_Liste.get(i).geloescht && transitionPressed(x1, y1, model.Trans_Liste.get(i)) &&
    					 von_St && !von_Tr) {    				
    				zu_Tr = true;
    				zu_Stelle = false;
    				index_Tr = i;
    				
    				//prüfen ob bereits Kante vorhanden
    				boolean vorhanden = false;
    				for(int j =0; j < model.Kanten_Liste.size(); j++) {
    					if(model.Kanten_Liste.get(j).von_Stelle && model.Kanten_Liste.get(j).zu_Trans &&
    							model.Kanten_Liste.get(j).index_Stelle == index_St &&
    							model.Kanten_Liste.get(j).index_Trans == index_Tr) {
    						vorhanden = true;
    					}
    				}
    				//wenn noch keine Kante vorhanden, wird diese erstellt
    				if(!vorhanden) {
    					model.Kanten_Liste.addLast(new Kante
        						(model.Stellen_Liste.get(index_St).stelle_x_kor+(model.groesseWidth/2), 
        						model.Stellen_Liste.get(index_St).stelle_y_kor+(model.groesseHeight/2), 
        						model.Trans_Liste.get(index_Tr).trans_x_kor+(model.groesseWidth/2),
        						model.Trans_Liste.get(index_Tr).trans_y_kor+(model.groesseHeight/2), 
        						Color.black, von_Tr, von_St, zu_Tr, zu_Stelle, index_Tr, index_St, false));      				
    					
    					

    					boolean akt = true; //zur Überprüfung ob Transition durch neue Kante aktiviert wird
    					
    					//prüfen ob die neue Eingangsstelle Marken besitzt
    					if(model.Stellen_Liste.get(index_St).anzahl_Marken > 0) {
    						
    						//wenn ja, werden alle anderen Eingangsstellen überprüft ob sie ebenfalls Marken besitzen    						 					
        					for(int j=0; j < model.Kanten_Liste.size(); j++) {        						
        						if((model.Kanten_Liste.get(j).von_Stelle) && 
        								(index_Tr == model.Kanten_Liste.get(j).index_Trans) &&
        								index_St != model.Kanten_Liste.get(j).index_Stelle) {
        							
        							if(model.Stellen_Liste.get(model.Kanten_Liste.get(j).index_Stelle).anzahl_Marken == 0) {
                    					akt = false;
                    				} 
        						}    						
        					}        					
    					} 
    					//wenn neue Eingangsstelle keine Marken besitzt, wird Transition deaktiviert
    					else if (model.Stellen_Liste.get(index_St).anzahl_Marken == 0) {
    						akt = false;
    					}
        				
    					//wenn Transition nur Eingangsstellen mit Marken hat, wird sie aktiviert
    					if(akt) {
    						model.Trans_Liste.get(i).trans_aktiv = true;    						
    					} else {
    						model.Trans_Liste.get(i).trans_aktiv = false;    						
    					}
    				}
    				vorhanden = false;
    			 }
    		 }
    		 
    		 //prüfen ob Kante in einer Stelle endet
    		 for(int i=0; i < model.Stellen_Liste.size(); i++) {    			
    			 if(!model.Stellen_Liste.get(i).geloescht && stellePressed(x1, y1, model.Stellen_Liste.get(i)) &&
    					 von_Tr && !von_St) {
    				 zu_Stelle = true;
    				 zu_Tr = false;
    				 index_St = i; 
    				 boolean vorhanden = false;
    				 
    				 //prüfen ob bereits Kante vorhanden
     				 for(int j =0; j < model.Kanten_Liste.size(); j++) {
     					if(model.Kanten_Liste.get(j).von_Trans && model.Kanten_Liste.get(j).zu_Stelle &&
     							model.Kanten_Liste.get(j).index_Stelle == index_St &&
     							model.Kanten_Liste.get(j).index_Trans == index_Tr) {
     						vorhanden = true;
     					}
     				 }
     				 
     				 //wenn noch keine Kante vorhanden, wird diese erzeugt
    				 if(!vorhanden) {
    					 model.Kanten_Liste.addLast(
        						 new Kante(model.Trans_Liste.get(index_Tr).trans_x_kor+(model.groesseWidth/2), 
          						model.Trans_Liste.get(index_Tr).trans_y_kor+(model.groesseHeight/2), 
          						model.Stellen_Liste.get(index_St).stelle_x_kor+(model.groesseWidth/2),
          						model.Stellen_Liste.get(index_St).stelle_y_kor+(model.groesseHeight/2), 
          						Color.black, von_Tr, von_St, zu_Tr, zu_Stelle, index_Tr, index_St, false));       				
        			  }
    				 vorhanden = false;
    			}    				
    		 }    		 
    		 von_Tr = false;
    		 von_St = false;
    		 zu_Tr = false;
    		 zu_Stelle = false;
    		 index_Tr = -1;
    		 index_St = -1;     		 
             repaint();
         }        
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// do nothing		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// do nothing		
	}
	
	
	
	
	/**
	 * Berechnet die aktuellen Koordinaten der Start- und Endpunkte der Kante
	 * welche momentan gezogen wird. Wird in der mouseDragged-Methode verwendet.
	 * 
	 * @param xAnfang  x-Koordinate des Startpunktes der Ziehkante
	 * @param yAnfang  y-Koordinate des Startpunktes der Ziehkante
	 * @param xEnde    x-Koordinate des Endpunktes der Ziehkante
	 * @param yEnde    y-Koordinate des Endpunktes der Ziehkante
	 */
	public void berechneZugKante(int xAnfang,int yAnfang, int xEnde, int yEnde) {
		zugkante = true;
		zug_xStart = xAnfang;
		zug_yStart = yAnfang;
		zug_xEnde = xEnde;
		zug_yEnde = yEnde;
		
		//Steigung berechnen
		double steiX = zug_xEnde - zug_xStart;
		double steiY = zug_yEnde - zug_yStart;
		
		//Richtung der x-Koordinate, wenn kleiner als 0, dann zeigt sie nach links
		//wenn größer als 0, dann zeigt sie nach rechts
		double dirX = steiX / (Math.sqrt((steiX*steiX) + (steiY*steiY)));
		
		//Richtung der y-Koordinate, wenn kleiner als 0, dann zeigt sie nach oben
		//wenn größer als 0, dann zeigt sie nach unten
		double dirY = steiY / (Math.sqrt((steiX*steiX) + (steiY*steiY)));			
		
		//1.Punkt der Spitze
		zug_xEnde1 = zug_xEnde;
		zug_yEnde1 = zug_yEnde;	
		
		//Endpunkt der Kante muß hinter dem 1. Punkt der Spitze liegen
		zug_xEnde = zug_xEnde - (int) (((model.groesseWidth/2))*dirX);
		zug_yEnde = zug_yEnde - (int) (((model.groesseHeight/2))*dirY);
		
		//Steigungswinkel
    	double winkA = Math.atan(steiY / steiX);
    	//Steigungswinkel in Grad
    	double winkB = (winkA/3.141592) * 180.0;
    	
    	
    	if(steiX > 0.0 && steiY > 0.0 || steiX == 0.0 && steiY == 0.0 ||
    			steiX < 0.0 && steiY <0.0) {
    		winkB = winkB + 180.0;     		
    	}    	
    	
    	if((dirY < 0.0) || (dirY == 0.0) && (dirX > 0.0) ||
    			(dirX == 0.0) && (dirY > 0.0)) {
    		winkB = winkB + 180.0;      		
    	}    	
    	
    	if(winkB > 360.0) {
    		winkB = winkB % 360.0;    		
    	}
    	
    	if(winkB < 0.0) {
    		winkB = winkB + 360.0;    		
    	}  
    	//2. Punkt für Spitze
    	winkA = (3.141592654*((winkB+20)%360.0))/180.0;
    	zug_xEnde2 = zug_xEnde + (int)((model.groesseWidth/2)*Math.cos(winkA));
    	zug_yEnde2 = zug_yEnde + (int)((model.groesseHeight/2)*Math.sin(winkA));
    	
    	//3.Punkt für Spitze
    	winkA = (3.141592654*((winkB-20)%360.0))/180.0;
    	zug_xEnde3 = zug_xEnde + (int)((model.groesseWidth/2)*Math.cos(winkA));
    	zug_yEnde3 = zug_yEnde + (int)((model.groesseHeight/2)*Math.sin(winkA));    	
	}
}
