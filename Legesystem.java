
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Legesystem{
  

  public Legesystem(String filnavn){
    File f = new File(filnavn);
    Scanner sc = null;

    try {
      sc = new Scanner(f);
    } catch (FileNotFoundException e) {
      System.out.println("File '" + filnavn + "' not found!");
    }

    String innlest = sc.nextLine();
    
    while (sc.hasNextLine()){
      String[] filen = innlest.split(" ");

      if (filen[1].compareTo("Pasienter") == 0){
        while (sc.hasNextLine()){
          innlest = sc.nextLine();
          if (innlest.charAt(0) == '#'){
            break;
          }

          Pasient nyPasient = new Pasient(filen[0],filen[1]);
          //legg til pasient 

        }
      }

    }
  }
}