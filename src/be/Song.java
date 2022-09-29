package be;

public class Song {

	private String name;
	private String artist;
	private String album;
	private Integer year;
	
	//WHen adding the song, name and artist should be entered
	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	
}
