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
	
	protected ThunderFile get(String name){
		for (ThunderFile tf : files){
			if (tf.getName().equalsIgnoreCase(name)){
				return tf;
			}
		}
		return null;
	}
	
	protected void load(String name, String path) throws FileAlreadyLoadedException{
		if (get(name) == null){
			final ThunderFile tf = new ThunderFile(name, path);
			files.add(tf);
			JSONParser parser = new JSONParser();
			try {
				final JSONObject jobj = (JSONObject)parser.parse(new FileReader(path + File.separator + name + ".json"));
				new Thread(){
					public void run(){
						if (jobj.size() != 0){
							Iterator<?> i = jobj.keySet().iterator();
							while(i.hasNext()){
								String key = (String) i.next();
								Object value = jobj.get(key);
								tf.set(key, value);
							}
							//files.add(tf);
							System.out.println("[Thunderbolt 2] Loaded " + tf.getName() + ".json");
							this.interrupt();
						}
					}
				}.start();	
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
