import java.util.Arrays;
// StackOverflow spørsmål som er om problemet
// https://stackoverflow.com/questions/65377725/why-i-am-getting-error-this-variable-is-null-in-java-class

public class testLege {
    public static void main(String[] args) throws UlovligUtskrift {
        // legemidler
        Vanlig vanlig = new Vanlig("Navn1", 3210, 100);
        Narkotisk narkotisk = new Narkotisk("navn2", 20, 200, 2);
        Vanedannende vanedannende = new Vanedannende("navn3", 30, 300, 3);
        // leger
        Lege lege = new Lege("Lege");
        Spesialist spesialist = new Spesialist("Spesialist", "id123");

        // pasient
        Pasient pasient = new Pasient("Karl", "12345612345");

        //tester comparable
        System.out.println("tester comparable");
        System.out.println(lege.compareTo(spesialist));
        System.out.println(spesialist.compareTo(lege));
        System.out.println(lege.compareTo(lege));
        Lege[] leger = {spesialist, lege};
        System.out.println(leger[0]);
        System.out.println(leger[1]);
        Arrays.sort(leger);
        System.out.println(leger[0]);
        System.out.println(leger[1]);
        //tester skrivResept
        System.out.println("\ntester skrivResept");
        spesialist.skrivHvitResept(vanlig, pasient, 3);
        spesialist.skrivHvitResept(vanedannende, pasient, 3);
        spesialist.skrivBlaaResept(narkotisk, pasient, 3);
        lege.skrivBlaaResept(vanedannende, pasient, 3);
        System.out.println(spesialist.hentResepter());
        lege.skrivBlaaResept(narkotisk, pasient, 3);
    }
}
