package package_;

import java.util.ArrayList;

public class WishList {
    private String name;
    private ArrayList<Car> wishes;

    public WishList(String name, ArrayList<Car> wishes) {
        this.name = name;
        this.wishes = wishes;
    }

    @Override
    public String toString() {
        if(wishes.isEmpty()){
            return this.name + " -- blank" + "\n";
        }
        // https://javastart.pl/baza-wiedzy/klasy/string-stringbuffer-i-stringbuilder
        StringBuilder output = new StringBuilder();
        output.append(this.name + "\n");
        for (Car wish : wishes) {
            output.append(wish.toString()).append("\n");
        }
        return output.toString();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Car> getWishes() {
        return wishes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWishes(ArrayList<Car> wishes) {
        this.wishes = wishes;
    }
}