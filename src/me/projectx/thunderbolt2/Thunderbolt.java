package me.projectx.thunderbolt2;

import me.projectx.thunderbolt2.managers.FileManager;
import me.projectx.thunderbolt2.models.ThunderFile;

/**
 * The main class of Thunderbolt containing key accessor methods
 * 
 * @author Daniel S. (The Gaming Grunts)
 */
public class Thunderbolt extends FileManager {
	
	/**
	 * Get a file by its name. Doesn't require .json extension. This method is thread-safe
	 * 
	 * @param name : The name of the file to get.
	 */
	public ThunderFile get(String name){
		return super.get(name);
	}
	
	/**
	 * Load a file into memory. This method is thread-safe
	 * 
	 * @param name : The name of the file to load.
	 * @param path : The path to the file.
	 */
	public void load(String name, String path){
		super.load(name, path);
	}
	
	/**
	 * Unload a file from memory. This method is thread-safe
	 * 
	 * @param name : The name of the file to unload
	 */
	public void unload(String name){
		super.unload(name);
	}
	
	/**
	 * Delete a file and remove it from memory. This method is thread-safe
	 * 
	 * @param name : The name of the file to delete
	 */
	public void delete(String name){
		super.delete(name);
	}
}
