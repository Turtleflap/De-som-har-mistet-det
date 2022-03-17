import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;  
class Legesystem{
	private static IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
	private static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
	private static Prioritetskoe<Lege> leger = new Prioritetskoe<>();
	private static Lenkeliste<Resept> resepter = new Lenkeliste<>();

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
                        Vanlig nyttVanlig = new Vanlig(filen[0], Double.parseDouble(filen[2]), Double.parseDouble(filen[3]));
                        legemidler.leggTil(nyttVanlig);
					//legg til pasient i pos
					}
					else if(filen[1].equals("narkotisk")){
						Narkotisk nyttNarkotisk = new Narkotisk(filen[0], Double.parseDouble(filen[2]), Double.parseDouble(filen[3]), Integer.parseInt(filen[4]));
						legemidler.leggTil(nyttNarkotisk);
					}
					//legg til pasient i pos
					else if(filen[1].equals("vanedannende")){
						Vanedannende nyttVanedannende = new Vanedannende(filen[0], Double.parseDouble(filen[2]), Double.parseDouble(filen[3]), Integer.parseInt(filen[4]));
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
	private static void seFullstendigOversikt() {
		System.out.println("Pasienter:\n");
		for(Pasient pasient : pasienter){
			System.out.println(pasient.hentNavn());
		}
		System.out.println("\nLegemidler:\n");
		for(Legemiddel legemiddel : legemidler){
			System.out.println(legemiddel);
		}
		System.out.println("\nLeger:\n");
		for(Lege lege : leger){
		System.out.println(lege.hentNavn());
		}
		System.out.println("\nResepter:\n");
		for(Resept resept : resepter){
			System.out.println(resept);
		}
	}
    private static void LeggTilISystem(){
        int valg = 0;
        System.out.println("\nHva ønsker du å legge til?\n" +
        "0: Pasient\n" + 
        "1: Legemiddel\n" + 
        "2: Lege\n" + 
        "3: Resept" +
        "Skriv inn et av alternativene: ");
        Scanner scn = new Scanner(System.in);
        valg = scn.nextInt();
        if(valg == 0){
            System.out.println("Du valgte pasient");
            scn.close();
        }
        else if(valg == 1){
            System.out.println("Du valgte legemiddel");
        }
        else if(valg == 2){
            System.out.println("Du valgte lege");
        }
        else if(valg == 3){
            System.out.println("Du valgte resept");
        }
        else{
            System.out.println("\nDu må velge et tall mellom 0 og 3");
            LeggTilISystem();
        }
    }

		public static void main(String[] args) throws NumberFormatException, UlovligUtskrift {
		int svar = 0;
		while (svar != 6) {
			try {
				System.out.println("Velg et alternativ\n" +
				"1: Se fullstendig oversikt\n" +
				"2: Legge til et nytt element\n" +
				"3: Bruk en resept\n" +
				"4: Se statistikk\n" +
				"5: Skriv data til fil\n" +
				"6: Avslutt programmet");
				System.out.print("Skriv inn alternativ: ");
				Scanner sc = new Scanner(System.in);
				svar = sc.nextInt();
				if (svar == 1) {
                    sc.close();
					continue;
				}
				if (svar == 2) {
					lesFraFil("LegeDataStor.txt");
					seFullstendigOversikt();
					continue;
				}
				if (svar == 3) {
                    sc.close();
                    LeggTilISystem();
                    continue;
				}
				if (svar == 4) {
                    sc.close();
					continue;
				}
				if (svar == 5) {
                    sc.close();
                    continue;
				}
				// Hvis svaret ikke matcher noen valg, start while lokken på nytt
				if (svar < 1 || svar > 6) {
					System.out.println("\nTallet du valgte har ingen alternativ!");
					continue;
				}
                sc.close();
				// Lukker scanner klassen
				// InputMismatchException gir feil hvis en string blir skrevet inn
				// https://www.tutorialspoint.com/what-is-inputmismatchexception-in-java-how-do-we-handle-it
			} catch (InputMismatchException e) {
				System.out.println("Aksepterer bare tall!\n");
			}
		}
		System.out.println("--- Avsluttet programmet ---");
	}
}