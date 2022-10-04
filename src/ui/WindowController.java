package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class WindowController {

    @FXML
    private TextArea textArea;
    
    @FXML
    private ListView<String> songList;
    
    ObservableList<String> test1 = FXCollections.observableArrayList("test1", "test2");

    @FXML
    void AddOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void editOnAction(ActionEvent event) {

    }

    @FXML
    void SelectOnAction(MouseEvent event) {
    	textArea.setText(songList.getId());
    }
    
    @FXML
    private void initialize() {
    	//cbSsongs.setItems(test1);
    	songList.setItems(test1);
    }
}
