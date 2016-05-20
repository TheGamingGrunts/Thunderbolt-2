package io.bluecube.thunderbolt.utils;

public class Validator {
	
	/**
	 * Ensure that a file name doesn't contain an extension
	 * 
	 * @param name : The name of the file
	 * @return A file name without an extension
	 */
	public static String checkName(String name){
		return (name.contains(".")) ? name.split(".")[0] : name;
	}
}
