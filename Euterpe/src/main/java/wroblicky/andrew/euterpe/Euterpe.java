package wroblicky.andrew.euterpe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Euterpe {
	
	private final static String EUTERPE_CONF_LOCATION = "src/main/resources/euterpe.conf";
	
	Euterpe() {
	}
	
	private void run(String visualizerArg, String modeArg) {
		DatabaseManager databaseManager = null;
		DataProvider dataProvider = null;
		Visualizer visualizer = null;
		Properties properties = this.getProperties();
		if (modeArg.equals("test")) {
			databaseManager = new SqliteDatabaseManager("test.db");
			dataProvider = new XmlDataProvider(databaseManager, properties);
			TestManager testManager = new TestManager(databaseManager, dataProvider);
			testManager.prepareDatabase();
		} else { // prod
			databaseManager = new SqliteDatabaseManager("euterpe.db");
		}
		if (visualizerArg.equals("terminal")) {
			visualizer = new CommandLineVisualizer(databaseManager, properties);
		}
		visualizer.launch();
	}
	
	public static void main(String[] args) {
		if (args.length < 2) {
			throw new IllegalStateException("Must have at least 2 args");
		}
		String visualizerArg = args[0]; // text or swing
		String modeArg = args[1]; // prod or test
		new Euterpe().run(visualizerArg, modeArg);
	}
	
	public Properties getProperties() {
		Properties properties = new Properties();
	       System.out.println("Working Directory = " +
	               System.getProperty("user.dir"));
		File propertiesFile = new File(EUTERPE_CONF_LOCATION);
		try {
			InputStream in = new FileInputStream(propertiesFile);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("An exception occurred attempting to read " + EUTERPE_CONF_LOCATION);
		}
		return properties;
	}

}
