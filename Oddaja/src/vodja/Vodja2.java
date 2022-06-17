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
import gui.VrstaGumba;
import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.Poteza;
import inteligenca.Inteligenca;
import inteligenca.MCTS2;
import inteligenca.Minimax;


public class Vodja2 {

	VrstaIgralca naVrsti;
	
	public static Okno okno;
	public static Igra igra;
	public static Map<Igralec,VrstaIgralca> vrstaIgralca;
	
	public static ArrayList<Igra> zgodovina;

	public static boolean clovekNaPotezi;
	
	public static StanjeZaslona stanjeZaslona;
	
	//požene se na začetku igre.
	
	public static void igramoNovoIgro2 () {
		igra = new Igra ();
		zgodovina = new ArrayList<Igra>();
		zgodovina.add(new Igra(igra));
		igramo2 ();
	}
	
	//preverja kdo ali kaj je na vrsti in ja...
	public static void igramo2() {
		//igra.rezultat();
		//okno.repaint();
		if (okno != null) okno.osveziGUI();
		
		switch(igra.stanjeIgre) {
		case NEODLOCENO:
			//System.out.println("NEODLOČENO");
			return;
		case ZMAGA_BEL:
			//System.out.println("BELI ZMAGA");
			//igra.rezultat();
			return;
		case ZMAGA_CRN:
			//System.out.println("ČRNI ZMAGA");
			//igra.rezultat();
			return;
		case V_TEKU:
			
				VrstaIgralca vrstaNaVrsti = vrstaIgralca.get(igra.naVrsti);
				switch(vrstaNaVrsti) {
				case C:
					clovekNaPotezi = true;			 
					break;
				case R: racunalnikovaPoteza2();
					
					break;
				
			}
		}
	}
	// sleep scene kokr je naredu profesor.
	
	public static Inteligenca inteligenca = new Inteligenca();
	public static Minimax minimax = new Minimax(5);
	public static MCTS2 mcts2 = new MCTS2(4000);

	
	private static void racunalnikovaPoteza2() {
			
				
				Poteza racPoteza = null;
				
				switch(igra.naVrsti) {
				case CRN: 
					racPoteza = mcts2.izberiPotezo(igra);
					break;
				case BEL: 
					racPoteza = inteligenca.izberiPotezo(igra);


					break;
				}
				igrajPotezo2(racPoteza);
	}
	
	public static void igrajPotezo2(Poteza poteza) {
		if(igra.odigraj(poteza)) {
			//igra.naVrsti = igra.naVrsti.obrat(); // zamenja igralca
			zgodovina.add(new Igra(igra));
			clovekNaPotezi = false;
		}
		igramo2();
	}

	}
