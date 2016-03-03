package wroblicky.andrew.euterpe.dao;

import java.util.List;

import wroblicky.andrew.euterpe.Property;

public interface PropertyDAO {
	
	public void createPropertyTable();
	
	public void insertProperty(Property property);
	
	public List<Property> getProperties();
	
	public Property getProperty(String key);
	
	public void updateProperty(String key, String value);
	
	public void deleteProperty(String key);
	
	public void dropProperties();

}
