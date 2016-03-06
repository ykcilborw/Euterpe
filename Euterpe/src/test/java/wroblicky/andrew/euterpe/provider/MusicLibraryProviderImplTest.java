package wroblicky.andrew.euterpe.provider;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

public class MusicLibraryProviderImplTest {

	@Test
	public void testGetMusicLibrary() {
		Properties properties = new Properties();
		properties.setProperty("itunes_xml_location",
				"src/main/resources/simple_library.xml");

		Set<String> artistNames = new HashSet<>();
		artistNames.add("10 Years");
		artistNames.add("Thirty Seconds to Mars");
		artistNames.add("AFI");

		Map<String, InputSong> songLookup = new HashMap<>();
		songLookup.put("6F5599FDEF814467", new InputSong(
				"Wasteland", "10 Years", "Rock", 
				MusicLibraryProviderImpl
						.convertUtcToUnix("2010-04-19T04:50:21Z"), 6, MusicLibraryProviderImpl
						.convertUtcToUnix("2014-10-16T06:47:00Z"), "6F5599FDEF814467"));
		songLookup.put("722C81D08E82F310", new InputSong(
				"From Yesterday", "Thirty Seconds to Mars", "Alternative", 
				MusicLibraryProviderImpl
						.convertUtcToUnix("2010-04-19T04:50:21Z"), 3,
						0, "722C81D08E82F310"));
		songLookup.put("F2C5F5E2EFC1D3D9", new InputSong(
				"Medicate", "AFI", "Alternative", 
				MusicLibraryProviderImpl
						.convertUtcToUnix("2010-04-19T04:50:22Z"), 3, 0, "F2C5F5E2EFC1D3D9"));
		
		Map<String, Integer> playCountLookup = new HashMap<>();
		playCountLookup.put("6F5599FDEF814467", 6);
		playCountLookup.put("722C81D08E82F310", 3);
		playCountLookup.put("F2C5F5E2EFC1D3D9", 3);

		MusicLibraryProviderImpl musicLibraryProvider = new MusicLibraryProviderImpl();
		MusicLibrary musicLibrary = musicLibraryProvider.getMusicLibrary(properties);
		assertEquals(artistNames, musicLibrary.getArtistNames());
		assertEquals(playCountLookup, musicLibrary.getPlayCountLookup());
		assertEquals(songLookup.keySet(), musicLibrary.getSongLookup().keySet());
		for (String key : songLookup.keySet()) {
			InputSong expected = songLookup.get(key);
			InputSong actual = musicLibrary.getSongLookup().get(key);
			assertEquals(expected.getArtist(), actual.getArtist());
			assertEquals(expected.getDateAdded(), actual.getDateAdded());
			assertEquals(expected.getGenre(), actual.getGenre());
			assertEquals(expected.getMostRecentPlayDate(), actual.getMostRecentPlayDate());
			assertEquals(expected.getName(), actual.getName());
			assertEquals(expected.getNumPlays(), actual.getNumPlays());
			assertEquals(expected.getPersistentID(), actual.getPersistentID());
		}
	}

	@Test
	public void testConvertDateToUTC() {
		assertEquals(1413442020,
				MusicLibraryProviderImpl
						.convertUtcToUnix("2014-10-16T06:47:00Z"));
	}
}
