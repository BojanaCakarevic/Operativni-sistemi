package zidari;

import java.util.ArrayList;
import java.util.List;

/*
 * Napisati program koji kreira jednu praznu gajbu piva i tri gradjevinska
 * radnika. Dva gradjevinska radnika se zovu Joza i Muhi i predstavljeni su
 * zasebnim procesima definisanim pomocu klase Thread. Treci radnik se zove
 * Gule, najmladji je od njih trojice i definisan je kao pozadinski proces
 * pomocu interfejsa Runnable. (5 poena)
 * 
 * Radni dan starije dvojce radnika se sastoji samo od ispijanja piva u
 * hladovini, pri cemu svako od njih moze u toku dana da popije po 50 piva,
 * posle cega zavrsava svoj "radni" dan. (5 poena)
 * 
 * Posto je najmladji, Gule po ceo dan odlazi do obliznjeg diskonta i kupuje
 * pivo drugoj dvojci radnika. Oni mu za ovo, naravno, daju nesto novca, a kako
 * diskont stalno nudi razne akcije, Gule svaki put donese razlicit broj flasa
 * piva. Ovo je vec dato i potrebno je samo pozvati metod Pivo::kupi. (5 poena)
 * 
 * Sinhronizovati klasu Gajba tako da se ni u kom slučaju ne izgubi ni jedno
 * pivo. Takodje, blokirati radnika koji pokusa da uzme pivo iz prazne gajbe
 * ili stavi pivo u punu gajbu. U gajbu može stati najvise 12 piva. Odblokirati
 * radnike cim se stvore uslovi za nastavak njihovog "rada". (10 poena)
 * 
 * Obratiti paznju na elegantnost i objektnu orijentisanost realizacije i stil
 * resenja. Za program koji se ne kompajlira, automatski se dobija 0 poena bez
 * daljeg pregledanja.
 */

public class Program {

	public static void main(String[] args) {

		Gajba gajba = new Gajba();
		Pivo pivo = new Pivo();

		StarijiRadnici sr = new StarijiRadnici(gajba);
		sr.setName("Jole");
		sr.start();

		StarijiRadnici sr2 = new StarijiRadnici(gajba);
		sr2.setName("Bole");
		sr2.start();

		Naslednik n = new Naslednik(gajba);
		Thread t = new Thread(n);
		t.setName("Kole");
		t.start();

	}
}

class Gajba {

	List<Pivo> piva = new ArrayList<>();

	public synchronized void stavi(Pivo pivo) throws InterruptedException {

		while (piva.size() + 1 > 12)
			wait();
		piva.add(pivo);
		notifyAll();
	}

	public synchronized Pivo uzmi() throws InterruptedException {

		while (piva.size() - 1 < 0)
			wait();
		Pivo p = piva.remove(0);
		notifyAll();
		return p;
	}
}

class Pivo {

	public void ispij() {
		System.out.println(Thread.currentThread().getName() + " pije " + opis + " pivo.");
		try {
			Thread.sleep((long) (500 + 500 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static Pivo[] kupi() {
		int n = (int) (1 + 5 * Math.random());
		Pivo[] piva = new Pivo[n];
		for (int i = 0; i < n; i++) {
			piva[i] = new Pivo();
		}
		System.out.println(Thread.currentThread().getName() + " kupuje " + n + " piva.");
		try {
			Thread.sleep((long) (1000 + 1000 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return piva;
	}

	private final String opis;

	Pivo() {
		opis = VRSTE[(int) (VRSTE.length * Math.random())];
	}

	private static final String[] VRSTE = { "Lav", "Lav Twist", "Lav Swing", "Lav Tamni", "Dundjerski", "Jelen",
			"Jelen Cool", "Jelen Warm", "Jelen Fresh", "Vajfert", };

}
