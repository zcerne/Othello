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
import inteligenca.MCTS;
import inteligenca.Minimax;


public class Vodja {

	VrstaIgralca naVrsti;
	
	public static Okno okno;
	public static Igra igra;
	public static Map<Igralec,VrstaIgralca> vrstaIgralca;
	
	public static ArrayList<Igra> zgodovina;

	public static boolean clovekNaPotezi;
	
	public static StanjeZaslona stanjeZaslona;
	
	//požene se na začetku igre.
	
	public static void gumb(VrstaGumba gumb) {
		switch(gumb) {
		
		case II:
			
			//okno.platnoMenu.setVisible(false);
			//okno.platnoIgra.setVisible(true);
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.C); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.C);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			break;
			
		case IR :
			//okno.platnoMenu.setVisible(false);
			//okno.platnoIgra.setVisible(true);
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.C); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			break;
		
		case RI :
			//okno.platnoMenu.setVisible(false);
			//okno.platnoIgra.setVisible(true);
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.C);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			break;
			
		
		case RR :
			//okno.platnoMenu.setVisible(false);
			//okno.platnoIgra.setVisible(true);
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();		
		
		case UNDO : 
			int ind = Vodja.zgodovina.size() - 1;
			int di = 0;
				if(Vodja.vrstaIgralca.get(Igralec.CRN) == VrstaIgralca.R || Vodja.vrstaIgralca.get(Igralec.BEL) == VrstaIgralca.R) di = 2;
				else if (Vodja.vrstaIgralca.get(Igralec.CRN) == VrstaIgralca.C && Vodja.vrstaIgralca.get(Igralec.BEL) == VrstaIgralca.C) di = 1;
				
				if(ind >= di) {
				Vodja.igra = new Igra(Vodja.zgodovina.get(ind-di));
				Vodja.zgodovina.remove(ind);
				if(di == 2) Vodja.zgodovina.remove(ind-1);
				igramo();
				}
				break;
		case MENU:
			stanjeZaslona = StanjeZaslona.MENU;
			
			break;
		default:
			break;
		}
	}
	

	public static void igramoNovoIgro () {
		igra = new Igra ();
		zgodovina = new ArrayList<Igra>();
		zgodovina.add(new Igra(igra));
		igramo ();
	}
	
	//preverja kdo ali kaj je na vrsti in ja...
	public static void igramo() {
		//igra.rezultat();
		okno.repaint();
		if (okno != null) okno.osveziGUI();
		if(stanjeZaslona == StanjeZaslona.MENU) {
			igra = null;
			okno.osveziGUI();
			return;
		}
		
		switch(igra.stanjeIgre) {
		case NEODLOCENO:
			System.out.println("NEODLOČENO");
			return;
		case ZMAGA_BEL:
			System.out.println("BELI ZMAGA");
			//igra.rezultat();
			return;
		case ZMAGA_CRN:
			System.out.println("ČRNI ZMAGA");
			//igra.rezultat();
			return;
		case V_TEKU:
			
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
	// sleep scene kokr je naredu profesor.
	
	public static Inteligenca inteligenca = new Inteligenca();
	public static Minimax minimax = new Minimax(1);
	public static MCTS mcts = new MCTS(2);

	
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
					racPoteza = mcts.izberiPotezo(igra);
					break;
				}
				igrajPotezo(racPoteza);
				//System.out.println("stanje: " + igra.stanjeIgre);
				
				}

		};
		worker.execute();
	}
	
	
	public static void igrajPotezo(Poteza poteza) {
		if(igra.odigraj(poteza)) {
			//igra.naVrsti = igra.naVrsti.obrat(); // zamenja igralca
			zgodovina.add(new Igra(igra));
			clovekNaPotezi = false;
		}
		igramo();
	}


	}
