package view_controll;




import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Model;

/**
 * Dialog-Fenster durch welches die Größe der Zeichenfläche verändert werden kann.
 * Dieses Fenster wird durch die Option *Größe der Zeichenfläche* unter Einstellungen 
 * in der Menüleiste erzeugt oder wenn ein gespeichertes Petrinetz geladen wird, dessen
 * Größe der Zeichenflaeche ungleich der Standardgröße der Zeichenfläche von 
 * 600 x 600 Pixel ist.
 * Im zweiten Fall bleibt das Dialog-Fenster jedoch unsichtbar, es werden
 * automatisch die Höhe und Breite, gemäß den geladenen Daten, angepasst. 
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */

public class Einstellungen extends JDialog implements ActionListener {
	
	/**
	 * Zeichenfläche bei welcher die Größe verändert wird
	 */
	Zeichenflaeche zeichenfl;	
	
	/**
	 * Referenz zur Klasse Model
	 */
	Model model;
	
	/**
	 * JPanel für Dialog-Fenster
	 */
	JPanel panGroesse;
	
	/**
	 * beinhaltet das "x" bei "Breite x Größe" im Dialog-Fenster
	 */
	JLabel labGroe;
	
	/**
	 * Textfeld in welches die Breite der Zeichenfläche eingegeben wird
	 */
	JTextField breite;
	
	/**
	 * Textfeld in welches die Höhe der Zeichenfläche eingegeben wird
	 */
	JTextField hoehe;
	
	/**
	 * Button mit welchem die neue Größe der Zeichenfläche bestätigt wird
	 */
	JButton grOK;
	
	/**
	 * speichert die neue Breite des Editors als String
	 */
	private String br;
	
	/**
	 * speichert die neue Höhe des Editors als String
	 */
	private String hoe;
	
	/**
	 * speichert die neue Breite des Editors als Zahl
	 */
	private int brzahl;
	
	
	/**
	 * speichert die neue Höhe des Editors als Zahl
	 */
	private int hoezahl;
	
	/**
	 * true, wenn Größe Zeichenflaeche durch Menüpunkt festgelegt wird und false
	 * wenn Petrinetz geladen wird und die abgespeicherte Größe aus der Datei bestimmt wird
	 * (nötig damit die Groesse der Zeichenflaeche des geladenen Netzes auch bei den
	 * Einstellungen sichtbar ist)
	 */
	boolean sichtbar = true;
	
	
	/**
	 * Konstruktor von Klasse Einstellungen
	 * 
	 * @param zeichenfl Petrinetz-Editor bei welchem die Größe verändert wird
	 */
	
	/**
	 * Konstruktor der Klasse Einstellungen
	 * 
	 * @param zeichenfl  Zeichenflaeche bei welcher die Groesse veraendert wird
	 * @param model      Model, in welchem die neue Groesse hinterlegt wird
	 * @param sichtbar   true, wenn JDialog Einstellungen sichtbar sein soll und
	 * 					 false wenn Groesse aus geladenem Netz automatisch
	 * 					 uebernommen wird
	 */
	public Einstellungen(Zeichenflaeche zeichenfl, Model model, boolean sichtbar) {
		this.zeichenfl = zeichenfl;		
		this.model = model;
		this.sichtbar = sichtbar;
		setSize(300, 200);
		setLayout(new GridLayout(2,1));		
		panGroesse = new JPanel();
		panGroesse.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 1), "Groesse der Zeichenflaeche (Breite x Höhe)"));
		labGroe = new JLabel("x");

		brzahl = model.getEditorWidth();
		hoezahl = model.getEditorHeight();
		
		br = Integer.toString(brzahl);
		hoe = Integer.toString(hoezahl);
		
		breite = new JTextField(br, 5);
		hoehe = new JTextField(hoe, 5);		

		breite.setEditable(true);
		hoehe.setEditable(true);
		grOK = new JButton ("bestätigen");
		grOK.setActionCommand("grOK");
		grOK.addActionListener(this);
		
		panGroesse.add(breite);		
		panGroesse.add(labGroe);
		panGroesse.add(hoehe);
		panGroesse.add(grOK);
		
		add(panGroesse);		
		setVisible(sichtbar);
	}


	/**
	 * Die eingegebene Breite und Höhe werden jeweils als String entgegen genommen und
	 * in Integer-Zahlen umgewandelt und die Zeichenflaeche erhält die neue Größe.
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
			
			br = breite.getText();
			hoe = hoehe.getText();			
			
			try {
				brzahl = new Integer(br).intValue();
				hoezahl = new Integer(hoe).intValue();
				if(brzahl <= 0 || hoezahl <= 0) {
					JOptionPane.showMessageDialog(this, "Bitte geben Sie ganze Zahlen größer 0 ein!");
					return;
				}
				zeichenfl.setPanelWidth(brzahl);
				
				
				zeichenfl.setPanelHeight(hoezahl);
				
				breite = new JTextField(br, 5);
				hoehe = new JTextField(hoe, 5);
				
				model.setEditorWidth(brzahl);
				model.setEditorHeight(hoezahl);			

				zeichenfl.resize(model.getEditorWidth(), model.getEditorHeight());
				zeichenfl.repaint();
				dispose(); //schließt Dialog-Fenster
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Bitte geben Sie ganze Zahlen größer 0 ein!");
				
			}
			
		
	}

}
