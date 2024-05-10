package package_;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class Klient {
    private String imie;
    private Double kwota;
    private Boolean posiadaAbonement;
    private ArrayList<Samochod> zyczenia = new ArrayList<>();
    private ArrayList<Samochod> lastTransaction;
    private Koszyk koszyk;

    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Simple Klient constructor
    public Klient(String imie, Integer kwota, Boolean posiadaAbonement) {
        this.imie = imie;
        this.kwota = (double) kwota;
        this.posiadaAbonement = posiadaAbonement;
    }
    // Adding samochod to zyczenia
    public void dodaj(Samochod samochod){
        samochod.setPosiadaAbonement(posiadaAbonement);
        this.zyczenia.add(samochod);
    }
    // Paying for samochod
    public void zaplac(PaymentType type, boolean wantPayByParts){
        lastTransaction = new ArrayList<>();
        Koszyk koszyk = pobierzKoszyk();
        int cenaKoszyka = cenaKoszyka(koszyk);

        // Adding prowizja if needed
        Double prowizja = 0.0;
        if (type == PaymentType.KARTA) {
            prowizja = cenaKoszyka * 0.01;
        }
        Double doZaplaty = cenaKoszyka + prowizja;

        // Handle case when we have enough money to pay for all samochods
        if (kwota >= doZaplaty) {
            kwota -= doZaplaty;
            ArrayList<Samochod> zyczenia = koszyk.getZyczenia();
            lastTransaction.addAll(zyczenia);
            koszyk.getZyczenia().clear();
        }
        // Handle case when we need to pay by parts
        else if (wantPayByParts) {
            koszyk.getZyczenia().sort(Comparator.comparingDouble(Samochod::getPrice).reversed());
            for (Samochod samochod : koszyk.getZyczenia()) {
                Double cenaSamochodu = samochod.getPrice() * samochod.getDistance();
                // if we heve enough money to pay for samochod fully
                if (kwota >= cenaSamochodu) {
                    kwota -= cenaSamochodu;
                    addTransaction(samochod);
                    samochod.setDistance(0);
                }
                // if we heve enough money to pay only for some distance(need to be called only once)
                else {
                    int distanceCanBeCovered = (int) Math.floor(kwota / samochod.getPrice());
                    kwota -= distanceCanBeCovered * samochod.getPrice();
                    addTransaction(samochod);
                    samochod.setDistance(samochod.getDistance() - distanceCanBeCovered);
                    break;
                }
            }
            // Remove cars if we have paid for them
            koszyk.getZyczenia().removeIf(samochod -> samochod.getDistance() <= 0);
        }
        // Handle the case when we don't have enough money and we don't want to pay by parts
        else {
            System.out.println("Niewystarczająca ilość pieniędzy w portfelu.");
        }
    }
    //  Helper function to avoid code redundancy
    private void addTransaction(Samochod samochod) {
        if (samochod instanceof Osobowy) {
            lastTransaction.add(new Osobowy((Osobowy) samochod));
        } else if (samochod instanceof Dostawczy) {
            lastTransaction.add(new Dostawczy((Dostawczy) samochod));
        } else if (samochod instanceof Zabytkowy) {
            lastTransaction.add(new Zabytkowy((Zabytkowy) samochod));
        } else if (samochod instanceof Darmo) {
            lastTransaction.add(new Darmo((Darmo) samochod));
        }
    }

    // Helper method to calculate the total price of the Koszyk
    private int cenaKoszyka(Koszyk koszyk) {
        int suma = 0;
        for (Samochod samochod : koszyk.getZyczenia()) {
            suma += samochod.getCost();
        }
        return suma;
    }

    // Getter for lista zyczen
    public ListaZyczen pobierzListeZyczen(){
        return new ListaZyczen(imie, zyczenia);
    }

    // Getter for Koszyk
    public Koszyk pobierzKoszyk(){
        if (this.koszyk == null){
            ArrayList<Samochod> arrStayInLista = new ArrayList<>();
            ArrayList<Samochod> goToKoszyk = new ArrayList<>();
            for (Samochod samochod : zyczenia){
                if (samochod.getPrice() != null) goToKoszyk.add(samochod);
                else arrStayInLista.add(samochod);
            }
            this.zyczenia = arrStayInLista;
            this.koszyk = new Koszyk(goToKoszyk, this.imie);
        }
        return this.koszyk;
    }

    // Przepraszam, całą logikę dodałem w pobierz koszyk.
    public void przepakuj(){}

    public void zwroc(SamochodTyp typ, String marka, int distance) {
        for (Samochod samochod : lastTransaction) {
            if (samochod.getType() == typ && samochod.getMark().equals(marka) && samochod.getDistance() >= distance) {
                double refundAmount = samochod.getPrice();
                kwota += (refundAmount * distance);
                boolean foundInKoszyk = false;
                for (Samochod koszykSamochod : koszyk.getZyczenia()) {
                    if (koszykSamochod.getMark().equals(marka)) {
                        foundInKoszyk = true;
                        koszykSamochod.setDistance(koszykSamochod.getDistance() + distance);
                        break;
                    }
                }
                if (!foundInKoszyk) {
                    samochod.setDistance(distance);
                    koszyk.getZyczenia().add(samochod);
                }
                lastTransaction.remove(samochod);
                return;
            }
        }
        System.out.println("No matching samochod");
    }
    public String pobierzPortfel() {
        return df.format(this.kwota);
    };

}
