package be;

public class Song {

	private String name;
	private String artist;
	private String album;
	private Integer year;
	
	//WHen adding the song, name and artist should be entered
	/*public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
	}*/
	public Song() {
		
	}
	
	public Song(String name, String artist, String album, Integer year) {
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

	public Integer getYear() {
		return year;
	}

	public String toString() {
		//Formatted to appear in song detail
		return String.format("%s, %s, %s, %d. ", name, artist, album, year);
	}
	
	
}
