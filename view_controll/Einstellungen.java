package view_controll;




import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Model;

/**
 * Dialog-Fenster durch welches die Gr��e der Zeichenfl�che ver�ndert werden kann.
 * Dieses Fenster wird durch die Option *Gr��e der Zeichenfl�che* unter Einstellungen 
 * in der Men�leiste erzeugt oder wenn ein gespeichertes Petrinetz geladen wird, dessen
 * Gr��e der Zeichenflaeche ungleich der Standardgr��e der Zeichenfl�che von 
 * 600 x 600 Pixel ist.
 * Im zweiten Fall bleibt das Dialog-Fenster jedoch unsichtbar, es werden
 * automatisch die H�he und Breite, gem�� den geladenen Daten, angepasst. 
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */

public class Einstellungen extends JDialog implements ActionListener {
	
	/**
	 * Zeichenfl�che bei welcher die Gr��e ver�ndert wird
	 */
	Zeichenflaeche zeichenfl;	
	
	/**
	 * Referenz zur Klasse Model
	 */
	Model model;
	
	/**
	 * JPanel f�r Dialog-Fenster
	 */
	JPanel panGroesse;
	
	/**
	 * beinhaltet das "x" bei "Breite x Gr��e" im Dialog-Fenster
	 */
	JLabel labGroe;
	
	/**
	 * Textfeld in welches die Breite der Zeichenfl�che eingegeben wird
	 */
	JTextField breite;
	
	/**
	 * Textfeld in welches die H�he der Zeichenfl�che eingegeben wird
	 */
	JTextField hoehe;
	
	/**
	 * Button mit welchem die neue Gr��e der Zeichenfl�che best�tigt wird
	 */
	JButton grOK;
	
	/**
	 * speichert die neue Breite des Editors als String
	 */
	private String br;
	
	/**
	 * speichert die neue H�he des Editors als String
	 */
	private String hoe;
	
	/**
	 * speichert die neue Breite des Editors als Zahl
	 */
	private int brzahl;
	
	
	/**
	 * speichert die neue H�he des Editors als Zahl
	 */
	private int hoezahl;
	
	/**
	 * true, wenn Gr��e Zeichenflaeche durch Men�punkt festgelegt wird und false
	 * wenn Petrinetz geladen wird und die abgespeicherte Gr��e aus der Datei bestimmt wird
	 * (n�tig damit die Groesse der Zeichenflaeche des geladenen Netzes auch bei den
	 * Einstellungen sichtbar ist)
	 */
	boolean sichtbar = true;
	
	
	/**
	 * Konstruktor von Klasse Einstellungen
	 * 
	 * @param zeichenfl Petrinetz-Editor bei welchem die Gr��e ver�ndert wird
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
				BorderFactory.createLineBorder(Color.BLACK, 1), "Groesse der Zeichenflaeche (Breite x H�he)"));
		labGroe = new JLabel("x");

		brzahl = model.getEditorWidth();
		hoezahl = model.getEditorHeight();
		
		br = Integer.toString(brzahl);
		hoe = Integer.toString(hoezahl);
		
		breite = new JTextField(br, 5);
		hoehe = new JTextField(hoe, 5);		

		breite.setEditable(true);
		hoehe.setEditable(true);
		grOK = new JButton ("best�tigen");
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
	 * Die eingegebene Breite und H�he werden jeweils als String entgegen genommen und
	 * in Integer-Zahlen umgewandelt und die Zeichenflaeche erh�lt die neue Gr��e.
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
					JOptionPane.showMessageDialog(this, "Bitte geben Sie ganze Zahlen gr��er 0 ein!");
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
				dispose(); //schlie�t Dialog-Fenster
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Bitte geben Sie ganze Zahlen gr��er 0 ein!");
				
			}
			
		
	}

}
