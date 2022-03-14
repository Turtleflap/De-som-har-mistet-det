public class Vanedannende extends Legemiddel{
    private int styrke;

    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
    public int hentVanedannendeStyrke() {
        return styrke;
    }
    @Override
    public String toString(){
        return "Vanndannende legemiddel: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff + "mg, id: " + ID + ", vanedannende styrke: " + styrke;
    }
}
