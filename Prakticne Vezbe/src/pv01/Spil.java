package pv01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spil {

	List<Karta> karta;
	Random r = new Random();

	public Spil() {
		super();
		this.karta = new ArrayList<Karta>();

		for (Rang r : Rang.values()) {
			if (r != Rang.DZOKER) {
				for (Boja b : Boja.values()) {
					if (b != Boja.DZOKER_CRNOBELI && b != Boja.DZOKER_OBOJENI) {
						Karta k = new Karta(b, r);
						this.karta.add(k);
					}
				}
			}
		}

		Karta k1 = new Karta(Boja.DZOKER_CRNOBELI, Rang.DZOKER);
		this.karta.add(k1);
		Karta k2 = new Karta(Boja.DZOKER_OBOJENI, Rang.DZOKER);
		this.karta.add(k2);
	}

	public List<Karta> getKarte() {
		return karta;
	}

	public int velicina() {
		return karta.size();
	}

//	public Karta uzmiOdGore() {
//		Karta k = karta.get(karta.size()-1);
//		karta.remove(karta.size()-1);
//		return k;
//	}
	
	public Karta uzmiOdGore() {
		Karta k = this.karta.get(this.velicina() - 1);
		this.karta.remove(this.velicina() - 1);
		return k;
	}

	public Karta uzmiOdDole() {
		Karta k = karta.get(0);
		karta.remove(0);
		return k;
	}

	public Karta uzmiIzSredine() {
		int index = r.nextInt(this.velicina());

		Karta k = karta.get(index);
		karta.remove(index);
		return k;
	}

	public void obrisi(Karta k) {
		karta.remove(k);
	}
	
	public void staviGore(Karta k) {
		karta.add(k);
	}

	public void staviDole(Karta k) {
		karta.add(k);
	}

	public void staviUSredinu(Karta k) {
		int index = r.nextInt(this.velicina());
		karta.add(index, k);
	}

	public void promesaj() {

		for (int i = 0; i < 54; i++) {
			int index = r.nextInt(54);
			karta.set(i, karta.get(index));
			karta.set(index, karta.get(i));
		}
	}

	public void stampaj() {
		System.out.println("Sadrzaj spila: " + karta);
	}

	public static void main(String[] args) {

		Spil s = new Spil();
		s.stampaj();
	}

}
