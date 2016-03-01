package wroblicky.andrew.euterpe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import wroblicky.andrew.euterpe.dao.DAOFactory;
import wroblicky.andrew.euterpe.dao.SqliteDAOFactory;
import wroblicky.andrew.euterpe.provider.DataLoader;
import wroblicky.andrew.euterpe.provider.MusicLibraryProvider;
import wroblicky.andrew.euterpe.provider.XmlDataProvider;
import wroblicky.andrew.euterpe.visualizer.CommandLineVisualizer;
import wroblicky.andrew.euterpe.visualizer.Visualizer;

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
			DAOFactory daoFactory, Properties properties) {
		Visualizer visualizer = null;
		if (visualizerArg.equals("terminal")) {
			visualizer = new CommandLineVisualizer(daoFactory, properties);
		}
		return visualizer;
	}

	public static void runTest(String visualizerArg, String modeArg,
			Properties properties) {
		DAOFactory daoFactory = new SqliteDAOFactory();
		MusicLibraryProvider musicLibraryProvider = new XmlDataProvider();
		DataLoader dataLoader = new DataLoader(musicLibraryProvider, daoFactory, properties);
		switch (modeArg) {
		case "sql_query_test":
			handleSqlQueryTest(daoFactory, dataLoader);
			break;
		case "initial_load_test":
			handleInitialDatabaseLoadTest(daoFactory, dataLoader);
			break;
		case "repeated_load_test":
			handleRepeatedLoadTest(daoFactory, dataLoader);
			break;
		}
	}

	public static void handleSqlQueryTest(DAOFactory daoFactory,
			DataLoader dataProvider) {
		SqlTestManager testManager = new SqlTestManager(daoFactory,
				dataProvider);
		testManager.prepareDatabase();
	}

	public static void handleInitialDatabaseLoadTest(
			DAOFactory daoFactory, DataLoader dataProvider) {

	}

	public static void handleRepeatedLoadTest(DAOFactory daoFactory,
			DataLoader dataProvider) {

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
