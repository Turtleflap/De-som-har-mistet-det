public class PResept extends HvitResept{
    public PResept(Legemiddel legemiddel, Lege lege, int pasientId, int reit) {
        super(legemiddel, lege, pasientId, reit);
    }
    @Override
    public int prisAaBetale() {
        if (legemiddel.hentPris() <= 108) {
            return 0;
        }
        return legemiddel.hentPris() - 108;
    }
}