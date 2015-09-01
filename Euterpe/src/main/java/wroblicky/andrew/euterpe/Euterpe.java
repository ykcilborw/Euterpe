package wroblicky.andrew.euterpe;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Euterpe {
	
	private Euterpe() {
	}
	
	private void run(String visualizerArg, String modeArg) {
		DatabaseManager databaseManager = null;
		Visualizer visualizer = null;
		Properties properties = this.getProperties();
		if (modeArg.equals("test")) {
			databaseManager = new SqliteDatabaseManager("test.db");
			TestManager testManager = new TestManager(databaseManager);
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
		InputStream in = getClass().getResourceAsStream("/main/resources/euterpe.properties");
		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
			// TODO
		}
		return properties;
	}

}
