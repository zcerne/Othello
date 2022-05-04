package inteligenca;

import logika.Igra;
import logika.Igralec;
import logika.Polje;

public class OceniPozicijo {
	
	// Metoda oceniPozicijo za igro TicTacToe
	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		int crni = igra.rezultat.get(Polje.CRN);
		int beli = igra.rezultat.get(Polje.BEL);
		
		if (jaz == Igralec.CRN) return crni - beli;
		else return beli - crni;
			
	}
	
	/*public static int oceniVrsto (Vrsta v, Igra igra, Igralec jaz) {
		Polje[][] plosca = igra.getPlosca();
		int count_X = 0;
		int count_O = 0;
		for (int k = 0; k < Igra.N && (count_X == 0 || count_O == 0); k++) {
			switch (plosca[v.x[k]][v.y[k]]) {
			case O: count_O += 1; break;
			case X: count_X += 1; break;
			case PRAZNO: break;
			}
		}
		if (count_O > 0 && count_X > 0) { return 0; }
		else if (jaz == Igralec.O) { return count_O - count_X; }
		else { return count_X - count_O; }
	}*/
	

}


