package zidari;

public class Naslednik implements Runnable {

	Gajba gajba;
	Pivo pivo;

	public Naslednik(Gajba gajba) {
		super();
		this.gajba = gajba;
	}

	@Override
	public void run() {

		try {
			while (!Thread.interrupted()) {
				Pivo[] piva = Pivo.kupi();
				for (int i = 0; i < piva.length; i++) {
					gajba.stavi(piva[i]);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
