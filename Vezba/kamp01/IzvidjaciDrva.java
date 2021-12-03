package kamp01;

/* 12 izvidjaca definisano pomocu interfejsa Runnable po pokretanju idu u sumu
* da traze drva za korpe, tj. pozivaju metod Suma::traziDrva 25 puta, i posle
* svakog poziva donose sakupljena drva nazad u kamp (Kamp::donesiDrva). Ako ih
* neko prekine u potrazi, izvidjaci se ne obaziru na to i regularno zavrsavaju
* svoj rad kada obave svih 25 potraga. (5 poena)
*/

public class IzvidjaciDrva implements Runnable {

	Suma suma;
	Kamp kamp;

	public IzvidjaciDrva(Suma suma, Kamp kamp) {
		super();
		this.suma = suma;
		this.kamp = kamp;
	}


	@Override
	public void run() {
		
		for (int i = 0; i < 3; i++) {
			int br = suma.traziDrva();
			try {
				kamp.donesiDrva(br);
				System.out.println("Doneo sam " + br + " drva");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
