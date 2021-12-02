package zidari;

public class StarijiRadnici extends Thread {

	Gajba gajba;

	public StarijiRadnici(Gajba gajba) {
		super();
		this.gajba = gajba;
		setDaemon(false);
	}

	public void run() {

		for (int i = 0; i < 50; i++) {
		try {
				Pivo p = gajba.uzmi();
				p.ispij();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}}

}
