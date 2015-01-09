package me.projectx.thunderbolt2;

import me.projectx.thunderbolt2.exceptions.FileAlreadyLoadedException;
import me.projectx.thunderbolt2.exceptions.FileNotLoadedException;
import me.projectx.thunderbolt2.managers.FileManager;
import me.projectx.thunderbolt2.models.ThunderFile;

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
	 */
	public void load(String name, String path){
		try {
			super.load(name, path);
		} catch(FileAlreadyLoadedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Unload a file from memory
	 * 
	 * @param name : The name of the file to unload
	 * @throws FileNotLoadedException
	 */
	public void unload(String name){
		try {
			super.unload(name);
		} catch(FileNotLoadedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete a file and remove it from memory
	 * 
	 * @param name : The name of the file to delete
	 */
	public void delete(String name){
		try {
			super.delete(name);
		} catch(FileNotLoadedException e) {
			e.printStackTrace();
		}
	}
}
