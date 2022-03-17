import java.util.Arrays;
// StackOverflow spørsmål som er om problemet
// https://stackoverflow.com/questions/65377725/why-i-am-getting-error-this-variable-is-null-in-java-class

public class testLege {
    public static void main(String[] args) throws UlovligUtskrift {
        // legemidler
        Vanlig vanlig = new Vanlig("Vanlig", 3210, 100);
        Narkotisk narkotisk = new Narkotisk("Narkotisk", 20, 200, 2);
        Vanedannende vanedannende = new Vanedannende("Vanedannende", 30, 300, 3);
        // leger
        Lege lege = new Lege("Kari Nordmann");
        Lege lege2 = new Lege("Hans Ryen");
        Spesialist spesialist = new Spesialist("Ola Nordmann", "ID123");

        // pasienter
        Pasient pasient = new Pasient("Karl", "12345612345");
        //Pasient pasient2 = new Pasient("Karl2", "12345612345");


        //tester comparable
        System.out.println("tester comparable verdier:");
        System.out.println(lege.compareTo(spesialist));
        System.out.println(spesialist.compareTo(lege));
        System.out.println(lege.compareTo(lege2));
        System.out.println("\n");
        Lege[] leger = {spesialist, lege, lege2};
        System.out.println(leger[0]);
        System.out.println(leger[1]);
        System.out.println(leger[2]);
        System.out.println("\nSortert liste:");
        Arrays.sort(leger);
        System.out.println(leger[0]);
        System.out.println(leger[1]);
        System.out.println(leger[2]);
        //tester skrivResept
        System.out.println("\ntester skrivResept:");
        spesialist.skrivHvitResept(vanlig, pasient, 3);
        spesialist.skrivHvitResept(vanedannende, pasient, 3);
        spesialist.skrivBlaaResept(narkotisk, pasient, 3);
        lege.skrivBlaaResept(vanedannende, pasient, 3);
        lege.skrivMilResept(vanedannende, pasient);
        lege2.skrivBlaaResept(vanlig, pasient, 3);
        lege2.skrivMilResept(vanedannende, pasient);
        System.out.println("\nHenter pasient og resepter");
        System.out.println(pasient);
        System.out.println("\nhenter lege og resepter");
        System.out.println(lege);
        System.out.println(lege.hentResepter());
        // gir feil med throw UlovligUtskrift
        // lege.skrivBlaaResept(narkotisk, pasient, 3);
    }
}