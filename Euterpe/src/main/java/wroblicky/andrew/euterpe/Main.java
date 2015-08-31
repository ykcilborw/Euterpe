package wroblicky.andrew.euterpe;

public class Main {
	
	public static void main(String[] args) {
		if (args.length < 2) {
			throw new IllegalStateException("Must have at least 2 args");
		}
		String visualizerArg = args[0]; // text or swing
		String modeArg = args[1]; // prod or test
		DatabaseManager databaseManager = null;
		Visualizer visualizer = null;
		if (modeArg.equals("test")) {
			databaseManager = new SqliteDatabaseManager("test.db");
			TestManager testManager = new TestManager(databaseManager);
			testManager.prepareDatabase();
		} else { // prod
			databaseManager = new SqliteDatabaseManager("euterpe.db");
		}
		if (visualizerArg.equals("terminal")) {
			visualizer = new CommandLineVisualizer(databaseManager);
		}
		visualizer.launch();
	}

}
