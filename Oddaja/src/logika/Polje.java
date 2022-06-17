package logika;

public enum Polje {
	CRN, BEL, PRAZNO;
	
	public Polje obrat() {
		if(this == CRN) return BEL;
		else if(this == BEL) return CRN;
		return PRAZNO;
	}
	
	public String toString() {
		if(this == CRN)return "C";
		else if(this == BEL) return "B";
		else return "O"; 
	}
}

