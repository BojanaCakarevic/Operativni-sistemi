package cas03;

class Nit extends Thread {

	// ovo polje postoji u klasi thread
	// ne moramo da ga pisemo

	// interrupt postavi boolean na true
	// nit nastavlja normalno da se izvrsava

	// ako koristimo interrupted mi smo obradili prekid, reagovali na
	// sve i kod ispod ne mora biti svesan tog prekida

	private static boolean interrupted;

	public boolean isInterrupted() {
		return interrupted;
	}

	public static boolean interrupted() {
		return interrupted;
		// interrupted = false;
	}

// Umesto ovog koristimo setInterrupted koji se moze 
// podesiti samo na true

//	public void setInterrupted(boolean interrupted) {
//		this.interrupted = interrupted;
//	}

	public void setInterrupted(boolean interrupted) {
		this.interrupted = true;
	}

	// Svi koji imaju referencu na nit moze postaviti
	// zastavicu true ili proveriti da li je zastavica takva, ne i promeniti je

	// moze se desiti da se ispise samo pola reda
	// mi hocemo sve ili nista
	// postavljamo uslov provere zastavice
	// ako niko nije trazio da prekinemo ono sto radimo trenutno
	@Override
	public void run() {
		for (int i = 0; i < 900 && !isInterrupted(); i++) {
			System.out.println(getName() + ": " + i);
		}
	}
}

// postoji staticki metod interrupted(), radi sve sto i isInterrupted
// samo ovaj metod moze da spusti zastavicu
// ne moze ga koristiti bilo ko, samo sama nit

public class Zaustavljanje {
	public static void main(String[] args) {
		Nit nit = new Nit();
		nit.start();

		// izvrsavanje mejna se zaustavi na neko vreme
		// nit predje u stanje blokiranosti, ali nakon nekog vremena
		// nastavlja normalno da se izvrsava;
		// Thread.sleep(1000);
		System.out.println("Kraj");

		// Ovo ne koristimo gotovo nikad.
		// nit.stop();
		// nit.suspend();
		// nit.interrupt(); - postavlja zastavicu na true
		// (to je onaj setInterrupt)

		// nit.join();
		// poslednja stvar koja se izvrsi u run-u u
		// niti nit se desila pre joina (ako nije bacio izuzetak)

	}
}
