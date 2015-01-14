package me.projectx.thunderbolt2.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.projectx.thunderbolt2.models.ThunderFile;
import me.projectx.thunderbolt2.org.json.JSONObject;

public abstract class FileManager {
	
	private Map<String, ThunderFile> fileMap = new HashMap<String, ThunderFile>();
	
	public synchronized ThunderFile get(String name){
		return fileMap.get(name);
	}
	
	private synchronized void create(String name, String path){
		if (get(name) == null){
			fileMap.put(name, new ThunderFile(name, path));
		}else{
			System.out.println("[Thunderbolt 2] The file '" + name + ".json' is already loaded!");
		}
	}
	
	protected synchronized ThunderFile load(String name, String path){
		if (get(name) == null){
			try {
				File f = new File(path + File.separator + name + ".json");
				if (f.exists()){
					if (f.length() != 0){
						BufferedReader br = new BufferedReader(new FileReader(f));
						String line;
						String jsonData = "";
						while ((line = br.readLine()) != null){
							jsonData += line + "\n";
						}
						br.close();
						JSONObject obj = new JSONObject(jsonData);
						Iterator<?> i = obj.keySet().iterator();
						ThunderFile tf = new ThunderFile(name, path);
						while (i.hasNext()){
							String key = (String) i.next();
							Object value = obj.get(key);
							tf.set(key, value);
						}
						fileMap.put(name, tf);
						System.out.println("[Thunderbolt 2] Loaded " + tf.getName() + ".json");	
						return tf;
					}
				}else{
					create(name, path);
					return get(name);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}	
		}else{
			System.out.println("[Thunderbolt 2] The file '" + name + ".json' is already loaded!");
		}
		return null;
	}
	
	protected synchronized void unload(String name){
		ThunderFile tf = get(name);
		if (tf != null){
			fileMap.remove(tf);
			tf = null;
		}else{
			System.out.println("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.");
		}
	}
	
	protected synchronized void delete(String name){
		ThunderFile tf = get(name);
		if (tf != null){
			try {
				fileMap.remove(name);
				Files.delete(Paths.get(tf.getPath() + File.separator + name + ".json"));
				tf = null;
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.");
		}
	}
}
