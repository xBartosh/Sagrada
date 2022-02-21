import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Optional;
import java.util.Random;

public class Sagrada extends Application {
    // Launch app
    public static void main(String[] args) {
        launch(args);
    }

    static int counter;
    static int roundCounter;
    static int favorTokens;
    static Color mainColor;
    static int windowCounter = 0;
    GridPane gridPaneFinal;
    HBox hBoxFinal;
    boolean isChosen;
    WindowFrameBoard windowFrameBoardDefault = new WindowFrameBoard();
    WindowFrameBoard[] windowFrameBoards = new WindowFrameBoard[4];
    WindowFrameBoard windowFrameBoardFinal = new WindowFrameBoard();
    String userDirectory = System.getProperty("user.dir");


    //
    public static Dice rollDices() {
        Dice dices = new Dice();
        int min = 1;
        int max = dices.getAllDicesNr();
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

        System.out.println(random);
        // logic for throwing the dices
        if (0 < random && random <= dices.getRedNr()) {
            // red
            return (new Dice(spinNumber(), Dice.ColorsInGame.RED));
        } else if (dices.getRedNr() < random && random <= (dices.getRedNr() + dices.getBlueNr())) {
            // blue
            return (new Dice(spinNumber(), Dice.ColorsInGame.BLUE));
        } else if (dices.getRedNr() + dices.getBlueNr() < random && random <= (dices.getRedNr() + dices.getBlueNr() + dices.getPurpleNr())) {
            // purple
            return (new Dice(spinNumber(), Dice.ColorsInGame.PURPLE));
        } else if ((dices.getRedNr() + dices.getBlueNr() + dices.getPurpleNr()) < random && random <= (dices.getRedNr() + dices.getBlueNr() + dices.getPurpleNr() + dices.getYellowNr())) {
            // yellow
            return (new Dice(spinNumber(), Dice.ColorsInGame.YELLOW));
        } else if ((dices.getRedNr() + dices.getBlueNr() + dices.getPurpleNr() + dices.getYellowNr()) < random && random <= (dices.getAllDicesNr())) {
            // green
            return (new Dice(spinNumber(), Dice.ColorsInGame.GREEN));
        }
        return dices;
    }

    // Spin the dice color
    public static Color spinColor() {
        Dice.ColorsInGame colorsInGame = Dice.ColorsInGame.randomColor();

        if (colorsInGame.equals(Dice.ColorsInGame.RED)) {
            mainColor = Color.RED;
            return Color.RED;
        } else if (colorsInGame.equals(Dice.ColorsInGame.BLUE)) {
            mainColor = Color.BLUE;
            return Color.BLUE;
        } else if (colorsInGame.equals(Dice.ColorsInGame.PURPLE)) {
            mainColor = Color.PURPLE;
            return Color.PURPLE;
        } else if (colorsInGame.equals(Dice.ColorsInGame.YELLOW)) {
            mainColor = Color.YELLOW;
            return Color.YELLOW;
        } else if (colorsInGame.equals(Dice.ColorsInGame.GREEN)) {
            mainColor = Color.GREEN;
            return Color.GREEN;
        } else {
            System.out.println("ERROR");
        }

        return Color.WHITE;
    }

    // Spin the dice number
    public static int spinNumber() {
        int min = 1;
        int max = 6;
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return random;
    }


