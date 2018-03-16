package wroblicky.andrew.euterpe.parser;

import java.util.Properties;

public interface MusicLibraryProvider {
	
	MusicLibrary getMusicLibrary(Properties properties);

}
