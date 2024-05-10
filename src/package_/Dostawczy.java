package package_;

import java.text.DecimalFormat;

public class Dostawczy extends Samochod {
    private Double price;
    private Integer distanceWithBasicPrice;
    private Double priceAfterXKM;
    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public Dostawczy(String mark, int distance){
        super(mark, distance, SamochodTyp.DOSTAWCZY);
        String[] values = Cennik.getMemory().get(mark);
        if (values != null){
            this.price = Double.parseDouble(values[1]);
            this.distanceWithBasicPrice = Integer.parseInt(values[2]);
            this.priceAfterXKM = Double.parseDouble(values[3]);
        }
    }
    public Dostawczy(Dostawczy dostawczy) {
        super(dostawczy.getMark(), dostawczy.getDistance(), dostawczy.getType());
        this.price = dostawczy.getPrice();
        this.distanceWithBasicPrice = dostawczy.getDistanceWithBasicPrice();
        this.priceAfterXKM = dostawczy.getPriceAfterXKM();
    }
    @Override
    public String toString() {
        String price = this.price != null ? "cena " + String.valueOf(this.price) : " ceny brak";
        if (super.getDistance() <= this.distanceWithBasicPrice) return super.getMark() + ", typ: " + super.getType().toString().toLowerCase() + ", ile: " + super.getDistance() + " km, " + "cena " + df.format(this.price);
        return super.getMark() + ", typ: " + super.getType().toString().toLowerCase() + ", ile: " + super.getDistance() + " km, " + df.format(this.price) + " (do " + distanceWithBasicPrice + "), " + df.format(priceAfterXKM) + " (od " + (distanceWithBasicPrice + 1) + ")";
    }

    @Override
    public Integer getCost(){
        int sum = 0;
        if (super.getDistance() > distanceWithBasicPrice){
            int kmOver= super.getDistance() -distanceWithBasicPrice;

            sum += (int) ((kmOver * this.priceAfterXKM) + (distanceWithBasicPrice * this.price));
        }
        else {
            sum += (int) (super.getDistance() * this.getPrice());

        }
        return Integer.valueOf(sum);
    }

    public Double getPrice() {return this.price;}
    public Integer getDistanceWithBasicPrice() {
        return distanceWithBasicPrice;
    }
    public Double getPriceAfterXKM() {
        return priceAfterXKM;
    }
}
