package supermarked.data;

import java.time.LocalDate;

public class Vare {
    private final String id;
    private final String navn;
    private final LocalDate prisDato;
    private final double pris;
    private final boolean erBilligmaerke;
    private final double maengde;
    private final String enhed;
    private final boolean erVejet;
    private final boolean erOekologisk;
    private final boolean erTilgaengelig;

    public Vare(String id, String navn, LocalDate prisDato, double pris,
                boolean erBilligmaerke, double maengde, String enhed,
                boolean erVejet, boolean erOekologisk, boolean erTilgaengelig) {
        this.id = id;
        this.navn = navn;
        this.prisDato = prisDato;
        this.pris = pris;
        this.erBilligmaerke = erBilligmaerke;
        this.maengde = maengde;
        this.enhed = enhed;
        this.erVejet = erVejet;
        this.erOekologisk = erOekologisk;
        this.erTilgaengelig = erTilgaengelig;
    }

    public String getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public LocalDate getPrisDato() {
        return prisDato;
    }

    public double getPris() {
        return pris;
    }

    public boolean getErBilligmaerke() {
        return erBilligmaerke;
    }

    public double getMaengde() {
        return maengde;
    }

    public String getEnhed() {
        return enhed;
    }

    public boolean getErVejet() {
        return erVejet;
    }

    public boolean getErOekologisk() {
        return erOekologisk;
    }

    public boolean getErTilgaengelig() {
        return erTilgaengelig;
    }

    @Override
    public String toString() {
        return "Vare[id=" + id + ", navn=" + navn + ", prisDato=" + prisDato + ", pris=" + pris
                + ", erBilligmaerke=" + erBilligmaerke + ", maengde=" + maengde + ", enhed=" + enhed
                + ", erVejet=" + erVejet + ", erOekologisk=" + erOekologisk
                + ", erTilgaengelig=" + erTilgaengelig + "]";
    }
}
