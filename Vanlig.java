public class Vanlig extends Legemiddel{
    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }
    @Override
    public String toString(){
    return super.hentNavn() + ", Pris: " + super.hentPris() + ", Virkestoff: " + super.hentVirkestoff() + "mg, ID: " + super.hentId();
    }
}