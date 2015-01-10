package me.projectx.thunderbolt2;

import me.projectx.thunderbolt2.exceptions.FileAlreadyLoadedException;
import me.projectx.thunderbolt2.exceptions.FileNotLoadedException;
import me.projectx.thunderbolt2.managers.FileManager;
import me.projectx.thunderbolt2.models.ThunderFile;

/**
 * The main class of Thunderbolt containing key accessor methods
 * 
 * @author Daniel S. (The Gaming Grunts)
 */
public class Thunderbolt extends FileManager {
	
	/**
	 * Get a file by its name. Doesn't require .json extension
	 * 
	 * @param name : The name of the file to get.
	 */
	public ThunderFile get(String name){
		return super.get(name);
	}
	
	/**
	 * Load a file into memory.
	 * 
	 * @param name : The name of the file to load.
	 * @param path : The path to the file.
	 * @throws FileAlreadyLoadedException 
	 */
	public void load(String name, String path) throws FileAlreadyLoadedException{
		super.load(name, path);
	}
	
	/**
	 * Unload a file from memory
	 * 
	 * @param name : The name of the file to unload
	 * @throws FileNotLoadedException
	 */
	public void unload(String name) throws FileNotLoadedException{
		super.unload(name);
	}
	
	/**
	 * Delete a file and remove it from memory
	 * 
	 * @param name : The name of the file to delete
	 * @throws FileNotLoadedException 
	 */
	public void delete(String name) throws FileNotLoadedException{
		super.delete(name);
	}
}
