package pv01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Program {

	public static void main(String[] agrs) {

		Spil s = new Spil();
		s.promesaj();

		List<Igrac> igraci = new ArrayList<Igrac>();
		for (int i = 0; i < 12; i++) {
			igraci.add(new Igrac("Igrac " + i));
		}

		System.out.println("POCETAK:");
		while (igraci.size() > 1) {
			List<Karta> karte = new ArrayList<>();

			for (Igrac igrac : igraci) {
				Karta karta = s.uzmiOdGore();
				karte.add(karta);
				System.out.println(igrac.getIme() + ": " + karta);
			}

			int max = 0;
			for (Karta karta : karte) {
				if (karta.getRang().getVrednost() > max) {
					max = karta.getRang().getVrednost();
				}
			}

			System.out.println("------------------------");

			for (int i = 0; i < igraci.size(); i++) {
				if (karte.get(i).getRang().getVrednost() != max) {
					igraci.remove(i);
				}
			}

		}
		System.out.println("Pobedio je igrac " + igraci.get(0).ime);

	}

}
