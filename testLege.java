import java.util.Arrays;

public class testLege {
    public static void main(String[] args) throws UlovligUtskrift {
        // legemidler
        Vanlig vanlig = new Vanlig("Navn1", 3210, 100);
        Narkotisk narkotisk = new Narkotisk("navn2", 20, 200, 2);
        Vanedannende vanedannende = new Vanedannende("navn3", 30, 300, 3);
        // leger
        Lege lege = new Lege("Lege");
        Spesialist spesialist = new Spesialist("navn5", "id123");

        // pasient
        Pasient pasient = new Pasient("Karl", "12345612345");

        // resepter
        HvitResept hvitResept = new HvitResept(vanlig, lege, pasient, 3);
        Militaerresept militaerresept = new Militaerresept(narkotisk, lege, pasient);
        PResept pResept = new PResept(vanedannende, lege, pasient, 3);
        BlaaResept blaaResept = new BlaaResept(vanedannende, lege, pasient, 3);

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
        System.out.println("\n tester skrivResept");
        System.out.println(lege.skrivHvitResept(vanlig, pasient, 3));
    }
}
