package wroblicky.andrew.euterpe.properties;

import java.util.List;

import wroblicky.andrew.euterpe.properties.Property;

public interface PropertyDAO {
	
	void createPropertyTable();
	
	void insertProperty(Property property);
	
	List<Property> getProperties();
	
	Property getProperty(String key);
	
	void updateProperty(String key, String value);
	
	void deleteProperty(String key);
	
	void dropProperties();

}
