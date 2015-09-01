package wroblicky.andrew.euterpe;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlDataProvider extends DataProvider {
	
	private final Map<Song, Integer> songsToPlays;
	
	public XmlDataProvider(DatabaseManager databaseManager, Properties properties) {
		super(databaseManager, properties);
		this.songsToPlays = new HashMap<>();
	}
	
	public UpdateSet findAddedData(Properties properties) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		UpdateSet updateSet = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(properties.getProperty("itunes_xml_location")));
		} catch (ParserConfigurationException  | SAXException | IOException e) {
			e.printStackTrace();
			System.out.println("An exception occurred");
		}
		return updateSet;
	}

}
