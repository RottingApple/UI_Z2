import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Vypocet {
	
	public Vypocet(){
		precitajvstup();
		vypocet();
	}


	public static void precitajvstup(){
		try {
			Scanner s = new Scanner(new FileReader("testovanie2.txt"));
			Information info = Information.getinstance();
			info.setDlzka(s.nextInt());
			info.setSirka(s.nextInt());
			boolean[][] mapa = new boolean[info.getSirka()][info.getDlzka()];
			int pocetpolicokprazdnych = 0;
			int kamenenaokraji = 0;
			int pocetkamenov= 0;
			System.out.println("Sirka je "+info.getSirka()+" Dlzka je "+info.getDlzka());
			for (int i = 0; i < info.getSirka(); i++) {
				for (int j = 0; j < info.getDlzka(); j++) {
					char noda = s.next().trim().charAt(0);
					if(noda == 'K'){
						if( (i==0) || (i==info.getSirka()-1) || (j == 0)  || (j == info.getDlzka()-1) ){
							//System.out.println("Kamen je na kraji");
							if(i == 0){
								info.getKamenenaokraji().add(j);
							}
							if(j == 0){
								info.getKamenenaokraji().add(4*info.getDlzka() - i-1);
							}
							if(j == info.getDlzka()-1){
								info.getKamenenaokraji().add(info.getDlzka()+i);
							}
							if(i == info.getSirka()-1){
								info.getKamenenaokraji().add(3*info.getDlzka()-j-1);
							}
							kamenenaokraji++;
						}
						pocetkamenov++;
						mapa[i][j] = false;
					}
					else{
						mapa[i][j] = true;
						pocetpolicokprazdnych++;
					}
					System.out.print(noda+" ");
				}
				System.out.println("");
			}
			System.out.println("Pocet kamenov: "+pocetkamenov+"\nPocet kamenov na okraji "+kamenenaokraji );
			System.out.println("Pocet prazdnych miest "+pocetpolicokprazdnych);
			info.setPocetvolnych(pocetpolicokprazdnych);
			info.setMapa(mapa);
			info.setPocet_kamenov(pocetkamenov);
			info.setObvod(2*info.getDlzka()+2*info.getSirka());
			info.setDelic(info.getObvod()/4);
			info.setPocetgenov(info.getObvod()/2 + info.getPocet_kamenov());
			System.out.println("Obvod je "+info.getObvod()+"\nDelic je "+info.getDelic());
			System.out.println("Dlzka genomu jedinca: "+info.getPocetgenov());
			//for (int i = 0; i < info.getKamenenaokraji().size(); i++) {
			//	System.out.print(info.getKamenenaokraji().get(i)+" ");
			//}
			//System.out.println("Ukoncujem");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void vypocet() {
		// TODO Auto-generated method stub
		Information info = Information.getinstance();
		Populacia populacia = new Populacia();
		Jedinec jedinec = null;
		int najdenageneracia = -1;
		populacia.setPopulacia(new Jedinec[100]);
		for (int i = 0; i < 100; i++) {
			jedinec = new Jedinec(info.getPocetgenov());
			jedinec.fillgenom();
			populacia.getPopulacia()[i] = jedinec;
		}
		/////////////////Init state
		int o = 0;
		while(o<info.getPocet_generacii()){
			for (int i = 0; i < 100; i++) {
				jedinec = populacia.getPopulacia()[i];
				FitnessFunkcia.hrabaj(jedinec);
				FitnessFunkcia.hrabaj(jedinec);
				FitnessFunkcia.hrabaj(jedinec);
				if(jedinec.getFitness() == info.getPocetvolnych()){
					System.out.println("Hura pohrabal som vsetky policka!");
					System.out.println("Generacia: "+(o+1));
					najdenageneracia = o;
					o = info.getPocet_generacii();
					i= 100;
				}
				//jedinec.setFitness((fit1+fit2)/2);
				//int fit3 = FitnessFunkcia.hrabaj(jedinec);
			//	System.out.println("Jedincova fitness je "+jedinec.getFitness());
			}
			populacia.sortnipopulaciu();
			populacia.setNovapopulacia(new Jedinec[100]);
			////elitarizmus
			int j=0;
			//Populacia novapop = new Populacia();
			for(j = 0 ; j<5; j++){
				populacia.getNovapopulacia()[j] = populacia.getPopulacia()[populacia.getPopulacia().length-j-1];
				//jedinec= populacia.getNovapopulacia()[j];
				//System.out.println("Jedincova fitness je "+jedinec.getFitness());
			}
			//////////////////////////////////////////////////////////////////////krizenie
			Jedinec krizenec;
			for(int z = 0 ; z < 20; z ++){
				int firstnumber = 80 + (int)(Math.random() * (((100) - 80) + 1))-1;
				int secondnumber = 20 + (int)(Math.random() * (((99) - 20) + 1));
				//System.out.println("Krizim jedinca "+firstnumber+" s "+secondnumber);
				krizenec = populacia.skryzjedincov(firstnumber, secondnumber);
				populacia.getNovapopulacia()[j] = krizenec;
				j++;
			}
			for(int z = 0 ; z < 20; z ++){
				int firstnumber = 60 + (int)(Math.random() * (((100) - 60) + 1))-1;
				int secondnumber = 0 + (int)(Math.random() * (((99) - 0) + 1));
				//System.out.println("Krizim jedinca "+firstnumber+" s "+secondnumber);
				krizenec = populacia.skryzjedincov(firstnumber, secondnumber);
				populacia.getNovapopulacia()[j] = krizenec;
				j++;
			}
			for(int z = 0 ; z < 20; z ++){
				int firstnumber = 40 + (int)(Math.random() * (((100) - 40) + 1))-1;
				int secondnumber = 0 + (int)(Math.random() * (((99) - 0) + 1));
				//System.out.println("Krizim jedinca "+firstnumber+" s "+secondnumber);
				krizenec = populacia.skryzjedincov(firstnumber, secondnumber);
				populacia.getNovapopulacia()[j] = krizenec;
				j++;
			}
			for(int z = 0 ; z < 20; z ++){
				int firstnumber = 20 + (int)(Math.random() * (((100) - 20) + 1))-1;
				int secondnumber = 0 + (int)(Math.random() * (((99) - 0) + 1));
				//System.out.println("Krizim jedinca "+firstnumber+" s "+secondnumber);
				krizenec = populacia.skryzjedincov(firstnumber, secondnumber);
				populacia.getNovapopulacia()[j] = krizenec;
				j++;
			}
			for (int i = 0; i < 15; i++) {
				jedinec = new Jedinec(info.getPocetgenov());
				jedinec.fillgenom();
				populacia.getNovapopulacia()[j] = jedinec;
				j++;
			}
			if(o<info.getPocet_generacii()-1)
			populacia.setPopulacia(populacia.getNovapopulacia());
			//populacia.vypispopulaciu();
			//System.out.println("J je "+j);
			System.out.println(populacia.getPopulacia()[0].getFitness());
			o++;
		}
	//	populacia.vypispopulaciu();
		int sum = 0;
		int jozko = 1;
		for (int i = 0; i < populacia.getPopulacia().length; i++) {
			if(populacia.getPopulacia()[i].getFitness() != 0){
			sum += populacia.getPopulacia()[i].getFitness();
			jozko++;
			}
		}
		sum = sum/jozko;
		System.out.println("Priemerna fitness populacie "+sum);
		Jedinec testik = populacia.getPopulacia()[99];
		if(najdenageneracia == -1){
			System.out.println("Nepooral som vsetky policka vypisujem najlepsie riesenie");
			System.out.println("Pocet prejdenych je "+testik.getFitness() + " z "+info.getPocetvolnych());
		}
		else{
			System.out.println("Riesenie je : ");
		}
		for(int i = 0;i < info.getSirka(); i ++){
			for(int j = 0; j < info.getDlzka(); j++){
				if(info.getMapa()[i][j]){
					if(testik.getCesta().containsKey(i*info.getDlzka()+j)){
						System.out.printf("%3d",testik.getCesta().get(i*info.getDlzka()+j));
					}else{
					System.out.printf("%3d",0);
					}
				}
				else{
					System.out.printf("%3s",'K');
				}
			}
			System.out.println("");
		}
		//System.out.println("\033[31m RED");
		System.out.printf("%3d\n",9);
	}

}
