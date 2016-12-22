import java.lang.reflect.Array;
import java.util.ArrayList;

public class Information {
	private static Information instance;
	private int sirka;
	private int dlzka;
	private boolean[][] mapa;
	private int pocet_kamenov;
	private int pocetvolnych;
	private int obvod ;
	private int delic ;
	private int pocetgenov;
	private int pocet_generacii=10000000;
	private ArrayList<Integer> kamenenaokraji;
	public Information(){
		
	}
	
	public static Information getinstance(){
		if(instance == null)
			instance = new Information();
			instance.setKamenenaokraji(new ArrayList<Integer>());
		return instance;
	}

	public int getSirka() {
		return sirka;
	}

	public void setSirka(int sirka) {
		this.sirka = sirka;
	}

	public int getDlzka() {
		return dlzka;
	}

	public void setDlzka(int dlzka) {
		this.dlzka = dlzka;
	}

	public boolean[][] getMapa() {
		return mapa;
	}

	public void setMapa(boolean[][] mapa) {
		this.mapa = mapa;
	}

	public ArrayList<Integer> getKamenenaokraji() {
		return kamenenaokraji;
	}

	public void setKamenenaokraji(ArrayList<Integer> kamenenaokraji) {
		this.kamenenaokraji = kamenenaokraji;
	}

	public int getPocet_kamenov() {
		return pocet_kamenov;
	}

	public void setPocet_kamenov(int pocet_kamenov) {
		this.pocet_kamenov = pocet_kamenov;
	}

	public int getPocetvolnych() {
		return pocetvolnych;
	}

	public void setPocetvolnych(int pocetvolnych) {
		this.pocetvolnych = pocetvolnych;
	}

	public int getObvod() {
		return obvod;
	}

	public void setObvod(int obvod) {
		this.obvod = obvod;
	}

	public int getDelic() {
		return delic;
	}

	public void setDelic(int delic) {
		this.delic = delic;
	}

	public int getPocetgenov() {
		return pocetgenov;
	}

	public void setPocetgenov(int pocetgenov) {
		this.pocetgenov = pocetgenov;
	}

	public int getPocet_generacii() {
		return pocet_generacii;
	}

	public void setPocet_generacii(int pocet_generacii) {
		this.pocet_generacii = pocet_generacii;
	}
}
