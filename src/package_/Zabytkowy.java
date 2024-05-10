package package_;

import java.text.DecimalFormat;

public class Zabytkowy extends Samochod {
    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private SamochodTyp type;
    private Double price;
    public Zabytkowy(String mark, Integer distance) {
        super(mark, distance, SamochodTyp.ZABYTKOWY);
        String[] values = Cennik.getMemory().get(mark);
        if (values != null){
            this.price = Double.parseDouble(values[1]);
        }
    }
    public Zabytkowy(Zabytkowy zabytkowy) {
        super(zabytkowy.getMark(), zabytkowy.getDistance(), zabytkowy.getType());
        this.type = zabytkowy.getType();
        this.price = zabytkowy.getPrice();
    }
    @Override
    public String toString() {
        String formattedPrice = this.price != null ? ", cena: " + df.format(this.price) : ", ceny brak";
        return super.getMark() + ", typ: " + super.getType().toString().toLowerCase() + ", ile: " + super.getDistance() + formattedPrice;
    }

    public Integer getDistance(){
        return super.getDistance();
    }
    @Override
    public Double getPrice() {return this.price;}
    @Override
    public Integer getCost() {
        return (int) (super.getDistance() * this.price);
    }
    public void setPrice(Double price) {this.price = price;}
}
