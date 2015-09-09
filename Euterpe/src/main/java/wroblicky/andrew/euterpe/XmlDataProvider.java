package wroblicky.andrew.euterpe;

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

public class XmlDataProvider extends DataProvider {
	
	
	private final Map<Song, Integer> songsToPlays;
	
	public XmlDataProvider(DatabaseManager databaseManager, Properties properties) {
		super(databaseManager, properties);
		this.songsToPlays = new HashMap<>();
	}
	
	public UpdateSet findAddedData(Properties properties) {
		
		UpdateSet updateSet = null;
		fetchSongProperties(properties);

		return updateSet;
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

}
