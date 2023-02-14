package view_controll;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.*;




/**
 * Stellt die graphische Benutzeroberfläche mit Zeichenfläche, Menüleiste und
 * Buttons dar. Enthält Main-Methode um den Editor zu starten. 
 * Desweiteren verfügt die Klasse über einen ActionListener, welcher auf
 * Buttonsklicks und Klicks auf Menüpunkte reagiert und einen MouseListener,
 * welcher die Auswahl einer angelegten Markierung unterstützt.
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */

public class Oberflaeche extends JFrame implements ActionListener, ListSelectionListener{
	
	/** Zeichenfläche welches in die Oberfläche eingefügt wird*/
	Zeichenflaeche zeichenfl;
	
	/**Scrollpanel um den sichtbaren Bereich der Zeichenfläche zu verschieben*/
	JScrollPane scroll;
	
	/**Referenz zum Model, in welchem die Daten zum Netz verwaltet werden*/
	Model model;	
	
	/**Referenz zu NetzSpeichern zum Speichern oder Laden eines Netzes*/
	NetzSpeichern dateiKlasse;
	
	/**Klasse Einstellungen, in welchem die Größe der Zeichenfläche festgelegt wird*/
	Einstellungen einstell;	

	
	/**Menü*/
	JMenuBar menuBar;
	
	/**Menüoption für Netz speichern, Netz laden, neues Netz zeichnen, Editor beenden*/
	JMenu datei;
	
	/**Menüeintrag um aktuelles Netz zu speichern*/
	JMenuItem netzSpeichern;
	
	/**Menüeintrag um ein gespeichertes Netz zu laden*/
	JMenuItem netzLaden;
	
	/**Menüeintrag um neues Netz zu zeichnen*/
	JMenuItem neuesNetz;
	
	/**Menüeintrag um Petrinetz-Editor zu beenden*/
	JMenuItem editorBeenden;		
	
	/**Menü für Einstellung der Größe der Zeichenfläche und Netzelemente*/
	JMenu einstellungen;
	
	/**Menüeintrag zum Einstellen der Größe der Zeichenfläche*/
	JMenuItem zeichenfeldGro;
	
	/**Menü für Einstellen der Größe der Elemente*/
	JMenu elementGro;
	
	/**Menüeintrag für Einstellen auf kleine Größe der Elemente*/
	JRadioButtonMenuItem groeKlein;
	
	/**Menüeintrag für Einstellen auf normale Größe der Elemente*/
	JRadioButtonMenuItem groeNormal;
	
	/**Menüeintrag für Einstellen auf große Größe der Elemente*/
	JRadioButtonMenuItem groeGross;
	
	
	
	/**Box für die Buttons rechts neben Zeichenfläche*/
	Box box;
	
	/**JPanel fürs Hinzufügen von Netzelementen*/
	JPanel panhinzu;	
	
	/**Button fürs Hinzufügen von Transitionen*/
	JButton trans;	
	
	/**Button fürs Hinzufügen von Stellen*/
	JButton stelle;
	
	/**Button fürs Hinzufügen von einzelnen Marken*/
	JButton marke;
	
	/**Button fürs Verändern der Markenanzahl von Stellen*/
	JButton markeAnzahl;
	
	/**Button fürs Hinzufügen von Kanten*/	
	JButton kante;
	
	/**Button fürs Hinzufügen von Namen zu den Netzelementen*/
	JButton namen;
	
	/**Panel fürs Löschen von Netzelementen*/
	JPanel panloesch;
	
	/**Box fürs Löschen von je einem Netzelement*/
	Box boxloe1;
	
	/**Box fürs Löschen von mehreren Netzelementen*/
	Box boxloe2;
	
	/**
	 * Button nach dessen Drücken die zu löschenden Elemente mit der Maustaste 
	 * markiert werden
	 */
	JButton loeschKenn;
	
	/**
	 * Button nach dessen Drücken die zum löschen markierten Elemente gelöscht 
	 * werden
	 */
	JButton mehrLoesch;
	
	/**
	 * Button nach dessen Drücken der Löschvorgang mehrerer Elemente abgebrochen wird
	 */
	JButton mehrAbbrechen;
	
	/**Button nach dessen Drücken einzelne Elemente gelöscht werden können*/
	JButton loeschen;		
	
	/**Panel fürs Verschieben von Netzelementen*/
	JPanel panversch;
	
	/**Box wenn jeweils ein Element verschoben werden soll*/
	Box boxversch;
	
	/**
	 * JButton für das Verschieben von einzelnen Netzelementen:
	 */
	JButton versch;
	
	/**
	 * Box wenn mehrere Elemente gleichzeitig/parallel verschoben werden sollen
	 */
	Box boxmehrver;

	
	/**
	 * Button nach dessen Drücken die zu verschiebenden Elemente mit Maustaste 
	 * markiert werden können
	 */
	JButton mehrVerschMark; 
	
	/**
	 * Button nach dessen Drücken, die gekennzeichneten Elemente verschoben 
	 * werden können
	 */
	JButton verschiebe; 
	
	/**false wenn zu verschiebende Elemente noch nicht gekennzeichnet wurden*/
	boolean verschkennz = false;
	
	/**
	 * Button nach dessen Drücken, das nach dem Verschieben neu enstandene Netz 
	 * fixiert wird
	 */
	JButton fixNetz; 	

	
	/**Panel für die angelegten Markierungen*/
	JPanel panlistMark;
	
	/**Box für die angelegten Markierungen*/
	Box boxmark;
	
	/**List für die angelegten Markierungen*/
	JList jList;		
	
	/**
	 * damit gespeicherte Markierungen in Liste hinzugefügt oder entfernt 
	 * werden können
	 */
	DefaultListModel listModel;
	
	/**Button um aktuelle Stellenbelegung als Markierung anzulegen*/
	JButton aktMarkhinzu;  
	
	/**Button um ausgewählte angelegte Markierung zu löschen*/
	JButton markLoe; 
	
	/**Button um ausgewählte angelegte Markierung umzubenennen*/
	JButton markUmben; 
	
	
	/**Panel für Button "Editor beenden"*/
	JPanel panende;
	
	/**Button um Editor zu beenden*/
	JButton beenden;
	
	
	/**Box für aktuelles Netz*/
	Box boxnetz;
	
	/**
	 * Textfeld für Namen des aktuellen Netz, falls dieses gespeichert oder
	 * geladen wurde
	 */
	JTextField nameNetz;
	
	
	/**Hilfs-Font zur Beschriftung der Buttons und OptionPanes*/
	Font font;
	
	
	/**Hilfs-String zur Beschriftung der Buttons und OptionPanes*/
	String string;
	
	/**speichert die Originalfarbe der JButtons*/
	Color origFarbe;
	
		
	
