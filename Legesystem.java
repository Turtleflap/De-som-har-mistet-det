
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
          filen = sc.nextLine();
          if (filen.charAt(0) == "#"){
            break;
          }
          //legg til pasient 

        }
      }


      else if (filen[1].compareTo("Legemidler") == 0){
        while (sc.hasNextLine()){
          filen = sc.nextLine();
          if (filen.charAt(0) == "#"){
            break;
          }
          //legg til legemiddel

        }
      }
    }
  }
}