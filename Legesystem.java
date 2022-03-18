import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 
class Legesystem{
	private static IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
	private static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
	private static Prioritetskoe<Lege> leger = new Prioritetskoe<>();
	private static Lenkeliste<Resept> resepter = new Lenkeliste<>();

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
                        type++;
                        break;
                    }
					String[] filen = innlest.trim().split(",");
                    Pasient nyPasient = new Pasient(filen[0], filen[1]);
					pasienter.leggTil(nyPasient);
				}
			}
			if (type == 1){
				while (sc.hasNextLine()){
                    innlest = sc.nextLine();
                    if(innlest.contains("#")){
                        type++;
						break;
                    }
                    if (innlest.charAt(innlest.length() - 1) == ',') {
                        innlest = innlest.substring(0, innlest.length() - 1);
                    }
					String[] filen = innlest.trim().split(",");                    
					//navn, type, pris, virkestoff, [styrke]
                    if(filen[1].equals("vanlig")){
						// Runder til nærmeste heltall og caster fra double til int
                        Vanlig nyttVanlig = new Vanlig(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]));
                        legemidler.leggTil(nyttVanlig);
					}
					else if(filen[1].equals("narkotisk")){
						Narkotisk nyttNarkotisk = new Narkotisk(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]), Integer.parseInt(filen[4]));
						legemidler.leggTil(nyttNarkotisk);
					}
					else if(filen[1].equals("vanedannende")){
						Vanedannende nyttVanedannende = new Vanedannende(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]), Integer.parseInt(filen[4]));
						legemidler.leggTil(nyttVanedannende);
					}
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
	public static void meny() throws UlovligUtskrift{
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
			else if (!valg.equals("6")) {
				System.out.println("\nUgyldig input!");
			}
		}
		System.out.println("--- Programmet er avsluttet ---");
	}
	//E3
	private static void seFullstendigOversikt() {
		System.out.println("__Alle elementer i systemet__");
		System.out.println("\n__Pasienter__\n");
		for(Pasient pasient : pasienter){
			System.out.println(pasient.hentNavn());
		}
		System.out.println("\n__Legemidler__\n");
		for(Legemiddel legemiddel : legemidler){
			System.out.println(legemiddel);
		}
		System.out.println("\n__Leger__\n");
		for(Lege lege : leger){
			System.out.println(lege.hentType() + ": " + lege.hentNavn());
		}
		System.out.println("\n__Resepter__\n");
		for(Resept resept : resepter){
			System.out.println(resept);
		}
	}
	//E4
	public static void leggTilISystem(Scanner sc) throws UlovligUtskrift{ //legge til nye elementer i Legesystemet
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
				return;
			}
			else if (valg.equals("2")){
				leggTilLegemiddel(sc);
				return;
			}
			else if (valg.equals("3")){
				leggTilLege(sc);
				return;
			}
			else if (valg.equals("4")){
				leggTilResept(sc);
				return;
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
				System.out.println("Pris:");
				pris = ((int) Math.round(sc.nextDouble()));
				System.out.println("Virkestoff: ");
				virkestoff = sc.nextDouble();
				System.out.println("Styrke: ");
				styrke = sc.nextInt();

				Narkotisk nyttNarkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
				legemidler.leggTil(nyttNarkotisk);
				return;
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
				return;
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
				return;
			}
			else if (valg.equals("4")){ 
				return;
			}
			else { 
				System.out.println("Ugylidg input!");
			}
		}
		
		
	}
	public static void leggTilLege(Scanner sc){
		String navn;
		String kontrollId;

		String valg = "";
		String typeLege = "\n Hvilken type lege skal legges inn?"
		+"\n 1: Spesialist"
		+"\n 2: Vanlig"
		+"\n 3: Tilbake"
		+"\nSkriv inn et av alternativene:";

		while (!valg.equals("4")){
			System.out.println(typeLege);
			valg = sc.nextLine();

			if (valg.equals("1")){
				System.out.println("Navn: ");
				navn = sc.nextLine();
				System.out.println("KontrollID: ");
				kontrollId = sc.nextLine();
				if(kontrollId.equals("")){
					System.out.println("Du maa skrive en ID");
					return;
				}
				else{
					Spesialist nySpesialist = new Spesialist(navn, kontrollId);
					leger.leggTil(nySpesialist);
				}
			}
			if (valg.equals("2")){
				System.out.println("Navn: ");
				navn = sc.nextLine();
				Lege nyLege = new Lege(navn);
				leger.leggTil(nyLege);
			}
			if (valg.equals("3")){
				return;
			}
		}

		System.out.println("Navn: ");
        navn = sc.nextLine();
        System.out.println("KontrollID: ");
        kontrollId = sc.nextLine();
		if(kontrollId.equals("")){
			System.out.println("Du maa skrive en ID");
			return;
		}
		else{
			if(kontrollId.equals("0")){
				Lege nyLege = new Lege(navn);
				leger.leggTil(nyLege);
			}
			else{
				Spesialist nySpesialist = new Spesialist(navn, kontrollId);
				leger.leggTil(nySpesialist);
			}
		}
	}
	public static void leggTilResept(Scanner sc) throws UlovligUtskrift{
		int legeId;
		int legemiddelId;
		int pasientId;
		int reit;
		String valg = null;
		Legemiddel legemiddelet = null;
		Lege utskriftslege = null;
		Pasient pasienten = null;


		//legemiddel
		System.out.println("Velg et legemiddel: ");
		for(Legemiddel legemiddel : legemidler){
			System.out.println(legemiddel.hentId() + ": " + legemiddel.hentNavn() + "(" +legemiddel.hentType() + ")");
		}
		legemiddelId = sc.nextInt();
		int teller = 0;
		if(legemiddelId <= (legemidler.stoerrelse()) && legemiddelId >= 1){
			for(Legemiddel legemiddel : legemidler){
				teller ++;
				if(legemiddelId == legemiddel.hentId()){
					legemiddelet = legemidler.hent(teller - 1);
					System.out.println(legemiddelet);
				}
			}
		}
		else{
			System.out.println("Uyldig input!");
			return;
		}
		//lege
		System.out.println("Velg en lege:");
		teller = 0;
		for(Lege lege : leger){
			teller++;
			System.out.println(teller + ": " + lege.hentNavn() + "(" + lege.hentType() + ")");
		}
		if(legemiddelet.hentType().equals("Narkotisk")){
			System.out.println("Velg en av legene (du valgte narkotisk, så velg en spesialist): ");
		}
		else{
			System.out.println("Velg en av legene: ");
		}
		legeId = sc.nextInt();
		
		if(legeId <= (leger.stoerrelse()) && legeId >= 1){
			utskriftslege = leger.hent(legeId - 1);
			System.out.println(utskriftslege.hentNavn());
		}
		else{
			System.out.println("Uyldig input!");
			return;
		}
		//pasient
		System.out.println("Velg en pasient: ");
		for(Pasient pasient : pasienter){
			System.out.println(pasient.hentID() + ": " + pasient.hentNavn());
		}
		pasientId = sc.nextInt();
		teller = 0;
		if(pasientId <= (pasienter.stoerrelse()) && pasientId >= 1){
			for(Pasient pasient : pasienter){
				teller ++;
				if(pasientId == pasient.hentID()){
					pasienten = pasienter.hent(teller - 1);
					System.out.println(pasienten);
				}
			}
		}
		else{
			System.out.println("Uyldig input!");
			return;
		}
		//Resept type
		System.out.println("Velg en respt: ");
		String typerResept = "\n Hvilken type har resept?"
		+"\n 1: blaa"
		+"\n 2: hvit"
		+"\n 3: militaer"
		+"\n 4: p-resept";
		System.out.println(typerResept);
		if(legemiddelet.hentType().equals("Narkotisk")){
			System.out.println("Du har valgt narkotisk, så velg blå resept:");
		}
		else{
			System.out.println("Velg en resept:");
		}
		valg = sc.nextLine();
		if(valg != null){
			while (!(valg.equals("1") || valg.equals("2") || valg.equals("3") || valg.equals("4"))){
				System.out.println("Du maa velge et av tall-alternativene 1-4");
				valg = sc.nextLine();
			}
		}
		System.out.println("\nvelg et antall for reit");
		reit = sc.nextInt();
		if(valg.equals("1")){
			BlaaResept blaa = utskriftslege.skrivBlaaResept(legemiddelet, pasienten, reit);
			resepter.leggTil(blaa);
		}
		else if(valg.equals("2")){
			HvitResept hvit = utskriftslege.skrivHvitResept(legemiddelet, pasienten, reit);
			resepter.leggTil(hvit);
		}
		else if(valg.equals("3")){
			Militaerresept militaer = utskriftslege.skrivMilResept(legemiddelet, pasienten);
			resepter.leggTil(militaer);
		}
		else{
			PResept p = utskriftslege.skrivPResept(legemiddelet, pasienten, reit);
			resepter.leggTil(p);
		}
	}
	public static void brukResept(Scanner sc){
		//velg pasient
		//velg resept
		//tilbke til hovedmeny
	}
    public static void main(String[] args) throws NumberFormatException, UlovligUtskrift {

        lesFraFil("LegeData.txt");
        // lesFraFil("nyStorFil.txt");

        meny();
	}
}