public abstract class Legemiddel {
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected int ID;
    static int oekning = 1;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        ID = oekning;
        oekning ++;
    }
    public int hentId() {
        return ID;
    }
    public String hentNavn() {
        return navn;
    }
    public int hentPris() {
        return pris;
    }
    public double hentVirkestoff() {
        return virkestoff;
    }
    public void nyPris(int nyPris) {
        pris = nyPris;
    }
    abstract public String hentType();

    public String toString(){
        return navn + ", Pris: " + pris + ", Virkestoff: " + virkestoff + "mg, ID: " + ID;
    }
}