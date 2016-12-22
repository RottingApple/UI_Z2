import java.util.Arrays;
import java.util.HashMap;

public class FitnessFunkcia {
	//public int get
	public static int hrabaj(Jedinec jedinec){
		Information info = Information.getinstance();
		int dlzkamapy = info.getDlzka();
		int sirkamapy = info.getSirka();
		int fitness = 0;
		//System.out.println("Obvod mapy je ");
		boolean[][] mapa = info.getMapa();
		int poradie = 1;
		HashMap<Integer,Integer>cesta = new HashMap<Integer,Integer>();
		
		
		for (int i = 0; i < jedinec.getGenom().length; i++) {
			Gen gen = jedinec.getGenom()[i];
			//System.out.print("Urcujem "+gen.getVstupdopola()+" ");
			String smer = urcismer(info,gen.getVstupdopola()); 
			//System.out.println("Smer je "+smer);
			boolean naceste = true;
			int[] startovnapozicia = urcipoziciu(info,gen.getVstupdopola());
			int y = startovnapozicia[0];
			int x = startovnapozicia[1];
			//System.out.println("Suradnice mam :"+(y*info.getDlzka()+x) );
			if(mapa[y][x] && !cesta.containsKey(y*info.getDlzka()+x)){
				cesta.put(y*info.getDlzka()+x,poradie);
				fitness++;
				//cesticka[y][x] = poradie;
			}else
				naceste = false;
			while(naceste){
				int[]novapozicia = novykrok(info,smer,x,y);
				int novyy = novapozicia[0];
				int novyx = novapozicia[1];
				//System.out.println("Suradnice mam :"+(novyy*info.getDlzka()+novyx) );
				String vysledok = checknipoziciu(info,novyx,novyy,cesta,mapa);
				if(vysledok == "Volna"){
					//dobre
					//cesticka[novyy][novyx] = poradie;
					cesta.put(novyy*info.getDlzka()+novyx,poradie);
				//	System.out.println("Trasa je volna");
				}
				if(vysledok == "Prek"){
					novyy = y;
					novyx = x;
					//System.out.println("Narazil som na prekazku");
					String novysmer = zvolinutrasu(info, x,y, gen, smer, cesta, mapa);
					if(novysmer == "Vysiel"){
						poradie++;
						naceste = false;
						fitness++;
					}
					if(novysmer == "Koniec" ){
					//System.out.println("Koncim nemam uz co robit, fitness je "+fitness);
						fitness = 0;
						if(fitness > jedinec.getFitness()){
							jedinec.setFitness(fitness);
							jedinec.setCesta(cesta);
						}
						return fitness;
					}
				//	System.out.println("Novy smer je "+novysmer);
					fitness--;
					smer = novysmer;
				}
				if(vysledok == "Vysiel"){
				//	System.out.println("Vysiel som zmapy");
					poradie++;
					naceste = false;
				}
				if(naceste)
				fitness++;
				x = novyx;
				y = novyy;
			}
		}
		//System.out.println("Fitness je "+fitness);
		if(fitness > jedinec.getFitness()){
			jedinec.setFitness(fitness);
			jedinec.setCesta(cesta);
		}
		return 0;
	}
	public static String zvolinutrasu(Information info,int x,int y, Gen gen,String smer,HashMap<Integer,Integer>cesta,boolean[][]mapa){
		int randomcislo = 0 + (int)(Math.random() * (((100) - 0) + 1));
		String novysmer=null;
		if(smer == "Dohora" || smer == "Dole"){
			if(gen.getSance()[0] >= randomcislo){
				novysmer = "Doprava";
			}
			else{
				novysmer = "Dolava";
			}
			int[] novapozicia = novykrok(info,novysmer,x,y);
			int novyy = novapozicia[0];
			int novyx = novapozicia[1];
			String vysledok = checknipoziciu(info,novyx,novyy,cesta,mapa);
			if(vysledok == "Volna"){
				return novysmer;
			}
			if(vysledok == "Vysiel")
				return "Vysiel";
			if(novysmer == "Doprava")
				novysmer = "Dolava";
			else
				novysmer = "Doprava";
			novapozicia = novykrok(info,novysmer,x,y);
			novyy = novapozicia[0];
			novyx = novapozicia[1];
			vysledok = checknipoziciu(info,novyx,novyy,cesta,mapa);
			if(vysledok == "Volna"){
				return novysmer;
			}
			if(vysledok == "Vysiel")
				return "Vysiel";
			return "Koniec";
		}
		if(smer == "Doprava" || smer == "Dolava"){
			if(gen.getSance()[0] >= randomcislo){
				novysmer = "Dohora";
			}
			else{
				novysmer = "Dole";
			}
			int[] novapozicia = novykrok(info,novysmer,x,y);
			int novyy = novapozicia[0];
			int novyx = novapozicia[1];
			String vysledok = checknipoziciu(info,novyx,novyy,cesta,mapa);
			if(vysledok == "Volna"){
				return novysmer;
			}
			if(vysledok == "Vysiel")
				return "Vysiel";
			if(novysmer == "Dohora")
				novysmer = "Dole";
			else
				novysmer = "Dohora";
			novapozicia = novykrok(info,novysmer,x,y);
			novyy = novapozicia[0];
			novyx = novapozicia[1];
			vysledok = checknipoziciu(info,novyx,novyy,cesta,mapa);
			if(vysledok == "Volna"){
				return novysmer;
			}
			if(vysledok == "Vysiel")
				return "Vysiel";
			return "Koniec";
		}
		return null;
		
	}
	private static int[] urcipoziciu(Information info, int vstup) {
		// TODO Auto-generated method stub
		int[] suradnice = new int[2];
		if(vstup-info.getDlzka()< 0 ){
			suradnice[0] = 0;
			suradnice[1] = vstup;
			return suradnice;
		}
		if(vstup-(info.getDlzka()+info.getSirka()) < 0 ){
			suradnice[0]= vstup-info.getDlzka();
			suradnice[1]= info.getDlzka()-1;
			return suradnice;
		}
		if( vstup- (2*info.getDlzka()+info.getSirka()) < 0 ){
			suradnice[0] = info.getSirka()-1;
			int temp = vstup-(info.getDlzka()+info.getSirka());
			temp = info.getDlzka() - temp-1;
			suradnice[1] = temp;
			return suradnice;
		}
		else{
			int temp = vstup- (2*info.getDlzka()+info.getSirka());
			temp = info.getSirka() - 1 - temp;
			suradnice[0]=temp;
			suradnice[1]=0;
			return suradnice;
		}
	}

