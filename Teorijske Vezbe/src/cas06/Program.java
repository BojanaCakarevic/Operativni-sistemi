package cas06;

import java.util.ArrayList;
import java.util.List;

// Dve male kriticne oblasti ne mogu zameniti jednu veliku
// jer izmedju njih moze da se desi prekid

// Metod wait() bezuslovno uspavljuje nit koja ga je pozvala

// Drzi je uspavanom sve dok druga nit ne pozove notify()
// ili notifyAll(), time nas obavestava da mozemo nastaviti
// sa  radom, nema potrebe za cekanjem

// Neophodno je obraditi izuzetak koji baca wait()
// Notify() se dodaje tamo gde se menja ono zbog 
// cega smo bili na cekanju (npr neki unos)

// Ako samo jedna nit nastavlja akciju - notify()
// ako to radi vise niti - notifyAll()

// wait() obavezno u petlju

class Bafer<T> {

	protected final List<T> lista;

	public Bafer() {
		this.lista = new ArrayList<>();
	}

	public boolean jePrazan() {
		return lista.size() == 0;
	}

	public void dodaj(T element) {
		lista.add(element);
		notify();
	}

	public T ukloni() throws InterruptedException {
		while(jePrazan()){
			wait();
		}
		return lista.remove(0);
	}
}

class Proizvodjas extends Thread {
	
	private Bafer<String> bafer;

	public Proizvodjas(Bafer<String> bafer) {
		super();
		this.bafer = bafer;
	}
}