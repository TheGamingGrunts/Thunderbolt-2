package me.projectx.thunderbolt2;

import me.projectx.thunderbolt2.exceptions.FileLoadException;
import me.projectx.thunderbolt2.models.ThunderFile;

import java.io.IOException;

/**
 * An interface specifying available methods for developers
 * 
 * @author Daniel S. (The Gaming Grunts)
 */
public interface Thunderbolt {
	
	/**
	 * Get a file by its name. Doesn't require .json extension. This method is thread-safe
	 *
     * <p>This method returns null if the file is not loaded</p>
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
	
	/**
	 * Delete a file on disk. 
	 * 
	 * @param name : The name of the file
	 * @param path : The path to the file
	 * @deprecated May be removed in the future in favor of a better technique
	 */
	public void delete(String name, String path);
}
