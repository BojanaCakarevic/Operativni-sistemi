package cas02;

// Kada pokrenemo neki jednostavan program na operativnom sistemu se pojavljuje novi (teski proces)
// Mozemo ga videti u task menadzeru gde menjamo prioritet, brisemo ga i sl (manipulacije)
// Taj proces sadrzi vise malih, lakih procesa tj niti
// Razlikuju se po tome sto laki procesi dele sve resurse (osim steka!), samim tim lakse i brze komuniciraju
// Definisanje niti:
// 1. definisati tip niti, odrediti sta rade itd
// 2. instanca te klase u mainu
// 3. poziv start() metoda


class Nit2 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i < 100; i += 2) {
			System.out.println(i);
		}
	}
}

class Nit extends Thread {

	// Za klase koje nasledjuju klasu Thread mozemo podesiti
	// vrstu niti (korisnicka/pozadinska) kroz konstruktor
	public Nit() {
		setDaemon(true);
	}
	
	// u ovaj metod kucamo kod koji zelimo da izvrse niti ove vrste
	@Override
	// ne mozemo baciti izuzetak iz run()-a
	// mogu runtime exceptions
	// sve obrade izuzetaka treba postaviti ovde
	public void run() {
		for (int i = 100; i < 200; i += 2) {
			System.out.println(i);
		}
	}
}

public class KreiranjeNiti {

	// u ovakvom programu imamo dve niti.
	// glavna nit koja izvrsava program i garbage collector koja
	// gleda da li je potrebno osloboditi neke resurse
	public static void main(String[] args) {
		for (int i = 0; i < 100; i += 2) {
			System.out.println(i);
		}

		// objekat koji je instanca klase Nit
		// nismo dobili novu nit izvrsavanja,
		// jos uvek se izvrsava samo main i garbidz collector
		Nit nit1 = new Nit();
		
		// mozemo ostampati ime niti
		// System.out.println(nit1.getName());
		
		// mozemo joj i dodeliti ime
		nit1.setName("neparni");
		
		// Nit je demonska ako nema drugu ulogu sem 
		// sluzenja drugima
		// Kada sve druge niti zavrse svoj rad, ona se gasi
		// Izvrsavanje programa nema smisla kada ostanu samo
		// demonske niti.
		// ako je true nit je pozadinska
		// false -> nit je korisnicka
		nit1.setDaemon(true);
		
		// kad pozovemo start() pocinje izvrsavanje ove nove niti
		nit1.start();

		// klasa Nit2 nije nit jer ne nasledjuje Thred
		// objekti iz nje nisu objekti koji nasledjuju nitii
		// definisali smo kod koji niti mogu izvrsavati
		// nema start() metoda
		Nit2 nit2 = new Nit2();
		// t jeste nit, ali nema kod koji izvrsava
		// konstruktor klase Thread() moze da primi argument tipa Runnable
		Thread t = new Thread(nit2);
		t.start();

		// Mozemo postaviti prioritet medju nitima
		// nit1.setPriority(5);
		// MIN_PRIORITY je definisano sa 1 u klasi Thread
		// MAX_PRIORITY - 5
		// **** Linux ignorise prioritet ****
	}
	
	// nit je iste vrste kao i nit koja ju je napravila
	// ako garbidz kolektor pravi nit ona je pozadinska
	// ako je pravi glavna nit onda je korisnicka

}
