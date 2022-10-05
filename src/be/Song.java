package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Song {

	private StringProperty name = new SimpleStringProperty();
	private StringProperty artist = new SimpleStringProperty();
	private StringProperty album = new SimpleStringProperty();
	private IntegerProperty year = new SimpleIntegerProperty();
	
	//WHen adding the song, name and artist should be entered
	/*public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
	}
	
	public Song(String name, String artist, String album, Integer year) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}*/

	/*public Song(String string, String string2, String string3, Integer valueOf) {
		name.set(string);
		artist.set(string2);
		album.set(string3);
		year.set(valueOf);
	}*/

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getArtist() {
		return artist.get();
	}

	public void setArtist(String artist) {
		this.artist.set(artist);
	}

	public String getAlbum() {
		return album.get();
	}

	public void setAlbum(String album) {
		this.album.set(album);
	}

	public Integer getYear() {
		return year.get();
	}

	public void setYear(Integer year) {
		this.year.set(year);
	}
	public String toString() {
		//Formatted to appear in song detail
		return String.format("%s, %s, %s, %d. ", name, artist, album, year);
	}
	
	
}
