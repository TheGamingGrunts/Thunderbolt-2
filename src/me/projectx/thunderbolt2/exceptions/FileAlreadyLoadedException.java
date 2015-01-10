package me.projectx.thunderbolt2.exceptions;

/**
 * An exception that's thrown if a ThunderFile is already loaded into memory
 * 
 * @author Daniel S. (The Gaming Grunts)
 */
public class FileAlreadyLoadedException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public FileAlreadyLoadedException(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
