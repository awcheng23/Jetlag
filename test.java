import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.LabelSkin;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;

public class test extends Application {
  @Override
  public void start(Stage primaryStage) {
    GridPane input = new GridPane();
    input.setPadding(new Insets(20));
    input.setHgap(50);
    input.setVgap(10);

    //labels
    Label label1 = new Label("Departure Airport");
    Label label2 = new Label("Departure Time");
    Label label3 = new Label("Arrival Airport");   

    //search boxes
    TextField departureAirport = new TextField("Departure Airport");
    TextField departureTime = new TextField("Departure Time");
    TextField arrivalAirport = new TextField("Arrival Airport");

    Button submitButton = new Button("Find");
    
    input.setAlignment(Pos.TOP_LEFT);
    input.add(label1,0,0);
    input.add(label2,1,0);
    input.add(label3,2,0);
    input.add(departureAirport,0,1);
    input.add(departureTime,1,1);
    input.add(arrivalAirport,2,1);
    input.add(submitButton,3,1);

    //labels
    GridPane output = new GridPane();
    output.setPadding(new Insets(20));
    output.setHgap(50);
    output.setVgap(10);

    Label label4 = new Label("Departure Airport");
    Label label5 = new Label("Layover Airport");
    Label label6 = new Label("Final Destination Airport");

    Label departureAirportOutput = new Label();
    Label layoverAirportOutput = new Label();
    Label arrivalAirportOutput = new Label();

    Label label7 = new Label("Departure Time");
    Label label8 = new Label("Layover Time");
    Label label9 = new Label("Final Destination Arrival Time"); 

    Label departureTimeOutput = new Label();
    Label layoverTimeOutput = new Label();
    Label arrivalTimeOutput = new Label();

    Label label10 = new Label("Price");
    Label label11 = new Label("Class");

    Label priceOutput = new Label();
    Label classOutput = new Label();

    submitButton.setOnAction(e -> {
      departureAirportOutput.setText(departureAirport.getText());
      layoverAirportOutput.setText(departureAirport.getText());
      arrivalAirportOutput.setText(arrivalAirport.getText());
      departureTimeOutput.setText("8:00 pm");
      layoverTimeOutput.setText("9:00 am");
      arrivalTimeOutput.setText("10:00 pm");
      priceOutput.setText("$4040");
      classOutput.setText("Economy");
    });

    output.setAlignment(Pos.TOP_LEFT);
    output.add(label4,0,0);
    output.add(label5,1,0);
    output.add(label6,2,0);
    output.add(departureAirportOutput,0,1);
    output.add(layoverAirportOutput,1,1);
    output.add(arrivalAirportOutput,2,1);
    output.add(label7,0,2);
    output.add(label8,1,2);
    output.add(label9,2,2);
    output.add(departureTimeOutput,0,3);
    output.add(layoverTimeOutput,1,3);
    output.add(arrivalTimeOutput,2,3);
    output.add(label10,0,4);
    output.add(label11,1,4);
    output.add(priceOutput,0,5);
    output.add(classOutput,1,5);

     // define scenes and containers
    VBox layout = new VBox(20);
    layout = new VBox(20);
    layout.setAlignment(Pos.TOP_CENTER);
    layout.getChildren().addAll(input,output);

    Scene scene = new Scene(layout, 800, 600);
    // show first scene
    primaryStage.setTitle("Jetlag");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
