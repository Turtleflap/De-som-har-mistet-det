public class Narkotisk extends Legemiddel{
    private int styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
    public int hentNarkotiskStyrke() {
        return styrke;
    }
    @Override
    public String toString(){
        return "Narkotisk legemiddel: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff + "mg, ID: " + ID + ", narkotisk styrke: " + styrke;
    }
}