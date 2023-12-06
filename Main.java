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
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.*;

/**
 * Jetlag Application
 *
 * @author Alice Cheng, May Ming
 * @version 0.0.2
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Obtain data
    FlightData data = new FlightData("data/flight.csv");
    List<String[]> flights = data.getFlights();
    Set<String> departures = data.getDepartures();
    Set<String> arrivals = data.getArrivals();

    // Application title
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

    // Search boxes
    ComboBox<String> departCBox = createSearchBox(departures);
    StackPane departPane = addPromptComboBox(departCBox, "Enter Departure Airport");

    ComboBox<String> arriveCBox = createSearchBox(arrivals);
    StackPane arrivePane = addPromptComboBox(arriveCBox, "Enter Arrival Airport");

    // Buttons
    Button submitButton = new Button("Find");
     
    Button clear = new Button("Clear");
    clear.setVisible(false);

    HBox searchRow = new HBox(30);    
    searchRow.getChildren().addAll(departPane, arrivePane, submitButton);
    searchRow.setAlignment(Pos.CENTER);

    // Shortest route representation
    GridPane output = new GridPane();
    StackPane outputPane = new StackPane();

    submitButton.setOnAction(e -> {
      // Airports entered in search box must exist
      if(departures.contains(departCBox.getValue()) && arrivals.contains(arriveCBox.getValue())) {
        displayFlight(flights, data.cheapestRoute(departCBox.getValue(), arriveCBox.getValue()), output, clear, outputPane);
      }
    });

    clear.setOnAction(e -> {  
      output.getChildren().clear();
      outputPane.getChildren().clear();
      ComboBox<String> departBox = createSearchBox(departures);
      StackPane departP = addPromptComboBox(departBox, "Enter Departure Airport");

      ComboBox<String> arriveBox = createSearchBox(arrivals);
      StackPane arriveP = addPromptComboBox(arriveBox, "Enter Arrival Airport");

      searchRow.getChildren().clear();
      searchRow.getChildren().addAll(departP, arriveP, submitButton);

      submitButton.setOnAction(event -> {
      // Airports entered in search box must exist
      if(departures.contains(departBox.getValue()) && arrivals.contains(arriveBox.getValue())) {
        displayFlight(flights, data.cheapestRoute(departBox.getValue(), arriveBox.getValue()), output, clear, outputPane);
      }
    });

      clear.setVisible(false);
    });

    // Final layout
    Image backgroundImage = new Image("./graphics/cloud-bg.jpg");
    BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null,
                                                     new BackgroundSize(800, 600, true, true, true, true));

    VBox vBox = new VBox(20);
    vBox.setBackground(new Background(background));
    vBox.getChildren().addAll(titleBox, searchRow, output, outputPane, clear);
    vBox.setAlignment(Pos.CENTER);

    Scene scene = new Scene(vBox, 800, 600);
    scene.getStylesheets().add("./graphics/style.css");

    primaryStage.setTitle("Jetlag");
    primaryStage.setMaximized(true);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private GridPane displayFlight(List<String[]> flights, String[] info, GridPane grid, Button clear, StackPane pane) {
    grid.setPadding(new Insets(30));
    grid.setHgap(40);
    grid.setVgap(15);
    grid.alignmentProperty().set(Pos.CENTER);
    
    clear.setVisible(true);
    
    Image boardingPass = new Image("./graphics/boarding-pass.png");

    ImageView iv = new ImageView(boardingPass);  
    iv.setFitWidth(grid.getWidth()*.5);   
    iv.setFitHeight(190);

    pane.getChildren().addAll(iv, grid);
    pane.setAlignment(Pos.CENTER);

    boolean exists = flightExists(flights, info);
    if (!exists){
      Label noFlights = new Label("No flights found");
      grid.add(noFlights, 0, 0);
      return grid;
    }

    String[] stops = info[1].split(",");
    int numStops = stops.length;

    for (int i = 0; i < numStops; i++){
      Label stopLabel;
      
      if (i == 0){
        stopLabel = new Label("Departure Airport");
      }
      else if (i == numStops-1){
        stopLabel = new Label("Final Airport");
      }
      else {
        stopLabel = new Label("Stop " + i);
      }

      Label airport = new Label(stops[i]);
      airport.setFont(Font.font("Tahoma",24));
      grid.add(stopLabel, i, 0);
      grid.add(airport, i, 1);
    }

    // Add cost
    String cost = info[0];

    Label priceLabel = new Label("Price");
    grid.add(priceLabel, numStops, 0);

    Label price = new Label("$"+cost);
    price.setFont(Font.font("Tahoma",24));
    grid.add(price, numStops, 1);

    return grid;
  }

  private boolean flightExists(List<String[]> flights, String[] info) {
    if (info[1] == null) {
      return false;
    }
    return true;
  }

  // Make disappearing prompt text in ComboBox
  private StackPane addPromptComboBox(ComboBox<String> comboBox, String label) {
    Label promptLabel = new Label(label);
    promptLabel.setStyle("-fx-text-fill: gray;");
    promptLabel.setOnMouseClicked(e -> {
      comboBox.show();
      comboBox.requestFocus();
    });

    TextFormatter<String> disappearText = new TextFormatter<>(change -> {
        if (change.getControlNewText().isEmpty()) {
            promptLabel.setVisible(true);
        } 
        else {
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

// Make ComboBox filter choices as user types
private ComboBox<String> createSearchBox(Collection<String> list) {
    ObservableList<String> observeList = FXCollections.observableArrayList(list);
    FXCollections.sort(observeList); // Sort airports alphabetically
    
    ComboBox<String> comboBox = new ComboBox<>(observeList);
    comboBox.setEditable(true);
    
    TextField editor = comboBox.getEditor();
    editor.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isBlank()) {
          comboBox.setItems(observeList);
          comboBox.show();
      } 
      else {
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
