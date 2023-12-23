//Yehonatan Menashe 206478067

import java.util.ArrayList;

/**
 * The type Regex patterns.
 */
public class RegexPatterns {
    /**
     * The constant for the first regex rule.
     */
    public static final String FIRST_REGEX =
            "<np>([^<]+)</np>(?: ,)? such as <np>([^<]+)</np>((?:"
                    + "(?: ,? ?)?<np>([^<]+)</np>)+(?: ,? ?)?(?:and|or)"
                    + " <np>([^<]+)</np>)?";
    /**
     * The constant for the second regex rule.
     */
    public static final String SECOND_REGEX =
            "such <np>([^<]+)</np>(?: ,)? as <np>([^<]+)</np>"
                    + "((?:( ?,? ?)?<np>([^<]+)</np>)*(?: ,? ?)?"
                    + "(?:and |or )<np>([^<]+)</np>)?";
    /**
     * The constant for the third regex rule.
     */
    public static final String THIRD_REGEX =
            "<np>([^<]+)</np>(?: ,)? including <np>([^<]+)</np>"
                    + "((?:( ?,? ?)?<np>([^<]+)</np>)*(?: ,? ?)?"
                    + "(?:and |or )<np>([^<]+)</np>)?";
    /**
     * The constant for the fourth regex rule.
     */
    public static final String FOURTH_REGEX =
            "<np>([^<]+)</np>(?: ,)? especially <np>([^<]+)</np>"
                    + "((?:( ?,? ?)?<np>([^<]+)</np>)*(?: ,? ?)?"
                    + "(?:and |or )<np>([^<]+)</np>)?";
    /**
     * The constant for the fifth regex rule.
     */
    public static final String FIFTH_REGEX =
            "<np>([^<]+)</np> ,? ?((which is )|(which is an example of )"
                    + "|(which is a kind of )|(which is a class of )"
                    + ")<np>([^<]+)</np>";

    /**
     * Regex list array.
     *
     * @return the array list of all but the last regex rules
     */
    public static ArrayList<String> regexList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(FIRST_REGEX);
        list.add(SECOND_REGEX);
        list.add(THIRD_REGEX);
        list.add(FOURTH_REGEX);
        list.add(FIFTH_REGEX);
        return list;
    }
}
