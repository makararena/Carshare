package package_;

import java.util.HashMap;
import java.util.Map;

public class Price {
    private static Price price;
    private static Map<String, String[]> memory;

    private Price() {
        memory = new HashMap<>();
    }

    public static Price getPrice() {
        if (price == null) {
            price = new Price();
        }
        return price;
    }

    // Personal
    public void add(CarType typ, String marka, Double priceWithAbonement, Double priceWithoutAbonement, Integer distanceWithBasicPrice, Double priceAfterXKm) {
        String[] values = {typ.toString(), String.valueOf(priceWithAbonement), String.valueOf(priceWithoutAbonement), String.valueOf(distanceWithBasicPrice), String.valueOf(priceAfterXKm)};
        memory.put(marka, values);
    }

    // Delivery
    public void add(CarType typ, String marka, Integer priceBasic, Integer distanceWithBasicPrice, Integer priceAfterXKM) {
        String[] values = {typ.toString(), String.valueOf(priceBasic), String.valueOf(distanceWithBasicPrice), String.valueOf(priceAfterXKM)};
        memory.put(marka, values);
    }
    // Historic
    public void add(CarType typ, String marka, Integer priceBasic) {
        String[] values = {typ.toString(), String.valueOf(priceBasic)};
        memory.put(marka, values);
    }
    // Free
    public void add(CarType typ, Integer maxDistance, String marka) {
        String[] values = {typ.toString(), String.valueOf(maxDistance)};
        memory.put(marka, values);
    }

    public static Map<String, String[]> getMemory() {
        return memory;
    }

    public static void setPrice(Price price) {
        Price.price = price;
    }

    public static void setMemory(Map<String, String[]> memory) {
        Price.memory = memory;
    }
}

