public class Militaerresept extends HvitResept{
    public Militaerresept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient, 3);
    }
    @Override
    public double prisAaBetale() {
        return 0;
    }
}