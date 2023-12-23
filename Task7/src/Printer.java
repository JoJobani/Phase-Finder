//Yehonatan Menashe 206478067

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Printer.
 */
public class Printer {
    private HashMap<String, Integer> map;

    /**
     * Instantiates a new Printer instance.
     *
     * @param map the map
     */
    public Printer(HashMap<String, Integer> map) {
        this.map = map;
    }

    /**
     * Sort and print the map.
     */
    public void printMap() {
        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
        }
    }
}
