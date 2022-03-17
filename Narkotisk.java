public class Narkotisk extends Legemiddel{
    private int styrke;

    public Narkotisk(String navn, double d, double virkestoff, int styrke){
        super(navn, d, virkestoff);
        this.styrke = styrke;
    }
    public int hentNarkotiskStyrke() {
        return styrke;
    }
    @Override
    public String toString(){
        return super.hentNavn() + ", Pris: " + super.hentPris() + ", Virkestoff: " + super.hentVirkestoff() + "mg, ID: " + super.hentId() + ", Narkotisk styrke: " + styrke;
    }
}