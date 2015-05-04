package me.projectx.thunderbolt2.utils;

public class Validator {
	
	/**
	 * Ensure that a file name doesn't contain a
	 * .json extension
	 * 
	 * @param name : The name of the file
	 * @return A file name without a .json file extension
	 */
	public static String checkName(String name){
		return (name.contains(".json")) ? name.substring(0, name.length() - 5) : name;
	}
}
