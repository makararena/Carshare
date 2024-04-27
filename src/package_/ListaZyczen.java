package package_;

import java.util.ArrayList;

public class ListaZyczen {
    String imie;
    ArrayList<Samochod> zyczenia;

    public ListaZyczen(String imie, ArrayList<Samochod> zyczenia) {
        this.imie = imie;
        this.zyczenia = zyczenia;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(this.imie + "\n");
        for (Samochod zyczenie : zyczenia) {
            output.append(zyczenie.toString()).append("\n");
        }
        return output.toString();
    }
}