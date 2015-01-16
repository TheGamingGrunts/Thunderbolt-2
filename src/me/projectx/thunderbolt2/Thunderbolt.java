package me.projectx.thunderbolt2;

import java.io.IOException;

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
	 * <p>
	 * NOTE: Make sure to call {@link Thunderbolt#unload(String)} when you're done using this file,
	 * otherwise a memory leak may occur!
	 * 
	 * @param name : The name of the file to load.
	 * @param path : The path to the file.
	 * @throws IOException 
	 */
	public ThunderFile load(String name, String path) throws IOException{
		return super.load(name, path);
	}
	
	/**
	 * Unload a file from memory. This method is thread-safe
	 * 
	 * @param name : The name of the file to unload
	 * @throws IllegalArgumentException
	 */
	public void unload(String name) throws IllegalArgumentException{
		super.unload(name);
	}
	
	/**
	 * Delete a file and remove it from memory. This method is thread-safe
	 * 
	 * @param name : The name of the file to delete
	 * @throws IOException 
	 */
	public void delete(String name) throws IOException{
		super.delete(name);
	}
}
