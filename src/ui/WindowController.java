package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import be.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class WindowController {

    @FXML
    private TextArea textArea;
    
    @FXML
    private ListView<Song> songList;
    
    ObservableList<String> test1 = FXCollections.observableArrayList("test1", "test2");
    ObservableList<String> test2 = FXCollections.observableArrayList("test1.1", "test2.1");
    ObservableList<Song> songlist = FXCollections.observableArrayList();

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
    	//cbSsongs.setItems(test1);
    	Song song = new Song();
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader("src/Songs.txt"));
    		String currentL = reader.readLine();
    		
    		while(currentL != null) {
    			
    			String[] line = currentL.split(";");
    			song.setName(line[0]);
    			song.setArtist(line[1]);
    			song.setAlbum(line[2]);
    			song.setYear(Integer.valueOf(line[3]));
    			//songlist.add(new Song(line[0], line[1], line[2], Integer.valueOf(line[3])));
    			System.out.println();
    			songlist.add(song);
    			//songList.getItems().addAll(song);
    			currentL = reader.readLine();
    		}
    		
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	/*songList = new ListView(songlist);*/
    	//songList.setItems(songlist);
    	//songList.getItems().add(songList);
    	//songList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