	public static String urcismer(Information info,int vstup){
		if(vstup-info.getDlzka()< 0 )
			return "Dole";
		if(vstup-(info.getDlzka()+info.getSirka()) < 0 )
			return "Dolava";
		if( vstup- (2*info.getDlzka()+info.getSirka()) < 0 )
			return "Dohora";
		else
			return "Doprava";
	}
	public static int[] novykrok(Information info,String smer,int x,int y){
		if(smer == "Dole"){
			int[] vysledok = new int[2];
			vysledok[0] = y+1;
			vysledok[1] = x;
			return vysledok;
		}
		if(smer == "Dolava"){
			int[] vysledok = new int[2];
			vysledok[0] = y;
			vysledok[1] = x-1;
			return vysledok;		}
		if(smer == "Dohora"){
			int[] vysledok = new int[2];
			vysledok[0] = y-1;
			vysledok[1] = x;
			return vysledok;		}
		if(smer == "Doprava"){
			int[] vysledok = new int[2];
			vysledok[0] = y;
			vysledok[1] = x+1;
			return vysledok;		}
		return null;
	}
	public static String checknipoziciu(Information info,int x, int y, HashMap<Integer,Integer>cesta,boolean[][] mapa){
		if(y >= info.getSirka() || x >= info.getDlzka() || x < 0 || y < 0)
			return "Vysiel";
		if(!mapa[y][x] || cesta.containsKey(y*info.getDlzka()+x) ){
			return "Prek";
		}
		return "Volna";
		
	}
}
