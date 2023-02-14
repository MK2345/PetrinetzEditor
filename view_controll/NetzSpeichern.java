package view_controll;




import java.io.*;
import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import model.Model;

/**
 * Klasse zum Speichern und Laden von Petrinetzen.
 * 
 * @version 29.10.2011 
 * @author Manuela Koller, Matrikelnummer q6900399
 *
 */

public class NetzSpeichern {
	
	/**
	 * zum Laden und Speichern von Petrinetzen
	 */
	JFileChooser fileChooser;
	
	/**
	 * Referenz zur Klasse Model
	 */
	Model model;
	
	/**
	 * zur Zwischenspeicherung der Daten des geladenen Petrinetzes
	 */
	Model modelLoad;	
	
	/**
	 * Datei welche durch JFileChooser geladen oder gespeichert wird
	 */
	File file;
	
	/**
	 * true wenn kein Netz geladen oder gespeichert, sondern Vorgang abgebrochen wurde
	 */
	boolean abbruch = false;
	
	
	/**
	 * Konstruktur
	 */
	public NetzSpeichern() {
		
		fileChooser = new JFileChooser(new File("."));
		fileChooser.setFileFilter(new SuperFileFilter());
		
	}		
			
	
	
	/**
	 * speichert das aktuelle Netz, welches dabei benannt werden muß.
	 * @param model  beinhaltet die Daten des aktuellen Netzes
	 */
	public void saveToFileSystem(Model model) {
		
		this.model = model;
		int retval = fileChooser.showSaveDialog(null);
		if(retval == JFileChooser.APPROVE_OPTION) {	
			
			file = fileChooser.getSelectedFile();
			if(!file.getName().endsWith(".txt")) {
				file = new File(file.getPath() + ".txt");				
			}
			try {
				FileOutputStream fs = new FileOutputStream(file);
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(model);
				abbruch = false;
				fs.close(); 
				os.close(); 
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else if(retval == JFileChooser.CANCEL_OPTION){
			
			abbruch = true;
			
			return;
		}		
	}
	
	/**
	 * Speichert aktuelles Petrinetz wenn dieses bereits 
	 * gespeichert auf Festplatte vorliegt, aber zwischenzeitlich
	 * eine Änderung vorgenommen wurde.
	 * @param model  beinhaltet die Daten des aktuellen Netzes
	 */
	public void sofortSpeichern(Model model) {
		this.model = model;		
		
			file = fileChooser.getSelectedFile();
			if(!file.getName().endsWith(".txt")) {
				file = new File(file.getPath() + ".txt");				
			}
			try {
				FileOutputStream fs = new FileOutputStream(file);
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(model);
				
				fs.close(); 
				os.close(); 
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}		
	}

	
	
	/**
	 * lädt ein gespeichertes Netz
	 */
	public void loadfromFileSystem() {			
		try  {
			int get = fileChooser.showOpenDialog(null);
			if(get == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();								
				FileInputStream fi = new FileInputStream(file);
				ObjectInputStream is = new ObjectInputStream(fi);				
				try {
					modelLoad = (Model) is.readObject();					
					
				} catch (ClassNotFoundException e) {					
					e.printStackTrace();					
				}				
			} if(get == JFileChooser.CANCEL_OPTION) {
				abbruch = true;				
			}
			
		}catch(FileNotFoundException e) {			
			
		}catch(IOException e){		
			
		}
	}	
	

	/**
	 * Wird vom JFileChooser benutzt um Dateien mit Endung .txt zu zeigen.
	 * @author mk
	 *
	 */
	private class SuperFileFilter extends FileFilter {
		
		public boolean accept(File f) {
			
			if(f.isDirectory()) {
				return true;
			}
			return f.getName().toLowerCase().endsWith(".txt");
		}		
		public String getDescription() {
			
			return "Alle Dateien";
		}
	}
}
