public class HvitResept extends Resept{
    private String farge = "hvit";

    public HvitResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit){
        super(legemiddel, lege, pasient, reit);
    }
    @Override
    public String farge() {
        return farge;
    }
    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }
    @Override
    public String toString() {
        return super.toString() + ", Farge: " + farge() + "\n";
    }
}