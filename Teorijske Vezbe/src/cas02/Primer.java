package cas02;

/* Napisati program koji implementira dve niti: jednu definisanu pomocu klase Thread
 * koja ispisuje prvih 10000 pozitivnih celih brojeva, i drugu definisanu pomocu interfejsa
 * Runnable koja ispisuje prvih 10000 negativnih celih brojeva
 * Nitima postaviti i imena 
*/

class PrvaNit extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}
}

class DrugaNit implements Runnable {
	@Override
	public void run() {
		for (int i = -1; i > -10; i--) {
			System.out.println(i);
		}
	}
}

public class Primer {

	public static void main(String[] args) {

		PrvaNit nit1 = new PrvaNit();
		nit1.setName("Pozitivni brojevi");
		nit1.start();

		DrugaNit nit2 = new DrugaNit();
		Thread t = new Thread(nit2);
		t.setName("Negativni brojevi");
		t.start();

	}

}
