import java.util.*;

public class ManualDistribution {
    final NavigableMap<Double, String> map = new TreeMap<>();
    double total = 0;

    public void add(String item, double weight) {
        if (weight <= 0) {
            return;
        }
        total += weight;
        map.put(total, item);
    }

    public String sample() {
        double value = Math.random() * total;
        return map.higherEntry(value).getValue();
    }

}