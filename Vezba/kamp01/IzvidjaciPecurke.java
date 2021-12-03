package kamp01;

/* 12 izvidjaca definisano pomocu klase Thread po pokretanju idu u sumu da traze
* pecurke, tj. pozivaju metod Suma::traziPecurke 20 puta, i posle svakog poziva
* donose pecurke koje su pronasli nazad u kamp (Kamp::donesiPecurke). Ako ih
* neko prekine u potrazi za pecurkama, hitno se vracaju u kamp bez pecuraka i
* regularno zavrsavaju svoj rad. (5 poena)
*/

public class IzvidjaciPecurke extends Thread {

	Kamp kamp;
	Suma suma;
	
	public IzvidjaciPecurke(String ime,Kamp kamp, Suma suma) {
		super();
		setName(ime);
		this.kamp = kamp;
		this.suma = suma;
		setDaemon(true);
	}

	public void run() {
		for (int i = 0; i < 3 && !Thread.interrupted(); i++) {
			int br = suma.traziPecurke();
			kamp.donesiPecurke(br);
			System.out.println("Doneo sam " + br + " pecuraka");
		}

	}

}
