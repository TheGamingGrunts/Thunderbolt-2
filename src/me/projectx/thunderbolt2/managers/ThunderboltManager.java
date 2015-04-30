package me.projectx.thunderbolt2.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.projectx.thunderbolt2.Thunderbolt;
import me.projectx.thunderbolt2.exceptions.FileLoadException;
import me.projectx.thunderbolt2.models.ThunderFile;
import me.projectx.thunderbolt2.org.json.JSONObject;

public class ThunderboltManager implements Thunderbolt {
	
	private volatile Map<String, ThunderFile> fileMap = new HashMap<String, ThunderFile>();
	
	public ThunderFile get(String name){
		return fileMap.get(name);
	}
	
	private ThunderFile create(final String name, final String path) throws FileLoadException{
		if (fileMap.get(name) == null){
			ThunderFile tf = new ThunderFile(name, path);
			fileMap.put(name, tf);
			return tf;
		}else{
			throw new FileLoadException(name);
		}
	}
	
	public ThunderFile load(String name, String path) throws FileLoadException, IOException{
		if (fileMap.get(name) == null){
			File f = new File(path + File.separator + name + ".json");
			if (f.exists()){
				ThunderFile tf = null;
				if (f.length() != 0){
					BufferedReader br = new BufferedReader(new FileReader(f));
					String line;
					String jsonData = "";
					while ((line = br.readLine()) != null){
						jsonData += line + "\n";
					}
					br.close();
					tf = new ThunderFile(name, path, jsonData);
					JSONObject obj = tf.getJSONObject();
					Iterator<?> i = obj.keySet().iterator();
					while (i.hasNext()){
						String key = (String) i.next();
						tf.set(key, obj.get(key));
					}
				} 
				tf = (tf != null) ? tf : new ThunderFile(name, path);
				fileMap.put(name, tf);
				return tf;
			}else{
				return this.create(name, path);
			}
		}else{
			throw new FileLoadException(name);
		}
	}
	
	public void unload(final String name){
		new Thread(){
			public void run(){
				if (fileMap.containsKey(name)){
					fileMap.remove(name);
				}else{
					try {
						throw new FileNotFoundException("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.");
					} catch(FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void delete(final String name){
		new Thread(){
			public void run(){
				try{
					if (fileMap.containsKey(name)){
						ThunderFile tf = fileMap.get(name);
						Files.delete(Paths.get(tf.getPath() + File.separator + name + ".json"));
						fileMap.remove(name);
						tf = null;
					}else{
						throw new FileNotFoundException("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.");
					}
				} catch(IOException e){
					e.printStackTrace();
				}
			}
		}.start();
	}
}