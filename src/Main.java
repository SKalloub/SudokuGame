import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.management.GarbageCollectorMXBean;
import java.security.Key;

public class Main extends Application {
   public  Sudoku Game = new Sudoku();
    TextField[][] entries = new TextField[9][9];
    int currentLevel = 1;
    public static void Main(String[] args) {
 launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane Pane = new AnchorPane();
        MenuBar menuBar = new MenuBar();
        Menu GameMenu = new Menu("Game");
        Menu LevelMenu = new Menu("Level");
        Menu HelpMenu = new Menu("Help");
        MenuItem RestartItem = new MenuItem("Restart");
        MenuItem NewGameItem = new MenuItem("New Game");
        RestartItem.setOnAction(event -> {
            Game.Clear(entries);
        });
        NewGameItem.setOnAction(event -> {
            Game.Clear(entries);
        });

        MenuItem Levels[] = new MenuItem[4];
        for (int i = 1; i <= 4; i++) {
            Levels[i-1] = new MenuItem("Level "+i);
            currentLevel = i;
            Levels[i-1].setOnAction(event -> {
                try {
                    Game.NewGame(currentLevel,entries);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
        MenuItem HowToPlay = new MenuItem("How To Play");
        GameMenu.getItems().addAll(RestartItem,NewGameItem);
        for (int i = 0; i < 4; i++) {
            LevelMenu.getItems().add(Levels[i]);
            int t = i+1;
            Levels[i].setOnAction(event -> {
                try {
                    Game.NewGame(t,entries);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                currentLevel = t;
            });
        }
        HelpMenu.getItems().add(HowToPlay);

        menuBar.setLayoutX(0);
        menuBar.setLayoutY(0);
        menuBar.setPrefWidth(938);
        menuBar.setPrefHeight(26);
        menuBar.getMenus().addAll(GameMenu,LevelMenu,HelpMenu);
        Pane.getChildren().add(menuBar);

        Button Check = new Button("Check");
        Check.setFont(Font.font("Corbel", FontWeight.BOLD,20));
        Check.setTextFill(Paint.valueOf("#213d59"));
        Check.setLayoutX(805);
        Check.setLayoutY(191);
        Check.setPrefWidth(86);
        Check.setPrefHeight(29);
        Check.getStyleClass().add("Check");
        Pane.getChildren().add(Check);

        VBox GameControl = new VBox();
        Button Clear = new Button("Clear");
        Button NewGame = new Button("New Game");
        Button GiveUP = new Button("Give up");
        Button Save = new Button("Save");
        Button Exit = new Button("Exit");

        Clear.getStyleClass().add("buttonControl");
        NewGame.getStyleClass().add("buttonControl");
        GiveUP.getStyleClass().add("buttonControl");
        Save.getStyleClass().add("buttonControl");
        Exit.getStyleClass().add("buttonControl");

        Clear.setPrefWidth(148);
        Clear.setPrefHeight(64);
        NewGame.setPrefWidth(148);
        NewGame.setPrefHeight(64);
        GiveUP.setPrefWidth(148);
        GiveUP.setPrefHeight(64);
        Save.setPrefWidth(148);
        Save.setPrefHeight(64);
        Exit.setPrefWidth(148);
        Exit.setPrefHeight(64);

        Clear.setTextFill(Paint.valueOf("#fffca7"));
        NewGame.setTextFill(Paint.valueOf("#fffca7"));
        GiveUP.setTextFill(Paint.valueOf("#fffca7"));
        Save.setTextFill(Paint.valueOf("#fffca7"));
        Exit.setTextFill(Paint.valueOf("#fffca7"));

        Clear.setFont(Font.font("Corbel",FontWeight.BOLD,24));
        NewGame.setFont(Font.font("Corbel",FontWeight.BOLD,24));
        GiveUP.setFont(Font.font("Corbel",FontWeight.BOLD,24));
        Save.setFont(Font.font("Corbel",FontWeight.BOLD,24));
        Exit.setFont(Font.font("Corbel",FontWeight.BOLD,24));

        GameControl.setLayoutX(774);
        GameControl.setLayoutY(241);
        GameControl.getChildren().addAll(Clear,NewGame,GiveUP,Save,Exit);
        Pane.getChildren().add(GameControl);

        NewGame.setOnAction(event -> {
            try {
                Game.NewGame(currentLevel,entries);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        GiveUP.setOnAction(event -> {
            Game.GiveUp(entries);
        });
        Check.setOnAction(event -> {
            System.out.println(check(entries));
            try {
                Game.Check(entries);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Clear.setOnAction(event -> {Game.Clear(entries);});
        Exit.setOnAction(event -> {primaryStage.close();});




        AnchorPane GameBoard = new AnchorPane();
        GameBoard.setStyle("");
        GameBoard.setLayoutX(9);
        GameBoard.setLayoutY(42);
        GameBoard.setPrefWidth(743);
        GameBoard.setPrefHeight(606);

        GridPane Boxes = new GridPane();
        Boxes.setLayoutX(15);
        Boxes.setLayoutY(15);
        Boxes.setPrefWidth(713);
        Boxes.setPrefHeight(577);
        Boxes.setGridLinesVisible(true);
        GameBoard.getChildren().add(Boxes);
        Pane.getChildren().add(GameBoard);
        GridPane[][] grids = new GridPane[3][3];
        Boxes.setStyle("-fx-border-color: #213d59; -fx-border-width: 2px;");
        for (int i = 0; i < 3; i++) {
            grids[i] = new GridPane[3];
            for (int j = 0; j < 3; j++) {
                grids[i][j] = new GridPane();
                Boxes.add(grids[i][j],j,i);
            }
        }
        for (int i = 0; i < 9; i++) {
            entries[i] = new TextField[9];
            for (int j = 0; j < 9; j++) {

                entries[i][j] = new TextField();
                entries[i][j].getStyleClass().add("tf");
                entries[i][j].setAlignment(Pos.CENTER);
                entries[i][j].setFont(Font.font("Cambria",29));
                entries[i][j].setStyle("-fx-text-inner-color: #213d59;");

                if (j%3==2)
                entries[i][j].getStyleClass().add("tf1");
                if (i%3==2)
                    entries[i][j].getStyleClass().add("tf2");
                if (j%3==2 && i%3==2)
                    entries[i][j].getStyleClass().add("tf3");


                    entries[i][j].setPrefWidth(80);
                entries[i][j].setPrefHeight(65);
                grids[i/3][j/3].add(entries[i][j],j%3,i%3);
            }
        }
        Game.NewGame(2,entries);

/*
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int x = i;
                int x2 = j;
                entries[i][j].addEventHandler(KeyEvent.KEY_PRESSED,event -> {
                    if (!check(entries)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Wrong input");
                        alert.show();
                        entries[x][x2].setText("");
                        for (int k = 0; k < 9; k++) {
                            for (int k2 = 0; k2 < 9; k2++) {
                                if (k==x && k2==x2)
                                    continue;
                                else
                                    entries[k][k2].setEditable(false);
                            }
                        }
                    }
                    else {
                        for (int k = 0; k < 9; k++) {
                            for (int k2 = 0; k2 < 9; k2++) {
                                if (k==x && k2==x2)
                                    continue;
                                else
                                    entries[k][k2].setEditable(true);
                            }
                        }
                    }
                });
            }
        }
*/
        Scene scene  = new Scene(Pane,938,662);
        scene.getStylesheets().add("Styling.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Sudoku");

        primaryStage.show();




    }

    private boolean check(TextField[][] entries) {
        int grid[][] = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!entries[i][j].getText().equals(""))
                grid[i][j] = Integer.parseInt(entries[i][j].getText());
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j]!=0 && !Sudoku.Check(grid,i,j,grid[i][j]))
                    return false;
            }
        }
        return true;
    }
}
