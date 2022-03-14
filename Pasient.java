public class Pasient {
    private String navn;
    private String fodselsnummer;
    private int ID;
    protected Koe<Resept> resepter = new Koe<>();
    // Må være static fordi det er felles for alle objekter
    static int ID_teller = 1;

    public Pasient(String navn, String fodselsnummer) {
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        // setter ID lik ID_teller og øker med 1
        ID = ID_teller;
        ID_teller++;
    }

    public void leggTilResept(Respet resept) {
        // legg til metode her fra resept klassen
    }

    public hentResepter() {
        // legg til Resepter her
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
        return "Pasient med navn: " + navn + ". Fodselsnummer: " + 
        fodselsnummer + ". ID: " + ID + ". Resepter: " + resepter;
    }
}
