package inteligenca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Stanje;
import splosno.Poteza;


public class MCTS2 extends Inteligenca {
	
	private static Random random = new Random ();
	
	private int obseg, count;
	Igralec jaz;
	
	public MCTS2 (int obseg) {
		this.obseg = obseg;
	}
	
	

	@Override
	public Poteza izberiPotezo (Igra igra) {
		this.count = 0;
		Igra g = new Igra(igra);
		Poteza najboljsaPoteza = monte_carlo_tree_search(g, this.obseg, g.naVrsti);
		return najboljsaPoteza;	
	}
	
	public Poteza monte_carlo_tree_search(Igra igra, int globina, Igralec jaz) {
		this.jaz = jaz;
		Veja root = new Veja(igra, 0 , 0, 0, null);
		
		while (count < obseg) {
			Veja list = izberivejo(root);

	        Stanje rezultat = simulacija(list);

	        backpropagate(list, rezultat);

	        count++;
	        }

	    Veja node = best_child(root);

	    return node.getPoteza();

	}
	    
	public Veja izberivejo(Veja node){
		
	    while (node.otroci.size() != 0){
	    	node = UCT.findBestNodeWithUCT(node);
	    }
	    
	    return pick_univisted(node); // in case no children are present / node is terminal
 }
	public Veja pick_univisted(Veja node) {
		if (!node.jeKoncna() && node.otroci.size() == 0) {
			node.ustvariOtroke();
			
			
			int i = (int) ((Math.random() * node.otroci.size()));
			
			return node.otroci.get(i);	
		}
		else if(node.isterminal()){
			return node; 
			
		}
    	else {
    		for (Veja sin : node.otroci) {
        		if (sin.visits == 0) return sin;
        	}
    	return node;

    	}
	}
	    
	
	public Stanje simulacija(Veja node) {
		Igra odigraj = new Igra(node.igra);

		while (odigraj.stanjeIgre == Stanje.V_TEKU) {
			int dolzina = odigraj.dovoljenePoteze.size();
			Poteza poteza = null;
			if(dolzina != 0) {
				int randomIndex = random.nextInt(dolzina);
				poteza = odigraj.dovoljenePoteze.get(randomIndex);
			}
			else if(dolzina == 0)System.out.println("PROBLEM V MCTS Simulacija 102!!!!");
			
			odigraj.odigraj(poteza);
			
			}
		return odigraj.stanjeIgre;
		}
	
	
	public void backpropagate(Veja node, Stanje stanje) {
		while (node.tata != null) {
			if (node.igra.naVrsti == jaz.obrat()){
				if(stanje == Stanje.ZMAGA_CRN && jaz == Igralec.CRN) node.wins++;
				else if (stanje == Stanje.ZMAGA_BEL && jaz == Igralec.BEL) node.wins++;
				
			}
			else if (stanje == Stanje.NEODLOCENO) node.wins += 0.5;
			node.visits++;
			node = node.tata;
			
		}	
		if (node.tata == null) {
			if(stanje == Stanje.ZMAGA_CRN && jaz == Igralec.CRN) node.wins++;
			else if (stanje == Stanje.ZMAGA_BEL && jaz == Igralec.BEL) node.wins++;
			else if (stanje == Stanje.NEODLOCENO) node.wins += 0.5;
			node.visits++;
		}
		
			
			
	}
	
	public Veja best_child(Veja root) {
		
		 return Collections.max(
		          root.otroci,
		          Comparator.comparing(c -> c.wins));
		    }
	
	
		/*Veja najjaci = null;
		double obiski = 0;
		for (Veja v : root.otroci) {
			if (v.wins >= obiski) {
				najjaci = v;
				obiski = v.wins;
			}
		}
		return najjaci;
	}*/
}
	
	
	
	
	
	
	

