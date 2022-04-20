package logika;

import java.util.ArrayList;

import splosno.Poteza;

public class Igra {
	
	public Polje[][] polja;
	public Polje naVrsti;
	
	public Igra() {
		polja = new Polje[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				polja[i][j] = Polje.PRAZNO;
			}
		}
		
		polja[3][3] = Polje.CRN;
		polja[3][4] = Polje.BEL;
		polja[4][3] = Polje.BEL;
		polja[4][4] = Polje.CRN;
		naVrsti = Polje.CRN;
		
	}
	
	public boolean odigraj(Poteza poteza) {
		ArrayList<Poteza> dobljeniZetoni = izvediPotezo(poteza);
		if(dobljeniZetoni.size() != 0) return true;
		
		return false;
	}
	
	public ArrayList<Poteza> izvediPotezo(Poteza poteza) {
		ArrayList<Poteza> izbrani = new ArrayList<>();
		int ii = poteza.getX();
		int jj = poteza.getY();
		if (polja[ii][jj] == Polje.PRAZNO) {
			//System.out.println("Glavni loop");
			int[][] smeri = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1,1}, {-1,1}, {1,-1}, {-1,-1}}; // vse smeri pregleda
			
			for(int[] smer : smeri) { //ppregled v vseh smereh
				int smerX = smer[0];
				int smerY = smer[1];
				int premik = 1;
				ArrayList<Poteza> izbraniVSmeri = new ArrayList<>(); // seznam vseh dobljenih krogcev/točk... v tej smeri
				//System.out.println("smer" + smerX + "  " + smerY);
				while (ii + premik*smerX >= 0 && ii + premik*smerX < 8 && jj + premik*smerY >= 0 && jj + premik*smerY < 8){
					// ustvarim koordinato
					Poteza izb = new Poteza(ii + premik*smerX, jj + premik*smerY); 
					
					//če je nasledji prazen pogledam nasledjo vrstico
					if (polja[ii + premik*smerX][jj + premik*smerY] == Polje.PRAZNO) {
						izbraniVSmeri.clear();
						break;
					}
					//če je nasledji od nasprotnika si ga shranim in nadaljujem v liniji
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti.obrat()) {
						izbraniVSmeri.add(izb); 
						premik ++;
					}
					//če je naslednji moj, zaključim s to linijo in dodam vse, dobljene točke v izbrani
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti) {
						izbrani.addAll(izbraniVSmeri);
						break;
					}
				}
			}	
			
		}
		return izbrani; //vrnem vse žetone, ki jih s to potezo dobim
	}
	
	public ArrayList<Poteza> dovoljenePoteze(){ // izvede metodo izvediPotezo za vsa polja in gleda kdaj je rezultat večji od nič
		ArrayList<Poteza> volni = new ArrayList<>();
		ArrayList<ArrayList<int[]>> listList = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Poteza poteza = new Poteza(i,j);
				boolean dovoljena = odigraj(poteza);
				Poteza izb = new Poteza(i,j);
				if (dovoljena) {
					volni.add(izb);
					//listList.add(izbrani); #to bi mogl bit vsi k jih obrnem s to potezo
				}
			}
		}
		
		return volni;
	}
}
