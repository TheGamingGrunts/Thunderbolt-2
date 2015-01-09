package me.projectx.thunderbolt2.exceptions;

public class FileAlreadyLoadedException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public FileAlreadyLoadedException(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
