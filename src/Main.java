import supermarked.data.Vare;
import supermarked.robot.Robot;

import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        List<Vare> alleVarer = readVarer("data/varer.csv");
        List<Vare> alleTilbud = readVarer("data/tilbud.csv");

        System.out.println(alleVarer.size() + " varer indlæst");
        System.out.println(alleVarer.get(2356));

        System.out.println(alleTilbud.size() + " tilbud indlæst");
        System.out.println(alleTilbud.get(123));

        // --- Robotten fylder en kurv med tilfældige varer ---
        Robot robot = new Robot();
        Collection<Vare> robotKurv = robot.fyldIKurv(alleVarer);

        // Robotten giver en Collection<Vare>, men kasseapparatet tager en Kurv, så vi overfører varerne
        Kurv kurv = new Kurv();
        for (Vare v : robotKurv) {
            kurv.addToCart(v);
        }

        // --- Kasseapparat ---
        Kasseapparat kasse = new Kasseapparat(alleTilbud);
        kasse.printReceipt(kurv);
    }

    // Læser en csv-fil og laver hver linje om til en Vare
    static List<Vare> readVarer(String filsti) throws Exception {
        List<Vare> liste = new ArrayList<>();

        List<String> linjer = Files.readAllLines(Path.of(filsti));

        for (int i = 1; i < linjer.size(); i++) {      // starter ved 1 for at springe overskriften over
            String[] f = linjer.get(i).split(";");

            Vare v = new Vare(
                    f[0],
                    f[1],
                    LocalDate.parse(f[2]),
                    Double.parseDouble(f[3]),
                    Boolean.parseBoolean(f[4]),
                    Double.parseDouble(f[5]),
                    f[6],
                    Boolean.parseBoolean(f[7]),
                    Boolean.parseBoolean(f[8]),
                    Boolean.parseBoolean(f[9]));

            liste.add(v);
        }

        return liste;
    }
}
