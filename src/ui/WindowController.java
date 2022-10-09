package ui;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
	
	String songFile = "src/Songs.txt";

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
    	Song song;
    	
    	String Sname = name.getText().trim();
    	String Sartist = artist.getText().trim();
    	String Salbum = album.getText().trim();
    	String Syear = album.getText().trim();
    
    		song = new Song(Sname, Sartist, Salbum, Syear);
    		
    		if(!inputCheck()) {
    			return;
    		}
    		try {
    			if(sortInsert(song)) {
    				displayAlertConfirm("Add Song","Song being added: " + Sname);
    				BufferedWriter writer = new BufferedWriter(new FileWriter(songFile));
    				
    				for(int i = 0; i < songobsList.size(); i++) {
    					writer.write(songobsList.get(i).toString());
    				}
    				writer.close();
    			}
    			else {
    				displayAlert("Duplicate Error", "Both song and artist mus be unique");
    			}
    		}
    		catch(Exception e) {
    			System.out.println(e.toString());
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
    }
    
    
    /*
     * Alert 
     * */
    public void displayAlert(String string) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Error!");
    	alert.setHeaderText("Error!");
    	alert.setContentText(string);
    	alert.showAndWait();
    }
    
    public boolean inputCheck() {
    	
    	if(name.getText().isBlank() || artist.getText().isBlank()) {
    		displayAlert("Error!", "Not name or artist");
        	return false;
    	}
    	else {
    		return true;
    	}
    }
    
    private void displayAlert(String header, String content) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Alert");
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
    /*
     * Alert with warning?
     * */
    private void displayAlertConfirm(String header, String content) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Alert");
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
}
