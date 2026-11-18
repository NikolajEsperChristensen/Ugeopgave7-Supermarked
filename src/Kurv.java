import supermarked.data.Vare;

import java.util.ArrayList;
import java.util.List;

// En kunde kurv: de varer kunden har samlet i butikken
public class Kurv {
    private final List<Vare> varer = new ArrayList<>();

    public void addToCart(Vare vare) {
        varer.add(vare);
    }

    public List<Vare> getItems() {
        return varer;
    }
}
