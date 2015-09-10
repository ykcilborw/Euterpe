package wroblicky.andrew.euterpe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Euterpe {

	private final static String EUTERPE_CONF_LOCATION = "src/main/resources/euterpe.conf";

	public static void main(String[] args) {
		if (args.length < 2) {
			throw new IllegalStateException("Must have at least 2 args");
		}
		String visualizerArg = args[0]; // text or swing
		String modeArg = args[1]; // prod or test
		evaluateOptions(visualizerArg, modeArg);
	}

	public static void evaluateOptions(String visualizerArg, String modeArg) {
		Properties properties = getProperties();
		if (modeArg.contains("test")) {
			runTest(visualizerArg, modeArg, properties);
		}
		// TODO runLive();
	}

	public static Visualizer getVisualizer(String visualizerArg,
			DatabaseManager databaseManager, Properties properties) {
		Visualizer visualizer = null;
		if (visualizerArg.equals("terminal")) {
			visualizer = new CommandLineVisualizer(databaseManager, properties);
		}
		return visualizer;
	}

	public static void runTest(String visualizerArg, String modeArg,
			Properties properties) {
		DatabaseManager databaseManager = new SqliteDatabaseManager("test.db");
		DataProvider dataProvider = new XmlDataProvider(databaseManager,
				properties);
		switch (modeArg) {
		case "sql_query_test":
			handleSqlQueryTest(databaseManager, dataProvider);
			break;
		case "initial_load_test":
			handleInitialDatabaseLoadTest(databaseManager, dataProvider);
			break;
		case "repeated_load_test":
			handleRepeatedLoadTest(databaseManager, dataProvider);
			break;
		}
	}

	public static void handleSqlQueryTest(DatabaseManager databaseManager,
			DataProvider dataProvider) {
		SqlTestManager testManager = new SqlTestManager(databaseManager,
				dataProvider);
		testManager.prepareDatabase();
	}

	public static void handleInitialDatabaseLoadTest(
			DatabaseManager databaseManager, DataProvider dataProvider) {

	}

	public static void handleRepeatedLoadTest(DatabaseManager databaseManager,
			DataProvider dataProvider) {

	}

	public static Properties getProperties() {
		Properties properties = new Properties();
		System.out.println("Working Directory = "
				+ System.getProperty("user.dir"));
		File propertiesFile = new File(EUTERPE_CONF_LOCATION);
		try {
			properties.load(new FileInputStream(propertiesFile));
		} catch (IOException e) {
			System.out.println("An exception occurred attempting to read "
					+ EUTERPE_CONF_LOCATION);
		}
		return properties;
	}

}
