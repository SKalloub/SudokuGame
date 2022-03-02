import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class WinStage {
    AnchorPane Pane = new AnchorPane();
    Rectangle rectangle = new Rectangle();
    Label label = new Label("Winner Winner Chicken Dinner!");
    ImageView image1 = new ImageView(new Image(new FileInputStream("congratulations.png")));
    ImageView image2 = new ImageView(new Image(new FileInputStream("sudoku.png")));
    Label label2 = new Label("You won the game!");
    Button button = new Button("Close");

    public WinStage() throws FileNotFoundException {
        Stage stage = new Stage();
        rectangle.setFill(Paint.valueOf("#b7aa53"));
        rectangle.setStrokeWidth(0);
        rectangle.setWidth(776);
        rectangle.setHeight(53);
        rectangle.setLayoutX(0);
        rectangle.setLayoutY(0);
        label.setLayoutX(199);
        label.setLayoutY(11);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Cambria", FontWeight.BOLD,26));
        label2.setLayoutX(242);
        label2.setLayoutY(93);
        label2.setTextFill(Paint.valueOf("#b22359"));
        label2.setFont(Font.font("Constantia",36));
        button.setOnAction(event -> {stage.close();});
        button.setFont(Font.font("Calibri",23));
        button.setStyle("-fx-background-color:  #56210b;-fx-background-radius: 40px;");
        button.setTextFill(Color.WHITE);
        button.setLayoutX(267);
        button.setLayoutY(163);
        button.setPrefWidth(249);
        button.setPrefHeight(43);
        image1.setFitWidth(109);
        image1.setFitHeight(97);
        image1.setLayoutX(96);
        image1.setLayoutY(124);
        Pane.getChildren().addAll(rectangle,label,label2,button,image1);
        stage.setScene(new Scene(Pane,776,229));
        stage.show();

    }
}
