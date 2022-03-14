public class HvitResept extends Resept{
    private String farge = "hvit";

    public HvitResept(Legemiddel legemiddel, Lege lege, int pasientId, int reit){
        super(legemiddel, lege, pasientId, reit);
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
        return super.toString() + ", farge: " + farge;
    }
}
