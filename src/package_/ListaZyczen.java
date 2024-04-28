package package_;

import java.util.ArrayList;

public class ListaZyczen {
    private String imie;
    private ArrayList<Samochod> zyczenia;

    public ListaZyczen(String imie, ArrayList<Samochod> zyczenia) {
        this.imie = imie;
        this.zyczenia = zyczenia;
    }

    @Override
    public String toString() {
        if(zyczenia.isEmpty()){
            return this.imie + " -- pusto" + "\n";
        }
        StringBuilder output = new StringBuilder();
        output.append(this.imie + "\n");
        for (Samochod zyczenie : zyczenia) {
            output.append(zyczenie.toString()).append("\n");
        }
        return output.toString();
    }

    public String getImie() {
        return imie;
    }

    public ArrayList<Samochod> getZyczenia() {
        return zyczenia;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setZyczenia(ArrayList<Samochod> zyczenia) {
        this.zyczenia = zyczenia;
    }
}