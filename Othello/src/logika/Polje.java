package logika;

public enum Polje {
	CRN, BEL, PRAZNO;
	
	public Polje obrat() {
		if(this == CRN) return BEL;
		else if(this == BEL) return CRN;
		return PRAZNO;
	}
}

