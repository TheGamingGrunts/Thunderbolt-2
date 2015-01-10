package me.projectx.thunderbolt2.managers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.projectx.thunderbolt2.exceptions.FileAlreadyLoadedException;
import me.projectx.thunderbolt2.exceptions.FileNotLoadedException;
import me.projectx.thunderbolt2.models.ThunderFile;
import me.projectx.thunderbolt2.org.json.JSONObject;
import me.projectx.thunderbolt2.org.json.simple.JSONParser;
import me.projectx.thunderbolt2.org.json.simple.ParseException;

public abstract class FileManager {
	
	private List<ThunderFile> files = new ArrayList<ThunderFile>();
	
	public ThunderFile get(String name){
		for (ThunderFile tf : files){
			if (tf.getName().equals(name)){
				return tf;
			}
		}
		return null;
	}
	
	private void create(String name, String path) throws FileAlreadyLoadedException{
		if (get(name) == null){
			files.add(new ThunderFile(name, path));
		}else{
			throw new FileAlreadyLoadedException("The File " + name + ".json is already loaded!");
		}
	}
	
	public void load(String name, String path) throws FileAlreadyLoadedException{
		if (get(name) == null){
			try {
				File f = new File(path + File.separator + name + ".json");
				if (f.exists()){
					if (f.length() != 0){
						JSONObject jobj = (JSONObject)new JSONParser().parse(new FileReader(f));
						ThunderFile tf = new ThunderFile(name, path);
						Iterator<?> i = jobj.keySet().iterator();
						while(i.hasNext()){
							String key = (String) i.next();
							Object value = jobj.get(key);
							tf.set(key, value);
						}
						files.add(tf);
						System.out.println("[Thunderbolt 2] Loaded " + tf.getName() + ".json");	
					}
				}else{
					create(name, path);
				}
			} catch(IOException | ParseException e) {
				e.printStackTrace();
			}	
		}else{
			throw new FileAlreadyLoadedException("The File " + name + ".json is already loaded!");
		}
	}
	
	protected void unload(String name) throws FileNotLoadedException{
		ThunderFile tf = get(name);
		if (tf != null){
			files.remove(tf);
			tf = null;
		}else{
			throw new FileNotLoadedException("The file " + name + ".json isn't loaded and/or doesn't exist.");
		}
	}
	
	protected void delete(String name) throws FileNotLoadedException{
		ThunderFile tf = get(name);
		if (tf != null){
			try {
				files.remove(tf);
				Files.delete(Paths.get(tf.getPath() + File.separator + name + ".json"));
				tf = null;
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else{
			throw new FileNotLoadedException("The file " + name + ".json isn't loaded and/or doesn't exist.");
		}
	}
}