    @Override
    public void start(Stage primaryStage) {

        // Buttons

        // for board
        Button[][] buttons = new Button[4][5];

        // for color spinning
        Button spinColorButton = new Button("Wylosuj kolor");
        spinColorButton.setPrefHeight(10);

        // for window spinning
        Button windowButton = new Button("Wylosuj karty wzorow");
        windowButton.setPrefHeight(10);
        windowButton.setDisable(true);
        windowButton.setVisible(false);

        // confirm for window
        Button confirmWindowButton = new Button();
        confirmWindowButton.setBackground(new Background(new BackgroundImage(new Image("file:///" + userDirectory + "\\src\\main\\resources\\" + "confirm.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        confirmWindowButton.setPrefSize(30, 30);
        confirmWindowButton.setStyle("-fx-border-color: black;");
        confirmWindowButton.setVisible(false);

        // arrows for windows
        Button arrowR = new Button();
        arrowR.setPrefSize(30, 30);
        arrowR.setLayoutY(608);
        arrowR.setLayoutX(429);
        arrowR.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 2;");
        arrowR.setBackground(new Background(new BackgroundImage(new Image("file:///" + userDirectory + "\\src\\main\\resources\\" + "arrowR.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arrowR.setVisible(false);

        Button arrowL = new Button();
        arrowL.setPrefSize(30, 30);
        arrowL.setLayoutY(608);
        arrowL.setLayoutX(22);
        arrowL.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 2;");
        arrowL.setBackground(new Background(new BackgroundImage(new Image("file:///" + userDirectory + "\\src\\main\\resources\\" + "arrowL.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        arrowL.setVisible(false);

        // for color
        Button colorButton = new Button();
        colorButton.setPrefSize(30, 30);
        colorButton.setStyle("-fx-border-color: black;");
        colorButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        // for spinning dices
        HBox spinDicesHBox = new HBox();
        spinDicesHBox.setAlignment(Pos.BOTTOM_CENTER);
        Button spinDicesButton = new Button("Start");
        spinDicesButton.setLayoutX(50);
        spinDicesButton.setLayoutY(150);
        spinDicesHBox.getChildren().add(spinDicesButton);

        // HBox round track, above gridPane and another HBox
        HBox roundTrack = new HBox();
        roundTrack.setPrefWidth(884);
        roundTrack.setMaxHeight(100);
        roundTrack.setSpacing(10);
        roundTrack.setLayoutY(10);
        roundTrack.setAlignment(Pos.CENTER);
        roundTrack.setPadding(new Insets(10, 0, 10, 0));
        roundTrack.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 2;"+
                "-fx-background-color: #ffdbb7;");
        roundTrack.setLayoutX(50);
        Button[] buttonsForRounds = new Button[10];
        String userDirectory = System.getProperty("user.dir");
        for (int i = 0; i < 10; i++) {
            buttonsForRounds[i] = new Button();
            buttonsForRounds[i].setPrefSize(75, 75);
            buttonsForRounds[i].setBackground(new Background(new BackgroundImage(new Image("file:///" + userDirectory + "\\src\\main\\resources\\" + "NUMBERS/" + (i + 1) + ".png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            roundTrack.getChildren().add(buttonsForRounds[i]);
        }

        // HBox for the start of the game
        HBox hBoxButtons = new HBox();
        hBoxButtons.setSpacing(10);

        // VBox, first part of the HBox
        VBox vBoxColor = new VBox();
        vBoxColor.getChildren().add(spinColorButton);
        vBoxColor.getChildren().add(colorButton);
        vBoxColor.setSpacing(7);
        vBoxColor.setAlignment(Pos.CENTER);
        hBoxButtons.getChildren().add(vBoxColor);

        // VBox, second part of the HBox
        VBox vBoxWindows = new VBox();
        vBoxWindows.getChildren().add(windowButton);
        vBoxWindows.getChildren().add(confirmWindowButton);
        vBoxWindows.setAlignment(Pos.CENTER);
        vBoxWindows.setSpacing(7);
        hBoxButtons.getChildren().add(vBoxWindows);


        // HBox above GridPane
        HBox hBoxA = new HBox();
        hBoxA.setStyle("-fx-border-width: 3;" +
                "-fx-border-color: black;");
        hBoxA.setPrefSize(250, 100);
        hBoxA.setSpacing(20);
        hBoxA.setAlignment(Pos.CENTER);
        hBoxA.setMaxWidth(381);


        // VBox
        VBox vBox = new VBox();
        vBox.setLayoutX(50);
        vBox.setLayoutY(150);
        vBox.getChildren().add(hBoxButtons);
        vBox.getChildren().add(hBoxA);
        vBox.getChildren().add(windowFrameBoardDefault.getGridPane());
        vBox.getChildren().add(windowFrameBoardDefault.gethBox());
        vBox.setSpacing(10);

        // Layout
        Group group = new Group();
        group.getChildren().add(roundTrack);
        group.getChildren().add(vBox);
        group.getChildren().add(arrowR);
        group.getChildren().add(arrowL);

        // Scene
        Scene scene = new Scene(group);
        scene.setFill(Color.web("#d2a887"));

        // Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sagrada");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Listeners

        EventHandler mouseEn = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.HAND);
            }
        };
        EventHandler mouseEx = new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        };

        // Color spinning
        spinColorButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainColor = spinColor();
                colorButton.setBackground(new Background(new BackgroundFill(mainColor, CornerRadii.EMPTY, Insets.EMPTY)));
                spinColorButton.setVisible(false);
                windowButton.setDisable(false);
                windowButton.setVisible(true);
            }
        });

        // Window Frames spinning
        windowButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Random random = new Random();
                int num1 = random.nextInt(12) + 1;
                int num2 = random.nextInt(12) + 1;
                while (num2 == num1) {
                    num2 = random.nextInt(12) + 1;
                }
                System.out.println("Pierwsza: " + num1);
                System.out.println("Druga: " + num2);
                windowFrameBoards[0] = new WindowFrameBoard(num1 + "_" + "1.txt", mainColor);
                windowFrameBoards[1] = new WindowFrameBoard(num1 + "_" + "2.txt", mainColor);
                windowFrameBoards[2] = new WindowFrameBoard(num2 + "_" + "1.txt", mainColor);
                windowFrameBoards[3] = new WindowFrameBoard(num2 + "_" + "2.txt", mainColor);


                vBox.getChildren().remove(2);
                vBox.getChildren().remove(2);
                vBox.getChildren().add(windowFrameBoards[0].getGridPane());
                vBox.getChildren().add(windowFrameBoards[0].gethBox());
                windowButton.setDisable(true);
                arrowR.setVisible(true);
                arrowL.setVisible(true);
                confirmWindowButton.setVisible(true);
            }
        });


        // Confirm window button
        confirmWindowButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                confirmWindowButton.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Wybor wzoru witrazu");
                alert.setHeaderText("Czy na pewno chcesz wybrac te karte wzorow?");
                alert.setContentText("");

                ButtonType buttonYes = new ButtonType("Tak");
                ButtonType buttonNo = new ButtonType("Nie");
                alert.getButtonTypes().setAll(buttonYes, buttonNo);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == buttonYes) {
                    windowFrameBoardFinal = windowFrameBoards[windowCounter];
                    vBox.getChildren().remove(2);
                    vBox.getChildren().remove(2);
                    vBox.getChildren().addAll(windowFrameBoardFinal.getGridPane(), windowFrameBoardFinal.gethBox());
                    spinDicesHBox.setPrefHeight(hBoxButtons.getHeight());
                    spinDicesHBox.setPrefWidth(hBoxA.getWidth());
                    vBox.getChildren().remove(hBoxButtons);
                    arrowR.setVisible(false);
                    arrowL.setVisible(false);
                    vBox.getChildren().add(0, spinDicesHBox);
                } else if(result.get() == buttonNo){
                    confirmWindowButton.setDisable(false);
                }
            }


        });

        // Arrows for windows
        arrowR.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                windowCounter++;
                vBox.getChildren().remove(2);
                vBox.getChildren().remove(2);
                if (windowCounter == 4) {
                    windowCounter = 0;
                    vBox.getChildren().add(windowFrameBoards[windowCounter].getGridPane());
                    vBox.getChildren().add(windowFrameBoards[windowCounter].gethBox());
                } else {
                    vBox.getChildren().add(windowFrameBoards[windowCounter].getGridPane());
                    vBox.getChildren().add(windowFrameBoards[windowCounter].gethBox());
                }

            }
        });

        arrowL.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                windowCounter--;
                vBox.getChildren().remove(2);
                vBox.getChildren().remove(2);
                if (windowCounter == -1) {
                    windowCounter = 3;
                    vBox.getChildren().add(windowFrameBoards[windowCounter].getGridPane());
                    vBox.getChildren().add(windowFrameBoards[windowCounter].gethBox());
                } else {
                    vBox.getChildren().add(windowFrameBoards[windowCounter].getGridPane());
                    vBox.getChildren().add(windowFrameBoards[windowCounter].gethBox());
                }

            }
        });


        // Roll the dices button on click
        spinDicesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dice dices = new Dice();
                if (dices.getAllDicesNr() > 0 && roundCounter < 10) {
                    if (counter % 3 == 0 && counter != 0) {
                        hBoxA.getChildren().remove(0, 3);
                        for (int i = 0; i < 3; i++) {
                            Dice dice = rollDices();
                            hBoxA.getChildren().add(dice.getButton());
                            hBoxA.getChildren().get(i).setOnMouseEntered(mouseEn);
                            hBoxA.getChildren().get(i).setOnMouseExited(mouseEx);
                        }
                    } else if (counter == 0) {
                        for (int i = 0; i < 3; i++) {
                            Dice dice = rollDices();
                            hBoxA.getChildren().add(dice.getButton());
                            hBoxA.getChildren().get(i).setOnMouseEntered(mouseEn);
                            hBoxA.getChildren().get(i).setOnMouseExited(mouseEx);
                        }
                    }

                    counter += 3;
                    roundCounter++;
                    spinDicesButton.setText("Runda " + roundCounter);
                } else {
                    hBoxA.getChildren().remove(0, 3);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No more dices!");
                    alert.setContentText("There is no more dices to roll");
                    alert.showAndWait();
                    spinDicesButton.setVisible(false);
                    dices.showAllDices();
                }
            }

        });


        arrowR.setOnMouseEntered(mouseEn);
        arrowR.setOnMouseExited(mouseEx);
        arrowL.setOnMouseEntered(mouseEn);
        arrowL.setOnMouseExited(mouseEx);
        spinDicesButton.setOnMouseEntered(mouseEn);
        spinDicesButton.setOnMouseExited(mouseEx);
        spinColorButton.setOnMouseEntered(mouseEn);
        spinColorButton.setOnMouseExited(mouseEx);
        windowButton.setOnMouseEntered(mouseEn);
        windowButton.setOnMouseExited(mouseEx);
        confirmWindowButton.setOnMouseEntered(mouseEn);
        confirmWindowButton.setOnMouseExited(mouseEx);

    }

}
