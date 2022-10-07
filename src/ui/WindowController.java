package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import be.Song;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
    private ListView<?> songList;
    
    ReadOnlyObjectProperty<ObservableList<Song>> listSongs = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    ReadOnlyObjectProperty<FilteredList<Song>> viewablelistSongs = new SimpleObjectProperty<FilteredList<Song>>(new FilteredList<>(listSongs.get()));
    
    
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
    	Song[] songs = new Song[0];
    	int counter = 0;
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader("src/Songs.txt"));
    		String currentL = reader.readLine();
    		
    		while(currentL != null) {
    			
    			//Extending the array 
    			Song[] temp = new Song[songs.length + 1];
    			System.arraycopy(songs, 0, temp, songs.length, counter);
    			songs = temp;
    			
    			
    			String[] line = currentL.split(";");
    			songs[counter] = new Song(line[0], line[1], line[2], Integer.valueOf(line[3]));

    			//songs = new {Song(line[0], line[1], line[2], Integer.valueOf(line[3]};
    			System.out.println(songs[counter]);
    			//songlist.add(song);
    			//songList.getItems().addAll(song);
    			currentL = reader.readLine();
    			counter++;
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
