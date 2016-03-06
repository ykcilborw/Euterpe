package wroblicky.andrew.euterpe.mapper;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.provider.InputSong;
import wroblicky.andrew.euterpe.provider.SongIdentificationKey;

public class SongMapper {
	
	public static Song from(InputSong inputSong, Artist artist) {
		return new Song(inputSong.getName(), artist, inputSong.getGenre(),
				inputSong.getDateAdded(), inputSong.getNumPlays(),
				inputSong.getMostRecentPlayDate());
	}
	
	public static SongIdentificationKey from(Song song) {
		return new SongIdentificationKey(song.getArtist().getName(), song.getName(), song.getDateAdded());
	}
}
