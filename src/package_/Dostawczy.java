package package_;

public class Dostawczy extends Samochod {
    SamochodTyp type;
    Double price;
    Integer distanceWithBasicPrice;
    Double priceAfterXKM;
    public Dostawczy(String mark, int distance){
        super(mark, distance);
        this.type = SamochodTyp.DOSTAWCZY;
        String[] values = Cennik.memory.get(mark);
        if (values != null){
            this.price = Double.parseDouble(values[1]);
            this.distanceWithBasicPrice = Integer.parseInt(values[2]);
            this.priceAfterXKM = Double.parseDouble(values[3]);
        }
    }
    @Override
    public String toString() {
        String price = this.price != null ? "cena " + String.valueOf(this.price) : " ceny brak";
        return super.mark + ", typ: " + type.toString() + ", ile: " + super.distance + " km, " + price + " (do " + distanceWithBasicPrice + "), " + priceAfterXKM + " (od " + (distanceWithBasicPrice + 1) + ")";
    }
    @Override
    public Double getBasicPrice() {
        return this.price;
    }
    @Override
    public Integer getPrice(){
        int sum = 0;
        if (super.distance > distanceWithBasicPrice){
            int kmOver= super.distance -distanceWithBasicPrice;

            sum += (int) ((kmOver * this.priceAfterXKM) + (distanceWithBasicPrice * this.price));
        }
        else {
            sum += (int) (super.distance * this.distanceWithBasicPrice);

        }
        return sum;
    }

}
