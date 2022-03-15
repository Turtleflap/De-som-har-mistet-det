public class BlaaResept extends Resept{
    private String farge = "blaa";

    public BlaaResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }
    @Override
    public String farge() {
        return farge;
    }
    @Override
    public int prisAaBetale() {
        return (int) (legemiddel.hentPris()*0.25);
    }
    @Override
    public String toString() {
        return super.toString() + ", farge: " + farge + "\n";
    }
}