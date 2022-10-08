package ui;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import be.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class WindowController {

	@FXML
    private Text songText;
	
	@FXML
    private TextField album;

    @FXML
    private TextField artist;

    @FXML
    private TextField name;
    
    @FXML
    private TextField year;
    
    @FXML
    private ListView<Song> songList;
    
    private ObservableList<Song> songobsList;
    

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void editOnAction(ActionEvent event) {

    }

    
    /*
     * display selected song in the text
     * */
    @FXML
    void SelectOnAction(MouseEvent event) {
    	songSelected();
    }
    
    @FXML
    private void initialize() throws IOException {
    	
    	/*
    	 * Reading from a file
    	 * Populating the ArrarList 
    	 * 
    	 * */
    	ArrayList<Song> songs = new ArrayList<Song>();
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader("src/Songs.txt"));
    		String currentL = reader.readLine();
    		
    		
    		while(currentL != null) {
    			
    			String[] line = currentL.split(";");
    			Song inputSong = new Song(line[0].trim(), line[1].trim(), line[2].trim(), line[3].trim());
    			songs.add(inputSong);
    			
    			System.out.println();
    			
    			currentL = reader.readLine();
    			
    		}
    		
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	/*
    	 * creating the observableList and adding it to the ListView
    	 * */
    	songobsList = FXCollections.observableArrayList(songs);
    	songList.setItems(songobsList);
    	
    	
    	/*
    	 * cellFactory to only show name and artist
    	 * */
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
    	
    	
    	/*
    	 * This parts selects the first song in the ListView
    	 * This also set text to the textfields
    	*/
    	songList.getSelectionModel().select(0);
    	Song selectedSong = songList.getSelectionModel().getSelectedItem();
    	FXCollections.sort(songList,)
    	if(selectedSong == null) {
    		songText.setText("No songs in the list");
    	}
    	else {
    		songText.setText(selectedSong.toString());
    		songText.setTextAlignment(TextAlignment.CENTER);
    		/*name.setText(selectedSong.getName());
    		artist.setText(selectedSong.getArtist());
    		album.setText(selectedSong.getAlbum());
    		year.setText(String.valueOf(selectedSong.getYear()));*/
    	}
    	
    	
    	
    	
    	
    	
    	
    }
    
    @FXML
    void AddOnAction(ActionEvent event) {
    	Song song;
    	
    	String Sname = name.getText().trim();
    	String Sartist = artist.getText().trim();
    	String Salbum = album.getText().trim();
    	String Syear = album.getText().trim();
    
    		song = new Song(Sname, Sartist, Salbum, Syear);
    		
    		if(song.getName() == null && song.getArtist() == null) {
    			//displayAlert("Not name or astist");
    			Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setTitle("Error!");
    	    	alert.setContentText("Not name or artist");
    	    	alert.show();
    		}
    		
    		
    		
    	
    	
    	
    	
    }
    
    public void songSelected() {
    	Song song = songList.getSelectionModel().getSelectedItem();
    	if(song == null) {
    		songText.setText("No Songs in the list");
    	}
    	else {
    		songText.setText(song.toString());
    		songText.setTextAlignment(TextAlignment.CENTER);
    	}
    }
    
    public void displayAlert(String string) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Error!");
    	alert.setHeaderText("Error!");
    	alert.setContentText(string);
    	alert.showAndWait();
    }
    
    public boolean inputCheck(Song song, boolean a) {
    	return true;
    }
    
}
