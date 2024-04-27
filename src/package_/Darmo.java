package package_;

public class Darmo extends Samochod {
    SamochodTyp type;
    Integer maxDistance;
    Double price;

    public Darmo(String mark, Integer distance) {
        super(mark, distance);
        this.type = SamochodTyp.DARMO;
        String[] values = Cennik.memory.get(mark);
        if (values != null) {
            this.maxDistance = Integer.parseInt(values[1]);
        }
        if (this.maxDistance < super.distance){
            super.distance = maxDistance;
        }
        this.price = 0.00;
    }

    @Override
    public String toString() {
        return super.mark + ", typ: " + type.toString() + ", ile: " + super.distance + ", ceny " + this.price;
    }

    public Double getBasicPrice() {
        return this.price;
    }
    @Override
    public Integer getPrice(){
        return 0;
    }
}
