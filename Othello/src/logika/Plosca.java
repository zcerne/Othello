package logika;

import java.util.ArrayList;

public class Plosca {
	public int[][] polja;
	public int naVrsti;
	
	public Plosca() {
		
		polja = new int[8][8];
		
		polja[3][3] = -1;
		polja[3][4] = 1;
		polja[4][3] = 1;
		polja[4][4] = -1;
		naVrsti = -1;
		
	}
	
	public ArrayList<int[]> izvediPotezo(int ii, int jj) {
		ArrayList<int[]> izbrani = new ArrayList<>();
		if (polja[ii][jj] == 0) {
			//System.out.println("Glavni loop");
			int[][] smeri = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1,1}, {-1,1}, {1,-1}, {-1,-1}}; // vse smeri pregleda
			
			for(int[] smer : smeri) { //ppregled v vseh smereh
				int smerX = smer[0];
				int smerY = smer[1];
				int premik = 1;
				ArrayList<int[]> izbraniVSmeri = new ArrayList<>(); // seznam vseh dobljenih krogcev/točk... v tej smeri
				//System.out.println("smer" + smerX + "  " + smerY);
				while (ii + premik*smerX >= 0 && ii + premik*smerX < 8 && jj + premik*smerY >= 0 && jj + premik*smerY < 8){
					// ustvarim koordinato
					int[] izb = {ii + premik*smerX, jj + premik*smerY}; 
					
					
					//če je nasledji prazen pogledam nasledjo vrstico
					if (polja[ii + premik*smerX][jj + premik*smerY] == 0) {
						izbraniVSmeri.clear();
						break;
					}
					//če je nasledji od nasprotnika si ga shranim in nadaljujem v liniji
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti*-1) {
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
	
	public ArrayList<int[]> dovoljenePoteze(){ // izvede metodo izvediPotezo za vsa polja in gleda kdaj je rezultat večji od nič
		ArrayList<int[]> volni = new ArrayList<>();
		ArrayList<ArrayList<int[]>> listList = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				ArrayList<int[]> izbrani = izvediPotezo(i, j);
				int[] izb = {i, j};
				if (izbrani.size() != 0) {
					volni.add(izb);
					listList.add(izbrani);
				}
			}
		}
		
		return volni;
	}
}
