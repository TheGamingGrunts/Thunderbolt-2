package me.projectx.thunderbolt2.exceptions;

public class FileNotLoadedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FileNotLoadedException(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
