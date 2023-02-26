package org.example.model;

public class Audio {
	private String id;
	private String artistName;
	private String trackTitle;
	private String albumTitle;
	private String trackNumber;
	private int year;
	private String number_of_reviews;
	private int sold_copies;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getTrackTitle() {
		return trackTitle;
	}
	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}
	public String getAlbumTitle() {
		return albumTitle;
	}
	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}
	public String getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getNumber_of_reviews() {
		return number_of_reviews;
	}
	public void setNumber_of_reviews(String number_of_reviews) {
		this.number_of_reviews = number_of_reviews;
	}
	public int getSold_copies() {
		return sold_copies;
	}
	public void setSold_copies(int sold_copies) {
		this.sold_copies = sold_copies;
	}
	@Override
	public String toString() {
		return "Audio [id=" + id + ", artistName=" + artistName + ", trackTitle=" + trackTitle + ", albumTitle="
				+ albumTitle + ", trackNumber=" + trackNumber + ", year=" + year + ", number_of_reviews="
				+ number_of_reviews + ", sold_copies=" + sold_copies + "]";
	}
	public Audio(String id, String artistName, String trackTitle, String albumTitle, String trackNumber, int year,
			String number_of_reviews, int sold_copies) {
		super();
		this.id = id;
		this.artistName = artistName;
		this.trackTitle = trackTitle;
		this.albumTitle = albumTitle;
		this.trackNumber = trackNumber;
		this.year = year;
		this.number_of_reviews = number_of_reviews;
		this.sold_copies = sold_copies;
	}
	public Audio() {
		
	}
	
//	public synchronized void sell(int numCopies) {
//		this.sold_copies += numCopies;
//	}
	
//	public synchronized int getTotalSoldCopies() {
//		int total = 0;
//		for (Audio audio : audioDB.values()) {
//			total += audio.getTotalSoldCopies();
//		}
//		return total;
//	}
	
	
	
	
	

	
	
}
