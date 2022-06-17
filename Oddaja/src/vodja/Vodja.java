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


public class Vodja {

	VrstaIgralca naVrsti;
	
	public static Okno okno;
	public static Igra igra;
	public static Map<Igralec,VrstaIgralca> vrstaIgralca;
	
	public static ArrayList<Igra> zgodovina;

	public static boolean clovekNaPotezi;
	
	public static StanjeZaslona stanjeZaslona;
	
	public static VrstaGumba kdoIgraB = VrstaGumba.RAND_B;
	public static VrstaGumba kdoIgraC = VrstaGumba.RAND_C;
	
	public static Inteligenca inteligenca = new Inteligenca();
	public static Minimax minimax = new Minimax(6);
	public static MCTS2 mcts2 = new MCTS2(15000);
	
	//požene se na začetku igre. Izbere igro na podlagi pritisnjenega gumba
	public static void gumb(VrstaGumba gumb) {
		
		switch(gumb) {
		
		case II:
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.C); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.C);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			break;
			
		case IR :
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.C); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			break;
		
		case RI :
			vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R); 
			vrstaIgralca.put(Igralec.BEL, VrstaIgralca.C);
			stanjeZaslona = StanjeZaslona.IGRA;
			igramoNovoIgro();
			break;
			
		
		case RR :
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
			igramo();
			
			break;
		case MCTS_B:
			kdoIgraB = VrstaGumba.MCTS_B;
			okno.osveziGUI();
			break;
		case MCTS_C:
			kdoIgraC = VrstaGumba.MCTS_C;
			okno.osveziGUI();
			break;
		case MINIMAX_B:
			kdoIgraB = VrstaGumba.MINIMAX_B;
			okno.osveziGUI();
			break;
		case MINIMAX_C:
			kdoIgraC = VrstaGumba.MINIMAX_C;
			okno.osveziGUI();
			break;
		case RAND_B:
			kdoIgraB = VrstaGumba.RAND_B;
			okno.osveziGUI();
			break;
		case RAND_C:
			kdoIgraC = VrstaGumba.RAND_C;
			okno.osveziGUI();
			break;
		default:
			break;
			
		}
	}

	public static void igramoNovoIgro () {
		igra = new Igra ();
		zgodovina = new ArrayList<Igra>();
		zgodovina.add(new Igra(igra));
		okno.osveziGUI();
		igramo ();
	}
	
	//preverja kdo ali kaj je na vrsti in ja...
	public static void igramo() {
		if (okno != null) {

			okno.osveziGUI();
		}
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
			System.out.println(igra.rezultat());
			
			return;
		case ZMAGA_CRN:
			System.out.println("ČRNI ZMAGA");
			System.out.println(igra.rezultat());
			return;
		case V_TEKU:
			
				VrstaIgralca vrstaNaVrsti = vrstaIgralca.get(igra.naVrsti);//določimo ali je na vrsti računalnik ali človek
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
	
	private static void racunalnikovaPoteza() {
		//za kasnejši odziv računalnika
		
		Igra zacetkaIgra = igra;
		SwingWorker<Poteza, Void> worker = new SwingWorker<Poteza, Void> () {
			@Override
			protected Poteza doInBackground() {
				Poteza racPoteza = null;
				switch(igra.naVrsti) {
				case CRN:
					if (kdoIgraC == VrstaGumba.RAND_C) racPoteza = inteligenca.izberiPotezo(zacetkaIgra);
					else if (kdoIgraC == VrstaGumba.MINIMAX_C) racPoteza = minimax.izberiPotezo(zacetkaIgra);
					else if (kdoIgraC == VrstaGumba.MCTS_C) racPoteza = mcts2.izberiPotezo(zacetkaIgra);
					break;
				
				case BEL: 
					if (kdoIgraB == VrstaGumba.RAND_B) racPoteza = inteligenca.izberiPotezo(zacetkaIgra);
					else if (kdoIgraB == VrstaGumba.MINIMAX_B) racPoteza = minimax.izberiPotezo(zacetkaIgra);
					else if (kdoIgraB == VrstaGumba.MCTS_B) racPoteza = mcts2.izberiPotezo(zacetkaIgra);
					break;
				}
				//try {TimeUnit.MILLISECONDS.sleep(1);} catch (Exception e) {};	
				return racPoteza;
			}
			@Override
			protected void done () {
				Poteza racPoteza = null;
				try {racPoteza = get();} catch (Exception e) {};
				
				igrajPotezo(racPoteza);
				
				}

		};
		worker.execute();
		
	}
	public static void igrajPotezo(Poteza poteza) {
		if(igra != null && igra.odigraj(poteza)) {
			zgodovina.add(new Igra(igra));
			clovekNaPotezi = false;
		}
		igramo();
	}

}
