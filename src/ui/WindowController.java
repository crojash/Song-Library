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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

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
    	songList.setItems(songobsList);
    	
    	songList.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>(){
    		
    		public ListCell<Song> call(ListView<Song> p){
    			
    			ListCell<Song> songCell = new ListCell<Song>() {
    				
    				protected void updateItem(Song s, boolean bln) {
    					super.updateItem(s, bln);
    					if(s != null) {
    						setText(s.getName() + ", " + s.getArtist());
    					}
    					else if(s == null) {
    						setText(null);
    					}
    				}
    			};
    			return songCell;
    		}
    	});
    	
    	
    	/*songobsList = FXCollections.observableArrayList(songs);
    	
    	
    	
    	songList.setItems(songobsList);*/
    	songList.getSelectionModel().select(0);
    	
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
