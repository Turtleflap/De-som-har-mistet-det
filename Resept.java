public abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    private int reit;
    private int ID;
    static int oekning = 1;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        ID = oekning;
        oekning++;
    }
    public int hentId() {
        return ID;
    }
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }
    public Lege hentLege() {
        return utskrivendeLege;
    }
    public Pasient hentPasient() {
        return pasient;
    }
    public int hentReit() {
        return reit;
    }
    public boolean bruk() {
        if (reit > 0) {
            reit --;
            return true;
        }
        return false;
    }
    abstract public String farge();
    abstract public double prisAaBetale();
    @Override
    public String toString() {
        return "ID: " + hentId() + ", Legemiddel: " + hentLegemiddel() + ", Reit: " + hentReit();
    }
}