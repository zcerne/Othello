package vodja;

public enum VrstaIgralca {
	C, R;
	
	@Override
	public String toString() {
		switch(this){
		case C: return "Na potezi je človek";
		case R: return "Na potezi je računalnik";
		default: assert false; return "";
		}
		
	}
}
