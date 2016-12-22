import java.util.Arrays;
import java.util.Comparator;

public class Populacia {
	static private Comparator<Jedinec> jedincovafitness;
	static{
		jedincovafitness = new Comparator<Jedinec>(){

			@Override
			public int compare(Jedinec o1, Jedinec o2) {
				return Integer.compare(o1.getFitness(), o2.getFitness());
			}
			
		};
	}
	private Jedinec[] populacia;
	private Jedinec[] novapopulacia;
	public void sortnipopulaciu(){
		Arrays.sort(populacia,jedincovafitness);
	}
	public Jedinec[] getPopulacia() {
		return populacia;
	}
	public void setPopulacia(Jedinec[] populacia) {
		this.populacia = populacia;
	}
	public Jedinec[] getNovapopulacia() {
		return novapopulacia;
	}
	public void setNovapopulacia(Jedinec[] novapopulacia) {
		this.novapopulacia = novapopulacia;
	}
	public void vypispopulaciu() {
		// TODO Auto-generated method stub
		for (int i = 0; i < populacia.length; i++) {
			System.out.println("Jedinec "+i+" ma fitness "+populacia[i].getFitness());
		}
		System.out.println("");
	}
	public Jedinec skryzjedincov(int prvy, int druhy ){
		Information info = Information.getinstance();
		Jedinec novy = new Jedinec(info.getPocetgenov());
		Jedinec prvyjedinec = populacia[prvy];
		Jedinec druhyjedinec = populacia[druhy];
		int zvysok = info.getPocetgenov() % 2;
		int polovica = info.getPocetgenov() / 2 +zvysok;
		for(int i = 0 ; i<polovica;i++){
			novy.getGenom()[i].setVstupdopola(prvyjedinec.getGenom()[i].getVstupdopola());
		}
		for(int i = polovica ; i<info.getPocetgenov(); i++){
			novy.getGenom()[i].setVstupdopola(druhyjedinec.getGenom()[i].getVstupdopola());
		}
	/*	System.out.println("Genom jedinca je : ");
		for(int i = 0 ; i< info.getPocetgenov(); i++){
			System.out.print(prvyjedinec.getGenom()[i].getVstupdopola()+" ");
		}
		System.out.println("");
		System.out.println("Genom jedinca je : ");
		for(int i = 0 ; i< info.getPocetgenov(); i++){
			System.out.print(druhyjedinec.getGenom()[i].getVstupdopola()+" ");
		}
		System.out.println("");
		System.out.println("Genom jedinca je : ");
		for(int i = 0 ; i< info.getPocetgenov(); i++){
			System.out.print(novy.getGenom()[i].getVstupdopola()+" ");
		}
		System.out.println("");*/
		return novy;
	}
}
