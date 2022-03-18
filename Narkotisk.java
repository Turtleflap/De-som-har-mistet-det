public class Narkotisk extends Legemiddel{
    private int styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
    public int hentNarkotiskStyrke() {
        return styrke;
    }
    public String hentType(){
        return "Narkotisk";
    }
    @Override
    public String toString(){
        return super.hentNavn() + ", Pris: " + super.hentPris() + ", Virkestoff: " + super.hentVirkestoff() + "mg, ID: " + super.hentId() + ", Narkotisk styrke: " + styrke;
    }
}