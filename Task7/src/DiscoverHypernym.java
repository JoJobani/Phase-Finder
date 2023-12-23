//Yehonatan Menashe 206478067

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static java.lang.System.exit;

/**
 * The type Discover hypernym.
 */
public class DiscoverHypernym {
    private static HashMap<String, Integer> map;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        map = new HashMap<>();
        //check for valid input
        if (args.length < 2) {
            System.out.println("Program needs path and lemma to function");
            exit(1);
        }
        //if lemma is longer than 1 word combine it to a single string
        String lemma = args[1];
        if (args.length > 2) {
            for (int i = 2; i < args.length; i++) {
                lemma = lemma + " ";
                lemma = lemma.concat(args[i]);
            }
        }
        //take care of corpus
        String corpus = args[0];
        File corDir = new File(corpus);
        File[] files = corDir.listFiles();
        if (files != null) {
            for (File file : files) {
                try (BufferedReader bufferedReader =
                             new BufferedReader(new InputStreamReader(
                                     new FileInputStream(file)))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.contains(lemma)) {
                            Catcher catcher = new Catcher(line, map, lemma);
                            map = catcher.lineProcess();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Could not read file");
                }
            }
        } else {
            System.out.println("File reading error");
        }
        Printer printer = new Printer(map);
        printer.printMap();
    }
}
