import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import java.util.*;

/**
 * Jetlag Application
 *
 * @author May Ming, Alice Cheng
 * @version 0.0.1
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {

    FlightData data = new FlightData("data/flight.csv");
    Set<String> departures = data.getDepartures();
    Set<String> arrivals = data.getArrivals();

    Label name = new Label();
    name.setText("Jetlag");
    name.setFont(Font.font("Century", 45));

    Image gif = new Image("graphics/plane.gif");
    ImageView gv = new ImageView(gif);
    gv.setPreserveRatio(true);
    gv.setFitWidth(100);

    HBox titleBox = new HBox(30);
    titleBox.getChildren().addAll(name, gv);
    titleBox.setAlignment(Pos.CENTER);

    Image backgroundImage = new Image("graphics/cloud-bg.jpg");
    BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, 
                                                     new BackgroundSize(800, 600, true, true, true, true));
    
    VBox vBox = new VBox(20);
    vBox.setBackground(new Background(background));
    vBox.getChildren().addAll(titleBox);
    vBox.setAlignment(Pos.CENTER);
    
    Scene scene = new Scene(vBox, 800, 600);
    // scene.getStylesheets().add("graphics/style.css");

    primaryStage.setTitle("Jetlag");
    primaryStage.setMaximized(true);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
