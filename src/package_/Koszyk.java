package package_;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Koszyk {
    String id;
    ArrayList<Samochod> zyczenia;

    Koszyk(ArrayList<Samochod> zyczenia, String id){
        this.zyczenia = zyczenia;
        this.id = id;
    }
    @Override
    public String toString() {
        if(zyczenia.isEmpty()){
            return id + " -- pusto";
        }
        StringBuilder output = new StringBuilder();
        output.append("\n");
        for (Samochod zyczenie : zyczenia) {
            output.append(zyczenie.toString()).append("\n");
        }
        return output.toString();
    }

}
