public class main {

    public static void rollDices() {
        Dice dices = new Dice();
        int min = 1;
        int max = dices.getAllDicesNr();
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

        System.out.println(random);
        // logic for throwing the dices
        if (0 < random && random <= dices.getRedNr()) {
            // red
            Dice dice = new Dice(spinNumber(), Dice.ColorsInGame.RED);
            dice.showDice();
        } else if (dices.getRedNr() < random && random <= (dices.getRedNr() + dices.getBlueNr())) {
            // blue
            Dice dice = new Dice(spinNumber(), Dice.ColorsInGame.BLUE);
            dice.showDice();
        } else if (dices.getRedNr() + dices.getBlueNr() < random && random <= (dices.getRedNr() + dices.getBlueNr() + dices.getPurpleNr())) {
            // purple
            Dice dice = new Dice(spinNumber(), Dice.ColorsInGame.PURPLE);
            dice.showDice();
        } else if ((dices.getRedNr() + dices.getBlueNr() + dices.getPurpleNr()) < random && random <= (dices.getRedNr() + dices.getBlueNr() + dices.getPurpleNr() + dices.getYellowNr())) {
            // yellow
            Dice dice = new Dice(spinNumber(), Dice.ColorsInGame.YELLOW);
            dice.showDice();
        } else if ((dices.getRedNr() + dices.getBlueNr() + dices.getPurpleNr() + dices.getYellowNr()) < random && random <= (dices.getAllDicesNr())) {
            // green
            Dice dice = new Dice(spinNumber(), Dice.ColorsInGame.GREEN);
            dice.showDice();
        }
    }

    public static Dice.ColorsInGame spinColor() {
        return Dice.ColorsInGame.randomColor();
    }

    public static int spinNumber() {
        int min = 1;
        int max = 6;
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return random;
    }

    public static void main(String[] args) {
        Dice dice = new Dice();
        while (dice.getAllDicesNr() > 0) {
            rollDices();
            dice.showAllDices();
        }

        Dice.ColorsInGame colorsInGame = spinColor();
        System.out.println(colorsInGame);





    }
}
