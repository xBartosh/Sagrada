import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dice {
    private static int redNr = 18;
    private static int blueNr = 18;
    private static int purpleNr = 18;
    private static int yellowNr = 18;
    private static int greenNr = 18;
    private int number;
    private ColorsInGame colorsInGame;

    // colors
    public enum ColorsInGame {
        RED, BLUE, PURPLE, YELLOW, GREEN;

        ColorsInGame(){ }

        private static final List<ColorsInGame> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static ColorsInGame randomColor()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    // constructors
    public Dice() { }

    public Dice(int number, ColorsInGame colorsInGame) {
        this.number = number;
        this.colorsInGame = colorsInGame;
        switch (colorsInGame) {
            case RED:
                this.redNr -= 1;
                break;
            case BLUE:
                this.blueNr -= 1;
                break;
            case PURPLE:
                this.purpleNr -= 1;
                break;
            case YELLOW:
                this.yellowNr -= 1;
                break;
            case GREEN:
                this.greenNr -= 1;
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    // getters
    public int getNumber() { return number; }

    public ColorsInGame getColor() { return colorsInGame; }

    public int getRedNr() {
        return redNr;
    }

    public int getBlueNr() {
        return blueNr;
    }

    public int getPurpleNr() {
        return purpleNr;
    }

    public int getYellowNr() { return yellowNr; }

    public int getGreenNr() {
        return greenNr;
    }

    public int getAllDicesNr() {
        return (this.redNr + this.blueNr + this.purpleNr + this.yellowNr + this.greenNr);
    }

    // show dices
    public void showAllDices() {
        System.out.println("Red: " + redNr + "\n" +
                "Blue: " + blueNr + "\n" +
                "Purple: " + purpleNr + "\n" +
                "Yellow: " + yellowNr + "\n" +
                "Green: " + greenNr + "\n");
    }

    // show one dice with its properties
    public void showDice(){
        System.out.println("Color: " + this.colorsInGame + "\n" +
                "Number: " + this.getNumber());

    }


}
