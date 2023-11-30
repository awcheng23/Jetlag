import java.io.FileInputStream;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
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

    HBox row1 = new HBox(20);
    HBox row2 = new HBox(20);
    //row1.setPadding(new Insets(20));
    //row2.setPadding(new Insets(20));

     //labels
    Label label1 = new Label("Departure Airport");
    Label label2 = new Label("Departure Time");
    Label label3 = new Label("Arrival Airport");   

    // Search boxes
    // Create a Label for prompt text
  Label promptLabel = new Label("Enter Departure Airport");
  promptLabel.setStyle("-fx-text-fill: gray;");

  // Use TextFormatter to show/hide prompt text
  TextFormatter<String> disappearText = new TextFormatter<>(change -> {
      if (change.getControlNewText().isEmpty()) {
          promptLabel.setVisible(true);
      } else {
          promptLabel.setVisible(false);
      }
      return change;
  });

  ObservableList<String> departuresList = FXCollections.observableArrayList(departures);
  ComboBox<String> departCBox = new ComboBox<>(departuresList);
  departCBox.setEditable(true);
  TextField departEditor = departCBox.getEditor();

  departEditor.setTextFormatter(disappearText);

  // StackPane to overlay ComboBox with promptLabel
  StackPane stackPane = new StackPane(departCBox, promptLabel);
  StackPane.setMargin(promptLabel, new javafx.geometry.Insets(0, 0, 0, 8));
  stackPane.setAlignment(Pos.CENTER_LEFT);
  departEditor.textProperty().addListener((observable, oldValue, newValue) -> {
      // Filter the choices based on the entered text
      ObservableList<String> filteredList = FXCollections.observableArrayList();
      departuresList.stream()
              .filter(choice -> choice.toLowerCase().contains(newValue.toLowerCase()))
              .forEach(filteredList::add);

      // Update the items in the ComboBox
      departCBox.setItems(filteredList);
      departCBox.show();
  });

    ObservableList<String> arrivalsList = FXCollections.observableArrayList(arrivals);
    ComboBox<String> arriveCBox = new ComboBox<>(arrivalsList);
    arriveCBox.setEditable(true);

    TextField arriveEditor = arriveCBox.getEditor();
    arriveEditor.setPromptText("Enter Arrival Airport");
    arriveEditor.textProperty().addListener((observable, oldValue, newValue) -> {
        // Filter the choices based on the entered text
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        arrivalsList.stream()
                .filter(choice -> choice.toLowerCase().contains(newValue.toLowerCase()))
                .forEach(filteredList::add);

        // Update the items in the ComboBox
        arriveCBox.setItems(filteredList);
        arriveCBox.show();
    });

    // TextField departureAirport = new TextField("Departure Airport");
    // TextField departureTime = new TextField("Departure Time");
    // TextField arrivalAirport = new TextField("Arrival Airport");

    Button submitButton = new Button("Find");
    
    
    //row1.getChildren().addAll(label1,label2,label3);
    row2.getChildren().addAll(stackPane, arriveCBox ,submitButton);
   // row1.setAlignment(Pos.CENTER);
    row2.setAlignment(Pos.CENTER);

    //labels
    HBox row3 = new HBox(10);
    //row3.setPadding(new Insets(10));

    HBox row4 = new HBox(20);
    //row4.setPadding(new Insets(20));

    HBox row5 = new HBox(10);
    //row5.setPadding(new Insets(10));

    HBox row6 = new HBox(20);
    //row6.setPadding(new Insets(10));

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
      departureAirportOutput.setText(departCBox.getValue());
      layoverAirportOutput.setText(departCBox.getValue());
      arrivalAirportOutput.setText(arriveCBox.getValue());
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

    
    row3.getChildren().addAll(label4,label5,label6);
    row3.setAlignment(Pos.CENTER);
    
    row4.getChildren().addAll(departureAirportOutput,layoverAirportOutput,arrivalAirportOutput);
    row4.setAlignment(Pos.CENTER);
    
    row5.getChildren().addAll(label7,label8,label9,label10,newFlight);
    row5.setAlignment(Pos.CENTER);
    
    row6.getChildren().addAll(departureTimeOutput,layoverTimeOutput,arrivalTimeOutput,priceOutput);
    row6.setAlignment(Pos.CENTER);

    Image backgroundImage = new Image("graphics/cloud-bg.jpg");
    BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, 
                                                     new BackgroundSize(800, 600, true, true, true, true));
    VBox vBox = new VBox(20);
    vBox.setBackground(new Background(background));
    vBox.getChildren().addAll(titleBox, row2,row3,row4,row5,row6);
    vBox.setAlignment(Pos.CENTER);
    
    Scene scene = new Scene(vBox, 800, 600);
    // scene.getStylesheets().add("graphics/style.css");

    primaryStage.setTitle("Jetlag");
    primaryStage.setMaximized(true);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  // private StackPane createSearchBox(Collection<String> list, String label) {
  //     Label promptLabel = new Label(label);
  //     promptLabel.setStyle("-fx-text-fill: gray;");
  //     TextFormatter<String> disappearText = new TextFormatter<>(change -> { // Hide text when typing
  //     if (change.getControlNewText().isEmpty()) {
  //         promptLabel.setVisible(true);
  //     } else {
  //         promptLabel.setVisible(false);
  //     }
  //     return change;

  //     //departEditor.setTextFormatter(disappearText);

  //     // StackPane to overlay ComboBox with promptLabel
  //     //StackPane stackPane = new StackPane(departCBox, promptLabel);
  //     //StackPane.setMargin(promptLabel, new javafx.geometry.Insets(0, 0, 0, 8));
  //     //stackPane.setAlignment(Pos.CENTER_LEFT);
  // });

  // }

  private ComboBox<String> createComboBox(Collection<String> list, String label) {
      ObservableList<String> observeList = FXCollections.observableArrayList(list);
      ComboBox<String> comboBox = new ComboBox<>(observeList);
      comboBox.setEditable(true);
      TextField editor = comboBox.getEditor();

      editor.textProperty().addListener((observable, oldValue, newValue) -> {
          // Filter the choices based on the entered text
          ObservableList<String> filteredList = FXCollections.observableArrayList();
          observeList.stream()
                  .filter(choice -> choice.toLowerCase().contains(newValue.toLowerCase()))
                  .forEach(filteredList::add);

          // Update the items in the ComboBox
          comboBox.setItems(filteredList);
          comboBox.show();
      });

      return comboBox;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
