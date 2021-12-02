package pv01;

public class Igrac {

	Karta karta;
	public String ime;
	
	public Igrac() {
		super();
		this.karta = new Karta();
	}

	public Igrac(String ime) {
		this.ime = ime;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public void stampaj() {
		System.out.println("Igrac " + ime + " ima kartu " + karta.stampaj());
	}

}
