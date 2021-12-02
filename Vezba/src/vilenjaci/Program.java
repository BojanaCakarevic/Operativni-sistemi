package vilenjaci;

import java.util.ArrayList;
import java.util.List;

/*
 * Napisati program koji kreira jednu Deda Mrazovu radionicu poklona i cetiri
 * vilenjaka. Vilenjaci koji rade u radionici se zovu Maglor, Mrazdalf,
 * Vetromir i Snegolas i definisani su kao zasebni procesi. Prva dva vilenjaka
 * su definisana pomocu klase Thread kao korisnicki procesi, a druga dva pomocu
 * interfejsa Runnable kao pozadinski procesi. (5 poena)
 * 
 * Radni dan prva dva vilenjaka se sastoji samo od proizvodnje igracaka, pri
 * cemu svako od njih moze u toku dana da napravi po 50 igracaka, posle cega
 * zavrsavaju svoj radni dan. (5 poena)
 * 
 * Druga dva vilenjaka po ceo dan pakuju poklone u koje stavljaju po jednu
 * igracku (pozivajuci metod Igracka::upakuj). (5 poena)
 * 
 * Sinhronizovati klasu Radionica tako da se ni u kom slucaju ne izgube
 * igracke. Takodje, blokirati vilenjaka koji pokusa da upakuje poklon bez
 * igracke ili napravi novu igracku ako je radionica puna. Na stolu u radionici
 * moze da stane najvise 10 igracaka. Odblokirati vilenjake cim se stvore
 * uslovi za nastavak njihovog rada. (10 poena)
 * 
 * Obratiti paznju na elegantnost i objektnu orijentisanost realizacije i stil
 * resenja. Za program koji se ne kompajlira, automatski se dobija 0 poena bez
 * daljeg pregledanja.
 */

public class Program {

	public static void main(String[] args) {
		
		Radionica radionica = new Radionica();
		Igracka igracka = new Igracka();
		
		VilenjaciProizvodjaci vp = new VilenjaciProizvodjaci(radionica, igracka);
		vp.setName("Mirko");
		vp.start();
		
		VilenjaciProizvodjaci vp2 = new VilenjaciProizvodjaci(radionica, igracka);
		vp2.setName("Slavko");
		vp2.start();
		
		VilenjaciPakeri vpp = new VilenjaciPakeri(radionica, igracka);
		Thread t = new Thread(vpp);
		t.setDaemon(false);
		t.setName("Dara");
		t.start();
		
		VilenjaciPakeri vpp2 = new VilenjaciPakeri(radionica, igracka);
		Thread t2 = new Thread(vpp2);
		t2.setDaemon(false);
		t2.setName("Rada");
		t2.start();
	}
	
}

class Radionica {

	List<Igracka> igracke = new ArrayList<>();

	// Na stolu u radionici moze da stane najvise 10 igracaka

	public synchronized void proizvedi(Igracka igracka) throws InterruptedException {

		while (igracke.size() + 1 > 10)
			wait();
		igracke.add(igracka);
		notifyAll();
		
	}

	public synchronized Igracka uzmiSaStola() throws InterruptedException {

		while (igracke.size() - 1 < 0)
			wait();
		Igracka r = igracke.remove(0);
		notifyAll();
		return r;

	}
}

class Igracka {

	public Igracka() {
		String boja = BOJE[(int) (BOJE.length * Math.random())];
		String zivotinja = ZIVOTINJE[(int) (ZIVOTINJE.length * Math.random())];
		this.opis = boja + " " + zivotinja;
		System.out.println(Thread.currentThread().getName() + " pravi " + opis + ".");
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void upakuj() {
		System.out.println(Thread.currentThread().getName() + " pakuje " + opis + ".");
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private static final String[] BOJE = { "Plavog", "Crvenog", "Zelenog", "Belog", "Zlatnog", };

	private static final String[] ZIVOTINJE = { "medu", "zeku", "papagaja", "irvasa", "lava", };

	private final String opis;
}