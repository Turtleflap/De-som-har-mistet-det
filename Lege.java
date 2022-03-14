public class Lege implements Comparable<Lege> {
    protected String navn;
    IndeksertListe<Resept> utskrevneResepter;

    public Lege(String navn){
        this.navn = navn;
    }
    public String hentNavn() {
        return navn;
    }

    public Lenkeliste<Resept> hentResept() {
        return utskrevneResepter;
    }

    @Override
    public String toString() {
        return "Navn: " + navn;
    }
    @Override
    // returner en int
    // Bruker mindre enn (A < B) for å få det alfabetisk
    // https://www.codegrepper.com/code-examples/java/how+to+check+if+one+string+is+alphabetically+before+another+in+java
    public int compareTo(Lege o) {
        return this.navn.compareTo(o.navn);
    }
}