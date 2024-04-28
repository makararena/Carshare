package package_;

import java.util.ArrayList;

public class Koszyk {
    private String id;
    private ArrayList<Samochod> zyczenia;

    Koszyk(ArrayList<Samochod> zyczenia, String id){
        this.zyczenia = zyczenia;
        this.id = id;
    }
    @Override
    public String toString() {
        if(zyczenia.isEmpty()){
            return id + " -- pusto" + "\n";
        }
        StringBuilder output = new StringBuilder();
        output.append(this.id + ":" + "\n");
        for (Samochod zyczenie : zyczenia) {
            output.append(zyczenie.toString()).append("\n");
        }
        return output.toString();
    }

    public String getId() {
        return id;
    }

    public ArrayList<Samochod> getZyczenia() {
        return zyczenia;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setZyczenia(ArrayList<Samochod> zyczenia) {
        this.zyczenia = zyczenia;
    }
}
