public class Vanlig extends Legemiddel{
    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }
    public String hentType(){
        return "Vanlig";
    }
    @Override
    public String toString(){
        return super.hentNavn() + ", Vanlig" + ", Pris: " + super.hentPris() + ", Virkestoff: " + super.hentVirkestoff() + "mg, ID: " + super.hentId();
    }
} 