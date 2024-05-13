package package_;

import java.text.DecimalFormat;

public class Historic extends Car {
    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private CarType type;
    private Double price;
    public Historic(String mark, Integer distance) {
        super(mark, distance, CarType.HISTORIC);
        String[] values = Price.getMemory().get(mark);
        if (values != null){
            this.price = Double.parseDouble(values[1]);
        }
    }
    public Historic(Historic historic) {
        super(historic.getMark(), historic.getDistance(), historic.getType());
        this.type = historic.getType();
        this.price = historic.getPrice();
    }
    @Override
    public String toString() {
        String formattedPrice = this.price != null ? ", price: " + df.format(this.price) : ", no price";
        return super.getMark() + ", type: " + super.getType().toString().toLowerCase() + ", how many: " + super.getDistance() + formattedPrice;
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
