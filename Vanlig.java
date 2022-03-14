public class Vanlig extends Legemiddel{
    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }
    @Override
    public String toString(){
    return "Vanlig legemiddel: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff + "mg, id: " + ID;
    }
}