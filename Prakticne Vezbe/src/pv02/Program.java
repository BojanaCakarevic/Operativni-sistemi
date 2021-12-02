package pv02;

import java.util.ArrayList;
import java.util.List;

/*
 * Data je implementacija beskonacnog spila karata (klase 'Spil' i 'Karta').
 * 
 * Napraviti nit koja uzima jednu po jednu kartu iz spila i deli ih drugim
 * nitima.
 * 
 * Napraviti 12 niti koje predstavljaju igrace. One cekaju da dobiju kartu od
 * dilera, koju potom ispisuju na ekranu i zavrsavaju svoj rad.
 * 
 * Glavna nit kreira i pokrece sve ostale niti posle cega zavrsava svoj rad.
 */

import java.util.Random;

class Diler extends Thread {

	List<Igrac> igraci = new ArrayList();
	Spil s = new Spil();

	public Diler(List<Igrac> igraci) {
		super();
		this.igraci = igraci;
	}

	@Override
	public void run() {
		System.out.println("Diler pocinje podelu: ");
		for (Igrac i : igraci) {
			if (i.getKarta() == null)
				i.setKarta(s.uzmi());
		}
	}

}

class Igrac extends Thread {

	String ime;
	volatile Karta karta = null;

	public Igrac(String ime) {
		super();
		this.ime = ime;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

	public void run() {
		while (this.karta == null) {
			// Igrac ceka kartu...
		}
		System.out.println("Igrac " + ime + " je dobio " + karta);
	}
}

class Karta {

	private String vrednost;

	public Karta(String vrednost) {
		this.vrednost = vrednost;
	}

	@Override
	public String toString() {
		return vrednost;
	}
}

class Spil {

	private static final String[] BOJE = "\u2660,\u2665,\u2666,\u2663".split(",");
	private static final String[] RANGOVI = "2,3,4,5,6,7,8,9,10,J,Q,K,A".split(",");
	private static final String[] DZOKERI = "\u2605,\u2606".split(",");
	private static final Random random = new Random();

	public Karta uzmi() {
		int id = random.nextInt(54);
		if (id == 53) {
			return new Karta(DZOKERI[0]);
		}
		if (id == 52) {
			return new Karta(DZOKERI[1]);
		}
		String boja = BOJE[id / 13];
		String rang = RANGOVI[id % 13];
		return new Karta(rang + boja);
	}
}

public class Program {

	public static void main(String[] args) {
		List<Igrac> igraci = new ArrayList<>();

		for (int i = 0; i < 12; i++) {
		    Igrac igrac = new Igrac("Igrac " + i);
			igraci.add(igrac);
		}
		
		for (Igrac i: igraci) {
			i.start();
		}
		
		Diler d = new Diler(igraci);
		d.start();
	}
}
