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

		Map<SongIdentificationKey, InputSong> songLookup = new HashMap<>();
		SongIdentificationKey tenYearsSongIdentificationKey = new SongIdentificationKey(
				"10 Years", "Wasteland",
				MusicLibraryProviderImpl
						.convertUtcToUnix("2014-10-16T06:47:00Z"));
		SongIdentificationKey thirtySecondsSongIdentificationKey = new SongIdentificationKey(
				"30 Seconds to Mars", "From Yesterday",
				MusicLibraryProviderImpl
						.convertUtcToUnix("2014-10-16T06:47:00Z"));

		// MusicLibrary expectedMusicLibrary = new MusicLibrary();
		// MusicLibrary musicLibrary =
		// MusicLibraryProviderImpl.getMusicLibrary(properties);
	}

	@Test
	public void testConvertDateToUTC() {
		assertEquals(1413442020,
				MusicLibraryProviderImpl
						.convertUtcToUnix("2014-10-16T06:47:00Z"));
	}
}
