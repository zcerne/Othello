package vodja;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import gui.Gumb;
import gui.Okno;
import gui.StanjeZaslona;
import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.Poteza;
import inteligenca.Inteligenca;
import inteligenca.Minimax;


public class Vodja {

	VrstaIgralca naVrsti;
	
	public static Okno okno;
	public static Igra igra;
	public static Map<Igralec,VrstaIgralca> vrstaIgralca;

	public static boolean clovekNaPotezi;
	
	public static StanjeZaslona stanjeZaslona;
	
	//požene se na začetku igre.
	
	public static void dolociIgralce(String gumb) {
		if (gumb == "IGRA1") {
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.C); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.C);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			
		}
		else if (gumb == "IGRA2") {
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.C);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			
		}
		else if (gumb == "IGRA3") {
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.C); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			
		}
		else if (gumb == "IGRA4") {
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			
		}
	}
	public static void igramoNovoIgro () {
		igra = new Igra ();
		igramo ();
	}
	
	//preverja kdo ali kaj je na vrsti in ja...
	public static void igramo() {
		igra.naPotezi();
		igra.prestejTocke();
		//igra.rezultat();
		//okno.repaint();
		okno.osveziGUI();
		
		switch(igra.stanjeIgre()) {
		case NEODLOCENO:
			System.out.println("NEODLOČENO");
			return;
		case ZMAGA_BEL:
			System.out.println("BELI ZMAGA");
			igra.rezultat();
			return;
		case ZMAGA_CRN:
			System.out.println("ČRNI ZMAGA");
			return;
		case V_TEKU:
			if(!moznost()) igrajPotezo(null);
			else {
 //te neumne možnosti so narjene mal chonky. Igraj samo če imaš možnost. Če je nimaš bo možnost() zamenjala igralca. 
				VrstaIgralca vrstaNaVrsti = vrstaIgralca.get(igra.naVrsti);
				switch(vrstaNaVrsti) {
				case C:
					clovekNaPotezi = true;
					 
					break;
				case R: racunalnikovaPoteza();
					break;
					
				}
			}
		}

	}
	// sleep scene kokr je naredu profesor
	public static Inteligenca inteligenca = new Inteligenca();
	public static Minimax minimax = new Minimax(3);
	
	private static void racunalnikovaPoteza() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
			@Override
			protected Void doInBackground() {
				try {TimeUnit.MILLISECONDS.sleep(1);} catch (Exception e) {};	
				return null;
			}
			@Override
			protected void done () {
				
				Poteza racPoteza = null;
				switch(igra.naVrsti) {
				case CRN: 
					racPoteza = inteligenca.izberiPotezo(igra);
					break;
				case BEL: 
					racPoteza = minimax.izberiPotezo(igra);
					break;
				}
				igrajPotezo(racPoteza);
				
				}

		};
		worker.execute();
	}
	
	
	public static void igrajPotezo(Poteza poteza) {
		if(igra.odigraj(poteza)) {
			//igra.naVrsti = igra.naVrsti.obrat(); // zamenja igralca
			clovekNaPotezi = false;
		}
		igramo();
	}

	public static boolean moznost() {
		if(!igra.moznost()) {
			//System.out.println(igra.stejMoznosti);
			//System.out.println(igra.naVrsti + "Nimaš možnosti");
		}
		return igra.moznost();
		
		}
	}
