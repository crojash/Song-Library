package be;

import java.util.Comparator;

public class Song {

	private String name;
	private String artist;
	private String album;
	private String year;
	
	//WHen adding the song, name and artist should be entered
	/*public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
	}*/
	public Song() {
		
	}
	
	public Song(String name, String artist, String album, String year) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}


	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}

	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	public String toString() {
		//Formatted to appear in song detail
		return String.format("%s; %s; %s; %s \n", name, artist, album, year);
	}
	
	
}
