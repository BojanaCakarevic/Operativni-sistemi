package vilenjaci;

/* Druga dva vilenjaka po ceo dan pakuju poklone u koje stavljaju po jednu
* igracku (pozivajuci metod Igracka::upakuj). (5 poena)
*/ 

public class VilenjaciPakeri implements Runnable {

	Radionica radionica;
	Igracka igracka;
	
	public VilenjaciPakeri(Radionica radionica, Igracka igracka) {
		super();
		this.radionica = radionica;
		this.igracka = igracka;
	}

	@Override
	public void run() {
		
		Igracka igracka;
		try {
			igracka = radionica.uzmiSaStola();
			igracka.upakuj();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
