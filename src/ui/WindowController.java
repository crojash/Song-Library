package ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;

public class WindowController{

	@FXML
    private Button addB;

    @FXML
    private TableColumn<?, ?> artist;

    @FXML
    private Button deleteB;

    @FXML
    private Button editB;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TextArea textArea;

    
    private void populateSongView() {
    	
    }

}
