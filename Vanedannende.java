public class Vanedannende extends Legemiddel{
    private int styrke;

    public Vanedannende(String navn, double d, double virkestoff, int styrke){
        super(navn, d, virkestoff);
        this.styrke = styrke;
    }
    public int hentVanedannendeStyrke() {
        return styrke;
    }
    @Override
    public String toString(){
        return super.hentNavn() + ", Pris: " + super.hentPris() + ", Virkestoff: " + super.hentVirkestoff() + "mg, ID: " + super.hentId() + ", Vanedannende styrke: " + styrke;
    }
}