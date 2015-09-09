package wroblicky.andrew.euterpe;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class XmlProviderTest {

	@Test
	public void testFetchSongProperties() {
		Properties properties = new Euterpe().getProperties();
		XmlDataProvider.fetchSongProperties(properties);
	}

}
