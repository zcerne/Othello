package inteligenca;

import logika.Igra;
import logika.Igralec;
import splosno.Poteza;

public class MCTS extends Inteligenca {
	
	private int globina;
	
	public MCTS (int globina) {
		this.globina = globina;
	}
}
	
//	@Override
//	public Poteza izberiPotezo (Igra igra) {
//		OcenjenaPoteza najboljsaPoteza = monte_carlo_tree_search(igra, this.globina, igra.naVrsti);
//		return najboljsaPoteza.poteza;	
//	}
//	
//	public monte_carlo_tree_search(Igra igra, int globina, Igralec jaz){
//	    while resources_left(time, computational power):
//	        leaf = traverse(root) # leaf = unvisited node 
//	        simulation_result = rollout(leaf)
//	        backpropagate(leaf, simulation_result)
//	    return best_child(root)
//
//	def traverse(node):
//	    while fully_expanded(node):
//	        node = best_uct(node)
//	    return pick_univisted(node.children) or node # in case no children are present / node is terminal 
//
//	def rollout(node):
//	    while non_terminal(node):
//	        node = rollout_policy(node)
//	    return result(node) 
//
//	def rollout_policy(node):
//	    return pick_random(node.children)
//
//	def backpropagate(node, result):
//	   if is_root(node) return 
//	   node.stats = update_stats(node, result) 
//	   backpropagate(node.parent)
//
//	def best_child(node):
//	    pick child with highest number of visits


