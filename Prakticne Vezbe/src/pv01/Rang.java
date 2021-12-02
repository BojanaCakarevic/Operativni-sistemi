package pv01;

public enum Rang {

	KEC(1), DVA(2), TRI(3), CETIRI(4), PET(5), SEST(6), SEDAM(7),
	OSAM(8), DEVET(9), DESET(10), ZANDAR(12), DAMA(13), KRALJ(14), DZOKER(15);
	
	private int vrednost;
	
	Rang(int vrednost) {
		this.vrednost = vrednost;
	}

	public int getVrednost() {
		return vrednost;
	}

}
