package package_;

public class Zabytkowy extends Samochod {
    SamochodTyp type;
    Double price;
    public Zabytkowy(String mark, Integer distance) {
        super(mark, distance);
        this.type = SamochodTyp.ZABYTKOWY;
        String[] values = Cennik.memory.get(mark);
        if (values != null){
            this.price = Double.parseDouble(values[1]);
        }
    }
    @Override
    public String toString() {
        String price = this.price != null ? "cena" + String.valueOf(this.price) : " ceny brak";
        return super.mark + ", typ: zabytkowy, ile: " + super.distance + price;
    }

    public Integer getDistance(){
        return super.distance;
    }
    @Override
    public Double getBasicPrice() {
        return this.price;
    }

    @Override
    public Integer getPrice() {
        return (int)(super.distance * this.price);
    }


}
