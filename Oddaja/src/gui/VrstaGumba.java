package gui;

public enum VrstaGumba {
	II, IR, RI, RR, RAND_C, MCTS_C, MINIMAX_C, RAND_B, MCTS_B, MINIMAX_B, UNDO, MENU;

	@Override
	public String toString(){
		switch(this) {
		case II: return "Igralec - Igralec";
		case IR: return "Igralec - Računalnik";
		case RI: return "Računalnik - Igralec";
		case RR: return "Računalnik - Računalnik";
		case UNDO : return "Undo";
		case MENU: return null;
		case MCTS_B : return "MCTS";
		case MCTS_C: return "MCTS";
		case MINIMAX_B: return "MINIMAX";
		case MINIMAX_C: return "MINIMAX";
		case RAND_B: return "Naključnež";
		case RAND_C:return "Naključnež";

		default:
			break;




		}

		return null;
		
	}
}
