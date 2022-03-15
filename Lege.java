public class Lege implements Comparable<Lege> {
    protected String navn;
    IndeksertListe<Resept> utskrevneResepter;

    public Lege(String navn){
        this.navn = navn;
    }
    public String hentNavn() {
        return navn;
    }

    public IndeksertListe<Resept> hentResept() {
        return utskrevneResepter;
    }

    // 4 metoder som oppretter alle resept typene

    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        // this er objektet av Lege
        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        // legger til utskrevne og pasient resepter
        // Trenger å caste til Resept for leggTil() metoden
        System.out.println(hvitResept);
        utskrevneResepter.leggTil((Resept) hvitResept);
        pasient.leggTilResept(hvitResept);
        // returnerer
        return hvitResept;
    }

    public Militaerresept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        Militaerresept militaerresept = new Militaerresept(legemiddel, this, pasient);
        utskrevneResepter.leggTil((Resept) militaerresept);
        pasient.leggTilResept(militaerresept);
        return militaerresept;
    }

    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        PResept pResept = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil((Resept) pResept);
        pasient.leggTilResept(pResept);
        return pResept;
    }

    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil((Resept) blaaResept);
        pasient.leggTilResept(blaaResept);
        return blaaResept;
    }

    @Override
    public String toString() {
        return "Navn: " + navn;
    }
    @Override
    // returner en int
    // Bruker mindre enn (A < B) for å få det alfabetisk
    // https://www.codegrepper.com/code-examples/java/how+to+check+if+one+string+is+alphabetically+before+another+in+java
    public int compareTo(Lege o) {
        return this.navn.compareTo(o.navn);
    }
}