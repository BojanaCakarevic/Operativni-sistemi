package pv03;

import java.util.ArrayList;
import java.util.List;

import pv01.Karta;
import pv01.Spil;

public class Talon {

	Karta karte;
	Spil spil;
	Igrac igrac;
	private int br;
	private Karta najjaca;
	
	public Talon() {
		super();
	}

	public Karta getKarte() {
		return karte;
	}

	public void setKarte(Karta karte) {
		this.karte = karte;
	}

	public Spil getSpil() {
		return spil;
	}

	public void setSpil(Spil spil) {
		this.spil = spil;
	}

	public Igrac getIgrac() {
		return igrac;
	}

	public void setIgrac(Igrac igrac) {
		this.igrac = igrac;
	}
	
	public synchronized void staviKartu(Karta k) {
		if (najjaca == null || k.getRang().getVrednost() > najjaca.getRang().getVrednost())
			najjaca = k; 
		br++;
		if (br == 12) {
			notifyAll();
		}
	}
	
	public synchronized void cekajOstale() throws InterruptedException {
		while (br != 12) {
			wait();
		}
	}

	
	public synchronized boolean jeNajjaca(Karta karta) {
		return karta.getRang().getVrednost() == najjaca.getRang().getVrednost();
	}
	
}
