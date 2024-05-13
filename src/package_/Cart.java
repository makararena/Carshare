package package_;

import java.util.ArrayList;

public class Cart {
    private String id;
    private ArrayList<Car> wishes;

    Cart(ArrayList<Car> wishes, String id){
        this.wishes = wishes;
        this.id = id;
    }
    @Override
    public String toString() {
        if(wishes.isEmpty()){
            return id + " -- blank" + "\n";
        }
        // https://javastart.pl/baza-wiedzy/klasy/string-stringbuffer-i-stringbuilder
        StringBuilder output = new StringBuilder();
        output.append(this.id + ":" + "\n");
        for (Car wish : wishes) {
            output.append(wish.toString()).append("\n");
        }
        return output.toString();
    }
    public ArrayList<Car> getWishes() {
        return wishes;
    }
}
