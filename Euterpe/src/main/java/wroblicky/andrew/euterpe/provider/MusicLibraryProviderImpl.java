package wroblicky.andrew.euterpe.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
	
	private static final String NAME = "Name";
	private static final String ARTIST = "Artist";
	private static final String GENRE = "Genre";
	private static final String DATE_ADDED = "Date Added";
	private static final String PLAY_COUNT = "Play Count";
	private static final String PLAY_DATE = "Play Date";
	
	@Override
	public MusicLibrary getMusicLibrary(Properties properties) {
		return new MusicLibrary(getSongs(properties));
	}
	
	static List<InputSong> getSongs(Properties properties) {
		List<InputSong> songs = new ArrayList<InputSong>();
		for (Map<String, String> songProp : fetchSongProperties(properties))  {
			 songs.add(new InputSong(songProp.get(NAME), songProp.get(ARTIST), songProp.get(GENRE), 
					 convertUtcToUnix(songProp.get(DATE_ADDED)),
					Integer.valueOf(songProp.get(PLAY_COUNT)), Long.valueOf(songProp.get(PLAY_DATE))));
		}
		return songs;
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
		return 0L;
	}
}
