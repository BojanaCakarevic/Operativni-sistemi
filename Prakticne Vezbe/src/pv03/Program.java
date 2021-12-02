package pv03;

import java.util.ArrayList;
import java.util.List;

import pv01.Spil;

/* Uzeti implementaciju klasa 'Karta' i 'Spil' iz prvog zadatka i adaptirati ih
 * tako da se mogu koristiti od strane vise procesa istovremeno.
 * 
 * Napraviti i pokrenuti 12 niti koje predstavljaju igrace. Svaka nit uzima
 * jednu kartu sa vrha spila i smesta je u svoje privatno polje. Potom tu kartu
 * stavlja na talon (videti ispod) i ceka da to urade i svi ostali igraci.
 * 
 * Kada su svi igraci stavili svoje karte na talon, nastavljaju izvrsavanje.
 * Svako samostalno proverava da li je imao najjacu kartu i stampa prigodnu
 * poruku o tome. Moze biti vise igraca sa najjacom kartom, gleda se samo rang
 * karte kao i u prethodnim zadacima.
 * 
 * Implementirati klasu 'Talon' koja ima sledece metode i koristiti je za
 * sinhronizaciju igraca:
 * 
 *   void staviKartu(Karta)   - pomocu koje igrac stavlja kartu na talon
 *   void cekajOstale()       - blokira nit dok se na talon ne stavi 12 karata
 *                              ovaj metod baca InterruptedException ako neko
 *                              prekine nit u toku ovog cekanja
 *   boolean jeNajjaca(Karta) - utvrdjuje da li je prosledejna karta najjaca
 * 
 * Glavna nit kreira spil i talon, pokrece sve ostale niti, posle cega zavrsava
 * svoj rad.
 */

public class Program {

	public static void main(String[] args) {

		Spil spil = new Spil();
		Talon talon = new Talon();
		List<Igrac> igraci = new ArrayList<Igrac>();
		
		for (int i = 0; i < 12; i++) {
			igraci.add(new Igrac("Igrac ", spil, talon));
			igraci.get(i).start();
		}
		
	}
}
