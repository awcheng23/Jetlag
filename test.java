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
    HBox row1 = new HBox(20);
    HBox row2 = new HBox(20);
    row1.setPadding(new Insets(20));
    row2.setPadding(new Insets(20));

    //labels
    Label label1 = new Label("Departure Airport");
    Label label2 = new Label("Departure Time");
    Label label3 = new Label("Arrival Airport");   

    //search boxes
    TextField departureAirport = new TextField("Departure Airport");
    TextField departureTime = new TextField("Departure Time");
    TextField arrivalAirport = new TextField("Arrival Airport");

    Button submitButton = new Button("Find");
    
    row1.setAlignment(Pos.TOP_LEFT);
    row1.getChildren().addAll(label1,label2,label3);
    row2.getChildren().addAll(departureAirport, departureTime, arrivalAirport,submitButton);

    //labels
    HBox row3 = new HBox(10);
    row3.setPadding(new Insets(10));

    HBox row4 = new HBox(20);
    row4.setPadding(new Insets(20));

    HBox row5 = new HBox(10);
    row5.setPadding(new Insets(10));

    HBox row6 = new HBox(20);
    row6.setPadding(new Insets(10));

    Label label4 = new Label("Departure Airport");
    Label label5 = new Label("Layover Airport");
    Label label6 = new Label("Final Destination Airport");

    Label departureAirportOutput = new Label();
    Label layoverAirportOutput = new Label();
    Label arrivalAirportOutput = new Label();

    Label label7 = new Label("Departure Time");
    Label label8 = new Label("Layover Time");
    Label label9 = new Label("Final Destination Arrival Time"); 
    Label label10 = new Label("Price");
    
    Label departureTimeOutput = new Label();
    Label layoverTimeOutput = new Label();
    Label arrivalTimeOutput = new Label();
    Label priceOutput = new Label();

    Button newFlight = new Button("Find New Flight");

    submitButton.setOnAction(e -> {
      departureAirportOutput.setText(departureAirport.getText());
      layoverAirportOutput.setText(departureAirport.getText());
      arrivalAirportOutput.setText(arrivalAirport.getText());
      departureTimeOutput.setText("8:00 pm");
      layoverTimeOutput.setText("9:00 am");
      arrivalTimeOutput.setText("10:00 pm");
      priceOutput.setText("$4040");
    });

    newFlight.setOnAction(e -> {
      departureAirportOutput.setText("");
      layoverAirportOutput.setText("");
      arrivalAirportOutput.setText("");
      departureTimeOutput.setText("");
      layoverTimeOutput.setText("");
      arrivalTimeOutput.setText("");
      priceOutput.setText("");
    });

    row3.setAlignment(Pos.TOP_LEFT);
    row3.getChildren().addAll(label4,label5,label6);

    row4.setAlignment(Pos.TOP_LEFT);
    row4.getChildren().addAll(departureAirportOutput,layoverAirportOutput,arrivalAirportOutput);

    row5.setAlignment(Pos.TOP_LEFT);
    row5.getChildren().addAll(label7,label8,label9,label10,newFlight);

    row6.setAlignment(Pos.TOP_LEFT);
    row6.getChildren().addAll(departureTimeOutput,layoverTimeOutput,arrivalTimeOutput,priceOutput);


     // define scenes and containers
    VBox layout = new VBox(5);
    layout.setAlignment(Pos.TOP_CENTER);
    layout.getChildren().addAll(row1,row2,row3,row4,row5,row6);

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
