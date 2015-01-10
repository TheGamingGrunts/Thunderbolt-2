package me.projectx.thunderbolt2.exceptions;

/**
 * An exception that's thrown if a file isn't loaded and/or doesn't exist
 * 
 * @author Daniel S. (The Gaming Grunts)
 */
public class FileNotLoadedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FileNotLoadedException(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
