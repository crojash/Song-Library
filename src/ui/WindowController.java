package ui;

/*
 * Song Library
 * Cesar Rojas Herrera
 * Abhishek Panwala
 * 
 * 
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class WindowController {
	
	String songFile = "src/data/Songs.txt";
	boolean confirmB;

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


    
    /*
     * display selected song in the text
     * */
    @FXML
    void SelectOnAction(MouseEvent event) {
    	songSelected();
    	//setText();
    }
    
    @FXML
    void selectSongOnAction(ActionEvent event) {
    	setText();
    	
    }
    
    @FXML
    void clearOnAction(ActionEvent event) {
    	clearText();
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
    		BufferedReader reader = new BufferedReader(new FileReader(songFile));
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
    	//Collections.sort(songobsList, Collections.reverseOrder());
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
    	 * 
    	*/
    	songList.getSelectionModel().select(0);
    	Song selectedSong = songList.getSelectionModel().getSelectedItem();
    	//FXCollections.sort(songList, new compareSong());
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
    
    
    /*
     * ADDDDDDDDDDDDDDDDDDD
     * */
    @FXML
    void AddOnAction(ActionEvent event) {
    	/*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Current song is added");
    	alert.setContentText("Song is getting added to the list");*/
    	
    	displayAlertConfirm("Add Song", "Song being added");
    	if(confirmB) {
    		//System.out.println("Yes");
    	
    	Song song;
    	
    	String Sname = name.getText().trim();
    	String Sartist = artist.getText().trim();
    	String Salbum = album.getText().trim();
    	String Syear = year.getText().trim();
    
    		song = new Song(Sname, Sartist, Salbum, Syear);
    		
    		if(!inputCheck()) {
    			return;
    		}
    		try {
    			if(sortInsert(song)) {
    				//displayAlertConfirm("Add Song","Song being added: " + Sname);
    				BufferedWriter writer = new BufferedWriter(new FileWriter(songFile));
    				
    				for(int i = 0; i < songobsList.size(); i++) {
    					writer.write(songobsList.get(i).toString());
    				}
    				writer.close();
    				clearText();
    			}
    			else {
    				displayAlert("Duplicate Error", "Both song and artist mus be unique");
    			}
    		}
    		catch(Exception e) {
    			System.out.println(e.toString());
    		}
    		confirmB = false;
    	}
    	else {
    		confirmB = false;
    		return;
    	}
    }
    
    private boolean sortInsert(Song song) {
    	
    	if(songobsList.size() == 0) {
    		songobsList.add(0, song);
    		return true;
    	}
    	for(int i = 0; i < songobsList.size(); i++) {
    		if(song.getName().compareToIgnoreCase(songobsList.get(i).getName()) < 0) {
    			songobsList.add(i, song);
    			return true;
    		}
    		else if(song.getName().compareToIgnoreCase(songobsList.get(i).getName()) == 0) {
    			if(song.getArtist().compareToIgnoreCase(songobsList.get(i).getArtist()) == 0) {
    				return false;
    			}
    			else if(song.getArtist().compareToIgnoreCase(songobsList.get(i).getArtist()) < 0) {
    				songobsList.add(i, song);
    				return true;
    			}
    		}
    	}
    	songobsList.add(songobsList.size(), song);
    	return true;
    }
    
    
    /*
     * Display song selected by the user in the Text field
     * */
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
    
     /*
      *DELETEEEEEEEEEE 
      * */
    @FXML
    void deleteOnAction(ActionEvent event) {
    	//ALERT HERE
    	displayAlertConfirm("Delete song", "Song being deleted");
    	if(confirmB) {
    	
	    	Song song = songList.getSelectionModel().getSelectedItem();
	    	try {
	    		songobsList.remove(song);
	    		BufferedWriter writer = new BufferedWriter(new FileWriter(songFile));
				
				for(int i = 0; i < songobsList.size(); i++) {
					writer.write(songobsList.get(i).toString());
				}
				writer.close();
				//songList.getSelectionModel().select(songList.getSelectionModel().getSelectedIndex());
	    	}
	    	catch(Exception e) {
	    		System.out.println(e.toString());
	    	}
	    	confirmB = false;
    	}
    	else {
    		confirmB = false;
    		return;
    	}
    }
    
    
    
    /*
     * EDIT Song
     * */
    @FXML
    void editOnAction(ActionEvent event) {
    	//ALERT HERE
    	displayAlertConfirm("Edited song","Song being edited");
    	
    	if(confirmB) {
    	
	    	if(!inputCheck()) {
	    		return;
	    	}
	    	
	    	Song song = songList.getSelectionModel().getSelectedItem();
	    	
	    	try {
	    		Song temp = new Song(name.getText().trim(), artist.getText().trim(),
	    				album.getText().trim(), year.getText().trim());
	    		if(song.getName().equals(name.getText()) && song.getArtist().equals(artist.getText())) {
	    			song.setAlbum(album.getText());
	    			song.setYear(year.getText());
	    			
	    			BufferedWriter writer = new BufferedWriter(new FileWriter(songFile));
	    			
	    			for(int i = 0; i < songobsList.size(); i++) {
	    				writer.write(songobsList.get(i).toString());
	    			}
	    			writer.close();
	    			
	    			clearText();
	    			//displayAlert("Info", "Song Edited");
	    		}
	    		else if(sortInsert(temp)) {
	    			songobsList.remove(song);
	    			BufferedWriter writer = new BufferedWriter(new FileWriter(songFile));
					
					for(int i = 0; i < songobsList.size(); i++) {
						writer.write(songobsList.get(i).toString());
					}
					writer.close();
					
					clearText();
					//displayAlert("Info","Song Edited");
	    		}
	    		else {
	    			displayAlert("Error","Both song and artist mus be unique");
	    		}
	    		
	    	}
	    	catch(Exception e) {
	    		System.out.println(e.toString());
	    	}
	    	confirmB = false;
    	}
    	else {
    		confirmB = false;
    		return;
    	}
    	
    }
    
    
    /*
     * Alert 
     * */
    /*private void displayAlert(String string) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Error!");
    	alert.setHeaderText("Error!");
    	alert.setContentText(string);
    	alert.showAndWait();
    }*/
    
    private boolean inputCheck() {
    	
    	if(name.getText().isBlank() || artist.getText().isBlank()) {
    		displayAlert("Error!", "Not name or artist");
        	return false;
    	}
    	else{
    		if(Integer.valueOf(year.getText()) < 0) { 
    			displayAlert("Error", "Year value has to be positive");
    			return false;
    		}
    		return true;
    	}
    }
    
    private void displayAlert(String header, String content) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Alert");
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
    /*
     * Alert with warning?
     * */
    private void displayAlertConfirm(String header, String content) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Alert");
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	
    	ButtonType okB = new ButtonType("Yes", ButtonBar.ButtonData.YES);
    	//ButtonType noB = new ButtonType("No", ButtonBar.ButtonData.NO);
    	ButtonType cancelB = new ButtonType ("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    	alert.getButtonTypes().setAll(okB, cancelB);
    	alert.showAndWait().ifPresent(type ->{
    		if(type == okB) {
    			//System.out.println("Ok");
    			confirmB = true;
    		}
    		else if(type == cancelB) {
    			//System.out.println("No");
    			confirmB = false;
    		}
    	});
    	
    	
    }
    
    private void setText() {
    	Song song = songList.getSelectionModel().getSelectedItem();
    	name.setText(song.getName());
    	artist.setText(song.getArtist());
    	album.setText(song.getAlbum());
    	year.setText(song.getYear());
    }
    
    private void clearText() {
    	name.clear();
    	artist.clear();
    	album.clear();
    	year.clear();
    }
    
}
