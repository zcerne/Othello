package logika;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import splosno.Poteza;

public class Igra {
	
	public Polje[][] polja;
	public Igralec naVrsti;
	
	private static Random random = new Random ();
	
	public static int N;
	public int stejMoznosti;
	
	public Map<Polje, Integer> rezultat;
	
	public Igra() {
		
		N = 8;
		polja = new Polje[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				polja[i][j] = Polje.PRAZNO;
			}
		}
		
		polja[N/2-1][N/2-1] = Polje.CRN;
		polja[N/2-1][N/2] = Polje.BEL;
		polja[N/2][N/2-1] = Polje.BEL;
		polja[N/2][N/2] = Polje.CRN;
		
		naVrsti = Igralec.CRN;
		
		stejMoznosti = 0;
		
		rezultat = new EnumMap<Polje, Integer>(Polje.class);
		rezultat.put(Polje.CRN, 2);
		rezultat.put(Polje.BEL, 2);
		
	}
	
	public Igra(Igra igra) {
		this.polja = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.polja[i][j] = igra.polja[i][j];
			}
		}
		
		this.rezultat = igra.rezultat;
		this.naVrsti = igra.naVrsti;
	}
	
	public boolean odigraj(Poteza poteza) {
		if(poteza != null) {
			ArrayList<Poteza> dobljeniZetoni = izvediPotezo(poteza);
			if(dobljeniZetoni.size() != 0) {
				int i = poteza.getX();
				int j = poteza.getY();
				polja[i][j] = naVrsti.dobiPolje(); //vrne vse krogce na katere poteza vpliva 
				for(Poteza izb : dobljeniZetoni) {
					int x = izb.getX();
					int y = izb.getY();
					polja[x][y] = naVrsti.dobiPolje(); //in jih nato obrne 
				}
				naVrsti = naVrsti.obrat();
				stejMoznosti = 0;
				return true;
			}
			else return false;
		}
		else {
			stejMoznosti +=1;
			naVrsti = naVrsti.obrat();
			return true;
		}
	}
	
	public boolean lahkoOdigram(Poteza poteza) {
		ArrayList<Poteza> dobljeniZetoni = izvediPotezo(poteza);
		if(dobljeniZetoni.size() != 0) {//vrne vse krogce na katere poteza vpliva 
			
			return true;
		}
		
		return false;
	}
	public void naPotezi() {
		//System.out.println(naVrsti); 
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
				while (ii + premik*smerX >= 0 && ii + premik*smerX < N && jj + premik*smerY >= 0 && jj + premik*smerY < N){
					// ustvarim koordinato
					Poteza izb = new Poteza(ii + premik*smerX, jj + premik*smerY); 
					
					//če je nasledji prazen pogledam nasledjo vrstico
					if (polja[ii + premik*smerX][jj + premik*smerY] == Polje.PRAZNO) {
						izbraniVSmeri.clear();
						break;
					}
					//če je nasledji od nasprotnika si ga shranim in nadaljujem v liniji
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti.obrat().dobiPolje()) {
						izbraniVSmeri.add(izb); 
						premik ++;
					}
					//če je naslednji moj, zaključim s to linijo in dodam vse, dobljene točke v izbrani
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti.dobiPolje()) {
						izbrani.addAll(izbraniVSmeri);
						break;
					}
				}
			}	
			
		}
		return izbrani; //vrnem vse žetone, ki jih s to potezo dobim
	}
	
	public ArrayList<Poteza> dovoljenePoteze(){ 
		ArrayList<Poteza> volni = new ArrayList<>();
		//ArrayList<ArrayList<int[]>> listList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Poteza poteza = new Poteza(i,j);
				boolean dovoljena = lahkoOdigram(poteza);
				if (dovoljena) {
					volni.add(poteza);
					//listList.add(izbrani); #to bi mogl bit vsi k jih obrnem s to potezo
				}
			}
		}
		
		return volni;
	}
	
	public void prestejTocke() {
		int crni = 0;
		int beli = 0;
		
		for (Polje[] poljei : polja) {
			for (Polje poljeij : poljei) {
				switch(poljeij) {
				case BEL: beli += 1;
				break;
				case CRN: crni += 1;
				break;
				case PRAZNO:
				break;
				}
			}
		}
		rezultat.put(Polje.CRN, crni);
		rezultat.put(Polje.BEL, beli);
	}
	
	public String rezultat() {
		System.out.println("CRNI: " + rezultat.get(Polje.CRN) + "  BELI: " + rezultat.get(Polje.BEL));
		return "CRNI: " + rezultat.get(Polje.CRN) + "       BELI: " + rezultat.get(Polje.BEL);
		
	}
	
	public boolean moznost() {
		ArrayList<Poteza> poteze = dovoljenePoteze();
		if(poteze.size() == 0) { // če pač ni možnosti ni možnosti in spet zamenja igralca
			return false;
		}
		return true;	
	}
	
	//vrne stanje igre. Očitno je mogoče da se celotna plošča sploh ne zapolni. Zato tok komplikacij. Za primer če noben nima možnosti sem "preprosto" (lol) vpelajl counter nemožnosti.
	public Stanje stanjeIgre() {		
		if(rezultat.get(Polje.BEL) + rezultat.get(Polje.CRN) == N*N || stejMoznosti >= 2) {
			
			if(stejMoznosti >= 2)System.out.println("Noben nima možnosti");
			
			if(rezultat.get(Polje.CRN) < rezultat.get(Polje.BEL)) return Stanje.ZMAGA_BEL;
			else if (rezultat.get(Polje.CRN) > rezultat.get(Polje.BEL)) return Stanje.ZMAGA_CRN;
			else if (rezultat.get(Polje.CRN) == rezultat.get(Polje.BEL)) return Stanje.NEODLOCENO;
			
		}
		else if(rezultat.get(Polje.BEL) + rezultat.get(Polje.CRN) < N*N ) return Stanje.V_TEKU;
		System.out.println("Neki ne dela v stanjeIgre. Vračam NULL.");
		return null;
	}

	//ne štekam fore statičnih metod. Tko je naredu profesor. Ne vem zaklajajajndjasbfdhuasfvhdsga.
	public Polje[][] getPlosca() {
		
		return polja;
	}
	
}
