package ui;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import be.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class WindowController {

	@FXML
    private Text songText;
    
    @FXML
    private ListView<Song> songList;
    
    private ObservableList<Song> songobsList;
    
    
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
    	//textArea.setText(songList.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void initialize() throws IOException {
    	
    	ArrayList<Song> songs = new ArrayList<Song>();
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader("src/Songs.txt"));
    		String currentL = reader.readLine();
    		
    		
    		while(currentL != null) {
    			
    			String[] line = currentL.split(";");
    			Song inputSong = new Song(line[0], line[1], line[2], Integer.valueOf(line[3]));
    			songs.add(inputSong);
    			
    			System.out.println();
    			
    			currentL = reader.readLine();
    			
    		}
    		
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	songobsList = FXCollections.observableArrayList(songs);
    	/*FilteredList<Song> filteredSongs = new FilteredList<>(songobsList,
    			item -> item.getName().contains("NewSong"));
    	if(filteredSongs.size())*/
    	
    	
    	
    	songList.setItems(songobsList);
    	//songList.disableProperty();
    	//songList.insetsProperty();
    	songList.getSelectionModel().select(0);
    	//songList.getSelectionModel().selectedIndexProperty().addListener((obs,odlVal, newVal) -> showItem(initialize));;
    	
    	Song selectedSong = songList.getSelectionModel().getSelectedItem();
    	
    	if(selectedSong == null) {
    		songText.setText("No songs in the list");
    	}
    	else {
    		songText.setText(selectedSong.toString());
    		songText.setTextAlignment(TextAlignment.CENTER);
    		
    	}
    	
    }
}
