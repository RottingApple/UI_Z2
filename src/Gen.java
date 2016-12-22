import java.util.HashMap;

public class Gen {
	private int vstupdopola;
	private int[] sance;
	public Gen(int vstupdopola, int[] sance) {
		this.vstupdopola = vstupdopola;
		this.sance = sance;
	}
	public Gen (){
		sance = new int[2];
	}
	public int getVstupdopola() {
		return vstupdopola;
	}
	public void setVstupdopola(int vstupdopola) {
		this.vstupdopola = vstupdopola;
	}
	public int[] getSance() {
		return sance;
	}
	public void setSance(int[] sance) {
		this.sance = sance;
	}
	
}
