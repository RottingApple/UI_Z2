import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Jedinec {
	private Gen[] genom;
	private HashMap<Integer,Integer>cesta;
	private int fitness;
	public Jedinec(int number){
		genom = new Gen[number];
		for (int i = 0; i < number; i++) {
			genom[i] = new Gen();
			genom[i].getSance()[0] = 50;
			genom[i].getSance()[1] = 50;
		}
		fitness = 0;
	}
	public Gen[] getGenom() {
		return genom;
	}

	public void setGenom(Gen[] genom) {
		this.genom = genom;
	}
	
	public void fillgenom(){
		Information info = Information.getinstance();
		HashMap<Integer,Integer>cisla = new HashMap<Integer,Integer>();
		int obvod = info.getObvod();
		for (int i = 0; i < genom.length; i++) {
			int cislo = getnumber(cisla,info);
			cisla.put(cislo, cislo);
			genom[i].setVstupdopola(cislo);
		}
	}
	public int getnumber(HashMap<Integer,Integer>numbers,Information info){
		ArrayList<Integer>kamene = info.getKamenenaokraji();
		int randomcislo=0;
		int obvod = info.getObvod();
		boolean trying = true;
		while(trying){
			randomcislo = 0 + (int)(Math.random() * (((info.getObvod()-1) - 0) + 1));
			if(kamene.contains(randomcislo) || numbers.containsKey(randomcislo)){
				
			}
			else{
				trying = false;
			}
		}
		return randomcislo;
	}
	public int getFitness() {
		return fitness;
	}
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	public HashMap<Integer,Integer> getCesta() {
		return cesta;
	}
	public void setCesta(HashMap<Integer,Integer> cesta) {
		this.cesta = cesta;
	}
}
