package vilenjaci;

/* Radni dan prva dva vilenjaka se sastoji samo od proizvodnje igracaka, pri
* cemu svako od njih moze u toku dana da napravi po 50 igracaka, posle cega
* zavrsavaju svoj radni dan. (5 poena)
*/

public class VilenjaciProizvodjaci extends Thread {

	Radionica radionica;
	Igracka igracka;

	public VilenjaciProizvodjaci(Radionica radionica, Igracka igracka) {
		super();
		this.radionica = radionica;
		this.igracka = igracka;
		setDaemon(true);
	}

	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				Igracka igracka = new Igracka();
				radionica.proizvedi(igracka);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
