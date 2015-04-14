package me.projectx.thunderbolt2.exceptions;

public class FileLoadException extends Exception {

	private static final long serialVersionUID = 12930275846413931L;

	public FileLoadException(String name){
		super("[Thunderbolt 2] The file '" + name + ".json' is already loaded!");
	}
}
