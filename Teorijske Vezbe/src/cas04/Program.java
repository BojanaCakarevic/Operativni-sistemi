package cas04;

class Brojac {

// Ako polje deklarisemo kao volatile, kompajler i virtuelna masina
// uzimaju u obzir da druga nit moze konkurentno azurirati to polje.
	
// Konkurentan pristup je bezbedan ako:
// a) polje je final i pristupa mu se nakon sto se poziv
//	konstruktora zavrsio
// b) svaki pristup polju je zasticen zajednickim katancem
// c) polje je volatile
	
// https://oblac.rs/volatile-nije-programiranje/	
	private volatile int br = 0;

	public void inc() {
		br++;
	}

	@Override
	public String toString() {
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
		for (int i = 0; i < 1000; i++) {
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
