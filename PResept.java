public class PResept extends HvitResept{
    public PResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }
    @Override
    public int prisAaBetale() {
        if (legemiddel.hentPris() <= 108) {
            return 0;
        }
        return legemiddel.hentPris() - 108;
    }
}