public abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    private int pasientId;
    private int reit;
    private int ID;
    static int oekning = 1;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
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
    public int hentPasientId() {
        return pasientId;
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
    abstract public int prisAaBetale();
    @Override
    public String toString() {
        return "Id: " + ID + ", legemiddel: " + legemiddel + ", lege: " + utskrivendeLege + ", passientId: " + pasientId + ", reit: " + reit;
    }
}