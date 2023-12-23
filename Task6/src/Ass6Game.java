//Yehonatan Menashe 206478067

import Levels.DirectHit;
import Levels.GreenStandard;
import Levels.LevelInformation;
import Levels.WideEasy;
import game.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * The game launcher.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new GreenStandard());
        List<LevelInformation> inputs = parseNums(args, levels);
        GameFlow game = new GameFlow();
        if (inputs.isEmpty()) {
            game.runLevels(levels);
        } else {
            game.runLevels(inputs);
        }
    }

    /**
     * Filter the arguments into a list of wanted levels.
     *
     * @param args            arguments input
     * @param availableLevels list of all levels
     * @return list of wanted levels
     */
    private static List<LevelInformation>
    parseNums(String[] args, List<LevelInformation>
            availableLevels) {
        List<Integer> levelNums = new ArrayList<>();
        for (String arg : args) {
            try {
                int levelNum = Integer.parseInt(arg);
                levelNums.add(levelNum);
            } catch (NumberFormatException e) {
                //a non-number was entered, ignore it
            }
        }
        List<LevelInformation> finalLevels = new ArrayList<>();
        for (int num : levelNums) {
            int levelWanted = num - 1;
            if (levelWanted >= 0 && levelWanted < 3) {
                finalLevels.add(availableLevels.get(levelWanted));
            }
        }
        return finalLevels;
    }
}
