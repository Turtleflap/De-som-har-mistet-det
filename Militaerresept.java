public class Militaerresept extends HvitResept{
    public Militaerresept(Legemiddel legemiddel, Lege lege, int pasientId) {
        super(legemiddel, lege, pasientId, 3);
    }
    @Override
    public int prisAaBetale() {
        return 0;
    }

}
