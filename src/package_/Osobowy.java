package package_;

public class Osobowy extends Samochod {
    SamochodTyp type;
    Double priceWithAbonement;
    Double priceWithoutAbonement;
    Double distanceWithBasicPrice;
    Double priceAfterXKm;

    public Osobowy(String mark, Integer distance) {
        super(mark, distance);
        this.type = SamochodTyp.OSOBOWY;
        String[] values = Cennik.memory.get(mark);
        if (values != null) {
            this.priceWithAbonement = Double.parseDouble(values[1]);
            this.priceWithoutAbonement = Double.parseDouble(values[2]);
            this.distanceWithBasicPrice = Double.parseDouble(values[3]);
            this.priceAfterXKm = Double.parseDouble(values[4]);
        }
    }
    @Override
    public String toString() {
        return super.mark + ", typ: " + type.toString() + ", ile: " + super.distance + " km, cena " + priceWithAbonement;
    }
    public Double getPriceAbonement() {
        return this.priceWithAbonement;
    }
    @Override
    public Double getBasicPrice() {
        return this.priceWithoutAbonement;
    }
    @Override
    public Integer getPrice() {
        double cena;
        if (super.distance > distanceWithBasicPrice) {
            int kmOver = (int) (super.distance - distanceWithBasicPrice);
            cena = (kmOver * priceAfterXKm) + (distanceWithBasicPrice * (super.getPosiadaAbonement() ? priceWithAbonement : priceWithoutAbonement));
        } else {
            cena = super.distance * (super.getPosiadaAbonement() ? priceWithAbonement : priceWithoutAbonement);
        }
        return (int) cena;
    }

}
