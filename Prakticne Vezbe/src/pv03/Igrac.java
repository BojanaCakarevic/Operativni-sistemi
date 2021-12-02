package pv03;

import java.util.ArrayList;
import java.util.List;

import pv01.Karta;
import pv01.Spil;

/* Napraviti i pokrenuti 12 niti koje predstavljaju igrace. Svaka nit uzima
* jednu kartu sa vrha spila i smesta je u svoje privatno polje. Potom tu kartu
* stavlja na talon (videti ispod) i ceka da to urade i svi ostali igraci.
*/

public class Igrac extends Thread {

	private String ime;
	private Karta karta;
	private Spil spil;
	private Talon talon;

	public Igrac(String ime, Spil spil, Talon talon) {
		super();
		this.ime = ime;
		this.spil = spil;
		this.talon = talon;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

	public Spil getSpil() {
		return spil;
	}

	public void setSpil(Spil spil) {
		this.spil = spil;
	}

	public Talon getTalon() {
		return talon;
	}

	public void setTalon(Talon talon) {
		this.talon = talon;
	}

	@Override
	public void run() {
		
		karta = spil.uzmiOdGore();
		System.out.println("Izvukao sam " + karta);
		
		talon.staviKartu(karta);
	//	System.out.println("Na talon sam stavio " + karta);
		
		try {
			talon.cekajOstale();
			if (talon.jeNajjaca(karta))
				poruka("je pobedio ");	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	public void poruka(String poruka) {
		System.out.println(getIme() + " " + poruka + " sa kartom " + karta.getRang());
	}

}
