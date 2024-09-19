import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Playlist {
	
	private ArrayList<Song> songs;
	
	public Playlist() {
		songs = new ArrayList<Song>();
	}
	
	public Playlist(String fileName) {
		songs = new ArrayList<Song>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = reader.readLine()) != null) {
				songs.add(new Song(line));
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
		boolean valid = index >= 0 && index <= songs.size() && song != null;
		
		if(valid) {
			songs.add(index, song);
		}
		return valid;
	}
	
	public int addSongs(Playlist playlist) {
		boolean valid = playlist != null;
		int addedCount = 0;
		if(valid) {
			for(Song song : playlist.getSongs()) {
				songs.add(song);
				addedCount++;
			}
		}
		return addedCount;
	}
	
	public int addSongs(String fileName) {
		int addedCount = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = reader.readLine()) != null) {
				songs.add(new Song(line));
				addedCount ++;
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return addedCount;
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		boolean valid = index >= 0 && index < songs.size();
		
		if(valid) {
			return songs.remove(index);
		}
		return null;
	}
	
	public void saveSongs(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			for(Song song : songs) {
				writer.write(song.toString());
				writer.newLine();
			}
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String songList = "";
		for(int i = 0; i < songs.size(); i++) {
			songList += songs.get(i).toString() + (i < songs.size() - 1 ? System.lineSeparator() : "");
		}
		
		return songList;
	}
	
	public int[] getTotalTime() {
		int totalSeconds = 0;
		for(Song song : songs) {
			int[] songTime = song.getTime();
			totalSeconds += songTime.length > 2 ? songTime[2] * 3600 : 0;
			totalSeconds += songTime.length > 1 ? songTime[1] * 60 : 0;
			totalSeconds += songTime.length > 0 ? songTime[0] : 0;
		}
		if(totalSeconds > 3600) {
			return new int[] {totalSeconds % 60, totalSeconds / 60 % 60, totalSeconds / 3600};
		}else if(totalSeconds > 60) {
			return new int[] {totalSeconds % 60, totalSeconds / 60};
		}else {
			return new int[] {totalSeconds};
		}
	}
}
