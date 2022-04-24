package vodja;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import gui.Okno;
import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.Poteza;

public class Vodja {

	VrstaIgralca naVrsti;
	
	public static Okno okno;
	public static Igra igra;
	
	public static Map<Igralec,VrstaIgralca> vrstaIgralca;

	public static boolean clovekNaPotezi;
	
	//požene se na začetku igre.
	public static void igramoNovoIgro () {
		igra = new Igra ();
		igramo ();
	}
	
	//preverja kdo ali kaj je na vrsti in ja...
	public static void igramo() {
		igra.naPotezi();
		igra.prestejTocke();
		okno.repaint();
		System.out.println("Rezultat: " + "CRNI: " + igra.rezultat.get(Polje.CRN) + "       BELI: " + igra.rezultat.get(Polje.BEL));
		switch(igra.stanjeIgre()) {
		case NEODLOCENO:
			System.out.println("NEODLOČENO");
			return;
		case ZMAGA_BEL:
			System.out.println("BELI ZMAGA");
			return;
		case ZMAGA_CRN:
			System.out.println("ČRNI ZMAGA");
			return;
		case V_TEKU:
			
			if(moznost()) { //te neumne možnosti so narjene mal chonky. Igraj samo če imaš možnost. Če je nimaš bo možnost() zamenjala igralca. 
				VrstaIgralca vrstaNaVrsti = vrstaIgralca.get(igra.naVrsti);
				switch(vrstaNaVrsti) {
				case C:
					clovekNaPotezi = true;
					break;
				case R: racunalnikovaPoteza();
					break;
					
				}
			}
			else {
			igra.naVrsti = igra.naVrsti.obrat();
			igramo();
			}
		}
		
	}
	// sleep scene kokr je naredu profesor
	private static void racunalnikovaPoteza() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
			@Override
			protected Void doInBackground() {
				try {TimeUnit.MILLISECONDS.sleep(10);} catch (Exception e) {};	
				return null;
			}
			@Override
			protected void done () {
				
				Poteza racPoteza = igra.racunalnikovaPoteza();
				//if(racPoteza == null) {
					//igra.naVrsti.obrat();
					//igramo();
				//}
				igrajPotezo(racPoteza);
			}
		};
		worker.execute();
	}
	
	
	public static void igrajPotezo(Poteza poteza) {
		if(igra.odigraj(poteza)) {
			
			igra.naVrsti = igra.naVrsti.obrat(); // zamenja igralca
			clovekNaPotezi = false;
		}
		igramo();
	}

	public static boolean moznost() {
		if(!igra.moznost()) {
			igra.stejMoznosti += 1;
			System.out.println("Nimaš možnosti");
		}
		else {
			igra.stejMoznosti = 0;
		}
		return igra.moznost();
		
		}
		
		
	}
