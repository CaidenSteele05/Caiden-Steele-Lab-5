import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info) {
		String[] str = info.split(INFO_DELIMITER);
		this.title = str[IDX_TITLE];
		this.artist = str[IDX_ARTIST].strip();
		String[] time= str[IDX_TIME].split(TIME_DELIMITER);
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		
		if(time.length > 2) {
			hours = Integer.parseInt(time[0]);
			minutes = Integer.parseInt(time[1]);
			seconds = Integer.parseInt(time[2]);
			this.time = new int[] {seconds, minutes, hours};
		}else if(time.length > 1) {
			minutes = Integer.parseInt(time[0]);
			seconds = Integer.parseInt(time[1]);
			this.time = new int[] {seconds, minutes};
		}else {
			seconds = Integer.parseInt(time[0]);
			this.time = new int[] {seconds};
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	
	public String toString() {
		String hours = "";
		String minutes = "";
		String seconds = "";
		
		if(time.length > 2) {
			hours = String.format("%d", time[2]) + ":";
			minutes = String.format("%02d", time[1]) + ":";
			seconds = String.format("%02d", time[0]);
		}else if(time.length > 1) {
			minutes = String.format("%d", time[1]) + ":";
			seconds = String.format("%02d", time[0]);
		}else {
			seconds = String.format("%d", time[0]);
		}
		return title + "; " + artist + "; " + hours + minutes + seconds;
	}
}
