import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

/**
 * Jetlag Application
 *
 * @author May Ming, Alice Cheng
 * @version 0.0.1
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {

    Label name = new Label();
    name.setText("Jetlag");
    name.setFont(Font.font("Lucida Bright", 45));

    Image backgroundImage = new Image("graphics/cloud-bg.jpg");
    BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, 
                                                     new BackgroundSize(800, 600, true, true, true, true));
   
    

    VBox vBox = new VBox(20);
    vBox.setBackground(new Background(background));
    vBox.getChildren().addAll(name);
    vBox.setAlignment(Pos.CENTER);
    
    Scene scene = new Scene(vBox, 800, 600);

    primaryStage.setTitle("Jetlag");
    primaryStage.setMaximized(true);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
