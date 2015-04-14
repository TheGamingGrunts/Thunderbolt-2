package me.projectx.thunderbolt2;

import java.io.IOException;

import me.projectx.thunderbolt2.exceptions.FileLoadException;
import me.projectx.thunderbolt2.models.ThunderFile;

/**
 * An interface specifying available methods for developers
 * 
 * @author Daniel S. (The Gaming Grunts)
 */
public interface Thunderbolt {
	
	/**
	 * Get a file by its name. Doesn't require .json extension. This method is thread-safe
	 * 
	 * @param name : The name of the file to get.
	 */
	public ThunderFile get(String name);
	
	
	/**
	 * Load a file into memory. 
	 * <p>
	 * NOTE: Make sure to call {@link Thunderbolt#unload(String)} when you're done using this file,
	 * otherwise a memory leak may occur!
	 * 
	 * @param name : The name of the file to load.
	 * @param path : The path to the file.
	 * @throws IOException 
	 * @throws FileLoadException 
	 */
	public ThunderFile load(String name, String path) throws IOException, FileLoadException;
	
	
	/**
	 * Unload a file from memory. 
	 * 
	 * @param name : The name of the file to unload
	 * @throws IllegalArgumentException
	 */
	public void unload(String name) throws IllegalArgumentException;
	
	/**
	 * Delete a file and remove it from memory.
	 * 
	 * @param name : The name of the file to delete
	 * @throws IOException 
	 */
	public void delete(String name) throws IOException;
}
