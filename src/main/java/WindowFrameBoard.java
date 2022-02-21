import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.Scanner;

public class WindowFrameBoard extends Dice {
    private Dice[][] dice;
    private GridPane gridPane;
    private HBox hBox;
    private Color mainColor;
    private Button colorButton;
    private int favorTokens;
    private String name;


    // Default board
    public WindowFrameBoard() {
        this.dice = new Dice[4][5];
        this.gridPane = new GridPane();
        this.gridPane.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 3;");

        this.colorButton = new Button();
        this.colorButton.setPrefSize(30, 30);
        this.colorButton.setStyle("-fx-border-color: black;");
        this.colorButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                dice[i][j] = new Dice();

                gridPane.add(dice[i][j].getButton(), j, i);
                dice[i][j].getButton().setPrefSize(75, 75);
                dice[i][j].getButton().setStyle("-fx-border-color: black;" +
                        "-fx-border-width: 3;");

                String userDirectory = System.getProperty("user.dir");
                Image img = new Image("DEFAULT/d.png");
                dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("file:///" + userDirectory + "\\src\\main\\resources\\" + "DEFAULT/d.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            }
        }

        // Space
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        // HBox under GridPane
        this.hBox = new HBox();
        this.hBox.setStyle("-fx-border-width: 3;" +
                "-fx-border-color: black;");
        this.hBox.setPadding(new Insets(10, 10, 10, 20));
        this.hBox.setAlignment(Pos.CENTER_LEFT);
        this.hBox.getChildren().addAll(colorButton, region1);
        this.hBox.setSpacing(10);
        this.hBox.setMaxWidth(381);
    }

    // Board loaded
    public WindowFrameBoard(String fileName, Color mainColor) {
        try {
            this.gridPane = new GridPane();
            this.hBox = new HBox();
            this.colorButton = new Button();
            this.dice = new Dice[4][5];

            // GridPane
            this.gridPane.setStyle("-fx-border-color: black;" +
                    "-fx-border-width: 3;");

            // Button
            this.colorButton.setPrefSize(30, 30);
            this.colorButton.setStyle("-fx-border-color: black;");
            this.colorButton.setBackground(new Background(new BackgroundFill(mainColor, CornerRadii.EMPTY, Insets.EMPTY)));

            // Space
            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);

            // HBox under GridPane
            this.hBox = new HBox();
            this.hBox.setStyle("-fx-border-width: 3;" +
                    "-fx-border-color: black;");
            this.hBox.setPadding(new Insets(10, 10, 10, 20));
            this.hBox.setAlignment(Pos.CENTER_LEFT);
            this.hBox.getChildren().addAll(this.colorButton, region1);
            this.hBox.setSpacing(10);
            this.hBox.setMaxWidth(381);

            String line = new String();
            FileReader fileReader = new FileReader("E:/SagradaInts/src/main/resources/WindowFrames/" + fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int i = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.charAt(0) != 'X') {
                    for (int j = 0; j < 5; j++) {
                        dice[i][j] = new Dice();
                        dice[i][j].getButton().setPrefSize(75, 75);
                        dice[i][j].getButton().setStyle("-fx-border-color: black;" +
                                "-fx-border-width: 3;");
                        if (line != null) {
                            switch (line.charAt(j)) {
                                case '1':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_1.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case '2':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_2.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case '3':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_3.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case '4':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_4.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case '5':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_5.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case '6':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d_6.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case 'W':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("DEFAULT/d.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case 'R':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("RED/red.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case 'B':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("BLUE/blue.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case 'Y':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("YELLOW/yellow.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case 'G':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("GREEN/green.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                case 'P':
                                    gridPane.add(dice[i][j].getButton(), j, i);
                                    dice[i][j].getButton().setBackground(new Background(new BackgroundImage(new Image("PURPLE/purple.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                                    break;
                                default:
                                    System.out.println("error");
                                    break;

                            }

                        }
                    }
                    i++;
                } else {
                    this.favorTokens = Character.getNumericValue(line.charAt(1));
                    System.out.println("Favor tokens" + this.favorTokens);
                }
            }

            // adding tokens to hBox
            for (int g = 0; g < favorTokens; g++) {
                Image[] image = new Image[favorTokens];
                ImageView[] imageView = new ImageView[favorTokens];
                image[g] = new Image("cut-diamond.png");
                imageView[g] = new ImageView(image[g]);
                hBox.getChildren().add(imageView[g]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int getFavorTokens() {
        return this.favorTokens;
    }

    public HBox gethBox() {
        return this.hBox;
    }

    public GridPane getGridPane() {
        return this.gridPane;
    }

    public Button getColorButton() {
        return colorButton;
    }
}
