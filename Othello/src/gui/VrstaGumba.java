package gui;

public enum VrstaGumba {
	II, IR, RI, RR, UNDO, MENU;

	@Override
	public String toString(){
		switch(this) {
		case II: return "Igralec - Igralec";
		case IR: return "Igralec - Računalnik";
		case RI: return "Računalnik - Igralec";
		case RR: return "Računalnik - Računalnik";
		case UNDO : return "Undo";
		}

		return null;
		
	}
}
