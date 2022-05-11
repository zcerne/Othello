package inteligenca;

import java.util.List;

import logika.Igra;
import logika.Igralec;
import splosno.Poteza;

public class Minimax extends Inteligenca {
	
	private static final int ZMAGA = 100; // vrednost zmage
	private static final int ZGUBA = -ZMAGA;  // vrednost izgube
	private static final int NEODLOC = 0;  // vrednost neodločene igre	
	
	private int globina;
	
	public Minimax (int globina) {
		this.globina = globina;
	}
	
	@Override
	public Poteza izberiPotezo (Igra igra) {
		OcenjenaPoteza najboljsaPoteza = minimax(igra, this.globina, igra.naVrsti);
		return najboljsaPoteza.poteza;
	}
	
	// vrne najboljso ocenjeno potezo z vidike igralca jaz
	public OcenjenaPoteza minimax(Igra igra, int globina, Igralec jaz) {
		OcenjenaPoteza najboljsaPoteza = null;
		List<Poteza> moznePoteze = igra.dovoljenePoteze();
		int ocena;
		
		for (Poteza p: moznePoteze) {
			//System.out.println(p);
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigraj (p);
			
			switch (kopijaIgre.stanjeIgre()) {
			case ZMAGA_BEL: ocena = (jaz == Igralec.BEL ? ZMAGA : ZGUBA); break;
			case ZMAGA_CRN: ocena = (jaz == Igralec.CRN ? ZMAGA : ZGUBA); break;
			case NEODLOCENO: ocena = NEODLOC; break;
			default:
				// nekdo je na potezi
				//System.out.println(globina);
				if (globina == 1) ocena = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
				// globina > 1
				else ocena = minimax(kopijaIgre, globina-1, jaz).ocena;
				
				//System.out.println("ocena " + ocena);
			}
			//System.out.println(ocena);
				if (najboljsaPoteza == null
					// max, če je p moja poteza
					|| jaz == igra.naVrsti && ocena > najboljsaPoteza.ocena
					// sicer min
					|| jaz != igra.naVrsti && ocena < najboljsaPoteza.ocena)
					najboljsaPoteza = new OcenjenaPoteza (p, ocena);
		}
		//System.out.println(najboljsaPoteza); 
		if(najboljsaPoteza == null) {
			if(igra.naVrsti == jaz) ocena = -100;
			else ocena = 100;
			najboljsaPoteza = new OcenjenaPoteza(null, ocena);
		}
		return najboljsaPoteza;
	}
}