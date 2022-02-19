import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Sagrada extends Application {
    public static void main(String[] args) {
        launch(args);
    }

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

    // Spinning random color
    public static Color spinColor() {
        Dice.ColorsInGame colorsInGame = Dice.ColorsInGame.randomColor();

        if (colorsInGame.equals(Dice.ColorsInGame.RED)) {
            return Color.RED;
        } else if (colorsInGame.equals(Dice.ColorsInGame.BLUE)) {
            return Color.BLUE;
        } else if (colorsInGame.equals(Dice.ColorsInGame.PURPLE)) {
            return Color.PURPLE;
        } else if (colorsInGame.equals(Dice.ColorsInGame.YELLOW)) {
            return Color.YELLOW;
        } else if (colorsInGame.equals(Dice.ColorsInGame.GREEN)) {
            return Color.GREEN;
        } else {
            System.out.println("ERROR");
        }

        return Color.WHITE;
    }

    public static int spinNumber() {
        int min = 1;
        int max = 6;
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return random;
    }

    @Override
    public void start(Stage primaryStage) {

        // Buttons
        Button[][] buttons = new Button[4][5];

        Button spincolorButton = new Button("Wylosuj kolor");
        colorButton.setPrefSize(100, 10);

        Button button = new Button();
        button.setPrefSize(30, 30);
        button.setStyle("-fx-border-color: black;");
        button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
        button


        // HBox
        HBox hBox = new HBox();
        hBox.setStyle("-fx-border-width: 2;" +
                "-fx-border-color: black;");
        hBox.setPadding(new Insets(10));
        hBox.getChildren().add(button);
        hBox.setMaxWidth(100);
        hBox.setAlignment(Pos.CENTER);




        // GridPane
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new Button();
                gridPane.add(buttons[i][j], j, i);
                buttons[i][j].setPrefSize(70, 70);
                buttons[i][j].setStyle("-fx-border-color: black;" +
                        "-fx-border-width: 3");


            }
        }
        gridPane.setHgap(0.49);
        gridPane.setVgap(0.49);

        // VBox
        VBox vBox = new VBox();
        vBox.setLayoutX(50);
        vBox.setLayoutY(350);
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(colorButton);
        vBox.setSpacing(10);

        // Layout
        Group group = new Group();
        group.getChildren().add(vBox);

        // Scene
        Scene scene = new Scene(group);

        // Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sagrada");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.show();

        // Listeners
        colorButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setBackground(new Background(new BackgroundFill(spinColor(), CornerRadii.EMPTY,Insets.EMPTY)));
                colorButton.setVisible(false);
            }
        });

    }
}
