import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 
class Legesystem{
	private static IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
	private static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
	private static Prioritetskoe<Lege> leger = new Prioritetskoe<>();
	private static Lenkeliste<Resept> resepter = new Lenkeliste<>();

    //private static Scanner user_input = new Scanner(System.in);
	//E1
	private static void lesFraFil(String filnavn) throws NumberFormatException, UlovligUtskrift {
		File f = new File(filnavn);
		Scanner sc = null;
		
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("File '" + filnavn + "' not found!");
		}
        
        int type = 0;
		String innlest = sc.nextLine();

        while (sc.hasNextLine()){			
			if (type == 0){
				while (sc.hasNextLine()){
                    innlest = sc.nextLine();
                    if(innlest.contains("#")){
                        System.out.println("break");
                        type++;
                        break;
                    }
					String[] filen = innlest.trim().split(",");
                    Pasient nyPasient = new Pasient(filen[0], filen[1]);
                    //System.out.println(nyPasient);
					pasienter.leggTil(nyPasient);
					System.out.println("la til pasient");
				}
			}
			if (type == 1){
				while (sc.hasNextLine()){
                    innlest = sc.nextLine();
                    if(innlest.contains("#")){
                        System.out.println("break");
                        type++;
						break;
                    }
                    if (innlest.charAt(innlest.length() - 1) == ',') {
                        innlest = innlest.substring(0, innlest.length() - 1);
                    }
					String[] filen = innlest.trim().split(",");
                    System.out.println("la til legemiddel");                    
					//navn, type, pris, virkestoff, [styrke]
                    if(filen[1].equals("vanlig")){
						// Runder til nærmeste heltall og caster fra double til int
                        Vanlig nyttVanlig = new Vanlig(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]));
                        legemidler.leggTil(nyttVanlig);
					//legg til pasient i pos
					}
					else if(filen[1].equals("narkotisk")){
						Narkotisk nyttNarkotisk = new Narkotisk(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]), Integer.parseInt(filen[4]));
						legemidler.leggTil(nyttNarkotisk);
					}
					//legg til pasient i pos
					else if(filen[1].equals("vanedannende")){
						Vanedannende nyttVanedannende = new Vanedannende(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]), Integer.parseInt(filen[4]));
						legemidler.leggTil(nyttVanedannende);
					}
					//legg til pasient i pos
				}
			}
			if (type == 2){
				while (sc.hasNextLine()){
                    innlest = sc.nextLine();
                    if(innlest.contains("#")){
                        System.out.println("break");
                        type++;
						break;
					}
					String[] filen = innlest.trim().split(",");
                    System.out.println("la til lege");

					if (filen[1].equals("0")){
						Lege nyLege = new Lege(filen[0]);
						leger.leggTil(nyLege);
					} else{
						Spesialist nySpesialist = new Spesialist(filen[0], (filen[1]));
						leger.leggTil(nySpesialist);
					}
				}
			}
			// legmiddelnr, legeNavn, pasientID,type, [reit]
			if (type == 3){
				while (sc.hasNextLine()){
                    innlest = sc.nextLine();
                    if(innlest.contains("#")){
                        System.out.println("break");
                        type++;
						break;
					}
					String[] filen = innlest.trim().split(",");
                    
					for(Lege lege : leger){
						if(lege.hentNavn().equals(filen[1])){
                            System.out.println("la til resept");
							if (filen[3].equals("hvit")){
								HvitResept hvit = lege.skrivHvitResept(legemidler.hent(Integer.parseInt(filen[0])-1), pasienter.hent(Integer.parseInt(filen[2])), Integer.parseInt(filen[4]));
								resepter.leggTil(hvit);
							}
							else if(filen[3].equals("blaa")){
								BlaaResept blaa = lege.skrivBlaaResept(legemidler.hent(Integer.parseInt(filen[0])-1), pasienter.hent(Integer.parseInt(filen[2])), Integer.parseInt(filen[4]));
								resepter.leggTil(blaa);
							}
							else if(filen[3].equals("militaer")) {
								Militaerresept militaer = lege.skrivMilResept(legemidler.hent(Integer.parseInt(filen[0])-1), pasienter.hent(Integer.parseInt(filen[2])));
								resepter.leggTil(militaer);
							}
							else if(filen[3].equals("p")) {
								PResept p = lege.skrivPResept(legemidler.hent(Integer.parseInt(filen[0])-1), pasienter.hent(Integer.parseInt(filen[2])), Integer.parseInt(filen[4]));
								resepter.leggTil(p);
							}
						}
					}
					
				}
				
			}
		}
	}
	//E2
	public static void meny(){
		String valg = "";
		String meny = "\n__Legesystem__"
		+"\nVelg et alternativ"
		+"\n1: Se fullstendig oversikt" 
		+"\n2: Legge til et nytt element" 
		+"\n3: Bruk en resept" 
		+"\n4: Se statistikk" 
		+"\n5: Skriv data til fil" 
		+"\n6: Avslutt programmet";
		Scanner sc = new Scanner(System.in);

		while (!valg.equals("6")){
			System.out.println(meny);

			valg = sc.nextLine();
			if (valg.equals("1")){
				seFullstendigOversikt();
			}
			else if (valg.equals("2")){
                leggTilISystem(sc);
			}
			else if (valg.equals("3")){
				continue;
			}
			else if (valg.equals("4")){
				continue;
			}
			else if (valg.equals("5")){
				continue;
			}
			else {
				System.out.println("\nUgyldig input!");
			}
		}
		System.out.println("--- Programmet er avsluttet ---");
	}
	//E3
	private static void seFullstendigOversikt() {
		System.out.println("__Alle elementer i systemet__");
		System.out.println("\n__Pasienter__");
		for(Pasient pasient : pasienter){
			System.out.println(pasient.hentNavn());
		}
		System.out.println("\n__Legemidler__\n");
		for(Legemiddel legemiddel : legemidler){
			System.out.println(legemiddel);
		}
		System.out.println("\n__Leger__\n");
		for(Lege lege : leger){
			System.out.println(lege.hentNavn());
		}
		System.out.println("\n__Resepter__\n");
		for(Resept resept : resepter){
			System.out.println(resept);
		}
	}
	//E4
	public static void leggTilISystem(Scanner sc){ //legge til nye elementer i Legesystemet
		String valg = "";
		String elementer = "\n__Elementer__"
		+"\nHvilket element onsker du aa legge til?"
		+"\n1: Pasient" 
		+"\n2: Legemiddel" 
		+"\n3: Lege" 
		+"\n4: Resept" 
		+"\n5: Tilbake til meny!"
		+"\nSkriv inn et av alternativene:";

		while (!valg.equals("5")){
			System.out.println(elementer);

			valg = sc.nextLine();
			if (valg.equals("1")){
				leggTilPasient(sc);
			}
			else if (valg.equals("2")){
				leggTilLegemiddel(sc);
			}
			else if (valg.equals("3")){
				continue;
			}
			else if (valg.equals("4")){
				continue;
			}
			else if (valg.equals("5")){ 
				return;
			}
			else {
				System.out.println("\nUgyldig input!");
			}
		}
		System.out.println("--- Programmet er avsluttet ---");
	}

	public static void leggTilPasient(Scanner sc){
		String navn;
		String fnr;
		Pasient nyPasient;

		System.out.println("Navn: ");
        navn = sc.nextLine();
        System.out.println("Fodselsnummer: ");
        fnr = sc.nextLine();
        nyPasient = new Pasient(navn, fnr);
        pasienter.leggTil(nyPasient);
		System.out.println(pasienter);
	}
    public static void leggTilLegemiddel(Scanner sc){
		String navn;
		int pris;
		double virkestoff;
        int styrke;
		//Meny for aa vlege type legemiddel:

		String valg = "";
		String typerLegemiddel = "\n Hvilken type har legemiddelet?"
		+"\n 1: Narkotisk"
		+"\n 2: Vanedannende"
		+"\n 3: Vanlig"
		+"\n 4: Tilbake"
		+"\nSkriv inn et av alternativene:";

		while (!valg.equals("4")){
			System.out.println(typerLegemiddel);

			valg = sc.nextLine();
			if (valg.equals("1")){ //Narkotisk
				System.out.println("Navn: ");
				navn = sc.nextLine();
				System.out.println("Pris");
				pris = ((int) Math.round(sc.nextDouble()));
				System.out.println("Virkestoff: ");
				virkestoff = sc.nextDouble();
				System.out.println("Styrke: ");
				styrke = sc.nextInt();

				Narkotisk nyttNarkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
				legemidler.leggTil(nyttNarkotisk);


			}
			else if (valg.equals("2")){ //Vanedannende
				System.out.println("Navn: ");
				navn = sc.nextLine();
				System.out.println("Pris");
				pris = ((int) Math.round(sc.nextDouble()));
				System.out.println("Virkestoff: ");
				virkestoff = sc.nextDouble();
				System.out.println("Styrke: ");
				styrke = sc.nextInt();

				Vanedannende nyttVanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
				legemidler.leggTil(nyttVanedannende);
			}
			else if (valg.equals("3")){ //vanlig
				System.out.println("Navn: ");
				navn = sc.nextLine();
				System.out.println("Pris");
				pris = ((int) Math.round(sc.nextDouble()));
				System.out.println("Virkestoff: ");
				virkestoff = sc.nextDouble();

				Vanlig nyVanlig = new Vanlig(navn, pris, virkestoff);
				legemidler.leggTil(nyVanlig);
			}
			else if (valg.equals("4")){ 
				return;
			}
			else {
				System.out.println("\nUgyldig input!");
			}
			
		}
		
		
	}
    public static void main(String[] args) throws NumberFormatException, UlovligUtskrift {

        lesFraFil("LegeData.txt");
        // lesFraFil("nyStorFil.txt");

        meny();
	}
}
