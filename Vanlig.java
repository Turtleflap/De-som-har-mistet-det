public class Vanlig extends Legemiddel{
    public Vanlig(String navn, double d, double virkestoff) {
        super(navn, d, virkestoff);
    }
    @Override
    public String toString(){
    return super.hentNavn() + ", Pris: " + super.hentPris() + ", Virkestoff: " + super.hentVirkestoff() + "mg, ID: " + super.hentId();
    }
}