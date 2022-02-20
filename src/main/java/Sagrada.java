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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class Sagrada extends Application {
    // Launch app
    public static void main(String[] args) {
        launch(args);
    }

    static int counter;
    static int roundCounter;

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

    // Spin the dice number
    public static int spinNumber() {
        int min = 1;
        int max = 6;
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return random;
    }

    // Load the board from file
    public static void loadBoard(GridPane gridPane, String fileName, Button[][] buttons) {
        try {
            String line = new String();
            String userDirectory = System.getProperty("user.dir");
            File file = new File(userDirectory + "\\src\\main\\resources\\" + fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;

            while ((line = bufferedReader.readLine()) != null) {
                for (int j = 0; j < 5; j++) {
                    if (line != null) {
                        switch (line.charAt(j)) {
                            case '1':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_1.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case '2':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_2.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case '3':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_3.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case '4':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_4.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case '5':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_5.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case '6':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_6.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case 'W':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case 'R':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("RED/red.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case 'B':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("BLUE/blue.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case 'Y':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("YELLOW/yellow.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case 'G':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("GREEN/green.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            case 'P':
                                buttons[i][j].setBackground(new Background(new BackgroundImage(new Image("PURPLE/purple.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                break;
                            default:
                                System.out.println("error");
                                break;

                        }

                    }
                }
                i++;
            }

        } catch (IOException e) {
            System.out.println("ERROR");
        }


    }

    @Override
    public void start(Stage primaryStage) {


        //Images
        Image[] image = new Image[5];
        ImageView[] imageView = new ImageView[5];

        for (int i = 0; i < 5; i++) {
            image[i] = new Image("cut-diamond.png");
            imageView[i] = new ImageView(image[i]);
        }

        // Buttons
        // for board
        Button[][] buttons = new Button[4][5];

        // for color spinning
        Button spinColorButton = new Button("Wylosuj kolor");
        spinColorButton.setPrefSize(100, 10);

        // for color
        Button colorButton = new Button();
        colorButton.setPrefSize(30, 30);
        colorButton.setStyle("-fx-border-color: black;");
        colorButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        // for spinning dices
        Button spinDicesButton = new Button("Start");
        spinDicesButton.setLayoutX(50);
        spinDicesButton.setLayoutY(150);

        // HBox round track, above gridPane and another HBox
        HBox roundTrack = new HBox();
        roundTrack.setSpacing(10);
        Button[] buttonsForRounds = new Button[10];
        String userDirectory = System.getProperty("user.dir");
        for (int i = 0; i < 10; i++) {
            buttonsForRounds[i] = new Button();
            buttonsForRounds[i].setPrefSize(75, 75);
            buttonsForRounds[i].setBackground(new Background(new BackgroundImage(new Image("file:///" + userDirectory + "\\src\\main\\resources\\" + "NUMBERS/" + (i+1)+".png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            roundTrack.getChildren().add(buttonsForRounds[i]);
        }

        // HBox above GridPane
        HBox hBoxA = new HBox();
        hBoxA.setStyle("-fx-border-width: 3;" +
                "-fx-border-color: black;");
        hBoxA.setPrefSize(250, 100);
        hBoxA.setSpacing(20);
        hBoxA.setAlignment(Pos.CENTER);
        hBoxA.setMaxWidth(381);

        // Region for trick
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        // HBox under GridPane
        HBox hBox = new HBox();
        hBox.setStyle("-fx-border-width: 3;" +
                "-fx-border-color: black;");
        hBox.setPadding(new Insets(10, 20, 10, 20));
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(colorButton, region1, imageView[0], imageView[1], imageView[2], imageView[3], imageView[4]);
        hBox.setSpacing(10);
        hBox.setMaxWidth(381);

        // GridPane
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(381);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new Button();
                gridPane.add(buttons[i][j], j, i);
                buttons[i][j].setPrefSize(75, 75);
                buttons[i][j].setStyle("-fx-border-color: black;" +
                        "-fx-border-width: 3;");
            }
        }
        gridPane.setHgap(0.49);
        gridPane.setVgap(0.49);
        gridPane.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 3");

        loadBoard(gridPane, "board_1.txt", buttons);

        // VBox
        VBox vBox = new VBox();
        vBox.setLayoutX(50);
        vBox.setLayoutY(100);
        vBox.getChildren().add(roundTrack);
        vBox.getChildren().add(spinDicesButton);
        vBox.getChildren().add(hBoxA);
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(spinColorButton);
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
        primaryStage.setResizable(false);
        primaryStage.show();

        // Listeners
        spinColorButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                colorButton.setBackground(new Background(new BackgroundFill(spinColor(), CornerRadii.EMPTY, Insets.EMPTY)));
                spinColorButton.setVisible(false);
            }
        });


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

        spinDicesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dice dices = new Dice();
                if (dices.getAllDicesNr() > 0) {
                    if (counter % 3 == 0 && counter != 0) {
                        hBoxA.getChildren().remove(0, 3);
                        for (int i = 0; i < 3; i++) {
                            Dice dice = rollDices();
                            hBoxA.getChildren().add(dice.loadDice());
                            hBoxA.getChildren().get(i).setOnMouseEntered(mouseEn);
                            hBoxA.getChildren().get(i).setOnMouseExited(mouseEx);
                        }
                    } else if (counter == 0) {
                        for (int i = 0; i < 3; i++) {
                            Dice dice = rollDices();
                            hBoxA.getChildren().add(dice.loadDice());
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


        spinDicesButton.setOnMouseEntered(mouseEn);
        spinDicesButton.setOnMouseExited(mouseEx);
        spinColorButton.setOnMouseEntered(mouseEn);
        spinColorButton.setOnMouseExited(mouseEx);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setOnMouseEntered(mouseEn);
                buttons[i][j].setOnMouseExited(mouseEx);
            }
        }

    }
}