	/**
	 * Konstruktor von Klasse Oberflaeche
	 * 
	 * @param zeichenfl  Zeichenflaeche wird in Main-Methode erzeugt und in Oberflaeche eingefuegt
	 * @param model  Model, von welchem Daten abgerufen und geaendert werden
	 * @param dateiKlasse   zum Speichern und Laden von Netzen
	 */
	public Oberflaeche(Zeichenflaeche zeichenfl, Model model, NetzSpeichern dateiKlasse) {
		super("Petrinetz-Editor  -  Manuela Koller  - 6900399");		
		this.zeichenfl = zeichenfl;
		this.model = model;
		this.dateiKlasse = dateiKlasse;			
				
		add(zeichenfl, BorderLayout.CENTER);		
		add(new JScrollPane(zeichenfl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);

		setSize(890, 700);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//damit Auswahl der Buttons auch mit Enter-Taste möglich
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		
		//verlängert die ToolTipAnzeige auf 30 Sekunden
		ToolTipManager.sharedInstance().setDismissDelay(30000);
		
		//Menü
		menuBar = new JMenuBar();
		datei = new JMenu("Datei");
		einstellungen = new JMenu("Einstellungen");			
		
		netzSpeichern = new JMenuItem("Petrinetz speichern");
		netzSpeichern.setActionCommand("netzsp");
		netzSpeichern.addActionListener(this);
		
		netzLaden = new JMenuItem("Petrinetz laden");
		netzLaden.setActionCommand("netzlad");
		netzLaden.addActionListener(this);		
				
		neuesNetz = new JMenuItem("Neues Netz zeichnen");
		neuesNetz.setActionCommand("neuesNetzZeichnen");
		neuesNetz.addActionListener(this);
		
		editorBeenden = new JMenuItem("Editor beenden");
		editorBeenden.setActionCommand("beenden");
		editorBeenden.addActionListener(this);
		
		datei.add(netzLaden);
		datei.add(netzSpeichern);		
		datei.addSeparator();
		datei.add(neuesNetz);
		datei.add(editorBeenden);
		
		zeichenfeldGro = new JMenuItem("Groesse der Zeichenflaeche (Breite x Höhe)");
		zeichenfeldGro.setActionCommand("zeichenfGroe");
		zeichenfeldGro.addActionListener(this);
		
		elementGro = new JMenu("Groesse der Elemente");
		
		groeKlein = new JRadioButtonMenuItem("klein", false);
		groeKlein.setActionCommand("groeKlein");
		groeKlein.addActionListener(this);
		
		groeNormal = new JRadioButtonMenuItem ("normal", true);
		groeNormal.setActionCommand("groeNormal");
		groeNormal.addActionListener(this);
		
		groeGross = new JRadioButtonMenuItem ("gross", false);		
		groeGross.setActionCommand("groeGross");
		groeGross.addActionListener(this);
		
		elementGro.add(groeKlein);
		elementGro.add(groeNormal);
		elementGro.add(groeGross);
		einstellungen.add(zeichenfeldGro);
		einstellungen.add(elementGro);			
		
		string = "                                                      " +
				"         schalten durch Klick mit rechter Maustaste auf aktivierte Transition";
		font = new Font(string, Font.ROMAN_BASELINE, 11);
		
		JMenuItem rechtsklick = new JMenuItem(string);
		rechtsklick.setForeground(Color.MAGENTA);		
		
		menuBar.add(datei);
		menuBar.add(einstellungen);			
		menuBar.add(rechtsklick);
		
		add(menuBar, BorderLayout.NORTH);		
		//Ende Menü
		

		
		//Box für Buttons 
		box = Box.createVerticalBox();	
		
		//Panel für Buttons unter Hinzufügen
		panhinzu = new JPanel(new GridLayout(3, 2));
		panhinzu.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3), "Hinzufügen"));	
		panhinzu.setPreferredSize(new Dimension(80, 80));
		panhinzu.setBackground(Color.cyan);
		
		string = "Transition";
		font = new Font(string, Font.PLAIN, 11);
		trans = new JButton(string);
		
		origFarbe = trans.getBackground(); //Originalfarbe der JButtons speichern
		
		trans.setFont(font);
		trans.setToolTipText("<html>Drücken, wenn Transitionen erzeugt werden sollen.<br>" +
				"Anschließend Klicks mit der linken Maustaste auf die Positionen<br>" +
				"in der Zeichenfläche, an welchen die Transitionen platziert<br>" +
				"werden soll.</html>");
		trans.setActionCommand("transition");
		trans.addActionListener(this);		
		
		string = "Stelle";
		font = new Font(string, Font.PLAIN, 11);
		stelle = new JButton(string);
		stelle.setFont(font);
		stelle.setToolTipText("<html>Drücken, wenn Stellen erzeugt werden sollen.<br>" +
				"Anschließend Klicks mit der linken Maustaste auf die Positionen<br>" +
				"in der Zeichenfläche, an welchen die Stellen platziert werden<br>" +
				"sollen.</html>");
		stelle.setActionCommand("stelle");
		stelle.addActionListener(this);		
		
		string = "Kante";
		font = new Font(string, Font.PLAIN, 11);
		kante = new JButton(string);
		kante.setFont(font);
		kante.setMargin(new Insets(5, 32, 5, 32));
		kante.setToolTipText("<html>Drücken, wenn Kanten erzeugt werden sollen.<br>" +
				"Anschließend mit der linken Maustaste auf das Element, von<br>" +
				"welchem die Kante ihren Ursprung nehmen soll drücken und<br>" +
				"die Maus bei gedrückt gehaltener Maustaste zum Zielelement<br>" +
				"ziehen und dort loslassen.</html>");
		kante.setActionCommand("kante");
		kante.addActionListener(this);		
		
		string = "Name";		
		font = new Font(string, Font.PLAIN, 11);
		namen = new JButton(string);
		namen.setFont(font);
		namen.setToolTipText("<html>Drücken, wenn Stellen und Transitionen benannt werden<br>" +
				"sollen.<br>" +
				"Anschließend je einen Klick mit der linken Maustaste auf die<br>" +
				"zu benennenden Elemente, sowie die jeweilige Eingabe des<br>" +
				"Namens in das dabei erscheinende Fenster.</html>");
		namen.setActionCommand("name");
		namen.addActionListener(this);			
		
		string = "Marke";
		font = new Font(string, Font.PLAIN, 11);
		marke = new JButton(string);
		marke.setFont(font);
		marke.setToolTipText("<html>Drücken, wenn Stellen Marken erhalten sollen.<br>" +
				"Anschließend Klicks mit der linken Maustaste auf die<br>" +
				"betreffenden Stellen.<br>" +
				"Bei jedem weiteren Klick auf eine Stelle wird die Marken-<br>" +
				"anzahl jeweils um 1 erhöht.</html>");
		marke.setActionCommand("marke");
		marke.addActionListener(this);		
		
		string = "Markenanzahl";
		font = new Font(string,  Font.PLAIN, 11);
		markeAnzahl = new JButton(string);
		markeAnzahl.setFont(font);
		markeAnzahl.setPreferredSize(new Dimension(10, 10));
		markeAnzahl.setToolTipText("<html>Drücken, wenn Stellen bestimmte Anzahlen von Marken<br>" +
				"erhalten sollen.<br>" +
				"Anschließend Klicks mit der linken Maustaste auf die<br>" +
				"betreffenden Stellen, sowie die jeweilige Eingabe der<br>" +
				"Markenzahl in das dabei erscheinende Fenster.</html>");
		markeAnzahl.setActionCommand("anzahlMarken");
		markeAnzahl.addActionListener(this);
		
		panhinzu.add(trans);
		panhinzu.add(stelle);
		panhinzu.add(kante);
		panhinzu.add(namen);	
		panhinzu.add(marke);		
		panhinzu.add(markeAnzahl);	
		
		
		// Panel für Buttons unter Löschen
		panloesch = new JPanel(new GridLayout(1, 2));	
		panloesch.setBackground(Color.pink);		
		panloesch.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3), "Löschen"));
		
		//Box ein Element löschen
		boxloe1 = Box.createVerticalBox();
		
		string = "ein Element";
		font = new Font(string, Font.PLAIN, 11);
		loeschen = new JButton(string);
		loeschen.setFont(font);
		loeschen.setMargin(new Insets(1, 16, 1, 16)); 		
		loeschen.setToolTipText("<html>Drücken, wenn Netzelemente einzeln gelöscht werden sollen.<br>" +
						"Anschließend jeweils ein Klick mit der linken Maustaste auf<br>" +
						"die zu löschenden Elemente.<br>" +
						"Löschen einer Kante, welche Teil einer Doppelkante ist,<br>" +
						"durch Klick mit der linken Maustaste auf deren Spitze oder<br>" +
						"an einem Punkt der Kante, welcher näher zu deren Spitze als<br>" +
						"zur Spitze der Gegenkante liegt.</html>");
		loeschen.setActionCommand("loeschen");
		loeschen.addActionListener(this);
		
		
		
		//Box mehrere Elemente löschen
		boxloe2 = Box.createVerticalBox();
		boxloe2.setPreferredSize(new Dimension(65, 70));
		boxloe2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 1), "mehr Elemente"));
		boxloe2.setPreferredSize(new Dimension(50, 80));
		
		string = "kennzeichnen";
		font = new Font(string, Font.PLAIN, 11);
		loeschKenn = new JButton(string);
		loeschKenn.setFont(font);
		loeschKenn.setMargin(new Insets(0, 4, 0, 4)); 
		loeschKenn.setToolTipText("<html>Drücken, wenn mehrere Netzelemente gleichzeitig gelöscht<br>" +
				"werden sollen.<br>" +
				"Anschließend je einen Klick mit der linken Maustaste auf<br>" +
				"die zu löschenden Elemente, welche dabei rot gekennzeichnet<br>" +
				"werden.<br>" +
				"Wenn eine Kante, welche Teil einer Doppelkante ist, gelöscht<br>" +
				"werden soll, dann Klick mit der linken Maustaste auf deren<br>" +
				"Spitze oder an einem Punkt der Kante, welcher näher zu deren<br>" +
				"Spitze als zur Spitze der Gegenkante liegt.<br>" +
				"Zum Schluß den Button *löschen* drücken, damit alle gekenn-<br>" +
				"zeichneten Elemente gelöscht werden.<br>" +
				"Falls die gekennzeichneten Elemente doch nicht gelöscht werden<br>" +
				"sollen, den Button *abbrechen* drücken.</html>");
		loeschKenn.setActionCommand("mehrLoeschKenn");
		loeschKenn.addActionListener(this);
		
		string = "löschen";
		font = new Font(string, Font.PLAIN, 11);
		mehrLoesch = new JButton(string);
		mehrLoesch.setFont(font);
		mehrLoesch.setMargin(new Insets(0, 18, 0, 18)); 
		mehrLoesch.setToolTipText("<html>Drücken, wenn die zuvor rot gekennzeichneten<br>" +
				"Elemente gelöscht werden sollen.</html>");
		mehrLoesch.setActionCommand("mehrLoeschen");
		mehrLoesch.addActionListener(this);
		
		string = "abbrechen";
		font = new Font(string, Font.PLAIN, 11);
		mehrAbbrechen = new JButton(string);
		mehrAbbrechen.setFont(font);
		mehrAbbrechen.setMargin(new Insets(0, 11, 0, 11));
		mehrAbbrechen.setToolTipText("<html>Drücken, wenn die rot gekennzeichneten Elemente<br>" +
				"doch nicht gelöscht werden sollen.</html>");
		mehrAbbrechen.setActionCommand("abbrechen");
		mehrAbbrechen.addActionListener(this);
		
		boxloe1.add(loeschen);		
		boxloe2.add(loeschKenn);
		boxloe2.add(mehrLoesch);
		boxloe2.add(mehrAbbrechen);
		panloesch.add(boxloe1);
		panloesch.add(boxloe2);		
		//Ende Löschen
		
		
		//Panel für Buttons unter Verschieben 			
		panversch = new JPanel(new GridLayout(1, 2));
		panversch.setBackground(Color.orange);
		panversch.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createLineBorder(Color.BLACK, 3), "Verschieben"));
		
		//Box wenn ein Element verschoben wird
		Box boxversch = Box.createVerticalBox();
		boxversch.setPreferredSize(new Dimension(50, 80));
		
		string = "ein Element";
		font = new Font(string, Font.PLAIN, 11);
		versch = new JButton(string);
		versch.setFont(font);
		versch.setMargin(new Insets(1, 16, 1, 16));	
		versch.setActionCommand("verschieben");
		versch.addActionListener(this);
		versch.setToolTipText("<html>Drücken, wenn Elemente einzeln verschoben werden sollen.<br>" +
				"Anschließend mit der linken Maustaste auf das zu verschiebende Element<br>" +
				"drücken und mit gedrückt gehaltener Maustaste die Maus bzw. das Element<br>" +
				"an die gewünschte Position ziehen und die Taste dort loslassen.</html>");
		
		
		
		Box boxmehrver = Box.createVerticalBox();
		boxmehrver.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 1), "mehr Elemente"));
		boxmehrver.setPreferredSize(new Dimension(65, 70));
			
		string = "kennzeichnen";
		font = new Font(string, Font.PLAIN, 11);		
		mehrVerschMark = new JButton(string);
		mehrVerschMark.setFont(font);
		mehrVerschMark.setMargin(new Insets(0, 4, 0, 4));
		mehrVerschMark.setActionCommand("mehrVerschMark");
		mehrVerschMark.addActionListener(this);
		mehrVerschMark.setToolTipText("<html>Drücken, wenn mehrere Elemente parallel zueinander verschoben werden sollen.<br>" +
				"Anschließend jeweils einen Klick mit der linken Maustaste auf die zu verschiebenden<br>" +
				"Elemente, diese werden dabei gelb gekennzeichnet.<br>" +
				"Danach Button *verschieben* drücken und mit der linken Maustaste auf eines der<br>" +
				"gelben Elemente drücken und mit gedrückt gehaltener Maustaste die Maus bzw. die<br>" +
				"Elemente an die gewünschte Position ziehen und die Taste dort loslassen.<br>" +
				"Zum Abschluß den Button *fixieren* drücken.</html>");
		
		string = "verschieben";
		font = new Font(string, Font.PLAIN, 11);
		verschiebe = new JButton(string);
		verschiebe.setFont(font);
		verschiebe.setMargin(new Insets(0, 8, 0, 8));
		verschiebe.setActionCommand("verschiebe");
		verschiebe.addActionListener(this);
		verschiebe.setToolTipText("<html>Drücken, um die gelb gekennzeichneten Elemente<br>" +
				"verschieben zu können.<br>" +
				"Danach mit der linken Maustaste auf eines der gelben Elemente<br>" +
				"drücken und mit gedrückt gehaltener Maustaste die Maus bzw.<br>" +
				"die Elemente an die gewünschte Postion ziehen und die Taste<br>" +
				"dort loslassen.</html>");
		
		string = "fixieren";
		font = new Font(string, Font.PLAIN, 11);
		fixNetz = new JButton(string);
		fixNetz.setFont(font);
		fixNetz.setMargin(new Insets(0, 20, 0, 20));
		fixNetz.setActionCommand("fixNetz");
		fixNetz.addActionListener(this);
		fixNetz.setToolTipText("<html>Drücken, wenn die verschobenen Elemente an ihren<br>" +
				"neuen Positionen fixiert werden sollen.</html>");
		
		boxversch.add(versch);
		
		boxmehrver.add(mehrVerschMark);
		boxmehrver.add(verschiebe);	
		boxmehrver.add(fixNetz);
		
		panversch.add(boxversch);
		panversch.add(boxmehrver);
		
		//Ende Verschieben		
		
		
		
		//Panel fürs aktuelle Netz
		JPanel panlistNetz = new JPanel();
		panlistNetz.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3), "Petrinetz"));
		panlistNetz.setPreferredSize(new Dimension (180, 90));
				
		Box boxnetz = Box.createVerticalBox();	
				
		string = "<html>speichern</html>";
		font = new Font(string, Font.PLAIN, 11);		
		JButton netzspeich = new JButton(string);
		netzspeich.setFont(font);
		netzspeich.setMargin(new Insets(0, 0, 0, 0));
		netzspeich.setToolTipText("<html>Drücken, wenn das aktuelle Petrinetz<br>" +
				"gespeichert werden soll.</html>");
		netzspeich.setActionCommand("netzsp");
		netzspeich.addActionListener(this);
			
		string = "<html>laden</html>";
		font = new Font(string, Font.PLAIN, 11);
		JButton netzlad = new JButton(string);
		netzlad.setFont(font);
		netzlad.setMargin(new Insets(0, 0, 0, 0));
		netzlad.setToolTipText("<html>Drücken, wenn ein gespeichertes Petrinetz<br>" +
				"geladen werden soll.</html>");
		netzlad.setActionCommand("netzlad");
		netzlad.addActionListener(this);	
				
		nameNetz = new JTextField("", 12);
		nameNetz.setPreferredSize(new Dimension(140, 40));
		nameNetz.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 1),
				"Name Petrinetz"));
		nameNetz.setToolTipText("<html>Hier wird der Name des aktuellen<br>" +
				"Petrinetzes angezeigt, falls dieses<br>" +
				"gespeichert wurde</html>");
		boxnetz.add(netzspeich);
		boxnetz.add(netzlad);
				
		panlistNetz.add(nameNetz);
		panlistNetz.add(boxnetz);
		
		
		//Panel für die Liste der Markierungen			
		panlistMark = new JPanel();
		panlistMark.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3), "Markierungen zum aktuellen Petrinetz"));
		panlistMark.setBackground(Color.cyan);
		panlistMark.setToolTipText("<html>Wenn eine dieser angelegten Markierungen im aktuellen<br>" +
				"Netz angezeigt werden soll, dann mit der linken Maustaste auf die<br>" +
				"ensprechende Markierung doppelklicken.</hml>");
		
		Box boxmark = Box.createVerticalBox();		
		
		string = "<html>anlegen</html>";
		font = new Font(string, Font.PLAIN, 11);		
		aktMarkhinzu = new JButton(string);
		aktMarkhinzu.setFont(font);
		aktMarkhinzu.setMargin(new Insets(0,0,0,0));
		aktMarkhinzu.setActionCommand("markAnlegen");		
		aktMarkhinzu.addActionListener(this);
		aktMarkhinzu.setToolTipText("<html>Drücken, wenn eine Markierung angelegt<br>" +
				"werden soll.</html>");
		
		string = "<html>umbenennen</html>";
		font = new Font(string, Font.PLAIN, 11);		
		markUmben = new JButton(string);
		markUmben.setFont(font);
		markUmben.setMargin(new Insets(0,0,0,0));
		markUmben.addActionListener(this);
		markUmben.setActionCommand("markUmben");
		markUmben.setToolTipText("<html>Wenn der Name einer angelegten Markierungen umbenannt werden<br>" +
				"soll, dann mit der linken Maustaste auf die jeweilige Markierung<br>" +
				"klicken und anschließend diesen Button *umbenennen* drücken.<br>" +				
				"In das erscheinende Fenster kann der neue Name eingegeben werden.</html>");
		
		string = "<html>löschen</html>";
		font = new Font(string, Font.PLAIN, 11);
		markLoe = new JButton(string);
		markLoe.setFont(font);
		markLoe.setMargin(new Insets(0,0,0,0));
		markLoe.addActionListener(this);
		markLoe.setActionCommand("markLoe");
		markLoe.setToolTipText("<html>Wenn eine angelegte Markierung gelöscht werden soll, dann<br>" +
				"mit der linken Maustaste auf die jeweilige Markierung klicken<br>" +
				"und anschließend diesen Button *löschen* drücken.</html>");
		
		boxmark.add(aktMarkhinzu);
		boxmark.add(markUmben);
		boxmark.add(markLoe);
		
		listModel = new DefaultListModel();
		listModel.addElement(model.Mark_Liste.getLast().name);
		jList = new JList(listModel);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setToolTipText("<html>Wenn eine dieser angelegten Markierungen im aktuellen<br>" +
				"Netz anzeigt werden soll, dann auf die entsprechende Markierung<br>" +
				"mit der linken Maustaste doppelklicken.</hml>");
		jList.addMouseListener(mouseListener);		
		jList.addListSelectionListener(this);		
		JScrollPane pan = new JScrollPane(jList);			
		pan.setPreferredSize(new Dimension(140, 80));			
		panlistMark.add(pan);		
		panlistMark.add(boxmark);			
		
		//Button fürs Beenden des Editors
		panende = new JPanel();		
		beenden = new JButton("Editor beenden");
		beenden.setPreferredSize(new Dimension( 120, 35));
		beenden.setActionCommand("beenden");
		beenden.addActionListener(this);
		beenden.setToolTipText("<html>Drücken, wenn das Programm beendet werden soll.</html>");
		panende.add(beenden);			
		
		box.add(panhinzu);		
		box.add(panloesch);
		box.add(panversch);	
		box.add(panlistNetz);
		box.add(panlistMark);
		box.add(panende);	
		add(box, BorderLayout.EAST);	
		
		setLocationRelativeTo(null);
	}	
	
	
	
	
	/**
	 * Wenn Doppelklick auf angelegte Markierung, dann wird sie durch diese
	 * Methode im aktuellen Netz abgebildet.
	 */
	MouseListener mouseListener = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {			
			
			if(e.getClickCount() == 2) {
				if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
					erstVorgangBeenden();
				} else {
					clearForeground();
					model.setAnfang();
					if(jList.getSelectedIndex() != -1) {
						LinkedList<Integer> addMark = new LinkedList(); //Index Stellen welche Marken erhalten haben
						LinkedList<Integer> nullMark = new LinkedList(); //Index Stellen welche 0 Marken erhalten haben
						for(int i=0; i < model.Mark_Liste.size(); i++) {
							if(jList.getSelectedValue() == model.Mark_Liste.get(i).name) {							
								for(int j=0; j < model.Mark_Liste.get(i).mark.size(); j++) {
									//prüfen ob leere Stelle nun Marken erhält
									if(model.Mark_Liste.get(i).mark.get(j) > 0 &&
											model.Stellen_Liste.get(j).anzahl_Marken == 0) {
										addMark.addLast(j);									
									} 
									//prüfen ob Stelle mit Marken nun 0 Marken erhält
									else if(model.Mark_Liste.get(i).mark.get(j) == 0 &&
											model.Stellen_Liste.get(j).anzahl_Marken > 0) {									
										nullMark.addLast(j);
									}
									//neue Markenanzahl wird auf Stelle übertragen
									model.Stellen_Liste.set(j,  new Stelle(model.Stellen_Liste.get(j).stelle_x_kor,
											model.Stellen_Liste.get(j).stelle_y_kor,
											model.Stellen_Liste.get(j).stelle_laenge,
											model.Stellen_Liste.get(j).stelle_breite,
											model.Mark_Liste.get(i).mark.get(j),
											model.Stellen_Liste.get(j).stelle_color,
											model.Stellen_Liste.get(j).tex,
											model.Stellen_Liste.get(j).versch_kenn,
											model.Stellen_Liste.get(j).loesch_kenn,
											model.Stellen_Liste.get(j).geloescht
											));	
								}
								//prüfen ob zwichenzeitlich neue Stellen erzeugt wurden, wenn ja erhalten diese 0 Marken
								if(model.Stellen_Liste.size() > model.Mark_Liste.get(i).mark.size()) {
									for(int j=model.Mark_Liste.get(i).mark.size(); j < model.Stellen_Liste.size(); j++){
										model.Stellen_Liste.set(j,  new Stelle(model.Stellen_Liste.get(j).stelle_x_kor,
												model.Stellen_Liste.get(j).stelle_y_kor,
												model.Stellen_Liste.get(j).stelle_laenge,
												model.Stellen_Liste.get(j).stelle_breite,
												0,
												model.Stellen_Liste.get(j).stelle_color,
												model.Stellen_Liste.get(j).tex,
												model.Stellen_Liste.get(j).versch_kenn,
												model.Stellen_Liste.get(j).loesch_kenn,
												model.Stellen_Liste.get(j).geloescht
												));	
										nullMark.addLast(j);
									}
								}							
							}
						}
						//prüfen ob Transitionen deaktiviert werden müssen, da evtl. Eingangsstellen jetzt ohne Marken
						for(int i=0; i < nullMark.size(); i++) {
							for(int k=0; k < model.Kanten_Liste.size(); k++) {
								if(model.Kanten_Liste.get(k).von_Stelle &&
										model.Kanten_Liste.get(k).index_Stelle == nullMark.get(i) &&
										model.Trans_Liste.get(model.Kanten_Liste.get(k).index_Trans).trans_aktiv){
									model.Trans_Liste.get(model.Kanten_Liste.get(k).index_Trans).trans_aktiv = false;
								}
							}
						}
						nullMark.clear();
						
						//prüfen ob Transitionen aktiviert werden müssen
						LinkedList<Integer> transAkt = new LinkedList(); //Transitionen welche evtl. aktiviert werden					
						for(int i=0; i < addMark.size(); i++) {
							for(int k=0; k < model.Kanten_Liste.size(); k++) {
								if(model.Kanten_Liste.get(k).von_Stelle &&
										model.Kanten_Liste.get(k).index_Stelle == addMark.get(i) &&
										!model.Trans_Liste.get(model.Kanten_Liste.get(k).index_Trans).trans_aktiv){
									transAkt.addLast(model.Kanten_Liste.get(k).index_Trans);
								}
							}
						}
						for(int i=0; i < transAkt.size(); i++) {
							boolean deakt = false; //wenn false bleibt, wird Transition aktiviert
							for(int k=0; k < model.Kanten_Liste.size(); k++) {
								if(model.Kanten_Liste.get(k).von_Stelle &&
										model.Kanten_Liste.get(k).index_Trans == transAkt.get(i) &&
										model.Stellen_Liste.get(model.Kanten_Liste.get(k).index_Stelle).anzahl_Marken == 0) {
									deakt = true;
								}
							}
							if(!deakt) {
								model.Trans_Liste.get(transAkt.get(i)).trans_aktiv = true;
							}
						}
					}
				}				
				
				zeichenfl.repaint();
			}
		}
	};
	
	
	
	/**
	 * Ueberschreibt die gleichnamige Methode des Interfaces ActionListener,
	 * um folgende Funktionen (durch Klick auf Buttons oder Menüpunkte) durchzuführen:
	 * Markierung anlegen
	 * Markierung umbenennen
	 * aktuelle Petrinetz auf Festplatte speichern
	 * Petrinetz von Festplatte laden
	 * angelegte Markierung löschen
	 * Größe der Elemente ändern
	 * Verschieben je eines oder mehrerer Elemente parallel
	 * Löschen je eines oder mehrerer Elemente gleichzeitig
	 * Hinzufügen von Transitionen, Stellen, Kanten, Namen und Marken
	 * Neues Netz zeichnen
	 * Editor beenden
	 * Editorgröße verändern
	 * 
	 */
	public void actionPerformed(ActionEvent e) { 		
		
		/**
		 * Neue Markierung anlegen
		 */
		if(e.getActionCommand() == "markAnlegen") {	
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();	
				model.setAnfang();
				Object[] optionen = {"Anfangsmarkierung", "neue Markierung"};
				int auswahl = JOptionPane.showOptionDialog(null, "<html>Möchten Sie die aktuelle Markierung<br>" +
						"als Anfangsmarkierung oder als<br>" +
						"neue Markierung anlegen?</html>",
						"Markierung anlegen",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						null, optionen, optionen[0]);
				//neue Markierung anlegen
				if(auswahl >= 1) {				
					model.markAnlegen();
					//wenn Anlage Markierung erfolgreich war, wird Name in Liste der Markierungen eingetragen					
					if(model.markAnlage) {
						listModel.addElement(model.Mark_Liste.getLast().name);
						model.markAnlage = false;
					}		
				}
				//Anfangsmarkierung anlegen
				else if (auswahl == 0) {					
					model.anfMarkSetzen();	
					//wenn keine Anfangsmarkierung vorhanden
					if(!model.anfMarkVorh) {						
						listModel.clear();
						for(int i=0; i < model.Mark_Liste.size(); i++) {
							listModel.addElement(model.Mark_Liste.get(i).name);
						}
					}
					//wenn Anfangsmarkierung bereits vorhanden
					else if(model.anfMarkVorh) {						
						model.anfMarkVorh = false;
					}
				} 					
			}					
		}
		
		
		/**
		 * Markierung umbenennen
		 */
		if(e.getActionCommand() == "markUmben"){
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				model.setAnfang();
				int index = jList.getSelectedIndex();
				if(jList.getSelectedIndex() != -1) {					
					string = JOptionPane.showInputDialog("Bitte geben Sie den neuen Namen ein");
					
					//abgebrochen
					if(string == null) {						
						return;
					}
					
					//kein Name wurde eingegeben
					if(string.length() == 0) {						
						JOptionPane.showMessageDialog(null, "<html>Es wurde kein Name für die Markierung eingegeben!<br>" +
								"Bitte geben Sie einen Namen ein.</html>");
						return;
					}					
					
					//Name wurde eingegeben
					if(string != null) {					
						String name1 = (String) jList.getSelectedValue();
						String name2 = "";
						
						//prüfen ob Anfangsmarkierung geändert werden soll
						if(name1.length() >= 4) {
							for(int i=0; i < 4; i++) {
								name2 = name2 + name1.charAt(i);							
							}
							//Name der Anfangsmarkierung soll geändert werden
							if(name2.equals("init")) {
								int option = JOptionPane.showConfirmDialog(this, 
										"<html>Soll diese Markierung weiterhin die<br>" +
										"Anfangsmarkierung bleiben?</html>", "",JOptionPane.YES_NO_OPTION);
								//Markierung soll weiterhin Anfangsmarkierung bleiben
								if(option == JOptionPane.YES_OPTION) {
									model.Mark_Liste.get(index).name = "init "+string;
									listModel.set(index, model.Mark_Liste.get(index).name);
								}
								
								//Markierung soll nicht mehr Anfangsmarkierung bleiben
								else if(option == JOptionPane.NO_OPTION) {									
									
									//das Vorwort init ist für die Anfangsmarkierung reserviert
									String s1 = "";
									if(string.length() >= 4) {
										for(int i= 0; i < 4; i++) {
											s1 = s1+string.charAt(i);
										}
										//neuer Name beginnt mit init, was nicht erlaubt ist
										if(s1.equals("init")) {
											JOptionPane.showMessageDialog(null, "<html>Der Name init ist bereits für die<br>" +
													"Anfangsmarkierung reserviert.<br>" +
													"Bitte geben Sie einen anderen Namen ein.</html>");
											return;
										}
										//neuer Name beginnt nicht mit init
										else {
											model.Mark_Liste.get(index).name = string;
											listModel.set(index, model.Mark_Liste.get(index).name);
										}
									}								
									//neuer Name der ehemaligen Anfangsmarkierung hat weniger als 4 Buchstaben
									//und beginnt somit nicht mit init
									else {
										model.Mark_Liste.get(index).name = string;
										listModel.set(index, model.Mark_Liste.get(index).name);
									}									
								}
								
								else if(option == JOptionPane.CLOSED_OPTION) {
									return;
								}								
							} 
							//wenn Name einer Markierung geändert werden soll, welche nicht die Anfangsmarkierung ist
							else {
								String s2 = "";
								if(string.length() >= 4) {
									for(int j=0; j < 4; j++) {
										s2 = s2 + string.charAt(j);
									}
									if(s2.equals("init")) {
										JOptionPane.showMessageDialog(null, "<html>Der Name init ist bereits für die<br>" +
												"Anfangsmarkierung reserviert.<br>" +
												"Bitte geben Sie einen anderen Namen ein.</html>");
										return;
									}else {
										model.Mark_Liste.get(index).name = string;
										listModel.set(index, model.Mark_Liste.get(index).name);
									}
								} else if(string.length() < 4) {
									model.Mark_Liste.get(index).name = string;
									listModel.set(index, model.Mark_Liste.get(index).name);
								}
							}				
						}
						//wenn Name einer Markierung geändert werden soll, welche nicht die Anfangsmarkierung ist
						else if(name1.length() < 4 ) {	
							String s3 = "";
							//prüfen ob Name der Markierung mit init beginnt
							if(string.length() >= 4) {
								for(int i=0; i < 4; i++){
									s3 = s3 + string.charAt(i);
								}
								//init ist für Anfangsmarkierung reserviert
								if(s3.equals("init")){
									JOptionPane.showMessageDialog(null, "<html>Der Name init ist bereits für die<br>" +
											"Anfangsmarkierung reserviert.<br>" +
											"Bitte geben Sie einen anderen Namen ein.</html>");
									return;
								}
								//neuer Name beginnt nicht mit init
								else {
									model.Mark_Liste.get(index).name = string;
									listModel.set(index, model.Mark_Liste.get(index).name);
								}
							}
							//neuer Name hat weniger als 4 Buchstaben und beginnt somit nicht mit init
							else {
								model.Mark_Liste.get(index).name = string;
								listModel.set(index, model.Mark_Liste.get(index).name);
							}
						}
						zeichenfl.repaint();					
					}
				}					
			}			 
		}
		
		
		/**
		 * aktuelle Petrinetz wird gespeichert
		 */
		if(e.getActionCommand() == "netzsp") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} 
			else {
				clearForeground();
				model.setAnfang();
				dateiKlasse.saveToFileSystem(model);
				//wenn kein Abbruch, dann Name des Netzes im Textfeld zeigen
				if(!dateiKlasse.abbruch) {
					String name1 = "";
					String name2 = "";
					name1 = dateiKlasse.file.getName();
					//Name des Petrinetzes wird ohne .txt angezeigt
					for(int i = 0; i < name1.length()-4; i++) {
						name2 = name2 + name1.charAt(i);
					}					
					nameNetz.setText(name2);
				}
				//wenn Abbruch, dann erscheint auch kein neuer Name im Textfeld
				else if(dateiKlasse.abbruch) {
					dateiKlasse.abbruch = false;
				}
			}	 
		}
		
		
		/**
		 * Ein Petrinetz soll geladen werden
		 */
		if(e.getActionCommand() == "netzlad"){
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();			
			} 
			else {
				clearForeground();
				model.setAnfang();
				//wenn sich ein bereits gespeichertes Netz in der Zeichenflaeche befindet
				if(!nameNetz.getText().equals("")) {					
					int option3 = JOptionPane.showConfirmDialog(this, "<html> Möchten Sie die Änderungen<br>" +
							"am Netz speichern?</html>", "speichern?", JOptionPane.YES_NO_OPTION);
					if(option3 == JOptionPane.YES_OPTION) {
						dateiKlasse.sofortSpeichern(model);
					}else if(option3 == JOptionPane.CLOSED_OPTION){
						return;
					}			
				} 
				//wenn sich ein noch nicht abgespeichertes Netz in der Zeichenflaeche befindet
				else if(nameNetz.getText().equals("") && (model.Trans_Liste.size() > 0 ||
						model.Stellen_Liste.size() > 0)) {				
					int option4 = JOptionPane.showConfirmDialog(this, "<html> Möchten Sie das Petrinetz<br>" +
							"speichern?", "speichern?", JOptionPane.YES_NO_OPTION);
					if(option4 == JOptionPane.YES_OPTION) {
						dateiKlasse.saveToFileSystem(model);
						if(dateiKlasse.abbruch) {
							dateiKlasse.abbruch = false;
							return;
						}
					}else if(option4 == JOptionPane.CLOSED_OPTION) {
						return;
					}
				}	
				
				//ein Petrinetz kann geladen werden
				dateiKlasse.loadfromFileSystem();  
				//wenn kein Abbruch, dann werden Daten des geladenen Netzes übernommen
				if(!dateiKlasse.abbruch) {				
					model.setAnfang();
					model.Stellen_Liste.clear();
					model.Trans_Liste.clear();
					model.Kanten_Liste.clear();	
					
					model.normal = dateiKlasse.modelLoad.normal;
					model.klein = dateiKlasse.modelLoad.klein;
					model.gross = dateiKlasse.modelLoad.gross;
					if(model.normal) {
						groeKlein.setSelected(false);
						groeNormal.setSelected(true);
						groeGross.setSelected(false);
					} else if(model.klein){
						groeKlein.setSelected(true);
						groeNormal.setSelected(false);
						groeGross.setSelected(false);
					}else if(model.gross) {
						groeKlein.setSelected(false);
						groeNormal.setSelected(false);
						groeGross.setSelected(true);
					}
										
					model.setEditorWidth(dateiKlasse.modelLoad.editorWidth);
					model.setEditorHeight(dateiKlasse.modelLoad.editorHeight);
					
					einstell = new Einstellungen(zeichenfl, model, false);
					einstell.grOK.doClick();
					einstell.dispose();
					zeichenfl.resize(model.getEditorWidth(), model.getEditorHeight());					
					
					model.Stellen_Liste = dateiKlasse.modelLoad.Stellen_Liste;
					model.Trans_Liste = dateiKlasse.modelLoad.Trans_Liste;
					model.Kanten_Liste = dateiKlasse.modelLoad.Kanten_Liste;
					model.groesseWidth = dateiKlasse.modelLoad.groesseWidth;
					model.groesseHeight = dateiKlasse.modelLoad.groesseHeight;
					model.groeMarkeWidth = dateiKlasse.modelLoad.groeMarkeWidth;
					model.groeMarkeHeight = dateiKlasse.modelLoad.groeMarkeHeight;
					model.groeMarkeZahl = dateiKlasse.modelLoad.groeMarkeZahl;
					zeichenfl.repaint();	
					
					//Namen des Petrinetzes ohne .txt auslesen und in Textfeld eintragen
					String name1 = "";
					String name2 = "";
					name1 = dateiKlasse.file.getName();
					for(int i=0; i < name1.length()-4; i++){
						name2 = name2 + name1.charAt(i);
					}
					nameNetz.setText(name2);
					model.Mark_Liste = dateiKlasse.modelLoad.Mark_Liste;					
					
					listModel.clear(); //Liste Markierungen des Vorgängernetzes löschen
					
					//Markierungen des geladenen Netzes in Liste anzeigen
					for(int i=0; i < model.Mark_Liste.size(); i++) {					
						listModel.addElement(model.Mark_Liste.get(i).name);
					}
					zeichenfl.repaint();
				} 
				//wenn Ladevorgang abgebrochen wurde
				else if (dateiKlasse.abbruch){
					dateiKlasse.abbruch = false;
					return;
				}				
			}			
		}
		
		
		/**
		 * eine ausgewählte Markierung wird geloescht
		 */
		if(e.getActionCommand() == "markLoe") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				model.setAnfang();
				int index = jList.getSelectedIndex();
				if(jList.getSelectedIndex() != -1) {						
					listModel.remove(index);
					model.Mark_Liste.remove(index);
				}
			}			
		}		

		
		
		
		
		/**
		 * Groesse der Elemente wird durch Menüpunkte geändert
		 */
		if(e.getActionCommand() == "groeKlein") {	
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();	
				groeKlein.setSelected(false);
			} else {
				clearForeground();
				model.setAnfang();
				model.groesseHalbieren();
				groeKlein.setSelected(true);
				groeNormal.setSelected(false);
				groeGross.setSelected(false);
				zeichenfl.repaint();
			}			
		}
		if(e.getActionCommand() == "groeNormal") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();	
				groeNormal.setSelected(false);
			} else {
				clearForeground();	
				model.setAnfang();
				model.groesseNormal();
				groeKlein.setSelected(false);
				groeNormal.setSelected(true);
				groeGross.setSelected(false);
				zeichenfl.repaint();
			}			
		}
		if(e.getActionCommand() == "groeGross") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();	
				groeGross.setSelected(false);
			} else{
				clearForeground();
				model.setAnfang();
				model.groesseVerdoppeln();
				groeKlein.setSelected(false);
				groeNormal.setSelected(false);
				groeGross.setSelected(true);
				zeichenfl.repaint();
			}			
		}
		
		
		
		
		/**
		 * einzelne Elemente verschieben
		 */
		if(e.getActionCommand() == "verschieben") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {				
				
					clearForeground();
					versch.setForeground(Color.magenta);
					versch.setBackground(Color.yellow);
					model.setVerschieben();		
									
			}						
		}
		
		
		
		/**
		 * mehrere Elemente können zum Verschieben gekennzeichnet werden
		 */
		if(e.getActionCommand() == "mehrVerschMark"){
			if(model.getLoeschKenn() ) {
				erstVorgangBeenden();
			} else {
				verschkennz = true;
				clearForeground();
				mehrVerschMark.setForeground(Color.magenta);
				mehrVerschMark.setBackground(Color.yellow);
				model.setVerschKennzei();
			}			
		}
		
		
		/**
		 * die zum Verschieben gekennzeichneten Elemente können nun verschoben werden 
		 */
		if(e.getActionCommand() == "verschiebe") {
			if(model.getLoeschKenn()) {
				erstVorgangBeenden();
			} else {
				if(verschkennz) {
					clearForeground();
					verschiebe.setForeground(Color.magenta);
					verschiebe.setBackground(Color.yellow);
					model.setMehrVerschieben();
				} else {					
					JOptionPane.showMessageDialog(null, "<html>Bitte die zu verschiebenden Elemente<br>" +
							"zuerst kennzeichnen</html>");
				}	
			}						
		}
		
		
		/**
		 * das durch das Verschieben neu entstandene Netz wird jetzt fixiert		
		 */
		if(e.getActionCommand () == "fixNetz") {
			if(model.getLoeschKenn()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				model.setFixNetz();
				verschkennz = false;
				for(int i=0; i < model.Trans_Liste.size(); i++) {
					if(model.Trans_Liste.get(i).versch_kenn) {
						model.Trans_Liste.get(i).versch_kenn = false;
					}
				}
				for(int i=0; i < model.Stellen_Liste.size(); i++) {
					if(model.Stellen_Liste.get(i).versch_kenn) {
						model.Stellen_Liste.get(i).versch_kenn = false;
					}
				}
				zeichenfl.repaint();
			}				
		}
		
		
		/**
		 * einzelne Elemente können gelöscht werden
		 */
		if(e.getActionCommand() == "loeschen") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				loeschen.setForeground(Color.magenta);
				loeschen.setBackground(Color.yellow);
				model.setLoeschen();
			}			
		}		
		
		
		/**
		 * mehrere Elemente können nun zum Loeschen gekennzeichnet werden
		 */
		if(e.getActionCommand() == "mehrLoeschKenn") {
			if(model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			}else {
				clearForeground();
				loeschKenn.setForeground(Color.magenta);
				loeschKenn.setBackground(Color.yellow);
				model.setLoeschKennzei();
			}		
		}			
		
		
		/**
		 * Loeschen der zum Loeschen gekennzeichneten Elemente
		 */
		if(e.getActionCommand() == "mehrLoeschen") {	
			if(model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			}else {
				//zum Loeschen gekennzeichnete Transitionen und deren Kanten werden gelöscht
				for(int i=0; i < model.Trans_Liste.size(); i++) {
					if(model.Trans_Liste.get(i).loesch_kenn) {
						model.Trans_Liste.get(i).loesch_kenn = false;
						model.Trans_Liste.get(i).geloescht = true; //Transition gilt als gelöscht
						for(int j=0; j < model.Kanten_Liste.size(); j++) {    					
	   					 if(model.Kanten_Liste.get(j).index_Trans == i) {   						
	   						 model.Kanten_Liste.remove(j);
	   						 j = j - 1;
	   					 }
	   				 }  
					}
				}	
				
				
				//zum Loeschen gekennzeichnete Stellen und deren Kanten werden gelöscht
				LinkedList <Integer> trans_akt_mehr = new LinkedList(); //Transitionen welche Stelle als Eingangsstelle haben				
				for(int i=0; i < model.Stellen_Liste.size(); i++) {
					if(model.Stellen_Liste.get(i).loesch_kenn) {
						model.Stellen_Liste.get(i).loesch_kenn = false;
						model.Stellen_Liste.get(i).geloescht = true; //Stelle gilt als gelöscht
						for(int j=0; j < model.Kanten_Liste.size(); j++) {
	   					 if(model.Kanten_Liste.get(j).index_Stelle == i) {
	   						 //Transitionen speichern welche Stelle als Eingangsstelle haben
	   						 if(model.Kanten_Liste.get(j).von_Stelle) {
	   							trans_akt_mehr.add(model.Kanten_Liste.get(j).index_Trans);
	   						 }   						 
	   						 model.Kanten_Liste.remove(j);
	   						 j = j - 1;
	   					 }   					 
	   				 }
						
					//wenn zwischengespeicherte Transition mind. eine Eingangsstelle ohne Marken hat, dann
					//diese deaktivieren, ansonsten aktivieren					 
	   				 for(int j=0; j < trans_akt_mehr.size(); j++) {   					 
	   					 boolean aktiv = true;
	   					 for(int k=0; k < model.Kanten_Liste.size(); k++) {   						 
	   						 if(model.Kanten_Liste.get(k).von_Stelle &&
	   								 model.Kanten_Liste.get(k).index_Trans == trans_akt_mehr.get(j)) {
	   							 if(model.Stellen_Liste.get(model.Kanten_Liste.get(k).index_Stelle).anzahl_Marken == 0)
	   								 aktiv = false;
	   						 }
	   					 }
	   					 //wenn Transition im Kanten_Liste nicht gefunden wurde, hat sie keine
	   					 //Eingangsstelle mehr und wird aktiviert, also auch grün gefärbt
	   					 if(aktiv) {
	   						 model.Trans_Liste.get(trans_akt_mehr.get(j)).trans_aktiv = true;
	   					 } else if(!aktiv) {
	   						 model.Trans_Liste.get(trans_akt_mehr.get(j)).trans_aktiv = false;
	   					 }
	   				 }
					}
					trans_akt_mehr.clear();					
				}	
				
				//zum Loeschen gekennzeichnete Kanten werden gelöscht
				int indexTra = -1;	//Index Transition welche mit Kante verbunden ist
				int indexSt = -1; //Index Stelle welche mit Kante verbunden ist
				LinkedList <Integer> stellenind = new LinkedList(); //weitere Eingangsstellen der Transition, welche mit Kante verbunden ist				
				for(int i=0; i < model.Kanten_Liste.size(); i++) {					
					if(model.Kanten_Liste.get(i).loesch_kenn) {						
						indexTra = model.Kanten_Liste.get(i).index_Trans;
						indexSt = model.Kanten_Liste.get(i).index_Stelle;
						
						//wenn Ausgangsstelle der Transition
						if(model.Kanten_Liste.get(i).zu_Stelle) {
							model.Kanten_Liste.remove(i); //löschen der Kante
							i = i - 1;
							repaint();
						}
						//wenn Eingangsstelle der Transition
						else if(model.Kanten_Liste.get(i).von_Stelle){							
							//prüfen ob noch mehrere Eingangsstellen der Transition existieren						
							for(int j=0; j < model.Kanten_Liste.size(); j++) {
								if((model.Kanten_Liste.get(j).von_Stelle) &&
										(model.Kanten_Liste.get(j).index_Stelle != indexSt) &&
										(model.Kanten_Liste.get(j).index_Trans == indexTra)) {									
									stellenind.addLast(model.Kanten_Liste.get(j).index_Stelle);
								}
							}
							
							boolean aktiv = true; //wenn true bleibt, wird Transition aktiviert, ansonsten deaktiviert
							//wenn Transition mind. eine Eingangsstelle ohne Marken besitzt, folgt aktiv = false
							for(int k=0; k < stellenind.size(); k++) {
								if(model.Stellen_Liste.get(stellenind.get(k)).anzahl_Marken == 0) {
									aktiv = false;
								}
							}
							if(aktiv) {
								model.Trans_Liste.get(model.Kanten_Liste.get(i).index_Trans).trans_aktiv = true;
							} else {
								model.Trans_Liste.get(model.Kanten_Liste.get(i).index_Trans).trans_aktiv = false;
							}
							
							stellenind.clear();
							model.Kanten_Liste.remove(i); //löschen der Kante
							i = i -1;								
						}					
					}
					indexTra = -1;
					indexSt = -1;
				}
				clearForeground();
				model.setAnfang();
				zeichenfl.repaint();				
			}			
		}	
		
		
		/**
		 * zum Loeschen gekennzeichneten Elemente werden doch nicht gelöscht, da *abbrechen* gedrückt
		 */
		
		if(e.getActionCommand() == "abbrechen") {
			if(model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				//alle zum Löschen gekennzeichneten Transitionen werden auf nicht löschen gesetzt
				for(int i=0; i < model.Trans_Liste.size(); i++) {
					if(model.Trans_Liste.get(i).loesch_kenn) {
						model.Trans_Liste.get(i).loesch_kenn = false;
					}
				}
				for(int i=0; i < model.Stellen_Liste.size(); i++) {
					if(model.Stellen_Liste.get(i).loesch_kenn) {
						model.Stellen_Liste.get(i).loesch_kenn = false;
					}
				}
				for(int i=0; i < model.Kanten_Liste.size(); i++) {
					if(model.Kanten_Liste.get(i).loesch_kenn) {
						model.Kanten_Liste.get(i).loesch_kenn = false;
					}
				}
				model.setAnfang();
				clearForeground();
				zeichenfl.repaint();				
			}			
		}		
		
		
		/**
		 * Transitionen können nun erzeugt werden
		 */		
		if(e.getActionCommand() == "transition") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				trans.setForeground(Color.magenta);	
				trans.setBackground(Color.yellow);
				model.setTransition();
			}			
		}
		
		
		/**
		 * Stellen können nun erzeugt werden
		 */		
		if(e.getActionCommand() == "stelle") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				stelle.setForeground(Color.magenta);
				stelle.setBackground(Color.yellow);
				model.setStelle();
			}			
		}
		
		
		/**
		 * jetzt kann Markenanzahl von Stellen erhöht werden
		 */
		if(e.getActionCommand() == "marke") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				marke.setForeground(Color.magenta);
				marke.setBackground(Color.yellow);
				model.setMarke();
			}			
		}
		
		
		/**
		 * jetzt kann Markenanzahl von Stellen gesetzt werden
		 */
		if(e.getActionCommand() == "anzahlMarken") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			}else {
				clearForeground();
				markeAnzahl.setForeground(Color.magenta);
				markeAnzahl.setBackground(Color.yellow);
				model.setAnzahlMarken();
			}			
		}
		
		/**
		 * jetzt können Kanten gezogen werden
		 */
		if(e.getActionCommand() == "kante") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				kante.setForeground(Color.magenta);
				kante.setBackground(Color.yellow);
				model.setKante();
			}			
		}
		
		
		/**
		 * jetzt können Elemente benannt werden
		 */
		if(e.getActionCommand() == "name") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				namen.setForeground(Color.magenta);
				namen.setBackground(Color.yellow);
				model.setNamen();
			}			
		}
		

		/**
		 * jetzt kann ein neues Netz gezeichnet werden
		 */
		if(e.getActionCommand() == "neuesNetzZeichnen"){
			
			//wenn geladenes Petrinetz in der Zeichenfläche, dann Abfrage ob Änderungen speichern			
			if(!nameNetz.getText().equals("")) {
				
				int option1 = JOptionPane.showConfirmDialog(this, "<html>Möchten Sie eventuelle Änderungen<br>" +
						"am Petrinetz speichern?", "Änderungen speichern?", JOptionPane.YES_NO_OPTION);
				if(option1 == JOptionPane.YES_OPTION) {					
					if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
						erstVorgangBeenden();
						return;
					} else {
						dateiKlasse.sofortSpeichern(model);	
					}									
				} else if(option1 == JOptionPane.CLOSED_OPTION) {
					return;
				}
			//wenn Petrinetz noch nicht gespeichert wurde, Abfrage ob dies gespeichert werden soll
			} else if(nameNetz.getText().equals("") && (model.Trans_Liste.size() > 0 ||
					model.Stellen_Liste.size() > 0)) {
				
				int option2 = JOptionPane.showConfirmDialog(this, "<html>Möchten Sie das Petrinetz<br>" +
						"speichern?", 
						"Petrinetz speichern?", JOptionPane.YES_NO_OPTION);
				
				if(option2 == JOptionPane.YES_OPTION) {
					if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
						erstVorgangBeenden();
						return;
					} else {
						dateiKlasse.saveToFileSystem(model);	
					}								
				}
				//wenn Abfragefenster geschlossen wurde
				else if(option2 == JOptionPane.CLOSED_OPTION) {					
					return;
				}				
			}
			//wenn Speichervorgang nicht abgebrochen wurde
			if(!dateiKlasse.abbruch) {
				clearForeground();
				model.setAnfang();
				model.Trans_Liste.clear();
				model.Stellen_Liste.clear();
				model.Kanten_Liste.clear();
				model.VerschElem_Liste.clear();				
				
				model.groesseHeight = 40;
				model.groesseWidth = 40;
				model.groeMarkeHeight = 14;
				model.groeMarkeWidth = 14;
				model.groeMarkeZahl = 20;
				
				model.setEditorHeight(600);
				model.setEditorWidth(600);
				
				einstell = new Einstellungen(zeichenfl, model, false);
				einstell.grOK.doClick();
				einstell.dispose();
				zeichenfl.resize(model.getEditorWidth(), model.getEditorHeight());
				
				groeKlein.setSelected(false);
				groeNormal.setSelected(true);
				groeGross.setSelected(false);
				
				model.normal = true;
				model.klein = false;
				model.gross = false;	
				
				//entfernen aller in der Liste befindlichen Markierungen
				for(int i=0; i < model.Mark_Liste.size(); i++) {
					listModel.remove(0);
				}
				model.Mark_Liste.clear();			
				
				model.mark = new Markierung();
				model.mark.name = "init Markierung";		
				model.Mark_Liste.addLast(model.mark);
				listModel.addElement(model.Mark_Liste.getLast().name);
				
				nameNetz.setText("");
				
				
				zeichenfl.repaint();
			} 
			//wenn Speichervorgang abgebrochen wurde
			else {
				dateiKlasse.abbruch = false;
			}
		}
		
		
		/**
		 * Programm wird beendet
		 */
		if(e.getActionCommand() == "beenden") {
			//prüfen ob sich ein geladenes Netz in der Zeichenfläche befindet
			if(!nameNetz.getText().equals("")) {
				
				int option0 = JOptionPane.showConfirmDialog(this, "<html>Möchten Sie eventuelle Änderungen<br>" +
						"am Petrinetz speichern?", "Änderungen speichern?", JOptionPane.YES_NO_OPTION);
				
				if(option0 == JOptionPane.YES_OPTION) {
					if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
						erstVorgangBeenden();
						return;
					} else {
						dateiKlasse.sofortSpeichern(model);
						System.exit(0);
					}					
				}else if(option0 == JOptionPane.NO_OPTION) {
					System.exit(0);
				}else if(option0 == JOptionPane.CLOSED_OPTION){
					return;
				}
				
				
			//prüfen ob sich ein nicht geladenes und nicht leeres Netz in der Zeichenfläche befindet
			} else if(nameNetz.getText().equals("") && (model.Trans_Liste.size() > 0 ||
					model.Stellen_Liste.size() > 0)) {

				int option1 = JOptionPane.showConfirmDialog(this, "<html>Möchten Sie das Petrinetz speichern?", "Petrinetz speichern?",
						JOptionPane.YES_NO_OPTION);
				
				if(option1 == JOptionPane.NO_OPTION) {
					System.exit(0);
				}else if(option1 == JOptionPane.CLOSED_OPTION) {
					return;
				}else if(option1 == JOptionPane.YES_OPTION) {
					if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
						erstVorgangBeenden();
						return;
					}else {					
						dateiKlasse.saveToFileSystem(model);
						if(!dateiKlasse.abbruch) {
							System.exit(0);
						}						
					}				
				}
				
				
			//sofort beenden wenn Zeichenflaeche leer ist
			} else if(nameNetz.getText().equals("") && (model.Trans_Liste.size() == 0) &&
					(model.Stellen_Liste.size() == 0)) {
				System.exit(0);
			}
		}	
		
		
		/**
		 * Groesse der Zeichenflaeche verändern
		 */
		if(e.getActionCommand() == "zeichenfGroe") {
			if(model.getLoeschKenn() || model.getVerschKenn() || model.getMehrVerschieben()) {
				erstVorgangBeenden();
			} else {
				clearForeground();
				model.setAnfang();
				einstell = new Einstellungen(zeichenfl, model, true);
			}			
		}		
		
	}
	
	
	
	
	/** 
	 * Deaktiviert alle Buttons. Wird z.B. verwendet bei Klick auf einen Button,
	 * damit bevor dieser aktiviert wird, alle anderen deaktiviert werden.
	 */
	 
	public void clearForeground() {
		trans.setForeground(Color.black);		
		stelle.setForeground(Color.black);
		kante.setForeground(Color.black);
		namen.setForeground(Color.black);
		marke.setForeground(Color.black);
		markeAnzahl.setForeground(Color.black);
		loeschen.setForeground(Color.black);
		loeschKenn.setForeground(Color.black);
		mehrLoesch.setForeground(Color.black);
		versch.setForeground(Color.black);
		mehrVerschMark.setForeground(Color.black);
		verschiebe.setForeground(Color.black);
		fixNetz.setForeground(Color.black);
		beenden.setForeground(Color.black);
		
		trans.setBackground(origFarbe);		
		stelle.setBackground(origFarbe);
		kante.setBackground(origFarbe);
		namen.setBackground(origFarbe);
		marke.setBackground(origFarbe);
		markeAnzahl.setBackground(origFarbe);
		loeschen.setBackground(origFarbe);
		loeschKenn.setBackground(origFarbe);
		mehrLoesch.setBackground(origFarbe);
		versch.setBackground(origFarbe);
		mehrVerschMark.setBackground(origFarbe);
		verschiebe.setBackground(origFarbe);
		fixNetz.setBackground(origFarbe);
		beenden.setBackground(origFarbe);
		
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	
	public void valueChanged(ListSelectionEvent e) {	
		//do nothing
	}
	
	
	/**
	 * Wenn gerade ein Löschvorgang oder Verschiebevorgang mehrerer Elemente
	 * begonnen wurde, muß dieser zuerst beendet werden, bevor andere Operationen
	 * gestartet werden können.
	 */
	public void erstVorgangBeenden() {
		if(model.getLoeschKenn()) {
			JOptionPane.showMessageDialog(null, "<html>Bitte beenden Sie zuerst Ihren Löschvorgang!</html>");
		}
		else if(model.getVerschKenn()) {
			JOptionPane.showMessageDialog(null, "<html>Bitte beenden Sie zuerst den Vorgang des<br>" +
					"Verschiebens mehrerer Elemente!</html>");
		}
		else if(model.getMehrVerschieben()) {
			JOptionPane.showMessageDialog(null, "<html>Bitte beenden Sie zuerst den Vorgang des<br>" +
					"Verschiebens mehrerer Elemente!</html>");
		}
	}	
}