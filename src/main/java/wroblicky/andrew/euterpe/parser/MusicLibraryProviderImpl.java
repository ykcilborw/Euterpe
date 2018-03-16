package wroblicky.andrew.euterpe.parser;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MusicLibraryProviderImpl implements MusicLibraryProvider {
	
	private static DateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
	
	static {
		dfm.setTimeZone(TimeZone.getTimeZone("GMT-0"));
	}
	
	private static final String NAME = "Name";
	private static final String ARTIST = "Artist";
	private static final String GENRE = "Genre";
	private static final String DATE_ADDED = "Date Added";
	private static final String PLAY_COUNT = "Play Count";
	private static final String PLAY_DATE = "Play Date UTC";
	private static final String PERSISTENT_ID = "Persistent ID";
	
	@Override
	public MusicLibrary getMusicLibrary(Properties properties) {
		Set<String> artistNames = new HashSet<>();
		Map<String, InputSong> songLookup = new HashMap<>();
		Map<String,Integer> playCountLookup = new HashMap<>();
		for (Map<String, String> songProp : fetchSongProperties(properties))  {
			// artistNames
			String artist = songProp.get(ARTIST);
			artistNames.add(artist);
			
			// songLookup
			String songName = songProp.get(NAME);
			long dateAdded = convertUtcToUnix(songProp.get(DATE_ADDED));
			String persistentID = songProp.get(PERSISTENT_ID);
			int playCount = Integer.valueOf(songProp.get(PLAY_COUNT));
			long playDate = convertUtcToUnix(songProp.get(PLAY_DATE));
			InputSong inputSong = new InputSong(songName, artist, songProp.get(GENRE), dateAdded, 
					playCount, playDate, persistentID);
			songLookup.put(persistentID, inputSong);
			
			// playCountLookup
			playCountLookup.put(persistentID, playCount);
		}
		return new MusicLibrary(artistNames, songLookup, playCountLookup);
	}
	
	static List<Map<String, String>> fetchSongProperties(Properties properties) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		List<Map<String, String>> parsedSongs = new ArrayList<>();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(properties
					.getProperty("itunes_xml_location")));
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			NodeList songs = (NodeList) xpath.compile("plist/dict/dict/dict")
					.evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < songs.getLength(); i++) {
				Element element = (Element) songs.item(i);
				NodeList dictProps = element.getElementsByTagName("*");
				Map<String, String> songProperties = new HashMap<>();
				String key = "";
				for (int r = 0; r < dictProps.getLength(); r++) {
					Element dictProp = (Element) dictProps.item(r);
					if (dictProp.getNodeName().equals("key")) {
						key = dictProp.getTextContent();
					} else {
						songProperties.put(key, dictProp.getTextContent());
					}
				}
				parsedSongs.add(songProperties);
			}
		} catch (ParserConfigurationException | SAXException | IOException
				| XPathExpressionException e) {
			e.printStackTrace();
		}
		for (Map<String, String> thing : parsedSongs) {
			System.out.println("\n");
			for (String key : thing.keySet()) {
				System.out.println("key: " + key + ", " + thing.get(key));
			}
		}
		return parsedSongs;
	}
	
	static long convertUtcToUnix(String utcTimestamp) {
		if (utcTimestamp == null) {
			return 0;
		}
		try {
			String year = utcTimestamp.substring(0, 4);
			String month = utcTimestamp.substring(5, 7);
			String day = utcTimestamp.substring(8, 10);
			String hour = utcTimestamp.substring(11, 13);
			String minute = utcTimestamp.substring(14, 16);
			String second = utcTimestamp.substring(17, 19);
			return dfm.parse(year + month + day + hour + minute + second)
					.getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(1);
			return 0;
		}
	}
}
