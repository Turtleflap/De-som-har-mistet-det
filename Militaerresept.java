public class Militaerresept extends HvitResept{
    public Militaerresept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient, 3);
    }
    @Override
    public int prisAaBetale() {
        return 0;
    }
}