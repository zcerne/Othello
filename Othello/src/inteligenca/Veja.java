package inteligenca;

import java.util.ArrayList;

import logika.Igra;
import logika.Igralec;
import splosno.Poteza;

public class Veja {
	
	public Igra igra;
	public double wins;
	public int visits;
	public Veja tata;
	public ArrayList<Veja> otroci;
	public Poteza poteza = null;

	public Veja(Igra igra, int visits, double wins, Veja stars) {
		this.igra = igra;
		this.visits = visits;
		this.wins = wins;
		this.tata = stars;
		this.otroci = new ArrayList<Veja>();
		
		
	}
	public void makebaby() {
		for (Poteza p : igra.dovoljenePoteze()) {
			Igra od_p = new Igra(igra);
			od_p.izvediPotezo(p);
			Veja baby = new Veja(od_p, 0 ,0, this);
			baby.poteza = p;
			this.otroci.add(baby);
		}
	}
	
    public Igra getIgra() {
        return igra;
    }
    
    public boolean isterminal() {
    	if (igra.dovoljenePoteze() == null) return true;
    	else return false;
    }
    
    public boolean fullyexpanded() {
    	
    	
    
    	if (this.otroci.size() == 0) return false;
    	else {
    		for (Veja sin : this.otroci) {
        		if (sin.visits == 0) return false;
        		//imam pripombe, bo žiga razmislil
        		// ga bom prepričal
        	
        	}
        	return true;
    	}
    	
    	
    }
    
    void setIgra(Igra Igra) {
        this.igra = Igra;
    }

    public Igralec getPlayer() {
        return igra.naVrsti;
    }
    public Poteza getPoteza() {
    	return poteza;
    }

//    public int visits() {
//        return visits;
//    }

    public void setvisits(int visits) {
        this.visits = visits;
    }

    double wins() {
        return wins;
    }

    void setwins(double wins) {
        this.wins = wins;
    }

}
