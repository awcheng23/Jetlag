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
import javafx.scene.layout.GridPane;
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
    List<String[]> flights = data.getFlights();
    Set<String> departures = data.getDepartures();
    Set<String> arrivals = data.getArrivals();

    Label name = new Label();
    name.setText("Jetlag");
    name.setFont(Font.font("Century", 50));

    Image gif = new Image("graphics/plane.gif");
    ImageView gv = new ImageView(gif);
    gv.setPreserveRatio(true);
    gv.setFitWidth(100);

    HBox titleBox = new HBox(30);
    titleBox.getChildren().addAll(name, gv);
    titleBox.setAlignment(Pos.CENTER);

    HBox searchRow = new HBox(30);

    // Search boxes
    ComboBox<String> departCBox = createSearchBox(departures);
    StackPane departPane = addPromptComboBox(departCBox, "Enter Departure Airport");

    ComboBox<String> arriveCBox = createSearchBox(arrivals);
    StackPane arrivePane = addPromptComboBox(arriveCBox, "Enter Arrival Airport");

    Button submitButton = new Button("Find");

    searchRow.getChildren().addAll(departPane, arrivePane, submitButton);
    searchRow.setAlignment(Pos.CENTER);

    /*********************************************************************************/

    // labels
    HBox row3 = new HBox(10);
    // row3.setPadding(new Insets(10));

    HBox row4 = new HBox(20);
    // row4.setPadding(new Insets(20));

    HBox row5 = new HBox(10);
    // row5.setPadding(new Insets(10));

    HBox row6 = new HBox(20);
    // row6.setPadding(new Insets(10));

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

    Button clear = new Button("Clear");

    submitButton.setOnAction(e -> {

      if(!departures.contains(departCBox.getValue()) || !arrivals.contains(arriveCBox.getValue())) {
        // they have not inputted valid airports
      } 
      else {
        displayFlight(flights, data.cheapestRoute(departCBox.getValue(), arriveCBox.getValue()));
      }
      
      // departureAirportOutput.setText(departCBox.getValue());
      // layoverAirportOutput.setText(departCBox.getValue());
      // arrivalAirportOutput.setText(arriveCBox.getValue());
      // departureTimeOutput.setText("8:00 pm");
      // layoverTimeOutput.setText("9:00 am");
      // arrivalTimeOutput.setText("10:00 pm");
      // priceOutput.setText("$4040");
    });

    clear.setOnAction(e -> {
      departureAirportOutput.setText("");
      layoverAirportOutput.setText("");
      arrivalAirportOutput.setText("");
      departureTimeOutput.setText("");
      layoverTimeOutput.setText("");
      arrivalTimeOutput.setText("");
      priceOutput.setText("");
    });

    // row3.getChildren().addAll(label4, label5, label6);
    // row3.setAlignment(Pos.CENTER);

    // row4.getChildren().addAll(departureAirportOutput, layoverAirportOutput, arrivalAirportOutput);
    // row4.setAlignment(Pos.CENTER);

    // row5.getChildren().addAll(label7, label8, label9, label10);
    // row5.setAlignment(Pos.CENTER);

    // row6.getChildren().addAll(departureTimeOutput, layoverTimeOutput, arrivalTimeOutput, priceOutput);
    // row6.setAlignment(Pos.CENTER);

    /******************************************************************************************/

    Image backgroundImage = new Image("graphics/cloud-bg.jpg");
    BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null,
        new BackgroundSize(800, 600, true, true, true, true));
    VBox vBox = new VBox(20);
    vBox.setBackground(new Background(background));
    vBox.getChildren().addAll(titleBox, searchRow);
    vBox.setAlignment(Pos.CENTER);

    Scene scene = new Scene(vBox, 800, 600);
    scene.getStylesheets().add("graphics/style.css");

    primaryStage.setTitle("Jetlag");
    primaryStage.setMaximized(true);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private GridPane displayFlight(List<String[]> flights, String[] info) {
    /*
    info[0] is the cost
    info[1] are the airports to stop at delimited by commas (e.g. JFK,ADD,DEH)
    info[2] are the indices indicating which flight each leg is (e.g. 3,500)
      - this means JFK > ADD is from flights[3] and ADD > DEH is from flights[500] 
     */

    String[] ports = info[1].split(",");
    String[] refs = info[2].split(",");

    int[] flightCode = new int[refs.length];
    for(int i=0; i<refs.length; i++)
      flightCode[i] = Integer.parseInt(refs[i].trim());

      
    return new GridPane();
  }

  private StackPane addPromptComboBox(ComboBox<String> comboBox, String label) {
    Label promptLabel = new Label(label);
    promptLabel.setStyle("-fx-text-fill: gray;");
    promptLabel.setOnMouseClicked(event -> {
      comboBox.show();
      comboBox.requestFocus();
    });

    TextFormatter<String> disappearText = new TextFormatter<>(change -> {
        if (change.getControlNewText().isEmpty()) {
            promptLabel.setVisible(true);
        } else {
            promptLabel.setVisible(false);
        }
        return change;
    });

    comboBox.getEditor().setTextFormatter(disappearText);

    // Overlay ComboBox with promptLabel
    StackPane stackPane = new StackPane(comboBox, promptLabel);
    StackPane.setMargin(promptLabel, new javafx.geometry.Insets(0, 0, 0, 10));
    stackPane.setAlignment(Pos.CENTER_LEFT);

    return stackPane;
}

private ComboBox<String> createSearchBox(Collection<String> list) {
    ObservableList<String> observeList = FXCollections.observableArrayList(list);
    FXCollections.sort(observeList); // Sort airports alphabetically
    
    ComboBox<String> comboBox = new ComboBox<>(observeList);
    comboBox.setEditable(true);
    
    TextField editor = comboBox.getEditor();
    editor.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()) {
          comboBox.setItems(observeList);
          comboBox.show();
      } else {
          // Filter the choices based on the entered text
          ObservableList<String> filteredList = FXCollections.observableArrayList();
          observeList.stream()
                  .filter(choice -> choice.toLowerCase().contains(newValue.toLowerCase()))
                  .forEach(filteredList::add);

          // Update the items in the ComboBox
          comboBox.setItems(filteredList);
          comboBox.show();
      }
    });

    return comboBox;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
