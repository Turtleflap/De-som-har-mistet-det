public abstract class Legemiddel {
    protected String navn;
    protected double pris;
    protected double virkestoff;
    protected int ID;
    static int oekning = 1;

    public Legemiddel(String navn, double pris, double virkestoff) {
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
    public double hentPris() {
        return pris;
    }
    public double hentVirkestoff() {
        return virkestoff;
    }
    public void nyPris(int nyPris) {
        pris = nyPris;
    }

    public String toString(){
        return "Legemiddel: " + hentNavn() + ", Pris: " + hentPris() + ", Virkestoff: " + hentVirkestoff() + "mg, ID: " + hentId();
    }
}