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
    private ComboBox<SortingType> sortingComboBox;
    
    @FXML
    private Button simulateButton;

    
    private Bookstore bookStore;
    
    private FileChooser fileChooser;
    
    private File file;
    
    public BookStoreGUI() {
    	bookStore = new Bookstore();
	}
    
    @FXML
    public void initialize() throws IOException{
    	fileChooser= new FileChooser();
    	fileChooser.setTitle("Seleccionar datos");                
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("txt", "*.txt"));
        
        sortingComboBox.getItems().add(SortingType.MERGESORT);
        sortingComboBox.getItems().add(SortingType.QUICKSORT);
        sortingComboBox.getItems().add(SortingType.BUBBLESORT);
    }
    
    public void chooseFiles(ActionEvent event) {
    	
        file = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
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
    public void simulate(ActionEvent event) throws IOException {
    	
    	File newFile = fileChooser.showSaveDialog(mainPanel.getScene().getWindow());
    	
    	if (file != null) {
        	bookStore.simulate(file, sortingComboBox.getSelectionModel().getSelectedItem());
    		bookStore.saveResults(newFile);
    		reset();
        }
    }
    
    private void reset() {
    	sortingComboBox.setDisable(true);
    	simulateButton.setDisable(true);
    	labelURL.clear();
    	sortingComboBox.getSelectionModel().clearSelection();
    }

}
