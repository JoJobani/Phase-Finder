//Yehonatan Menashe 206478067

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Catcher.
 */
public class Catcher {
    private final String line;
    private HashMap<String, Integer> map;
    private final ArrayList<String> regex;
    private final String lemma;
    private static final int NEW_VALUE = 1;

    /**
     * Instantiates a new Catcher.
     *
     * @param line  the line
     * @param map   the current map
     * @param lemma the lemma
     */
    public Catcher(String line, HashMap<String, Integer> map, String lemma) {
        this.line = line;
        this.map = map;
        regex = RegexPatterns.regexList();
        this.lemma = lemma;
    }

    /**
     * Find the actual hypernym within the line.
     *
     * @return the updated hash map
     */
    public HashMap<String, Integer> lineProcess() {
        for (String s : regex) {
            Pattern pattern = Pattern.compile(s);
            Matcher match = pattern.matcher(line);
            if (match.find()) {
                //normal regex case
                if (match.group(0).contains("<np>" + lemma + "</np>")
                        && !match.group(1).equals(lemma)) {
                    String hypernym = match.group(1);
                    if (!map.containsKey(hypernym)) {
                        map.put(hypernym, NEW_VALUE);
                    } else {
                        map.put(hypernym, map.get(hypernym) + 1);
                    }
                }
                //fifth regex case
                if (s.equals(RegexPatterns.FIFTH_REGEX)) {
                    if (match.group(0).contains(lemma)) {
                        String hypernym = match.group(2);
                        if (!map.containsKey(hypernym)) {
                            map.put(hypernym, NEW_VALUE);
                        } else {
                            map.put(hypernym, map.get(hypernym) + 1);
                        }
                    }
                }
            }
        }
        return map;
    }
}
