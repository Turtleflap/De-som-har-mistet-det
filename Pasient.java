

public class Pasient {
    private String navn;
    private String fodselsnummer;
    private int ID;
    protected Lenkeliste<Resept> resepter;
    // Må være static fordi det er felles for alle objekter
    static int ID_teller = 1;

    public Pasient(String navn, String fodselsnummer) {
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        // oppretter Lenkeliste indeks
        resepter = new Lenkeliste<>();
        // setter ID lik ID_teller og øker med 1
        ID = ID_teller;
        ID_teller++;
    }

    public void leggTilResept(Resept resept) {
        resepter.leggTil(resept);
    }

    public Lenkeliste<Resept> hentResepter() {
        return resepter;
    }

    public String hentNavn() {
        return navn;
    }
    
    public String hentFodselsnummer() {
        return fodselsnummer;
    }

    public int hentID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Pasient: " + hentNavn() + ". Fodselsnummer: " + 
        hentFodselsnummer() + "\nID: " + hentID() + ", Resepter: " + hentResepter();
    }
}