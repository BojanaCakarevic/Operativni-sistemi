package pv01;

public class Karta {
	
	Boja boja;
	Rang rang;
	
	public Karta() {
		super();
	}
	
	public Karta(Boja boja, Rang rang) {
		this.boja = boja;
		this.rang = rang;
	}

	public Boja getBoja() {
		return boja;
	}

	public void setBoja(Boja boja) {
		this.boja = boja;
	}

	public Rang getRang() {
		return rang;
	}

	public void setRang(Rang rang) {
		this.rang = rang;
	}
	
	public String stampaj() {
		return "[" + rang + ", " + boja + "]";
	}
	
	
	@Override
	public String toString() {
		return "Karta [boja=" + boja + ", rang=" + rang + "]";
	}

	public static void main(String[] args) {
		Karta k = new Karta();
	}

}
