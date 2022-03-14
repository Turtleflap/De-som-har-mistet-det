public class Hovedprogram {
    public static void main(String[] args) {
        Narkotisk narkotisk = new Narkotisk("narkotisk", 400, 0.3, 5);
        Vanedannende vanedannende = new Vanedannende("vanndannende", 200, 0.7, 4);
        Vanlig vanlig = new Vanlig("vanlig", 170, 0.4);

        Lege lege = new Lege("Ole");
        Spesialist spesialist = new Spesialist("Jan", "A15");

        HvitResept hvitResept = new HvitResept(narkotisk, spesialist, 3, 5);
        BlaaResept blaaResept = new BlaaResept(vanedannende, lege, 2, 5);
        Militaerresept militaerresept = new Militaerresept(vanlig, lege, 3);
        PResept pResept = new PResept(narkotisk, spesialist, 4, 2);

        System.out.println(narkotisk);
        System.out.println(vanedannende);
        System.out.println(vanlig + "\n");
        System.out.println("Lege " + lege);
        System.out.println("Spesialist " + spesialist + "\n");
        System.out.println("Hvit resept: " + hvitResept + "\n");
        System.out.println("Blaa resept: " + blaaResept + "\n");
        System.out.println("Militaer resept: " + militaerresept + "\n");
        System.out.println("P-resept: " + pResept + "\n");


    }
}
