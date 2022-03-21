import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.io.PrintWriter;

class Legesystem{
	private static IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
	private static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
	private static Prioritetskoe<Lege> leger = new Prioritetskoe<>();
	private static Lenkeliste<Resept> resepter = new Lenkeliste<>();

	// E1
	private static void lesFraFil(String filnavn) throws NumberFormatException, UlovligUtskrift { 
		File f = new File(filnavn);
		Scanner sc = null;
		
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("Fil " + filnavn + "' ikke funnet!");
		}
        
        int type = 0;
		String innlest = sc.nextLine();

        while (sc.hasNextLine()){			
			if (type == 0){
				while (sc.hasNextLine()){
                    innlest = sc.nextLine();
                    if (innlest.contains("#")) {
                        type++;
                        break;
                    }
					String[] filen = innlest.trim().split(",");
                    Pasient nyPasient = new Pasient(filen[0], filen[1]);
					pasienter.leggTil(nyPasient);
				}
			}
			// oppretter objekter og legger til i riktig liste		
			if (type == 1){
				while (sc.hasNextLine()){
                    innlest = sc.nextLine();
                    if (innlest.contains("#")) {
                        type++;
						break;
                    }
                    if (innlest.charAt(innlest.length() - 1) == ',') {
                        innlest = innlest.substring(0, innlest.length() - 1);
                    }
					String[] filen = innlest.trim().split(",");                    
					// navn, type, pris, virkestoff, [styrke]
                    if (filen[1].equals("vanlig")) {
						// Runder til naermeste heltall og caster fra double til int
                        Vanlig nyttVanlig = new Vanlig(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]));
                        legemidler.leggTil(nyttVanlig);
					}
					else if (filen[1].equals("narkotisk")) {
						Narkotisk nyttNarkotisk = new Narkotisk(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]), Integer.parseInt(filen[4]));
						legemidler.leggTil(nyttNarkotisk);
					}
					else if (filen[1].equals("vanedannende")) {
						Vanedannende nyttVanedannende = new Vanedannende(filen[0], ((int) Math.round(Double.parseDouble(filen[2]))), Double.parseDouble(filen[3]), Integer.parseInt(filen[4]));
						legemidler.leggTil(nyttVanedannende);
					}
				}
			}
			if (type == 2){
				while (sc.hasNextLine()){
                    innlest = sc.nextLine();
                    if (innlest.contains("#")) {
                        type++;
						break;
					}
					String[] filen = innlest.trim().split(",");
					if (filen[1].equals("0")) {
						Lege nyLege = new Lege(filen[0]);
						leger.leggTil(nyLege);
					} else {
						Spesialist nySpesialist = new Spesialist(filen[0], (filen[1]));
						leger.leggTil(nySpesialist);
					}
				}
			}
			// legmiddelnr, legeNavn, pasientID,type, [reit]
			if (type == 3){
				while (sc.hasNextLine()) {
                    innlest = sc.nextLine();
                    if (innlest.contains("#")) {
                        type++;
						break;
					}
					String[] filen = innlest.trim().split(",");
                    
					for (Lege lege : leger) {
						if (lege.hentNavn().equals(filen[1])) {
							if (filen[3].equals("hvit")){
								HvitResept hvit = lege.skrivHvitResept(legemidler.hent(Integer.parseInt(filen[0])-1), pasienter.hent(Integer.parseInt(filen[2])), Integer.parseInt(filen[4]));
								resepter.leggTil(hvit);
							}
							else if (filen[3].equals("blaa")){
								BlaaResept blaa = lege.skrivBlaaResept(legemidler.hent(Integer.parseInt(filen[0])-1), pasienter.hent(Integer.parseInt(filen[2])), Integer.parseInt(filen[4]));
								resepter.leggTil(blaa);
							}
							else if	(filen[3].equals("militaer")) {
								Militaerresept militaer = lege.skrivMilResept(legemidler.hent(Integer.parseInt(filen[0])-1), pasienter.hent(Integer.parseInt(filen[2])));
								resepter.leggTil(militaer);
							}
							else if	(filen[3].equals("p")) {
								PResept p = lege.skrivPResept(legemidler.hent(Integer.parseInt(filen[0])-1), pasienter.hent(Integer.parseInt(filen[2])), Integer.parseInt(filen[4]));
								resepter.leggTil(p);
							}
						}
					}
					
				}
			}
		}
	}
	// E2
	public static void meny() throws UlovligUtskrift {
		String valg = "";
		String meny = "\n__Legesystem__"
		+"\nVELG ET ALTERNATIV:"
		+"\n1: Se fullstendig oversikt" 
		+"\n2: Legge til et nytt element" 
		+"\n3: Bruk en resept" 
		+"\n4: Se statistikk" 
		+"\n5: Skriv data til fil" 
		+"\n6: Avslutt programmet";
		Scanner sc = new Scanner(System.in);
		while (!valg.equals("6")) {
			System.out.println(meny);
			valg = sc.nextLine();
			if (valg.equals("1")) {
				seFullstendigOversikt(); 
			}
			else if (valg.equals("2")) {
				leggTilISystem(sc);
			}
			else if (valg.equals("3")) {
				brukResept(sc);
			}
			else if (valg.equals("4")) {
				seStatestikk(sc);
			}
			else if (valg.equals("5")) {
				skrivTilFil("skrevetUtData.txt");
			}
			else if (!valg.equals("6")) {
				System.out.println("\nUgyldig input!");
			}
		}
		sc.close();
		System.out.println("__Programmet er avsluttet__");
	}
	// E3
	private static void seFullstendigOversikt() {
		System.out.println("__Alle elementer i systemet__");
		System.out.println("\n__Pasienter__\n");
		String tabellPasient = "| %-4d | %-30s | %-13s |%n";

		System.out.format("+------+--------------------------------+---------------+%n");
		System.out.format("| ID   | Navn                           | Fodselsnummer |%n");
		System.out.format("+------+--------------------------------+---------------+%n");
		for (Pasient pasient : pasienter) {
			System.out.format(tabellPasient, pasient.hentID(), pasient.hentNavn(), pasient.hentFodselsnummer());
			System.out.format("+------+--------------------------------+---------------+%n");
		}
		System.out.println("\n__Legemidler__\n");
		String tabellLegemiddel = "| %-12s | %-4d | %-20s | %-6d | %-10s | %-8s |%n";

		System.out.format("+--------------+------+----------------------+--------+------------+----------+%n");
		System.out.format("| Type         | ID   | Navn                 | Pris   | Virkestoff | Styrke   |%n");
		System.out.format("+--------------+------+----------------------+--------+------------+----------+%n");
		for (Legemiddel legemiddel : legemidler) {
			double styrke = 0;
			String str = "ingen";
			if (legemiddel instanceof Vanedannende) {
				Vanedannende vanedannedne = (Vanedannende)legemiddel;
				styrke = vanedannedne.hentVanedannendeStyrke();
			}
			else if (legemiddel instanceof Narkotisk) {
				Narkotisk narkotisk = (Narkotisk)legemiddel;
				styrke = narkotisk.hentNarkotiskStyrke();
			}
			if (styrke != 0) {
				str = styrke + "";
			}
			System.out.format(tabellLegemiddel, legemiddel.hentType() , legemiddel.hentId(), legemiddel.hentNavn(), legemiddel.hentPris(), legemiddel.hentVirkestoff(), str);
			System.out.format("+--------------+------+----------------------+--------+------------+----------+%n");
		}
		System.out.println("\n__Leger__\n");
		String tabellLege = "| %-10s | %-30s |%n";

		System.out.format("+------------+--------------------------------+%n");
		System.out.format("| Type       | Navn                           |%n");
		System.out.format("+------------+--------------------------------+%n");
		for (Lege lege : leger) {
			System.out.format(tabellLege, lege.hentType().toLowerCase(), lege.hentNavn());
			System.out.format("+------------+--------------------------------+%n");
		}
		System.out.println("\n__Resepter__\n");
		String tabellResept = "| %-4d | %-20s | %-6d | %-5s | %-5s |%n";

		System.out.format("+------+----------------------+--------+-------+-------+%n");
		System.out.format("| ID   | Legemiddel           | Pris   | Reit  | Farge |%n");
		System.out.format("+------+----------------------+--------+-------+-------+%n");
		for (Resept resept : resepter) {
			String farge = "";
			int pris = 0;
			if (resept instanceof BlaaResept) {
				BlaaResept blaa = (BlaaResept)resept;
				pris = blaa.prisAaBetale();
				farge = "blaa";
			}
			else if (resept instanceof HvitResept) {
				HvitResept hvit = (HvitResept)resept;
				pris = hvit.prisAaBetale();
				farge = "hvit";
			}
			else if (resept instanceof Militaerresept) {
				Militaerresept mil = (Militaerresept)resept;
				pris = mil.prisAaBetale();
				farge = "hvit";
			}
			else {
				PResept pr = (PResept)resept;
				pris = pr.prisAaBetale();
				farge = "hvit";
			}
			System.out.format(tabellResept, resept.hentId(), resept.hentLegemiddel().hentNavn(), pris, resept.hentReit(), farge);
			System.out.format("+------+----------------------+--------+-------+-------+%n");
		}
	}
	// E4
	// legge til nye elementer i Legesystemet
	public static void leggTilISystem(Scanner sc) throws UlovligUtskrift {
		String valg = "";
		String elementer = "\n__Elementer__"
		+"\nHvilket element onsker du aa legge til?"
		+"\n1: Pasient" 
		+"\n2: Legemiddel" 
		+"\n3: Lege" 
		+"\n4: Resept" 
		+"\n5: Tilbake til menyen!"
		+"\nSkriv inn et av alternativene:";
		
		while (!valg.equals("5")) {
			System.out.println(elementer);

			valg = sc.nextLine();
			if (valg.equals("1")) {
				leggTilPasient(sc); 
			}
			else if (valg.equals("2")) {
				leggTilLegemiddel(sc);
			}
			else if (valg.equals("3")) {
				leggTilLege(sc);
			}
			else if (valg.equals("4")) {
				leggTilResept(sc);
			}
			else if (!valg.equals("5")) {
				System.out.println("\nUgyldig input!");
			}
		}
		System.out.println("\n__Tilbake til Hovedmeny__");
	}
	public static void leggTilPasient(Scanner sc) {
		String input;
		String navn;
		String fnr;
		Pasient nyPasient;
		System.out.println("__Legg til pasient__");

		System.out.println("Navn: ");
        input = sc.nextLine();
		// ser om bruker legger inn noe eller legger inn mellomrom forst. gir nytt forsok
		if (input != "" && input.charAt(0) != ' ' ) {
			navn = input;
			System.out.println("Fodselsnummer: ");
			input = sc.nextLine();
			if (input != "" && input.charAt(0) != ' ') {
				fnr = input;
				nyPasient = new Pasient(navn, fnr);
				pasienter.leggTil(nyPasient);
				System.out.println(nyPasient.hentNavn() +" er lagt til i systemet!");
			}
		}
		System.out.println("\n__Tilbake__");
	}
    public static void leggTilLegemiddel(Scanner sc) {
		String navn;
		int pris;
		double virkestoff;
        int styrke;
		// meny for aa velge type legemiddel:

		String valg = "";
		String typerLegemiddel = "\n__Legg til legemiddel__"
		+"\nHvilken type har legemiddelet?"
		+"\n1: Narkotisk"
		+"\n2: Vanedannende"
		+"\n3: Vanlig"
		+"\n4: Tilbake"
		+"\nSkriv inn et av alternativene:";
		
		while (!valg.equals("4")) {
			System.out.println(typerLegemiddel);

			valg = sc.nextLine();
			// narkotisk
			if (valg.equals("1")) {
				System.out.println("__Legg til Narkotisk__");
				System.out.println("Navn: ");
				navn = sc.nextLine();
				System.out.println("Pris:");
				pris = ((int) Math.round(sc.nextDouble()));
				System.out.println("Virkestoff: ");
				virkestoff = 0;
				try {
					virkestoff = sc.nextDouble();
					// hvis formatet paa Stringen eller inputen konverteres feil
					// catcher flere exceptions med |
				} catch (NumberFormatException | InputMismatchException e) {
					System.out.println("Ikke et gyldig tall!");
				}
				System.out.println("Styrke: ");
				styrke = sc.nextInt();

				Narkotisk nyttNarkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
				legemidler.leggTil(nyttNarkotisk);
				System.out.println(nyttNarkotisk.hentNavn()+ " er lagt til i systemet!");
			}
			// vanedannende
			else if (valg.equals("2")) {
				System.out.println("Navn: ");
				navn = sc.nextLine();
				System.out.println("Pris");
				pris = ((int) Math.round(sc.nextDouble()));
				System.out.println("Virkestoff: ");
				virkestoff = 0;
				try {
					virkestoff = sc.nextDouble();
				} catch (NumberFormatException | InputMismatchException e) {
					System.out.println("Ikke et gyldig tall!");
				}
				System.out.println("Styrke: ");
				styrke = sc.nextInt();

				Vanedannende nyttVanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
				legemidler.leggTil(nyttVanedannende);
				return;
			}
			// vanlig
			else if (valg.equals("3")) {
				System.out.println("Navn: ");
				navn = sc.nextLine();
				System.out.println("Pris");
				pris = ((int) Math.round(sc.nextDouble()));
				System.out.println("Virkestoff: ");
				virkestoff = 0;
				try {
					virkestoff = sc.nextDouble();
				} catch (NumberFormatException | InputMismatchException e) {
					System.out.println("Ikke et gyldig tall!");
				}

				Vanlig nyVanlig = new Vanlig(navn, pris, virkestoff);
				legemidler.leggTil(nyVanlig);
				return;
			}
			System.out.println("\nUgylidg input!");
			}
		System.out.println("\n__Tilbake__");
		return;
	}
	public static void leggTilLege(Scanner sc) {
		String navn;
		String kontrollId;

		String valg = "";
		String typeLege = "\nHvilken type lege skal legges inn?"
		+"\n1: Spesialist"
		+"\n2: Vanlig"
		+"\n3: Tilbake"
		+"\nSkriv inn et av alternativene:";

		while (!valg.equals("3")) {
			System.out.println(typeLege);
			valg = sc.nextLine();

			if (valg.equals("1")) {
				System.out.println("Navn: ");
				navn = sc.nextLine();
				System.out.println("KontrollID: ");
				kontrollId = sc.nextLine();
				if (kontrollId.equals("")) {
					System.out.println("Du maa skrive en ID");
					return;
				}
				else {
					Spesialist nySpesialist = new Spesialist(navn, kontrollId);
					leger.leggTil(nySpesialist);
				}
			}
			else if (valg.equals("2")) {
				System.out.println("Navn: ");
				navn = sc.nextLine();
				Lege nyLege = new Lege(navn);
				leger.leggTil(nyLege);
			}
			System.out.println("\nUgyldig input!");
		}
		System.out.println("\n__Tilbake__"); 
		return;
	}
	public static void leggTilResept(Scanner sc) throws UlovligUtskrift {
		int legeId = 0;
		int legemiddelId = 0;
		int pasientId;
		int reit;
		int spesialistId = 0;
		int teller = 0;
		String valg;
		Legemiddel legemiddelet = null;
		Lege utskriftslege = null;
		Pasient pasienten = null;

		// legemiddel
		System.out.println("\nVelg et legemiddel:\n");
		for (Legemiddel legemiddel : legemidler) {
			System.out.println(legemiddel.hentId() + ": " + legemiddel.hentNavn() + "(" +legemiddel.hentType() + ")");
		}
		while (legemiddelId < 1 || legemiddelId > legemidler.stoerrelse()) {
			System.out.println("Velg et legemiddel");
			legemiddelId = sc.nextInt();
		}
		legemiddelet = legemidler.hent(legemiddelId - 1);
		System.out.println(legemiddelet.hentNavn());

		//lege
		if (legemiddelet instanceof Narkotisk) {
			System.out.println("\nDu valgte narkotisk, saa velg en spesialist:\n");
			for (Lege lege : leger) {
				teller ++;
				if (lege instanceof Spesialist) {
					//Spesialist spesialist = (Spesialist)lege;
					System.out.println(teller + ": " + lege.hentNavn());
				}
			}
			while (spesialistId < 1 || spesialistId > leger.stoerrelse() || !(leger.hent(spesialistId - 1) instanceof Spesialist)) {
				spesialistId = sc.nextInt();
				System.out.println("Skriv inn IDen til en av spesialistene over");
			}
			utskriftslege = leger.hent(spesialistId - 1);
			System.out.println(utskriftslege.hentNavn());
		}	
		else {
			System.out.println("\nVelg en lege:\n");
			teller = 0;
			for(Lege lege : leger){
				teller++;
				System.out.println(teller + ": " + lege.hentNavn() + "(" + lege.hentType() + ")");
			}
			while (legeId < 1 || legeId > leger.stoerrelse()) {
				if (legemiddelet.hentType().equals("Narkotisk")) {
					System.out.println("Velg en av legene (du valgte narkotisk, saa velg en spesialist): ");
				}
				else {
					System.out.println("Velg en av legene: ");
				}
				legeId = sc.nextInt();
			}
			utskriftslege = leger.hent(legeId - 1);
			System.out.println(utskriftslege.hentNavn());
		}
		
		// pasient
		System.out.println("\nVelg en pasient:\n");
		for (Pasient pasient : pasienter) {
			System.out.println(pasient.hentID() + ": " + pasient.hentNavn());
		}
		pasientId = sc.nextInt();
		while (pasientId < 1 || pasientId > pasienter.stoerrelse()) {
			System.out.println("Velg en pasient");
			pasientId = sc.nextInt();
		}
		pasienten = pasienter.hent(pasientId - 1);
		System.out.println(pasienten.hentNavn());
		
		//Resept type
		if (legemiddelet instanceof Narkotisk) {
			System.out.println("\nDu valgte narkotisk, saa da ble resepten blaa.\n");
			System.out.println("Velg et antall for reit:");
				reit = sc.nextInt();
				BlaaResept blaa = utskriftslege.skrivBlaaResept(legemiddelet, pasienten, reit);
				resepter.leggTil(blaa);
		}
		else{
			System.out.println("\nVelg en respt:\n");
			String typerResept = "\nHvilken type har resept?"
			+"\n1: blaa"
			+"\n2: hvit"
			+"\n3: militaer"
			+"\n4: p-resept";
			System.out.println(typerResept);
			valg = sc.nextLine();
			while (!(valg.equals("1") || valg.equals("2") || valg.equals("3") || valg.equals("4"))){
				if (legemiddelet.hentType().equals("Narkotisk")) {
					System.out.println("Du har valgt narkotisk, saa velg blaa resept:");
				}
				else {
					System.out.println("Velg en resept:");
				}
				valg = sc.nextLine();
			}
			if (valg.equals("1")) {
				System.out.println("\nVelg et antall for reit:");
				reit = sc.nextInt();
				BlaaResept blaa = utskriftslege.skrivBlaaResept(legemiddelet, pasienten, reit);
				resepter.leggTil(blaa);
			}
			else if (valg.equals("2")) {
				System.out.println("\nVelg et antall for reit:");
				reit = sc.nextInt();
				HvitResept hvit = utskriftslege.skrivHvitResept(legemiddelet, pasienten, reit);
				resepter.leggTil(hvit);
			}
			else if (valg.equals("3")) {
				Militaerresept militaer = utskriftslege.skrivMilResept(legemiddelet, pasienten);
				resepter.leggTil(militaer);
			}
			else {
				System.out.println("\nvelg et antall for reit:");
				reit = sc.nextInt();
				PResept p = utskriftslege.skrivPResept(legemiddelet, pasienten, reit);
				resepter.leggTil(p);
			}
		}	
	}
	// E5
	public static void brukResept(Scanner sc) {
		// velg pasient
		int pasientId = 0;
		int reseptId = 0;
		String listePasienter = "\nHvilken pasient vil du se resepter for?";
		
		System.out.println("__Bruk en resept__");
		for (Pasient pasient : pasienter) {
			listePasienter = listePasienter + "\n" + pasient.hentID() + ": " 
			+ pasient.hentNavn() + " (fnr: " + pasient.hentFodselsnummer() + ")";
		}
		System.out.println(listePasienter);
		while (pasientId < 1 || pasientId > pasienter.stoerrelse()) {
			try {
				System.out.println("Velg en av pasientene:");
				pasientId = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nAksepterer ikke bokstaver!");
				// Tommer scanner for feil input
				sc.next();
				continue;
			}
		}

		Pasient pasienten = pasienter.hent(pasientId - 1);
		Resept resepten = null;
		if (pasienten.hentResepter().stoerrelse() == 0) {
			System.out.println("\nDenne pasienten har ingen resepter");
			return;
		}

		System.out.println("\nValgt pasient: " + pasienten.hentNavn() + " (fnr: " 
		+ pasienten.hentFodselsnummer() + ")");
		for (int i = 0; i < pasienten.hentResepter().stoerrelse(); i++) {
			resepten = pasienten.hentResepter().hent(i);
			System.out.println((i + 1) + ": " + resepten.hentLegemiddel().hentNavn() 
			+ " (" + resepten.hentReit() + " reit)");
		}
		while (reseptId < 1 || reseptId > pasienten.hentResepter().stoerrelse()) {
			System.out.println("Hvilken resept vil du bruke?");
			reseptId = sc.nextInt();
		}
		resepten = pasienten.hentResepter().hent(reseptId - 1);
		if (resepten.bruk() == true) {
			System.out.println("Brukte resept paa " + resepten.hentLegemiddel().hentNavn() 
			+ ". Antall gjenvaerende reit: " + resepten.hentReit());
		}
		else {
			System.out.println("Kunne ikke bruke resept paa " + resepten.hentLegemiddel().hentNavn() 
			+ " (ingen gjennvaerende reit).");
		}
	}
	// E6
	public static void seStatestikk(Scanner sc) {
		String valg = "";

        while (!valg.equals("5")) {
        System.out.println("\nHvilken statistikk vil du se?\n" +
        "1: Totalt antall resepter paa vanedannende legemidler\n" +
        "2: Totalt antall resepter paa narkotiske legemidler\n" +
        "3: Navn paa alle leger som har skrevet ut minst en resept paa narkotiske legemidler\n" +
        "4: Navn paa alle pasienter som har minst en gyldig resept paa narkotiske legemidler\n" +
        "5: Tilbake");

            valg = sc.next();

			// totalt vanedannende
            if (valg.equals("1")) {
                int teller = 0;
                for (Resept resept : resepter) {
                    if (resept.hentLegemiddel() instanceof Vanedannende) {
                        teller ++;
                    }
                }
                System.out.println("\nTotalt antall resepter paa vanedannende legemidler: " + teller);

			// totalt narkotiske
            } else if (valg.equals("2")) {
                int teller = 0;
                for (Resept resept : resepter) {
                    if (resept.hentLegemiddel() instanceof Narkotisk) {
                        teller ++;
                    }
                }
                System.out.println("\nTotalt antall resepter paa narkotiske legemidler: " + teller);

            }
			// leger som har skrevet ut minst en resept paa narkotisk
			else if (valg.equals("3")) {
                System.out.println("\nFoelgende leger har skrevet ut minst en resept paa narkotiske legemidler:\n");
				
				String legerNarkotisk = "| %-30s | %-6d |%n";
				System.out.format("+--------------------------------+--------+%n");
				System.out.format("| Navn                           | Antall |%n");
				System.out.format("+--------------------------------+--------+%n");
                for (Lege lege : leger) {
                    int teller = 0;
                    if (!(lege instanceof Spesialist)) {
                        continue;
                    }
                    for (Resept resept : lege.hentResepter()) {
                        if (resept.hentLegemiddel() instanceof Narkotisk) {
                            teller ++;
                        }
                    }
                    if (teller == 0) {
                        continue;
                    }
					System.out.format(legerNarkotisk, lege.hentNavn(), teller);
					System.out.format("+--------------------------------+--------+%n");
				}
            }
			// pasienter som har minst en gyldig resept paa narkotisk
			else if (valg.equals("4")) {
                System.out.println("\nFoelgende pasienter har minst en gyldig resept paa narkotiske legemidler:\n");

				String pasienterNarkotisk = "| %-30s | %-6d |%n";
				System.out.format("+--------------------------------+--------+%n");
				System.out.format("| Navn                           | Antall |%n");
				System.out.format("+--------------------------------+--------+%n");
                for (Pasient pasient : pasienter) {
                    int teller = 0;
                    for (Resept resept : pasient.hentResepter()) {
                        if (resept.hentLegemiddel() instanceof Narkotisk) {
                            teller ++;
                        }
                    }
                    if (teller == 0) {
                        continue;
                    }
					System.out.format(pasienterNarkotisk, pasient.hentNavn(), teller);
					System.out.format("+--------------------------------+--------+%n");
                }
            }
			else if (!valg.equals("5")) {
				System.out.println("\nUgyldig input!");
			}
        }
    }
	public static void skrivTilFil(String filnavn) {
		// sTom er en tom streng som bygges opp av hvilket legemiddel som skal legges til
		PrintWriter f = null;
		String sTom = "";
		try {
			f = new PrintWriter(filnavn);
		} catch (Exception e) {
			System.out.println("Kunne ikke skrive " + filnavn);
			// exit(1) indikerer en gal avslutning
			System.exit(1);
		}

		f.println("# Pasienter (navn, fnr)");
		for (Pasient pasient : pasienter) {
			sTom = pasient.hentNavn() + "," + pasient.hentFodselsnummer();
            f.println(sTom);
		}

		f.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
		for (Legemiddel legemiddel : legemidler) {
			if (legemiddel instanceof Narkotisk) {
				// caster legemiddel til narkotisk
				Narkotisk narkotisk = (Narkotisk)legemiddel;
				// bytter ut den tomme strengen
				sTom = legemiddel.hentNavn() + "," + narkotisk.hentType().toLowerCase() + "," + narkotisk.hentPris() + "," + narkotisk.hentVirkestoff() + "," + narkotisk.hentNarkotiskStyrke();
			}
			else if (legemiddel instanceof Vanedannende) {
				Vanedannende vanedannende = (Vanedannende)legemiddel;
				sTom = legemiddel.hentNavn() + "," + vanedannende.hentType().toLowerCase() + "," + vanedannende.hentPris() + "," + vanedannende.hentVirkestoff() + "," + vanedannende.hentVanedannendeStyrke();
			}
			else if (legemiddel instanceof Vanlig) {
				Vanlig vanlig = (Vanlig)legemiddel;
				sTom = legemiddel.hentNavn() + "," + vanlig.hentType().toLowerCase() + "," + vanlig.hentPris() + "," + vanlig.hentVirkestoff();
			}
			f.println(sTom);
		}

		f.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
		for (Lege lege : leger) {
			sTom = lege.hentNavn();
			if (lege instanceof Spesialist) {
				// caster lege til spesialist
				// legger til den tomme strenger
				Spesialist spesialist = (Spesialist)lege;
				sTom += "," + spesialist.hentKontrollId();
			} else {
				// gir vanlig Lege
				sTom += ",0";
			}
			f.println(sTom);
		}
		f.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
		for (Resept resept : resepter) {
			// PasientID starter paa 1, saa maa trekke fra 1 paa hentID()
			sTom = resept.hentLegemiddel().hentId() + "," + resept.hentLege().hentNavn() + "," + (resept.hentPasient().hentID()-1);
			if (resept instanceof BlaaResept) {
				sTom += ",blaa," + resept.hentReit();
			}
			else if (resept instanceof Militaerresept) {
				sTom += ",militaer,";
			}
			else if (resept instanceof PResept) {
				sTom += ",p," + resept.hentReit();
			}
			else if (resept instanceof HvitResept) {
				sTom += ",hvit," + resept.hentReit();
			}
			f.println(sTom);
		}
		f.close();
		System.out.println("\nSkrevet til " + filnavn);
	}

    public static void main(String[] args) throws UlovligUtskrift {
        lesFraFil("LegeData.txt");
        meny();
	}
}