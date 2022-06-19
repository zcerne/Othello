<<<<<<< HEAD
package logika;

public enum Igralec {
	CRN, BEL;

	public Polje dobiPolje() {
		return (this == BEL ? Polje.BEL : Polje.CRN);
		
	}

	public Igralec obrat() {
		return (this == CRN ? BEL : CRN);
	}
	
	@Override
	public String toString() {
		switch(this){
		case CRN: return "Na potezi je ČRNI";
		case BEL: return "Na potezi je BELI";
		default: assert false; return "";
		}
		
	}
}
=======
package logika;

public enum Igralec {
	CRN, BEL;

	public Polje dobiPolje() {
		return (this == BEL ? Polje.BEL : Polje.CRN);
		
	}

	public Igralec obrat() {
		return (this == CRN ? BEL : CRN);
	}
	
	@Override
	public String toString() {
		switch(this){
		case CRN: return "Na potezi je ČRNI";
		case BEL: return "Na potezi je BELI";
		default: assert false; return "";
		}
		
	}
}
>>>>>>> refs/remotes/origin/main
