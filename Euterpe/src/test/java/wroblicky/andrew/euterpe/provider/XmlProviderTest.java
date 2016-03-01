package wroblicky.andrew.euterpe.provider;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

import wroblicky.andrew.euterpe.Euterpe;
import wroblicky.andrew.euterpe.provider.MusicLibraryProviderImpl;

public class XmlProviderTest {

	@Test
	public void testFetchSongProperties() {
		Properties properties = new Euterpe().getProperties();
		MusicLibraryProviderImpl.fetchSongProperties(properties);
	}

}
