package ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Bookstore;
import model.SortingType;

import java.io.File;
import java.io.IOException;

public class BookStoreGUI {

    @FXML
    private BorderPane mainPanel;
    
    @FXML
    private TextField labelURL;

    @FXML
    private ComboBox<String> sortingComboBox;
    
    @FXML
    private Button simulateButton;

    
    private Bookstore bookStore;
    
    private FileChooser fileChooser;
    
    public BookStoreGUI() {
    	bookStore = new Bookstore();
	}
    
    @FXML
    public void initialize() throws IOException{
    	fileChooser= new FileChooser();
    	fileChooser.setTitle("Seleccionar datos");                
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("txt", "*.txt"));
        
        sortingComboBox.getItems().add(SortingType.COUNTINGSORT.toString());
        sortingComboBox.getItems().add(SortingType.QUICKSORT.toString());
        sortingComboBox.getItems().add(SortingType.BUBBLESORT.toString());
    }
    
    public void chooseFiles(ActionEvent event) {
    	
        File file = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
        if (file != null) {
        	labelURL.setText(file.getAbsolutePath());
        	sortingComboBox.setDisable(false);
        }
    }
    
    @FXML
    public void onActionSortingChoose(Event event) {
    	simulateButton.setDisable(false);
    }
    
    @FXML
    public void simulate(ActionEvent event) {
    	
    }

}
