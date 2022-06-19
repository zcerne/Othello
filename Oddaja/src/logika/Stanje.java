package logika;

public enum Stanje {
NEODLOCENO, ZMAGA_CRN, ZMAGA_BEL, V_TEKU;
	
	public Igralec kdoJeZmagal() {
		switch(this) {
		case ZMAGA_BEL:
			return Igralec.BEL;
		case ZMAGA_CRN:
			return Igralec.CRN;
		default:
			break;
		case NEODLOCENO:
			return null;
		case V_TEKU:
			return null;
		}
		return null;
	}
}
