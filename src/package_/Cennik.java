package package_;

import java.util.HashMap;
import java.util.Map;

public class Cennik {
    private static Cennik cennik;
    static Map<String, String[]> memory;

    private Cennik() {
        memory = new HashMap<>();
    }

    public static Cennik pobierzCennik() {
        if (cennik == null) {
            cennik = new Cennik();
        }
        return cennik;
    }

    // Osobowy
    public void dodaj(SamochodTyp typ, String marka, Double priceWithAbonement, Double priceWithoutAbonement, Integer distanceWithBasicPrice, Double priceAfterXKm) {
        String[] values = {typ.toString(), String.valueOf(priceWithAbonement), String.valueOf(priceWithoutAbonement), String.valueOf(distanceWithBasicPrice), String.valueOf(priceAfterXKm)};
        memory.put(marka, values);
    }

    // Dostawczy
    public void dodaj(SamochodTyp typ, String marka, Integer priceBasic, Integer distanceWithBasicPrice, Integer priceAfterXKM) {
        String[] values = {typ.toString(), String.valueOf(priceBasic), String.valueOf(distanceWithBasicPrice), String.valueOf(priceAfterXKM)};
        memory.put(marka, values);
    }
    // Zabytkowy
    public void dodaj(SamochodTyp typ, String marka, Integer priceBasic) {
        String[] values = {typ.toString(), String.valueOf(priceBasic)};
        memory.put(marka, values);
    }
    // Darmo
    public void dodaj(SamochodTyp typ, Integer maxDistance, String marka) {
        String[] values = {typ.toString(), String.valueOf(maxDistance)};
        memory.put(marka, values);
    }
}

