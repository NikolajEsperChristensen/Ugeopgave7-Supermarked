import supermarked.data.Vare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kasseapparat {
    // Priserne er inkl. 25% moms
    private static final double MOMS_SATS = 0.25;

    // Alle tilbud, så kasseapparatet hurtigt kan slå en vare op på dens id
    private final Map<String, Vare> tilbudEfterId = new HashMap<>();

    public Kasseapparat(List<Vare> alleTilbud) {
        for (Vare tilbud : alleTilbud) {
            tilbudEfterId.put(tilbud.getId(), tilbud);
        }
    }

    // Er varen på tilbud?
    public boolean isOnOffer(Vare vare) {
        return tilbudEfterId.containsKey(vare.getId());
    }

    // Den pris kunden skal betale: tilbudspris hvis varen er på tilbud, ellers normalpris
    public double findPrice(Vare vare) {
        Vare tilbud = tilbudEfterId.get(vare.getId());
        if (tilbud != null) {
            return tilbud.getPris();
        }
        return vare.getPris();
    }

    // Samlet beløb efter rabatter
    public double calculateTotal(Kurv kurv) {
        double total = 0;
        for (Vare vare : kurv.getItems()) {
            total += findPrice(vare);
        }
        return round(total);
    }

    // Moms er en del af totalen (prisen er inkl. moms): total * 25 / 125
    public double calculateVat(double total) {
        return round(total * MOMS_SATS / (1 + MOMS_SATS));
    }

    public void printReceipt(Kurv kurv) {
        System.out.println("----------------------------------------");
        System.out.println("                KASSEBON");
        System.out.println("----------------------------------------");

        // Saml ens varer: tæl hvor mange der er af hver vare (ud fra id).
        // unikkeVarer husker hver vare én gang i den rækkefølge, de først kom i kurven.
        List<Vare> unikkeVarer = new ArrayList<>();
        Map<String, Integer> antalPerVare = new HashMap<>();

        for (Vare vare : kurv.getItems()) {
            String id = vare.getId();
            if (antalPerVare.containsKey(id)) {
                // varen er set før: læg en til antallet
                antalPerVare.put(id, antalPerVare.get(id) + 1);
            } else {
                // varen er ny: start på 1 og husk den
                antalPerVare.put(id, 1);
                unikkeVarer.add(vare);
            }
        }

        for (Vare vare : unikkeVarer) {
            int antal = antalPerVare.get(vare.getId());

            // Varenavnet står kun én gang, derefter antal × stykpris = samlet pris
            System.out.println(vare.getNavn());
            System.out.println("  " + antal + " × " + kr(vare.getPris()) + " = " + kr(antal * vare.getPris()));

            // På tilbud: rabatten på en separat linje som et negativt beløb
            if (isOnOffer(vare)) {
                double rabatPerStk = findPrice(vare) - vare.getPris();     // negativ
                System.out.println("  Tilbud: " + antal + " × " + kr(rabatPerStk) + " = " + kr(antal * rabatPerStk));
            }
        }

        double total = calculateTotal(kurv);
        double moms = calculateVat(total);

        System.out.println("----------------------------------------");
        System.out.println("Antal varer: " + kurv.getItems().size());
        System.out.println("TOTAL: " + kr(total));
        System.out.println("Heraf moms (25%): " + kr(moms));
        System.out.println("----------------------------------------");
    }

    // Skriver et beløb med 2 decimaler, fx 2,50 kr.
    private String kr(double beloeb) {
        return String.format("%.2f kr.", beloeb);
    }

    // Afrunder til hele øre
    private double round(double beloeb) {
        return Math.round(beloeb * 100) / 100.0;
    }
}
