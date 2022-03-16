public class Lege implements Comparable<Lege> {
    protected String navn;
    protected IndeksertListe<Resept> utskrevneResepter;

    public Lege(String navn){
        this.navn = navn;
        // oppretter IndekstertListe i konstruktøren
        utskrevneResepter = new IndeksertListe<>();
    }
    public String hentNavn() {
        return navn;
    }

    public IndeksertListe<Resept> hentResepter() {
        return utskrevneResepter;
    }

    // 4 metoder som oppretter alle resept typene

    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        // hvis legemiddel er et objekt av Narkotisk
        if (legemiddel instanceof Narkotisk) {
            // this er objektet av Lege
                throw new UlovligUtskrift(this, legemiddel);
            }
            HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
            // legger til utskrevneResepter
            utskrevneResepter.leggTil(hvitResept);
            pasient.leggTilResept(hvitResept);
            // returnerer
            return hvitResept;
    }

    public Militaerresept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
                throw new UlovligUtskrift(this, legemiddel);
            }
        Militaerresept militaerresept = new Militaerresept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(militaerresept);
        pasient.leggTilResept(militaerresept);
        return militaerresept;
    }

    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        PResept pResept = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(pResept);
        pasient.leggTilResept(pResept);
        return pResept;
    }

    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            // hvis ikke et objekt av Spesialist
            throw new UlovligUtskrift(this, legemiddel);
        }
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(blaaResept);
        pasient.leggTilResept(blaaResept);
        return blaaResept;
    }

    @Override
    public String toString() {
        return "Lege: " + navn + "\n" + utskrevneResepter;
    }
    @Override
    // returner en int
    // Bruker mindre enn (A < B) for å få det alfabetisk
    // https://www.codegrepper.com/code-examples/java/how+to+check+if+one+string+is+alphabetically+before+another+in+java
    public int compareTo(Lege lege) {
        // sammenligner dette lege objektet sitt navn med et annet
        return this.navn.compareTo(lege.navn);
    }
}