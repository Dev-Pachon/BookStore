package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BookStoreGUI {

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TextField numberOfBoxes;

    @FXML
    private TextField numberOfShelves;

    @FXML
    void next(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookStore.fxml"));

        fxmlLoader.setController(this);
        Parent addContactPane = fxmlLoader.load();

        mainPanel.getChildren().clear();
        mainPanel.getChildren().add(addContactPane);


    }


}
