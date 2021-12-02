package cas05;

// "Ako pisete u promenljivu iz koje ce zatim moci da cita druga nit, ili citate iz
// promenljive u koju je poslednja mogla da pise druga nit, morate koristiti sinhronizaciju."


// "Kritična oblast (critical section) – je deo programskog koda za koji važi: 
// ako je neki proces unutar svoje kritič ne oblasti, ni jedan drugi proces ne sme 
// biti unutar svoje kritič ne oblasti. Drugim reč ima, proces ne sme biti prekinut 
// dok se nalazi u svojoj kritič noj oblasti. "

class Brojac {

	private volatile int br = 0;

	public synchronized void inc() {
		br++;
	}

	@Override
	public synchronized String toString() {
		return "Vrednost: " + br;
	}
}

class Nit extends Thread {

	private Brojac brojac;

	public Nit(Brojac brojac) {
		this.brojac = brojac;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100_000; i++) {
			brojac.inc();
		}
	}
}

public class Program {

	public static void main(String[] arguments)
			throws InterruptedException {

		Brojac brojac = new Brojac();

		Nit n1 = new Nit(brojac);
		Nit n2 = new Nit(brojac);
		Nit n3 = new Nit(brojac);

		n1.start();
		n2.start();
		n3.start();

		n1.join();
		n2.join();
		n3.join();

		System.out.println(brojac);

	}
}

